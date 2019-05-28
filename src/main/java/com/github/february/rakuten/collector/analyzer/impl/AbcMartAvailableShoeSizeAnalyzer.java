package com.github.february.rakuten.collector.analyzer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.text.html.HTML.Tag;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.analyzer.AvailableShoeSizeAnalyzer;
import com.github.february.rakuten.collector.bean.AvailableShoeSize;

@Component
public class AbcMartAvailableShoeSizeAnalyzer implements AvailableShoeSizeAnalyzer {

	@Override
	public AvailableShoeSize[] analyze(String html) {
		List<AvailableShoeSize> sizeList = new ArrayList<AvailableShoeSize>();
		List<AvailableShoeSize> result = new ArrayList<AvailableShoeSize>();
		
		Document doc = Jsoup.parse(html);		
		Elements inventoryChoiceNames = doc.getElementsByClass("inventory_choice_name");
		ListIterator<Element> inventoryChoiceElements = inventoryChoiceNames.listIterator();
		while(inventoryChoiceElements.hasNext()) {
			Element element = inventoryChoiceElements.next();
			if(Tag.SPAN.toString().equals(element.tagName())) {
				if(element.childNodeSize() > 0) {
					String sizeStr = element.childNode(0).outerHtml().trim();
					AvailableShoeSize size = new AvailableShoeSize();
					size.setValue(sizeStr);
					sizeList.add(size);
				}	
			}
		}
		
		if(sizeList.size() > 0) {
			Elements inventories = doc.getElementsByClass("inventory");
			for(int i=0; i<sizeList.size(); i++) {	
				Element inventoryElement = inventories.get(i);
				if(inventoryElement.getElementsByClass("inventory_soldout").isEmpty()) {
					result.add(sizeList.get(i));
				}
			}
		}
		
		return result.toArray(new AvailableShoeSize[0]);
	}

}
