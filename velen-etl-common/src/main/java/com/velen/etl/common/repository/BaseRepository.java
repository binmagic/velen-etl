package com.velen.etl.common.repository;

import com.velen.etl.common.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<E>>
{

}
