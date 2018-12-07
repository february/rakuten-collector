package com.github.february.rakuten.collector.bean;

import java.io.Serializable;

public final class RakutenWebServiceUrl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ichibaItem;

	public String getIchibaItem() {
		return ichibaItem;
	}

	public void setIchibaItem(String ichibaItem) {
		this.ichibaItem = ichibaItem;
	}

}
