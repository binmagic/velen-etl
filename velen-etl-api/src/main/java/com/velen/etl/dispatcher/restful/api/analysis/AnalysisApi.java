package com.velen.etl.dispatcher.restful.api.analysis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("velen-etl-dispatcher-analysis")
public interface AnalysisApi
{
	/**
	 * 测试用
	 */
	@GetMapping("/project/dispatch/dataflow/test")
	ResponseEntity test();
}
