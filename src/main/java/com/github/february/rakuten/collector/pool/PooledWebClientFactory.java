package com.github.february.rakuten.collector.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

@Component
public class PooledWebClientFactory implements PooledObjectFactory<WebClient> {
	
	public PooledWebClientFactory(){
		System.out.println("init WebClient factory");
    }

	@Override
	public void activateObject(PooledObject<WebClient> arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyObject(PooledObject<WebClient> arg0) throws Exception {
		WebClient webClient = arg0.getObject();
        if(webClient != null){
        	webClient.close();
        	webClient = null;
            System.out.println("WebClient destroy");
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
		System.out.println("return WebClient item");
		
	}

	@Override
	public boolean validateObject(PooledObject<WebClient> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
