package com.velen.etl.dataflow.collection.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DemoProcessorApplication
{
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String myProcessor(String payload)
	{
		return "Processed: " + payload;
	}

	//@Transformer(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)

	public static void main(String[] args)
	{
		SpringApplication.run(DemoProcessorApplication.class, args);
	}
}
