package com.velen.etl.geneartor.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TableMetadata
{
	String db;
	String table;

	List<PropertyMetadata> properties = new ArrayList<>();

	public TableMetadata(String db, String table)
	{
		this.db = db;
		this.table = table;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		TableMetadata that = (TableMetadata)o;
		return Objects.equals(db, that.db) &&
				Objects.equals(table, that.table);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(db, table);
	}

	public boolean addProperty(PropertyMetadata property)
	{
		if(!properties.contains(property))
		{
			properties.add(property);
			return true;
		}

		return false;
	}
}
