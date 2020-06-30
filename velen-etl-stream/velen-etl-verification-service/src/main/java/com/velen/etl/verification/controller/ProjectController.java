package com.velen.etl.verification.controller;

import com.velen.etl.dispatcher.restful.api.DispatchApi;
import com.velen.etl.verification.entity.InputFieldRule;
import com.velen.etl.verification.entity.InputParseFormat;
import com.velen.etl.verification.entity.Project;
import com.velen.etl.verification.repository.InputFieldRuleRepository;
import com.velen.etl.verification.repository.InputParseFormatRepository;
import com.velen.etl.verification.repository.ProjectRepository;
import com.velen.etl.verification.service.ProjectService;
import com.velen.etl.verification.entity.DeployEnum;
import com.velen.etl.verification.entity.VerifyEnum;
import com.velen.etl.verification.tdo.FieldRuleTDO;
import com.velen.etl.verification.tdo.ParseFormatTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/project/verification")
public class ProjectController
{
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private InputFieldRuleRepository inputFieldRuleRepository;
	@Autowired
	private InputParseFormatRepository inputParseFormatRepository;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private DispatchApi dispatchApi;

	//@Autowired
	//private NacosConfigManager nacosConfigManager;


	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg) throws Exception
	{

		List<InputParseFormat> inputParseFormats = this.inputParseFormatRepository.withQueryFindByProjectId("a");

		//this.inputParseFormatRepository.withQueryDeleteAllByProjectId("a");

		this.inputParseFormatRepository.insert(new InputParseFormat(new Random().nextInt(), "a", VerifyEnum.InputParseType.NONE, "", ""));
		this.inputParseFormatRepository.insert(new InputParseFormat(new Random().nextInt(), "a", VerifyEnum.InputParseType.NONE, "", ""));


		//projectRepository.insert(new Project(arg, true, ""));
		return "TEST OK:" + arg;
	}

	/**
	 * 创建项目
	 */
	@PostMapping("/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("topic") String topic, @RequestParam("enforced") Boolean enforcedVerify,
	                      @RequestParam("operator") String operator)
	{
		// uncheck parameters

		Optional<Project> op = projectRepository.findById(appId);

		if(op.isPresent())
		{
			projectRepository.deleteById(appId);
		}

		//if(projectRepository.existsById(appId))
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


		Project project = projectRepository.insert(new Project(appId, topic, enforcedVerify, "", "DEFAULT-GROUP", operator));

		try
		{
			if(!projectService.updateProject("demo-source", "DEFAULT_GROUP", project, true))
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		catch(Exception e)
		{

		}


		return ResponseEntity.ok().build();
	}

	/**
	 * 设置输入流的解释方式
	 */
	@PostMapping("/set-input-parse")
	ResponseEntity setInputParse(@RequestParam("appId") String appId
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO
			, @RequestParam("operator") String operator)
	{
		// uncheck parameters

		List<InputParseFormat> inputParseFormats = this.inputParseFormatRepository.withQueryFindByProjectId(appId);
		for(InputParseFormat o : inputParseFormats)
		{
			this.inputParseFormatRepository.delete(o);
		}

		inputParseFormats.clear();
		for(ParseFormatTDO tdo : parseFormatsTDO)
		{
			inputParseFormats.add(new InputParseFormat(tdo.getIndex(), appId, tdo.getInputParseType(), tdo.getFormula()
			, operator));
		}
		inputParseFormatRepository.insert(inputParseFormats);

		if(!projectService.updateParseFormats(appId, "", "", inputParseFormats, true))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.ok().build();
	}

	/**
	 * 设置字段入库规则
	 */
	@PostMapping("/set-field-rule")
	ResponseEntity setFieldRule(@RequestParam("appId") String appId
			, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator)
	{

		List<InputFieldRule> inputFieldRules = this.inputFieldRuleRepository.withQueryFindByProjectId(appId);
		for(InputFieldRule o : inputFieldRules)
		{
			this.inputFieldRuleRepository.delete(o);
		}

		inputFieldRules.clear();
		for(FieldRuleTDO tdo : fieldRulesTDO)
		{
			inputFieldRules.add(new InputFieldRule(tdo.getFieldName(), appId, tdo.getKeyRuleType(), tdo.getKeyRule(), tdo.getValueRule(), operator));
		}
		this.inputFieldRuleRepository.insert(inputFieldRules);

		if(!projectService.updateFieldRules(inputFieldRules, true))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


		return ResponseEntity.ok().build();
	}

	/**
	 * 设置规则
	 */
	@PostMapping("/settle")
	ResponseEntity settle(@RequestParam("appId") String appId, @RequestParam("topic") String topic, @RequestParam("enforced") Boolean enforcedVerify
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 部署项目
	 */
	/*@PostMapping("/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type, @RequestParam("operator") String operator)
	{

		String streamName = appId + "-test";
		String streamDefinition = "test-source - | log";
		//dataFlowApi.define(streamName, streamDefinition, true);

		//dataFlowApi.deploy(streamName);

		return ResponseEntity.ok().build();
	}*/

	/**
	 * 部署stream
	 */
	@PostMapping("/stream/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.StreamDeployType type, @RequestParam("operator") String operator)
	{
		String id = "";
		return dispatchApi.deploy(id, type);
	}

	/**
	 * 部署task
	 */
	@PostMapping("/task/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.TaskDeployType type, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 部署app
	 */
	@PostMapping("/app/deploy")
	ResponseEntity deploy(@RequestParam("appId") String appId, @RequestParam("type") DeployEnum.AppDeployType type, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}


}
