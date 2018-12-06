package com.github.february.rakuten.collector.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.february.rakuten.collector.bean.IchibaItemSearchResult;
import com.github.february.rakuten.collector.config.RakutenConfig;

@Component
public class ApiService {
	
	@Autowired
	RakutenConfig rakutenConfig;

	@Autowired
	RestTemplate restTemplate;
	
	public void run() {
		String url = rakutenConfig.getUrl().getIchibaItem();
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("applicationId", rakutenConfig.getApplicationId());
		uriVariables.put("affiliateId", rakutenConfig.getAffiliateId());
		uriVariables.put("format", rakutenConfig.getFormat());
		uriVariables.put("formatVersion", rakutenConfig.getFormatVersion());
		uriVariables.put("keyword", "addidas");
		restTemplate.postForObject(url, uriVariables, IchibaItemSearchResult.class);
	}

}
