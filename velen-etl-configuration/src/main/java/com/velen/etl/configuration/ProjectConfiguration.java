package com.velen.etl.configuration;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "project.basic", ignoreInvalidFields = true)
@ConditionalOnProperty(prefix="project.basic", name = "topic")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RefreshScope
public class ProjectConfiguration
{
	//@Validated 用于数据校验
	//@Length(min=14,max=20) 校验规则
	private String topic;
	@Value("${project.basic.verify:false}")
	private Boolean verify;
	//@Value("${project.basic.restful:true}")
	//private Boolean restful;

	//private String dataId;
	//private String group;

	// PROPERTY DEFINITION
	//@Value("${project.basic.topic-property:}")
	//private String topicProperty;
	//private String verifyProperty;
	//private String PROPERTY_TOPIC = "topic";
}
