package com.github.february.rakuten.collector.job;

public interface Processor<R, W> {
	
	public W[] process(R input);

}
