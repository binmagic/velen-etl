package com.velen.etl.dataflow.collection.controller;

import com.velen.etl.dataflow.collection.service.ProcessorService;
import com.velen.etl.dataflow.collection.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class ProducerController
{
	//@Autowired
	//private SourceService sendService;

	//@Autowired
	//private ProcessorService processorService;

	@Autowired
	private SourceService sourceService;


	@RequestMapping("/test/{msg}")
	public ResponseEntity send(@PathVariable("msg") String msg)
	{
		String response = sourceService.sendMsg(msg);

		return ResponseEntity.ok(response);
	}
}
