package com.velen.etl.generator.tdo;

import com.velen.etl.generator.entity.GenerateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTableColumnTDO
{
	String name;
	GenerateEnum.DataType type;
	// 额外类型数据
	List<EventTableColumnTDO> extraTypeDataList;
	boolean primary;
	String comment;
	int index;
}
