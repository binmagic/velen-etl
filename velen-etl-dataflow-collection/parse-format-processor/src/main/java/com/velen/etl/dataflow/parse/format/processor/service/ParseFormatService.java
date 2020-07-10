package com.velen.etl.dataflow.parse.format.processor.service;

import com.velen.etl.dataflow.parse.format.processor.properties.ParseFormatProperties;

import java.util.Map;

public interface ParseFormatService
{
	String test(String message);
	//void setVerify(boolean verify);
	//void setTopic(String topic);
	ParseFormatProperties resetInputParseFormats(Map<String, String> formats);
}
