package com.velen.etl.verification.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.dispatcher.stream.api.FieldVerifyApi;
import com.velen.etl.dispatcher.stream.api.InputParseApi;
import com.velen.etl.dispatcher.stream.api.InputVerifyApi;
import com.velen.etl.verification.configuration.VerificationConfiguration;
import com.velen.etl.verification.entity.*;
import com.velen.etl.verification.repository.ProjectAppRepository;
import com.velen.etl.verification.tdo.FieldRuleTDO;
import com.velen.etl.verification.tdo.ParseFormatTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/project/verification")
public class ProjectController
{
	//@Autowired
	//private ProjectRepository projectRepository;
	//@Autowired
	//private InputFieldRuleRepository inputFieldRuleRepository;
	//@Autowired
	//private InputParseFormatRepository inputParseFormatRepository;

	//@Autowired
	//private NacosConfigService nacosConfigService;
	//@Autowired
	//private TestConfiguration testConfiguration;

	@Autowired
	private ProjectAppRepository projectAppRepository;

	//@Autowired
	//private DispatchApi dispatchApi;
	//@Autowired
	//private InputVerifyApi inputVerifyApi;
	FeignClientBuilder feignClientBuilder;

	// TODO: 这个也许需要 ProjectConfiguration
	//@Value("${project.restful}")
	//private boolean restful = true;
	@Autowired
	private VerificationConfiguration verificationConfiguration;

	public ProjectController(@Autowired ApplicationContext appContext)
	{
		this.feignClientBuilder = new FeignClientBuilder(appContext);
	}

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg) throws Exception
	{
		System.out.println(verificationConfiguration.getRestful());

		//List<InputParseFormat> inputParseFormats = this.inputParseFormatRepository.withQueryFindByProjectId("a");

		//this.inputParseFormatRepository.withQueryDeleteAllByProjectId("a");

		//this.inputParseFormatRepository.insert(new InputParseFormat(new Random().nextInt(), "a", VerifyEnum.InputParseType.NONE, "", ""));
		//this.inputParseFormatRepository.insert(new InputParseFormat(new Random().nextInt(), "a", VerifyEnum.InputParseType.NONE, "", ""));


		//projectRepository.insert(new Project(arg, true, ""));
		return "TEST OK:" + arg;
	}

	/**
	 * 创建项目(也用于属性更新)
	 */
	@PostMapping("/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("verify") Boolean verify,
	                      @RequestParam("operator") String operator)
	{
		// uncheck parameters

		Optional<ProjectApp> op = projectAppRepository.findById(appId);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_EXISTS.code()).body(appId);
		}

		ProjectApp project = projectAppRepository.insert(new ProjectApp(appId, verify, operator));

		// deploy stream
		//Map<String, String> properties = new HashMap<>();
		//properties.put(verificationConfiguration.getTopicProperty(), topic);
		//properties.put(verificationConfiguration.getVerifyProperty(), Boolean.toString(verify));
		//dispatchApi.deployStream(appId, "", properties);

		/*try
		{
			// id
			String id = "";
			// group
			String group = "DEFAULT_GROUP";

			if(restful)
			{

			}
			else
			{
				if(!nacosConfigService.updateProject(id, group, project))
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		catch(Exception e)
		{

		}*/


		return ResponseEntity.ok(project);
	}

	/**
	 * 删除项目
	 */
	@PostMapping("/destroy")
	ResponseEntity destroy(@RequestParam("appId") String appId)
	{
		Optional<ProjectApp> op = this.projectAppRepository.findById(appId);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_NOT_EXISTS.code()).body(appId);
		}

		ProjectApp projectApp = op.get();
		assert projectApp != null;

		this.projectAppRepository.delete(projectApp);

		return ResponseEntity.ok(projectApp);
	}

	/**
	 * 设置校验模式
	 */
	@PostMapping("/set-verify")
	ResponseEntity setVerify(@RequestParam("appId") String appId, @RequestParam("verify") Boolean verify, @RequestParam("operator") String operator)
	{
		// uncheck parameters

		Optional<ProjectApp> op = this.projectAppRepository.findById(appId);

		if(!op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_NOT_EXISTS.code()).body(appId);
		}

		ProjectApp projectApp = op.get();
		assert projectApp != null;

		//if(projectRepository.existsById(appId))
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		projectApp.setVerify(verify);
		projectApp.setLastTimeOperator(operator);

		InputVerifyApi fc = this.feignClientBuilder.forType(InputVerifyApi.class, InputVerifyApi.makeId(appId)).build();
		// make the call
		fc.setVerify(verify);

		projectApp = projectAppRepository.save(projectApp);

		// deploy stream
		//Map<String, String> properties = new HashMap<>();
		//properties.put(verificationConfiguration.getTopicProperty(), "");
		//properties.put(verificationConfiguration.getVerifyProperty(), Boolean.toString(verify));

		return ResponseEntity.ok(projectApp);
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

		Optional<ProjectApp> op = this.projectAppRepository.findById(appId);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_NOT_EXISTS.code()).body(appId);
		}

		ProjectApp projectApp = op.get();
		assert projectApp != null;

		projectApp.clearInputParseFormat();
		for(ParseFormatTDO tdo : parseFormatsTDO)
		{
			projectApp.putInputParseFormat(tdo.getIndex(), tdo.getInputParseType(), tdo.getFormula());
		}

		projectApp.setLastTimeOperator(operator);

		InputParseApi fc = this.feignClientBuilder.forType(InputParseApi.class, InputParseApi.makeId(appId)).build();
		// make the call
		fc.setFormats(parseFormatsTDO);

		projectApp = projectAppRepository.save(projectApp);

		/*

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
		inputParseFormatRepository.insert(inputParseFormats);*/

		//if(!nacosConfigService.updateParseFormats(appId, "", "", inputParseFormats))
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		return ResponseEntity.ok(projectApp);
	}

	/**
	 * 设置字段入库规则
	 */
	@PostMapping("/set-field-rule")
	ResponseEntity setFieldRule(@RequestParam("appId") String appId
			, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator)
	{

		// uncheck parameters

		Optional<ProjectApp> op = this.projectAppRepository.findById(appId);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_NOT_EXISTS.code()).body(appId);
		}

		ProjectApp projectApp = op.get();
		assert projectApp != null;

		projectApp.clearInputFieldRule();
		for(FieldRuleTDO tdo : fieldRulesTDO)
		{
			projectApp.putInputFieldRule(tdo.getFieldName(), tdo.getKeyRuleType(), tdo.getKeyRule(), tdo.getValueRule());
		}
		projectApp.setLastTimeOperator(operator);

		FieldVerifyApi fc = this.feignClientBuilder.forType(FieldVerifyApi.class, FieldVerifyApi.makeId(appId)).build();
		// make the call
		fc.setFields(fieldRulesTDO);

		projectApp = projectAppRepository.save(projectApp);

		/*List<InputFieldRule> inputFieldRules = this.inputFieldRuleRepository.withQueryFindByProjectId(appId);
		for(InputFieldRule o : inputFieldRules)
		{
			this.inputFieldRuleRepository.delete(o);
		}

		inputFieldRules.clear();
		for(FieldRuleTDO tdo : fieldRulesTDO)
		{
			inputFieldRules.add(new InputFieldRule(tdo.getFieldName(), appId, tdo.getKeyRuleType(), tdo.getKeyRule(), tdo.getValueRule(), operator));
		}
		this.inputFieldRuleRepository.insert(inputFieldRules);*/

		//if(!nacosConfigService.updateFieldRules(inputFieldRules))
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


		return ResponseEntity.ok(projectApp);
	}

	/**
	 * 设置规则
	 */
	@PostMapping("/settle")
	ResponseEntity settle(@RequestParam("appId") String appId, @RequestParam("verify") Boolean verify
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator)
	{
		Optional<ProjectApp> op = this.projectAppRepository.findById(appId);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.VERIFICATION_PROJECT_NOT_EXISTS.code()).body(appId);
		}

		ProjectApp projectApp = op.get();
		assert projectApp != null;

		//if(projectRepository.existsById(appId))
		//	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		projectApp.setVerify(verify);

		for(FieldRuleTDO tdo : fieldRulesTDO)
		{
			projectApp.putInputFieldRule(tdo.getFieldName(), tdo.getKeyRuleType(), tdo.getKeyRule(), tdo.getValueRule());
		}

		for(ParseFormatTDO tdo : parseFormatsTDO)
		{
			projectApp.putInputParseFormat(tdo.getIndex(), tdo.getInputParseType(), tdo.getFormula());
		}

		projectApp.setLastTimeOperator(operator);

		{
			InputParseApi fc = this.feignClientBuilder.forType(InputParseApi.class, InputParseApi.makeId(appId)).build();
			// make the call
			fc.setFormats(parseFormatsTDO);
		}

		{
			InputVerifyApi fc = this.feignClientBuilder.forType(InputVerifyApi.class, InputVerifyApi.makeId(appId)).build();
			// make the call
			fc.setVerify(verify);
		}

		{
			FieldVerifyApi fc = this.feignClientBuilder.forType(FieldVerifyApi.class, FieldVerifyApi.makeId(appId)).build();
			// make the call
			fc.setFields(fieldRulesTDO);
		}

		projectApp = projectAppRepository.save(projectApp);

		return ResponseEntity.ok(projectApp);
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
	@PostMapping("/deploy")
	ResponseEntity deployStream(@RequestParam("appId") String appId, @RequestParam("procedure") String procedure, @RequestParam("operator") String operator)
	{
		// uncheck parameters

		/*Optional<Project> op = projectRepository.findById(appId);

		if(!op.isPresent())
			return ResponseEntity.status(ResultCode.PROJECT_EXISTS).build();

		// basic
		Project project = op.get();
		assert project != null;

		Map<String, String> properties = new HashMap<>();
		properties.put(verificationConfiguration.getTopicProperty(), project.getTopic());
		properties.put(verificationConfiguration.getVerifyProperty(), Boolean.toString(project.getVerify()));

		// input parse
		// project.parse.formats[key] = value
		List<InputParseFormat> inputParseFormats = this.inputParseFormatRepository.withQueryFindByProjectId(appId);
		for(int i = 0; i < inputParseFormats.size(); ++i)
		{
			InputParseFormat o = inputParseFormats.get(i);
			assert o != null;
			//String key1 = verificationConfiguration.getFormatKeyPrefix() + i;
			//properties.put(key1, o.getInputParseType().name());
			//String key2 = verificationConfiguration.getFormatValuePrefix() + i;
			//properties.put(key2, o.getRegex());

		}

		// rule
		List<InputFieldRule> inputFieldRules = this.inputFieldRuleRepository.withQueryFindByProjectId(appId);
		for(int i = 0; i < inputFieldRules.size(); ++i)
		{
			InputFieldRule o = inputFieldRules.get(i);
			assert o != null;
			//String key1 = verificationConfiguration.getFormatKeyPrefix() + i;
			//properties.put(key1, o.getKeyRuleType().name());
			//String key2 = verificationConfiguration.getFormatValuePrefix() + i;
			//properties.put(key2, o.getKeyRule());

		}

		return dispatchApi.deployStream(appId, procedure, properties);*/

		//return dispatchApi.deploy(id, type);
		return ResponseEntity.ok().build();
	}

	/**
	 * 部署task
	 */
	/*@PostMapping("/deploy")
	ResponseEntity deployTask(@RequestParam("appId") String appId, @RequestParam("type") String type, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}*/

	/**
	 * 部署app
	 */
	/*@PostMapping("/deploy")
	ResponseEntity deployApp(@RequestParam("appId") String appId, @RequestParam("type") String type, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}*/


}
