package com.github.february.rakuten.collector.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.bean.Site;
import com.github.february.rakuten.collector.entity.AccessTokenHistory;
import com.github.february.rakuten.collector.repository.AccessTokenHistoryRepo;
import com.weidian.open.sdk.oauth.OAuth;
import com.weidian.open.sdk.response.oauth.OAuthResponse;
import com.weidian.open.sdk.util.JsonUtils;

@Component
public final class WeidianService {
	
	@Autowired
    private AccessTokenHistoryRepo accessTokenHistoryRepo;
	
	public void refreshAccessToken() throws Exception {
		OAuth oauth = OAuth.getInstance();
		OAuthResponse response = oauth.getPersonalToken();
		if(response.getStatus().getStatusCode() == 0) {
			Map<?, ?> resp = JsonUtils.toObject(response.toString(), Map.class);
			AccessTokenHistory entity = new AccessTokenHistory();
			entity.setSite(Site.WEIDIAN.getValue());
			entity.setToken((String)((Map<?, ?>)resp.get("result")).get("access_token"));
			entity.setCreateTime(new Date());
			accessTokenHistoryRepo.save(entity);
		}
	}

}
