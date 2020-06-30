package com.velen.etl.verification.repository;

import com.velen.etl.verification.entity.InputParseFormat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputParseFormatRepository extends MongoRepository<InputParseFormat, Integer>
{
	//InputParseFormat findByProject(String project);

	@Query("{'projectId':?0}")
	List<InputParseFormat> withQueryFindByProjectId(String projectId);

	//@Query("{'projectId':?0}")
	//void withQueryDeleteAllByProjectId(String projectId);
}
