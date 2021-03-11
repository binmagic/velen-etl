package com.velen.etl;


import java.util.HashMap;
import java.util.Map;

public enum ResultCode
{
	FATAL_ERROR(999, "致命错误"),


	INVALID_PARAMETERS(1000, "参数无效"), // parameter invalid

	VERIFICATION_PROJECT_EXISTS(2000, "项目已经存在"),
	VERIFICATION_PROJECT_NOT_EXISTS(2001, "项目不存在"),

	GENERATOR_DATABASE_EXISTS(3000, "库已经存在"),
	GENERATOR_DATABASE_NOT_EXISTS(3001, "库不存在"),
	GENERATOR_DATABASE_CREATE_FAILURE(3002, "库创建失败"),
	GENERATOR_TABLE_EXISTS(3003, "元数据表已经存在"),
	GENERATOR_TABLE_CREATE_FAILURE(3004, "元数据表创建失败"),
	GENERATOR_TABLE_NOT_EXISTS(3005, "元数据表不存在"),
	GENERATOR_COLUMN_EXISTS(3006, "属性已经存在"),
	GENERATOR_COLUMN_CREATE_FAILURE(3007, "属性创建失败"),


	DISPATCH_UPDATE_STREAM_FAILURE(4000, "更新stream失败"), // stream flow update failure
	DISPATCH_UPDATE_TASK_FAILURE(4001, "更新task失败"), // task flow update failure

	DISPATCH_INVALID_PROCESS(4010, "无效的工序"), // process of input is invalid
	DISPATCH_INVALID_PROCEDURE(4011, "无效的流程"), // procedure of input is invalid
	DISPATCH_UNSUPPORTED_PLATFORM(4012, "不支持的平台"), // platform of input is unsupported*/

	DISPATCH_JOB_UNDEPLOY(5000, "JOB 未部署"),
	DISPATCH_JOB_RUNNING(5001, "JOB 正在运行中"),
	DISPATCH_JOB_ERROR(5002, "JOB 发生错误"),
	DISPATCH_JOB_FINISHED(5003, "JOB 运行完成"),


	;


	static Map<Integer, ResultCode> AllCodes = new HashMap<>();
	static
	{
		for(ResultCode o : ResultCode.values())
		{
			AllCodes.put(o.code(), o);
		}
	}

	public static ResultCode valueOf(int value)
	{
		return AllCodes.getOrDefault(value, FATAL_ERROR);
	}

	private int value;
	private String msg;

	public int code()
	{
		return value;
	}
	public String message()
	{
		return msg;
	}

	ResultCode(int value, String msg)
	{
		this.value = value;
		this.msg = msg;
	}

}
