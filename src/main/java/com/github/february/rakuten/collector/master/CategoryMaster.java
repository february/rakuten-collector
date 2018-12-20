package com.github.february.rakuten.collector.master;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.february.rakuten.collector.bean.WeidianCate;
import com.github.february.rakuten.collector.service.WeidianService;
import com.weidian.open.sdk.entity.Cate;

@Configuration
public class CategoryMaster {
	
	@Autowired
	WeidianService weidian;
	
	@Bean
	public Map<String, WeidianCate> wedianCategory() throws Exception {
		Map<String, WeidianCate> result = new HashMap<String, WeidianCate>();
		Cate[] e = weidian.getCategories(true);
		for(Cate  i : e) {
			if(i.getParentId().equals("0")) {
				WeidianCate cate = new WeidianCate();
				cate.setId(i.getCateId());
				cate.setName(i.getCateName());
				result.put(i.getCateName(), cate);
			} else {
				if(!result.containsKey(i.getParentCateName())) {
					WeidianCate cate = new WeidianCate();
					cate.setId(i.getParentId());
					cate.setName(i.getParentCateName());
					if(cate.getChildren() == null) {
						cate.setChildren(new HashMap<String, WeidianCate>());
					}
					result.put(i.getParentCateName(), cate);
				}
				
				WeidianCate parent = result.get(i.getParentCateName());
				WeidianCate me = new WeidianCate();
				String childName = i.getCateName().replace(parent.getName() + "-", "");
				me.setId(i.getCateId());
				me.setName(childName);
				parent.getChildren().put(childName, me);
				result.put(i.getParentCateName(), parent);				
			}
		}
		
		return result;
	}
}
