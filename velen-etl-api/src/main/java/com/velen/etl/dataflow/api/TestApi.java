package com.velen.etl.dataflow.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("test")
public interface TestApi
{
	/**
	 * 测试用
	 */
	@GetMapping("/project/dataflow/stream/get")
	String get();

	/**
	 * 更新
	 */
	@RequestMapping("/project/dataflow/stream/update")
	String update(@RequestParam("topic") String topic, @RequestParam("enforced") Boolean enforcedVerify);
}
