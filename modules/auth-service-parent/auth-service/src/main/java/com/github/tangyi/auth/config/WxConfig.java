package com.github.tangyi.auth.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.github.tangyi.auth.properties.WxProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信相关配置
 *
 * @author zdz
 * @date 2022/04/14 12:27
 */
@Configuration
@AllArgsConstructor
public class WxConfig {

    /**
     * 微信的属性，如appId，appSecret，sessionHost
     */
    private final WxProperties wxProperties;


    /**
     * 微信相关配置类
     *
     * @return 微信相关配置类
     */
    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(wxProperties.getAppId());
        config.setSecret(wxProperties.getAppSecret());
        return config;
    }

    /**
     * 微信相关service类
     *
     * @param maConfig 微信相关配置
     * @return 微信相关service
     */
    @Bean
    public WxMaService wxMaService(WxMaConfig maConfig) {
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(maConfig);
        return service;
    }

}

