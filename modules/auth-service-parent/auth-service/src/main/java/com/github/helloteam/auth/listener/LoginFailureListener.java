package com.github.helloteam.auth.listener;

import com.github.helloteam.common.security.event.CustomAuthenticationFailureEvent;
import com.github.helloteam.user.api.feign.UserServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 登录失败事件监听器
 *
 * @author zdz
 * @date 2022/04/14 22:56
 */
@Slf4j
@AllArgsConstructor
@Component
public class LoginFailureListener implements ApplicationListener<CustomAuthenticationFailureEvent> {

    /**
     * 用户service客户端
     */
    private final UserServiceClient userServiceClient;

    /**
     * 处理逻辑
     *
     * @param event 认证登录失败事件
     */
    @Override
    public void onApplicationEvent(CustomAuthenticationFailureEvent event) {
        // 登录失败后的处理
    }

}
