package com.github.february.rakuten.collector.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.february.rakuten.collector.pool.WebClientPool;

@Component
@ConfigurationProperties
public class HttpService {
	
	@Autowired
	WebClientPool webClientPool;
	
	@Value("${browser.tempFolder}")
	String tempFolder;
	
	public String getPage(String url) throws Exception {
		WebClient webClient = webClientPool.borrowObject();		
        HtmlPage page = null;
        page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30000);
        webClientPool.returnObject(webClient);
        return page.asXml();
	}
	
	public String[] downloadJpg(String[] urls) throws Exception {
		return download(urls, "jpg");
	}
	
	public String downloadJpg(String url) throws Exception {
		return download(url, "jpg");
	}
	
	public String[] download(String[] urls, String extName) throws Exception {
		List<String> filePathList = new ArrayList<String>();
		WebClient webClient = webClientPool.borrowObject();
		try {
			for(String url : urls) {
				System.out.println(url);
				filePathList.add(download(webClient, url, extName));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			webClientPool.returnObject(webClient);
		}
		return filePathList.toArray(new String[0]);
	}
	
	public String download(String url, String extName) throws Exception {
		String filePath = null;
		WebClient webClient = webClientPool.borrowObject();
		try {
			filePath = download(webClient, url, extName);
		} catch (Exception ex) {
			throw ex;
		} finally {
			webClientPool.returnObject(webClient);
		}
		return filePath;
	}
	
	private String download(WebClient webClient, String url, String extName) throws Exception {
		Page page = webClient.getPage(url);
		InputStream contentAsStream = page.getWebResponse().getContentAsStream();
		String filePath = this.tempFolder + "/" + getRandomFileName(extName);
		OutputStream outputStream = new FileOutputStream(filePath);
		IOUtils.write(IOUtils.readFully(contentAsStream,(int)page.getWebResponse().getContentLength()), outputStream);
		webClient.close();
		return filePath;
	}
	
	public static String getRandomFileName(String extName) {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = simpleDateFormat.format(new Date());
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		return str + rannum + "." + extName.toLowerCase();
	}


}
