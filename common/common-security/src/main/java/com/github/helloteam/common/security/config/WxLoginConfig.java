package com.github.helloteam.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.helloteam.common.security.core.CustomUserDetailsService;
import com.github.helloteam.common.security.wx.WxLoginSuccessHandler;
import com.github.helloteam.common.security.wx.WxSecurityConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 微信登录相关配置
 *
 * @author zdz
 * @date 2022/04/11 20:06
 */
@Configuration
public class WxLoginConfig {

    /**
     * 配置微信登录
     *
     * @return WxSecurityConfigurer
     */
    @Bean
    public WxSecurityConfigurer wxSecurityConfigurer(
            @Lazy PasswordEncoder encoder, @Lazy ClientDetailsService clientDetailsService,
            @Lazy CustomUserDetailsService userDetailsService, @Lazy ObjectMapper objectMapper,
            @Lazy AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        WxSecurityConfigurer wxSecurityConfigurer = new WxSecurityConfigurer();
        wxSecurityConfigurer.setWxLoginSuccessHandler(wxLoginSuccessHandler(encoder, clientDetailsService, objectMapper, defaultAuthorizationServerTokenServices));
        wxSecurityConfigurer.setUserDetailsService(userDetailsService);
        return wxSecurityConfigurer;
    }

    /**
     * 微信登录成功后的处理
     *
     * @return AuthenticationSuccessHandler 认证成功事件处理器
     */
    @Bean
    public AuthenticationSuccessHandler wxLoginSuccessHandler(
            PasswordEncoder encoder, ClientDetailsService clientDetailsService, ObjectMapper objectMapper,
            AuthorizationServerTokenServices defaultAuthorizationServerTokenServices) {
        return WxLoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .clientDetailsService(clientDetailsService)
                .passwordEncoder(encoder)
                .defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
    }

}
