package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.VdianItemSearchResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 店铺内商品搜索<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%8D%95%E4%B8%AA%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class WeidianItemSearchRequest extends AbstractRequest<VdianItemSearchResponse> {

  private String fx;
  
  private String keyWord;
  
  private String page;
  
  private String pageSize;

  public WeidianItemSearchRequest(String accessToken, String fx, String keyWord, String page, String pageSize) {
    super(accessToken);
    this.fx = fx;
    this.keyWord = keyWord;
    this.page = page;
    this.pageSize = pageSize;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("fx", this.fx);
    map.put("keyWord", this.keyWord);
    map.put("page", this.page);
    map.put("pageSize", this.pageSize);
    return JsonUtils.toJson(map);
  }

public String getFx() {
	return fx;
}

public void setFx(String fx) {
	this.fx = fx;
}

public String getKeyWord() {
	return keyWord;
}

public void setKeyWord(String keyWord) {
	this.keyWord = keyWord;
}

public String getPage() {
	return page;
}

public void setPage(String page) {
	this.page = page;
}

public String getPageSize() {
	return pageSize;
}

public void setPageSize(String pageSize) {
	this.pageSize = pageSize;
}

}
