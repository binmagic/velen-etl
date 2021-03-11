package com.velen.etl.dispatcher.restful.api;

import com.sun.istack.NotNull;
import com.velen.etl.dispatcher.entity.JobSubmitTDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("velen-etl-dispatcher")
public interface DispatchApi
{
	static String make(String id, String procedure)
	{
		return procedure + "-" + id;
	}
	/**
	 * 测试用
	 */
	@RequestMapping("/project/dispatch/test/{arg}")
	String test(@PathVariable("arg") String arg);

	/**
	 * 部署stream
	 */
	//@PostMapping("/project/dispatch/deploy")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 获取stream状态
	 */
	//@GetMapping("/project/dispatch/status-stream")
	//ResponseEntity statusStream(@RequestParam("name") String name);

	/**
	 * 部署
	 */
	@PostMapping("/project/dispatch/deploy")
	ResponseEntity deploy(@RequestParam(value = "platform") String platform, @RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("definition") String definition, @RequestBody Map<String, String> properties);

	/**
	 * 部署
	 */
	@PostMapping("/project/dispatch/deploy1")
	ResponseEntity deploy(@NotNull @RequestParam(value = "platform") String platform, @RequestParam("type") String type, @NotNull @RequestBody JobSubmitTDO jobSubmitTDO);

	/**
	 * 获取当前app状态
	 * 暂时通过 {@link com.velen.etl.ResultCode} 来决定状态
	 */
	@PostMapping("/project/dispatch/status")
	ResponseEntity status(@NotNull @RequestParam(value = "platform") String platform, @NotNull @RequestParam(value = "appId") String appId);

	/**
	 * 关停当前app
	 */
	@PostMapping("/project/dispatch/kill")
	ResponseEntity kill(@NotNull @RequestParam(value = "platform") String platform, @NotNull @RequestParam(value = "appId") String appId);

	/**
	 * 更新对应stream的配置规则
	 * 如果服务是启动着的，将会更新否则不会
	 */
	//@PostMapping("/project/dispatch/update-stream")
	//ResponseEntity updateStream(@RequestParam("appId") String appId, @RequestParam("type") String type);

	/**
	 * 更新 definition 的配置
	 */
	@Deprecated
	//@PostMapping("/project/dispatch/update-stream")
	//ResponseEntity updateStream(@RequestParam("platform") String platform, @RequestParam("type") String type, @RequestParam("definition") String definition);
	@PostMapping("/project/dispatch/update-definition")
	ResponseEntity updateDefinition(@RequestParam(value = "platform", required = false) String platform, @RequestParam("type") String type, @RequestParam("procedure") String procedure, @RequestParam("definition") String definition);

	/**
	 * 更新task调度规则
	 * 更新 definition 的配置
	 */
	//@PostMapping("/project/dispatch/update-task")
	//ResponseEntity updateTask(@RequestParam("platform") String platform, @RequestParam("type") String type, @RequestParam("definition") String definition);

	/**
	 * 更新stream调度规则
	 * 更新正在进行程序的配置或 restful 调用修改配置
	 */
	@Deprecated
	@PostMapping("/project/dispatch/update-stream")
	ResponseEntity updateStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestBody(required = false) Map<String, String> properties);


	/**
	 * 部署stream
	 * 如果项目是运行时将会重新部署
	 * platform 规则由 dispatcher 服务决定，外部服务无权控制
	 * id 的换转规则由 dispatcher 服务提供，外部服务无权控制
	 */
	@Deprecated
	@PostMapping("/project/dispatch/deploy-stream")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type);
	//ResponseEntity deploy(@RequestParam("id") String id, @RequestParam("type") DeployEnum.StreamDeployType type);
	// TODO: platform 涉及到 [dataflow, flink， yarn 等] 是否要混用的问题，暂时完全由 dispatcher 服务决定，外部服务无权控制
	//ResponseEntity deployStream(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type);
	ResponseEntity deployStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestBody(required = false) Map<String, String> properties);

	/**
	 * 部署task
	 * 如果项目是运行时将会重新部署
	 */
	//@PostMapping("/project/dispatch/deploy-task")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.TaskDeployType type);
	//ResponseEntity deployTask(@RequestParam("appId") String appId, /*@RequestParam("platform") DeployEnum.PlatformType platform, */@RequestParam("procedure") String procedure, @RequestBody Map<String, String> properties);

	/**
	 * 部署app
	 * 如果项目是运行时将会重新部署
	 */
	//@PostMapping("/project/dispatch/deploy-app")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.AppDeployType type);
	//ResponseEntity deployApp(@RequestParam("appId") String appId, /*@RequestParam("platform") DeployEnum.PlatformType platform, */@RequestParam("procedure") String procedure, @RequestBody Map<String, String> properties);

	/**
	 * 设置输入流的解释方式
	 */
	//@PostMapping("/project/dispatch/collection/fields")
	//ResponseEntity setFields(@RequestParam("flow") String flow, @RequestParam("appId") String appId, @RequestParam("args") List<String> args, @RequestBody List<FieldRuleTDO> fields);

	/**
	 * 设置输入流的解释方式
	 */
	//@PostMapping("/project/dispatch/collection/formats")
	//ResponseEntity setFormats(@RequestParam("flow") String flow, @RequestParam("appId") String appId, @RequestParam("args") List<String> args, @RequestBody List<ParseFormatTDO> parseFormatsTDO);

	/**
	 * 设置是否验证
	 */
	//@PostMapping("/project/dispatch/collection/verify")
	//ResponseEntity setVerify(@RequestParam("flow") String flow, @RequestParam("appId") String appId, @RequestParam("args") List<String> args, @RequestParam("verify") Boolean verify);

	/**
	 * 设置输入流的解释方式
	 */
	//@PostMapping("/project/dispatch/collection/fields")
	//ResponseEntity setFields(@RequestParam("name") String name, @RequestBody List<PropertyMetadataTDO> fields);


}
