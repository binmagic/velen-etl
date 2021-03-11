package org.springframework.cloud.spark.rest.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.spark.rest.client.support.SparkPropertyKeys;

import java.util.Set;

@ConfigurationProperties(prefix = SparkPropertyKeys.PREFIX + "client")
public class SparkClientProperties
{
	/**
	 * The Spark External Service URI.
	 */
	private String externalUri = "http://localhost:6066";

	/**
	 * The Spark History Server URI.
	 */
	private String historyUri = "http://localhost:18080";

	private Authentication authentication = new Authentication();

	/**
	 * Skip Ssl validation.
	 */
	private boolean skipSslValidation;

	public boolean isSkipSslValidation() {
		return skipSslValidation;
	}

	public void setSkipSslValidation(boolean skipSslValidation) {
		this.skipSslValidation = skipSslValidation;
	}

	public String getExternalUri()
	{
		return externalUri;
	}

	public void setExternalUri(String externalUri)
	{
		this.externalUri = externalUri;
	}

	public String getHistoryUri()
	{
		return historyUri;
	}

	public void setHistoryUri(String historyUri)
	{
		this.historyUri = historyUri;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public static class Authentication {

		private Basic basic = new Basic();
		private Oauth2 oauth2 = new Oauth2();

		/**
		 * OAuth2 Access Token.
		 */
		private String accessToken;

		/**
		 * OAuth2 Client Id.
		 */
		private String clientId;

		/**
		 * OAuth2 Client Secret.
		 */
		private String clientSecret;

		/**
		 * OAuth2 Token Uri.
		 */
		private String tokenUri;

		/**
		 * OAuth2 Scopes.
		 */
		private Set<String> scope;

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getClientId() {
			return clientId;
		}

		public void setClientId(String clientId) {
			this.clientId = clientId;
		}

		public String getClientSecret() {
			return clientSecret;
		}

		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}

		public String getTokenUri() {
			return tokenUri;
		}

		public void setTokenUri(String tokenUri) {
			this.tokenUri = tokenUri;
		}

		public Set<String> getScope() {
			return scope;
		}

		public void setScope(Set<String> scope) {
			this.scope = scope;
		}

		public Basic getBasic() {
			return basic;
		}

		public void setBasic(Basic basic) {
			this.basic = basic;
		}

		public Oauth2 getOauth2() {
			return oauth2;
		}

		public void setOauth2(Oauth2 oauth2) {
			this.oauth2 = oauth2;
		}

		public static class Basic {

			/**
			 * The login username.
			 */
			private String username;

			/**
			 * The login password.
			 */
			private String password;

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}
		}

		public static class Oauth2 {

			private String clientRegistrationId;
			private String username;
			private String password;

			public String getClientRegistrationId() {
				return clientRegistrationId;
			}

			public void setClientRegistrationId(String clientRegistrationId) {
				this.clientRegistrationId = clientRegistrationId;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}
		}
	}
}
