package com.velen.etl.dispatcher.controller;

import com.velen.etl.dispatcher.stream.api.ExtractApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Deprecated
@Component
public class DynamicFeignClient
{
	/*interface MyRequest
	{
		@RequestMapping(value = "/stream/source/test", method = RequestMethod.POST)
		String call();

		@RequestMapping(value = "/stream/source/test1", method = RequestMethod.POST)
		String call(@RequestBody List<Integer> list);
	}*/

	FeignClientBuilder feignClientBuilder;

	public DynamicFeignClient(@Autowired ApplicationContext appContext)
	{
		this.feignClientBuilder = new FeignClientBuilder(appContext);
	}

	/*
	 * Dynamically call a service registered in the directory.
	 */

	public String doCall(String id)
	{

		// create a feign client

		ExtractApi fc = this.feignClientBuilder.forType(ExtractApi.class, id).build();

		// make the call
		return fc.test();
	}

	public String doCall(String id, List<Integer> list)
	{

		// create a feign client

		ExtractApi fc = this.feignClientBuilder.forType(ExtractApi.class, id).build();

		// make the call
		return fc.test(list);
	}
}
