package com.velen.etl.dataflow.processor.input.verify.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Validated
@Configuration
@ConfigurationProperties(value = "app", ignoreInvalidFields = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputVerifyProperties
{
	/*@AllArgsConstructor
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
	}*/

	//private List<Field> fields = new ArrayList<>();

	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	@RefreshScope
	public static class Property
	{
		private String name;
		private String type;
		private int index;
		//private boolean require; // 这个可能没啥用, 或是区分出来必验证的字段
	}

	private String appId;
	private String business;
	private boolean verify;

	// 通过不同的 topic 区分 event, profile, 暂时启动独立的处理 stream
	// index of list is property index
	//private Map<String, List<Property>> metas = new HashMap<>();
	//private List<Property> properties = new ArrayList<>();

	/*public InputVerifyProperties setProperties(String name, List<Property> properties)
	{
		this.metas.putIfAbsent(name, properties);
		return this;
	}*/

	// name, property
	private Map<String, Property> properties = new HashMap<>();
}
