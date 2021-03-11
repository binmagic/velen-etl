package com.velen.etl.generator.entity;

public enum TableType
{
	EVENT(0, "event"),
	PROFILE(1, "profile"),
	;

	int id;
	String value;

	public int id()
	{
		return this.id;
	}

	public String value()
	{
		return this.value;
	}

	TableType(int id, String value)
	{
		this.id = id;
		this.value = value;
	}

	public static TableType valueOf(int id)
	{
		if(id == PROFILE.id)
			return PROFILE;

		return EVENT;
	}
}
