package com.velen.etl.common.channel.dataflow.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * bindings:
 *   parser-input:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface ParserSink
{
	String INPUT = "parser-input";

	/**
	 * @return input channel.
	 */
	@Input(ParserSink.INPUT)
	SubscribableChannel input();
}
