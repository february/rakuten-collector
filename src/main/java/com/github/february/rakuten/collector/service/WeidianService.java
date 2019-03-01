package com.github.february.rakuten.collector.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.bean.Site;
import com.github.february.rakuten.collector.entity.AccessTokenHistory;
import com.github.february.rakuten.collector.repository.AccessTokenHistoryRepo;
import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.entity.Cate;
import com.weidian.open.sdk.entity.Item;
import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.oauth.OAuth;
import com.weidian.open.sdk.request.product.MediaUploadRequest;
import com.weidian.open.sdk.request.product.VdianItemAddRequest;
import com.weidian.open.sdk.request.product.VdianItemDeleteRequest;
import com.weidian.open.sdk.request.product.WeidianCateGetListRequest;
import com.weidian.open.sdk.request.product.WeidianItemSearchRequest;
import com.weidian.open.sdk.response.AbstractResponse;
import com.weidian.open.sdk.response.CommonResponse;
import com.weidian.open.sdk.response.oauth.OAuthResponse;
import com.weidian.open.sdk.response.product.VdianCateGetListResponse;
import com.weidian.open.sdk.response.product.VdianItemSearchResponse;
import com.weidian.open.sdk.util.JsonUtils;

@Component
public final class WeidianService {

	private static final Log logger = LogFactory.getLog(WeidianService.class);

	@Autowired
	private AccessTokenHistoryRepo accessTokenHistoryRepo;
	
	@Autowired
	AbstractWeidianClient client;

	private void refreshAccessToken() throws Exception {
		logger.info("Refresh AccessToken from Weidian");
		OAuth oauth = OAuth.getInstance();
		OAuthResponse response = oauth.getPersonalToken();
		if (response.getStatus().getStatusCode() == 0) {
			Map<?, ?> resp = JsonUtils.toObject(response.toString(), Map.class);
			AccessTokenHistory entity = new AccessTokenHistory();
			entity.setSite(Site.WEIDIAN.getValue());
			entity.setToken((String) ((Map<?, ?>) resp.get("result")).get("access_token"));
			entity.setCreateTime(new Date());
			accessTokenHistoryRepo.save(entity);
		}
	}

	private synchronized String getAccessToken() throws Exception {
		AccessTokenHistory param = new AccessTokenHistory();
		param.setSite(Site.WEIDIAN.getValue());
		AccessTokenHistory resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
		if (resp == null) {
			refreshAccessToken();
			resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
		} else {
			Date now = new Date();
			Date lastTime = resp.getCreateTime();

			long nd = 1000 * 24 * 60 * 60;
			long nh = 1000 * 60 * 60;
			long diff = now.getTime() - lastTime.getTime();
			long hour = diff % nd / nh;
			if (hour > 5) {
				refreshAccessToken();
				resp = accessTokenHistoryRepo.findTop1BySiteOrderByCreateTimeDesc(Site.WEIDIAN.getValue());
			}
		}
		return resp.getToken();
	}

	private byte[] getBytes(String path) throws Exception {
		byte[] data = null;
		InputStream in = new FileInputStream(path);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		return data;
	}

	public String[] uploadImage(String[] paths) throws Exception {
		List<String> urlList = new ArrayList<String>();
		String accessToken = this.getAccessToken();
		for(String path : paths) {
			try {
				MediaUploadRequest request = new MediaUploadRequest(accessToken, this.getBytes(path));
				AbstractResponse response = client.multipart(request);
				if (response.getStatus().getStatusCode() == 0) {
					Map<?, ?> resp = JsonUtils.toObject(response.toString(), Map.class);
					String url = (String) resp.get("result");
					urlList.add(url);
					logger.info(path + " uploaded successfully and forward to " + url);
				} else {
					logger.error(path + " upload failed and cause of " + response.getStatus().getStatusReason());
				}
			} catch (Exception ex) {
				logger.error(path + " upload failed and cause of " + ex.getMessage());
			}
		}
		return urlList.toArray(new String[0]);
	}
	
	public Cate[] getCategories(boolean showNoCate) throws Exception {
		Cate[] result = new Cate[0];
		String accessToken = this.getAccessToken();
		try {
			VdianCateGetListResponse response = client.executePost(new WeidianCateGetListRequest(accessToken, showNoCate));
			if (response.getStatus().getStatusCode() == 0) {
				result = response.getResult();
			} else {
	        	logger.error("get shop categories failed and cause of " + response.getStatus().getStatusReason());
	        }
		} catch (OpenException e) {
	    	  logger.error("get shop categories failed and cause of " + e.getMessage());
	      }	   
		return result;
	}
	
	public void addItem(Item item) throws Exception {
		String accessToken = this.getAccessToken();
	    try {
	        AbstractResponse response = client.executePost(new VdianItemAddRequest(accessToken, item));
	        if (response.getStatus().getStatusCode() == 0) {
	        	Map<?, ?> resp = JsonUtils.toObject(response.toString(), Map.class);
	        	@SuppressWarnings("unchecked")
				Map<String, String> result = (Map<String, String>) resp.get("result");
	        	String itemId = result.get("itemid");
	        	logger.info(item.getItemName() + " put on sale successfully and itemid is " + itemId);
	        } else {
	        	logger.error(item.getItemName() + " put on sale failed and cause of " + response.getStatus().getStatusReason());
	        }
	      } catch (OpenException e) {
	    	  logger.error(item.getItemName() + " put on sale failed and cause of " + e.getMessage());
	      }	    
	}
	
	public Item[] searchItem(String keyWord) throws Exception {
		List<Item> result = new ArrayList<Item>();
		String accessToken = this.getAccessToken();
	    try {
	    	String fx = "1";
	    	int page = 1;
	    	int pageSize = 30;
	    	int total = 0;
	    	boolean isContinue = true;
	    	while(isContinue) {
	    		logger.info(keyWord + " search at page " + page);
	    		VdianItemSearchResponse response = client.executeGet(new WeidianItemSearchRequest(accessToken, fx, keyWord, String.valueOf(page), String.valueOf(pageSize)));
		        if (response.getStatus().getStatusCode() == 0) {
		        	result.addAll(Arrays.asList(response.getResult().getItems()));
		        	total = response.getResult().getTotal();
		        	if((page * pageSize) < total) {
		        		page ++;
		        	} else {
		        		isContinue = false;
		        	}		        	
		        } else {
		        	logger.error(keyWord + " search failed and cause of " + response.getStatus().getStatusReason());
		        }
	    	}	    	
	    } catch (OpenException e) {
	    	logger.error(keyWord + " search failed and cause of " + e.getMessage());
	    	throw e;
	    }
	    
	    return result.toArray(new Item[0]);
	}
	
	public void removeItem(Item item) throws Exception {
		String accessToken = this.getAccessToken();
		try {
			CommonResponse response = client.executeGet(new VdianItemDeleteRequest(accessToken, item.getItemId()));
			if (response.getStatus().getStatusCode() != 0) {
	        	logger.error(item.getItemId() + " remove item failed and cause of " + response.getStatus().getStatusReason());
	        }
		} catch (OpenException e) {
	    	logger.error(item.getItemId() + " remove item failed and cause of " + e.getMessage());
	    	throw e;
	    }
		
	}
}