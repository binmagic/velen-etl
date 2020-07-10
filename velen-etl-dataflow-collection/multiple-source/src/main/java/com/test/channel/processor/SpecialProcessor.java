package com.test.channel.processor;

import com.test.channel.sink.SpecialSink;
import com.test.channel.source.SpecialSource;
import org.springframework.cloud.stream.messaging.Source;

public interface SpecialProcessor extends Source, SpecialSink
{
}
