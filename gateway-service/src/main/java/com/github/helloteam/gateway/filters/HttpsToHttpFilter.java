package com.github.helloteam.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * 将HTTPS改为HTTP
 * 网关进来的协议是HTTPS，其它服务一般部署在内网，没必要走HTTPS
 *
 * @author zdz
 * @date 2022/04/11 22:03
 */
@Component
public class HttpsToHttpFilter implements GlobalFilter, Ordered {

    /**
     * 该处理操作的Order级别
     */
    private static final int HTTPS_TO_HTTP_FILTER_ORDER = 10099;

    /**
     * 处理逻辑
     * @param exchange exchange
     * @param chain Filter chain
     * @return Mono
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 原始URI
        URI originalUri = exchange.getRequest().getURI();
        // 当前请求
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String forwardedUri = request.getURI().toString();
        if (forwardedUri != null && forwardedUri.startsWith("https")) {
            // 将Https转化为Http，其他信息不变
            try {
                URI mutatedUri = new URI("http",
                        originalUri.getUserInfo(),
                        originalUri.getHost(),
                        originalUri.getPort(),
                        originalUri.getPath(),
                        originalUri.getQuery(),
                        originalUri.getFragment());
                mutate.uri(mutatedUri);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        }
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    /**
     * LoadBalancerClientFilter的order是10100
     * 要在LoadBalancerClientFilter执行之前将HTTPS修改为HTTP，则这里的order设置为10099
     *
     * @return int
     */
    @Override
    public int getOrder() {
        return HTTPS_TO_HTTP_FILTER_ORDER;
    }

}
