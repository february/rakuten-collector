package com.github.february.rakuten.collector.bean;

public enum Site {
	
	WEIDIAN("WEIDIAN");
	
	private String value;
	
	private Site(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
