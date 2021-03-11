package com.velen.etl.dataflow.sink.hive;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.velen.etl.dataflow.sink.hive.properties.MetaSinkProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.text.MessageFormat;

@SpringBootApplication(scanBasePackages = {"com.velen.etl"})
@EnableDiscoveryClient
public class App
{
	// 暂时先都认为是先事件
	public static void main(String[] args)
	{
		/*JSONArray jsonArray = new JSONArray();
		JSONArray jsonArray1 = new JSONArray();
		jsonArray1.add("1");
		jsonArray1.add("2");
		jsonArray1.add("3");
		jsonArray1.add("4");

		jsonArray.add("distinct_id");
		jsonArray.add("time");
		jsonArray.add("event");
		jsonArray.add("project");
		jsonArray.add(jsonArray1);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("a", "haha");
		jsonObject.put("b", 0.0f);
		jsonObject.put("c", 1);
		jsonObject.put("d", new MetaSinkProperties("ha", "ha", "ha"));

		String pa = "INSERT {0}, VALUE {1} {2}, {3}";

		String out = MessageFormat.format(pa, jsonObject.get("a"), jsonObject.get("b"), jsonObject.get("c"), jsonObject.get("d"));


		String jsonString = jsonArray.toJSONString();

		for(Object object : jsonArray)
		{
			System.out.println(object.getClass().getSimpleName());
		}*/

		SpringApplication.run(App.class, args);
	}
}
