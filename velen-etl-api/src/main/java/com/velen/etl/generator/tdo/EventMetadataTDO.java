package com.velen.etl.generator.tdo;

import javafx.util.Pair;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一做成一个表，现在不区分 event 了
 */
@Deprecated
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventMetadataTDO
{
	// [db_name.]table_name    -- (Note: TEMPORARY available in Hive 0.14.0 and later)
	String db;
	String table;
	//String event;

	// private boolean require;
	// specific properties
	// <name,<index,type>>
	List<PropertyMetadataTDO> properties = new ArrayList<>();
}
