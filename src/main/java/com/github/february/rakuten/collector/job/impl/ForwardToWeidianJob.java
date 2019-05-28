package com.github.february.rakuten.collector.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.collector.job.Reader;
import com.github.february.rakuten.collector.job.Writer;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.weidian.open.sdk.entity.Item;

@Component
public class ForwardToWeidianJob extends TransmitJob<RakutenIchibaItemSearchResult, Item> {
	
	@Autowired
	ForwardToWeidianReader reader;
	
	@Autowired
	ForwardToWeidianProcessor processor;
	
	@Autowired
	ForwardToWeidianWriter writer;

	@Override
	public Reader<RakutenIchibaItemSearchResult> reader() {
		return reader;
	}

	@Override
	public Processor<RakutenIchibaItemSearchResult, Item> processor() {
		return processor;
	}

	@Override
	public Writer<Item> writer() {
		return writer;
	}

}
