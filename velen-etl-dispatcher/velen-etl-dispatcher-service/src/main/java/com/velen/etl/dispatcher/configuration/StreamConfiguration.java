package com.velen.etl.dispatcher.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "dispatcher.stream", ignoreInvalidFields = true)
@Setter
@Getter
@Deprecated
public class StreamConfiguration
{
	/*static class DataFlowStream
	{
		private List<String> definitions = new ArrayList<>();
	}*/

	//private Map<String, String> definitions = new HashMap<>();

}
