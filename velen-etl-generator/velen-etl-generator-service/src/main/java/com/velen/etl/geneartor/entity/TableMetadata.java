package com.velen.etl.geneartor.entity;

import javafx.util.Pair;
import lombok.*;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.SerDeInfo;
import org.apache.hadoop.hive.metastore.api.StorageDescriptor;
import org.apache.hadoop.hive.metastore.api.Table;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TableMetadata
{
//	String catName;
	String db;
	String table;

	boolean external;

	List<ColumnMetadata> columns = new ArrayList<>();

	// TODO: 创建表结构使用, 由 AppMetadata 决定
	List<Pair<String, String>> partitions = new ArrayList<>();

	public TableMetadata(String db, String table)
	{
		this.db = db;
		this.table = table;
	}

	public TableMetadata(String db, String table/*, int type*/, List<ColumnMetadata> columns)
	{
		this.db = db;
		this.table = table;
		this.external = false;
		this.columns = columns;
	}

	public TableMetadata(String db, String table, List<ColumnMetadata> columns, List<Pair<String, String>> partitions)
	{
		this.db = db;
		this.table = table;
		this.external = false;
		this.columns = columns;
		this.partitions = partitions;
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

	public boolean addProperty(ColumnMetadata property)
	{
		if(!this.columns.contains(property))
		{
			this.columns.add(property);
			return true;
		}

		return false;
	}

	public void addProperties(Collection<ColumnMetadata> properties)
	{
		for(ColumnMetadata property : properties)
		{
			if(!this.columns.contains(property))
			{
				this.columns.add(property);
			}
		}
	}

	/*public void addPartitions(Map<String, String> partitions)
	{
		this.partitions.putAll(partitions);
	}*/

	// TODO: 测试用，可以用 hive-serd 库的常量
	static SerDeInfo SER_DE_INFO = new SerDeInfo("null", "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe",
			new HashMap<String, String>(){{
				put("mapkey.delim=", ":");
				put("serialization.format=", ",");
				put("field.delim=", ","); }});

	public Table toTable()
	{
		List<FieldSchema> cols = new ArrayList<>();
		for(ColumnMetadata meta : columns)
		{
			cols.add(meta.toFieldSchema());
		}

		StorageDescriptor sd = new StorageDescriptor(cols, "", "org.apache.hadoop.mapred.TextInputFormat", "org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat",
				false, -1, SER_DE_INFO, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_MAP);

		// EXTERNAL_TABLE MANAGED_TABLE
		return new Table(table, db, "anonymous", 1459382234, 0, 0, sd, Collections.EMPTY_LIST, Collections.EMPTY_MAP, "null", "null", "EXTERNAL_TABLE");
	}
}
