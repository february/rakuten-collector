package com.github.february.rakuten.collector.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class BrandConfig {

	private Map<String, String> brands;

	public Map<String, String> getBrands() {
		return brands;
	}

	public void setBrands(Map<String, String> brands) {
		this.brands = brands;
	}

}
