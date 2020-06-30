package com.velen.etl.verification.service;


import com.velen.etl.common.exception.InvalidSynchronizeException;
import com.velen.etl.verification.entity.InputFieldRule;
import com.velen.etl.verification.entity.InputParseFormat;
import com.velen.etl.verification.entity.Project;

import java.util.List;

public interface ProjectService
{
	//boolean updateProject(String appId, String prefix, String group, Project project);
	boolean updateProject(String dataId, String group, Project project, boolean restful) throws InvalidSynchronizeException;
	boolean updateParseFormats(String appId, String prefix, String group, List<InputParseFormat> parseFormats, boolean restful);
	boolean updateFieldRules(List<InputFieldRule> fieldRules, boolean restful);
}
