package com.velen.etl.verification.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * input:
 *   parse:
 *     formats:
 *       - type: JSON
 *         formula: content
 *       - type: REGEX
 *         formula: content
 */
@Deprecated
@Configuration
@ConfigurationProperties(prefix = "input.parse", ignoreInvalidFields = true)
@Setter
@Getter
public class InputParseFormatConfiguration
{
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	public static class ParseFormatConfiguration
	{
		private int type;
		private String formula;
	}

	List<ParseFormatConfiguration> formats = new ArrayList<>();

}
