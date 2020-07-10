package com.velen.etl.dataflow.sink.hive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
public class App
{
	// 暂时先都认为是先事件
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
