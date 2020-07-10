package com.velen.etl.dataflow.processor.input.verify.service;

import com.velen.etl.dataflow.processor.input.verify.properties.InputVerifyProperties;

import java.util.List;
import java.util.Map;

public interface InputVerifyService
{
	String test(String message);

	InputVerifyProperties setAppId(String appId);
	InputVerifyProperties setBusiness(String business);
	InputVerifyProperties setVerify(boolean verify);
	//InputVerifyProperties setFields(List<InputVerifyProperties.Field> fields);
	//InputVerifyProperties setProperties(String name, List<InputVerifyProperties.Property> properties);
	InputVerifyProperties setProperties(Map<String, InputVerifyProperties.Property> properties);
}
