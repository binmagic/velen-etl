package com.velen.etl.dataflow.collection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stream")
@EnableBinding(Source.class)
public class TestController
{
	//@Autowired
	//ProjectConfiguration projectConfiguration;

	@Autowired
	private Source source;

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{msg}")
	public ResponseEntity send(@PathVariable("msg") String msg)
	{
		boolean response = source.output().send(MessageBuilder.withPayload(msg).build());

		return ResponseEntity.ok(response);
	}

	/**
	 * 更新
	 *//*
	@RequestMapping("/update")
	ResponseEntity update(@RequestParam("topic") String topic, @RequestParam("enforced") Boolean enforcedVerify)
	{
		//projectConfiguration.setTopic(topic);
		//projectConfiguration.setVerify(enforcedVerify);

		return ResponseEntity.ok().build();
	}*/
}
