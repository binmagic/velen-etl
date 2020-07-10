package com.velen.etl.collections.dataflow.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.collections.dataflow.properties.ParseProperties;
import com.velen.etl.collections.dataflow.service.ParseFormatService;
import com.velen.etl.common.channel.dataflow.processor.ParserProcessor;
import com.velen.etl.common.exception.InputParseException;
import com.velen.etl.configuration.InputParseFormatConfiguration;
import com.velen.etl.verification.entity.VerifyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Service
//@EnableBinding(ParserProcessor.class)
@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {ParseProperties.class})
public class ParseFormatServiceImpl implements ParseFormatService
{
	private final Logger logger = LoggerFactory.getLogger(ParseFormatService.class);

	interface ParseHandler
	{
		JSONObject parse(String regex, String content) throws InputParseException;
	}
	static Map<VerifyEnum.InputParseType, ParseHandler> HANDLERS = new HashMap<>();
	static
	{
		HANDLERS.put(VerifyEnum.InputParseType.JSON, new ParseHandler()
		{
			@Override
			public JSONObject parse(String regex, String content) throws InputParseException
			{
				return JSONObject.parseObject(content);
			}
		});

		HANDLERS.put(VerifyEnum.InputParseType.SPLIT, new ParseHandler()
		{
			String PREFIX = "INDEX_";
			@Override
			public JSONObject parse(String regex, String content) throws InputParseException
			{
				String[] values = content.split(regex);
				JSONObject jsonObject = new JSONObject();
				for(int i = 0; i < values.length; ++i)
				{
					jsonObject.put(PREFIX + i, values[i]);
				}
				return jsonObject;
			}
		});

		HANDLERS.put(VerifyEnum.InputParseType.REGEX, new ParseHandler()
		{
			@Override
			public JSONObject parse(String regex, String content) throws InputParseException
			{
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(content);
				if(matcher.find())
				{
					String tmp = matcher.group();
					return JSONObject.parseObject(content);
				}

				return null;
			}
		});

	}

	@Autowired
	private Processor processor;

	//private String topic;
	private boolean verify;
	private List<String> availableList = new ArrayList<>();
	// 暂时不先注入，要看如何使用配置
	private InputParseFormatConfiguration inputParseFormatConfiguration = new InputParseFormatConfiguration();

	@Override
	public String test(String message)
	{
		transform(message);
		return message;
	}

	@Override
	public void setVerify(boolean verify)
	{
		this.verify = verify;
	}

	@Override
	public void setInputParseFormats(List<InputParseFormatConfiguration.ParseFormatConfiguration> formats)
	{
		if(formats.isEmpty())
		{
			logger.error("formats is empty!");
			return;
		}

		inputParseFormatConfiguration.setFormats(formats);
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Object transform(String payload)
	{

		JSONObject jsonObject = null;
		for(InputParseFormatConfiguration.ParseFormatConfiguration o : this.inputParseFormatConfiguration.getFormats())
		{
			final VerifyEnum.InputParseType type = VerifyEnum.InputParseType.parse(o.getType());
			ParseHandler handler = HANDLERS.get(type);

			try
			{
				if(handler != null)
					jsonObject = handler.parse(o.getFormula(), payload);
			}
			catch(InputParseException e)
			{
				// ignore
				return "";
			}

			if(jsonObject != null)
				break;
		}

		processor.output().send(MessageBuilder.withPayload(jsonObject.toJSONString()).build());
		logger.error("OUTPUT:" + jsonObject.toJSONString());

		//return new SimpleDateFormat(("yyyy-mm-dd HH:mm:ss")).format(message);
		return payload;
	}

}
