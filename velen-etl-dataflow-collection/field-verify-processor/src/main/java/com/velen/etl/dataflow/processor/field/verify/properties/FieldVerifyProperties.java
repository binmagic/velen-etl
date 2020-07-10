package com.velen.etl.dataflow.processor.field.verify.properties;

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
@ConfigurationProperties(value = "app.input", ignoreInvalidFields = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldVerifyProperties
{
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	@RefreshScope
	public static class Field
	{
		private String name;
		private int keyType;
		private String keyRule;
		private String valueRule;
	}

	private String appId;
	private String business;
	//private boolean verify;

	private List<Field> fields = new ArrayList<>();
}
