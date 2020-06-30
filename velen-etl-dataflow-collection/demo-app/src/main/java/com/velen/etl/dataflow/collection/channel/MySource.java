package com.velen.etl.dataflow.collection.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * bindings:
 *   my-output:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface MySource
{
	String OUTPUT = "my-output";

	@Output(MySource.OUTPUT)
	MessageChannel output();
}
