package com.velen.etl.generator.entity;

@Deprecated
public class TableColumn
{
	/**
	 *   | TINYINT
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
		TINYINT,
		SMALLINT,
		INT,
		BIGINT,
		BOOLEAN,
		FLOAT,
		DOUBLE,
		DOUBLE_PRECISION,
		STRING,
		BINARY,
		TIMESTAMP,
		DECIMAL,
		DECIMALEX,
		DATE,
		VARCHAR,
		CHAR,
		ARRAY,
		MAP,
		STRUCT,
		UNION,
	}

	String name;
	DataType type;
	// 额外数据?
	boolean primary = false;
	String comment;
}
