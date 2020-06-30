package com.velen.etl.dispatcher.restful.api.yarn;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("velen-etl-dispatcher-yarn")
public interface YarnApi
{
}
