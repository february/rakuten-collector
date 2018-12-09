package com.github.february.rakuten.collector.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.http.HttpService;
import com.weidian.open.sdk.http.Param;
import com.weidian.open.sdk.util.SystemConfig;

@Component
public class DefaultHttpService implements HttpService {
	
  private static final Log logger = LogFactory.getLog(DefaultHttpService.class);

  @Autowired
  RequestConfig defaultRequestConfig;
	
  @Autowired
  CloseableHttpClient httpClient;
  
  @Value("${browser.tempFolder}")
  String tempFolder;

  @Override
  public String get(String url) throws OpenException {
    return this.httpExecute(new HttpGet(url), url);
  }
  
  public static String getRandomFileName(String extName) {
		SimpleDateFormat simpleDateFormat;
		simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = simpleDateFormat.format(new Date());
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		return str + rannum + "." + extName.toLowerCase();
  }
  
	public String[] downloadJpg(String[] urls) throws Exception {
		return download(urls, "jpg");
	}
	
	public String downloadJpg(String url) throws Exception {
		return download(url, "jpg");
	}
	
	public String[] download(String[] urls, String extName) throws Exception {
		List<String> filePathList = new ArrayList<String>();
		for(String url : urls) {
			try {
				filePathList.add(download(url, extName));
			} catch (Exception ex) {
				logger.error(url + " download failed and cause of " + ex.getMessage());
			}
		}
		return filePathList.toArray(new String[0]);
	}
  
  private String download(String url, String extName) throws Exception {
		String filePath = this.tempFolder + "/" + getRandomFileName(extName);
		
		HttpGet httpGet=new HttpGet(url);
        
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity=response.getEntity();
        try {
        	if(httpEntity!=null){
                InputStream inputStream=httpEntity.getContent();
                FileUtils.copyToFile(inputStream, new File(filePath)); 
                logger.info(url + " downloaded successfully and save to " + filePath);
            }
        } catch (Exception ex) {
        	throw ex;
        } finally {
        	response.close();
        }
        
		return filePath;
	}

  @Override
  public String post(String url, Param... params) throws OpenException {
    HttpPost post = new HttpPost(url);
    if (params != null && params.length > 0) {
      List<NameValuePair> parameters = new ArrayList<NameValuePair>(params.length);
      for (Param p : params) {
        parameters.add(new BasicNameValuePair(p.getName(), p.getValue()));
      }
      post.setEntity(new UrlEncodedFormEntity(parameters, Charset.forName(SystemConfig.ENC_UTF8)));
    }
    return this.httpExecute(post, url);
  }

  @Override
  public String multipart(String url, String name, byte[] content) throws OpenException {
    HttpPost httpPost = new HttpPost(url);
    HttpEntity entity = MultipartEntityBuilder.create().addPart(name, new ByteArrayBody(content, "media.jpg")).build();
    httpPost.setEntity(entity);
    return httpExecute(httpPost, url);
  }

  private String httpExecute(HttpRequestBase request, String url) throws OpenException {
    request.setConfig(RequestConfig.copy(defaultRequestConfig).build());
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(request);
      if (response == null) {
        throw new OpenException("http response is null|url:" + url);
      }

      StatusLine status = response.getStatusLine();
      int code = status.getStatusCode();
      if (code != 200) {
        throw new OpenException("http response code is " + code + "|reason:" + status.getReasonPhrase() + "|url:" + url);
      }

      HttpEntity entity = response.getEntity();
      if (entity == null) {
        throw new OpenException("http response entity is null|url:" + url);
      }

      return EntityUtils.toString(response.getEntity(), SystemConfig.ENC_UTF8);
    } catch (ClientProtocolException e) {
      throw new OpenException("http client protocol exception|url:" + url, e);
    } catch (IOException e) {
      throw new OpenException("http io exception|url:" + url, e);
    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          // ignore
        }
      }
    }
  }

}
