package com.velen.etl.geneartor.repository;

import com.velen.etl.geneartor.entity.AppMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppMetaRepository extends MongoRepository<AppMetadata, String>
{
}
