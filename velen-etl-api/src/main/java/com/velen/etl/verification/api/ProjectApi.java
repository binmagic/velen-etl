package com.velen.etl.verification.api;

import com.velen.etl.verification.tdo.FieldRuleTDO;
import com.velen.etl.verification.tdo.ParseFormatTDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 项目启动的流处理的更新是从这里调， 还是从 dispatcher 里调
 */
@FeignClient("velen-etl-verification")
public interface ProjectApi
{
	/**
	 * 测试用
	 */
	@RequestMapping("/project/verification/{arg}")
	String test(@PathVariable("arg") String arg);

	/**
	 * 创建项目
	 */
	@PostMapping("/project/verification/create")
	ResponseEntity create(@RequestParam("appId") String appId, /*@RequestParam("topic") String topic, */@RequestParam("verify") Boolean verify,
	                      @RequestParam("operator") String operator);

	/**
	 * 删除项目
	 */
	@PostMapping("/project/verification/destroy")
	ResponseEntity destroy(@RequestParam("appId") String appId);

	/**
	 * 设置校验模式
	 */
	@PostMapping("/project/verification/set-verify")
	ResponseEntity setVerify(@RequestParam("appId") String appId, @RequestParam("verify") Boolean verify, @RequestParam("operator") String operator);

	/**
	 * 设置输入流的解释方式(也用于属性更新)
	 */
	@PostMapping("/project/verification/set-input-parse")
	ResponseEntity setInputParse(@RequestParam("appId") String appId
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO
			, @RequestParam("operator") String operator);

	/**
	 * 设置字段入库规则(也用于属性更新)
	 */
	@PostMapping("/project/verification/set-field-rule")
	ResponseEntity setFieldRule(@RequestParam("appId") String appId
			, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator);

	/**
	 * 设置清洗规则
	 */
	//@PostMapping("/project/create")
	//ResponseEntity extract(@RequestParam("project") String project);

	/**
	 * 设置规则
	 */
	@PostMapping("/project/verification/settle")
	ResponseEntity settle(@RequestParam("appId") String appId, /*@RequestParam("topic") String topic, */@RequestParam("verify") Boolean verify
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator);

	/**
	 * 部署stream
	 */
	@Deprecated
	@PostMapping("/project/verification/deploy-stream")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type, @RequestParam("operator") String operator);
	ResponseEntity deployStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestParam("operator") String operator);

	/**
	 * 部署task
	 */
	@Deprecated
	@PostMapping("/project/verification/deploy-task")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.TaskDeployType type, @RequestParam("operator") String operator);
	ResponseEntity deployTask(@RequestParam("appId") String appId, @RequestParam("type") String type, @RequestParam("operator") String operator);

	/**
	 * 部署app
	 */
	@Deprecated
	@PostMapping("/project/verification/deploy-app")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.AppDeployType type, @RequestParam("operator") String operator);
	ResponseEntity deployApp(@RequestParam("appId") String appId, @RequestParam("type") String type, @RequestParam("operator") String operator);
}