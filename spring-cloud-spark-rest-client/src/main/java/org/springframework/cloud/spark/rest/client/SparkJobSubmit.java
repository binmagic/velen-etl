package org.springframework.cloud.spark.rest.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SparkJobSubmit
{
	@Getter
	@Setter
	@NoArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	static class SparkProperties
	{

		@JsonProperty(value = "spark.jars")
		private String jars;
		@JsonProperty(value = "spark.app.name")
		private String appName;
		@JsonProperty(value = "spark.master")
		private String master;
		private Map<String, String> platformProperties = new HashMap<>();
	}

	private String action;
	private String appResource;
	private List<String> appParameters;
	private String clientSparkVersion;

	private String mainClass;
	private Map<String,String> environmentVariables;
	private SparkProperties sparkProperties;


}
