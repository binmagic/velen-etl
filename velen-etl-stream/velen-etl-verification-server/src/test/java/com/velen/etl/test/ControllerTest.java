package com.velen.etl.test;

import com.velen.etl.verification.controller.ProjectController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class)
public class ControllerTest
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProjectController projectController;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void init()
	{
		//mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
		logger.info("开始测试...");
	}

	@After
	public void after()
	{
		logger.info("测试结束...");
	}

	@Test
	public void testCreate() throws Exception
	{
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/dispatch/dataflow/define")
				.accept(MediaType.APPLICATION_JSON).param("name", "test",
						"definition", "http | log", "deploy", "true"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//logger.info(mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testRegex()
	{
		String content = "2019-11-25 10:38:55.371 INFO   jdk11-online [@3a907380-f60b-4e28-ae2b-c30e562277bf] (inner-executor1-1) (com.storm.net.log4jConfig.TxDBLog) {\"logname\":\"MoneyFlow\",\"GameSvrId\":\"104\",\"dtEventTime\":\"2019-11-25 10:38:55\",\"PlatID\":1,\"iZoneAreaID\":1,\"vopenid\":\"2825002579772470409\",\"Sequence\":\"\",\"Level\":5,\"AfterMoney\":80379,\"iMoney\":15,\"Reason\":0,\"SubReason\":\"\",\"AddOrReduce\":1,\"iMoneyType\":2,\"LoginChannel\":9,\"Account\":\"ÎÞµÐÍþ¸ç\",\"vRoleID\":\"185466558818172851\",\"vRoleName\":\"hw010\",\"Uuid\":\"3a907380-f60b-4e28-ae2b-c30e562277bf\"}";
		String regex = ".*\\(com.storm.net.log4jConfig.TxDBLog\\).*(\\{\\\"logname\\\":\\\"(.+?)\\\"\\,.*\\})";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);

		if(matcher.find())
		{
			for(int i = 0; i < matcher.groupCount(); ++i)
			{
				String group = matcher.group(i);
				System.out.println(group);
			}
		}

	}


}
