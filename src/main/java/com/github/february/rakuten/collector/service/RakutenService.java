package com.github.february.rakuten.collector.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.february.rakuten.collector.bean.IchibaItemSearchResult;
import com.github.february.rakuten.collector.config.RakutenConfig;

@Component
public class RakutenService {

	@Autowired
	RakutenConfig rakutenConfig;

	@Autowired
	RestTemplate restTemplate;

	public void ichibaItemSearch() throws JsonParseException, JsonMappingException, IOException {
		String url = rakutenConfig.getUrl().getIchibaItem();
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("applicationId", rakutenConfig.getApplicationId());
		uriVariables.put("affiliateId", rakutenConfig.getAffiliateId());
		uriVariables.put("format", rakutenConfig.getFormat());
		uriVariables.put("formatVersion", rakutenConfig.getFormatVersion());
		uriVariables.put("keyword", "addidas");

		IchibaItemSearchResult result = restTemplate.postForObject(url, uriVariables, IchibaItemSearchResult.class);

		System.out.println(result.getCount());
	}

}
