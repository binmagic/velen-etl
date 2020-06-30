package com.velen.etl.verification.dto;

import com.velen.etl.verification.entity.VerifyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 输入解析格式
 */
@Deprecated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParseFormatTDO
{
	private VerifyEnum.InputParseType inputParseType;
	private String formula;
}
