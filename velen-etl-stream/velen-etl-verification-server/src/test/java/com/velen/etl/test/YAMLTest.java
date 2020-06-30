package com.velen.etl.test;

import com.alibaba.fastjson.JSONObject;
import com.velen.etl.verification.entity.InputParseFormat;
import com.velen.etl.verification.entity.VerifyEnum;
import org.junit.Test;
import org.springframework.boot.json.YamlJsonParser;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class YAMLTest
{
	@Test
	public void testGen() throws Exception
	{
		InputParseFormat ipf = new InputParseFormat(new Random().nextInt(), "a", VerifyEnum.InputParseType.NONE, "", "");
		JSONObject jsonObject = (JSONObject)JSONObject.toJSON(ipf);

		Map<String, Object> map = new HashMap<>();
		map.put("fuck2", "111");

		//List<String> list = new ArrayList<>();
		//list.add("s");
		//list.add("b");
		//map.put("fuck", list);
		Yaml yaml = new Yaml();
		yaml.setName("hahahaa");
		String a = yaml.dumpAs(map, org.yaml.snakeyaml.nodes.Tag.NULL, DumperOptions.FlowStyle.FLOW);
		String b = yaml.dump(jsonObject);

		Map<String, Object> innerMap = jsonObject.getInnerMap();
		String innerYaml = yaml.dumpAsMap(innerMap);

		YamlJsonParser ysp = new YamlJsonParser();
		Map<String, Object> parseMap = ysp.parseMap(jsonObject.toJSONString());

		yaml.dump(map, new FileWriter("stu.yaml"));

	}

	@Test
	public void testReaderYaml() throws FileNotFoundException
	{
		Yaml yaml = new Yaml();
		Map<String, Object> loaded = yaml.load(new FileReader("/Users/bianxueming/WorkFolder/velen-etl/parse.yaml"));
		String string = yaml.toString();

	}
}
