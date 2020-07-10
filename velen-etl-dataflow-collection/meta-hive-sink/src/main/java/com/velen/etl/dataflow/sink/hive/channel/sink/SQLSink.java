package com.velen.etl.dataflow.sink.hive.channel.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * bindings:
 *   parser-input:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface SQLSink
{
	String INPUT = "sql-input";

	/**
	 * @return input channel.
	 */
	@Input(SQLSink.INPUT)
	SubscribableChannel input();
}
