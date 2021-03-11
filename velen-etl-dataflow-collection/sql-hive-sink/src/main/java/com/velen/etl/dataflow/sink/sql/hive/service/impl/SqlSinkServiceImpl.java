package com.velen.etl.dataflow.sink.sql.hive.service.impl;

import com.velen.etl.dataflow.sink.sql.hive.properties.SqlSinkProperties;
import com.velen.etl.dataflow.sink.sql.hive.repository.SqlSinkRepository;
import com.velen.etl.dataflow.sink.sql.hive.service.SqlSinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(value = {Sink.class})
//@EnableConfigurationProperties(value = {SqlSinkProperties.class})
public class SqlSinkServiceImpl implements SqlSinkService
{
	//@Autowired
	//private SqlSinkProperties sqlSinkProperties;

	@Autowired
	private SqlSinkRepository sqlSinkRepository;

	@Override
	public void execute(String sql)
	{
		this.sqlSinkRepository.execute(sql);
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 */
	@StreamListener(Sink.INPUT)
	public void transform(String payload)
	{
		execute(payload);
	}
}
