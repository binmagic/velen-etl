package com.velen.etl.dispatcher.restful.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface DispatchApi
{
	/**
	 * 部署stream
	 */
	@PostMapping("/project/dispatch/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("operator") String operator);


}
