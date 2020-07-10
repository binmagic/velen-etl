package com.velen.etl.generator.tdo;

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
public class TableMetadataTDO
{
	// [db_name.]table_name    -- (Note: TEMPORARY available in Hive 0.14.0 and later)
	String db; // AppId
	String table; // [event,profile]
	//String event;

	// private boolean require;
	// specific properties
	// <name,<index,type>>
	List<PropertyMetadataTDO> properties = new ArrayList<>();
}
