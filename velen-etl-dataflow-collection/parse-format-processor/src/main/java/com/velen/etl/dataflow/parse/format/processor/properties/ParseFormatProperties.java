package com.velen.etl.dataflow.parse.format.processor.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
@Configuration
@ConfigurationProperties(value = "app.input", ignoreInvalidFields = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParseFormatProperties
{
	// 这个可能需要放到第二个流中
	//private boolean verify;
	//private String name;

	private Map<String, String> formats;
}
