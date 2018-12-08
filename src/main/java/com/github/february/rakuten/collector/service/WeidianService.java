package com.github.february.rakuten.collector.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	private static final Log logger = LogFactory.getLog(WeidianService.class);
	
	@Autowired
    private AccessTokenHistoryRepo accessTokenHistoryRepo;
	
	public void refreshAccessToken() throws Exception {
		logger.info("Refresh AccessToken from Weidian");
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
	
	public synchronized String getAccessToken() throws Exception {
		String result = null;
		AccessTokenHistory param = new AccessTokenHistory();
		param.setSite(Site.WEIDIAN.getValue());
		AccessTokenHistory resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
		if(resp == null) {
			refreshAccessToken();
			resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
		} else {
			Date now = new Date();
			Date lastTime = resp.getCreateTime();
			
			long nd = 1000*24*60*60;
			long nh = 1000*60*60;
			long diff = now.getTime() - lastTime.getTime();
			long hour = diff%nd/nh;
			if(hour > 5) {
				refreshAccessToken();
				resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
			}
		}
		result = resp.getToken();
		return result;
	}
	
	

}
