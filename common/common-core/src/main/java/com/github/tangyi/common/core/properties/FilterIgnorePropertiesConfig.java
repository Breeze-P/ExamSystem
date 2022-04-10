package com.github.tangyi.common.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储不进行权限拦截操作的URL
 *
 * @author zdz
 * @date 2022/04/10 15:16
 */
@Data
@Configuration
@RefreshScope
@ConditionalOnExpression("!'${ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "ignore")
public class FilterIgnorePropertiesConfig {

    /**
     * URL
     */
    private List<String> urls = new ArrayList<>();

    /**
     * Client名
     */
    private List<String> clients = new ArrayList<>();

}
