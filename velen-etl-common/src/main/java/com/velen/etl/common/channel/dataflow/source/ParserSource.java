package com.velen.etl.common.channel.dataflow.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * bindings:
 *   my-output:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface ParserSource
{
	String OUTPUT = "parser-output";

	@Output(ParserSource.OUTPUT)
	MessageChannel output();
}
