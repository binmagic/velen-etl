package com.velen.etl.dataflow.processor.field.verify.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.dataflow.processor.field.verify.properties.FieldVerifyProperties;
import com.velen.etl.dataflow.processor.field.verify.service.FieldVerifyService;
import com.velen.etl.verification.tdo.FieldRuleTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stream")
public class FieldVerifyController
{
	@Autowired
	private FieldVerifyService fieldVerifyService;

	/**
	 * 测试用
	 */
	@RequestMapping("/processor/test")
	String test()
	{
		return "OK";
	}

	/**
	 * 测试用
	 */
	@RequestMapping("/processor/test1")
	String test(@RequestBody List<Integer> list)
	{
		System.out.println("ExtractController::test1");
		return "response list ok!";
	}

	@PostMapping("/source/{msg}")
	ResponseEntity test(@PathVariable("msg") String msg)
	{
		return ResponseEntity.ok(fieldVerifyService.test(msg));
	}

	/**
	 * 设置AppId
	 */
	@PostMapping("/source/set-appid")
	ResponseEntity setAppId(@RequestParam("appId") String appId)
	{
		FieldVerifyProperties properties = this.fieldVerifyService.setAppId(appId);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置业务名
	 */
	@PostMapping("/source/set-business")
	ResponseEntity setBusiness(@RequestParam("business") String business)
	{
		FieldVerifyProperties properties = this.fieldVerifyService.setBusiness(business);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/processor/fields")
	ResponseEntity setFields(@RequestBody List<FieldRuleTDO> fields)
	{
		// check parameter

		if(fields.isEmpty())
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to fields!");

		List<FieldVerifyProperties.Field> list = new ArrayList<>();
		for(FieldRuleTDO o : fields)
		{
			list.add(new FieldVerifyProperties.Field(o.getFieldName(), o.getKeyRuleType().getKey()
			, o.getKeyRule(), o.getValueRule()));
		}

		FieldVerifyProperties properties = this.fieldVerifyService.setFields(list);

		return ResponseEntity.ok(properties);
	}
}
