package com.velen.etl.common.entity;

import javafx.util.Pair;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Map;

@Deprecated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "event_metadata") // 不先区分表名，暂时先用库来做区分
public class EventMetadata implements Serializable
{
	@Id
	String event;
	Map<String, Pair<Integer, String>> properties;
}
