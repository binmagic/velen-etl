package com.velen.etl.generator.entity;

@Deprecated
public interface HiveFileFormat
{
	String SerDe();
	String inputFormat();
	String outputFormat();
}
