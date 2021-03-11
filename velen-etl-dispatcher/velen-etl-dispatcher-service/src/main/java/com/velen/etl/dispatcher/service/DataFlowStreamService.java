package com.velen.etl.dispatcher.service;

import org.springframework.cloud.dataflow.rest.client.StreamOperations;

import java.util.HashMap;
import java.util.Map;

public interface DataFlowStreamService extends StreamOperations
{
	enum Status
	{
		UNDEPLOY(1, ""),
		RUNNING(1, ""),
		ERROR(1, ""),
		FINISH(1, ""),

		;

		final int retCode;
		final String symbol;
		Status(int c, String s)
		{
			this.retCode = c;
			this.symbol = s;
		}

		public int getRetCode()
		{
			return retCode;
		}

		public String getSymbol()
		{
			return symbol;
		}

		static Map<String, Status> MAPS = new HashMap<>();

		static
		{
			for(Status s : Status.values())
			{
				MAPS.put(s.getSymbol(), s);
			}
		}

		public static Status parse(String key)
		{
			return MAPS.getOrDefault(key, UNDEPLOY);
		}

	}

	/**
	 * 流是否被部署
	 */
	boolean isDeployed(String name);

	/**
	 * 获取状态
	 */
	int status(String name);

	/**
	 * 流定义是否存在
	 */
	boolean exist(String name);

	/**
	 * 获得流定义
	 */
	String getDefinition(String name);

}
