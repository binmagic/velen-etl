package com.velen.etl.geneartor.controller;


import com.velen.etl.geneartor.entity.Location;
import com.velen.etl.geneartor.entity.Test;
import com.velen.etl.geneartor.repository.TestMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;


@RestController
public class TestMongoDBController
{
	@Autowired
	TestMongoDBRepository testMongoDBRepository;

	/**
	 * 保存数据
	 *
	 * @return
	 */
	@GetMapping("/save")
	public Test save()
	{
		Test test = Test.builder()
				.id(UUID.randomUUID().toString())
				.name("zhangsan")
				.age(32)
				.build();
		Collection<Location> locationCollection = new LinkedHashSet<>();
		Location location = Location.builder()
				.place("hangzhou")
				.year("2002")
				.build();
		locationCollection.add(location);
		test.setLocations(locationCollection);
		return testMongoDBRepository.save(test);
	}

	/**
	 * 根据名称查询数据
	 *
	 * @param name 用户名
	 * @return
	 */
	@GetMapping("/queryByName")
	public Test queryByName(String name)
	{
		return testMongoDBRepository.findByName(name);
	}

	/**
	 * 根据年龄查询数据
	 *
	 * @param age 用户年龄
	 * @return
	 */
	@GetMapping("/queryByAge")
	public List<Test> queryByAge(Integer age)
	{
		return testMongoDBRepository.withQueryFindByAge(age);
	}
}


