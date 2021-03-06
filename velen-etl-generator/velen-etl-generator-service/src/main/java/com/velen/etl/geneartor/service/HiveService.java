package com.velen.etl.geneartor.service;

import com.velen.etl.geneartor.entity.ColumnMetadata;
import com.velen.etl.geneartor.entity.TableMetadata;

import java.util.List;
import java.util.Map;

public interface HiveService
{
	/**
	 * 创建库
	 * create database name
	 */
	boolean createDatabase(String database, String comment);

	/**
	 * 删除库
	 * create database name
	 */
	void dropDatabase(String database);

	/**
	 * 直接建表
	 * create table t1(
	 *     id      int
	 *    ,name    string
	 *    ,hobby   array<string>
	 *    ,add     map<String,string>
	 * )
	 * row format delimited
	 * fields terminated by ','
	 * collection items terminated by '-'
	 * map keys terminated by ':'
	 * ;
	 */
	//boolean createTable(Table table);
	boolean createTable(TableMetadata tableMetadata);

	/**
	 * 删除表
	 * drop table table_name;
	 */
	void dropTable(String database, String table);

	/**
	 * 表是否存在
	 */
	boolean tableExists(String database, String table);

	/**
	 * 修改表(接口暂时先这样写)
	 */
	boolean alterTable(String oldDatabase, String oldTable, TableMetadata newTable);

	/**
	 * 获取表数据
	 */
	//void getTable(String database, String table);

	/**
	 * 装载数据
	 * load data local inpath '/home/hadoop/Desktop/data' overwrite into table t2;
	 */

	/**
	 * 删除表中数据
	 * truncate table table_name;
	 */
	void truncateTable(String database, String table);

	/**
	 * 按分区删除数据
	 * alter table table_name drop partition (partition_name='分区名')
	 */


	/**
	 * 删除列数据
	 */
	boolean deleteColumns(String database, String table, String column);

	/**
	 * 更新列数据
	 */
	boolean updateColumns(String database, String table, String column);



	/**
	 * 添加列
	 * 可以一次增加多个列
	 * ALTER TABLE table_name ADD COLUMNS (c1 INT,c2 STRING);
	 * 添加一列并增加列字段注释
	 * ALTER TABLE table_name ADD COLUMNS (new_col INT COMMENT 'a comment');
	 */
	boolean addColumns(String database, String table, List<ColumnMetadata> columns);

	/**
	 * 改变列名/类型/位置/注释
	 * ALTER TABLE table_name CHANGE
	 * 	[CLOUMN] col_old_name col_new_name column_type
	 * 	[CONMMENT col_conmment]
	 * 	[FIRST|AFTER column_name]
	 * 	[CASCADE|RESTRICT];
	 */
	void changeColumns(String database, String table, Map<ColumnMetadata, ColumnMetadata> columns);

	/**
	 * 删除列
	 * 原有Hive表 table_name 中有a,b,c,d,e这几个字段
	 * 将从 table_name 中删除“d”列:
	 * ALTER TABLE table_name REPLACE COLUMNS (a int, b int, c string, e string);
	 */
	void replaceColumns(String database, String table, List<ColumnMetadata> columns);
}
