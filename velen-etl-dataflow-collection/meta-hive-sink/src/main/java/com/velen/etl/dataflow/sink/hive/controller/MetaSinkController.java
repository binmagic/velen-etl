package com.velen.etl.dataflow.sink.hive.controller;

import com.velen.etl.dataflow.sink.hive.service.MetaSinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stream")
public class MetaSinkController
{
	@Autowired
	MetaSinkService metaSinkService;


	@PostMapping("/sink/test1/{msg}")
	ResponseEntity test(@PathVariable("msg") String msg)
	{
		//parseFormatService.test(msg);
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/sink/test2/{msg}")
	ResponseEntity test(@PathVariable("sql") String sql, @RequestBody List<Object> args)
	{
		int i = metaSinkService.insert(sql, args.toArray());
		return ResponseEntity.ok(i);
	}
}
