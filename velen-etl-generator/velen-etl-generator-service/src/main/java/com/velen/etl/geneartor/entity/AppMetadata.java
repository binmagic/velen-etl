package com.velen.etl.geneartor.entity;

import com.velen.etl.generator.entity.TableType;
import lombok.*;
import org.springframework.data.annotation.Id;
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
@Document(collection = "app")
public class AppMetadata
{

	// partitions
	//static Map<String, String> partitions = new HashMap<>();

	public AppMetadata(String db)
	{
		this.db = db;
	}

	@Id
	String db;

	//List<PropertyMetadata> properties = new ArrayList<>();
	Map<String, TableMetadata> tables = new HashMap<>();
	Map<String, TableMetadata> hiveTables = new HashMap<>();

	public TableMetadata getTable(String key)
	{
		return this.tables.getOrDefault(key, null);
	}

	public TableMetadata getHiveTable(String key)
	{
		return this.hiveTables.getOrDefault(key, null);
	}

	/*public TableMetadata getTable(int type)
	{
		return getTable(TableType.valueOf(type));
	}

	public TableMetadata getTable(TableType type)
	{
		TableMetadata table = new TableMetadata(db, type.value());

		for(Map.Entry<String, TableMetadata> entry : tables.entrySet())
		{
			TableMetadata meta = entry.getValue();
			if(meta.getType() == type.id())
			{
				table.addProperties(meta.getProperties());
			}
		}

		table.setPartitions(partitions);

		return table;
	}*/

	/*public boolean addProperty(String table, PropertyMetadata property)
	{
		TableMetadata tb = tables.get(table);
		if(tb == null)
			return false;

		tb.addProperty(property);
		*//*if(!properties.contains(property))
		{
			properties.add(property);
			return true;
		}*//*

		return false;
	}*/

	public TableMetadata putTable(TableMetadata table)
	{
		return this.tables.put(table.getTable(), table);
	}

	public TableMetadata dropTable(String table)
	{
		return this.tables.remove(table);
	}

	public TableMetadata putHiveTable(TableMetadata table)
	{
		return this.hiveTables.put(table.getTable(), table);
	}

	public TableMetadata dropHiveTable(String table)
	{
		return this.hiveTables.remove(table);
	}
}
