package com.velen.etl.verification.service.impl;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.velen.etl.common.exception.InvalidSynchronizeException;
import com.velen.etl.verification.entity.InputFieldRule;
import com.velen.etl.verification.entity.InputParseFormat;
import com.velen.etl.verification.entity.Project;
import com.velen.etl.verification.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Deprecated
@Service
public class ProjectServiceImpl implements ProjectService
{
	@Autowired
	private NacosConfigManager nacosConfigManager;

	//@Autowired
	//private DispatchApi dispatchApi;

	@Override
	//public boolean updateProject(String appId, String prefix, String group, Project project)
	public boolean updateProject(String dataId, String group, Project project, boolean restful) throws InvalidSynchronizeException
	{
		Map<String, Object> verify = new LinkedHashMap<>();
		verify.put("topic", project.getTopic());
		verify.put("verify", project.getVerify());
		verify.put("dataId", dataId);
		//verify.put("group", project.getGroup());

		//verify.put("type", "yaml");

		Map<String, Object> root = new LinkedHashMap<>();
		root.put("project", verify);

		Yaml yaml = new Yaml();
		String content = yaml.dumpAsMap(root);

		StringBuilder sb = new StringBuilder();
		sb.append(content);
		sb.append(", type=yaml");
		//String content = sb.toString();

		//return nacosConfigManager.getConfigService().publishConfig(dataId, group, sb.toString());
		return true;
	}


	/**
	 * 输出YAML内容
	 * input:
	 *   parse:
	 *     formats:
	 *       - type: JSON
	 *         formula: ssss
	 *       - type: REGEX
	 *         formula: sssss
	 */
	@Override
	public boolean updateParseFormats(String appId, String prefix, String group, List<InputParseFormat> parseFormats, boolean restful)
	{
		List<Object> list = new LinkedList<>();
		for(InputParseFormat o : parseFormats)
		{
			list.add(o.toMap());
		}

		Map<String, Object> fmts = new LinkedHashMap<>();
		fmts.put("formats", list);

		Map<String, Object> root = new LinkedHashMap<>();
		root.put("project", fmts);

		/*Map<String, Object> parse = new LinkedHashMap<>();
		parse.put("parse", formats);

		Map<String, Object> input = new LinkedHashMap<>();
		input.put("input", parse);*/

		Yaml yaml = new Yaml();
		String content = yaml.dumpAsMap(root);

		try
		{
			nacosConfigManager.getConfigService().publishConfig("a", "DEFAULT_GROUP", content);
		}
		catch(NacosException e)
		{

		}

		return true;
	}

	@Override
	public boolean updateFieldRules(List<InputFieldRule> fieldRules, boolean restful)
	{
		return false;
	}
}
