package com.velen.etl.verification.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Document(collation = "input_field_rule")
public class InputFieldRule// extends BaseEntity<InputFieldRule>
{
	@Id
	private String fieldName;
	@Indexed
	private String projectId;
	private VerifyEnum.FieldRuleType keyRuleType;
	private String keyRule;
	private String valueRule;

	private String lastTimeOperator;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		InputFieldRule that = (InputFieldRule)o;
		return Objects.equals(fieldName, that.fieldName);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(fieldName);
	}

	public Map<String, Objects> toMap()
	{
		Map<String, Objects> map = new LinkedHashMap<>();

		return map;
	}
}
