package com.velen.etl.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.dataflow.rest.client.DataFlowTemplate;
//import org.springframework.cloud.dataflow.rest.util.HttpClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
//import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.net.URI;
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
@Configuration
@ConfigurationProperties(prefix = "dispatcher.dataflow", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="dispatcher.dataflow", name = "uri")
//@EnableAutoConfiguration(exclude = {DataFlowClientAutoConfiguration.class, SimpleTaskAutoConfiguration.class})
/* 这个怎么弄，加上 ConditionalOnProperty , EnableAutoConfiguration 不能启作用 */
@Setter
@Getter
@RefreshScope
@Deprecated // 不使用这种方式了，使用 DataFlowClientAutoConfiguration 的方式注册
public class DataFlowConfiguration
{
	//public static String prefix = "dispatcher.dataflow";

	// generate DataFlowTemplate
	private String uri;
	private String username;
	private String password;

	// apps URI <repositoryURI + streamsURI>
	//private String repositoryURI;
	//private List<String> apps = new ArrayList<>();
	//private Map<String, String> apps = new HashMap<>();
	// Definitions of Stream of DataFlow
	// <type, definitions>
	//private Map<String, String> streams = new HashMap<>();
	// <type, definitions>
	//private Map<String, String> tasks = new HashMap<>();


	/*public String getRepository()
	{
		return repositoryURI;
	}

	public void setRepository(String repositoryURI)
	{
		this.repositoryURI = repositoryURI;
	}*/

	// TODO: 作废，使用 DataFlowClientAutoConfiguration 的方式注册
	/*@Bean(name = "dataFlowTemplate")
	//@Scope(value = "refresh")
	@ConditionalOnBean(value = {DataFlowConfiguration.class})
	public DataFlowTemplate dataFlowTemplate()
	{
		final URI targetUri = URI.create(uri);

		final RestTemplate restTemplate = DataFlowTemplate.getDefaultDataflowRestTemplate();
		final HttpClientConfigurer httpClientConfigurer = HttpClientConfigurer.create(targetUri);
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password))
			httpClientConfigurer.basicAuthCredentials(username, password);
		restTemplate.setRequestFactory(httpClientConfigurer.buildClientHttpRequestFactory());

		final DataFlowTemplate dataFlowTemplate = new DataFlowTemplate(targetUri, restTemplate);

		return dataFlowTemplate;
	}*/
}
