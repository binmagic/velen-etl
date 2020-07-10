package com.velen.etl.common.repository;

import com.velen.etl.common.entity.EventMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventMetadataRepository extends MongoRepository<EventMetadata, String>
{
}
