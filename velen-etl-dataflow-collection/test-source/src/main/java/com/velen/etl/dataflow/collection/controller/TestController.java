package com.velen.etl.dataflow.collection.controller;

import com.velen.etl.configuration.ProjectConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/dataflow/stream")
public class TestController
{
	@Autowired
	ProjectConfiguration projectConfiguration;

	@Autowired
	private Source source;

	/**
	 * 测试用
	 */
	@GetMapping("/get")
	String get()
	{
		return projectConfiguration.toString();
	}

	/**
	 * 更新
	 */
	@RequestMapping("/update")
	ResponseEntity update(@RequestParam("topic") String topic, @RequestParam("enforced") Boolean enforcedVerify)
	{
		projectConfiguration.setTopic(topic);
		projectConfiguration.setVerify(enforcedVerify);

		return ResponseEntity.ok().build();
	}
}
