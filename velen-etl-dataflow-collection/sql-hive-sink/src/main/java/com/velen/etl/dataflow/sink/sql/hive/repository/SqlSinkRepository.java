package com.velen.etl.dataflow.sink.sql.hive.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class SqlSinkRepository
{
	protected final Logger logger = LoggerFactory.getLogger(SqlSinkRepository.class);

	@Autowired
	private JdbcTemplate hiveJdbcTemplate;

	public void execute(String sql)
	{
		try
		{
			hiveJdbcTemplate.execute(sql);
		}
		catch(Exception e)
		{
			logger.error("sql {} execute failure {}", sql, e.toString());
		}
	}
}
