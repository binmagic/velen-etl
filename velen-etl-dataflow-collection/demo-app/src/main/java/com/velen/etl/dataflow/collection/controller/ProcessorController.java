package com.velen.etl.dataflow.collection.controller;

import com.velen.etl.common.entity.VerifyEnum;
import com.velen.etl.dataflow.collection.service.ProcessorService;
import com.velen.etl.dataflow.collection.service.SourceService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProcessorController
{
	@Autowired
	private SourceService sourceService;

	@Autowired
	private ProcessorService processorService;


	@RequestMapping("/send/{msg}")
	public void send(@PathVariable("msg") String msg)
	{
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println(msg);
		System.out.println("---------------------------------------------------------------------------------");
		sourceService.sendMsg(msg);
	}

	@RequestMapping("/dataflow/xx/processor/filter")
	public ResponseEntity filter(@RequestParam("type") String type, @RequestParam("formula") String formula)
	{
		if(StringUtils.isEmpty(type) || StringUtils.isEmpty(formula))
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		VerifyEnum.InputParseType inputParseType = VerifyEnum.InputParseType.parse(type);
		if(inputParseType == VerifyEnum.InputParseType.NONE)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		List<Pair<VerifyEnum.InputParseType, String>> results = processorService.setFilter(inputParseType, formula);

		return ResponseEntity.ok(results);
	}

	@RequestMapping("/dataflow/xx/processor/filters")
	public ResponseEntity filters(@RequestParam("filters") List<Pair<String, String>> rules)
	{
		if(CollectionUtils.isEmpty(rules))
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		List<Pair<VerifyEnum.InputParseType, String>> input = new ArrayList<>();
		for(Pair<String, String> pair : rules)
		{
			VerifyEnum.InputParseType inputParseType = VerifyEnum.InputParseType.parse(pair.getKey());
			if(inputParseType == VerifyEnum.InputParseType.NONE)
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

			input.add(new Pair<>(inputParseType, pair.getValue()));
		}

		List<Pair<VerifyEnum.InputParseType, String>> results = processorService.setFilters(input);

		return ResponseEntity.ok(results);
	}

	@RequestMapping("/dataflow/xx/processor/delete")
	public ResponseEntity delete(@RequestParam("type") String type, @RequestParam("formula") String formula)
	{
		if(StringUtils.isEmpty(type) || StringUtils.isEmpty(formula))
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		VerifyEnum.InputParseType inputParseType = VerifyEnum.InputParseType.parse(type);
		if(inputParseType == VerifyEnum.InputParseType.NONE)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		List<Pair<VerifyEnum.InputParseType, String>> results = processorService.delete(inputParseType, formula);

		return ResponseEntity.ok(results);
	}

	@RequestMapping("/dataflow/xx/processor/clear")
	public ResponseEntity clear()
	{
		processorService.clear();
		return ResponseEntity.ok().build();
	}
}
