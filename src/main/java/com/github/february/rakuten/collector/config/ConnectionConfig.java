package com.github.february.rakuten.collector.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {
	
	@Bean
	PoolingHttpClientConnectionManager connectionManager(@Value("${http.pool_max_total}") int maxTotal, @Value("${http.max_per_route}") int maxRoute) {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	    connectionManager.setMaxTotal(maxTotal);
	    connectionManager.setDefaultMaxPerRoute(maxRoute);
	    return connectionManager;
	}
	
	@Bean
	RequestConfig defaultRequestConfig(
			@Value("${http.conn_timeout}") int connTimeout,
			@Value("${http.read_timeout}") int readTimeout
	) {		        
		return RequestConfig.custom().setSocketTimeout(connTimeout).setConnectTimeout(connTimeout).setConnectionRequestTimeout(readTimeout).build();
	}

}
