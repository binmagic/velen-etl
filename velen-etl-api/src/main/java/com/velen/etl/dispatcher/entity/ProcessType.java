package com.velen.etl.dispatcher.entity;


import java.util.HashMap;
import java.util.Map;

/**
 * 处理类型
 */
public enum ProcessType
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
