package com.velen.etl.verification.repository;

import com.velen.etl.verification.entity.InputFieldRule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputFieldRuleRepository extends MongoRepository<InputFieldRule, String>
{
	@Query("{'projectId':?0}")
	List<InputFieldRule> withQueryFindByProjectId(String projectId);
}
