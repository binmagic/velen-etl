package com.velen.etl.test;

import com.velen.etl.configuration.HiveDruidConfiguration;
import com.velen.etl.configuration.InputParseFormatConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		  com.velen.etl.configuration.InputParseFormatConfiguration.class
		, com.velen.etl.configuration.InputFieldRuleConfiguration.class
		, com.velen.etl.configuration.HiveDruidConfiguration.class
})
@WebAppConfiguration
@ContextConfiguration(classes = {
		HiveDruidConfiguration.class
})
@TestPropertySource("classpath:test.properties")
@ImportResource("classpath:/application.yml")
@ActiveProfiles("dev")
public class ApplicationTest
{
	//@Autowired
	//private WebApplicationContext context;

	@Autowired
	InputParseFormatConfiguration inputParseFormatConfiguration;

	@Autowired
	HiveDruidConfiguration hiveDruidConfiguration;

	@Test
	public void contextLoads()
	{
	}

	// 不知道为什么配置文件读不出来
	@Test
	public void testConfiguration()
	{
		URL url = this.getClass().getResource("classpath:/application.yml");
		List<InputParseFormatConfiguration.ParseFormatConfiguration> list = inputParseFormatConfiguration.getFormats();
		System.out.println(inputParseFormatConfiguration.getFormats());


	}
}
