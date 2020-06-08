package com.velen.etl.generator.entity;

import java.util.Map;

/**
 * : DELIMITED [FIELDS TERMINATED BY char [ESCAPED BY char]] [COLLECTION ITEMS TERMINATED BY char]
 *         [MAP KEYS TERMINATED BY char] [LINES TERMINATED BY char]
 *         [NULL DEFINED AS char]   -- (Note: Available in Hive 0.13 and later)
 *   | SERDE serde_name [WITH SERDEPROPERTIES (property_name=property_value, property_name=property_value, ...)]
 */
/*enum RowDelimitedType
{
	FIELDS,
	ESCAPED,
	COLLECTION,
	MAP,
	LINES,
	NULL,
}*/

@Deprecated
class SerDe
{
	String name;
	Map<String, String> properties;
}

@Deprecated
interface CustomFileFormat
{
	String SerDe();
	String inputFormat();
	String outputFormat();
}

enum StoredType implements CustomFileFormat
{
	SEQUENCEFILE("org.apache.hadoop.mapred.SequenceFileInputFormat", "org.apache.hadoop.mapred.SequenceFileOutputFormat"),
	TEXTFILE("org.apache.hadoop.mapred.TextInputFormat", "org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat"),
	RCFILE("org.apache.hadoop.hive.ql.io.RCFileInputFormat", "org.apache.hadoop.hive.ql.io.RCFileOutputFormat"),
	ORC("org.apache.hadoop.hive.ql.io.orc.OrcSerde", "org.apache.hadoop.hive.ql.io.orc.OrcInputFormat", "org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat"),
	PARQUET("org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe", "org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat", "org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat"),
	AVRO("org.apache.hadoop.hive.serde2.avro.AvroSerDe", "org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat", "org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat"),
	;


	StoredType(String inputFormat, String outputFormat)
	{
		this("", inputFormat, outputFormat);
	}

	StoredType(String serde, String inputFormat, String outputFormat)
	{
		this.serde = serde;
		this.inputFormat = inputFormat;
		this.outputFormat = outputFormat;
	}

	private String serde;
	private String inputFormat;
	private String outputFormat;


	@Override
	public String SerDe()
	{
		return serde;
	}

	@Override
	public String inputFormat()
	{
		return inputFormat;
	}

	@Override
	public String outputFormat()
	{
		return outputFormat;
	}
}
