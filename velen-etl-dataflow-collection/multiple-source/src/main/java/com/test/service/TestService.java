package com.test.service;

import com.test.channel.source.SpecialSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(value = {Source.class, SpecialSource.class})
public class TestService
{

	//@StreamListener(Processor.INPUT)
	@SendTo(Source.OUTPUT)
	public String test1(String payload)
	{
		return "processor" + payload;
	}

	//@StreamListener(Processor.INPUT)
	@SendTo(SpecialSource.OUTPUT)
	public String test2(String payload)
	{
		return "special" + payload;
	}
}
