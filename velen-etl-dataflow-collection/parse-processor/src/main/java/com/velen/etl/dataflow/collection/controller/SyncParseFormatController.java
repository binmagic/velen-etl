package com.velen.etl.dataflow.collection.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/processor")
public class SyncParseFormatController
{
	//@Autowired
	//private ProcessService processService;


	/**
	 * 同步解析格式
	 */
	//@PostMapping("/project/processor/test")
	@GetMapping("/project/processor/test")
	ResponseEntity test(String regex)
	{
		//processService.setRegex(regex);
		return ResponseEntity.ok().build();
	}

	/**
	 * 同步解析格式
	 */
	/*@PostMapping("/project/processor/set-input-parse")
	ResponseEntity setInputParse(@RequestParam("appId") String appId, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO,
	                      @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}*/

}
