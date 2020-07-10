package com.velen.etl.dataflow.sink.hive.repository;

import com.velen.etl.common.entity.MetaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.SQLException;

@Repository
public class MetaSinkRepository
{
	@Autowired
	private JdbcTemplate hiveJdbcTemplate;

	public void insert(MetaEvent event)
	{
		hiveJdbcTemplate.update("insert into bus_receiver(id,name,address,en_name,member_family) values(?,?,?,?,?)",
				new PreparedStatementSetter(){
					@Override
					public void setValues(java.sql.PreparedStatement ps) throws SQLException
					{
						//ps.setLong(1, busReceiverEntity.getId());
						//ps.setString(2,busReceiverEntity.getName());
						//ps.setString(3,busReceiverEntity.getAddress());
						//ps.setString(4,busReceiverEntity.getEnName());
						//ps.setInt(5,busReceiverEntity.getMemberFamily());
						// ps.setDate(6,new java.sql.Date(new Date().getTime()));
						/* ps.setString(7,busReceiverEntity.getRegionCode());*/
					}
				}
		);

	}
}
