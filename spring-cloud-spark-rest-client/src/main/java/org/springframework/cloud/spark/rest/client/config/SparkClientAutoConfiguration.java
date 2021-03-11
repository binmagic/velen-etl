package org.springframework.cloud.spark.rest.client.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.spark.rest.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Configuration class to provide default beans for {@link ISparkJobClient} instances.
 */
@Configuration
@EnableConfigurationProperties(SparkClientProperties.class)
public class SparkClientAutoConfiguration
{
	private static Log logger = LogFactory.getLog(SparkClientAutoConfiguration.class);

	private static final String DEFAULT_REGISTRATION_ID = "default";

	@Autowired
	private SparkClientProperties properties;

	@Autowired(required = false)
	private RestTemplate restTemplate;

	@Bean
	@ConditionalOnMissingBean(SparkTemplate.class)
	public SparkTemplate sparkTemplate() throws Exception {

		RestTemplate template = SparkTemplate.prepareRestTemplate(restTemplate);
		/*final HttpClientConfigurer httpClientConfigurer = HttpClientConfigurer.create(new URI(properties.getServerUri()))
				.skipTlsCertificateVerification(properties.isSkipSslValidation());

		if (StringUtils.hasText(this.properties.getAuthentication().getAccessToken())) {
			template.getInterceptors().add(new OAuth2AccessTokenProvidingClientHttpRequestInterceptor(this.properties.getAuthentication().getAccessToken()));
			logger.debug("Configured OAuth2 Access Token for accessing the Data Flow Server");
		}
		else if (StringUtils.hasText(this.properties.getAuthentication().getClientId())) {
			ClientRegistration clientRegistration = clientRegistrations.findByRegistrationId(DEFAULT_REGISTRATION_ID);
			template.getInterceptors().add(clientCredentialsTokenResolvingInterceptor(clientRegistration,
					clientRegistrations, this.properties.getAuthentication().getClientId()));
			logger.debug("Configured OAuth2 Client Credentials for accessing the Data Flow Server");
		}
		else if(!StringUtils.isEmpty(properties.getAuthentication().getBasic().getUsername()) &&
				!StringUtils.isEmpty(properties.getAuthentication().getBasic().getPassword())){
			httpClientConfigurer.basicAuthCredentials(properties.getAuthentication().getBasic().getUsername(),
					properties.getAuthentication().getBasic().getPassword());
			template.setRequestFactory(httpClientConfigurer.buildClientHttpRequestFactory());
		}
		else if (oauth2ClientProperties != null && !oauth2ClientProperties.getRegistration().isEmpty()
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getUsername())
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getPassword())) {
			ClientHttpRequestInterceptor bearerTokenResolvingInterceptor = bearerTokenResolvingInterceptor(
					oauth2ClientProperties, properties.getAuthentication().getOauth2().getUsername(),
					properties.getAuthentication().getOauth2().getPassword(),
					properties.getAuthentication().getOauth2().getClientRegistrationId());
			template.getInterceptors().add(bearerTokenResolvingInterceptor);
			logger.debug("Configured OAuth2 Bearer Token resolving for accessing the Data Flow Server");
		}
		else {
			logger.debug("Not configuring security for accessing the Data Flow Server");
		}*/

		return new SparkTemplate(new URI(properties.getExternalUri()), new URI(properties.getHistoryUri()), template);
	}

	@Deprecated
	@Bean
	@ConditionalOnMissingBean(SparkHiddenOperations.class)
	public SparkHiddenOperations sparkExternalTemplate() throws Exception {

		RestTemplate template = SparkTemplate.prepareRestTemplate(restTemplate);
		/*final HttpClientConfigurer httpClientConfigurer = HttpClientConfigurer.create(new URI(properties.getServerUri()))
				.skipTlsCertificateVerification(properties.isSkipSslValidation());

		if (StringUtils.hasText(this.properties.getAuthentication().getAccessToken())) {
			template.getInterceptors().add(new OAuth2AccessTokenProvidingClientHttpRequestInterceptor(this.properties.getAuthentication().getAccessToken()));
			logger.debug("Configured OAuth2 Access Token for accessing the Data Flow Server");
		}
		else if (StringUtils.hasText(this.properties.getAuthentication().getClientId())) {
			ClientRegistration clientRegistration = clientRegistrations.findByRegistrationId(DEFAULT_REGISTRATION_ID);
			template.getInterceptors().add(clientCredentialsTokenResolvingInterceptor(clientRegistration,
					clientRegistrations, this.properties.getAuthentication().getClientId()));
			logger.debug("Configured OAuth2 Client Credentials for accessing the Data Flow Server");
		}
		else if(!StringUtils.isEmpty(properties.getAuthentication().getBasic().getUsername()) &&
				!StringUtils.isEmpty(properties.getAuthentication().getBasic().getPassword())){
			httpClientConfigurer.basicAuthCredentials(properties.getAuthentication().getBasic().getUsername(),
					properties.getAuthentication().getBasic().getPassword());
			template.setRequestFactory(httpClientConfigurer.buildClientHttpRequestFactory());
		}
		else if (oauth2ClientProperties != null && !oauth2ClientProperties.getRegistration().isEmpty()
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getUsername())
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getPassword())) {
			ClientHttpRequestInterceptor bearerTokenResolvingInterceptor = bearerTokenResolvingInterceptor(
					oauth2ClientProperties, properties.getAuthentication().getOauth2().getUsername(),
					properties.getAuthentication().getOauth2().getPassword(),
					properties.getAuthentication().getOauth2().getClientRegistrationId());
			template.getInterceptors().add(bearerTokenResolvingInterceptor);
			logger.debug("Configured OAuth2 Bearer Token resolving for accessing the Data Flow Server");
		}
		else {
			logger.debug("Not configuring security for accessing the Data Flow Server");
		}*/

		return new SparkHiddenTemplate(new URI(properties.getExternalUri()), template);
	}

	@Deprecated
	@Bean
	@ConditionalOnMissingBean(SparkEventLogOperations.class)
	public SparkEventLogOperations sparkHistoryTemplate() throws Exception {

		RestTemplate template = SparkTemplate.prepareRestTemplate(restTemplate);
		/*final HttpClientConfigurer httpClientConfigurer = HttpClientConfigurer.create(new URI(properties.getServerUri()))
				.skipTlsCertificateVerification(properties.isSkipSslValidation());

		if (StringUtils.hasText(this.properties.getAuthentication().getAccessToken())) {
			template.getInterceptors().add(new OAuth2AccessTokenProvidingClientHttpRequestInterceptor(this.properties.getAuthentication().getAccessToken()));
			logger.debug("Configured OAuth2 Access Token for accessing the Data Flow Server");
		}
		else if (StringUtils.hasText(this.properties.getAuthentication().getClientId())) {
			ClientRegistration clientRegistration = clientRegistrations.findByRegistrationId(DEFAULT_REGISTRATION_ID);
			template.getInterceptors().add(clientCredentialsTokenResolvingInterceptor(clientRegistration,
					clientRegistrations, this.properties.getAuthentication().getClientId()));
			logger.debug("Configured OAuth2 Client Credentials for accessing the Data Flow Server");
		}
		else if(!StringUtils.isEmpty(properties.getAuthentication().getBasic().getUsername()) &&
				!StringUtils.isEmpty(properties.getAuthentication().getBasic().getPassword())){
			httpClientConfigurer.basicAuthCredentials(properties.getAuthentication().getBasic().getUsername(),
					properties.getAuthentication().getBasic().getPassword());
			template.setRequestFactory(httpClientConfigurer.buildClientHttpRequestFactory());
		}
		else if (oauth2ClientProperties != null && !oauth2ClientProperties.getRegistration().isEmpty()
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getUsername())
				&& StringUtils.hasText(properties.getAuthentication().getOauth2().getPassword())) {
			ClientHttpRequestInterceptor bearerTokenResolvingInterceptor = bearerTokenResolvingInterceptor(
					oauth2ClientProperties, properties.getAuthentication().getOauth2().getUsername(),
					properties.getAuthentication().getOauth2().getPassword(),
					properties.getAuthentication().getOauth2().getClientRegistrationId());
			template.getInterceptors().add(bearerTokenResolvingInterceptor);
			logger.debug("Configured OAuth2 Bearer Token resolving for accessing the Data Flow Server");
		}
		else {
			logger.debug("Not configuring security for accessing the Data Flow Server");
		}*/

		//return new SparkExternalTemplate(new URI(properties.getExternalUri()), template);
		return null;
	}



}
