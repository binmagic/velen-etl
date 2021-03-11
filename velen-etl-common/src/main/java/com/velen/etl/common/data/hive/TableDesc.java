package com.velen.etl.common.data.hive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class TableDesc
{
	String db;
	String name;
	List<TableColumn> columns = new ArrayList<>();
	String comment;
}
