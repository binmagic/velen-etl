package com.velen.etl.dataflow.sink.hive.properties;

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
@ConfigurationProperties(value = "app", ignoreInvalidFields = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaSinkProperties
{
	private String appId;
	private String business;
	//public static String inputDestination;

	// 这个可能需要放到第二个流中
	//private boolean verify;
	//private String name;

	//private String inputDestination;
	private Map<String, String> formats;
}
