package com.velen.etl.geneartor.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AppMetadata
{
	public AppMetadata(String db)
	{
		this.db = db;
	}

	String db;
	List<PropertyMetadata> properties = new ArrayList<>();
	Map<String, TableMetadata> tables = new HashMap<>();

	public boolean addProperty(String table, PropertyMetadata property)
	{
		TableMetadata tb = tables.get(table);
		if(tb == null)
			return false;

		tb.addProperty(property);
		if(!properties.contains(property))
		{
			properties.add(property);
			return true;
		}

		return false;
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

	public TableMetadata addTable(TableMetadata table)
	{
		return this.tables.put(table.getTable(), table);
	}

	public TableMetadata dropTable(String table)
	{
		return this.tables.remove(table);
	}
}
