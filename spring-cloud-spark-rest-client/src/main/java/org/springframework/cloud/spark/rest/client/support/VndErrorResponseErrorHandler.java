/*
 * Copyright 2013-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.spark.rest.client.support;

import org.springframework.cloud.spark.rest.client.SparkJobServerException;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors.VndError;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Extension of {@link DefaultResponseErrorHandler} that knows how to de-serialize a
 * {@link VndError} structure.
 *
 * @author Eric Bottard
 * @author Gunnar Hillert
 */
public class VndErrorResponseErrorHandler extends DefaultResponseErrorHandler {

	private ResponseExtractor<VndErrors> vndErrorsExtractor;

	private ResponseExtractor<VndError> vndErrorExtractor;

	public VndErrorResponseErrorHandler(List<HttpMessageConverter<?>> messageConverters) {
		vndErrorsExtractor = new HttpMessageConverterExtractor<VndErrors>(VndErrors.class, messageConverters);
		vndErrorExtractor = new HttpMessageConverterExtractor<VndError>(VndError.class, messageConverters);
	}

	/**
	 * This server responds with different HTTP codes depending on the situation:
	 *   200 OK - Request was processed successfully
	 *   400 BAD REQUEST - Request was malformed, not successfully validated, or of unexpected type
	 *   468 UNKNOWN PROTOCOL VERSION - Request specified a protocol this server does not understand
	 *   500 INTERNAL SERVER ERROR - Server throws an exception internally while processing the request
	 */
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException
	{
		int statusCode = response.getRawStatusCode();

		return super.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		VndErrors vndErrors = null;
		try {
			if (HttpStatus.FORBIDDEN.equals(response.getStatusCode())) {
				vndErrors = new VndErrors(vndErrorExtractor.extractData(response));
			}
			else {
				vndErrors = vndErrorsExtractor.extractData(response);
			}
		}
		catch (Exception e) {
			super.handleError(response);
		}
		if (vndErrors != null) {
			throw new SparkJobServerException(vndErrors);
		}
		else {
			//see https://github.com/spring-cloud/spring-cloud-dataflow/issues/2898
			final String message = StringUtils.hasText(response.getStatusText())
					? response.getStatusText()
					: String.valueOf(response.getStatusCode());

			throw new SparkJobServerException(
				new VndErrors(String.valueOf(response.getStatusCode()), message));
		}

	}
}
