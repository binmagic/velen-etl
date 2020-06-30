package com.velen.etl.verification.entity;

import java.util.HashMap;
import java.util.Map;

public interface DeployEnum
{
	int getKey();

	/**
	 * 流处理部署类型
	 */
	enum StreamDeployType implements DeployEnum
	{
		TEST(0),
		;


		private final int key;
		//private final String dsl;

		StreamDeployType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, StreamDeployType> map = new HashMap<>();
		static
		{
			for(StreamDeployType o : values())
			{
				map.put(o.getKey(), o);
			}
		}

		public static StreamDeployType parse(int i)
		{
			return map.getOrDefault(i, TEST);
		}
	}

	/**
	 * 任务处理部署类型
	 */
	enum TaskDeployType implements DeployEnum
	{
		TEST(0),
		;


		private final int key;
		//private final String dsl;

		TaskDeployType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, TaskDeployType> map = new HashMap<>();
		static
		{
			for(TaskDeployType o : values())
			{
				map.put(o.getKey(), o);
			}
		}

		public static TaskDeployType parse(int i)
		{
			return map.getOrDefault(i, TEST);
		}
	}

	/**
	 * 应用(分析)处理部署类型
	 */
	enum AppDeployType implements DeployEnum
	{
		TEST(0),
		;


		private final int key;
		//private final String dsl;

		AppDeployType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, AppDeployType> map = new HashMap<>();
		static
		{
			for(AppDeployType o : values())
			{
				map.put(o.getKey(), o);
			}
		}

		public static AppDeployType parse(int i)
		{
			return map.getOrDefault(i, TEST);
		}
	}
}
