package com.velen.etl.dispatcher.restful.api.flink;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * https://ci.apache.org/projects/flink/flink-docs-release-1.10/monitoring/rest_api.html#api
 */
@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface FlinkApi
{
}
