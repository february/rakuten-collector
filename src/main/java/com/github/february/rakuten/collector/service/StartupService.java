package com.github.february.rakuten.collector.service;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weidian.open.sdk.AbstractWeidianClient;
import com.weidian.open.sdk.DefaultWeidianClient;

@Component
public final class StartupService {
	
	private static final Log logger = LogFactory.getLog(StartupService.class);
	
	@Autowired
	DefaultHttpService httpService;

	@PostConstruct
    public void run() throws Exception {
		logger.info("Prepare settings for startup");
		AbstractWeidianClient client = DefaultWeidianClient.getInstance();
		client.setHttpService(httpService);
	}

}
