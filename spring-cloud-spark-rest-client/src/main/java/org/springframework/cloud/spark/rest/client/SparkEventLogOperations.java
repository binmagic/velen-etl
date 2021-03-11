package org.springframework.cloud.spark.rest.client;

import org.springframework.cloud.spark.rest.client.config.SparkClientProperties;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SparkEventLogOperations
{
	/**
	 * A list of all applications.
	 * ?status=[completed|running] list only applications in the chosen state.
	 * ?minDate=[date] earliest start date/time to list.
	 * ?maxDate=[date] latest start date/time to list.
	 * ?minEndDate=[date] earliest end date/time to list.
	 * ?maxEndDate=[date] latest end date/time to list.
	 * ?limit=[limit] limits the number of applications listed.
	 * Examples:
	 * ?minDate=2015-02-10
	 * ?minDate=2015-02-03T16:42:40.000GMT
	 * ?maxDate=2015-02-11T20:41:30.000GMT
	 * ?minEndDate=2015-02-12
	 * ?minEndDate=2015-02-12T09:15:10.000GMT
	 * ?maxEndDate=2015-02-14T16:30:45.000GMT
	 * ?limit=10
	 */
	void list();

	/**
	 * A list of all jobs for a given application.
	 * ?status=[running|succeeded|failed|unknown] list only jobs in the specific state.
	 */
	void jobs();

	/**
	 * Details for the given job.
	 */
	void status(String jobId);

	/**
	 * A list of all stages for a given application.
	 * ?status=[active|complete|pending|failed] list only stages in the state.
	 */
	void stages();

	/**
	 * A list of all attempts for the given stage.
	 */
	void stage(String jobId);

	/**
	 * Details for the given stage attempt.
	 */
	void stageByAttemptId(String AttemptId);

	/**
	 * Summary metrics of all tasks in the given stage attempt.
	 * ?quantiles summarize the metrics with the given quantiles.
	 * Example: ?quantiles=0.01,0.5,0.99
	 */
	void summary();



}
