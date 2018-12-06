package com.github.february.rakuten.collector.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.github.february.rakuten.collector.service.BrowserService;

@ShellComponent
public class MyCommands {

	@Autowired
	BrowserService browserService;
    
    @ShellMethod("Add two integers together.")
    public int add(int a, int b) {
        // https://item.rakuten.co.jp/abc-mart/5735140002/

        String pageXml;
		try {
			pageXml = browserService.getPage("https://item.rakuten.co.jp/abc-mart/5735140002/");
			System.out.println(pageXml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return a + b;
    }
}