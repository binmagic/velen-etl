/*
 * Copyright 2014-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.spark.rest.client;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The exception indicates errors occurs when using instance 
 * of <code>ISparkJobServerClient</code>.
 *
 */
public class SparkJobServerException extends RuntimeException
{

	private static final long serialVersionUID = -5065403696198358625L;

	private final VndErrors vndErrors;

	/**
	 * Constructs a new <code>SparkJobServerClientException</code> instance
	 * with the specified detail message. The cause is not initialized, and 
	 * may subsequently be initialized by a call to initCause.
	 * 
	 * @param message the detail message. The detail message is saved for 
	 *        later retrieval by the getMessage() method.
	 */
	/*public SparkJobServerClientException(String message) {
		super(message);
	}*/
	
	/**
     * Constructs a new <code>SparkJobServerClientException</code> instance
     * with the specified detail message and cause.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     */
	public SparkJobServerException(String message) {
		this(new VndErrors("warning", message));
	}

	/**
	 * Initializes a {@link SparkJobServerException} with the provided (mandatory error).
	 *
	 * @param vndErrors Must not be null
	 */
	public SparkJobServerException(VndErrors vndErrors) {
		Assert.notNull(vndErrors, "The provided vndErrors parameter must not be null.");
		this.vndErrors = vndErrors;
	}

	@Override
	public String getMessage() {
		final List<String> errorMessages = new ArrayList<>(0);
		for (VndErrors.VndError e : vndErrors) {
			errorMessages.add(e.getMessage());
		}
		return StringUtils.collectionToDelimitedString(errorMessages, "\n");
	}
}
