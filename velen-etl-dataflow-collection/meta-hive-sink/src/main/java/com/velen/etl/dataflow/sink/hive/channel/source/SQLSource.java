package com.velen.etl.dataflow.sink.hive.channel.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * bindings:
 *   sql-output:
 *     destination: stream-demo
 *     content-type: text/plain
 */
public interface SQLSource
{
	String OUTPUT = "sql-output";

	@Output(SQLSource.OUTPUT)
	MessageChannel output();
}
