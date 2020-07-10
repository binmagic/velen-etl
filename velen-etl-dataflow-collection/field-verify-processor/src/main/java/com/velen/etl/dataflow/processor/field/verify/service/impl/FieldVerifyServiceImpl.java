package com.velen.etl.dataflow.processor.field.verify.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.processor.field.verify.properties.FieldVerifyProperties;
import com.velen.etl.dataflow.processor.field.verify.service.FieldVerifyService;
import com.velen.etl.generator.entity.GenerateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {FieldVerifyProperties.class})
public class FieldVerifyServiceImpl implements FieldVerifyService
{
	@Autowired
	private FieldVerifyProperties fieldVerifyProperties;

	@Autowired
	private Processor processor;

	@Override
	public String test(String message)
	{
		//return transform(message).toString();
		return message;
	}

	@Override
	public FieldVerifyProperties setAppId(String appId)
	{
		fieldVerifyProperties.setAppId(appId);
		return fieldVerifyProperties;
	}

	@Override
	public FieldVerifyProperties setBusiness(String business)
	{
		fieldVerifyProperties.setBusiness(business);
		return fieldVerifyProperties;
	}

	@Override
	public FieldVerifyProperties setFields(List<FieldVerifyProperties.Field> fields)
	{
		fieldVerifyProperties.setFields(fields);
		return fieldVerifyProperties;
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Processor.INPUT)
	//@SendTo(Processor.OUTPUT)
	public Object transform(JSONObject payload)
	{
		// 这个好像可以将攒的功能写到这里

		JSONArray jsonArray = new JSONArray();
		List<FieldVerifyProperties.Field> fields = this.fieldVerifyProperties.getFields();

		for(FieldVerifyProperties.Field field : fields)
		{
			Object value = payload.get(field.getName());
			if(value == null)
			{
				GenerateEnum.DataType type = GenerateEnum.DataType.parse(field.getKeyType());
				jsonArray.add(type.getDefaultValue());
			}
			else
			{
				jsonArray.add(value);
			}
		}

		if(!jsonArray.isEmpty())
			processor.output().send(MessageBuilder.withPayload(jsonArray).build());

		return payload;
	}
}
