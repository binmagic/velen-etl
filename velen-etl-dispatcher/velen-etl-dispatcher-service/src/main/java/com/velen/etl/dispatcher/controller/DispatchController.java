package com.velen.etl.dispatcher.controller;


import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project/dispatch")
public class DispatchController
{
	/**
	 * 部署stream
	 */
	@PostMapping("/project/dispatch/stream/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type)
	{
		String name = "";
		String definition = "";

		// dataflow: get -> define -> undeploy -> deploy


		return ResponseEntity.ok().build();
	}

	/**
	 * 部署task
	 */
	@PostMapping("/project/dispatch/task/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.TaskDeployType type)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 部署app
	 */
	@PostMapping("/project/dispatch/app/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.AppDeployType type)
	{
		return ResponseEntity.ok().build();
	}
}
