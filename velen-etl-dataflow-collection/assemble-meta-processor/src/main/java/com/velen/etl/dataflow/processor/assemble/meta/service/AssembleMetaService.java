package com.velen.etl.dataflow.processor.assemble.meta.service;

import com.velen.etl.dataflow.processor.assemble.meta.properties.AssembleMetaProperties;

public interface AssembleMetaService
{
	String test(String message);
	AssembleMetaProperties setAppId(String appId);
	AssembleMetaProperties setBusiness(String business);
}
