package com.github.february.rakuten.collector.job.impl;

import com.github.february.rakuten.collector.job.Job;
import com.github.february.rakuten.collector.job.Processor;
import com.github.february.rakuten.collector.job.Reader;
import com.github.february.rakuten.collector.job.Writer;

public abstract class TransmitJob<R, W> implements Job<R, W> {
	
	public void execute() {
		Reader<R> reader = this.reader();
		Processor<R, W> processor = this.processor();
		Writer<W> writer = this.writer();
		R[] inputArr = reader.read();
		for(R input : inputArr) {
			W[] outputArr = processor.process(input);
			for(W output : outputArr) {
				writer.write(output);
			}
		}
	}

}
