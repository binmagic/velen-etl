package com.velen.etl.verification.entity;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public interface DeployEnum
{
	int getKey();

	/**
	 * 统计平台类型
	 */
	@Deprecated
	enum PlatformType implements DeployEnum
	{
		INVALID(0),
		DATAFLOW(1),
		FLINK(2),
		MIX(10),
		;


		private final int key;
		//private final String dsl;

		PlatformType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, PlatformType> map = new HashMap<>();
		private static final Map<String, PlatformType> mapByName = new HashMap<>();
		static
		{
			for(PlatformType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static PlatformType parse(int i)
		{
			return map.getOrDefault(i, INVALID);
		}

		public static PlatformType parse(String str)
		{
			return mapByName.getOrDefault(str.toUpperCase(), INVALID);
		}
	}

	/**
	 * 处理类型
	 */
	@Deprecated
	enum ProcessType implements DeployEnum
	{
		INVALID(0),
		STREAM(1),
		TASK(2),
		APP(3),
		;


		private final int key;
		//private final String dsl;

		ProcessType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, ProcessType> map = new HashMap<>();
		private static final Map<String, ProcessType> mapByName = new HashMap<>();
		static
		{
			for(ProcessType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static ProcessType parse(int i)
		{
			return map.getOrDefault(i, INVALID);
		}

		public static ProcessType parse(String str)
		{
			return mapByName.getOrDefault(str.toUpperCase(), INVALID);
		}
	}

	/**
	 * 流处理部署类型
	 * warning: 如果定义了这个，将会导致增加新内容需要更新代码
	 */
	@Deprecated
	enum StreamDeployType implements DeployEnum
	{
		INVALID(0),
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
		private static final Map<String, StreamDeployType> mapByName = new HashMap<>();
		static
		{
			for(StreamDeployType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static StreamDeployType parse(int i)
		{
			return map.getOrDefault(i, INVALID);
		}

		public static StreamDeployType parse(String str)
		{
			return mapByName.getOrDefault(str.toUpperCase(), INVALID);
		}
	}

	/**
	 * 任务处理部署类型
	 * warning: 如果定义了这个，将会导致增加新内容需要更新代码
	 */
	@Deprecated
	enum TaskDeployType implements DeployEnum
	{
		INVALID(0),
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
		private static final Map<String, TaskDeployType> mapByName = new HashMap<>();
		static
		{
			for(TaskDeployType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static TaskDeployType parse(int i)
		{
			return map.getOrDefault(i, INVALID);
		}

		public static TaskDeployType parse(String str)
		{
			return mapByName.getOrDefault(str.toUpperCase(), INVALID);
		}
	}

	/**
	 * 应用(分析)处理部署类型
	 * warning: 如果定义了这个，将会导致增加新内容需要更新代码
	 */
	@Deprecated
	enum AppDeployType implements DeployEnum
	{
		INVALID(0),
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
		private static final Map<String, AppDeployType> mapByName = new HashMap<>();
		static
		{
			for(AppDeployType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static AppDeployType parse(int i)
		{
			return map.getOrDefault(i, INVALID);
		}

		public static AppDeployType parse(String str)
		{
			return mapByName.getOrDefault(str.toUpperCase(), INVALID);
		}
	}
}
