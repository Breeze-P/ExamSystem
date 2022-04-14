package com.github.tangyi.auth.security;

import com.github.tangyi.common.basic.enums.LoginTypeEnum;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.security.tenant.TenantContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展JwtAccessTokenConverter，增加租户code
 *
 * @author zdz
 * @date 2022/04/14 23:02
 */
public class CustomTokenConverter extends JwtAccessTokenConverter {

    /**
     * RSA签名器
     */
    private RsaSigner signer;

    /**
     * 自定义Header
     */
    private Map<String, String> customHeaders;

    /**
     * Json信息解析器
     */
    private JsonParser objectMapper = JsonParserFactory.create();

    /**
     * 构造器
     *
     * @param keyPair       k-v对
     * @param customHeaders 自定义的信息头
     */
    public CustomTokenConverter(KeyPair keyPair, Map<String, String> customHeaders) {
        super();
        super.setKeyPair(keyPair);
        this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
        this.customHeaders = customHeaders;
    }

    /**
     * 编码逻辑
     *
     * @param accessToken    访问token
     * @param authentication 认证信息
     * @return 经过编码的信息
     */
    @Override
    protected String encode(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String content;
        try {
            content = this.objectMapper.formatMap(getAccessTokenConverter().convertAccessToken(accessToken, authentication));
        } catch (Exception ex) {
            throw new IllegalStateException("Cannot convert access token to JSON", ex);
        }
        return JwtHelper.encode(content, this.signer, this.customHeaders).getEncoded();
    }

    /**
     * 增强访问token，即在访问token中添加部分额外信息
     *
     * @param accessToken    访问token
     * @param authentication 认证信息
     * @return 经过增强后的token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 存储需要添加的额外信息
        final Map<String, Object> additionalInfo = new HashMap<>();
        // 获取此次访问的授权类型（用户名/手机号/微信）
        String grantType = authentication.getOAuth2Request().getGrantType();
        // 在额外信息中加入tenantCode
        additionalInfo.put("tenantCode", TenantContextHolder.getTenantCode());
        // 在额外信息中加入登录类型（用户名/手机号/微信）
        String loginType = "";
        if (grantType.equalsIgnoreCase(CommonConstant.GRANT_TYPE_PASSWORD)) {
            loginType = LoginTypeEnum.PWD.getType();
        } else if (grantType.equalsIgnoreCase(CommonConstant.GRANT_TYPE_MOBILE)) {
            loginType = LoginTypeEnum.SMS.getType();
        } else if (grantType.equalsIgnoreCase(LoginTypeEnum.WECHAT.getType())) {
            loginType = LoginTypeEnum.WECHAT.getType();
        }
        additionalInfo.put("loginType", loginType);
        // 在用户的访问token中添加上这些额外信息
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(accessToken, authentication);
    }

}
