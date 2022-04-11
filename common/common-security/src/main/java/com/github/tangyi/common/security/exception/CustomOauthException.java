package com.github.tangyi.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.tangyi.common.security.serializer.CustomOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义的OauthException
 *
 * @author zdz
 * @date 2022/04/11 19:56
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg) {
        super(msg);
    }

}
