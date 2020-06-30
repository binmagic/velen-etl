package com.velen.etl.verification.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "input_parse_format")
public class InputParseFormat// extends BaseEntity<InputParseFormat>
{

	//private Integer index;
	//@Id
	//private String id;
	@Id
	private Integer index;
	@Indexed
	private String projectId;
	private VerifyEnum.InputParseType inputParseType;
	//@Field(value = "regex")
	private String regex;

	private String lastTimeOperator;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		InputParseFormat that = (InputParseFormat)o;
		return Objects.equals(index, that.index);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(index);
	}

	public Map<String, Object> toMap()
	{
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("type", inputParseType);
		map.put("formula", regex);
		return map;
	}
}
