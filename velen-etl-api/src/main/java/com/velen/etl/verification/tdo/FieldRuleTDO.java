package com.velen.etl.verification.tdo;

import com.velen.etl.verification.entity.VerifyEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段入库规则
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldRuleTDO
{
	private String fieldName;
	private VerifyEnum.FieldRuleType keyRuleType;
	private String keyRule;
	private String valueRule;
}
