package com.velen.etl.dataflow.sink.hive.service.impl;

import com.velen.etl.dataflow.sink.hive.service.MetaSinkService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class MetaSinkServiceImpl implements MetaSinkService
{
}
