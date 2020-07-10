package com.velen.etl.generator.api;

import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import com.velen.etl.generator.tdo.TableMetadataTDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("velen-etl-generator")
public interface TableMetadataApi
{
	/**
	 * 测试用
	 */
	@RequestMapping("/meta/generator/test/{arg}")
	String test(@PathVariable("arg") String arg);

	/**
	 * 创建 app database
	 */
	@PostMapping("/meta/generator/create-app")
	ResponseEntity createApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 删除 app database
	 */
	@PostMapping("/meta/generator/drop-app")
	ResponseEntity dropApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 创建元事件表
	 */
	@PostMapping("/meta/generator/create-table")
	//ResponseEntity createMeta(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO table);
	ResponseEntity createTable(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO tableTDO);

	/**
	 * 同步元事件表
	 */
	@PostMapping("/meta/generator/update-table")
	ResponseEntity updateTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO);

	/**
	 * 获取元事件表
	 */
	@PostMapping("/meta/generator/get-table")
	//TableMetadataTDO getMeta(@RequestParam("appId") String appId, @RequestParam("business") String business, @RequestParam("operator") String operator);
	TableMetadataTDO getTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestParam("operator") String operator);

	/**
	 * 增加表属性
	 */
	@PostMapping("/meta/generator/add-properties")
	ResponseEntity addProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO);

	/**
	 * 删除表属性
	 */
	@PostMapping("/meta/generator/remove-properties")
	ResponseEntity removeProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO);

	/**
	 * 更新表属性
	 */
	@PostMapping("/meta/generator/update-properties")
	ResponseEntity updateProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO);

}
