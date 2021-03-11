package com.velen.etl.common.data.hive;

@Deprecated
public interface FileFormat
{
	String SerDe();
	String inputFormat();
	String outputFormat();
}
