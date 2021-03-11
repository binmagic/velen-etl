package com.velen.etl.geneartor.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.common.entity.EventMetadata;
import com.velen.etl.common.repository.EventMetadataRepository;
import com.velen.etl.geneartor.service.HiveService;
import com.velen.etl.generator.tdo.EventMetadataTDO;
import com.velen.etl.generator.tdo.EventTableTDO;
import com.velen.etl.generator.tdo.TableMetadataTDO;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/event/generator")
public class EventTableController
{
	@Autowired
	private EventMetadataRepository eventMetadataRepository;

	@Autowired
	private HiveService hiveService;

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg)
	{
		EventMetadata metadata = new EventMetadata("11111", Collections.EMPTY_MAP);

		eventMetadataRepository.insert(metadata);

		return arg;
	}

	/**
	 * 创建元事件表
	 */
	@PostMapping("/create")
	//ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") EventTableTDO tableTDO, @RequestParam("operator") String operator)
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("table") TableMetadataTDO tableTDO, @RequestParam("operator") String operator)
	{
		// check parameter

		String id = tableTDO.getTable();
		Optional<EventMetadata> op = this.eventMetadataRepository.findById(id);

		if(op.isPresent())
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS.code()).body(id);
		}

		//EventMetadata metadata = this.eventMetadataRepository.insert(new EventMetadata(id, tableTDO.getProperties()));

		//if()

		return ResponseEntity.ok().build();
	}

	/**
	 * 创建元事件表以其它事件表为模板
	 */
	@PostMapping("/create-like")
	ResponseEntity createLike(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * 删除元事件表
	 */
	@PostMapping("/drop")
	ResponseEntity drop(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		String id = table;
		this.eventMetadataRepository.deleteById(id);

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
	/*@PostMapping("/get")
	ResponseEntity get(@RequestParam("appId") String appId, @RequestParam("db") String db, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok().build();
	}*/

	/**
	 * 数据同步
	 */
	@PostMapping("/meta/sync-meta")
	ResponseEntity syncMeta(@RequestParam("appId") String appId, @RequestParam("event") String event, @RequestBody Map<String, Pair<Integer, String>> metas)
	{
		// check parameter

		String id = event;
		Optional<EventMetadata> op = this.eventMetadataRepository.findById(id);

		if(op.isPresent())
		{
			// delete immediately
			this.eventMetadataRepository.deleteById(id);
		}

		EventMetadata metadata = new EventMetadata(event, metas);

		this.eventMetadataRepository.insert(metadata);

		return ResponseEntity.ok().build();
	}

	/**
	 * 数据获取
	 */
	@PostMapping("/meta/get-meta")
	Map<String, Pair<Integer, String>> getMeta(@RequestParam("appId") String appId, @RequestParam("event") String event, @RequestParam("operator") String operator)
	{
		// check parameter

		String table = event;
		Optional<EventMetadata> op = this.eventMetadataRepository.findById(table);

		if(op.isPresent())
		{
			EventMetadata metadata = op.get();
			return metadata.getProperties();
		}

		return Collections.EMPTY_MAP;
	}
}
