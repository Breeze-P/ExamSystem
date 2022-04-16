package com.github.helloteam.common.log;

import com.github.helloteam.common.log.aspect.LogAspect;
import com.github.helloteam.common.log.event.LogListener;
import com.github.helloteam.user.api.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 日志配置信息类
 *
 * @author zdz
 * @date 2022/04/11 15:49
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    @Autowired
    private UserServiceClient userServiceClient;

    @Bean
    public LogListener sysLogListener() {
        return new LogListener(userServiceClient);
    }

    @Bean
    public LogAspect sysLogAspect() {
        return new LogAspect();
    }

}
