package com.velen.etl.common.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public abstract class BaseEntity<E>
{
	@Id
	protected String id;
}
