package com.velen.etl.dataflow.processor.input.parse.service;


import com.velen.etl.dataflow.processor.input.parse.properties.InputParseProperties;

import java.util.Map;

public interface InputParseService
{
	String test(String message);
	//void setVerify(boolean verify);
	//void setTopic(String topic);
	InputParseProperties setAppId(String appId);
	InputParseProperties setBusiness(String business);
	InputParseProperties resetInputParseFormats(Map<String, String> formats);
}
