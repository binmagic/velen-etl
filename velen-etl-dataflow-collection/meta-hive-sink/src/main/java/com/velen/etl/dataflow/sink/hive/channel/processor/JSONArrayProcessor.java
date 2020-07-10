package com.velen.etl.dataflow.sink.hive.channel.processor;

import com.velen.etl.dataflow.sink.hive.channel.sink.JSONArraySink;
import com.velen.etl.dataflow.sink.hive.channel.source.JSONArraySource;

public interface JSONArrayProcessor extends JSONArraySource, JSONArraySink
{
}
