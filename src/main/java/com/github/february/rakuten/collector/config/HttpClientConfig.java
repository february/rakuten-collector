package com.github.february.rakuten.collector.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {
	
	@Autowired
	PoolingHttpClientConnectionManager connectionManager;
	
	@Autowired
	RequestConfig defaultRequestConfig;
	
	@Bean
	CloseableHttpClient httpClient() {
		CloseableHttpClient httpClient =
		        HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig)
		            .build();
		return httpClient;
	}

}
