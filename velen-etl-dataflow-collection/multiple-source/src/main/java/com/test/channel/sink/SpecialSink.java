package com.test.channel.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * bindings:
 *   parser-input:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface SpecialSink
{
	String INPUT = "special-input";

	/**
	 * @return input channel.
	 */
	@Input(SpecialSink.INPUT)
	SubscribableChannel input();
}
