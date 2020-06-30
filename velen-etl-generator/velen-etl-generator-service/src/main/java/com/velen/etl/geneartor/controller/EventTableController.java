package com.velen.etl.geneartor.controller;

import com.velen.etl.generator.tdo.EventTableTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/event/generator")
public class EventTableController
{
	/**
	 * 创建元事件表
	 */
	@PostMapping("/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 创建元事件表以其它事件表为模板
	 */
	@PostMapping("/create-like")
	ResponseEntity createLike(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 删除元事件表
	 */
	@PostMapping("/drop")
	ResponseEntity drop(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 更新元事件表
	 */
	@PostMapping("/update")
	ResponseEntity update(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 返回已经创建的元事件表
	 */
	@PostMapping("/get")
	ResponseEntity get(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 数据同步
	 * @param reload true: 加载所有，false：新创建的
	 */
	/*@PostMapping("/event/generator/synchronize")
	List<TableDescTDO> synchronize(@RequestParam("appId") String appId, @RequestParam("reload") Boolean reload, @RequestParam("operator") String operator)
	{

	}*/
}
