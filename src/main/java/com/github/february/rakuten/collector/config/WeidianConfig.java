package com.github.february.rakuten.collector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.DefaultWeidianClient;

@Configuration
public class WeidianConfig {
	
	@Bean
	AbstractWeidianClient client() {
		return DefaultWeidianClient.getInstance();
	}

}
