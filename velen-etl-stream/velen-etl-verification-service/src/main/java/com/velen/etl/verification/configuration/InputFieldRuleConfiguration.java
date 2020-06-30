package com.velen.etl.verification.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * input:
 *   field:
 *     rules:
 *       - name: assss
 *         type: JSON
 *         key: ssss
 *         value: ssss
 *       - name: dddd
 *         type: JSON
 *         key: bbbb
 *         value: 4444
 */
@Deprecated
@Configuration
@ConfigurationProperties(prefix = "input.field", ignoreInvalidFields = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

		public String getKey()
		{
			return keyRule;
		}

		public void setKey(String keyRule)
		{
			this.keyRule = keyRule;
		}

		public String getValue()
		{
			return valueRule;
		}

		public void setValue(String valueRule)
		{
			this.valueRule = valueRule;
		}
	}

	private List<FieldRuleConfiguration> rules = new ArrayList<>();
}
