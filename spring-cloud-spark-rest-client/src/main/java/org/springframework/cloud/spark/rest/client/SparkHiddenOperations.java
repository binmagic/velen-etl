package org.springframework.cloud.spark.rest.client;

public interface SparkHiddenOperations
{
	/**
	 * submit a job to spark platform.
	 */
	SparkSubmissionStatus create(SparkJobSubmit jobSubmit) throws SparkJobServerException;

	/**
	 * to kill current job by id
	 */
	SparkSubmissionStatus kill(String submissionId) throws SparkJobServerException;

	/**
	 * display information by id
	 */
	SparkSubmissionSpecificStatus status(String submissionId) throws SparkJobServerException;
}
