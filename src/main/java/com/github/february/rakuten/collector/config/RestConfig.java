package com.github.february.rakuten.collector.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.github.february.rakuten.collector.handler.RakutenResponseErrorHandler;

@Configuration
public class RestConfig {
	
	@Autowired
	RakutenResponseErrorHandler rakutenResponseErrorHandler;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		return restTemplate;
	}

}
