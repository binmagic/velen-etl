package com.velen.etl.dispatcher.stream.api;

import javafx.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Deprecated
public interface ExtractApi
{
	/**
	 * 测试用
	 */
	@PostMapping("/stream/source/test")
	String test();

	/**
	 * 测试用
	 */
	@PostMapping("/stream/source/test1")
	String test(@RequestBody List<Integer> list);

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/source/formats")
	//ResponseEntity set(@RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO);
	ResponseEntity setFormats(@RequestBody Map<String, String> formats);

	/**
	 * 设置是否验证
	 */
	@PostMapping("/stream/source/verify")
	ResponseEntity setVerify(@RequestParam("verify") Boolean verify, @RequestBody Map<String, Pair<Integer,String>> metas);
}
