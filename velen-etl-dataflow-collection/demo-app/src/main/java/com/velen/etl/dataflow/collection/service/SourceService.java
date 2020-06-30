package com.velen.etl.dataflow.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * stream:
 *   bindings:
 *     output:
 *        destination: stream-demo # kafka topic
 *        content-type: text/plain
 */
@EnableBinding(Source.class)
public class SourceService
{
	@Autowired
	private Source source;


	public void sendMsg(String msg)
	{
		source.output().send(MessageBuilder.withPayload(msg).build());
	}
}
