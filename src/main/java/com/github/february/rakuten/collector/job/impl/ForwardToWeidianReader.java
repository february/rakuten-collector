package com.github.february.rakuten.collector.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.february.rakuten.collector.job.Reader;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchParam;
import com.github.february.rakuten.sdk.bean.RakutenIchibaItemSearchResult;
import com.github.february.rakuten.sdk.service.RakutenService;

@Component
public final class ForwardToWeidianReader implements Reader<RakutenIchibaItemSearchResult> {
	
	private static final Log logger = LogFactory.getLog(ForwardToWeidianReader.class);
	
	@Autowired
	RakutenService rakutenService;

	@Override
	public RakutenIchibaItemSearchResult[] read() {
		List<RakutenIchibaItemSearchResult> resultList = new ArrayList<RakutenIchibaItemSearchResult>();
		int page = 1;
		int pageCount = 1;
		boolean isContinue = true;
		while(isContinue) {
			RakutenIchibaItemSearchParam param = new RakutenIchibaItemSearchParam();
			param.setShopCode("abc-mart");
			param.setKeyword("キッズ スニーカー");
			param.setAvailability(1);
			try {
				RakutenIchibaItemSearchResult result = rakutenService.ichibaItemSearch(param);
				resultList.add(result);
				pageCount = result.getPageCount();
				logger.info(param.getKeyword() + " read successfully at page " + page + "/" + pageCount);
			} catch (Exception e) {
				logger.error(param.getKeyword() + " read failed at page " + page + "/" + pageCount + " and cause of " + e.getMessage());
			}
			if(page >= pageCount) {
				isContinue = false;
			} else {
				page++;
			}
		}
		
		return resultList.toArray(new RakutenIchibaItemSearchResult[0]);
	}

}
