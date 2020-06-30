package com.velen.etl.dataflow.test;

import com.velen.etl.dataflow.collection.ParseProcessorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ParseProcessorApplication.class})
@WebAppConfiguration
public class StreamTest
{
	@Autowired
	private Source source;

	@Test
	public void contextLoads()
	{
		source.output().send(MessageBuilder.withPayload("From MyChannel").build());
	}
}
