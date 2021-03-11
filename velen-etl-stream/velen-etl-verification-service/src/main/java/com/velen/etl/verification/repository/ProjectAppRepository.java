package com.velen.etl.verification.repository;

import com.velen.etl.verification.entity.ProjectApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectAppRepository extends MongoRepository<ProjectApp, String>
{
	//Project findByProjectId(String project);
}
