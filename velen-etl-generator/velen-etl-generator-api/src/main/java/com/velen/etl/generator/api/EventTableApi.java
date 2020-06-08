package com.velen.etl.generator.api;

import com.velen.etl.generator.dto.EventTableTDO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("velen-etl-generator")
public interface EventTableApi
{
	/**
	 * 创建元事件表
	 */
	@PostMapping("/event/generator/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator);

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
	@PostMapping("/event/generator/get")
	ResponseEntity get(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator);


	/**
	 * 服务间创建order
	 */
	//ResponseEntity createOrderIfNoExist();

	/**
	 * 数据同步
	 */
	@PostMapping("/event/generator/synchronize")
	void synchronize();

}
