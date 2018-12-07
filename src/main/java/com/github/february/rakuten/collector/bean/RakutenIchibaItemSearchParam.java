package com.github.february.rakuten.collector.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class RakutenIchibaItemSearchParam implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String applicationId;
	private String affiliateId;
	private String format;
	private String callback;
	private String elements;
	private Integer formatVersion;
	
	private String keyword;
	private String shopCode;
	private String itemCode;
	private Long genreId;
	private Long tagId;
	private Integer hits;
	private Integer page;
	private String sort;
	private Long minPrice;
	private Long maxPrice;
	private Integer availability;
	private Integer field;
	private Integer carrier;
	private Integer imageFlag;
	private Integer orFlag;
	@JsonProperty("NGKeyword")
	private String ngKeyword;
	private Integer purchaseType;
	private Integer shipOverseasFlag;
	private String shipOverseasArea;
	private Integer asurakuFlag;
	private Integer asurakuArea;
	private Integer pointRateFlag;
	private Integer pointRate;
	private Integer postageFlag;
	private Integer creditCardFlag;
	private Integer giftFlag;
	private Integer hasReviewFlag;
	private Float maxAffiliateRate;
	private Float minAffiliateRate;
	private Integer hasMovieFlag;
	private Integer pamphletFlag;
	private Integer appointDeliveryDateFlag;
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
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getElements() {
		return elements;
	}
	public void setElements(String elements) {
		this.elements = elements;
	}
	public Integer getFormatVersion() {
		return formatVersion;
	}
	public void setFormatVersion(Integer formatVersion) {
		this.formatVersion = formatVersion;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Long getGenreId() {
		return genreId;
	}
	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}
	public Long getTagId() {
		return tagId;
	}
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	public Integer getHits() {
		return hits;
	}
	public void setHits(Integer hits) {
		this.hits = hits;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Long getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}
	public Long getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = availability;
	}
	public Integer getField() {
		return field;
	}
	public void setField(Integer field) {
		this.field = field;
	}
	public Integer getCarrier() {
		return carrier;
	}
	public void setCarrier(Integer carrier) {
		this.carrier = carrier;
	}
	public Integer getImageFlag() {
		return imageFlag;
	}
	public void setImageFlag(Integer imageFlag) {
		this.imageFlag = imageFlag;
	}
	public Integer getOrFlag() {
		return orFlag;
	}
	public void setOrFlag(Integer orFlag) {
		this.orFlag = orFlag;
	}
	public String getNgKeyword() {
		return ngKeyword;
	}
	public void setNgKeyword(String ngKeyword) {
		this.ngKeyword = ngKeyword;
	}
	public Integer getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(Integer purchaseType) {
		this.purchaseType = purchaseType;
	}
	public Integer getShipOverseasFlag() {
		return shipOverseasFlag;
	}
	public void setShipOverseasFlag(Integer shipOverseasFlag) {
		this.shipOverseasFlag = shipOverseasFlag;
	}
	public String getShipOverseasArea() {
		return shipOverseasArea;
	}
	public void setShipOverseasArea(String shipOverseasArea) {
		this.shipOverseasArea = shipOverseasArea;
	}
	public Integer getAsurakuFlag() {
		return asurakuFlag;
	}
	public void setAsurakuFlag(Integer asurakuFlag) {
		this.asurakuFlag = asurakuFlag;
	}
	public Integer getAsurakuArea() {
		return asurakuArea;
	}
	public void setAsurakuArea(Integer asurakuArea) {
		this.asurakuArea = asurakuArea;
	}
	public Integer getPointRateFlag() {
		return pointRateFlag;
	}
	public void setPointRateFlag(Integer pointRateFlag) {
		this.pointRateFlag = pointRateFlag;
	}
	public Integer getPointRate() {
		return pointRate;
	}
	public void setPointRate(Integer pointRate) {
		this.pointRate = pointRate;
	}
	public Integer getPostageFlag() {
		return postageFlag;
	}
	public void setPostageFlag(Integer postageFlag) {
		this.postageFlag = postageFlag;
	}
	public Integer getCreditCardFlag() {
		return creditCardFlag;
	}
	public void setCreditCardFlag(Integer creditCardFlag) {
		this.creditCardFlag = creditCardFlag;
	}
	public Integer getGiftFlag() {
		return giftFlag;
	}
	public void setGiftFlag(Integer giftFlag) {
		this.giftFlag = giftFlag;
	}
	public Integer getHasReviewFlag() {
		return hasReviewFlag;
	}
	public void setHasReviewFlag(Integer hasReviewFlag) {
		this.hasReviewFlag = hasReviewFlag;
	}
	public Float getMaxAffiliateRate() {
		return maxAffiliateRate;
	}
	public void setMaxAffiliateRate(Float maxAffiliateRate) {
		this.maxAffiliateRate = maxAffiliateRate;
	}
	public Float getMinAffiliateRate() {
		return minAffiliateRate;
	}
	public void setMinAffiliateRate(Float minAffiliateRate) {
		this.minAffiliateRate = minAffiliateRate;
	}
	public Integer getHasMovieFlag() {
		return hasMovieFlag;
	}
	public void setHasMovieFlag(Integer hasMovieFlag) {
		this.hasMovieFlag = hasMovieFlag;
	}
	public Integer getPamphletFlag() {
		return pamphletFlag;
	}
	public void setPamphletFlag(Integer pamphletFlag) {
		this.pamphletFlag = pamphletFlag;
	}
	public Integer getAppointDeliveryDateFlag() {
		return appointDeliveryDateFlag;
	}
	public void setAppointDeliveryDateFlag(Integer appointDeliveryDateFlag) {
		this.appointDeliveryDateFlag = appointDeliveryDateFlag;
	}

}
