package com.velen.etl.dataflow.sink.hive.channel.processor;

import com.velen.etl.dataflow.sink.hive.channel.sink.SQLSink;
import com.velen.etl.dataflow.sink.hive.channel.source.SQLSource;

public interface SQLProcessor extends SQLSource, SQLSink
{
}
