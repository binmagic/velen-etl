package com.velen.etl.dispatcher.restful.api;

import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("velen-etl-dispatcher")
public interface DispatchApi
{
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
	 * 更新对应stream的配置规则
	 * 如果服务是启动着的，将会更新否则不会
	 */
	//@PostMapping("/project/dispatch/update-stream")
	//ResponseEntity updateStream(@RequestParam("appId") String appId, @RequestParam("type") String type);

	/**
	 * 更新 definition 的配置
	 */
	//@PostMapping("/project/dispatch/update-stream")
	//ResponseEntity updateStream(@RequestParam("platform") String platform, @RequestParam("type") String type, @RequestParam("definition") String definition);
	@PostMapping("/project/dispatch/update-definition")
	ResponseEntity updateDefinition(@RequestParam(value = "platform", required = false) String platform, @RequestParam("type") DeployEnum.ProcessType type, @RequestParam("procedure") String procedure, @RequestParam("definition") String definition);

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
	@PostMapping("/project/dispatch/update-stream")
	ResponseEntity updateStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestBody(required = false) Map<String, String> properties);


	/**
	 * 部署stream
	 * 如果项目是运行时将会重新部署
	 * platform 规则由 dispatcher 服务决定，外部服务无权控制
	 * id 的换转规则由 dispatcher 服务提供，外部服务无权控制
	 */
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


}
