package com.github.february.rakuten.collector.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.analyzer.impl.AbcMartAvailableShoeSizeAnalyzer;
import com.github.february.rakuten.collector.bean.AvailableShoeSize;
import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.collector.service.HttpService;
import com.github.february.rakuten.collector.service.WeidianService;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItem;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.weidian.open.sdk.entity.Item;
import com.weidian.open.sdk.entity.Sku;

@Component
public final class ForwardToWeidianProcessor implements Processor<RakutenIchibaItemSearchResult, Item> {
	
	private static final Log logger = LogFactory.getLog(ForwardToWeidianProcessor.class);
	
	@Autowired
	AbcMartAvailableShoeSizeAnalyzer analyzer;
	
	@Autowired
	HttpService httpService;
	
	@Autowired
	WeidianService weidian;

	@Override
	public Item[] process(RakutenIchibaItemSearchResult input) {
		List<Item> items = new ArrayList<Item>();
		List<RakutenIchibaItem> rakutenItems = input.getItems();
		for(RakutenIchibaItem rakutenItem : rakutenItems) {
			try {
				String pageXml = httpService.getPage(rakutenItem.getItemUrl());
				AvailableShoeSize[] sizes = analyzer.analyze(pageXml);				
				String[] urls = rakutenItem.getMediumImageUrls();
				for(int i=0; i<urls.length; i++) {
					urls[i] = urls[i].replace("?_ex=128x128", "");
				}
				
				logger.info("prepare images for " + rakutenItem.getItemUrl());
				String[] paths = httpService.downloadJpg(urls);				
				String[] imgUrls = weidian.uploadImage(paths);
				
				Item item = new Item();
				
			    item.setItemName(rakutenItem.getItemName());
			    item.setItemDesc(rakutenItem.getItemCaption());
			    item.setImgs(imgUrls);
			    item.setPrice(String.valueOf(rakutenItem.getItemPrice()));
			    item.setStock(999);
			    
			    List<Sku> skus = new ArrayList<Sku>();
			    for(AvailableShoeSize size : sizes) {
			    	Sku sku = new Sku();
			    	sku.setTitle(size.getValue());
				    sku.setPrice(String.valueOf(rakutenItem.getItemPrice()));
				    sku.setStock(999);
				    skus.add(sku);
			    }
			    item.setSkus(skus.toArray(new Sku[0]));
				items.add(item);
			} catch (Exception ex) {
				logger.error(rakutenItem.getItemUrl() + " process failed and cause of " + ex.getMessage());
			}
		}
		return items.toArray(new Item[0]);
	}

}
