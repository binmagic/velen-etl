package com.velen.etl.dataflow.collection.channel;

import org.springframework.cloud.stream.messaging.Processor;

public interface MyProcessor extends MySource, MySink
{
}
