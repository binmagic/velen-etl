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
	ResponseEntity createApp(@RequestParam("appId") String appId, @RequestParam("appName") String appName, @RequestParam("operator") String operator);

	/**
	 * 删除 app database
	 */
	@PostMapping("/meta/generator/drop-app")
	ResponseEntity dropApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 用于创建/更新hive表
	 */
	@PostMapping("/meta/generator/alter-hive")
	//ResponseEntity createMeta(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO table);
	ResponseEntity alterHive(@RequestParam("appId") String appId, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 用于创建/更新元事件表
	 * 不操作 HIVE 库
	 */
	@PostMapping("/meta/generator/alter-table")
	//ResponseEntity createMeta(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO table);
	ResponseEntity alterTable(@RequestParam("appId") String appId, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 同步更新元事件表
	 * 只用于更新
	 */
	@PostMapping("/meta/generator/update-table")
	ResponseEntity updateTable(@RequestParam("appId") String appId, @RequestParam("hiveTableType") String hiveTableType, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 修改元事件表
	 */
	//@PostMapping("/meta/generator/alter-table")
	//ResponseEntity alterTable(@RequestParam("appId") String appId, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator);

	/**
	 * 获取元事件表
	 */
	@PostMapping("/meta/generator/get-table")
	//TableMetadataTDO getMeta(@RequestParam("appId") String appId, @RequestParam("business") String business, @RequestParam("operator") String operator);
	TableMetadataTDO getTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestParam("operator") String operator);

	/**
	 * 获取元事件表
	 */
	@PostMapping("/meta/generator/get-tables")
	List<TableMetadataTDO> getTables(@RequestParam("appId") String appId, @RequestParam("operator") String operator);

	/**
	 * 增加表属性
	 */
	@Deprecated
	@PostMapping("/meta/generator/add-properties")
	ResponseEntity addProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator);

	/**
	 * 删除表属性(目前不允许更新)
	 */
	@Deprecated
	@PostMapping("/meta/generator/remove-properties")
	ResponseEntity removeProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator);

	/**
	 * 更新表属性(目前不允许更新)
	 */
	@Deprecated
	@PostMapping("/meta/generator/update-properties")
	ResponseEntity updateProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator);

}
