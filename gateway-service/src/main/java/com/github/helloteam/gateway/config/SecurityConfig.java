package com.github.helloteam.gateway.config;

import com.github.helloteam.common.core.properties.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * oauth2 client配置
 *
 * @author zdz
 * @date 2022/04/11 21:49
 */
@Configuration
@Import(FilterIgnorePropertiesConfig.class)
@EnableWebFluxSecurity
public class SecurityConfig {

	@Autowired
	private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		String[] ignores = new String[filterIgnorePropertiesConfig.getUrls().size()];
		http
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers(filterIgnorePropertiesConfig.getUrls().toArray(ignores)).permitAll()
				.anyExchange().authenticated();
		http.oauth2ResourceServer().jwt();
		return http.build();
	}

}
