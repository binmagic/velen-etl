package com.velen.etl.dataflow.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "com.velen.etl")
@EnableDiscoveryClient
public class DemoApplication
{
	public static void main(String[] args) throws UnknownHostException
	{
		SpringApplication.run(DemoApplication.class, args);
	}
}
