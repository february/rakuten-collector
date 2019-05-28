package com.github.february.rakuten.collector.pool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

@Component
public class PooledWebClientFactory implements PooledObjectFactory<WebClient> {
	
	private static final Log logger = LogFactory.getLog(PooledWebClientFactory.class);
	
	public PooledWebClientFactory(){
		logger.info("WebClientPool is initialized");
    }

	@Override
	public void activateObject(PooledObject<WebClient> arg0) throws Exception {
		logger.info("WebClient instance is activated");	
	}

	@Override
	public void destroyObject(PooledObject<WebClient> arg0) throws Exception {
		WebClient webClient = arg0.getObject();
        if(webClient != null){
        	webClient.close();
        	webClient = null;
            logger.info("WebClient instance is destroy");
        }
		
	}

	@Override
	public PooledObject<WebClient> makeObject() throws Exception {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
		return new DefaultPooledObject<WebClient>(webClient);
	}

	@Override
	public void passivateObject(PooledObject<WebClient> arg0) throws Exception {
		logger.info("WebClient instance is released");		
	}

	@Override
	public boolean validateObject(PooledObject<WebClient> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
