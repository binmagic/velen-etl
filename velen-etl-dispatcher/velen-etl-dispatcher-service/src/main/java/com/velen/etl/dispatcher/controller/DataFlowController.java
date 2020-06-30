package com.velen.etl.dispatcher.controller;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.configuration.DataFlowConfiguration;
import com.velen.etl.dispatcher.service.DataFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.core.ApplicationType;
import org.springframework.cloud.dataflow.rest.client.DataFlowOperations;
import org.springframework.cloud.dataflow.rest.client.DataFlowTemplate;
//import org.springframework.cloud.dataflow.rest.resource.StreamDefinitionResource;
//import org.springframework.cloud.dataflow.rest.util.HttpClientConfigurer;
import org.springframework.cloud.dataflow.rest.resource.AppRegistrationResource;
import org.springframework.cloud.dataflow.rest.resource.StreamDefinitionResource;
import org.springframework.cloud.dataflow.rest.resource.StreamDeploymentResource;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project/dispatch/dataflow")
public class DataFlowController
{
	static Map<String, ApplicationType> APP_PARSER = new HashMap<>();
	static
	{
		for(ApplicationType o : ApplicationType.values())
		{
			APP_PARSER.put(o.name(), o);
		}
	}
	public static ApplicationType parseType(String string)
	{
		return APP_PARSER.getOrDefault(string, ApplicationType.app);
	}

	//@Autowired
	//private DataFlowConfiguration dataFlowConfiguration;

	//@Autowired
	//private DataFlowTemplate dataFlowTemplate;

	@Autowired
	private DataFlowOperations dataFlowOperations;

	// 这个是否需要，还是写一个管理调度的
	//@Autowired
	//private DataFlowService dataFlowService;

	//private StringBuilder stringBuilder;
	//private MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();

	@GetMapping("/test")
	ResponseEntity test()
	{
		PagedModel<StreamDefinitionResource> list = dataFlowOperations.streamOperations().list();

		/*stringBuilder = new StringBuilder();
		stringBuilder.append(dataFlowConfiguration.getUri());
		stringBuilder.append("/streams/deployments");
		stringBuilder.append("/");
		stringBuilder.append("test111");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("{\"app.time.timestamp.format\":\"YYYY\"}", headers);
		//parameters.clear();
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity responseEntity = restTemplate.postForEntity(stringBuilder.toString(), entity, String.class);

		return responseEntity;*/

		return ResponseEntity.ok(list);
	}

	/**
	 * 注册App
	 * /apps/<type>/<name>上的POST请求允许注册新的申请
	 * @param type [app，source，processor，sink，task]之一
	 */
	@PostMapping("/register")
	ResponseEntity register(@RequestParam("name") String name, @RequestParam("type") String type
			, @RequestParam("uri") String uri, @RequestParam("metadataUri") String metadataUri, @RequestParam("force") boolean force)
	{
		AppRegistrationResource resource = dataFlowOperations.appRegistryOperations().register(name, parseType(type), uri, metadataUri, force);

		return ResponseEntity.ok(resource);


		/*stringBuilder = new StringBuilder();
		stringBuilder.append(dataFlowConfiguration.getUri());
		stringBuilder.append("/apps");
		stringBuilder.append("/");
		stringBuilder.append(type);
		stringBuilder.append("/");
		stringBuilder.append(name);
		if(!StringUtils.isEmpty(version))
		{
			stringBuilder.append("/");
			stringBuilder.append(version);
		}

		parameters.clear();
		parameters.add("uri", uri);
		parameters.add("force", String.valueOf(force));

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForEntity(stringBuilder.toString(), parameters, String.class);*/
	}

	/**
	 * 定义stream
	 */
	@PostMapping("/define")
	ResponseEntity define(@RequestParam("name") String name, @RequestParam("definition") String definition
			, @RequestParam("description") String description, @RequestParam("deploy") Boolean deploy)
	{
		StreamDefinitionResource resource = dataFlowOperations.streamOperations().createStream(name, definition, description, deploy);

		return ResponseEntity.ok(resource.getStatusDescription());


		/*stringBuilder = new StringBuilder();
		stringBuilder.append(dataFlowConfiguration.getUri());
		stringBuilder.append("/streams/definitions");

		parameters.clear();
		parameters.add("name", name);
		parameters.add("definition", definition);
		parameters.add("deploy", String.valueOf(deploy));

		RestTemplate restTemplate = new RestTemplate();*/

		//return restTemplate.postForEntity(stringBuilder.toString(), parameters, String.class);

		//return ResponseEntity.ok().build();
	}

	/**
	 * 列出相关的流定义
	 * 流端点允许您列出相关的流定义
	 */
	@PostMapping("/get")
	ResponseEntity get(@RequestParam("name") String name)
	{
		StreamDefinitionResource resource = dataFlowOperations.streamOperations().getStreamDefinition(name);
		return ResponseEntity.ok(resource.getStatus());
	}

	/**
	 * 部署stream
	 */
	@PostMapping("/deploy")
	ResponseEntity deploy(@RequestParam("name") String name, @RequestBody Map<String, String> properties)
	{
		dataFlowOperations.streamOperations().deploy(name, properties);

		return ResponseEntity.ok().build();


		/*stringBuilder = new StringBuilder();
		stringBuilder.append(dataFlowConfiguration.getUri());
		stringBuilder.append("/streams/deployments");
		stringBuilder.append("/");
		stringBuilder.append(name);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>("{\"app.time.timestamp.format\":\"YYYY\"}", headers);

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForEntity(stringBuilder.toString(), entity, String.class);*/
	}

}
