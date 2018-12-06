package com.github.february.rakuten.collector.analyzer;

public interface HtmlAnalyzer<T> {
	
	public T[] analyze(String html);

}
