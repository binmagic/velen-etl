package com.velen.etl.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Deprecated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableDescTDO
{
	String db;
	String name;
	List<EventTableColumnTDO> columns = new ArrayList<>();
	String comment;
}
