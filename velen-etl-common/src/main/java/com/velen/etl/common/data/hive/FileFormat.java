package com.velen.etl.common.data.hive;

public interface FileFormat
{
	String SerDe();
	String inputFormat();
	String outputFormat();
}
