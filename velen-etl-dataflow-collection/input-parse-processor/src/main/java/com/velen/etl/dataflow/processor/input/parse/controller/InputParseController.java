package com.velen.etl.dataflow.processor.input.parse.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.dataflow.processor.input.parse.properties.InputParseProperties;
import com.velen.etl.dataflow.processor.input.parse.service.InputParseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@RestController
@RequestMapping("/stream")
public class InputParseController
{
	@Autowired
	private InputParseService inputParseService;

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
		inputParseService.test(msg);
		return ResponseEntity.ok(msg);
	}

	/**
	 * 设置AppId
	 */
	@PostMapping("/source/set-appid")
	ResponseEntity setAppId(@RequestParam("appId") String appId)
	{
		InputParseProperties properties = this.inputParseService.setAppId(appId);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置业务名
	 */
	@PostMapping("/source/set-business")
	ResponseEntity setBusiness(@RequestParam("business") String business)
	{
		InputParseProperties properties = this.inputParseService.setBusiness(business);

		return ResponseEntity.ok(properties);
	}

	static Map<String, String> TRANSFERRED_MAP = new HashMap<>();
	static
	{
		TRANSFERRED_MAP.put("|", "\\|");
		TRANSFERRED_MAP.put(".", "\\.");
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/source/formats")
	ResponseEntity setFormats(@RequestBody Map<String, String> formats)
	{
		// check parameter

		if(formats.isEmpty())
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to formats!");

		// transferred meaning
		for(Map.Entry<String, String> entry : formats.entrySet())
		{
			String delimeter = TRANSFERRED_MAP.get(entry.getValue());
			if(delimeter != null)
			{
				entry.setValue(delimeter);
			}
		}

		InputParseProperties properties = this.inputParseService.resetInputParseFormats(formats);

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
