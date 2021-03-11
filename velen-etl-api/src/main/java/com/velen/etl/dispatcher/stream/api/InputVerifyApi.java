package com.velen.etl.dispatcher.stream.api;

import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface InputVerifyApi
{
	String prefix = "input-verify-";

	static String makeId(String id)
	{
		return prefix + id;
	}

	/**
	 * 设置是否验证
	 */
	@PostMapping("/stream/processor/verify")
	ResponseEntity setVerify(@RequestParam("verify") Boolean verify);

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/processor/fields")
	ResponseEntity setFields(@RequestParam("name") String name, @RequestBody List<PropertyMetadataTDO> fields);
}
