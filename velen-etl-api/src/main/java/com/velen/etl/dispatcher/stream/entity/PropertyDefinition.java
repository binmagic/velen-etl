package com.velen.etl.dispatcher.stream.entity;

/**
 * 配置的启动参数的属性定义
 * 暂时先放到这里在，是否要放到 configuration 里在说
 */
@Deprecated // 移动到 configuration
public class PropertyDefinition
{
	public static String PROPERTY_TOPIC = "topic";
	public static String PROPERTY_VERIFY = "verify";
	//public static String PROPERTY_TOPIC = "topic";
}
