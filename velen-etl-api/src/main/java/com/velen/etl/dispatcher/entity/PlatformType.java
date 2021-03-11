package com.velen.etl.dispatcher.entity;


import java.util.HashMap;
import java.util.Map;

/**
 * 统计平台类型
 */
public enum PlatformType
{
	INVALID(0),
	DATAFLOW(1),
	FLINK(2),
	SPARK(3),
	MIX(10),
	;


	private final int key;
	//private final String dsl;

	PlatformType(int key)
	{
		this.key = key;
	}

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
