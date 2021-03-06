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

package org.springframework.cloud.spark.rest.client;

/**
 * Used by clients and servers to assert API compatibility.
 *
 * @author Eric Bottard
 */
public class Version
{

	/**
	 * Should be incremented each time the API evolves whether it's a breaking change or
	 * not.
	 */
	public static final int REVISION = 14;

	public static final String VALIDATION_PROTOCOL_VERSION = "v1";
	public static final String VALIDATION_RELATION_VERSION = "2.4.5";

	public static final String REVISION_KEY = "highestProtocolVersion";

	public static final String SPARK_VISION_KEY = "serverSparkVersion";

	private Version() {

	}

}
