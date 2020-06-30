package com.velen.etl.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.net.UnknownHostException;


@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = com.velen.etl.dispatcher.restful.api.DispatchApi.class)
public class App
{
	public static void main(String[] args) throws UnknownHostException
	{
		SpringApplication.run(App.class, args);
	}
}
