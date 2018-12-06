package com.github.february.rakuten.collector.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.github.february.rakuten.collector.bean.RakutenWebServiceUrl;

@Configuration
@ConfigurationProperties(prefix = "rakuten")
public class RakutenConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RakutenWebServiceUrl url;
	
	private String applicationId;
	
	private String affiliateId;
	
	private String format;
	
	private String formatVersion;

	public RakutenWebServiceUrl getUrl() {
		return url;
	}

	public void setUrl(RakutenWebServiceUrl url) {
		this.url = url;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAffiliateId() {
		return affiliateId;
	}

	public void setAffiliateId(String affiliateId) {
		this.affiliateId = affiliateId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormatVersion() {
		return formatVersion;
	}

	public void setFormatVersion(String formatVersion) {
		this.formatVersion = formatVersion;
	}

}
