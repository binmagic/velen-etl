package com.velen.etl.geneartor.repository;

import com.velen.etl.geneartor.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

@Deprecated
public interface ProjectRepository extends MongoRepository<Project, String>
{

}
