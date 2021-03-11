package com.velen.etl.verification.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 接收字段的Key验证规则
 */
public enum FieldRuleType
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
