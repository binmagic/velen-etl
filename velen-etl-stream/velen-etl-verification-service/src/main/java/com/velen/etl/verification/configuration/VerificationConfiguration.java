package com.velen.etl.verification.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service.verification", ignoreInvalidFields = true)
//@ConditionalOnProperty(prefix="service.verification", name = "restful")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RefreshScope
public class VerificationConfiguration
{
	@Value("${restful:true}")
	private Boolean restful;





	// PROPERTY DEFINITION
	/*@Value("${topic-property:top}")
	private String topicProperty;
	@Value("${verify-property:verify}")
	private String verifyProperty;
	//private String PROPERTY_TOPIC = "topic";
	@Value("${format-prefix:project.parse.formats.k}")
	private String formatKeyPrefix;
	@Value("${format-prefix:project.parse.formats.v}")
	private String formatValuePrefix;


	@Value("${field-name-property:name}")
	private String fieldNameProperty;
	@Value("${field-key-type-property:type}")
	private String keyRuleTypeProperty;
	@Value("${field-key-rule-property:key-rule}")
	private String keyRuleProperty;
	@Value("${value-value-rule-property:value-rule}")
	private String valueRuleProperty;*/

}
