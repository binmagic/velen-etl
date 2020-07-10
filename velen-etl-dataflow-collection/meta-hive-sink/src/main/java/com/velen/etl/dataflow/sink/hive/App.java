package com.velen.etl.dataflow.sink.hive;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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


		String jsonString = jsonArray.toJSONString();

		for(Object object : jsonArray)
		{
			System.out.println(object.getClass().getSimpleName());
		}*/

		SpringApplication.run(App.class, args);
	}
}
