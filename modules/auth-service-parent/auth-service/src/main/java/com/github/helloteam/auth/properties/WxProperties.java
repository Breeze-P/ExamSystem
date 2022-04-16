package com.github.helloteam.auth.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信配置
 *
 * @author zdz
 * @date 2022/04/14 12:27
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "wx")
public class WxProperties {

    /**
     * 微信id
     */
    private String appId;

    /**
     * 微信密码
     */
    private String appSecret;

    /**
     * 授权类型
     */
    private String grantType;

}
