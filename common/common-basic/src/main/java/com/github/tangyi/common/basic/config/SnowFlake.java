package com.github.tangyi.common.basic.config;

import com.github.tangyi.common.basic.properties.SnowflakeProperties;
import com.github.tangyi.common.core.utils.SnowflakeIdWorker;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID生成配置
 *
 * @author zdz
 * @date 2022/04/10 14:53
 */
@Configuration
@AllArgsConstructor
public class SnowFlake {

	/**
	 * SnowFlake属性
	 */
	private final SnowflakeProperties snowflakeProperties;

	@Bean
	public SnowflakeIdWorker initTokenWorker() {
		return new SnowflakeIdWorker(Integer.parseInt(snowflakeProperties.getWorkId()),
				Integer.parseInt(snowflakeProperties.getDataCenterId()));
	}

}
