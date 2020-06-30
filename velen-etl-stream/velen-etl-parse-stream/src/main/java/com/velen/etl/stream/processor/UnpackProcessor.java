package com.velen.etl.stream.processor;

import com.velen.etl.stream.configuration.UnpackProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(Processor.class)
@EnableConfigurationProperties({UnpackProperties.class})
public class UnpackProcessor
{
}
