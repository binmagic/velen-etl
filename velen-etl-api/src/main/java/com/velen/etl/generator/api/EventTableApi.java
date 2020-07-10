package com.velen.etl.generator.api;

import com.velen.etl.generator.tdo.EventMetadataTDO;
import com.velen.etl.generator.tdo.EventTableTDO;
import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import javafx.util.Pair;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Deprecated
@FeignClient("velen-etl-generator")
public interface EventTableApi
{
	/**
	 * 测试用
	 */
	@RequestMapping("/event/generator/test/{arg}")
	String test(@PathVariable("arg") String arg);

	/**
	 * 创建元事件表
	 */
	@PostMapping("/event/generator/create")
	//ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator);
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventMetadataTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 创建元事件表以其它事件表为模板
	 */
	@PostMapping("/event/generator/create-like")
	ResponseEntity createLike(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 删除元事件表
	 */
	@PostMapping("/event/generator/drop")
	ResponseEntity drop(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator);

	/**
	 * 更新元事件表
	 */
	@PostMapping("/event/generator/update")
	ResponseEntity update(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 返回已经创建的元事件表
	 */
	/*@PostMapping("/event/generator/get")
	ResponseEntity get(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator);*/


	/**
	 * 数据同步
	 */
	@PostMapping("/event/generator/meta/update-meta")
	//ResponseEntity syncMeta(@RequestParam("appId") String appId, @RequestParam("event") String event, @RequestBody Map<String, Pair<Integer,String>> metas);
	ResponseEntity updateMeta(@RequestParam("appId") String appId, @RequestParam("event") String event, @RequestBody List<PropertyMetadataTDO> properties);

	/**
	 * 数据获取
	 */
	@PostMapping("/event/generator/meta/get-meta")
	Map<String, Pair<Integer,String>> getMeta(@RequestParam("appId") String appId, @RequestParam("event") String event, @RequestParam("operator") String operator);

}
