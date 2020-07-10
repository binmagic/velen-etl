package com.velen.etl.dispatcher.controller;


import com.velen.etl.ResultCode;
import com.velen.etl.configuration.DispatchFlowConfiguration;
import com.velen.etl.dispatcher.service.DataFlowStreamService;
import com.velen.etl.verification.entity.DeployEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/project/dispatch")
public class DispatchController
{
	@Autowired
	private DataFlowStreamService dataFlowStreamService;

	//@Autowired
	//private DispatchService dispatchService;

	//@Autowired
	//private DynamicFeignClient feignClient;

	private String PROPERTY_APPLICATION_NAME = "spring.application.name";

	// TODO: 暂时先写在这里
	//public static String PREFIX_STREAM = "stream";
	//public static String PREFIX_TASK = "task";
	//public static String PREFIX_APP = "app";

	public static String makeDataId(String appId, String procedure)
	{
		return appId + "-" + procedure;
	}

	@Autowired
	public DispatchFlowConfiguration dispatchFlowConfiguration;

	// 动态 feign 调用
	FeignClientBuilder feignClientBuilder;

	public DispatchController(@Autowired ApplicationContext appContext)
	{
		this.feignClientBuilder = new FeignClientBuilder(appContext);
	}

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg)
	{
		/*Object ret = feignClient.doCall("aaaaa");
		System.out.println(ret);

		List<Integer> list = new ArrayList<>();
		list.add(1);
		Object ret1 = feignClient.doCall("aaaaa", list);
		System.out.println(ret1);*/
		return "OK!";
	}

	/**
	 * 更新 definition 的配置
	 */
	//@PostMapping("/project/dispatch/update-stream")
	//ResponseEntity updateStream(@RequestParam("platform") String platform, @RequestParam("type") String type, @RequestParam("definition") String definition);
	@PostMapping("/update-definition")
	ResponseEntity updateDefinition(@RequestParam(value = "platform", required = false) String platform, @RequestParam("type") DeployEnum.ProcessType type, @RequestParam("procedure") String procedure, @RequestParam("definition") String definition)
	{
		//if(type == DeployEnum.ProcessType.INVALID)
		//	return ResponseEntity.status(ResultCode.DISPATCH_INVALID_PROCESS).build();

		DeployEnum.PlatformType platformType;
		if(StringUtils.isEmpty(platform))
		{
			platformType = DeployEnum.PlatformType.DATAFLOW;
		}
		else
		{
			platformType = DeployEnum.PlatformType.parse(platform);
			if(platformType == DeployEnum.PlatformType.INVALID)
				return ResponseEntity.status(ResultCode.DISPATCH_UNSUPPORTED_PLATFORM).build();
		}

		DispatchFlowConfiguration.DataFlowDefinition def = dispatchFlowConfiguration.getDataFlow();
		if(def == null)
		{
			def = new DispatchFlowConfiguration.DataFlowDefinition();
			dispatchFlowConfiguration.setDataFlow(def);
		}
		/*switch(platformType)
		{
			case INVALID:
			case DATAFLOW:
				def = dispatchFlowConfiguration.getDataFlow();
			default:
				break;
		}*/

		Map<String, String> properties;
		if(type == DeployEnum.ProcessType.STREAM)
			properties = def.getStreams();
		else if(type == DeployEnum.ProcessType.TASK)
			properties = def.getTasks();
		else if(type == DeployEnum.ProcessType.APP)
			properties = def.getApps();
		else
			return ResponseEntity.status(ResultCode.DISPATCH_INVALID_PROCESS).build();

		properties.put(procedure, definition);

		return ResponseEntity.ok(dispatchFlowConfiguration);
	}


	/**
	 * 更新stream调度规则
	 *
	 */
	@PostMapping("/update-stream")
	ResponseEntity updateStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestBody(required = false) Map<String, String> properties)
	{
		throw new UnsupportedOperationException("/update-stream");
		//DeployEnum.PlatformType platformType = DeployEnum.PlatformType.parse(platform);
		//if(platformType == DeployEnum.PlatformType.INVALID)
		//	return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("platform is " + platform);


		// TODO: 这里还要细化设计，没法将调用中的详细异常返回给client
		//if(!dispatchService.updateStream(platformType, type, definition))
		//	return ResponseEntity.status(ResultCode.DISPATCH_UPDATE_STREAM_FAILURE).body("内容需要细化");

		//return ResponseEntity.ok().build();
	}

	/**
	 * 更新task调度规则
	 * 更新 definition 的配置
	 */
	/*@PostMapping("/update-task")
	ResponseEntity updateTask(@RequestParam("platform") String platform, @RequestParam("type") String type, @RequestParam("definition") String definition)
	{
		DeployEnum.PlatformType platformType = DeployEnum.PlatformType.parse(platform);
		if(platformType == DeployEnum.PlatformType.INVALID)
			return ResponseEntity.status(ResultCode.INVALID_PARAMETERS).body("platform is " + platform);

		// TODO: 这里还要细化设计，没法将调用中的详细异常返回给client
		//if(!dispatchService.updateTask(platformType, type, definition))
		//	return ResponseEntity.status(ResultCode.DISPATCH_UPDATE_TASK_FAILURE).body("内容需要细化");

		return ResponseEntity.ok().build();
	}*/

	/**
	 * 部署stream
	 * process: 创建流定义，启动
	 */
	@PostMapping("/deploy-stream")
	ResponseEntity deployStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestBody(required = false) Map<String, String> properties)
	{
		String name = makeDataId(appId, procedure);
		String DSL = this.dispatchFlowConfiguration.searchDefinition(procedure);
		if(StringUtils.isEmpty(DSL))
			return ResponseEntity.status(ResultCode.DISPATCH_INVALID_PROCEDURE).body(procedure);

		if(properties == null)
			properties = Collections.EMPTY_MAP;

		String oldDSL = this.dataFlowStreamService.getDefinition(name);

		if(StringUtils.isEmpty(oldDSL))
		{
			this.dataFlowStreamService.createStream(name, DSL, name, false);
		}
		else
		{
			if(this.dataFlowStreamService.isDeployed(name))
			{
				this.dataFlowStreamService.undeploy(name);
			}

			if(!DSL.equals(oldDSL))
			{
				// update
				this.dataFlowStreamService.destroy(name);
				this.dataFlowStreamService.createStream(name, DSL, name, false);
			}
		}

		// properties 传参
		// "app.<pipe>.spring.application.name": "a"
		this.dataFlowStreamService.deploy(name, properties);

		return ResponseEntity.ok(name);
	}

	/**
	 * 部署task
	 */
	/*@PostMapping("/deploy-task")
	ResponseEntity deployTask(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type)
	{
		return ResponseEntity.ok().build();
	}*/

	/**
	 * 部署app
	 */
	/*@PostMapping("/deploy-app")
	ResponseEntity deployApp(@RequestParam("appId") String appId, @RequestParam("platform") DeployEnum.PlatformType platform, @RequestParam("type") String type)
	{
		return ResponseEntity.ok().build();
	}*/
}
