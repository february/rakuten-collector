package com.github.february.rakuten.collector.job;

public interface Writer<W> {
	
	public boolean write(W output);

}
