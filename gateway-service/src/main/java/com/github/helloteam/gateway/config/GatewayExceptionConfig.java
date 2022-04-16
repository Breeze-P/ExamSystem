package com.github.helloteam.gateway.config;

import com.github.helloteam.gateway.handler.GatewayExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * 网关异常处理
 *
 * @author zdz
 * @date 2022/04/11 21:50
 */
@Configuration
public class GatewayExceptionConfig {

    /**
     * 自定义异常处理
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(
            ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodeConfigurer) {
        GatewayExceptionHandler gatewayExceptionHandler = new GatewayExceptionHandler();
        gatewayExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        gatewayExceptionHandler.setMessageWriters(serverCodeConfigurer.getWriters());
        gatewayExceptionHandler.setMessageReaders(serverCodeConfigurer.getReaders());
        return gatewayExceptionHandler;
    }

}
