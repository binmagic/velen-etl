package com.velen.etl.dataflow.sink.hive.channel.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * bindings:
 *   json-array-output:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface JSONArraySource
{
	String OUTPUT = "json-array-output";

	@Output(JSONArraySource.OUTPUT)
	MessageChannel output();
}
