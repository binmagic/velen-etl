package com.velen.etl.dispatcher.restful.api.dataflow;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Map;

/**
 * https://www.springcloud.cc/spring-cloud-dataflow.html#api-guide
 * https://www.springcloud.cc/spring-cloud-dataflow.html#dataflow-template
 */
@FeignClient("velen-etl-dispatcher-dataflow")
public interface DataFlowApi
{
	/**
	 * 测试用
	 */
	@RequestMapping("/project/dispatch/dataflow/test/{arg}")
	String test(@PathVariable("arg") String arg);

	/**
	 * 注册App
	 * /apps/<type>/<name>上的POST请求允许注册新的申请
	 * @param type [app，source，processor，sink，task]之一
	 */
	@PostMapping("/project/dispatch/dataflow/register")
	ResponseEntity register(@RequestParam("name") String name, @RequestParam("type") String type
			, @RequestParam("uri") String uri, @RequestParam("metadataUri") String metadataUri, @RequestParam("force") boolean force);

	/**
	 * 创建新的流定义
	 * 通过为流定义端点创建POST请求来实现创建流定义
	 */
	@PostMapping("/project/dispatch/dataflow/define")
	ResponseEntity define(@RequestParam("name") String name, @RequestParam("definition") String definition
			, @RequestParam("description") String description, @RequestParam("deploy") Boolean deploy);


	/**
	 * 列出相关的流定义
	 * 流端点允许您列出相关的流定义
	 */
	@PostMapping("/project/dispatch/dataflow/get")
	ResponseEntity get(@RequestParam("name") String name);


	/**
	 *
	 */
	//void list();

	@PostMapping("/project/dispatch/dataflow/info")
	ResponseEntity info(@RequestParam("name") String name);


	/**
	 * 流验证
	 * 流验证端点允许您在流定义中验证应用程序
	 */
	//ResponseEntity validate(String name);

	/**
	 * 更新stream
	 */
	//ResponseEntity update(String streamName, String releaseName, PackageIdentifier packageIdentifier,
	//             Map<String, String> updateProperties, boolean force, List<String> appNames);

	/**
	 * 回滚stream
	 */
	//ResponseEntity rollback(@RequestParam("name") String name, int version);

	/**
	 * 获得清单
	 * 返回已发布版本的清单。对于具有依赖项的包，清单包含这些依赖项的内容。
	 */
	//@PostMapping("/project/dispatch/dataflow/manifest")
	//String manifest(@RequestParam("name") String name, @RequestParam("version") int version);



	/**
	 * 部署stream
	 */
	@PostMapping("/project/dispatch/dataflow/deploy")
	ResponseEntity deploy(@RequestParam("name") String name, @RequestBody Map<String, String> properties);

	/**
	 * 取消stream
	 */
	@PostMapping("/project/dispatch/dataflow/undeploy")
	ResponseEntity undeploy(@RequestParam("name") String name);

	/**
	 * 取消所有stream
	 */
	@PostMapping("/project/dispatch/dataflow/undeploy-all")
	ResponseEntity undeployAll();

	/**
	 *
	 */
	//ResponseEntity execute();
}
