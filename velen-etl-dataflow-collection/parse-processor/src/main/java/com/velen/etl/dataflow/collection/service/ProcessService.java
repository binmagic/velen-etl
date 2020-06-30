package com.velen.etl.dataflow.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
//@Component
//@EnableBinding(Processor.class)
public class ProcessService
{
	// 解析的配置
	//@Autowired
	//private InputParseFormatConfiguration inputParseFormatConfiguration;


	/**
	 * 消息输出
	 */
	//@StreamListener(Processor.INPUT)
	//@SendTo(Processor.OUTPUT)
	public String transform(String value)
	{
		return value + "-REGEX- " + getRegex();
	}

	/**
	 * 通道消息转换
	 */
	//@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.INPUT)
	public Object transform(Object message)
	{
		//return new SimpleDateFormat(("yyyy-mm-dd HH:mm:ss")).format(message);
		return message;
	}



	private String regex;

	public String getRegex()
	{
		return regex;
	}

	public void setRegex(String regex)
	{
		this.regex = regex;
	}

	public String parse(String str)
	{
		return str;
	}



}
