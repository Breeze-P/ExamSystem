package com.github.helloteam.exam.config;

import com.github.helloteam.common.core.constant.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ配置
 *
 * @author zdz
 * @date 2022/04/16 14:21
 */
@Configuration
public class RabbitSubmitExaminationConfig {

    /**
     * 提交考试队列
     *
     * @return 考试队列
     */
    @Bean
    public Queue submitExaminationQueue() {
        return new Queue(MqConstant.SUBMIT_EXAMINATION_QUEUE);
    }

}
