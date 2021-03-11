package com.velen.etl.dataflow.processor.assemble.meta.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processor/assemble")
public class AssembleMetaController
{
	/**
	 * 测试用
	 */
	@RequestMapping("/test")
	String test()
	{
		return "OK";
	}


}
