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
	private String insertSql;
}
