package com.velen.etl.geneartor.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Deprecated
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableOrder implements Serializable
{
	public enum TableType
	{
		EVENT,
		PROFILE,
	}

	private String creator;
	private Date createTime;

	private String project;
	private TableType tableType;



	//private List<TableColumn>
}
