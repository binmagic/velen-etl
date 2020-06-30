package com.velen.etl.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusReceiverEntity
{
	long id;
	String name;
	String address;
	String enName;
	int memberFamily;
}
