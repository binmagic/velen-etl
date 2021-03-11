package com.velen.etl.common.data.hive;

/**
 * file_format:
 *   : SEQUENCEFILE
 *   | TEXTFILE    -- (Default, depending on hive.default.fileformat configuration)
 *   | RCFILE      -- (Note: Available in Hive 0.6.0 and later)
 *   | ORC         -- (Note: Available in Hive 0.11.0 and later)
 *   | PARQUET     -- (Note: Available in Hive 0.13.0 and later)
 *   | AVRO        -- (Note: Available in Hive 0.14.0 and later)
 *   | JSONFILE    -- (Note: Available in Hive 4.0.0 and later)
 *   | INPUTFORMAT input_format_classname OUTPUTFORMAT output_format_classname
 */
@Deprecated
public enum StoredAsType implements FileFormat
{
	SEQUENCEFILE("org.apache.hadoop.mapred.SequenceFileInputFormat", "org.apache.hadoop.mapred.SequenceFileOutputFormat"),
	TEXTFILE("org.apache.hadoop.mapred.TextInputFormat", "org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat"),
	RCFILE("org.apache.hadoop.hive.ql.io.RCFileInputFormat", "org.apache.hadoop.hive.ql.io.RCFileOutputFormat"),
	ORC("org.apache.hadoop.hive.ql.io.orc.OrcSerde", "org.apache.hadoop.hive.ql.io.orc.OrcInputFormat", "org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat"),
	PARQUET("org.apache.hadoop.hive.ql.io.parquet.serde.ParquetHiveSerDe", "org.apache.hadoop.hive.ql.io.parquet.MapredParquetInputFormat", "org.apache.hadoop.hive.ql.io.parquet.MapredParquetOutputFormat"),
	AVRO("org.apache.hadoop.hive.serde2.avro.AvroSerDe", "org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat", "org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat"),
	;


	StoredAsType(String inputFormat, String outputFormat)
	{
		this("", inputFormat, outputFormat);
	}

	StoredAsType(String serde, String inputFormat, String outputFormat)
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
