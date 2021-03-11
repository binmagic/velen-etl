package com.velen.etl.dataflow.processor.assemble.meta.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.processor.assemble.meta.properties.AssembleMetaProperties;
import com.velen.etl.dataflow.processor.assemble.meta.service.AssembleMetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(value = {AssembleMetaProperties.class})
public class AssembleMetaServiceImpl implements AssembleMetaService
{
	@Autowired
	private AssembleMetaProperties assembleMetaProperties;

	@Autowired
	private Processor processor;

	final String DB_NAME_PREFIX = "db_name";
	final String TABLE_NAME_PREFIX = "table_name";
	final String DISTINCT_ID_PREFIX = "distinct_id";
	final String CREATE_TIME_PREFIX = "create_time";
	final String EVENT_PREFIX = "event";
	final String PROJECT_PREFIX = "project";
	final String PROPERTIES_PREFIX = "Properties";
	final String PARTITIONED_EVENT_PREFIX = "partitioned_event";
	final String PARTITIONED_TIME_PREFIX = "partitioned_time";

	//final String key1 = "talbe_name";

	@Override
	public String test(String message)
	{
		//return transform(message).toString();
		return message;
	}

	@Override
	public AssembleMetaProperties setAppId(String appId)
	{
		assembleMetaProperties.setAppId(appId);
		return assembleMetaProperties;
	}

	@Override
	public AssembleMetaProperties setBusiness(String business)
	{
		assembleMetaProperties.setBusiness(business);
		return assembleMetaProperties;
	}

	/**
	 * 通道消息转换
	 * INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
	 */
	@StreamListener(Processor.INPUT)
	//@SendTo(Processor.OUTPUT)
	public Object transform(JSONObject payload)
	{
		String pattern = "INSERT INTO {0}.{1} ({2}) values ({3}) PARTITION (p_event={}}, p_time={}});";

		StringBuilder keys = new StringBuilder();
		StringBuilder values = new StringBuilder();

		keys.append(DISTINCT_ID_PREFIX);
		values.append(payload.get(DISTINCT_ID_PREFIX));

		keys.append(",");
		values.append(",");

		keys.append(CREATE_TIME_PREFIX);
		values.append(payload.get(CREATE_TIME_PREFIX));

		keys.append(",");
		values.append(",");

		keys.append(EVENT_PREFIX);
		values.append(payload.get(EVENT_PREFIX));

		keys.append(",");
		values.append(",");

		keys.append(PROJECT_PREFIX);
		values.append(payload.get(PROJECT_PREFIX));

		JSONObject jsonProperties = payload.getJSONObject(PROPERTIES_PREFIX);
		if(jsonProperties != null)
		{
			Set<Map.Entry<String, Object>> sets = jsonProperties.entrySet();
			for(Map.Entry<String, Object> entry : sets)
			{
				keys.append(",");
				values.append(",");

				keys.append(entry.getKey());
				values.append(entry.getValue());
			}
		}

		String sql = MessageFormat.format(pattern, payload.get(DB_NAME_PREFIX), payload.get(TABLE_NAME_PREFIX)
		, keys.toString(), values.toString(), payload.get(PARTITIONED_EVENT_PREFIX), payload.get(PARTITIONED_TIME_PREFIX));

		processor.output().send(MessageBuilder.withPayload(sql).build());

		return sql;
	}
}
