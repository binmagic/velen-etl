package com.velen.etl.service;

import com.velen.etl.entity.BusReceiverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class BusReceiverService
{

	@Autowired
	JdbcTemplate hiveJdbcTemplate;

	//@PostConstruct
	public void createTable() {
		StringBuffer sql = new StringBuffer("create table IF NOT EXISTS ");
		sql.append("bus_receiver ");
		sql.append("(id BIGINT comment '主键ID' " +
				",name STRING  comment '姓名' " +
				",address STRING comment '地址'" +
				",en_name STRING comment '拼音名字'" +
				",member_family INT comment '家庭成员'" +
				",createDate DATE comment '创建时') ");
		sql.append(" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'"); // 定义分隔符
		sql.append(" STORED AS TEXTFILE"); // 作为文本存储*/
		hiveJdbcTemplate.execute(sql.toString());
	}

	public void loadData(String pathFile){
		String sql = "LOAD DATA INPATH  '"+pathFile+"' INTO TABLE bus_receiver";
		hiveJdbcTemplate.execute(sql);
	}

	public void insert(BusReceiverEntity busReceiverEntity) {
		hiveJdbcTemplate.update("insert into bus_receiver(id,name,address,en_name,member_family) values(?,?,?,?,?)",
				new PreparedStatementSetter(){
					@Override
					public void setValues(java.sql.PreparedStatement ps) throws SQLException
					{
						ps.setLong(1, busReceiverEntity.getId());
						ps.setString(2,busReceiverEntity.getName());
						ps.setString(3,busReceiverEntity.getAddress());
						ps.setString(4,busReceiverEntity.getEnName());
						ps.setInt(5,busReceiverEntity.getMemberFamily());
						// ps.setDate(6,new java.sql.Date(new Date().getTime()));
						/* ps.setString(7,busReceiverEntity.getRegionCode());*/
					}
				}
		);
	}

	public void select()
	{
		hiveJdbcTemplate.query("select * from bus_receiver", new RowCallbackHandler()
		{
			@Override
			public void processRow(ResultSet rs) throws SQLException
			{
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));


			}
		});
	}

	public void deleteAll(){
		String sql = "insert overwrite table bus_receiver select * from bus_receiver where 1=0";
		hiveJdbcTemplate.execute(sql);
	}
}

