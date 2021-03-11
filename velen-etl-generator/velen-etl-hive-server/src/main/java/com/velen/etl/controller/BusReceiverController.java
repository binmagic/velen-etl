package com.velen.etl.controller;

import com.velen.etl.entity.BusReceiverEntity;
import com.velen.etl.service.BusReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BusReceiverController
{
	@Autowired
	BusReceiverService busReceiverService;

	@GetMapping("/execute/{sql}")
	void execute(@PathVariable("sql") String sql)
	{
		busReceiverService.execute(sql);
	}

	@GetMapping("/test")
	void test()
	{
		busReceiverService.createTable();
	}

	@GetMapping("/insert")
	void insert()
	{
		BusReceiverEntity be = new BusReceiverEntity();
		be.setId(new Random().nextLong());
		be.setName("test");
		be.setEnName("test2");
		be.setAddress("sssss");
		be.setMemberFamily(10);
		busReceiverService.insert(be);
	}

	@GetMapping("/select")
	void select()
	{
		busReceiverService.select();
	}
}
