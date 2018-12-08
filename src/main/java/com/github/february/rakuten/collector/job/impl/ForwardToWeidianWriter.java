package com.github.february.rakuten.collector.job.impl;

import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.bean.WeidianBean;
import com.github.february.rakuten.collector.job.Writer;

@Component
public final class ForwardToWeidianWriter implements Writer<WeidianBean> {

	@Override
	public boolean write(WeidianBean output) {
		// TODO Auto-generated method stub
		return false;
	}

}
