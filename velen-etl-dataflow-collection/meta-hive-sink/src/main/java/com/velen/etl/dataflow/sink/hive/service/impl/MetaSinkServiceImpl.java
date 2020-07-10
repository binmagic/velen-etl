package com.velen.etl.dataflow.sink.hive.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.sink.hive.channel.sink.JSONArraySink;
import com.velen.etl.dataflow.sink.hive.channel.sink.SQLSink;
import com.velen.etl.dataflow.sink.hive.properties.MetaSinkProperties;
import com.velen.etl.dataflow.sink.hive.repository.MetaSinkRepository;
import com.velen.etl.dataflow.sink.hive.service.MetaSinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(value = {SQLSink.class, JSONArraySink.class})
@EnableConfigurationProperties(value = {MetaSinkProperties.class})
public class MetaSinkServiceImpl implements MetaSinkService
{
	@Autowired
	private MetaSinkProperties metaSinkProperties;

	@Autowired
	private MetaSinkRepository metaSinkRepository;

	@Override
	public int insert(String sql, Object[] args)
	{
		return metaSinkRepository.insert(sql, args);
	}

	@Override
	public int insert(String sql)
	{
		return metaSinkRepository.insert(sql);
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 * 格式：
	 * "["distinct_id","time","event","project1","project2","project3","project4"]"
	 */
	@StreamListener(JSONArraySink.INPUT)
	public void transform(JSONArray payload)
	{
		Object[] objects = payload.toArray();

		insert(this.metaSinkProperties.getInsertSql(), objects);
	}

	/**
	 * 通道消息转换(全都转换成JSON对象)
	 * 格式：
	 * "insert into (.....)"
	 */
	@StreamListener(SQLSink.INPUT)
	public void transform(String payload)
	{
		// 要不要验一下 payload 是不是SQL

		insert(payload);
	}
}
