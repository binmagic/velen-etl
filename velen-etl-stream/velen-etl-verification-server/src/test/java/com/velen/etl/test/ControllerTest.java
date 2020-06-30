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
		mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
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


}
