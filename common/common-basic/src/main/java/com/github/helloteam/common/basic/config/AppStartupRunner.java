package com.github.helloteam.common.basic.config;

import com.github.helloteam.common.basic.properties.SysProperties;
import com.github.helloteam.common.core.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 系统启动时的一些处理操作
 *
 * @author zdz
 * @date 2022/04/10 14:50
 */
@Slf4j
@AllArgsConstructor
@Component
public class AppStartupRunner implements CommandLineRunner {

    /**
     * 系统属性配置
     */
    private final SysProperties sysProperties;

    /**
     * 设置系统属性
     */
    @Override
    public void run(String... args) throws Exception {
        if (StringUtils.isNotBlank(sysProperties.getCacheExpire())) {
            System.setProperty(CommonConstant.CACHE_EXPIRE, sysProperties.getCacheExpire());
        }
    }
}
