package com.velen.etl.dataflow.parse.format.processor.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.dataflow.parse.format.processor.properties.ParseFormatProperties;
import com.velen.etl.dataflow.parse.format.processor.service.ParseFormatService;
import com.velen.etl.verification.entity.VerifyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stream")
public class ParseFormatController
{
	@Autowired
	private ParseFormatService parseFormatService;

	/**
	 * 测试用
	 */
	@RequestMapping("/source/test")
	String test()
	{
		return "OK";
	}

	/**
	 * 测试用
	 */
	@RequestMapping("/source/test1")
	String test(@RequestBody List<Integer> list)
	{
		System.out.println("ExtractController::test1");
		return "response list ok!";
	}

	@PostMapping("/source/{msg}")
	ResponseEntity test(@PathVariable("msg") String msg)
	{
		parseFormatService.test(msg);
		return ResponseEntity.ok(msg);
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/source/formats")
	ResponseEntity set(@RequestBody Map<String, String> formats)
	{
		// check parameter

		if(formats.isEmpty())
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to formats!");

		ParseFormatProperties properties = this.parseFormatService.resetInputParseFormats(formats);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置是否验证
	 *//*
	@PostMapping("/source/verify")
	ResponseEntity set(@RequestParam("verify") Boolean verify)
	{
		return ResponseEntity.ok("1");
	}*/
}
