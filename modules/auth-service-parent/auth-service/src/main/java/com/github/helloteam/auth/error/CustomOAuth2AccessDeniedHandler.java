package com.github.helloteam.auth.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Oauth2的访问拒绝处理类
 *
 * @author zdz
 * @date 2022/04/14 22:29
 */
@Slf4j
public class CustomOAuth2AccessDeniedHandler extends AbstractOAuth2SecurityExceptionHandler implements AccessDeniedHandler {

    /**
     * 网页响应异常转译器
     */
    private WebResponseExceptionTranslator<?> exceptionTranslator = new DefaultWebResponseExceptionTranslator();

    /**
     * 自定义OAuth2异常渲染类
     */
    private CustomOAuth2ExceptionRenderer exceptionRenderer = new CustomOAuth2ExceptionRenderer();

    /**
     * 处理逻辑
     *
     * @param request       Http请求
     * @param response      Http响应
     * @param authException 访问拒绝异常
     */
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) throws IOException {
        try {
            // 将访问拒绝异常通过转译器转译成响应实体
            ResponseEntity<?> result = exceptionTranslator.translate(authException);
            result = enhanceResponse(result, authException);
            exceptionRenderer.handleResponseBeanResponse(result, new ServletWebRequest(request, response));
            response.flushBuffer();
        } catch (ServletException e) {
            log.error(e.getMessage(), e);
        } catch (IOException | RuntimeException e) {
            throw e;
        } catch (Exception e) {
            // Wrap other Exceptions. These are not expected to happen
            throw new RuntimeException(e);
        }
    }

}
