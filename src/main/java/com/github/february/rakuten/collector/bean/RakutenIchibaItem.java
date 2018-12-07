package com.github.february.rakuten.collector.bean;

import java.io.Serializable;

public final class RakutenIchibaItem implements Serializable {

	private static final long serialVersionUID = 1L;

	// 商品名: 従来の商品名を表示させたい場合は、「catchcopy＋itemname」で表示してください。
	// ※キャリア(carrier)の指定により返却情報が異なります。
	private String itemName;
	// 商品名: 従来の商品名を表示させたい場合は、「catchcopy＋itemname」で表示してください。
	// ※キャリア(carrier)の指定により返却情報が異なります。
	private String catchcopy;
	// 商品コード
	private String itemCode;
	// 商品価格
	private int itemPrice;
	// 商品説明文: ※キャリア(carrier)の指定により返却情報が異なります。
	private String itemCaption;
	// 商品URL: ※キャリア(carrier)の指定により返却情報が異なります。
	// ※仕様変更により、2015/07/01から、
	// 入力パラメーターにアフィリエイトIDが
	// 含まれていた場合、affiliateUrlと
	// 同じ値を返却いたします。 
	// httpsではじまる商品のURL
	private String itemUrl;
	// アフィリエイトURL: （入力パラメーターにアフィリエイトIDが含まれていた時のみ）
	// ※carrierパラメーターの指定に関わらずPC/mobile両対応のURLを返却 
	// httpsではじまるアフィリエイトのURL
	private String affiliateUrl;
	// 商品画像有無フラグ: 0：商品画像無し 1：商品画像有り
	private String imageFlag;
	// 商品画像64x64URL: 最大3枚の画像（画像サイズ64px*64px）をimageUrl の配列で返却 httpsではじまる商品画像64x64のURL
	private String[] smallImageUrls;
	// 商品画像128x128URL: 最大3枚の画像（画像サイズ128px*128px）を imageUrl の配列で返却 httpsではじまる商品画像128x128のURL
	private String[] mediumImageUrls;	
	// 販売可能フラグ: 0：販売不可能 1：販売可能
	private int availability;
	// 消費税フラグ: 0：税込	1：税別
	private int taxFlag;
	// 送料フラグ: 0：送料込	1：送料別
	private int postageFlag;
	// クレジットカード利用可能フラグ: 0：カード利用不可 1：カード利用可
	private int creditCardFlag;
	// ショップオブザイヤーフラグ: 0：ショップオブザイヤー未受賞店舗 1：ショップオブザイヤー受賞店舗
	private int shopOfTheYearFlag;
	// 海外配送フラグ: 0：海外配送不可 1：海外配送可能
	private int shipOverseasFlag;
	// 海外配送対象地域: 「/」(スラッシュ)区切りで対応国が表示されます。
	private String shipOverseasArea;
	// あす楽フラグ: 0：翌日配送不可	1：翌日配送可能
	// ※「あす楽」の詳細はこちらをご覧ください
	private int asurakuFlag;
	// あす楽〆時間: HH:MM という形式で返却します。
	// ※「あす楽フラグ」が1の場合のみ返却します。
	private String asurakuClosingTime;
	// あす楽配送対象地域: 「/」(スラッシュ)区切りで対応地域が表示されます。
	// ※「あす楽フラグ」が1の場合のみ返却します。
	private String asurakuArea;
	// アフィリエイト利用利率
	private Double affiliateRate;
	// 販売開始時刻: タイムセールが設定されている場合のみ（YYYY-MM-DD HH:MM形式）
	private String startTime;
	// 販売終了時刻: タイムセールが設定されている場合のみ（YYYY-MM-DD HH:MM形式）
	private String endTime;
	// レビュー件数
	private int reviewCount;
	// レビュー平均
	private Double reviewAverage;
	// 商品別ポイント倍付け: 例）5 →ポイント5倍
	// 商品別ポイント倍付けについてはこちらをご確認ください。
	// ※ポイント倍付けの終了日時がリクエスト日時から24時間後以降の場合のみ表示されます。
	private Double pointRate;
	// 商品別ポイント倍付け開始日時: 商品別ポイント倍付け(pointRate)の適用開始日時
	// ※ポイント倍付けの終了日時がリクエスト日時から24時間後以降の場合のみ表示されます。
	private String pointRateStartTime;
	// 商品別ポイント倍付け終了日時: 商品別ポイント倍付け(pointRate)の適用終了日時
	// ※ポイント倍付けの終了日時がリクエスト日時から24時間後以降の場合のみ表示されます。
	private String pointRateEndTime;
	// ギフト包装フラグ: 0: ギフト包装不可能	1: ギフト包装可能
	private int giftFlag;
	// 店舗名
	private String shopName;
	// 店舗コード: 店舗ごとのURL （http://www.rakuten.co.jp/[xyz]）	におけるxyzのこと
	private String shopCode;
	// 店舗URL: httpsからはじまる店舗ごとのURL
	private String shopUrl;
	// 店舗アフィリエイトURL: （入力パラメーターにアフィリエイトIDが含まれていた時のみ）
	// ※carrierパラメーターの指定に関わらずPC/mobile両対応のURLを返却 
	// httpsではじまる店舗アフィリエイトのURL
	private String shopAffiliateUrl;
	// ジャンルID
	private String genreId;
	// タグID: 複数のタグIDを配列で返却
	private String[] tagIds;	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCatchcopy() {
		return catchcopy;
	}
	public void setCatchcopy(String catchcopy) {
		this.catchcopy = catchcopy;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemCaption() {
		return itemCaption;
	}
	public void setItemCaption(String itemCaption) {
		this.itemCaption = itemCaption;
	}
	public String getItemUrl() {
		return itemUrl;
	}
	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
	public String getAffiliateUrl() {
		return affiliateUrl;
	}
	public void setAffiliateUrl(String affiliateUrl) {
		this.affiliateUrl = affiliateUrl;
	}
	public String getImageFlag() {
		return imageFlag;
	}
	public void setImageFlag(String imageFlag) {
		this.imageFlag = imageFlag;
	}
	public String[] getSmallImageUrls() {
		return smallImageUrls;
	}
	public void setSmallImageUrls(String[] smallImageUrls) {
		this.smallImageUrls = smallImageUrls;
	}
	public String[] getMediumImageUrls() {
		return mediumImageUrls;
	}
	public void setMediumImageUrls(String[] mediumImageUrls) {
		this.mediumImageUrls = mediumImageUrls;
	}
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
		this.availability = availability;
	}
	public int getTaxFlag() {
		return taxFlag;
	}
	public void setTaxFlag(int taxFlag) {
		this.taxFlag = taxFlag;
	}
	public int getPostageFlag() {
		return postageFlag;
	}
	public void setPostageFlag(int postageFlag) {
		this.postageFlag = postageFlag;
	}
	public int getCreditCardFlag() {
		return creditCardFlag;
	}
	public void setCreditCardFlag(int creditCardFlag) {
		this.creditCardFlag = creditCardFlag;
	}
	public int getShopOfTheYearFlag() {
		return shopOfTheYearFlag;
	}
	public void setShopOfTheYearFlag(int shopOfTheYearFlag) {
		this.shopOfTheYearFlag = shopOfTheYearFlag;
	}
	public int getShipOverseasFlag() {
		return shipOverseasFlag;
	}
	public void setShipOverseasFlag(int shipOverseasFlag) {
		this.shipOverseasFlag = shipOverseasFlag;
	}
	public String getShipOverseasArea() {
		return shipOverseasArea;
	}
	public void setShipOverseasArea(String shipOverseasArea) {
		this.shipOverseasArea = shipOverseasArea;
	}
	public int getAsurakuFlag() {
		return asurakuFlag;
	}
	public void setAsurakuFlag(int asurakuFlag) {
		this.asurakuFlag = asurakuFlag;
	}
	public String getAsurakuClosingTime() {
		return asurakuClosingTime;
	}
	public void setAsurakuClosingTime(String asurakuClosingTime) {
		this.asurakuClosingTime = asurakuClosingTime;
	}
	public String getAsurakuArea() {
		return asurakuArea;
	}
	public void setAsurakuArea(String asurakuArea) {
		this.asurakuArea = asurakuArea;
	}
	public Double getAffiliateRate() {
		return affiliateRate;
	}
	public void setAffiliateRate(Double affiliateRate) {
		this.affiliateRate = affiliateRate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public Double getReviewAverage() {
		return reviewAverage;
	}
	public void setReviewAverage(Double reviewAverage) {
		this.reviewAverage = reviewAverage;
	}
	public Double getPointRate() {
		return pointRate;
	}
	public void setPointRate(Double pointRate) {
		this.pointRate = pointRate;
	}
	public String getPointRateStartTime() {
		return pointRateStartTime;
	}
	public void setPointRateStartTime(String pointRateStartTime) {
		this.pointRateStartTime = pointRateStartTime;
	}
	public String getPointRateEndTime() {
		return pointRateEndTime;
	}
	public void setPointRateEndTime(String pointRateEndTime) {
		this.pointRateEndTime = pointRateEndTime;
	}
	public int getGiftFlag() {
		return giftFlag;
	}
	public void setGiftFlag(int giftFlag) {
		this.giftFlag = giftFlag;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getShopAffiliateUrl() {
		return shopAffiliateUrl;
	}
	public void setShopAffiliateUrl(String shopAffiliateUrl) {
		this.shopAffiliateUrl = shopAffiliateUrl;
	}
	public String getGenreId() {
		return genreId;
	}
	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}
	public String[] getTagIds() {
		return tagIds;
	}
	public void setTagIds(String[] tagIds) {
		this.tagIds = tagIds;
	}

}
