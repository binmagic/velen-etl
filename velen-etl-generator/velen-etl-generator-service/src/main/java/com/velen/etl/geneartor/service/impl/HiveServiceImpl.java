package com.velen.etl.geneartor.service.impl;

import com.velen.etl.common.data.hive.Table;
import com.velen.etl.common.data.hive.TableColumn;
import com.velen.etl.geneartor.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Format;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

// 先写成临时的
@Service
public class HiveServiceImpl implements HiveService
{
	@Autowired
	JdbcTemplate hiveJdbcTemplate;

	@Override
	public boolean createDatabase(String database)
	{
		String pattern = "CREATE DATABASE {0};";
		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database));
		return true;
	}

	@Override
	public void dropDatabase(String database)
	{
		String pattern = "DROP DATABASE {0};";
		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database));
	}

	@Deprecated
	@Override
	public boolean createTable(Table table)
	{
		// 行写成临时的
		StringBuilder sb = new StringBuilder();

		try
		{
			sb.append("create table ");
			sb.append(table.getName());
			sb.append(" (\n");
			sb.append("  distinct_id int \n");
			sb.append(", time timestamp \n");
			sb.append(", event string \n");
			sb.append(", project string \n");
			for(TableColumn column : table.getColumns())
			{
				sb.append(", ");
				sb.append(column.getName());
				sb.append(column.getType().name());
				if(!StringUtils.isEmpty(column.getComment()))
				{
					sb.append(" comment '");
					sb.append(column.getComment());
					sb.append(" '");
				}
				sb.append(" \n");
			}
			sb.append(" )\n");
			sb.append(" row format delimited\n");
			sb.append(" fields terminated by ','\n");
			sb.append(" collection items terminated by '-'\n");
			sb.append(" map keys terminated by ':'\n");
			sb.append(";");

			hiveJdbcTemplate.execute(sb.toString());
		}
		catch(Exception e)
		{
			return false;
		}

		return true;
	}

	@Override
	public boolean createTable(String database, String table)
	{
		String pattern = "CREATE EXTERNAL TABLE {0}.{1}\n" +
				"(\n" +
				"    distinct_id STRING COMMENT 'user of identity' ,\n" +
				"    create_time TIMESTAMP COMMENT 'create time' ,\n" +
				"    event STRING COMMENT 'name of event' ,\n" +
				"    project STRING COMMENT 'name of project'\n" +
				")\n" +
				"COMMENT 'event table'\n" +
				"PARTITIONED BY(p_event STRING, p_time TIMESTAMP)\n" +
				"ROW FORMAT DELIMITED\n" +
				"    FIELDS TERMINATED BY '\\001'\n" +
				"STORED AS SEQUENCEFILE;";

		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database, table));
		return true;
	}

	@Override
	public void dropTable(String database, String table)
	{
		String pattern = "DROP TABLE {0}.{1};";

		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database, table));
	}

	@Override
	public void truncateTable(String database, String table)
	{
		String pattern = "TRUNCATE TABLE {0}.{1};";

		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database, table));
	}

	@Override
	public boolean addColumns(String database, String table, List<TableColumn> columns)
	{
		String pattern = "ALTER TABLE {0}.{1} ADD COLUMNS ({2} {3} COMMENT '{4}');";

		try
		{
			for(TableColumn column : columns)
			{
				hiveJdbcTemplate.execute(
						MessageFormat.format(pattern, database, column.getName(),
						column.getType().name(), StringUtils.isEmpty(column.getComment()) ? "" : column.getComment())
				);
			}
		}
		catch(Exception e)
		{

		}
		return true;
	}

	@Override
	public void changeColumns(String database, String table, Map<TableColumn, TableColumn> columns)
	{

	}

	@Override
	public void replaceColumns(String database, String table, List<TableColumn> columns)
	{

	}
}
