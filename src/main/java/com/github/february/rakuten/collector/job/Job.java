package com.github.february.rakuten.collector.job;

public interface Job<R, W> {
	
	Reader<R> reader();
	
	Processor<R, W> processor();
	
	Writer<W> writer();
	
	public void execute();

}
