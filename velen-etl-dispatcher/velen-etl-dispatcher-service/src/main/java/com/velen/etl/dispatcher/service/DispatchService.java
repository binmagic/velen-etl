package com.velen.etl.dispatcher.service;

import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface DispatchService
{
	/**
	 * 更新调度规则
	 */
	boolean updateStream(DeployEnum.PlatformType platformType, String type, String definition);

	/**
	 * 更新调度规则
	 */
	boolean updateTask(DeployEnum.PlatformType platformType, String type, String definition);

	/**
	 * 部署stream
	 */
	boolean deployStream(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type);

	/**
	 * 部署task
	 */
	boolean deployTask(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type);

	/**
	 * 部署app
	 */
	boolean deployApp(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type);

}
