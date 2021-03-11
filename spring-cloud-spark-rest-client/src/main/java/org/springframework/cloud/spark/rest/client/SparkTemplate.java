package org.springframework.cloud.spark.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.spark.rest.client.support.VndErrorResponseErrorHandler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class SparkTemplate
{
	/**
	 * A template used for http interaction.
	 */
	protected final RestTemplate restTemplate;

	private final SparkHiddenOperations hiddenOperations;
	private final SparkEventLogOperations eventLogOperations;

	/**
	 * Setup a {@link SparkHiddenTemplate} using the provided baseURI. Will build a
	 * {@link RestTemplate} implicitly with the required set of Jackson MixIns. For more
	 * information, please see {@link #prepareRestTemplate(RestTemplate)}.
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
	 * @param externalURI Must not be null
	 * @param historyURI Must not be null
	 */
	public SparkTemplate(URI externalURI, URI historyURI) {
		this(externalURI, historyURI, getDefaultSparkRestTemplate());
	}

	/**
	 * Setup a {@link SparkHiddenTemplate} using the provide {@link RestTemplate}. Any
	 * missing Mixins for Jackson will be added implicitly. For more information, please
	 * see {@link #prepareRestTemplate(RestTemplate)}.
	 *
	 * @param externalURI Must not be null
	 * @param historyURI Must not be null
	 * @param restTemplate Must not be null
	 */
	public SparkTemplate(URI externalURI, URI historyURI, RestTemplate restTemplate) {

		Assert.notNull(externalURI, "The provided externalURI must not be null.");
		Assert.notNull(historyURI, "The provided externalURI must not be null.");
		Assert.notNull(restTemplate, "The provided restTemplate must not be null.");

		this.restTemplate = prepareRestTemplate(restTemplate);

		this.hiddenOperations = new SparkHiddenTemplate(externalURI, restTemplate);
		this.eventLogOperations = new SparkEventLogTemplate(historyURI, restTemplate);


		/*SparkJobSubmit submit = new SparkJobSubmit();
		submit.setAction("CreateSubmissionRequest");
		submit.setAppResource("file:/spark.jar");
		submit.setClientSparkVersion(VALIDATION_RELATION_VERSION);
		submit.setMainClass("com.mycompany.MyJob");

		SparkSubmissionStatus s  = restTemplate.postForObject(createLink.getHref(), "{}", SparkSubmissionStatus.class);



		SparkSubmissionSpecificStatus aa = restTemplate.getForObject(statusLink.getHref() + "/driver-20200811181757-0000", SparkSubmissionSpecificStatus.class);

		RootResource resource = restTemplate.getForObject(baseURI + "v1/submissions/status/driver-20200811181757-0000", RootResource.class);
*/
		/*final RootResource resourceSupport = restTemplate.getForObject(baseURI, RootResource.class);

		if (resourceSupport != null) {
			if (resourceSupport.getApiRevision() == null) {
				throw new IllegalStateException("Incompatible version of Spark server detected.\n"
						+ "Follow instructions in the documentation for the version of the server you are "
						+ "using to download a compatible version of the shell.\n"
						+ "Documentation can be accessed at http://spark.apache.org/docs/latest/monitoring.html");
			}
			String serverRevision = resourceSupport.getApiRevision().toString();
			if (!String.valueOf(Version.REVISION).equals(serverRevision)) {
				String downloadURL = getLink(resourceSupport, "dashboard").getHref() + "#about";
				throw new IllegalStateException(String.format(
						"Incompatible version of Data Flow server detected.\n"
								+ "Trying to use shell which supports revision %s, while server revision is %s. Both "
								+ "revisions should be aligned.\n"
								+ "Follow instructions at %s to download a compatible version of the shell.",
						Version.REVISION, serverRevision, downloadURL));
			}

			this.submissionsLink = getLink(resourceSupport, SUBMISSIONS_REL);
			this.applicationsLink = getLink(resourceSupport, APPLICATIONS_REL);
			this.contextsLink = getLink(resourceSupport, CONTEXTS_REL);
		}
		else {
			this.submissionsLink = null;
			this.applicationsLink = null;
			this.contextsLink = null;
		}*/

		//String json = restTemplate.getForObject(baseURI + "/applications", String.class);

		//logger.info(json);
		//final RootResource resourceSupport = restTemplate.getForObject(baseURI, RootResource.class);
	}

	public SparkHiddenOperations getHiddenOperations()
	{
		return hiddenOperations;
	}

	public SparkEventLogOperations getEventLogOperations()
	{
		return eventLogOperations;
	}

	/**
	 * Invokes {@link #prepareRestTemplate(RestTemplate)}.
	 *
	 * @return RestTemplate with the required Jackson MixIns applied
	 */
	public static RestTemplate getDefaultSparkRestTemplate() {
		return prepareRestTemplate(null);
	}

	public static Link getLink(RepresentationModel<?> resourceSupport, String rel) {
		Link link = resourceSupport.getLink(rel).get();
		if (link == null) {
			throw new SparkJobServerException(
					"Server did not return a link for '" + rel + "', links: '" + resourceSupport + "'");
		}
		return link;
	}

	/**
	 * Will augment the provided {@link RestTemplate} with the Jackson Mixins required by
	 * Spring Cloud Data Flow, specifically:
	 *
	 * @param restTemplate Can be null. Instantiates a new {@link RestTemplate} if null
	 * @return RestTemplate with the required Jackson Mixins
	 */
	public static RestTemplate prepareRestTemplate(RestTemplate restTemplate) {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}

		restTemplate.setErrorHandler(new VndErrorResponseErrorHandler(restTemplate.getMessageConverters()));

		boolean containsMappingJackson2HttpMessageConverter = false;

		for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
			if (converter instanceof MappingJackson2HttpMessageConverter) {
				containsMappingJackson2HttpMessageConverter = true;
				final MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
				SparkTemplate.prepareObjectMapper(jacksonConverter.getObjectMapper());
			}
		}

		if (!containsMappingJackson2HttpMessageConverter) {
			throw new IllegalArgumentException(
					"The RestTemplate does not contain a required " + "MappingJackson2HttpMessageConverter.");
		}
		return restTemplate;
	}

	/**
	 * Mutable operation to add several required MixIns to the provided
	 * {@link ObjectMapper}.
	 *
	 * @param objectMapper Must not be null
	 * @return ObjectMapper with several mixIns applied
	 */
	public static ObjectMapper prepareObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "The objectMapper must not be null.");
		return objectMapper;
		/*return objectMapper
				.registerModule(new Jackson2HalModule())
				.addMixIn(JobExecution.class, JobExecutionJacksonMixIn.class)
				.addMixIn(JobParameters.class, JobParametersJacksonMixIn.class)
				.addMixIn(JobParameter.class, JobParameterJacksonMixIn.class)
				.addMixIn(JobInstance.class, JobInstanceJacksonMixIn.class)
				.addMixIn(ExitStatus.class, ExitStatusJacksonMixIn.class)
				.addMixIn(StepExecution.class, StepExecutionJacksonMixIn.class)
				.addMixIn(ExecutionContext.class, ExecutionContextJacksonMixIn.class)
				.addMixIn(StepExecutionHistory.class, StepExecutionHistoryJacksonMixIn.class);*/
	}
}
