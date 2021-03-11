package com.velen.etl.dispatcher.restful.api;

import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import com.velen.etl.verification.tdo.FieldRuleTDO;
import com.velen.etl.verification.tdo.ParseFormatTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DataFlowCollectionApi
{
	String FIELD_VERIFY_PREFIX = "field-verify-";

	/*static String fieldFeing(String[] args)
	{
		return prefix + id;
	}*/

	String INPUT_PARSE_PREFIX = "input-parse-";

	/*static String parse(String[] args)
	{
		return prefix + id;
	}*/

	String INPUT_VERIFY_PREFIX = "input-verify-";

	/*static String verify(String[] args)
	{
		return prefix + id;
	}*/

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/processor/fields")
	ResponseEntity setFields(@RequestBody List<FieldRuleTDO> fields);

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/stream/source/formats")
	ResponseEntity setFormats(@RequestBody List<ParseFormatTDO> parseFormatsTDO);

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
