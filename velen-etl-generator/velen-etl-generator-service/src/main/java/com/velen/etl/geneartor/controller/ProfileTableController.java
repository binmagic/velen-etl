package com.velen.etl.geneartor.controller;

import com.velen.etl.generator.tdo.EventTableTDO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile/generator")
public class ProfileTableController
{
	/**
	 * 创建profile表
	 */
	@PostMapping("/profile/generator/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 创建profile以其它profile为模板
	 */
	@PostMapping("/profile/generator/create-like")
	ResponseEntity createLike(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 删除profile表
	 */
	@PostMapping("/profile/generator/drop")
	ResponseEntity drop(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 更新profile表
	 */
	@PostMapping("/profile/generator/update")
	ResponseEntity update(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

	/**
	 * 返回已经创建的profile表
	 */
	@PostMapping("/profile/generator/get")
	ResponseEntity get(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}

}
