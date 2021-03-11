package org.springframework.cloud.spark.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.spark.rest.client.config.SparkClientProperties;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class SparkEventLogTemplate implements SparkEventLogOperations
{
	private final Logger logger = LoggerFactory.getLogger(SparkEventLogTemplate.class);

	/**
	 * A template used for http interaction.
	 */
	protected final RestTemplate restTemplate;

	/*public static final String SUBMISSIONS_REL = "/submissions";

	private static final String APPLICATIONS_REL = "/applications";

	private static final String CONTEXTS_REL = "/contexts";

	private static final String BINARIES_REL = "/binaries";

	private static final String JOBS_REL = "/jobs";

	private static final String DATA_REL = "/data";*/

	//private final Link submissionsLink;

	//private final Link applicationsLink;

	//private final Link contextsLink;

	//private final Link deploymentLink;

	//private final Link validationLink;

	/**
	 * Setup a {@link SparkHiddenTemplate} using the provided baseURI. Will build a
	 * {@link RestTemplate} implicitly with the required set of Jackson MixIns. For more
	 * information, please see {@link @SparkTemplate.prepareRestTemplate(RestTemplate)}.
	 * Please be aware that the created RestTemplate will use the JDK's default timeout
	 * values. Consider passing in a custom {@link RestTemplate} or, depending on your JDK
	 * implementation, set System properties such as:
	 * <ul>
	 * <li>sun.net.client.defaultConnectTimeout
	 * <li>sun.net.client.defaultReadTimeout
	 * </ul>
	 * For more information see <a href=
	 * "https://docs.oracle.com/javase/7/docs/technotes/guides/net/properties.html">this
	 * link</a>
	 *
	 * @param baseURI Must not be null
	 */
	public SparkEventLogTemplate(URI baseURI) {
		this(baseURI, SparkTemplate.getDefaultSparkRestTemplate());
	}

	/**
	 * Setup a {@link SparkHiddenTemplate} using the provide {@link RestTemplate}. Any
	 * missing Mixins for Jackson will be added implicitly. For more information, please
	 * see {@link @SparkTemplate.prepareRestTemplate(RestTemplate)}.
	 *
	 * @param baseURI Must not be null
	 * @param restTemplate Must not be null
	 */
	public SparkEventLogTemplate(URI baseURI, RestTemplate restTemplate) {

		Assert.notNull(baseURI, "The provided baseURI must not be null.");
		Assert.notNull(restTemplate, "The provided restTemplate must not be null.");

		this.restTemplate = restTemplate;

	}


	@Override
	public void list()
	{

	}

	@Override
	public void jobs()
	{

	}

	@Override
	public void status(String jobId)
	{

	}

	@Override
	public void stages()
	{

	}

	@Override
	public void stage(String jobId)
	{

	}

	@Override
	public void stageByAttemptId(String AttemptId)
	{

	}

	@Override
	public void summary()
	{

	}
}
