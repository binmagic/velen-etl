package com.velen.etl.dataflow.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
@EnableBinding(Source.class)
public class TestSourceApplication
{
	public static void main(String[] args) throws UnknownHostException
	{
		SpringApplication.run(TestSourceApplication.class, args);
	}
}
