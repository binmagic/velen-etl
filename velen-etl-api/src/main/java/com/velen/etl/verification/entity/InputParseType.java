package com.velen.etl.verification.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据解析类型
 */
public enum InputParseType
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
		return mapByName.getOrDefault(str.toUpperCase(), NONE);
	}

	/**
	 * 验证内容是否与类型一致
	 */
	public boolean validate(String value)
	{
		return true;
	}
}
