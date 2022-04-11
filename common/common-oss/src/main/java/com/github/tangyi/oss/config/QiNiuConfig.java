package com.github.tangyi.oss.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 七牛云配置
 *
 * @author zdz
 * @date 2022/04/11 19:46
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "qiniu")
@ConditionalOnExpression("!'${qiniu}'.isEmpty()")
public class QiNiuConfig {

	/**
	 * 访问Key
	 */
	private String accessKey;

	/**
	 * 秘钥
	 */
	private String secretKey;

	/**
	 *
	 */
	private String bucket;

	/**
	 * 外部访问域名
	 */
	private String domainOfBucket;

	/**
	 * 链接超时时间，单位秒
	 */
	private Integer expire;

}
