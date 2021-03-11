package com.velen.etl.dataflow.processor.input.verify.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.dataflow.processor.input.verify.properties.InputVerifyProperties;
import com.velen.etl.dataflow.processor.input.verify.service.InputVerifyService;
import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import com.velen.etl.generator.tdo.TableMetadataTDO;
import com.velen.etl.verification.tdo.FieldRuleTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stream")
public class InputVerifyController
{
	@Autowired
	private InputVerifyService inputVerifyService;

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
		return ResponseEntity.ok(inputVerifyService.test(msg));
	}

	/**
	 * 设置输入流的解释方式
	 */
	/*@PostMapping("/processor/fields")
	ResponseEntity setFields(@RequestBody List<FieldRuleTDO> fields)
	{
		// check parameter

		if(fields.isEmpty())
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to formats!");

		List<InputVerifyProperties.Field> list = new ArrayList<>();
		for(FieldRuleTDO o : fields)
		{
			list.add(new InputVerifyProperties.Field(o.getFieldName(), o.getKeyRuleType().getKey()
			, o.getKeyRule(), o.getValueRule()));
		}

		InputVerifyProperties properties = this.inputVerifyService.setFields(list);

		return ResponseEntity.ok(properties);
	}*/

	/**
	 * 设置AppId
	 */
	@PostMapping("/source/set-appid")
	ResponseEntity setAppId(@RequestParam("appId") String appId)
	{
		InputVerifyProperties properties = this.inputVerifyService.setAppId(appId);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置业务名
	 */
	@PostMapping("/source/set-business")
	ResponseEntity setBusiness(@RequestParam("business") String business)
	{
		InputVerifyProperties properties = this.inputVerifyService.setBusiness(business);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置是否验证
	 */
	@PostMapping("/processor/verify")
	ResponseEntity setVerify(@RequestParam("verify") Boolean verify)
	{
		InputVerifyProperties properties = this.inputVerifyService.setVerify(verify);

		return ResponseEntity.ok(properties);
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/processor/properties")
	ResponseEntity setProperties(@RequestParam("name") String name, @RequestBody List<PropertyMetadataTDO> properties/*Map<String, String> properties*/)
	{
		// check parameter

		//if(properties.isEmpty())
		//	return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("fail to properties!");

		Map<String, InputVerifyProperties.Property> tmp = new HashMap<>();

		/*for(Map.Entry<String, String> entry : properties.entrySet())
		{
			list.add(new InputVerifyProperties.Property(entry.getKey(), entry.getValue(), 1, false));
		}*/

		for(PropertyMetadataTDO tdo : properties)
		{
			tmp.put(tdo.getName(), new InputVerifyProperties.Property(tdo.getName(), tdo.getType(), tdo.getIndex()));
		}

		InputVerifyProperties props = this.inputVerifyService.setProperties(tmp);

		return ResponseEntity.ok(props);
	}
}
