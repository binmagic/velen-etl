package com.velen.etl.dataflow.sink.hive.controller;

import com.velen.etl.dataflow.sink.hive.service.MetaSinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class MetaSinkController
{
	@Autowired
	MetaSinkService metaSinkService;


	@PostMapping("/sink/{msg}")
	ResponseEntity test(@PathVariable("msg") String msg)
	{
		//parseFormatService.test(msg);
		return ResponseEntity.ok(msg);
	}
}
