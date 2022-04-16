package com.github.helloteam.common.security.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录成功事件
 *
 * @author zdz
 * @date 2022/04/11 20:13
 */
@Data
public class CustomAuthenticationSuccessEvent extends ApplicationEvent {

    /**
     * 进行登录操作的用户信息
     */
    private UserDetails userDetails;

    /**
     * 构造器
     *
     * @param authentication 认证信息
     * @param userDetails    登录的用户信息
     */
    public CustomAuthenticationSuccessEvent(Authentication authentication, UserDetails userDetails) {
        super(authentication);
        this.userDetails = userDetails;
    }

}
