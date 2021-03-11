package com.velen.etl.dispatcher.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class JobSubmitTDO
{
	private String appId; // 不确定这个是否要
	private String appName;
	private String appResource; // DSL或是资源
	private List<String> appParameters = new ArrayList<>(); // app 启动参数
	private Map<String,String> environmentVariables = new HashMap<>(); // 环境变量
	private Map<String, String> platformProperties = new HashMap<>(); // 平台属性
}
