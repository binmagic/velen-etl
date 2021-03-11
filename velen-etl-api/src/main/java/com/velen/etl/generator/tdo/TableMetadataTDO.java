package com.velen.etl.generator.tdo;

import javafx.util.Pair;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一做成一个表，现在不区分 event 了
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TableMetadataTDO
{
	// [db_name.]table_name    -- (Note: TEMPORARY available in Hive 0.14.0 and later)
	String db; // AppId
	String table;
	//String event;
	//int type; // [event,profile]

	// private boolean require;
	// specific properties
	// <name,<index,type>>
	List<PropertyMetadataTDO> properties = new ArrayList<>();
	// TODO: 暂时没想好样不要展式层传
	List<Pair<String, String>> partitions = new ArrayList<>();

	//List<PropertyMetadataTDO> allProperties = new ArrayList<>();
}
