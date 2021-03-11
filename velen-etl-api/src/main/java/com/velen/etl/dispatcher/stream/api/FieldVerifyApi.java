package com.velen.etl.dispatcher.stream.api;

import com.velen.etl.verification.tdo.FieldRuleTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FieldVerifyApi
{
	String prefix = "field-verify-";

	static String makeId(String id)
	{
		return prefix + id;
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/processor/fields")
	ResponseEntity setFields(@RequestBody List<FieldRuleTDO> fields);
}
