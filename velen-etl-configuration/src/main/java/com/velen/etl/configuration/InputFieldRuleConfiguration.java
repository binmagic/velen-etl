package com.velen.etl.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * project:
 *   fields:
 *     - name: assss
 *       type: JSON
 *       key-rule: ssss
 *       value-rule: ssss
 *     - name: dddd
 *       type: JSON
 *       key-rule: bbbb
 *       value-rule: 4444
 */
@Configuration
@ConfigurationProperties(prefix = "project", ignoreInvalidFields = true)
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RefreshScope
public class InputFieldRuleConfiguration
{
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	@RefreshScope
	public static class FieldRuleConfiguration
	{
		private String name;
		private int type;
		private String keyRule;
		private String valueRule;
	}

	private List<FieldRuleConfiguration> fields = new ArrayList<>();
}
