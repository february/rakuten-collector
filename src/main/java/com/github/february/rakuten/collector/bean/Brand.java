package com.github.february.rakuten.collector.bean;

import java.io.Serializable;

public class Brand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jp;
	
	private String cn;

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

}
