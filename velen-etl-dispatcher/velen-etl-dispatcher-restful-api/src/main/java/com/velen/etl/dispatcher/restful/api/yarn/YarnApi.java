package com.velen.etl.dispatcher.restful.api.yarn;

import org.springframework.cloud.openfeign.FeignClient;

@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface YarnApi
{
}
