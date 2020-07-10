package com.velen.etl.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.velen.etl")
@EnableMongoRepositories(basePackages = "com.velen.etl.common.repository")
@EnableDiscoveryClient
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
