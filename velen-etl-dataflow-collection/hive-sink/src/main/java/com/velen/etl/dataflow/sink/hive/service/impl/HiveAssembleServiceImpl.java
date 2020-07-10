package com.velen.etl.dataflow.sink.hive.service.impl;

import com.velen.etl.dataflow.sink.hive.service.HiveAssembleService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@Deprecated
@EnableBinding(Sink.class)
public class HiveAssembleServiceImpl implements HiveAssembleService
{

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Sink.INPUT)
	public void receive(String payload)
	{

	}
}
