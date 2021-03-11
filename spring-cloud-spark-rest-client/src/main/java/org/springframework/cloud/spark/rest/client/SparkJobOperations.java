package org.springframework.cloud.spark.rest.client;

import org.springframework.cloud.spark.rest.client.config.SparkClientProperties;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface SparkJobOperations
{
	/**
	 * SUBMITTED
	 * RUNNING
	 * FINISHED
	 * RELAUNCHING
	 * UNKNOWN
	 * KILLED
	 * FAILED
	 * ERROR
	 * NOT_FOUND
	 * RETRYING (Mesos)
	 * QUEUED (Mesos)
	 */

	/**
	 * Lists all the information of jars for potential jobs to be running
	 * in the Spark Cluster behind the Spark Job Server.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /jars' </code>of the
	 * Spark Job Server.
	 *
	 * @return a list containing information of Spark Job jars
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of spark job jars
	 */
	List<SparkJobJarInfo> getJars() throws SparkJobServerException;

	/**
	 * Uploads a jar containing spark job to the Spark Job Server under
	 * the given application name.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /jars/&lt;appName&gt;' </code>
	 * of the Spark Job Server.
	 *
	 * @param jarData the instance of <code>InputStream</code> contains the
	 *     contents of the target jar file to be uploaded
	 * @param appName the application name under which the related Spark Job
	 *     is about to run, meanwhile the application name also be the alias
	 *     name of the uploaded jar file.
	 * @return true if the operation of uploading is successful, false otherwise
	 * @throws SparkJobServerException if the given parameter jarData or
	 *     appName is null, or error occurs when uploading the related spark job
	 *     jar
	 */
	boolean uploadSparkJobJar(InputStream jarData, String appName)
			throws SparkJobServerException;

	/**
	 * Uploads a jar containing spark job to the Spark Job Server under
	 * the given application name.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /jars/&lt;appName&gt;' </code>
	 * of the Spark Job Server.
	 *
	 * @param jarFile the jar file
	 * @param appName the application name under which the related Spark Job
	 *     is about to run, meanwhile the application name also be the alias
	 *     name of the uploaded jar file.
	 * @return true if the operation of uploading is successful, false otherwise
	 * @throws SparkJobServerException if the given parameter jarData or
	 *     appName is null, or error occurs when uploading the related spark job
	 *     jar
	 */
	boolean uploadSparkJobJar(File jarFile, String appName)
			throws SparkJobServerException;

	/**
	 * Lists all the contexts available in the Spark Job Server.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /contexts '</code>
	 * of the Spark Job Server.
	 *
	 * @return a list containing names of current contexts
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of contexts
	 */
	List<String> getContexts() throws SparkJobServerException;

	/**
	 * Creates a new context in the Spark Job Server with the given context name.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /contexts/&lt;name&gt;' </code>
	 * of the Spark Job Server.
	 *
	 * @param contextName the name of the new context to be created, it should be not null
	 *        and should begin with letter.
	 * @param params a map containing the key-value pairs appended to appoint the context
	 *        settings if there is a need to configure the new created context, or null indicates
	 *        the new context with the default configuration
	 * @return true if the operation of creating is successful, false it failed to create
	 *        the context because a context with the same name already exists
	 * @throws SparkJobServerException when the given contextName is null or empty string,
	 *        or I/O error occurs while trying to create context in spark job server.
	 */
	boolean createContext(String contextName, Map<String, String> params) throws SparkJobServerException;

	/**
	 * Delete a context with the given name in the Spark Job Server.
	 * All the jobs running in it will be stopped consequently.
	 *
	 * <p>
	 * This method implements the Rest API <code>'DELETE /contexts/&lt;name&gt;' </code>
	 * of the Spark Job Server.
	 *
	 * @param contextName the name of the target context to be deleted, it should be not null
	 *        and should begin with letter.
	 * @return true if the operation of the deleting is successful, false otherwise
	 * @throws SparkJobServerException when the given contextName is null or empty string,
	 *        or I/O error occurs while trying to delete context in spark job server.
	 */
	boolean deleteContext(String contextName) throws SparkJobServerException;

	/**
	 * Lists the last N jobs in the Spark Job Server.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs' </code> of the Spark
	 * Job Server.
	 *
	 * @return a list containing information of the jobs
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of jobs
	 */
	List<SparkJobInfo> getJobs() throws SparkJobServerException;

	/**
	 * Lists the last N jobs in the Spark Job Server for the specified job status.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs&quest;status&equals;&lpar;RUNNING&verbar;ERROR&verbar;FINISHED&verbar;
	 * STARTED&verbar;OK&rpar;' </code> of the Spark
	 * Job Server.
	 *</p>
	 * @param jobStatus RUNNING OK ERROR FINISHED STARTED
	 * @return a list containing information of the jobs for specified status
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of jobs
	 */
	List<SparkJobInfo> getJobsByStatus(String jobStatus) throws SparkJobServerException;

	/**
	 * Start a new job with the given parameters.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /jobs' </code> of the Spark
	 * Job Server.
	 *
	 * @param data contains the the data processed by the target job.
	 * 		 <p>
	 * 	     If it is null, it means the target spark job doesn't needs any data set
	 *       in the job configuration.
	 * 		 <p>
	 * 	     If it is not null, the format should be like a key-value pair, such as
	 * 	     <code>dataKey=dataValue</code>, what the dataKey is determined by the
	 * 		 one used in the target spark job main class which is assigned by
	 *       ISparkJobServerClientConstants.PARAM_CLASS_PATH.
	 * @param params a non-null map containing parameters to start the job.
	 *       the key should be the following ones:
	 *       i. <code>ISparkJobServerClientConstants.PARAM_APP_NAME</code>, necessary
	 *       one and should be one of the existing name in the calling of <code>GET /jars</code>.
	 *       That means the appName is the alias name of the uploaded spark job jars.
	 *
	 *       ii.<code>ISparkJobServerClientConstants.PARAM_CLASS_PATH</code>, necessary one
	 *
	 *       iii.<code>ISparkJobServerClientConstants.PARAM_CONTEXT</code>, optional one
	 *
	 *       iv.<code>ISparkJobServerClientConstants.PARAM_SYNC</code>, optional one
	 *
	 * @return the corresponding job status or job result
	 * @throws SparkJobServerException the given parameters exist null or empty value,
	 *        or I/O error occurs when trying to start the new job
	 */
	SparkJobResult startJob(String data, Map<String, String> params) throws SparkJobServerException;

	/**
	 * Start a new job with the given parameters.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /jobs' </code> of the Spark
	 * Job Server.
	 *
	 * @param dataFile contains the the data processed by the target job.
	 * 		 <p>
	 * 	     If it is null, it means the target spark job doesn't needs any data set
	 *       in the job configuration.
	 * 		 <p>
	 * 	     If it is not null, the format should be Typesafe Config Style, such as
	 * 	     json, properties file etc. See <a href="http://github.com/typesafehub/config">http://github.com/typesafehub/config</a>
	 * 	     what the keys in the file are determined by the
	 * 		 one used in the target spark job main class which is assigned by
	 *       ISparkJobServerClientConstants.PARAM_CLASS_PATH.
	 * @param params a non-null map containing parameters to start the job.
	 *       the key should be the following ones:
	 *       i. <code>ISparkJobServerClientConstants.PARAM_APP_NAME</code>, necessary
	 *       one and should be one of the existing name in the calling of <code>GET /jars</code>.
	 *       That means the appName is the alias name of the uploaded spark job jars.
	 *
	 *       ii.<code>ISparkJobServerClientConstants.PARAM_CLASS_PATH</code>, necessary one
	 *
	 *       iii.<code>ISparkJobServerClientConstants.PARAM_CONTEXT</code>, optional one
	 *
	 *       iv.<code>ISparkJobServerClientConstants.PARAM_SYNC</code>, optional one
	 *
	 * @return the corresponding job status or job result
	 * @throws SparkJobServerException the given parameters exist null or empty value,
	 *        or I/O error occurs when trying to start the new job
	 */
	SparkJobResult startJob(File dataFile, Map<String, String> params) throws SparkJobServerException;

	/**
	 * Start a new job with the given parameters.
	 *
	 * <p>
	 * This method implements the Rest API <code>'POST /jobs' </code> of the Spark
	 * Job Server.
	 *
	 * @param dataFileStream contains the the data processed by the target job.
	 * 		 <p>
	 * 	     If it is null, it means the target spark job doesn't needs any data set
	 *       in the job configuration.
	 * 		 <p>
	 * 	     If it is not null, the format should be Typesafe Config Style, such as
	 * 	     json, properties file etc. See <a href="http://github.com/typesafehub/config">http://github.com/typesafehub/config</a>
	 * 	     what the keys in the file are determined by the
	 * 		 one used in the target spark job main class which is assigned by
	 *       ISparkJobServerClientConstants.PARAM_CLASS_PATH.
	 * @param params a non-null map containing parameters to start the job.
	 *       the key should be the following ones:
	 *       i. <code>ISparkJobServerClientConstants.PARAM_APP_NAME</code>, necessary
	 *       one and should be one of the existing name in the calling of <code>GET /jars</code>.
	 *       That means the appName is the alias name of the uploaded spark job jars.
	 *
	 *       ii.<code>ISparkJobServerClientConstants.PARAM_CLASS_PATH</code>, necessary one
	 *
	 *       iii.<code>ISparkJobServerClientConstants.PARAM_CONTEXT</code>, optional one
	 *
	 *       iv.<code>ISparkJobServerClientConstants.PARAM_SYNC</code>, optional one
	 *
	 * @return the corresponding job status or job result
	 * @throws SparkJobServerException the given parameters exist null or empty value,
	 *        or I/O error occurs when trying to start the new job
	 */
	SparkJobResult startJob(InputStream dataFileStream, Map<String, String> params) throws SparkJobServerException;

	/**
	 * Gets the result or status of a specific job in the Spark Job Server.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs/&lt;jobId&gt;' </code>
	 * of the Spark Job Server.
	 *
	 * @param jobId the id of the target job
	 * @return the corresponding <code>SparkJobResult</code> instance if the job
	 * with the given jobId exists, or null if there is no corresponding job or
	 * the target job has no result.
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of the target job
	 */
	SparkJobResult getJobResult(String jobId) throws SparkJobServerException;

	/**
	 * Gets the job configuration of a specific job.
	 *
	 * <p>
	 * This method implements the Rest API <code>'GET /jobs/&lt;jobId&gt;/config' </code>
	 * of the Spark Job Server.
	 *
	 * @param jobId the id of the target job
	 * @return the corresponding <code>SparkJobConfig</code> instance if the job
	 * with the given jobId exists, or null if there is no corresponding job in
	 * the spark job server.
	 * @throws SparkJobServerException error occurs when trying to get
	 *         information of the target job configuration
	 */
	SparkClientProperties getConfig(String jobId) throws SparkJobServerException;

	/**
	 * Kill the specified job
	 *
	 * <p>
	 * This method implements the Rest API <code>'DELETE /jobs/&lt;jobId&gt' </code> of the Spark
	 * Job Server.
	 *
	 * @param jobId the id of the target job.
	 *
	 * @return the corresponding killed job status or killed job result
	 * @throws SparkJobServerException if failed to kill a job,
	 *        or I/O error occurs when trying to kill existing job
	 */
	boolean killJob(String jobId) throws SparkJobServerException;
}
