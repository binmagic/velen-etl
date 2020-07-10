package com.velen.etl.collections.dataflow.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.collections.dataflow.properties.ParseProperties;
import com.velen.etl.collections.dataflow.service.ParseFormatService;
import com.velen.etl.configuration.InputParseFormatConfiguration;
import com.velen.etl.verification.entity.VerifyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stream")
public class ExtractController
{
	@Autowired
	private ParseFormatService parseFormatService;

	@Autowired
	private ParseProperties parseProperties;

	/**
	 * 测试用
	 */
	@RequestMapping("/source/test")
	String test()
	{
		System.out.println("ExtractController::test");
		return "response ok!" + parseProperties.isVerify();
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

		List<InputParseFormatConfiguration.ParseFormatConfiguration> list = new ArrayList<>();

		for(Map.Entry<String, String> entry: formats.entrySet())
		{
			VerifyEnum.InputParseType type = VerifyEnum.InputParseType.parse(entry.getKey());
			if(type != VerifyEnum.InputParseType.NONE && type.validate(entry.getValue()))
			{
				list.add(new InputParseFormatConfiguration.ParseFormatConfiguration(entry.getKey(), entry.getValue()));
			}
		}

		if(list.isEmpty())
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to formats!");

		this.parseFormatService.setInputParseFormats(list);

		return ResponseEntity.ok(list);
	}

	/**
	 * 设置是否验证
	 */
	@PostMapping("/source/verify")
	ResponseEntity set(@RequestParam("verify") Boolean verify)
	{
		return ResponseEntity.ok("1");
	}
}
