package com.velen.etl.dataflow.collection.demo;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.velen.etl.configuration.ProjectConfiguration;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@EnableScheduling
@EnableBinding(Source.class)
@SpringBootApplication(scanBasePackages = "com.velen.etl")
@EnableDiscoveryClient
public class DemoSourceApplication
{
	@Autowired
	private Source source;

	@Autowired
	private ProjectConfiguration projectConfiguration;

	@Autowired
	private NacosConfigManager nacosConfigManager;

	@Scheduled(fixedDelay = 1000)
	public void sendGreetingEvents() throws NacosException
	{
		//this.source.output().send(MessageBuilder.withPayload("Greeting: " + new Date().toString()).build());
	}

	@PostConstruct
	private void start() throws NacosException
	{
		nacosConfigManager.getConfigService().addListener(projectConfiguration.getDataId(), projectConfiguration.getGroup(), new Listener()
		{
			@Override
			public Executor getExecutor()
			{
				return null;
			}

			@Override
			public void receiveConfigInfo(String configInfo)
			{
				System.out.println("recieve:" + configInfo);
			}
		});
	}

	/*@StreamListener(Processor.INPUT)
	@SendTo(Processor.INPUT)
	@InboundChannelAdapter()
	public void test()
	{

	}*/

	public static void main(String[] args)
	{
		SpringApplication.run(DemoSourceApplication.class, args);
	}
}
