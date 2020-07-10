package com.velen.etl.dataflow.parse.format.processor.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.velen.etl.dataflow.parse.format.processor.properties.ParseFormatProperties;
import com.velen.etl.dataflow.parse.format.processor.service.ParseFormatService;
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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Service
//@EnableBinding(ParserProcessor.class)
@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {ParseFormatProperties.class})
public class ParseFormatServiceImpl implements ParseFormatService
{
	private final Logger logger = LoggerFactory.getLogger(ParseFormatService.class);

	interface ParseHandler
	{
		JSONObject parse(String regex, String content) throws JsonParseException;
	}
	static Map<String, ParseHandler> HANDLERS = new HashMap<>();
	static
	{
		HANDLERS.put(VerifyEnum.InputParseType.JSON.name().toUpperCase(), new ParseHandler()
		{
			@Override
			public JSONObject parse(String regex, String content) throws JsonParseException
			{
				return JSONObject.parseObject(content);
			}
		});

		HANDLERS.put(VerifyEnum.InputParseType.SPLIT.name().toUpperCase(), new ParseHandler()
		{
			String PREFIX = "INDEX_";
			@Override
			public JSONObject parse(String regex, String content) throws JsonParseException
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

		HANDLERS.put(VerifyEnum.InputParseType.REGEX.name().toUpperCase(), new ParseHandler()
		{
			@Override
			public JSONObject parse(String regex, String content) throws JsonParseException
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

	@Autowired
	private ParseFormatProperties parseFormatProperties;

	//private List<ParseHandler> parseHandlers = new ArrayList<>();

	@Override
	public String test(String message)
	{
		return transform(message).toString();
	}

	@Override
	public ParseFormatProperties resetInputParseFormats(Map<String, String> formats)
	{
		if(formats.isEmpty())
		{
			logger.error("formats is empty!");
			return this.parseFormatProperties;
		}

		parseFormatProperties.setFormats(formats);

		return parseFormatProperties;
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Object transform(String payload)
	{

		JSONObject jsonObject = null;
		//for(InputParseFormatConfiguration.ParseFormatConfiguration o : this.inputParseFormatConfiguration.getFormats())
		for(Map.Entry<String, String> entry : this.parseFormatProperties.getFormats().entrySet())
		{
			String type = entry.getKey().toUpperCase();
			String formula = entry.getValue();

			//final VerifyEnum.InputParseType inputParseType = VerifyEnum.InputParseType.parse(type);
			ParseHandler handler = HANDLERS.get(type);

			try
			{
				if(handler != null)
					jsonObject = handler.parse(formula, payload);
			}
			catch(JsonParseException e)
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
