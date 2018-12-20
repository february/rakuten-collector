package com.github.february.rakuten.collector.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.february.rakuten.collector.bean.Brand;

@Configuration
@ConfigurationProperties
public class BrandConfig {

	private List<Brand> brands;

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	
	@Bean
	Map<String, String> brandsMap() {
		Map<String, String> brandsMap = new HashMap<String, String>();
		for(Brand brand : brands) {
			brandsMap.put(brand.getJp(), brand.getCn());
		}
		
		return brandsMap;
	}
	
}
