package com.velen.etl.dispatcher.service;

import org.springframework.cloud.dataflow.rest.client.StreamOperations;

public interface DataFlowStreamService extends StreamOperations
{
	/**
	 * 流是否被部署
	 */
	boolean isDeployed(String name);

	/**
	 * 流定义是否存在
	 */
	boolean exist(String name);

	/**
	 * 获得流定义
	 */
	String getDefinition(String name);

}
