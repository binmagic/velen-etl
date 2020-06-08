package com.velen.etl.generator.dto;

import com.velen.etl.generator.entity.GenerateEnum;
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
public class EventTableColumnTDO
{
	String name;
	GenerateEnum.DataType type;
	// 额外类型数据
	List<EventTableColumnTDO> extraTypeDataList = new ArrayList<>();
	boolean primary = false;
	String comment;
}
