package com.github.february.rakuten.collector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.february.rakuten.collector.pool.WebClientPool;

@Component
public class BrowserService {
	
	@Autowired
	WebClientPool webClientPool;
	
	public String getPage(String url) throws Exception {
		WebClient webClient = webClientPool.borrowObject();		
        HtmlPage page = null;
        page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30000);
        webClientPool.returnObject(webClient);
        return page.asXml();
	}

}
