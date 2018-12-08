package com.github.february.rakuten.collector.job.impl;

import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.weidian.open.sdk.entity.Item;

@Component
public final class ForwardToWeidianProcessor implements Processor<RakutenIchibaItemSearchResult, Item> {

	@Override
	public Item[] process(RakutenIchibaItemSearchResult input) {
		// TODO Auto-generated method stub
		return null;
	}

}
