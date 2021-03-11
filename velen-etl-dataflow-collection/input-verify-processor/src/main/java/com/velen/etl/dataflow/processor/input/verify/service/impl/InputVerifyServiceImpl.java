package com.velen.etl.dataflow.processor.input.verify.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.processor.input.verify.properties.InputVerifyProperties;
import com.velen.etl.dataflow.processor.input.verify.service.InputVerifyService;
import com.velen.etl.generator.api.TableMetadataApi;
import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {InputVerifyProperties.class})
@EnableFeignClients(basePackageClasses = {TableMetadataApi.class})
public class InputVerifyServiceImpl implements InputVerifyService
{
	@Autowired
	private Processor processor;

	@Autowired
	private InputVerifyProperties inputVerifyProperties;

	@Autowired
	private TableMetadataApi tableMetadataApi;

	@Override
	public String test(String message)
	{
		return message;
	}

	@Override
	public InputVerifyProperties setAppId(String appId)
	{
		inputVerifyProperties.setAppId(appId);
		return inputVerifyProperties;
	}

	@Override
	public InputVerifyProperties setBusiness(String business)
	{
		inputVerifyProperties.setBusiness(business);
		return inputVerifyProperties;
	}

	@Override
	public InputVerifyProperties setVerify(boolean verify)
	{
		inputVerifyProperties.setVerify(verify);
		return inputVerifyProperties;
	}

	/*@Override
	public InputVerifyProperties setFields(List<InputVerifyProperties.Field> fields)
	{
		inputVerifyProperties.setFields(fields);
		return inputVerifyProperties;
	}*/

	/*@Override
	public InputVerifyProperties setProperties(String name, List<InputVerifyProperties.Property> properties)
	{
		this.inputVerifyProperties.setProperties(name, properties);
		return inputVerifyProperties;
	}*/

	@Override
	public InputVerifyProperties setProperties(Map<String, InputVerifyProperties.Property> properties)
	{
		this.inputVerifyProperties.setProperties(properties);
		return inputVerifyProperties;
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Object transform(JSONObject payload)
	{
		// TODO: 现在不确定 JSON 会不会变位置

		JSONObject jsonObject = new JSONObject();
		Map<String, InputVerifyProperties.Property> properties = this.inputVerifyProperties.getProperties();

		if(true/*this.inputVerifyProperties.isVerify()*/)
		{

			List<PropertyMetadataTDO> tdos = new ArrayList<>();

			Set<Map.Entry<String, Object>> entries = payload.entrySet();
			//for(int i = 0; i < entries.size(); ++i)
			for(Map.Entry<String, Object> entry : entries)
			{
				InputVerifyProperties.Property property = properties.get(entry.getKey());
				if(property == null)
				{
					String name = entry.getKey();
					String type = entry.getValue().getClass().getSimpleName();

					tdos.add(new PropertyMetadataTDO(name, type, "", 0));


					//jsonObject.put(entry.getKey(), entry.getValue());
				}
			}

			//if(!tdos.isEmpty())
			//	tableMetadataApi.updateTable(this.inputVerifyProperties.getAppId(), this.inputVerifyProperties.getBusiness(), tdos);
		}
		else
		{

			Set<Map.Entry<String, Object>> entries = payload.entrySet();
			for(Map.Entry<String, Object> entry : entries)
			//for(int i = 0; i < entries.size(); ++i)
			{
				//Map.Entry<String, Object> entry = entries
				InputVerifyProperties.Property property = properties.get(entry.getKey());
				if(property != null)
				{
					// 类型验证
					jsonObject.put(entry.getKey(), entry.getValue());
				}
			}
		}

		if(jsonObject.isEmpty())
			processor.output().send(MessageBuilder.withPayload(jsonObject).build());

		return payload;
	}
}
