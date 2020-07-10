package com.velen.etl.geneartor.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PropertyMetadata
{
	private String name;
	private String type;
	private int index;
	private boolean require;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		PropertyMetadata that = (PropertyMetadata)o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}
}
