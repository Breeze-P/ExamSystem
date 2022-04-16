package com.github.helloteam.common.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务间调用携带Authorization、Tenant-Code请求头
 *
 * @author zdz
 * @date 2022/04/11 20:37
 */
@Configuration
public class CustomFeignConfig implements RequestInterceptor {

    /**
     * 请求头中认证token的标识
     */
    private static final String TOKEN_HEADER = "authorization";

    /**
     * 请求头中租户code的标识
     */
    private static final String TENANT_HEADER = "Tenant-Code";


    /**
     * 利用请求模板自定义请求，
     * 将从请求头信息中获取到的认证token标识和租户code标识存到自定义请求的请求头中
     *
     * @param requestTemplate 请求模板
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            requestTemplate.header(TOKEN_HEADER, getHeaders(request).get(TOKEN_HEADER));
            requestTemplate.header(TENANT_HEADER, getHeaders(request).get(TENANT_HEADER));
        }
    }

    /**
     * 获取请求
     *
     * @return 请求
     */
    private HttpServletRequest getHttpServletRequest() {
        try {
            // hystrix隔离策略会导致RequestContextHolder.getRequestAttributes()返回null
            // 解决方案：http://www.itmuch.com/spring-cloud-sum/hystrix-threadlocal/
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null)
                return attributes.getRequest();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取请求的请求头信息，并返回存储请求头信息的map
     *
     * @param request 请求
     * @return 存储请求头信息的map
     */
    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

}
