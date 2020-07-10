package com.velen.etl.dataflow.sink.hive.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MetaSinkRepository
{
	@Autowired
	private JdbcTemplate hiveJdbcTemplate;

	public int insert(String sql, Object[] objects)
	{
		return hiveJdbcTemplate.update(sql, objects);
	}

	public int insert(String sql)
	{
		return hiveJdbcTemplate.update(sql);
	}
}
