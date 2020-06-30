package com.velen.etl.generator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Deprecated
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SerDeTDO
{
	String name;
	Map<String, String> properties;
}
