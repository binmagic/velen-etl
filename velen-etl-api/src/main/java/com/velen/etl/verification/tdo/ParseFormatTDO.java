package com.velen.etl.verification.tdo;

import com.velen.etl.verification.entity.InputParseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 输入解析格式
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParseFormatTDO
{
	private Integer index;
	private InputParseType inputParseType;
	private String formula;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		ParseFormatTDO that = (ParseFormatTDO)o;
		return Objects.equals(index, that.index);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(index);
	}
}
