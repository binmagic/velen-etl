package com.velen.etl.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * dispatcher:
 *   data-flow: -- platform
 *     name: test
 *     apps: -- process type
 *       test: http | log -- procedure | definition
 *     streams: -- process type
 *       test: http | log -- procedure | definition
 *     tasks: -- process type
 *       test: http | log -- procedure | definition
 */
@Configuration
@ConfigurationProperties(prefix = "service.dispatcher", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="service.dispatcher", name = "name")
@Setter
@Getter
@RefreshScope
public class DispatchFlowConfiguration
{
	@Configuration
	@Setter
	@Getter
	@RefreshScope
	public static class DataFlowDefinition
	{
		private Map<String, String> apps = new HashMap<>();
		// Definitions of Stream of DataFlow
		// <procedure, definitions>
		private Map<String, String> streams = new HashMap<>();
		// <procedure, definitions>
		private Map<String, String> tasks = new HashMap<>();
	}

	private String name;
	// 用与多种部署方式的切换[dataflow,flink 是否混用，暂时没有定数]
	@Value("${dispatch-platform:dataflow}")
	private String dispatchPlatform;
	private DataFlowDefinition dataFlow;

	public String searchDefinition(String procedure)
	{
		/*String dsl = dataFlow.apps.getOrDefault(procedure, null);
		if(!StringUtils.isEmpty(dsl))
			return dsl;*/

		if(dataFlow == null)
			return null;

		return dataFlow.streams.getOrDefault(procedure, null);
	}

	public String searchDefinition(String process, String procedure)
	{
		throw new UnsupportedOperationException("searchDefinition(String process, String procedure)");
	}

	public String searchDefinition(String platform, String process, String procedure)
	{
		throw new UnsupportedOperationException("searchDefinition(String platform, String process, String procedure)");
	}


	// 废弃的思考过程
	// apps URI <repositoryURI + streamsURI>
	//private String repositoryURI;
	//private List<String> apps = new ArrayList<>();
	//private Map<String, String> apps = new HashMap<>();
	// Definitions of Stream of DataFlow
	// <type, definitions>
	//private Map<String, String> streams = new HashMap<>();
	// <type, definitions>
	//private Map<String, String> tasks = new HashMap<>();
}
