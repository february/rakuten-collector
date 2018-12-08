package com.github.february.rakuten.collector.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weidian.open.sdk.util.SystemConfig;

@Configuration
public class HttpClientConfig {
	
	@Autowired
	PoolingHttpClientConnectionManager connectionManager;
	
	@Autowired
	RequestConfig defaultRequestConfig;
	
	@Bean
	PoolingHttpClientConnectionManager connectionManager() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	    connectionManager.setMaxTotal(SystemConfig.HTTP_POOL_MAX_TOTAL);
	    connectionManager.setDefaultMaxPerRoute(SystemConfig.HTTP_MAX_PER_ROUTE);
	    return connectionManager;
	}
	
	@Bean
	RequestConfig defaultRequestConfig() {
		RequestConfig defaultRequestConfig =
		        RequestConfig.custom().setSocketTimeout(SystemConfig.HTTP_CONNECTION_TIMEOUT)
	            .setConnectTimeout(SystemConfig.HTTP_CONNECTION_TIMEOUT)
	            .setConnectionRequestTimeout(SystemConfig.HTTP_READ_TIMEOUT).build();
		return defaultRequestConfig;
	}
	
	@Bean
	CloseableHttpClient httpClient() {
		CloseableHttpClient httpClient =
		        HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig(defaultRequestConfig)
		            .build();
		return httpClient;
	}

}
