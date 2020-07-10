package com.velen.etl.dataflow.sink.hive.channel.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * bindings:
 *   json-array-input:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface JSONArraySink
{
	String INPUT = "json-array-input";

	/**
	 * @return input channel.
	 */
	@Input(JSONArraySink.INPUT)
	SubscribableChannel input();
}
