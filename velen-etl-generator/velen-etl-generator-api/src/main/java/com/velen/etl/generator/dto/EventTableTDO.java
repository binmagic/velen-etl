package com.velen.etl.generator.dto;

import com.velen.etl.generator.entity.GenerateEnum;
import com.velen.etl.generator.entity.TableColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventTableTDO
{
	// [EXTERNAL]
	boolean external = false;
	// [db_name.]table_name    -- (Note: TEMPORARY available in Hive 0.14.0 and later)
	String db;
	String name;
	// [(col_name data_type [column_constraint_specification] [COMMENT col_comment], ... [constraint_specification])]
	List<EventTableColumnTDO> columns = new ArrayList<>();
	// [COMMENT table_comment]
	String comment;
	// [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
	List<EventTableColumnTDO> partitionedBy = new ArrayList<>();
	// -------------------------------------------------------------------------------------------------------------
	// 暂未实现
	// [CLUSTERED BY (col_name, col_name, ...) [SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS]
	// [SKEWED BY (col_name, col_name, ...)                  -- (Note: Available in Hive 0.10.0 and later)]
	//     ON ((col_value, col_value, ...), (col_value, col_value, ...), ...)
	//     [STORED AS DIRECTORIES]
	// -------------------------------------------------------------------------------------------------------------
	// [
	//   [ROW FORMAT row_format]
	//   [STORED AS file_format]
	//     | STORED BY 'storage.handler.class.name' [WITH SERDEPROPERTIES (...)]  -- (Note: Available in Hive 0.6.0 and later)
	//  ]
	Map<GenerateEnum.RowDelimitedType, String> rowFormat = new HashMap<>();
	GenerateEnum.StoredAsType storedAs = GenerateEnum.StoredAsType.TEXTFILE;
	// [LOCATION hdfs_path]
	String location;
	// [TBLPROPERTIES (property_name=property_value, ...)]   -- (Note: Available in Hive 0.6.0 and later)
	Map<String, String> tblProperties = new HashMap<>();
}
