package com.velen.etl.generator.dto;

import com.velen.etl.generator.entity.GenerateEnum;
import com.velen.etl.generator.entity.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Deprecated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTableColumnTDO
{
	String name;
	TableColumn.DataType type;
	// 额外类型数据
	List<EventTableColumnTDO> extraTypeDataList;
	boolean primary;
	String comment;
	int index;
}
