package com.github.february.rakuten.collector.job.impl;

import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.job.Writer;
import com.weidian.open.sdk.entity.Item;

@Component
public final class ForwardToWeidianWriter implements Writer<Item> {

	@Override
	public boolean write(Item output) {
		// TODO Auto-generated method stub
		return false;
	}

}
