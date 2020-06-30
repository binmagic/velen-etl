package com.velen.etl.dispatcher.restful.api;

import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("velen-etl-dispatcher")
public interface DispatchApi
{
	/**
	 * 部署stream
	 */
	//@PostMapping("/project/dispatch/deploy")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("operator") String operator);


	/**
	 * 部署stream
	 */
	@PostMapping("/project/dispatch/stream/deploy")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type);
	ResponseEntity deploy(@RequestParam("id") String id, @RequestParam("type") DeployEnum.StreamDeployType type);

	/**
	 * 部署task
	 */
	@PostMapping("/project/dispatch/task/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.TaskDeployType type);

	/**
	 * 部署app
	 */
	//@PostMapping("/project/dispatch/app/deploy")
	//ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.AppDeployType type);


}
