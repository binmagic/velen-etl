package com.velen.etl.geneartor.service.impl;

import com.velen.etl.geneartor.configuration.HiveMetaStoreConfiguration;
import com.velen.etl.geneartor.entity.ColumnMetadata;
import com.velen.etl.geneartor.entity.TableMetadata;
import com.velen.etl.geneartor.service.HiveService;
import javafx.util.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.*;
import org.apache.hadoop.hive.metastore.client.builder.DatabaseBuilder;
import org.apache.hadoop.hive.metastore.client.builder.TableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Primary
@Service
public class HiveMetaStoreServiceImpl implements HiveService
{
	final Logger logger = LoggerFactory.getLogger(HiveService.class);

	@Autowired
	@Qualifier("hiveMetaStoreClient")
	HiveMetaStoreClient hiveMetaStoreClient;

	@Autowired
	HiveMetaStoreConfiguration hiveMetaStoreConfiguration;

	@Autowired
	Configuration configuration;

	@Override
	public boolean createDatabase(String database, String comment)
	{
		try
		{
			Database db = new DatabaseBuilder()
					.setName(database)
					.setDescription(comment)
					.create(hiveMetaStoreClient, configuration);

			return db != null;
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("create db fail! " + ignore.getMessage());
		}
		return false;
	}

	@Override
	public void dropDatabase(String database)
	{
		try
		{
			hiveMetaStoreClient.dropDatabase(database, false, true, true);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("drop db fail! " + ignore.getMessage());
		}
	}

	@Override
	public boolean createTable(TableMetadata tableMetadata)
	{
		try
		{
			List<FieldSchema> cols = new ArrayList<>();
			for(ColumnMetadata meta : tableMetadata.getColumns())
			{
				cols.add(new FieldSchema(meta.getName(), meta.getType(), meta.getComment()));
			}
			List<FieldSchema> parts = new ArrayList<>();
			for(Pair<String, String> entry : tableMetadata.getPartitions())
			{
				cols.add(new FieldSchema(entry.getKey(), entry.getValue(), entry.getKey()));
			}

			Table tbl = new TableBuilder()
					.setDbName(tableMetadata.getDb())
					.setTableName(tableMetadata.getTable())
					//.setType(TableType.EXTERNAL_TABLE.toString())
					//.setCatName(catName)
					//.setSerdeLib("org.apache.hadoop.hive.ql.io.orc.OrcSerde")
					//.setInputFormat("org.apache.hadoop.hive.ql.io.orc.OrcInputFormat")
					//.setOutputFormat("org.apache.hadoop.hive.ql.io.orc.OrcOutputFormat")
					.setCols(cols)
					.setPartCols(parts)
					//.addTableParam(hive_metastoreConstants.TABLE_IS_TRANSACTIONAL, "false")
					.create(hiveMetaStoreClient, configuration);

			return tbl != null;
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("create table fail! " + ignore.getMessage());
		}
		return false;
	}

	@Deprecated
	public boolean createTable(String database, String table)
	{
		try
		{

			Map<String, String> map = new HashMap<String, String>()
			{
				{
					put("", "");
					put("", "");
				}
			};
			// Table(tableName:test1, dbName:default, owner:anonymous, createTime:1593604587, lastAccessTime:0, retention:0,
			// sd:StorageDescriptor(cols:[FieldSchema(name:uuid, type:string, comment:null), FieldSchema(name:id, type:int, comment:null), FieldSchema(name:time, type:date, comment:null), FieldSchema(name:prototypes, type:map<string,string>, comment:null)],
			//    location:hdfs://hadoop1:9000/user/hive/warehouse/test1, inputFormat:org.apache.hadoop.mapred.TextInputFormat, outputFormat:org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat, compressed:false, numBuckets:-1,
			//    serdeInfo:SerDeInfo(name:null, serializationLib:org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe, parameters:{mapkey.delim=:, serialization.format=,, field.delim=,}),
			// bucketCols:[], sortCols:[], parameters:{}, skewedInfo:SkewedInfo(skewedColNames:[], skewedColValues:[], skewedColValueLocationMaps:{}), storedAsSubDirectories:false), partitionKeys:[],
			// parameters:{totalSize=47, numRows=2, rawDataSize=45, COLUMN_STATS_ACCURATE={\"BASIC_STATS\":\"true\"}, numFiles=2, transient_lastDdlTime=1593605387, bucketing_version=2}, viewOriginalText:null, viewExpandedText:null, tableType:MANAGED_TABLE, rewriteEnabled:false, catName:hive, ownerType:USER)



			// Table(tableName:event1, dbName:abc5, owner:root, createTime:1594880242, lastAccessTime:0, retention:0,
			// sd:StorageDescriptor(cols:[FieldSchema(name:distinct_id, type:string, comment:user of identity), FieldSchema(name:create_time, type:timestamp, comment:create time), FieldSchema(name:event, type:string, comment:name of event), FieldSchema(name:project, type:string, comment:name of project), FieldSchema(name:p_event, type:string, comment:null), FieldSchema(name:p_time, type:timestamp, comment:null)],
			//     location:file:/user/hive/warehouse/event1, inputFormat:org.apache.hadoop.mapred.SequenceFileInputFormat, outputFormat:org.apache.hadoop.hive.ql.io.HiveSequenceFileOutputFormat, compressed:false, numBuckets:-1,
			//     serdeInfo:SerDeInfo(name:null, serializationLib:org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe, parameters:{serialization.format=\\001, field.delim=\\001}),
			// bucketCols:[], sortCols:[], parameters:{}, skewedInfo:SkewedInfo(skewedColNames:[], skewedColValues:[], skewedColValueLocationMaps:{}), storedAsSubDirectories:false), partitionKeys:[FieldSchema(name:p_event, type:string, comment:null), FieldSchema(name:p_time, type:timestamp, comment:null)],
			// parameters:{totalSize=0, EXTERNAL=TRUE, numRows=0, rawDataSize=0, COLUMN_STATS_ACCURATE={\"BASIC_STATS\":\"true\"}, numFiles=0, numPartitions=0, transient_lastDdlTime=1594880242, bucketing_version=2, comment=haha}, viewOriginalText:null, viewExpandedText:null, tableType:EXTERNAL_TABLE, rewriteEnabled:false, catName:hive, ownerType:USER)

			SerDeInfo serDeInfo = new SerDeInfo("null", "org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe",
					new HashMap<String, String>(){{
						put("mapkey.delim=", ":");
						put("serialization.format=", ",");
						put("field.delim=", ","); }});


			StorageDescriptor sd = new StorageDescriptor(Collections.EMPTY_LIST, "", "org.apache.hadoop.mapred.TextInputFormat", "org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat",
					false, -1, serDeInfo, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_MAP);


			Table tbl = new Table(table, database, "anonymous", 1459382234, 0, 0, sd, Collections.EMPTY_LIST, Collections.EMPTY_MAP, "null", "null", "MANAGED_TABLE");
			//tbl.setDbName(database);
			//tbl.setTableName(table);





			//sd.setInputFormat("org.apache.hadoop.mapred.TextInputFormat");
			//sd.setOutputFormat("org.apache.hadoop.hive.ql.io.IgnoreKeyTextOutputFormat");

			//tbl.setSd(sd);

			hiveMetaStoreClient.createTable(tbl);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("create table fail! " + ignore.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void dropTable(String database, String table)
	{
		try
		{
			hiveMetaStoreClient.dropTable(database, table, true, true);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("drop table fail! " + ignore.getMessage());
		}
	}

	@Override
	public boolean tableExists(String database, String table)
	{
		try
		{
			return hiveMetaStoreClient.tableExists(database, table);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("create table fail! " + ignore.getMessage());
		}

		return false;
	}

	@Override
	public boolean alterTable(String oldDatabase, String oldTable, TableMetadata newTable)
	{
		try
		{
			Table tbl = hiveMetaStoreClient.getTable(oldDatabase, oldTable);
			if(tbl == null)
				return false;

			List<FieldSchema> cols = new ArrayList<>();
			for(ColumnMetadata meta : newTable.getColumns())
			{
				cols.add(new FieldSchema(meta.getName(), meta.getType(), meta.getComment()));
			}
			tbl.getSd().setCols(cols);

			List<FieldSchema> parts = new ArrayList<>();
			for(Pair<String, String> entry : newTable.getPartitions())
			{
				cols.add(new FieldSchema(entry.getKey(), entry.getValue(), entry.getKey()));
			}
			tbl.setPartitionKeys(parts);

			//add new column with cascade option
			hiveMetaStoreClient.alter_table(newTable.getDb(), newTable.getTable(), tbl, true);

			return true;
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("alter table fail! " + ignore.getMessage());
		}
		return false;
	}

	@Override
	public void truncateTable(String database, String table)
	{

	}

	@Override
	public boolean deleteColumns(String database, String table, String column)
	{
		try
		{
			hiveMetaStoreClient.deleteTableColumnStatistics(database, table, column);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("delete table column {}.{}.{} fail! message {}", database, table, column, ignore.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean updateColumns(String database, String table, String column)
	{
		try
		{
			//ColumnStatistics cs = new ColumnStatistics();

			//hiveMetaStoreClient.updateTableColumnStatistics(null);
		}
		catch(Exception ignore)
		{
			ignore.printStackTrace();
			logger.error("delete table column {}.{}.{} fail! message {}", database, table, column, ignore.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean addColumns(String database, String table, List<ColumnMetadata> columns)
	{
		try
		{
			ColumnStatistics cs = new ColumnStatistics();

			hiveMetaStoreClient.updateTableColumnStatistics(cs);
		}
		catch(Exception ignore)
		{
			logger.error("create table fail! " + ignore.getMessage());
			return false;
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
