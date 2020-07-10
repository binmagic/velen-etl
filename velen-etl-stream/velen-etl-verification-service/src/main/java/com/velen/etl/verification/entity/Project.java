package com.velen.etl.verification.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "project")
public class Project// extends BaseEntity<Project>
{
	@Id
	private String id;

	private String topic;
	private Boolean verify;
	//private String dataId;
	//private String group;
	private String lastTimeOperator;

	//private List<InputParseFormat> inputParseFormats;

	//private List<InputFieldRule> inputFieldRules;


	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Project project = (Project)o;
		return Objects.equals(id, project.id);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}
