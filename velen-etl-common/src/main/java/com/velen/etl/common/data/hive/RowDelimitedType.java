package com.velen.etl.common.data.hive;

/**
 * row_format
 *   : DELIMITED [FIELDS TERMINATED BY char [ESCAPED BY char]] [COLLECTION ITEMS TERMINATED BY char]
 *         [MAP KEYS TERMINATED BY char] [LINES TERMINATED BY char]
 *         [NULL DEFINED AS char]   -- (Note: Available in Hive 0.13 and later)
 *   | SERDE serde_name [WITH SERDEPROPERTIES (property_name=property_value, property_name=property_value, ...)]
 */
public enum RowDelimitedType
{
	FIELDS,
	ESCAPED,
	COLLECTION,
	MAP,
	LINES,
	NULL,
}
