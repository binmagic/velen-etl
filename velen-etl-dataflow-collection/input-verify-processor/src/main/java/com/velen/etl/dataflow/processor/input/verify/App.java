package com.velen.etl.dataflow.processor.input.verify;

import com.velen.etl.generator.api.TableMetadataApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.velen.etl"}, scanBasePackageClasses = {TableMetadataApi.class})
@EnableDiscoveryClient
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}
}
