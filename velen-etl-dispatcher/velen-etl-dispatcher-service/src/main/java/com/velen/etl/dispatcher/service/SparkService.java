package com.velen.etl.dispatcher.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SparkService
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

	void create(String appName, String appResource, List<String> appParameters, Map<String,String> environmentVariables, Map<String, String> platformProperties);

	/**
	 * 获取状态
	 */
	int status(String submissionId);

	/**
	 * 关闭程序
	 */
	void kill(String submissionId);
}
