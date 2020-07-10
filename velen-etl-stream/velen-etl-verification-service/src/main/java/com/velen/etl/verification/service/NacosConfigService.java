package com.velen.etl.verification.service;


import com.alibaba.nacos.api.exception.NacosException;
import com.velen.etl.common.exception.InvalidSynchronizeException;
import com.velen.etl.verification.entity.InputFieldRule;
import com.velen.etl.verification.entity.InputParseFormat;
import com.velen.etl.verification.entity.Project;

import java.util.List;

public interface NacosConfigService
{
	//boolean updateProject(String appId, String prefix, String group, Project project);
	boolean updateProject(String dataId, String group, Project project) throws NacosException;
	boolean updateParseFormats(String appId, String prefix, String group, List<InputParseFormat> parseFormats) throws NacosException;
	boolean updateFieldRules(List<InputFieldRule> fieldRules) throws NacosException;
}
