package com.velen.etl.dataflow.collection.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * bindings:
 *   my-input:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface MySink
{
	String INPUT = "my-input";

	/**
	 * @return input channel.
	 */
	@Input(MySink.INPUT)
	SubscribableChannel input();
}
