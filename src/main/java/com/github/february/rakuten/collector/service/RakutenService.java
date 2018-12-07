package com.github.february.rakuten.collector.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.february.rakuten.collector.bean.RakutenIchibaItemSearchParam;
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

	public RakutenIchibaItemSearchResult ichibaItemSearch(RakutenIchibaItemSearchParam param) throws JsonParseException, JsonMappingException, IOException {
		String url = rakutenConfig.getUrl().getIchibaItem();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);  
		@SuppressWarnings("unchecked")
		Map<String, Object> uriVariables = mapper.readValue(mapper.writeValueAsString(param), Map.class);
		uriVariables.put("applicationId", rakutenConfig.getApplicationId());
		uriVariables.put("affiliateId", rakutenConfig.getAffiliateId());
		uriVariables.put("format", rakutenConfig.getFormat());
		uriVariables.put("formatVersion", rakutenConfig.getFormatVersion());
		RakutenIchibaItemSearchResult result = restTemplate.postForObject(url, uriVariables, RakutenIchibaItemSearchResult.class);
		return result;
	}

}
