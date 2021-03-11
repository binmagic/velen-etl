package com.velen.etl.dataflow.sink.sql.hive.controller;

import com.velen.etl.dataflow.sink.sql.hive.service.SqlSinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stream")
public class SqlSinkController
{
	@Autowired
	SqlSinkService sqlSinkService;

	@PostMapping("/sink/test2/{msg}")
	ResponseEntity test(@PathVariable("sql") String sql)
	{
		sqlSinkService.execute(sql);
		return ResponseEntity.ok(sql);
	}
}
