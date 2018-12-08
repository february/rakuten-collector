package com.github.february.rakuten.collector.job.impl;

import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.bean.WeidianBean;
import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;

@Component
public final class ForwardToWeidianProcessor implements Processor<RakutenIchibaItemSearchResult, WeidianBean> {

	@Override
	public WeidianBean[] process(RakutenIchibaItemSearchResult input) {
		// TODO Auto-generated method stub
		return null;
	}

}
