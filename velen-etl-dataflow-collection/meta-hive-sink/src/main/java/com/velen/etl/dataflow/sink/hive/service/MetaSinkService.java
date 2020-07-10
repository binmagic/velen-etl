package com.velen.etl.dataflow.sink.hive.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;


public interface MetaSinkService
{
	int insert(String sql, Object[] args);
	int insert(String sql);

}
