package com.github.february.rakuten.collector.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientPool extends GenericObjectPool<WebClient> {

	public WebClientPool(PooledObjectFactory<WebClient> factory, GenericObjectPoolConfig<WebClient> config) {
		super(factory, config);
	}

}
