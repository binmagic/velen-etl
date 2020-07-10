package com.velen.etl.common.channel.dataflow.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface VerifySource
{
	String OUTPUT = "verify-output";

	@Output(VerifySource.OUTPUT)
	MessageChannel output();
}
