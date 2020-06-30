package com.velen.etl.test;


import com.velen.etl.dispatcher.controller.DataFlowController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
		com.velen.etl.dispatcher.controller.DataFlowController.class
		, com.velen.etl.configuration.DataFlowConfiguration.class
})
@WebAppConfiguration
public class DataFlowTests
{
	protected Logger logger = LoggerFactory.getLogger(DataFlowTests.class);

	@Autowired
	private DataFlowController dataFlowController;

	private MockMvc mockMvc;

	@Before
	public void init()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(dataFlowController).build();
		logger.info("开始测试...");
	}

	@After
	public void after()
	{
		logger.info("测试结束...");
	}

	@Test
	public void define() throws Exception
	{
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/project/dispatch/dataflow/define")
				.accept(MediaType.APPLICATION_JSON).param("name", "test",
						"definition", "http | log", "deploy", "true"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		//logger.info(mvcResult.getResponse().getContentAsString());
	}
}
