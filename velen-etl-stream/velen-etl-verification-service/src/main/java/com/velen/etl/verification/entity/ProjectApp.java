package com.velen.etl.verification.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "project_app")
public class ProjectApp// extends BaseEntity<Project>
{
	@Id
	private String id;

	//private String topic;
	private boolean verify;
	//private String dataId;
	//private String group;
	private String lastTimeOperator;

	public ProjectApp(String id, boolean verify, String operator)
	{
		this.id = id;
		this.verify = verify;
		this.lastTimeOperator = operator;
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class InputParseFormat
	{
		private int index;
		private InputParseType inputParseType;
		private String regex;
	}

	@Getter
	@Setter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class InputFieldRule
	{
		private String fieldName;
		private FieldRuleType keyRuleType;
		private String keyRule;
		private String valueRule;
	}

	private Map<Integer, InputParseFormat> inputParseFormats = new HashMap<>();

	private Map<String, InputFieldRule> inputFieldRules = new HashMap<>();

	public Map<Integer, InputParseFormat> clearInputParseFormat()
	{
		Map<Integer, InputParseFormat> tmp = this.inputParseFormats;
		this.inputParseFormats.clear();
		return tmp;
	}

	public InputParseFormat putInputParseFormat(int index, InputParseType inputParseType, String regex)
	{
		return this.inputParseFormats.put(index, new InputParseFormat(index, inputParseType, regex));
	}

	public Map<String, InputFieldRule> clearInputFieldRule()
	{
		Map<String, InputFieldRule> tmp = this.inputFieldRules;
		this.inputFieldRules.clear();
		return tmp;
	}

	public InputFieldRule putInputFieldRule(String fieldName, FieldRuleType keyRuleType, String keyRule, String valueRule)
	{
		return this.inputFieldRules.put(fieldName, new InputFieldRule(fieldName, keyRuleType, keyRule, valueRule));
	}


	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		ProjectApp project = (ProjectApp)o;
		return Objects.equals(id, project.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
