package com.velen.etl.common.dao;

/*import com.github.binmagic.saas.velen.common.entity.BaseEntity;
import lombok.NonNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleReactiveMongoRepository;
import reactor.core.publisher.Flux;

public class ExpandRepositoryImpl<E extends BaseEntity<E>> extends SimpleReactiveMongoRepository<E, String> implements ExpandRepository<E>
{

	private MongoEntityInformation<E, String> entityInformation;
	private ReactiveMongoOperations mongoOperations;

	public ExpandRepositoryImpl(@NonNull MongoEntityInformation<E, String> entityInformation, @NonNull ReactiveMongoOperations mongoOperations)
	{

		super(entityInformation, mongoOperations);
		this.entityInformation = entityInformation;
		this.mongoOperations = mongoOperations;
	}

	@Override
	public Flux<E> findAll(Example<E> example, Pageable pageable)
	{
		return mongoOperations.find(Query.query(Criteria.byExample(example))
				.collation(entityInformation.getCollation()).with(pageable)
				, example.getProbeType(), entityInformation.getCollectionName());
	}
}*/
