package com.test.channel.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * bindings:
 *   my-output:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface SpecialSource
{
	String OUTPUT = "special-output";

	@Output(SpecialSource.OUTPUT)
	MessageChannel output();
}
