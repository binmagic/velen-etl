package com.velen.etl.dispatcher.restful.api.stream;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface StreamApi
{
	/**
	 * 启动stream处理
	 */
	@PostMapping("/project/dispatch/stream/startup")
	ResponseEntity define(@RequestParam("appId") String appId, @RequestParam("job") String job,
	                      @RequestParam("operator") String operator);

	/**
	 * 关闭stream处理
	 */
	@PostMapping("/project/dispatch/stream/shutdown")
	ResponseEntity shutdown(@RequestParam("appId") String appId, @RequestParam("identity") String identity,
	                      @RequestParam("operator") String operator);
}
