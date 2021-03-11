package com.velen.etl.generator.entity;

import org.apache.hadoop.hive.serde.serdeConstants;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DataType
{
	VOID(Object.class, serdeConstants.VOID_TYPE_NAME, null),
	// ---------------------------------------------
	// primitive_type
	TINYINT(char.class, serdeConstants.TINYINT_TYPE_NAME, 0),
	SMALLINT(Short.class, serdeConstants.SMALLINT_TYPE_NAME, 0),
	INT(Integer.class, serdeConstants.INT_TYPE_NAME, 0),
	BIGINT(Long.class, serdeConstants.BIGINT_TYPE_NAME, 0),
	BOOLEAN(Boolean.class, serdeConstants.BOOLEAN_TYPE_NAME, false),
	FLOAT(Float.class, serdeConstants.FLOAT_TYPE_NAME, 0.0f),
	DOUBLE(Double.class, serdeConstants.DOUBLE_TYPE_NAME, 0.0),
	//DOUBLE_PRECISION(8, 0.0),
	STRING(String.class, serdeConstants.STRING_TYPE_NAME, ""),
	BINARY(Byte[].class, serdeConstants.BINARY_TYPE_NAME, ""),
	TIMESTAMP(Timestamp.class, serdeConstants.TIMESTAMP_FORMATS, null),
	//DECIMAL(11, 0),
	//DECIMALEX(12, 0),
	DATE(Date.class, serdeConstants.DATE_TYPE_NAME, "1979-1-1 0:0:0"),
	VARCHAR(char[].class, serdeConstants.VARCHAR_TYPE_NAME, ""),
	CHAR(Byte.class, serdeConstants.CHAR_TYPE_NAME, ""),
	// ---------------------------------------------
	ARRAY(List.class, serdeConstants.LIST_TYPE_NAME, "[]"),
	MAP(Map.class, serdeConstants.MAP_TYPE_NAME, "{}"),
	STRUCT(Class.class, serdeConstants.STRUCT_TYPE_NAME, ""),
	//UNION(19, ""),
			;

	private final Type key;
	private final String value;
	private final Object defaultValue;

	DataType(Type key, String value, Object defaultValue)
	{
		this.key = key;
		this.value = value;
		this.defaultValue = defaultValue;
	}

	public Type getKey()
	{
		return key;
	}

	public String getValue()
	{
		return value;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	private static final Map<Type, DataType> map = new HashMap<>();
	static
	{
		for(DataType o : values())
		{
			map.put(o.getKey(), o);
		}
	}

	public static DataType parse(Type t)
	{
		return map.getOrDefault(t, VOID);
	}
}
