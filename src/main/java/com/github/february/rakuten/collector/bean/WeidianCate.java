package com.github.february.rakuten.collector.bean;

import java.io.Serializable;
import java.util.List;

public class WeidianCate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private List<WeidianCate> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WeidianCate> getChildren() {
		return children;
	}

	public void setChildren(List<WeidianCate> children) {
		this.children = children;
	}

}
