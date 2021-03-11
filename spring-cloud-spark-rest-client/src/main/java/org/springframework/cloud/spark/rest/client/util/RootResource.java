/*
 * Copyright 2017 the original author or authors.
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

package org.springframework.cloud.spark.rest.client.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cloud.spark.rest.client.Version;
import org.springframework.hateoas.RepresentationModel;

/**
 * Describes the other available resource endpoints, as well as provides information about
 * the server itself, such as API revision number.
 *
 * @author Eric Bottard
 */
public class RootResource extends RepresentationModel<RootResource> {

	private String highestProtocolVersion;
	private String serverSparkVersion;

	// For JSON un-marshalling
	private RootResource() {
	}

	public RootResource(String highestProtocolVersion, String serverSparkVersion)
	{
		this.highestProtocolVersion = highestProtocolVersion;
		this.serverSparkVersion = serverSparkVersion;
	}

	@JsonProperty(Version.REVISION_KEY)
	public String getHighestProtocolVersion()
	{
		return highestProtocolVersion;
	}

	public void setHighestProtocolVersion(String highestProtocolVersion)
	{
		this.highestProtocolVersion = highestProtocolVersion;
	}

	@JsonProperty(Version.SPARK_VISION_KEY)
	public String getServerSparkVersion()
	{
		return serverSparkVersion;
	}

	public void setServerSparkVersion(String serverSparkVersion)
	{
		this.serverSparkVersion = serverSparkVersion;
	}
}
