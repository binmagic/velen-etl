package org.springframework.cloud.spark.rest.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SparkJobSubmitBuilder
{
	private String action;
	private String appResource;
	private List<String> appParameters;
	private String clientSparkVersion;

	private String mainClass;
	private Map<String,String> environmentVariables;

	private String appName;
	private String master;
	private Map<String, String> platformProperties;
	//private SparkJobSubmit.SparkProperties sparkProperties;


	public SparkJobSubmitBuilder setAction(String action)
	{
		this.action = action;
		return this;
	}

	public SparkJobSubmitBuilder setAppResource(String appResource)
	{
		this.appResource = appResource;
		return this;
	}

	public SparkJobSubmitBuilder setAppParameters(List<String> appParameters)
	{
		this.appParameters = appParameters;
		return this;
	}

	public SparkJobSubmitBuilder addAppParameter(String parameter)
	{
		if(this.appParameters == null)
			this.appParameters = new ArrayList<>();

		this.appParameters.add(parameter);
		return this;
	}

	public SparkJobSubmitBuilder setClientSparkVersion(String clientSparkVersion)
	{
		this.clientSparkVersion = clientSparkVersion;
		return this;
	}

	public SparkJobSubmitBuilder setMainClass(String mainClass)
	{
		this.mainClass = mainClass;
		return this;
	}

	public SparkJobSubmitBuilder setEnvironmentVariables(Map<String, String> environmentVariables)
	{
		this.environmentVariables = environmentVariables;
		return this;
	}

	public SparkJobSubmitBuilder addEnvironmentVariable(String key, String value)
	{
		if(this.environmentVariables == null)
			this.environmentVariables = new HashMap<>();

		this.environmentVariables.put(key, value);
		return this;
	}

	public SparkJobSubmitBuilder setAppName(String appName)
	{
		this.appName = appName;
		return this;
	}

	public SparkJobSubmitBuilder setMaster(String master)
	{
		this.master = master;
		return this;
	}

	public SparkJobSubmitBuilder setPlatformProperties(Map<String, String> platformProperties)
	{
		this.platformProperties = platformProperties;
		return this;
	}

	public SparkJobSubmitBuilder addPlatformProperty(String key, String value)
	{
		if(this.platformProperties == null)
			this.platformProperties = new HashMap<>();

		this.platformProperties.put(key, value);
		return this;
	}

	public SparkJobSubmit build()
	{
		if(this.action == null)
		{
			throw new IllegalArgumentException("action is null!");
		}
		if(this.appResource == null)
		{
			throw new IllegalArgumentException("appResource is null!");
		}
		if(this.mainClass == null)
		{
			throw new IllegalArgumentException("mainClass is null!");
		}
		if(this.appName == null)
		{
			throw new IllegalArgumentException("appName is null!");
		}
		if(this.master == null)
		{
			throw new IllegalArgumentException("master is null!");
		}
		SparkJobSubmit submit = new SparkJobSubmit();
		submit.setAction(this.action);
		submit.setAppResource(this.appResource);
		submit.setMainClass(this.mainClass);
		if(clientSparkVersion == null)
		{
			submit.setClientSparkVersion("2.4.5");
		}
		else
		{
			submit.setClientSparkVersion(clientSparkVersion);
		}

		if(this.appParameters != null || !this.appParameters.isEmpty())
			submit.setAppParameters(this.appParameters);

		if(this.environmentVariables != null || !this.environmentVariables.isEmpty())
			submit.setEnvironmentVariables(this.environmentVariables);

		SparkJobSubmit.SparkProperties sparkProperties = new SparkJobSubmit.SparkProperties();
		sparkProperties.setAppName(this.appName);
		sparkProperties.setJars(this.appResource);
		sparkProperties.setMaster(this.master);

		if(this.platformProperties != null || !this.platformProperties.isEmpty())
			sparkProperties.setPlatformProperties(this.platformProperties);

		submit.setSparkProperties(sparkProperties);

		return submit;
	}
}
