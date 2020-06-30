package com.velen.etl.dispatcher.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dispatcher:
 *   dataflow:
 *     uri: http://192.168.1.30:9393
 *     repository: file://root/scdf/
 *     streams:
 *       - 1.jar
 *       - 2.jar
 *       - 3.jar
 *     definitions:
 *       test: http | log
 */
/*@Deprecated
@Configuration
@ConfigurationProperties(prefix = "dispatcher.dataflow", ignoreInvalidFields = true)
@Setter
@Getter
public class DataFlowConfiguration
{
	private String uri;

	// apps URI <repositoryURI + streamsURI>
	private String repositoryURI;
	private List<String> apps = new ArrayList<>();
	// Definitions of Stream of DataFlow
	private Map<String, String> definitions = new HashMap<>();




	public String getRepository()
	{
		return repositoryURI;
	}

	public void setRepository(String repositoryURI)
	{
		this.repositoryURI = repositoryURI;
	}
}*/
