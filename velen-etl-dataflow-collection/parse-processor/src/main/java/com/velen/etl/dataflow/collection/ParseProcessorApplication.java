package com.velen.etl.dataflow.collection;

import com.velen.etl.dataflow.collection.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.Transformer;

import java.net.UnknownHostException;

//@EnableBinding(Processor.class)
@SpringBootApplication
@EnableDiscoveryClient
public class ParseProcessorApplication
{
	public static void main(String[] args) throws UnknownHostException
	{
		SpringApplication.run(ParseProcessorApplication.class, args);
	}
}
