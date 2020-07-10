package com.velen.etl.geneartor.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.common.data.hive.DataType;
import com.velen.etl.common.data.hive.Table;
import com.velen.etl.common.data.hive.TableColumn;
import com.velen.etl.geneartor.entity.AppMetadata;
import com.velen.etl.geneartor.entity.PropertyMetadata;
import com.velen.etl.geneartor.entity.TableMetadata;
import com.velen.etl.geneartor.service.HiveService;
import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import com.velen.etl.generator.tdo.TableMetadataTDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/meta/generator")
public class TableMetaController
{
	// TODO: 暂时先用内存存
	// AppId,
	private Map<String, AppMetadata> appMetas = new HashMap<>();

	@Autowired
	private HiveService hiveService;

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg)
	{
		//EventMetadata metadata = new EventMetadata("11111", Collections.EMPTY_MAP);

		//eventMetadataRepository.insert(metadata);

		return arg;
	}

	/**
	 * 创建 app database
	 */
	@PostMapping("/create-app")
	ResponseEntity createApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		if(appMetas.containsKey(appId))
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_EXISTS).body(appId);
		}
		boolean result = hiveService.createDatabase(appId);

		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_FAILURE).body(appId);
		}

		// TODO: 需要存档，不然重启就费了
		AppMetadata meta = appMetas.put(appId, new AppMetadata(appId));

		return ResponseEntity.ok(meta);
	}

	/**
	 * 删除 app database
	 */
	@PostMapping("/meta/generator/drop-app")
	ResponseEntity dropApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		if(!appMetas.containsKey(appId))
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS).body(appId);
		}
		hiveService.dropDatabase(appId);

		// TODO: 需要存档，不然重启就费了
		AppMetadata meta = appMetas.remove(appId);

		return ResponseEntity.ok(meta);
	}

	/**
	 * 创建元事件表
	 */
	@PostMapping("/create-table")
	//ResponseEntity createMeta(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO table)
	ResponseEntity createTable(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO tableTDO)
	{
		AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(tableTDO.getTable(), null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(appId);
		}

		List<PropertyMetadata> list = new ArrayList<>();
		List<TableColumn> columns = new ArrayList<>();


		for(PropertyMetadataTDO tdo : tableTDO.getProperties())
		{
			PropertyMetadata prop = new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getIndex(), tdo.isRequire());
			list.add(prop);

			if(appMeta.addProperty(prop))
			{
				columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), Collections.EMPTY_LIST, false, "", tdo.getIndex()));
			}
		}

		tableMeta = new TableMetadata(tableTDO.getDb(), tableTDO.getTable(), list);

		boolean result = hiveService.createTable(tableMeta.getDb(), tableMeta.getTable());
		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_FAILURE).body(appId);
		}

		result = hiveService.addColumns(tableMeta.getDb(), tableMeta.getTable(), columns);

		if(!result)
		{
			hiveService.dropTable(tableMeta.getDb(), tableMeta.getTable());
			return ResponseEntity.status(ResultCode.GENERATOR_COLUMN_FAILURE).body(appId);
		}

		appMeta.addTable(tableMeta);


		/*List<TableMetadata> metas = metaTables.getOrDefault(appId, null);
		if(metas != null)
		{
			metas = metadatas.put(appId, new ArrayList<>());
		}

		for(TableMetadata meta : metas)
		{
			if(meta.equals(table))
				return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(table);
		}

		Table tb = new Table();
		tb.setDb(table.getDb());
		tb.setName(table.getTable());
		List<TableColumn> columns = new ArrayList<>();
		tb.setColumns(columns);

		List<PropertyMetadata> properties = new ArrayList<>();
		for(PropertyMetadataTDO tdo: table.getProperties())
		{
			properties.add(new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getIndex(), tdo.isRequire()));
			columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), null, false, "", tdo.getIndex()));
		}

		TableMetadata meta = new TableMetadata(table.getDb(), table.getTable(), properties);

		metas.add(meta);*/




		return ResponseEntity.ok(tableMeta);

	}

	/**
	 * 同步元事件表
	 */
	@PostMapping("/meta/generator/update-table")
	ResponseEntity updateTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO)
	{
		AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(appId);
		}

		List<TableColumn> columns = new ArrayList<>();

		for(PropertyMetadataTDO tdo : propertiesTDO)
		{
			PropertyMetadata prop = new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getIndex(), tdo.isRequire());

			if(appMeta.addProperty(prop))
			{
				columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), Collections.EMPTY_LIST, false, "", tdo.getIndex()));
			}

			tableMeta.addProperty(prop);
		}

		return ResponseEntity.ok(tableMeta);
	}

	/**
	 * 数据获取
	 */
	@PostMapping("/get-table")
	//TableMetadataTDO getMeta(@RequestParam("appId") String appId, @RequestParam("business") String business, @RequestParam("operator") String operator)
	TableMetadataTDO getTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return null;
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta == null)
		{
			return null;
		}

		List<PropertyMetadataTDO> list = new ArrayList<>();

		for(PropertyMetadata o : tableMeta.getProperties())
		{
			list.add(new PropertyMetadataTDO(o.getName(), o.getType(), o.getIndex(), o.isRequire()));
		}

		TableMetadataTDO tdo = new TableMetadataTDO(tableMeta.getDb(), tableMeta.getTable(), list);

		return tdo;
	}

	/**
	 * 数据同步
	 */
	/*@PostMapping("/update-meta")
	ResponseEntity updateMeta(@RequestParam("appId") String appId, @RequestParam("business") String business, @RequestBody List<PropertyMetadataTDO> properties)
	{
		List<TableMetadata> metas = metadatas.getOrDefault(appId, null);
		if(metas == null)
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_NOT_EXISTS).body(appId);

		List<TableColumn> columns = new ArrayList<>();
		List<PropertyMetadata> props = new ArrayList<>();

		for(PropertyMetadataTDO tdo: properties)
		{
			columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), null, false, "", tdo.getIndex()));
			props.add(new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getIndex(), tdo.isRequire()));
		}

		for(TableMetadata meta : metas)
		{
			if(meta.getTable().equals(business))
			{
				meta.replaceProperties(props);
			}
				//return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(table);
		}

		hiveService.addColumns(business, columns);

		return ResponseEntity.ok().build();
	}*/

	/**
	 * 同步元事件表
	 */
	@PostMapping("/meta/generator/add-properties")
	ResponseEntity addProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO)
	{
		AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(appId);
		}

		List<TableColumn> columns = new ArrayList<>();

		for(PropertyMetadataTDO tdo : propertiesTDO)
		{
			PropertyMetadata prop = new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getIndex(), tdo.isRequire());

			if(appMeta.addProperty(prop))
			{
				columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), Collections.EMPTY_LIST, false, "", tdo.getIndex()));
			}

			tableMeta.addProperty(prop);
		}

		boolean result = hiveService.addColumns(appId, table, columns);

		return ResponseEntity.ok(tableMeta);
	}

	/**
	 * 同步元事件表
	 */
	@PostMapping("/meta/generator/remove-properties")
	ResponseEntity removeProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO)
	{
		AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS).body(appId);
		}

		List<PropertyMetadata> list = new ArrayList<>();
		List<TableColumn> columns = new ArrayList<>();

		return ResponseEntity.ok(tableMeta);
	}



}
