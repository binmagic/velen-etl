package com.velen.etl.dataflow.processor.assemble.meta.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;


@Validated
@Configuration
@ConfigurationProperties(value = "app.assemble", ignoreInvalidFields = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssembleMetaProperties
{
	private String appId;
	private String business;

	/**
	 * 
	 */

	//private List<Field> fields = new ArrayList<>();
}
