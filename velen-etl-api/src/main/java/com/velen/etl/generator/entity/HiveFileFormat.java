package com.velen.etl.generator.entity;

public interface HiveFileFormat
{
	String SerDe();
	String inputFormat();
	String outputFormat();
}
