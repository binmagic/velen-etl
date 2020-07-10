package com.velen.etl.verification.properties;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "test")
//@NacosConfigurationProperties(dataId = "test", autoRefreshed = true)
@Configuration
@PropertySource("classpath:test.properties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestConfiguration
{

	//@NacosValue(value = "${people.count:0}", autoRefreshed = true)
	@Value(value = "${test.count}")
	private String count;

	//@Value(value = "${test.lists}")
	private List<Integer> lists = new ArrayList<>();

	private Map<String, String> maps = new HashMap<>();

	@NacosConfigListener(dataId = "test", timeout = 500)
	public void onChange(String newContent) throws Exception
	{
		System.out.println("onChange : " + newContent);
	}

	@Override
	public String toString()
	{
		return super.toString();
	}
}
