package com.velen.etl.verification.tdo;

import com.velen.etl.verification.entity.FieldRuleType;
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
	private String fieldName; // context: device_id, distinct_id, time, event, project_id
	private FieldRuleType keyRuleType;
	private String keyRule;
	private String valueRule;
}
