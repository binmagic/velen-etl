package com.velen.etl.common.channel.dataflow.processor;

import com.velen.etl.common.channel.dataflow.sink.ParserSink;
import com.velen.etl.common.channel.dataflow.source.ParserSource;

public interface ParserProcessor extends ParserSource, ParserSink
{
}
