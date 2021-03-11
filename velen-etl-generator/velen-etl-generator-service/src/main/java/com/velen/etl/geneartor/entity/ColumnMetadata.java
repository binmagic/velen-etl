package com.velen.etl.geneartor.entity;

import lombok.*;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ColumnMetadata
{
	private String name;
	private String type;
	private String comment;
	private int index;

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		ColumnMetadata that = (ColumnMetadata)o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(name);
	}

	public FieldSchema toFieldSchema()
	{
		return new FieldSchema(this.name, this.getType(), "");
	}
}
