package com.velen.etl.dataflow.processor.assemble.meta.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.processor.assemble.meta.properties.AssembleMetaProperties;
import com.velen.etl.dataflow.processor.assemble.meta.service.AssembleMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {AssembleMetaProperties.class})
public class AssembleMetaServiceImpl implements AssembleMetaService
{
	@Autowired
	private AssembleMetaProperties assembleMetaProperties;


	@Override
	public String test(String message)
	{
		//return transform(message).toString();
		return message;
	}

	@Override
	public AssembleMetaProperties setAppId(String appId)
	{
		assembleMetaProperties.setAppId(appId);
		return assembleMetaProperties;
	}

	@Override
	public AssembleMetaProperties setBusiness(String business)
	{
		assembleMetaProperties.setBusiness(business);
		return assembleMetaProperties;
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Object transform(JSONObject payload)
	{



		return "";
	}
}
