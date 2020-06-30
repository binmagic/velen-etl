package com.velen.etl.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * dispatcher:
 *   flow:
 *     name: test
 *     apps:
 *       test: http | log
 *     streams:
 *       test: http | log
 *     tasks:
 *       test: http | log
 */
@Configuration
@ConfigurationProperties(prefix = "dispatcher.flow", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="dispatcher.flow", name = "name")
@Setter
@Getter
@RefreshScope
public class DispatchFlowConfiguration
{
	private String name;
	// apps URI <repositoryURI + streamsURI>
	//private String repositoryURI;
	//private List<String> apps = new ArrayList<>();
	private Map<String, String> apps = new HashMap<>();
	// Definitions of Stream of DataFlow
	// <type, definitions>
	private Map<String, String> streams = new HashMap<>();
	// <type, definitions>
	private Map<String, String> tasks = new HashMap<>();
}
