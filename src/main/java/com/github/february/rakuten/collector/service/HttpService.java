package com.github.february.rakuten.collector.service;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.february.rakuten.collector.pool.WebClientPool;

@Component
@ConfigurationProperties
public class HttpService {
	
	private static final Log logger = LogFactory.getLog(HttpService.class);
	
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
		for(String url : urls) {
			try {
				filePathList.add(download(webClient, url, extName));
			} catch (Exception ex) {
				logger.error(url + " download failed and cause of " + ex.getMessage());
			}
		}
		webClientPool.returnObject(webClient);
		return filePathList.toArray(new String[0]);
	}
	
	public String download(String url, String extName) throws Exception {
		String filePath = null;
		WebClient webClient = webClientPool.borrowObject();
		try {
			filePath = download(webClient, url, extName);
		} catch (Exception ex) {
			logger.error(url + " download failed and cause of " + ex.getMessage());
		} finally {
			webClientPool.returnObject(webClient);
		}
		return filePath;
	}
	
	private String download(WebClient webClient, String url, String extName) throws Exception {
		String filePath = this.tempFolder + "/" + getRandomFileName(extName);
        try {
            DataInputStream dataInputStream = new DataInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(context);
            dataInputStream.close();
            fileOutputStream.close();
            logger.info(url + " downloaded successfully and save to " + filePath);
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
        	throw e;
        } finally {
        	webClient.close();
        }		
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
