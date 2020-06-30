package com.velen.etl.stream.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties("unpack")
public class UnpackProperties
{

	private Integer corePoolSize = 10;
	private Integer keepAliveSeconds = 3;
	private Integer maxPoolSize = 10;
	private Integer queueCapacity = 10000;
	private String rejectedExecutionHandler = "java.util.concurrent.ThreadPoolExecutor$CallerRunsPolicy";

	@NotNull
	public Integer getCorePoolSize()
	{
		return corePoolSize;
	}

	public void setCorePoolSize(Integer corePoolSize)
	{
		this.corePoolSize = corePoolSize;
	}

	@NotNull
	public Integer getKeepAliveSeconds()
	{
		return keepAliveSeconds;
	}

	public void setKeepAliveSeconds(Integer keepAliveSeconds)
	{
		this.keepAliveSeconds = keepAliveSeconds;
	}

	@NotNull
	public Integer getMaxPoolSize()
	{
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize)
	{
		this.maxPoolSize = maxPoolSize;
	}

	@NotNull
	public Integer getQueueCapacity()
	{
		return queueCapacity;
	}

	public void setQueueCapacity(Integer queueCapacity)
	{
		this.queueCapacity = queueCapacity;
	}

	@NotNull
	public String getRejectedExecutionHandler()
	{
		return rejectedExecutionHandler;
	}

	public void setRejectedExecutionHandler(String rejectedExecutionHandler)
	{
		this.rejectedExecutionHandler = rejectedExecutionHandler;
	}
}


