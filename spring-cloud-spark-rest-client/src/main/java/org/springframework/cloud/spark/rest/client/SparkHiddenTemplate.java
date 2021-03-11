package org.springframework.cloud.spark.rest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SparkHiddenTemplate implements SparkHiddenOperations
{
	private final Logger logger = LoggerFactory.getLogger(SparkHiddenTemplate.class);

	/*public static final String SUBMISSIONS_REL = "/submissions";

	private static final String APPLICATIONS_REL = "/applications";

	private static final String CONTEXTS_REL = "/contexts";

	private static final String BINARIES_REL = "/binaries";

	private static final String JOBS_REL = "/jobs";

	private static final String DATA_REL = "/data";*/


	public static final String STATUS_REL = "/submissions/status/{submissionId}";
	private static final String CREATE_REL = "/submissions/create";
	private static final String KILL_REL = "/submissions/kill/{submissionId}";

	//private static final String VALIDATION_PROTOCOL_VERSION = "v1";
	//private static final String VALIDATION_RELATION_VERSION = "2.4.5";

	/**
	 * A template used for http interaction.
	 */
	protected final RestTemplate restTemplate;

	//private final Link submissionsLink;

	//private final Link applicationsLink;

	//private final Link contextsLink;

	//private final Link deploymentLink;

	//private final Link validationLink;

	private final Link statusLink;
	private final Link createLink;
	private final Link killLink;

	//private final String sparkVersion;


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
	public SparkHiddenTemplate(URI baseURI) {
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
	public SparkHiddenTemplate(URI baseURI, RestTemplate restTemplate) {

		Assert.notNull(baseURI, "The provided baseURI must not be null.");
		Assert.notNull(restTemplate, "The provided restTemplate must not be null.");

		this.restTemplate = restTemplate;

		this.createLink = new Link(baseURI + "/" + Version.VALIDATION_PROTOCOL_VERSION + CREATE_REL);
		this.killLink = new Link(baseURI + "/" + Version.VALIDATION_PROTOCOL_VERSION + KILL_REL);
		this.statusLink = new Link(baseURI + "/" + Version.VALIDATION_PROTOCOL_VERSION + STATUS_REL);

		//String jobs = restTemplate.getForObject(baseURI + "/jobs", String.class);
	}

	@Override
	public SparkSubmissionStatus create(SparkJobSubmit jobSubmit) throws SparkJobServerException
	{
		String uriTemplate = this.createLink.getHref();
		return restTemplate.postForObject(uriTemplate, jobSubmit, SparkSubmissionStatus.class);
	}

	@Override
	public SparkSubmissionStatus kill(String submissionId) throws SparkJobServerException
	{
		String uriTemplate = this.killLink.expand(submissionId).getHref();
		return restTemplate.getForObject(uriTemplate, SparkSubmissionStatus.class);
	}

	@Override
	public SparkSubmissionSpecificStatus status(String submissionId) throws SparkJobServerException
	{
		String uriTemplate = this.statusLink.expand(submissionId).getHref();
		return restTemplate.getForObject(uriTemplate, SparkSubmissionSpecificStatus.class);
	}
}
