package com.velen.etl.verification.repository;

import com.velen.etl.verification.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String>
{
	//Project findByProjectId(String project);
}
