package com.github.february.rakuten.collector.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.february.rakuten.collector.analyzer.impl.AbcMartAvailableShoeSizeAnalyzer;
import com.github.february.rakuten.collector.bean.AvailableShoeSize;
import com.github.february.rakuten.collector.job.impl.ForwardToWeidianJob;
import com.github.february.rakuten.collector.service.DefaultHttpService;
import com.github.february.rakuten.collector.service.WeidianService;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItem;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchParam;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.github.february.rakuten.sdk.service.RakutenService;

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
    public int db(int a, int b) throws Exception {
		weidian.uploadImage(new String[] {
				"/Users/zhanghao/Downloads/www.jpeg",
				"/Users/zhanghao/Downloads/www.jpeg"
		});
//		job.execute();
		return 5;
	}
	
	@ShellMethod("Add two integers together.")
    public int dec(int a, int b) throws Exception {
		RakutenIchibaItemSearchParam param = new RakutenIchibaItemSearchParam();
		param.setShopCode("abc-mart");
		param.setKeyword("キッズ スニーカー");
		RakutenIchibaItemSearchResult result = rakutenService.ichibaItemSearch(param);
		
		List<RakutenIchibaItem> items = result.getItems();
		for(RakutenIchibaItem item : items) {
			System.out.println(item.getItemUrl());
			String pageXml = httpService.get(item.getItemUrl());
			AvailableShoeSize[] sizes = abcMartAvailableShoeSizeAnalyzer.analyze(pageXml);
			for(AvailableShoeSize size : sizes) {
				System.out.println(size.getValue());
			}
			
			String[] urls = item.getMediumImageUrls();
			for(int i=0; i<urls.length; i++) {
				urls[i] = urls[i].replace("?_ex=128x128", "");
			}
			
			httpService.downloadJpg(urls);
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