package com.velen.etl.geneartor.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

@Deprecated
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Test
{
	@Id
	private String id;
	private String name;
	private Integer age;
	@Field("locs")
	private Collection<Location> locations = new LinkedHashSet<>();
}
