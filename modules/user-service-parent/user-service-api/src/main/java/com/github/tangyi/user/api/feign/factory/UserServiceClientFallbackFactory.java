package com.github.tangyi.user.api.feign.factory;

import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.user.api.feign.fallback.UserServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户断路器工厂类
 *
 * @author zdz
 * @date 2022/04/15 00:25
 */
@Component
public class UserServiceClientFallbackFactory implements FallbackFactory<UserServiceClient> {

    /**
     * 创建逻辑
     * @return 用户service客户端实例
     */
    @Override
    public UserServiceClient create(Throwable throwable) {
        UserServiceClientFallbackImpl userServiceClientFallback = new UserServiceClientFallbackImpl();
        userServiceClientFallback.setThrowable(throwable);
        return userServiceClientFallback;
    }

}
