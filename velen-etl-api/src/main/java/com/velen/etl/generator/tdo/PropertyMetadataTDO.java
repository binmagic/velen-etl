package com.velen.etl.generator.tdo;

import lombok.*;

import java.util.Objects;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyMetadataTDO
{
	private String name;
	private String type; // 匹配 hive 类型
	private String comment;
	private int index; // 列的索引, 这样这个有点小问题, 要是按表，需要做表解析

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		PropertyMetadataTDO that = (PropertyMetadataTDO)o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
