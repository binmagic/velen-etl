package com.velen.etl.geneartor.repository;

import com.velen.etl.geneartor.entity.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TestMongoDBRepository extends MongoRepository<Test, String>
{
	Test findByName(String name);

	@Query("{'age':?0}")
	List<Test> withQueryFindByAge(Integer age);
}
