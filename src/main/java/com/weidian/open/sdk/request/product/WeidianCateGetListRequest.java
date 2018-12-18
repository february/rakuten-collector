package com.weidian.open.sdk.request.product;

import java.util.HashMap;
import java.util.Map;

import com.weidian.open.sdk.exception.OpenException;
import com.weidian.open.sdk.request.AbstractRequest;
import com.weidian.open.sdk.response.product.VdianCateGetListResponse;
import com.weidian.open.sdk.util.JsonUtils;

/**
 * 获取全店商品分类<br/>
 * <a href="http://wiki.open.weidian.com/index.php?title=%E8%8E%B7%E5%8F%96%E5%8D%95%E4%B8%AA%E5%95%86%E5%93%81">查看接口文档</a>
 * */
public class WeidianCateGetListRequest extends AbstractRequest<VdianCateGetListResponse> {

  private boolean showNoCate;

  public WeidianCateGetListRequest(String accessToken, boolean  showNoCate) {
    super(accessToken);
    this.showNoCate = showNoCate;
  }

  @Override
  public String getParam() throws OpenException {
    Map<String, Object> map = new HashMap<String, Object>((int) (1 / .75f) + 1);
    map.put("showNoCate", this.showNoCate ? 1 : 0);
    return JsonUtils.toJson(map);
  }

public boolean isShowNoCate() {
	return showNoCate;
}

public void setShowNoCate(boolean showNoCate) {
	this.showNoCate = showNoCate;
}

}
