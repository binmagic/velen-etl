package com.velen.etl.dataflow.processor.field.verify.service;


import com.velen.etl.dataflow.processor.field.verify.properties.FieldVerifyProperties;

import java.util.List;

public interface FieldVerifyService
{
	String test(String message);
	FieldVerifyProperties setAppId(String appId);
	FieldVerifyProperties setBusiness(String business);
	FieldVerifyProperties setFields(List<FieldVerifyProperties.Field> fields);
}
