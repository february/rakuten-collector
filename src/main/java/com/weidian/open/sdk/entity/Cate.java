package com.weidian.open.sdk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cate {
	
	@JsonProperty("sort_num")
	private int sortNum;

	@JsonProperty("parent_id")
	private String parentId;
	
	@JsonProperty("cate_id")
	private String cateId;

	@JsonProperty("cate_name")
	private String cateName;
	
	@JsonProperty("parent_cate_name")
	private String parentCateName;

	@JsonProperty("cate_item_num")
	private String cateItemNum;
	
	@JsonProperty("child_cate_item_sum")
	private String childCateItemSum;
	
	@JsonProperty("listUrl")
	private String listUrl;
	
	@JsonProperty("shopLogo")
	private String shopLogo;
	
	@JsonProperty("shopName")
	private String shopName;
	
	@JsonProperty("description")
	private String description;

	@JsonProperty("child_cate")
	private Cate[] childCate;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getParentCateName() {
		return parentCateName;
	}

	public void setParentCateName(String parentCateName) {
		this.parentCateName = parentCateName;
	}

	public String getCateItemNum() {
		return cateItemNum;
	}

	public void setCateItemNum(String cateItemNum) {
		this.cateItemNum = cateItemNum;
	}

	public String getChildCateItemSum() {
		return childCateItemSum;
	}

	public void setChildCateItemSum(String childCateItemSum) {
		this.childCateItemSum = childCateItemSum;
	}

	public String getListUrl() {
		return listUrl;
	}

	public void setListUrl(String listUrl) {
		this.listUrl = listUrl;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Cate[] getChildCate() {
		return childCate;
	}

	public void setChildCate(Cate[] childCate) {
		this.childCate = childCate;
	}
}
