package org.springframework.cloud.spark.rest.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SparkSubmissionStatus
{
	private String action;
	private String message;
	private String serverSparkVersion;
	private String submissionId;
	boolean success;
}
