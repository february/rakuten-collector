package com.github.february.rakuten.collector.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RakutenIchibaItemSearchResult implements Serializable {

	private static final long serialVersionUID = 1L;

	// 検索数: 検索結果の総商品数
	private int count;
	// ページ番号: 現在のページ番号
	private int page;
	// ページ内商品始追番: 検索結果の何件目からか
	private int first;
	// ページ内商品終追番: 検索結果の何件目までか
	private int last;
	// ヒット件数番: 1度に返却する商品数
	private int hits;
	// キャリア情報: PC=0 or mobile=1 or smartphone=2
	private int carrier;
	// 総ページ数: 最大100
	private int pageCount;	
	
	@JsonProperty("Items")
	private List<RakutenIchibaItem> items;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getCarrier() {
		return carrier;
	}
	public void setCarrier(int carrier) {
		this.carrier = carrier;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List<RakutenIchibaItem> getItems() {
		return items;
	}
	public void setItems(List<RakutenIchibaItem> items) {
		this.items = items;
	}
	
}
