package com.velen.etl.common.data.hive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableColumn
{
	String name;
	DataType type;
	// 额外类型数据
	List<TableColumn> extraTypeDataList;
	boolean primary;
	String comment;
	int index;
}
