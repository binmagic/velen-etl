package com.velen.etl.collections.dataflow.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Validated
@ConfigurationProperties("parse")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParseProperties
{
	// 这个可能需要放到第二个流中
	private boolean verify;
	//private String name;

	private Map<String, String> inputFilter;
}
