package com.velen.etl.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "project", ignoreInvalidFields = true)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RefreshScope
public class ProjectConfiguration
{
	private String topic;
	private Boolean verify;

	private String dataId;
	private String group;
}
