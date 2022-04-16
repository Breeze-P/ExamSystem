package com.github.helloteam.common.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.helloteam.common.security.handler.CustomAccessDeniedHandler;
import com.github.helloteam.common.security.mobile.MobileSecurityConfigurer;
import com.github.helloteam.common.core.properties.FilterIgnorePropertiesConfig;
import com.github.helloteam.common.security.wx.WxSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 资源服务器配置
 *
 * @author zdz
 * @date 2022/04/11 19:58
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * 资源ID
     */
    private static final String RESOURCE_ID = "resource_id";

    /**
     * 开放权限的URL
     */
    private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    /**
     * 手机登录配置
     */
    private final MobileSecurityConfigurer mobileSecurityConfigurer;

    /**
     * 微信登录配置
     */
    private final WxSecurityConfigurer wxSecurityConfigurer;

    /**
     * Object Mapper
     */
    private final ObjectMapper objectMapper;

    /**
     * 手机登录、微信登录相关配置，以及不进行拦截的URL配置
     *
     * @param filterIgnorePropertiesConfig 不进行拦截的URL配置
     * @param mobileSecurityConfigurer     手机登录相关配置
     * @param wxSecurityConfigurer         微信登录相关配置
     * @param objectMapper                 Object Mapper
     */
    @Autowired
    public CustomResourceServerConfig(FilterIgnorePropertiesConfig filterIgnorePropertiesConfig,
                                      MobileSecurityConfigurer mobileSecurityConfigurer,
                                      WxSecurityConfigurer wxSecurityConfigurer,
                                      ObjectMapper objectMapper) {
        this.filterIgnorePropertiesConfig = filterIgnorePropertiesConfig;
        this.mobileSecurityConfigurer = mobileSecurityConfigurer;
        this.wxSecurityConfigurer = wxSecurityConfigurer;
        this.objectMapper = objectMapper;
    }

    /**
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
        resources.accessDeniedHandler(accessDeniedHandler());
    }

    /**
     * @param http
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] ignores = new String[filterIgnorePropertiesConfig.getUrls().size()];
        http.csrf()
                .disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(filterIgnorePropertiesConfig.getUrls().toArray(ignores)).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        // 手机号登录
        http.apply(mobileSecurityConfigurer);
        // 微信登录
        http.apply(wxSecurityConfigurer);
    }

    /**
     * 处理拒绝访问异常的Handler
     *
     * @return 处理拒绝访问异常的Handler
     */
    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler(objectMapper);
    }

}