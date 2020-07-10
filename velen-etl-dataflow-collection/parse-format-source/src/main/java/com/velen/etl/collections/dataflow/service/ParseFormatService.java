package com.velen.etl.collections.dataflow.service;

import com.velen.etl.configuration.InputParseFormatConfiguration;

import java.util.List;

public interface ParseFormatService
{
	String test(String message);
	void setVerify(boolean verify);
	//void setTopic(String topic);
	void setInputParseFormats(List<InputParseFormatConfiguration.ParseFormatConfiguration> formats);
}
