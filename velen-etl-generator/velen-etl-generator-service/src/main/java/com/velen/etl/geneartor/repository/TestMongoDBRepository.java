package com.velen.etl.geneartor.repository;

import com.velen.etl.geneartor.entity.Test;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Deprecated
@Repository
public interface TestMongoDBRepository extends MongoRepository<Test, String>
{
	//@Query("{'age':?0}")
	//List<Test> withQueryFindByAge(Integer age);
}
