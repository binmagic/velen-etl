package com.velen.etl.common.data.hive;

import java.util.HashMap;
import java.util.Map;

/**
 * data_type
 *   : TINYINT
 *   | SMALLINT
 *   | INT
 *   | BIGINT
 *   | BOOLEAN
 *   | FLOAT
 *   | DOUBLE
 *   | DOUBLE PRECISION -- (Note: Available in Hive 2.2.0 and later)
 *   | STRING
 *   | BINARY      -- (Note: Available in Hive 0.8.0 and later)
 *   | TIMESTAMP   -- (Note: Available in Hive 0.8.0 and later)
 *   | DECIMAL     -- (Note: Available in Hive 0.11.0 and later)
 *   | DECIMAL(precision, scale)  -- (Note: Available in Hive 0.13.0 and later)
 *   | DATE        -- (Note: Available in Hive 0.12.0 and later)
 *   | VARCHAR     -- (Note: Available in Hive 0.12.0 and later)
 *   | CHAR        -- (Note: Available in Hive 0.13.0 and later)
 *   | array_type
 *   | map_type
 *   | struct_type
 *   | union_type  -- (Note: Available in Hive 0.7.0 and later)
 */
public enum DataType
{
	NONE(0, ""),
	// ---------------------------------------------
	// primitive_type
	TINYINT(1, 0),
	SMALLINT(2, 0),
	INT(3, 0),
	BIGINT(4, 0),
	BOOLEAN(5, false),
	FLOAT(6, 0.0f),
	DOUBLE(7, 0.0),
	DOUBLE_PRECISION(8, 0.0),
	STRING(9, ""),
	BINARY(10, ""),
	TIMESTAMP(10, null),
	DECIMAL(11, 0),
	DECIMALEX(12, 0),
	DATE(13, null),
	VARCHAR(14, ""),
	CHAR(15, ""),
	// ---------------------------------------------
	ARRAY(16, "[]"),
	MAP(17, "{}"),
	STRUCT(18, ""),
	UNION(19, ""),
	;

	private final int key;
	private final Object defaultValue;

	DataType(int key, Object defaultValue)
	{
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public int getKey()
	{
		return key;
	}

	public Object getDefaultValue()
	{
		return defaultValue;
	}

	private static final Map<Integer, DataType> map = new HashMap<>();
	private static final Map<String, DataType> mapByName = new HashMap<>();
	static
	{
		for(DataType o : values())
		{
			map.put(o.getKey(), o);
			mapByName.put(o.name().toUpperCase(), o);
		}
	}

	public static DataType parse(int i)
	{
		return map.getOrDefault(i, NONE);
	}

	public static DataType parse(String str)
	{
		return map.getOrDefault(str.toUpperCase(), NONE);
	}
}
