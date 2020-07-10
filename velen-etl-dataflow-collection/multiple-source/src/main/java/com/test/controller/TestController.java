package com.test.controller;

import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class TestController
{
	@Autowired
	private TestService testService;

	@PostMapping("/source/i1/{msg}")
	ResponseEntity test1(@PathVariable("msg") String msg)
	{
		testService.test1(msg);
		return ResponseEntity.ok(msg);
	}

	@PostMapping("/source/i2/{msg}")
	ResponseEntity test2(@PathVariable("msg") String msg)
	{
		testService.test2(msg);
		return ResponseEntity.ok(msg);
	}
}
