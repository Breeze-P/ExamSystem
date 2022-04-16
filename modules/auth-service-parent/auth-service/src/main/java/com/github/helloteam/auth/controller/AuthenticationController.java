package com.github.helloteam.auth.controller;

import com.github.helloteam.common.core.constant.CommonConstant;
import com.github.helloteam.common.core.exceptions.CommonException;
import com.github.helloteam.common.core.model.ResponseBean;
import com.github.helloteam.common.core.web.BaseController;
import com.nimbusds.jose.jwk.JWKSet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Authentication管理Controller
 *
 * @author zdz
 * @date 2022/04/14 12:10
 */
@RestController
@RequestMapping("/v1/authentication")
public class AuthenticationController extends BaseController {

    /**
     * 用户token service
     */
    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    /**
     * JWT Secret k-v set
     */
    @Autowired
    private JWKSet jwkSet;

    /**
     * 用户信息校验
     *
     * @param authentication 校验信息
     * @return 用户主体
     */
    @RequestMapping("user")
    public Object user(Authentication authentication) {
        return authentication.getPrincipal();
    }

    /**
     * 清除用户的access_token
     *
     * @param request 处理请求
     * @return 包含请求处理结果的响应Bean
     */
    @PostMapping("removeToken")
    public ResponseBean<Boolean> removeToken(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(accessToken)) {
            throw new CommonException("accessToken为空.");
        }
        if (accessToken.startsWith(CommonConstant.AUTHORIZATION_BEARER)) {
            accessToken = accessToken.split(CommonConstant.AUTHORIZATION_BEARER)[1];
        }
        return new ResponseBean<>(consumerTokenServices.revokeToken(accessToken));
    }

    /**
     * 获取JWT K—V集合
     *
     * @return Map K-V集合
     */
    @GetMapping("/jwks.json")
    public Map<String, Object> keys() {
        return this.jwkSet.toJSONObject();
    }

}
