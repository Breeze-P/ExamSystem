package com.github.helloteam.auth.error;

import com.github.helloteam.common.core.constant.ApiMsg;
import com.github.helloteam.common.core.model.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.http.converter.jaxb.JaxbOAuth2ExceptionMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义异常renderer类
 *
 * @author zdz
 * @date 2022/04/14 22:17
 */
@Slf4j
public class CustomOAuth2ExceptionRenderer {

    /**
     * Http消息转换器
     */
    private List<HttpMessageConverter<?>> messageConverters = geDefaultMessageConverters();

    /**
     * 处理响应实体的响应，将网页请求信息封装到Http请求/响应消息中
     *
     * @param responseEntity 响应实体
     * @param webRequest 网页请求
     */
    public void handleResponseBeanResponse(ResponseEntity<?> responseEntity, ServletWebRequest webRequest) throws Exception {
        if (responseEntity == null) {
            return;
        }
        // 通过原生的网页请求，生成Http请求和Http响应，之后作为输出的请求和响应消息
        HttpInputMessage inputMessage = createHttpInputMessage(webRequest);
        HttpOutputMessage outputMessage = createHttpOutputMessage(webRequest);
        // 将Http响应消息的状态码设置为响应实体的状态码
        if (outputMessage instanceof ServerHttpResponse) {
            ((ServerHttpResponse) outputMessage).setStatusCode(responseEntity.getStatusCode());
        }
        // 响应实体的消息头不为空，将Http响应消息的消息头设置为响应实体的消息头
        HttpHeaders entityHeaders = responseEntity.getHeaders();
        if (!entityHeaders.isEmpty()) {
            outputMessage.getHeaders().putAll(entityHeaders);
        }
        // 若响应实体的消息体不为空，则将Http响应消息的消息体设置为响应实体的消息体
        Object body = responseEntity.getBody();
        if (body != null) {
            // 返回ResponseBean
            ResponseBean<?> responseBean = new ResponseBean<>(body, ApiMsg.KEY_ACCESS, ApiMsg.DENIED);
            writeWithMessageConverters(responseBean, inputMessage, outputMessage);
        } else {
            // flush headers
            outputMessage.getBody();
        }
    }

    /**
     *
     * @param returnValue 返回值
     * @param inputMessage Http请求消息
     * @param outputMessage Http响应消息
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void writeWithMessageConverters(Object returnValue, HttpInputMessage inputMessage, HttpOutputMessage outputMessage)
            throws IOException, HttpMediaTypeNotAcceptableException {
        List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptedMediaTypes.isEmpty()) {
            acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptedMediaTypes);
        Class<?> returnValueType = returnValue.getClass();
        List<MediaType> allSupportedMediaTypes = new ArrayList<>();
        for (MediaType acceptedMediaType : acceptedMediaTypes) {
            for (HttpMessageConverter messageConverter : messageConverters) {
                if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
                    messageConverter.write(returnValue, acceptedMediaType, outputMessage);
                    if (log.isDebugEnabled()) {
                        MediaType contentType = outputMessage.getHeaders().getContentType();
                        if (contentType == null) {
                            contentType = acceptedMediaType;
                        }
                        log.debug("Written [" + returnValue + "] as \"" + contentType + "\" using [" + messageConverter + "]");
                    }
                    return;
                }
            }
        }
        for (HttpMessageConverter messageConverter : messageConverters) {
            allSupportedMediaTypes.addAll(messageConverter.getSupportedMediaTypes());
        }
        throw new HttpMediaTypeNotAcceptableException(allSupportedMediaTypes);
    }

    /**
     * 获取默认的消息转化器
     *
     * @return Http消息转化器
     */
    private List<HttpMessageConverter<?>> geDefaultMessageConverters() {
        List<HttpMessageConverter<?>> result = new ArrayList<>(new RestTemplate().getMessageConverters());
        result.add(new JaxbOAuth2ExceptionMessageConverter());
        return result;
    }

    /**
     * 将原生的网页请求转化成Http请求
     *
     * @param webRequest 原生的网页请求
     * @return Http请求
     */
    private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest) {
        return new ServletServerHttpRequest(webRequest.getNativeRequest(HttpServletRequest.class));
    }

    /**
     * 将原生的网页请求转化成Http响应
     *
     * @param webRequest 原生的网页请求
     * @return Http响应
     */
    private HttpOutputMessage createHttpOutputMessage(NativeWebRequest webRequest) {
        return new ServletServerHttpResponse((HttpServletResponse) webRequest.getNativeResponse());
    }

}
