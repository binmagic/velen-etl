package com.velen.etl.generator.tdo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class SerDeTDO
{
	String name;
	Map<String, String> properties;
}
