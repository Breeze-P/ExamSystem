package com.github.helloteam.user.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 利用DefaultKaptcha验证码配置信息类
 *
 * @author zdz
 * @date 2022/04/16 11:05
 */
@Configuration
public class ValidateCodeConfig {

    /**
     * 配置验证码相关设定
     *
     * @return 验证码生成器实例
     */
    @Bean
    public DefaultKaptcha producer() {
        // 验证码相关配置
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        // 验证码字体颜色
        properties.put("kaptcha.textproducer.font.color", "black");
        // 验证码字体大小
        properties.put("kaptcha.textproducer.font.size", "40");
        // 验证码文本字符间距
        properties.put("kaptcha.textproducer.char.space", "6");
        // 验证码文本字符长度
        properties.put("kaptcha.textproducer.char.length", "4");
        // 验证码宽度
        properties.put("kaptcha.image.width", "120");
        // 验证码高度
        properties.put("kaptcha.image.height", "50");

        // 生成自定义的验证码生成器实例
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
