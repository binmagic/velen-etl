package com.velen.etl.generator.entity;

@Deprecated
public interface GenerateEnum
{
	/**
	 * row_format
	 *   : DELIMITED [FIELDS TERMINATED BY char [ESCAPED BY char]] [COLLECTION ITEMS TERMINATED BY char]
	 *         [MAP KEYS TERMINATED BY char] [LINES TERMINATED BY char]
	 *         [NULL DEFINED AS char]   -- (Note: Available in Hive 0.13 and later)
	 *   | SERDE serde_name [WITH SERDEPROPERTIES (property_name=property_value, property_name=property_value, ...)]
	 */
	enum RowDelimitedType implements GenerateEnum
	{
		FIELDS,
		ESCAPED,
		COLLECTION,
		MAP,
		LINES,
		NULL,
	}

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
	enum DataType implements GenerateEnum
	{
		// ---------------------------------------------
		// primitive_type
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
		// ---------------------------------------------
		ARRAY,
		MAP,
		STRUCT,
		UNION,
	}

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
	enum StoredAsType implements GenerateEnum, HiveFileFormat
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
}
