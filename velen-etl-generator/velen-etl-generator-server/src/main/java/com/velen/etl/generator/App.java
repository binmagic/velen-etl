package com.velen.etl.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.velen.etl")
@EnableDiscoveryClient
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
