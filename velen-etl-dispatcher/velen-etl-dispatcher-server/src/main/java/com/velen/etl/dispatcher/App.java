package com.velen.etl.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.dataflow.rest.client.config.DataFlowClientAutoConfiguration;

import java.net.UnknownHostException;


@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
