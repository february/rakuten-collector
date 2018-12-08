package com.github.february.rakuten.collector.job.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.job.Writer;
import com.github.february.rakuten.collector.service.WeidianService;
import com.weidian.open.sdk.entity.Item;

@Component
public final class ForwardToWeidianWriter implements Writer<Item> {
	
	private static final Log logger = LogFactory.getLog(ForwardToWeidianWriter.class);
	
	@Autowired
	WeidianService weidian;

	@Override
	public void write(Item output) {
		try {
			weidian.addItem(output);
		} catch (Exception e) {
			logger.error(output.getItemName() + " add failed and cause of " + e.getMessage());
		}
	}

}
