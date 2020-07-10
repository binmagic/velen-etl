package com.velen.etl.dataflow.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
public class TestSourceApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(TestSourceApplication.class, args);
	}
}
