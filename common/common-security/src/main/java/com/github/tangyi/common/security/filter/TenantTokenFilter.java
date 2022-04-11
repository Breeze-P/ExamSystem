package com.github.tangyi.common.security.filter;

import com.github.tangyi.common.security.constant.SecurityConstant;
import com.github.tangyi.common.security.tenant.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取请求头里的租户code
 *
 * @author zdz
 * @date 2022/04/11 20:14
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantTokenFilter implements Filter {

    /**
     * 重写doFilter操作，获取请求头中的租户code
     * @param servletRequest 请求
     * @param servletResponse 响应
     * @param filterChain filter chain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 获取请求头里的TENANT_CODE
        String tenantCode = request.getHeader(SecurityConstant.TENANT_CODE_HEADER);
        // 没有携带tenantCode则采用默认的tenantCode
        if (tenantCode == null) {
            tenantCode = SecurityConstant.DEFAULT_TENANT_CODE;
        }
        TenantContextHolder.setTenantCode(tenantCode);
        filterChain.doFilter(request, response);
        TenantContextHolder.clear();
    }

}
