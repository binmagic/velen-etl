package com.velen.etl.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * project:
 *   formats:
 *     - type: JSON
 *       formula: ssss
 *     - type: REGEX
 *       formula: sssss
 */
@Configuration
@ConfigurationProperties(prefix = "project", ignoreInvalidFields = true)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RefreshScope
public class InputParseFormatConfiguration
{
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	@RefreshScope
	public static class ParseFormatConfiguration
	{
		private int type;
		private String formula;

		@Override
		public boolean equals(Object o)
		{
			if(this == o) return true;
			if(o == null || getClass() != o.getClass()) return false;
			ParseFormatConfiguration that = (ParseFormatConfiguration)o;
			return type == that.type &&
					Objects.equals(formula, that.formula);
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(type, formula);
		}
	}

	List<ParseFormatConfiguration> formats = new ArrayList<>();
}
