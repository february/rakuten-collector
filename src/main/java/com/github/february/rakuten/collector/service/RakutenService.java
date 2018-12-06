package com.github.february.rakuten.collector.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.february.rakuten.collector.bean.RakutenIchibaItemSearchResult;
import com.github.february.rakuten.collector.config.RakutenConfig;

/**
 * 乐天市场访问服务
 * @author zhanghao
 *
 */
@Component
public class RakutenService {

	@Autowired
	RakutenConfig rakutenConfig;

	@Autowired
	RestTemplate restTemplate;

	public RakutenIchibaItemSearchResult ichibaItemSearch() throws JsonParseException, JsonMappingException, IOException {
		String url = rakutenConfig.getUrl().getIchibaItem();
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("applicationId", rakutenConfig.getApplicationId());
		uriVariables.put("affiliateId", rakutenConfig.getAffiliateId());
		uriVariables.put("format", rakutenConfig.getFormat());
		uriVariables.put("formatVersion", rakutenConfig.getFormatVersion());
		uriVariables.put("keyword", "キッズ スニーカー");
		uriVariables.put("shopCode", "abc-mart");

		RakutenIchibaItemSearchResult result = restTemplate.postForObject(url, uriVariables, RakutenIchibaItemSearchResult.class);

		return result;
	}

}
