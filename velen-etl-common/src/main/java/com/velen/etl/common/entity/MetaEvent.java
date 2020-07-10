package com.velen.etl.common.entity;

import com.velen.etl.common.annotation.ScatterColumn;
import com.velen.etl.common.annotation.StructColumn;
import lombok.*;

import javax.persistence.*;
import javax.swing.tree.RowMapper;
import java.sql.Timestamp;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Entity
@Table(name="meta_events")
public class MetaEvent
{
	private String db;
	private String table;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private String distinctId;
	private Timestamp time;
	private String event;
	private String project;

	//@ManyToOne(cascade = CascadeType.PERSIST)
	//@JoinColumn(name ="properties")
	@ScatterColumn
	private Map<String, Object> properties;
}
