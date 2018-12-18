package com.github.february.rakuten.collector.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.february.rakuten.collector.analyzer.impl.AbcMartAvailableShoeSizeAnalyzer;
import com.github.february.rakuten.collector.bean.AvailableShoeSize;
import com.github.february.rakuten.collector.bean.WeidianCate;
import com.github.february.rakuten.collector.job.impl.ForwardToWeidianJob;
import com.github.february.rakuten.collector.service.DefaultHttpService;
import com.github.february.rakuten.collector.service.WeidianService;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItem;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchParam;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.github.february.rakuten.sdk.service.RakutenService;
import com.weidian.open.sdk.entity.Cate;
import com.weidian.open.sdk.entity.Item;

@ShellComponent
public class MyCommands {
	
	@Autowired
	DefaultHttpService httpService;

	@Autowired
	RakutenService rakutenService;
	
	@Autowired
	ForwardToWeidianJob job;
	
	@Autowired
	WeidianService weidian;
	
	@Autowired
	AbcMartAvailableShoeSizeAnalyzer abcMartAvailableShoeSizeAnalyzer;
	
	@ShellMethod("Add two integers together.")
    public int cat(int a) throws Exception {
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
						cate.setChildren(new ArrayList<WeidianCate>());
					}
					result.put(i.getParentCateName(), cate);
				}
				
				WeidianCate parent = result.get(i.getParentCateName());
				WeidianCate me = new WeidianCate();
				me.setId(i.getCateId());
				me.setName(i.getCateName());
				parent.getChildren().add(me);
				result.put(i.getParentCateName(), parent);				
			}
		}
		return 1;
	}
	
	@ShellMethod("Add two integers together.")
    public int db(int a, int b) throws Exception {
//		weidian.uploadImage(new String[] {
//				"/Users/zhanghao/Downloads/www.jpeg",
//				"/Users/zhanghao/Downloads/www.jpeg"
//		});
//		job.execute();
		Item[] is = weidian.searchItem("RAKUTEN");
		for(Item i : is) {
			System.out.println(i.getItemName());
		}
		
		
//		Item item = new Item();
//	    item.setItemName("RAKUTEN[]测试商品");
//	    item.setImgs(new String[] {"http://wd.geilicdn.com/vshop163187074-1415608235762-1176009.png?w=480&h=0"});
//	    item.setPrice("1.00");
//	    item.setStock(100);
//
//	    Sku sku = new Sku();
//	    sku.setTitle("测试型号1");
//	    sku.setPrice("1.00");
//	    sku.setStock(100);
//	    item.setSkus(new Sku[] {sku});
//	    
//	    weidian.addItem(item);
	    
		return 5;
	}
	
	@ShellMethod("Add two integers together.")
    public int dec(int a, int b) throws Exception {
		List<RakutenIchibaItemSearchResult> resultList = new ArrayList<RakutenIchibaItemSearchResult>();
		int page = 1;
		int pageCount = 1;
		boolean isContinue = true;
		while(isContinue) {
			RakutenIchibaItemSearchParam param = new RakutenIchibaItemSearchParam();
			param.setShopCode("abc-mart");
			param.setKeyword("キッズ スニーカー");
			param.setAvailability(1);
			try {
				RakutenIchibaItemSearchResult result = rakutenService.ichibaItemSearch(param);
				resultList.add(result);
				pageCount = result.getPageCount();
			} catch (Exception e) {}
			if(page >= pageCount) {
				isContinue = false;
			} else {
				page++;
			}
		}
		
		Set<String> brands = new HashSet<String>();
		
		
		for(RakutenIchibaItemSearchResult r : resultList) {
			List<RakutenIchibaItem> is = r.getItems();
			for(RakutenIchibaItem i : is) {
				System.out.println(i.getItemName());
			}
		}
		
		return a-b;
	}
    
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        // https://item.rakuten.co.jp/abc-mart/5735140002/


        String pageXml;
		try {
			pageXml = httpService.get("https://item.rakuten.co.jp/abc-mart/5735140002/");
			AvailableShoeSize[] sizes = abcMartAvailableShoeSizeAnalyzer.analyze(pageXml);
			for(AvailableShoeSize size : sizes) {
				System.out.println(size.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return a + b;
    }
}