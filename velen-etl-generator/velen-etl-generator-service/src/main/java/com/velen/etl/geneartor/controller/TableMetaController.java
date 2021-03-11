package com.velen.etl.geneartor.controller;

import com.velen.etl.ResultCode;
import com.velen.etl.geneartor.entity.AppMetadata;
import com.velen.etl.geneartor.entity.ColumnMetadata;
import com.velen.etl.geneartor.entity.TableMetadata;
import com.velen.etl.geneartor.repository.AppMetaRepository;
import com.velen.etl.geneartor.service.HiveService;
import com.velen.etl.generator.tdo.PropertyMetadataTDO;
import com.velen.etl.generator.tdo.TableMetadataTDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


/**
 * v0.1 没有考虑多进程处理 mogodb 的问题
 *      没有异常处理
 */
@RestController
@RequestMapping("/meta/generator")
@EnableMongoRepositories(basePackages = "com.velen.etl.geneartor.repository")
public class TableMetaController
{
	private final Logger logger = LoggerFactory.getLogger(TableMetaController.class);

	FeignClientBuilder feignClientBuilder;

	@Autowired
	private AppMetaRepository appMetaRepository;

	private AppMetadata getApp(String appId)
	{
		Optional<AppMetadata> op = appMetaRepository.findById(appId);
		if(op.isPresent())
			return op.get();
		return null;
	}

	@Qualifier("hiveMetaStoreServiceImpl")
	@Autowired
	private HiveService hiveService;

	/**
	 * 测试用
	 */
	@RequestMapping("/test/{arg}")
	String test(@PathVariable("arg") String arg)
	{

		hiveService.createDatabase(arg, "");
		hiveService.dropDatabase(arg);

		/*AppMetadata appMetadata = new AppMetadata(arg);

		List<PropertyMetadata> list = new ArrayList<>();
		list.add(new PropertyMetadata("a", "int", "", 0));
		list.add(new PropertyMetadata("b", "int", "", 0));

		appMetadata.addTable(new TableMetadata(arg, "event", 0, list));

		//hiveService.createDatabase(arg, "test");
		hiveService.createTable(appMetadata.getTable(0));*/

		//hiveService.deleteColumns("abc5", "event", "a");




		Optional<AppMetadata> op = appMetaRepository.findById(arg);

		if(op.isPresent())
		{
			AppMetadata app = op.get();
			System.out.println(app);
		}
		else
		{

			List<ColumnMetadata> properties = new ArrayList<>();
			TableMetadata tb = new TableMetadata(arg, "test", properties);
			//Map<String, TableMetadata> tbs = new HashMap<>();
			//tbs.put("test", tb);

			AppMetadata app = new AppMetadata(arg);
			app.putTable(tb);
			appMetaRepository.insert(app);
		}


		//EventMetadata metadata = new EventMetadata("11111", Collections.EMPTY_MAP);

		//eventMetadataRepository.insert(metadata);

		return arg;
	}

	/**
	 * 创建 app database
	 */
	@PostMapping("/create-app")
	ResponseEntity createApp(@RequestParam("appId") String appId, @RequestParam("appName") String appName, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);

		if(appMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_EXISTS.code()).body(appId);
		}
		boolean result = hiveService.createDatabase(appId, appName);

		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_CREATE_FAILURE.code()).body(appId);
		}

		appMeta = this.appMetaRepository.insert(new AppMetadata(appId));

		// how do handle Exception
		logger.info("[CREATE APP: {}:{}]", appId, appName);

		return ResponseEntity.ok(appMeta);
	}

	/**
	 * 删除 app database
	 */
	@PostMapping("/drop-app")
	ResponseEntity dropApp(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);

		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}
		hiveService.dropDatabase(appId);

		this.appMetaRepository.delete(appMeta);

		return ResponseEntity.ok(appMeta);
	}

	/**
	 * 创建元事件表, 用于创建/更新hive表, 需要全量更新
	 */
	@PostMapping("/alter-hive")
	//ResponseEntity createMeta(@RequestParam("appId") String appId, @RequestParam("operator") String operator, @RequestBody TableMetadataTDO table)
	ResponseEntity alterHive(@RequestParam("appId") String appId, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);

		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		/*List<ColumnMetadata> cols = new ArrayList<>();
		for(PropertyMetadataTDO tdo : tableTDO.getProperties())
		{
			ColumnMetadata prop = new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex());
			cols.add(prop);
		}*/
		List<ColumnMetadata> cols = tableTDO.getProperties().stream().map(tdo -> new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex())).collect(Collectors.toList());

		TableMetadata hiveMeta = appMeta.getHiveTable(tableTDO.getTable());

		boolean result = false;
		if(hiveMeta == null)
		{
			hiveMeta = new TableMetadata(tableTDO.getDb(), tableTDO.getTable(), cols, tableTDO.getPartitions());
			result = hiveService.createTable(hiveMeta);
			if(result)
			{
				appMeta.putTable(hiveMeta);
			}
		}
		else
		{
			hiveMeta.setColumns(cols);
			hiveMeta.setPartitions(tableTDO.getPartitions());
			result = hiveService.alterTable(hiveMeta.getDb(), hiveMeta.getTable(), hiveMeta);
		}

		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_CREATE_FAILURE.code()).body(appId);
		}

		/*if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS.code()).body(appId);
		}

		List<PropertyMetadata> cols = new ArrayList<>();
		for(PropertyMetadataTDO tdo : tableTDO.getProperties())
		{
			PropertyMetadata prop = new PropertyMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex());
			cols.add(prop);
		}

		tableMeta = new TableMetadata(tableTDO.getDb(), tableTDO.getTable(), tableTDO.getType(), list);
		appMeta.addTable(tableMeta);*/

		/*boolean result = hiveService.createTable(tableMeta);
		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_CREATE_FAILURE.code()).body(appId);
		}*/

		this.appMetaRepository.save(appMeta);

		logger.info("[ALTER HIVE: {}-{}]", appId, tableTDO);

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

		return ResponseEntity.ok(appMeta);

	}

	/**
	 * 用于创建/更新元事件表, 需要全量更新
	 * 不操作 HIVE 库
	 */
	@PostMapping("/alter-meta")
	ResponseEntity alterMeta(@RequestParam("appId") String appId, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		/*List<ColumnMetadata> cols = new ArrayList<>();
		for(PropertyMetadataTDO tdo : tableTDO.getProperties())
		{
			ColumnMetadata prop = new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex());
			cols.add(prop);
		}*/
		List<ColumnMetadata> cols = tableTDO.getProperties().stream().map(tdo -> new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex())).collect(Collectors.toList());

		TableMetadata tableMeta = appMeta.getTable(tableTDO.getTable());

		if(tableMeta == null)
		{
			tableMeta = new TableMetadata(tableTDO.getDb(), tableTDO.getTable());
			appMeta.putTable(tableMeta);
		}
		tableMeta.setColumns(cols);
		tableMeta.setPartitions(tableTDO.getPartitions());

		/*if(tableMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_NOT_EXISTS.code()).body(appId);
		}
		*/


		this.appMetaRepository.save(appMeta);

		logger.info("[ALTER TABLE: {}-{}]", appId, tableTDO);

		return ResponseEntity.ok(appMeta);
	}

	/**
	 * 同步更新元事件表
	 * 不操作 HIVE 库, 但是更新 hiveTable
	 */
	@PostMapping("/update-table")
	ResponseEntity updateTable(@RequestParam("appId") String appId, @RequestParam("hiveTableType") String hiveTableType, @RequestBody TableMetadataTDO tableTDO, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		List<ColumnMetadata> cols = tableTDO.getProperties().stream().map(tdo -> new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex())).collect(Collectors.toList());

		{
			TableMetadata tableMeta = alterColumns(appId, tableTDO.getTable(), appMeta.getTable(tableTDO.getTable()), cols);
			appMeta.putTable(tableMeta);
		}

		{
			TableMetadata tableMeta = alterColumns(appId, hiveTableType, appMeta.getHiveTable(hiveTableType), cols);
			appMeta.putHiveTable(tableMeta);
		}

		this.appMetaRepository.save(appMeta);

		return ResponseEntity.ok(appMeta);
	}

	private TableMetadata alterColumns(String db, String table, TableMetadata tableMeta, List<ColumnMetadata> cols)
	{
		if(tableMeta == null)
		{
			tableMeta = new TableMetadata(db, table, cols);
		}
		else
		{
			List<ColumnMetadata> all = tableMeta.getColumns().parallelStream().collect(toList());
			cols.addAll(all);
			// 去重并集
			List<ColumnMetadata> distinct = cols.stream().distinct().collect(toList());

			tableMeta.setColumns(distinct);
		}

		return tableMeta;
	}

	/**
	 * 同步元事件表
	 */
	@Deprecated
	@PostMapping("/update-table1")
	ResponseEntity updateTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO)
	{
		AppMetadata appMeta = getApp(appId);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_NOT_EXISTS.code()).body(appId);
		}

		for(PropertyMetadataTDO tdo : propertiesTDO)
		{
			ColumnMetadata prop = new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex());
			tableMeta.addProperty(prop);
		}

		this.appMetaRepository.save(appMeta);

		return ResponseEntity.ok(appMeta);
	}

	/**
	 * 修改元事件表
	 */
	@Deprecated
	@PostMapping("/alter-table1")
	ResponseEntity alterTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);

		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		TableMetadata hiveTable = appMeta.getTable(table);
		if(hiveTable != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS.code()).body(appId);
		}

		if(hiveService.tableExists(hiveTable.getDb(), hiveTable.getTable()))
		{
			if(!hiveService.alterTable(hiveTable.getDb(), hiveTable.getTable(), hiveTable))
				return ResponseEntity.status(ResultCode.GENERATOR_TABLE_CREATE_FAILURE.code()).body(hiveTable);
		}
		else
		{
			if(!hiveService.createTable(hiveTable))
				return ResponseEntity.status(ResultCode.GENERATOR_TABLE_CREATE_FAILURE.code()).body(hiveTable);
		}


		return ResponseEntity.ok(hiveTable);
	}

	/**
	 * 数据获取
	 */
	@PostMapping("/get-table")
	//TableMetadataTDO getMeta(@RequestParam("appId") String appId, @RequestParam("business") String business, @RequestParam("operator") String operator)
	TableMetadataTDO getTable(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);
		//AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return null;
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta == null)
		{
			return null;
		}

		/*List<PropertyMetadataTDO> list = new ArrayList<>();

		for(ColumnMetadata o : tableMeta.getColumns())
		{
			list.add(new PropertyMetadataTDO(o.getName(), o.getType(), o.getComment(), o.getIndex()));
		}*/
		List<PropertyMetadataTDO> list = tableMeta.getColumns().stream().map(o -> new PropertyMetadataTDO(o.getName(), o.getType(), o.getComment(), o.getIndex())).collect(Collectors.toList());

		TableMetadataTDO tdo = new TableMetadataTDO(tableMeta.getDb(), tableMeta.getTable(), list, tableMeta.getPartitions());

		return tdo;
	}

	/**
	 * 获取元事件表
	 */
	@PostMapping("/get-tables")
	List<TableMetadataTDO> getTables(@RequestParam("appId") String appId, @RequestParam("operator") String operator)
	{
		AppMetadata appMeta = getApp(appId);
		//AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		if(appMeta == null)
		{
			return null;
		}

		List<TableMetadataTDO> tables = new ArrayList<>();

		for(Map.Entry<String, TableMetadata> entry : appMeta.getTables().entrySet())
		{
			TableMetadata tableMeta = entry.getValue();

			List<PropertyMetadataTDO> list = new ArrayList<>();

			for(ColumnMetadata o : tableMeta.getColumns())
			{
				list.add(new PropertyMetadataTDO(o.getName(), o.getType(), o.getComment(), o.getIndex()));
			}

			tables.add(new TableMetadataTDO(tableMeta.getDb(), tableMeta.getTable(), list, tableMeta.getPartitions()));
		}

		return tables;
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
	 * 增加表属性
	 */
	@Deprecated
	@PostMapping("/add-properties")
	ResponseEntity addProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator)
	{
		//AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		AppMetadata appMeta = getApp(appId);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS.code()).body(appId);
		}

		List<ColumnMetadata> columns = new ArrayList<>();

		for(PropertyMetadataTDO tdo : propertiesTDO)
		{
			ColumnMetadata prop = new ColumnMetadata(tdo.getName(), tdo.getType(), tdo.getComment(), tdo.getIndex());

			/*if(appMeta.addProperty(table, prop))
			{
				columns.add(new TableColumn(tdo.getName(), DataType.parse(tdo.getType()), Collections.EMPTY_LIST, false, "", tdo.getIndex()));
			}*/

			//tableMeta.addProperty(prop);
		}

		boolean result = hiveService.addColumns(appId, table, columns);

		if(!result)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_COLUMN_CREATE_FAILURE.code()).body(columns);
		}

		this.appMetaRepository.save(appMeta);

		return ResponseEntity.ok(tableMeta);
	}

	/**
	 * 同步元事件表
	 */
	@Deprecated
	@PostMapping("/remove-properties")
	ResponseEntity removeProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator)
	{
		//AppMetadata appMeta = appMetas.getOrDefault(appId, null);
		AppMetadata appMeta = getApp(appId);
		if(appMeta == null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_DATABASE_NOT_EXISTS.code()).body(appId);
		}

		TableMetadata tableMeta = appMeta.getTables().getOrDefault(table, null);
		if(tableMeta != null)
		{
			return ResponseEntity.status(ResultCode.GENERATOR_TABLE_EXISTS.code()).body(appId);
		}

		List<ColumnMetadata> list = new ArrayList<>();

		return ResponseEntity.ok(tableMeta);
	}

	/**
	 * 更新表属性
	 */
	@Deprecated
	@PostMapping("/update-properties")
	ResponseEntity updateProperties(@RequestParam("appId") String appId, @RequestParam("table") String table, @RequestBody List<PropertyMetadataTDO> propertiesTDO, @RequestParam("operator") String operator)
	{
		return ResponseEntity.ok("tableMeta");
	}



}
