package org.springframework.cloud.spark.rest.client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SparkSubmissionSpecificStatus extends SparkSubmissionStatus
{
	private String driverState;
	private String workerHostPort;
	private String workerId;
}
