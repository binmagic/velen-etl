package com.velen.etl.dispatcher.restful.api.dataflow;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * https://www.springcloud.cc/spring-cloud-dataflow.html#api-guide
 * https://www.springcloud.cc/spring-cloud-dataflow.html#dataflow-template
 */
@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface DataFlowApi
{
	/**
	 * 测试用
	 */
	@GetMapping("/project/dispatch/dataflow/test")
	ResponseEntity test();

	/**
	 * 注册App
	 * A POST request on /apps/<type>/<name>/<version> allows registration of a new application in Skipper mode.
	 * @param type [app，source，processor，sink，task]之一
	 */
	@PostMapping("/project/dispatch/dataflow/register")
	ResponseEntity register(@RequestParam("type") String type, @RequestParam("name") String name, @RequestParam("version") String version
			, @RequestParam("uri") String uri, @RequestParam("force") Boolean force);

	/**
	 * 定义stream
	 */
	@PostMapping("/project/dispatch/dataflow/define")
	ResponseEntity define(@RequestParam("name") String name, @RequestParam("definition") String definition
			, @RequestParam("deploy") Boolean deploy);

	/**
	 * 部署stream
	 */
	@PostMapping("/project/dispatch/dataflow/deploy")
	ResponseEntity deploy(@RequestParam("name") String name);

	/**
	 * 取消stream
	 */
	@PostMapping("/project/dispatch/dataflow/cancel")
	ResponseEntity cancel();

	/**
	 * 取消所有stream
	 */
	@PostMapping("/project/dispatch/dataflow/cancel-all")
	ResponseEntity cancelAll();

	/**
	 *
	 */
	ResponseEntity execute();
}
