package com.velen.etl.verification.api;

import com.velen.etl.verification.dto.FieldRuleTDO;
import com.velen.etl.verification.dto.ParseFormatTDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Deprecated
@FeignClient("velen-etl-verification")
public interface ProjectApi
{
	/**
	 * 测试用
	 */
	@PostMapping("/project/verification/test")
	String test(@RequestParam("arg") String arg);

	/**
	 * 创建项目
	 */
	@PostMapping("/project/verification/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("enforced") Boolean enforcedVerify,
	                      @RequestParam("operator") String operator);

	/**
	 * 设置清洗规则
	 */
	//@PostMapping("/project/create")
	//ResponseEntity extract(@RequestParam("project") String project);

	/**
	 * 设置规则
	 */
	@PostMapping("/project/verification/settle")
	ResponseEntity settle(@RequestParam("appId") String appId, @RequestParam("enforced") Boolean enforcedVerify
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator);

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/project/verification/set-input-parse")
	ResponseEntity setInputParse(@RequestParam("appId") String appId
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO
			, @RequestParam("operator") String operator);

	/**
	 * 设置字段入库规则
	 */
	@PostMapping("/project/verification/set-field-rule")
	ResponseEntity setFieldRule(@RequestParam("appId") String appId
			, @RequestParam("fields") List<FieldRuleTDO> fieldRuleTDO
			, @RequestParam("operator") String operator);

	/**
	 * 部署项目
	 */
	@Deprecated
	@PostMapping("/project/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("operator") String operator);


}
