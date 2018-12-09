package com.weidian.open.sdk.response.product;

import com.weidian.open.sdk.entity.Item;
import com.weidian.open.sdk.response.AbstractResponse;

public class VdianItemSearchResponse extends AbstractResponse {

  private VdianItemListSearchResult result;

  public VdianItemListSearchResult getResult() {
    return result;
  }

  public void setResult(VdianItemListSearchResult result) {
    this.result = result;
  }
  
  
  public static class VdianItemListSearchResult {

	    private Item[] items;
	    
	    private int total;

		public Item[] getItems() {
			return items;
		}

		public void setItems(Item[] items) {
			this.items = items;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	    
	}

}