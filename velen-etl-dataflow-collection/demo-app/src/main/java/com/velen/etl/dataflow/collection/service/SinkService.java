package com.velen.etl.dataflow.collection.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * stream:
 *   bindings:
 *     input:
 *       destination: stream-demo
 */
@EnableBinding(Sink.class)
public class SinkService
{
	@StreamListener(Sink.INPUT)
	public void receive(Object payload)
	{
		System.out.println(payload);
	}
}
