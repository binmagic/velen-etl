package com.velen.etl.common.entity;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public interface VerifyEnum
{
	int getKey();

	/**
	 * 接收字段的Key验证规则
	 */
	enum FieldRuleType implements VerifyEnum
	{
		NONE(0),
		REGEX(1),
		POSITION(2),
		FIELD(3),
		;


		private final int key;

		FieldRuleType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, FieldRuleType> map = new HashMap<>();
		static
		{
			for(FieldRuleType o : values())
			{
				map.put(o.getKey(), o);
			}
		}

		public static FieldRuleType parse(int i)
		{
			return map.getOrDefault(i, NONE);
		}
	}

	/**
	 * 数据解析类型
	 */
	enum InputParseType implements VerifyEnum
	{
		NONE(0),
		JSON(1),
		REGEX(2),
		SPLIT(3),
		;

		private final int key;

		InputParseType(int key)
		{
			this.key = key;
		}

		@Override
		public int getKey()
		{
			return this.key;
		}

		private static final Map<Integer, InputParseType> map = new HashMap<>();
		private static final Map<String, InputParseType> mapByName = new HashMap<>();
		static
		{
			for(InputParseType o : values())
			{
				map.put(o.getKey(), o);
				mapByName.put(o.name(), o);
			}
		}

		public static InputParseType parse(int i)
		{
			return map.getOrDefault(i, NONE);
		}

		public static InputParseType parse(String str)
		{
			return map.getOrDefault(str.toUpperCase(), NONE);
		}
	}



}
