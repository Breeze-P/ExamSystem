package com.github.tangyi.oss.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * fastDfs配置
 *
 * @author zdz
 * @date 2022/04/11 19:48
 */
@Deprecated
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@ConditionalOnExpression("!'${fdfs}'.isEmpty()")
public class FastDfsConfig {
// 解决jmx重复注册bean的问题

}

