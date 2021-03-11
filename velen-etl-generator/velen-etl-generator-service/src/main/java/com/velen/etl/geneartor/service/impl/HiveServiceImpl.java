package com.velen.etl.geneartor.service.impl;

import com.velen.etl.geneartor.entity.ColumnMetadata;
import com.velen.etl.geneartor.entity.TableMetadata;
import com.velen.etl.geneartor.service.HiveService;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.Format;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

//@Primary
@Service
public class HiveServiceImpl implements HiveService
{
	@Autowired
	@Qualifier("hiveDruidTemplate")
	JdbcTemplate hiveJdbcTemplate;

	@Autowired
	@Qualifier("hiveMetaStoreClient")
	HiveMetaStoreClient hiveMetaStoreClient;

	@Override
	public boolean createDatabase(String database, String comment)
	{
		String pattern = "CREATE DATABASE {0}";

		//String pattern = "CREATE SCHEMA {0};";

		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database));
		return true;
	}

	@Override
	public void dropDatabase(String database)
	{
		//String pattern = "DROP SCHEMA {0};";
		String pattern = "DROP DATABASE {0}";
		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database));
	}

	/*@Deprecated
	public boolean createTable(TableMetadata table)
	{
		// 行写成临时的
		StringBuilder sb = new StringBuilder();

		try
		{
			sb.append("create table ");
			sb.append(table.getTable());
			sb.append(" (\n");
			sb.append("  distinct_id int \n");
			sb.append(", time timestamp \n");
			sb.append(", event string \n");
			sb.append(", project string \n");
			for(ColumnMetadata column : table.getColumns())
			{
				sb.append(", ");
				sb.append(column.getName());
				sb.append(column.getType());
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
	}*/

	@Override
	public boolean tableExists(String database, String table)
	{
		return false;
	}

	@Override
	public boolean createTable(TableMetadata tableMetadata)
	{
		return createTable(tableMetadata.getDb(), tableMetadata.getTable(), "");
	}

	protected boolean createTable(String database, String table, String comment)
	{
		String pattern = "CREATE EXTERNAL TABLE {0}.{1}\n" +
				"(\n" +
				"    distinct_id STRING COMMENT 'user of identity' ,\n" +
				"    create_time TIMESTAMP COMMENT 'create time' ,\n" +
				"    event STRING COMMENT 'name of event' ,\n" +
				"    project STRING COMMENT 'name of project'\n" +
				")\n" +
				"COMMENT '{2}'\n" +
				"PARTITIONED BY(p_event STRING, p_time TIMESTAMP)\n" +
				"ROW FORMAT DELIMITED\n" +
				"    FIELDS TERMINATED BY '\\001'\n" +
				"STORED AS SEQUENCEFILE";

		hiveJdbcTemplate.execute(MessageFormat.format(pattern, database, table, comment));
		return true;
	}

	@Override
	public boolean alterTable(String oldDatabase, String oldTable, TableMetadata newTable)
	{
		return false;
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
	public boolean deleteColumns(String database, String table, String column)
	{
		return false;
	}

	@Override
	public boolean updateColumns(String database, String table, String column)
	{
		return false;
	}

	@Override
	public boolean addColumns(String database, String table, List<ColumnMetadata> columns)
	{
		String pattern = "ALTER TABLE {0}.{1} ADD COLUMNS ({2} {3} COMMENT '{4}');";

		try
		{
			for(ColumnMetadata column : columns)
			{
				hiveJdbcTemplate.execute(
						MessageFormat.format(pattern, database, column.getName(),
						column.getType(), StringUtils.isEmpty(column.getComment()) ? "" : column.getComment())
				);
			}
		}
		catch(Exception e)
		{

		}
		return true;
	}

	@Override
	public void changeColumns(String database, String table, Map<ColumnMetadata, ColumnMetadata> columns)
	{

	}

	@Override
	public void replaceColumns(String database, String table, List<ColumnMetadata> columns)
	{

	}
}
