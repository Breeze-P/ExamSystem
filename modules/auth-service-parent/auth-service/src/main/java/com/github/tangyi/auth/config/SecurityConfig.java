package com.github.tangyi.auth.config;

import com.github.tangyi.auth.error.CustomOAuth2AccessDeniedHandler;
import com.github.tangyi.auth.security.CustomUserDetailsAuthenticationProvider;
import com.github.tangyi.common.security.core.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Spring Security配置
 *
 * @author zdz
 * @date 2022/04/14 12:21
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户信息service
     */
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Authorization服务器端点配置
     */
    @Autowired
    private AuthorizationServerEndpointsConfiguration endpoints;

    /**
     * 配置Spring Security
     *
     * @param http http security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 前后端分离，关闭csrf (Cross-Site request forgery)跨站请求伪造
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated();
        // accessDeniedHandler
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    /**
     * 实例化并返回一个Authentication Manager Bean对象
     *
     * @return Authentication Manager Bean对象
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 实例化并返回一个密码编码器实例
     *
     * @return 密码编码器实例
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证Provider，提供获取用户信息、认证、授权等功能
     *
     * @return Authentication Provider
     */
    @Bean
    public AuthenticationProvider authProvider() {
        return new CustomUserDetailsAuthenticationProvider(encoder(), userDetailsService);
    }

    /**
     * 实例化并返回一个自定义的访问拒绝处理器实例
     *
     * @return 自定义的访问拒绝处理器实例
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomOAuth2AccessDeniedHandler();
    }

}

