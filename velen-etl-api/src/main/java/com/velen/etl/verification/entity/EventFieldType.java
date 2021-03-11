package com.velen.etl.verification.entity;

import lombok.Getter;

@Getter
public enum EventFieldType
{
	DEVICE_ID("device_id", "接收用户「设备ID」", "test comment"),
	DISTINCT_ID("distinct_id", "接收用户「用户ID」", "test comment"),
	TIME("time", "接收事件「发生时间」", "test comment"),
	EVENT("event", "接收事件「名称」", "test comment"),
	PROJECT_ID("project_id", "接收事件「项目ID」", "test comment"),

	;

	String value;
	String name;
	String comment;

	EventFieldType(String value, String name, String comment)
	{
		this.value = value;
		this.name = name;
		this.comment = comment;
	}

	public String getValue()
	{
		return value;
	}
}
