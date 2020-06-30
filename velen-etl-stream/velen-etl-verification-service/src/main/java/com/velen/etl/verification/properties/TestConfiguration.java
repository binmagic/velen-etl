package com.velen.etl.verification.properties;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "test")
@NacosConfigurationProperties(dataId = "test", autoRefreshed = true)
@Configuration
public class TestConfiguration
{

	@NacosValue(value = "${people.count:0}", autoRefreshed = true)
	//@Value(value = "${people.count}")
	private String count;

	public String getCount()
	{
		return count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

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
