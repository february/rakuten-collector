package com.github.february.rakuten.collector.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.bean.WeidianBean;
import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.collector.job.Reader;
import com.github.february.rakuten.collector.job.Writer;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;

@Component
public class ForwardToWeidianJob extends TransmitJob<RakutenIchibaItemSearchResult, WeidianBean> {
	
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
	public Processor<RakutenIchibaItemSearchResult, WeidianBean> processor() {
		return processor;
	}

	@Override
	public Writer<WeidianBean> writer() {
		return writer;
	}

}
