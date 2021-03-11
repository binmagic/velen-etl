package com.velen.etl.dispatcher.stream.api;

import com.velen.etl.verification.tdo.ParseFormatTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InputParseApi
{
	String prefix = "input-parse-";

	static String makeId(String id)
	{
		return prefix + id;
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/source/formats")
	ResponseEntity setFormats(@RequestBody List<ParseFormatTDO> parseFormatsTDO);
}
