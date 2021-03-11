package com.velen.etl.dispatcher.service.impl;

import com.velen.etl.dispatcher.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.spark.rest.client.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SparkServiceImpl implements SparkService
{
	@Autowired
	private SparkTemplate sparkTemplate;

	@Override
	public void create(String appName, String appResource, List<String> appParameters, Map<String, String> environmentVariables, Map<String, String> platformProperties)
	{
		SparkJobSubmit submit = new SparkJobSubmitBuilder()
				.setAppName(appName)
				.setAppResource(appResource)
				.setAction("CreateSubmissionRequest")
				.setMainClass("")
				.setMaster("")
				.setAppParameters(appParameters)
				.setEnvironmentVariables(environmentVariables)
				.setPlatformProperties(platformProperties)
				.build();

		this.sparkTemplate.getHiddenOperations().create(submit);
	}

	@Override
	public int status(String submissionId)
	{
		SparkSubmissionSpecificStatus status = this.sparkTemplate.getHiddenOperations().status(submissionId);
		return Status.parse(status.getDriverState()).getRetCode();
	}

	@Override
	public void kill(String submissionId)
	{
		this.sparkTemplate.getHiddenOperations().kill(submissionId);
	}
}
