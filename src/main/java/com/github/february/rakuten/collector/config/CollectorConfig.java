package com.github.february.rakuten.collector.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gargoylesoftware.htmlunit.WebClient;
import com.github.february.rakuten.collector.pool.PooledWebClientFactory;
import com.github.february.rakuten.collector.pool.WebClientPool;

@Configuration
@ConfigurationProperties
public class CollectorConfig {
	
	@Autowired
	PooledWebClientFactory webClientFactory;
	
	@Bean
	public WebClientPool webClientPool(
			@Value("${browser.webclient.pool.maxTotal}") int maxTotal, 
			@Value("${browser.webclient.pool.maxIdle}") int maxIdle,
			@Value("${browser.webclient.pool.minIdle}") int minIdle,
			@Value("${browser.webclient.pool.maxWaitMillis}") int maxWaitMillis) {
        GenericObjectPoolConfig<WebClient> config = new GenericObjectPoolConfig<WebClient>();
        config.setJmxEnabled(false);
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        return new WebClientPool(webClientFactory, config);
	}

}
