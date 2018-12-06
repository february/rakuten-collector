package com.github.february.rakuten.collector.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.february.rakuten.collector.bean.AvailableShoeSize;
import com.github.february.rakuten.collector.service.AnalyzeService;
import com.github.february.rakuten.collector.service.BrowserService;

@ShellComponent
public class MyCommands {

	@Autowired
	AnalyzeService analyzeService;
	
	@Autowired
	BrowserService browserService;	
    
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        // https://item.rakuten.co.jp/abc-mart/5735140002/

        String pageXml;
		try {
			pageXml = browserService.getPage("https://item.rakuten.co.jp/abc-mart/5735140002/");
			AvailableShoeSize[] sizes = analyzeService.getAvailableShoeSize(pageXml);
			for(AvailableShoeSize size : sizes) {
				System.out.println(size.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return a + b;
    }
}