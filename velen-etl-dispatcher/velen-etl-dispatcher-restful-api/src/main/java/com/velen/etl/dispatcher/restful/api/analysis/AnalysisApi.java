package com.velen.etl.dispatcher.restful.api.analysis;

import org.springframework.cloud.openfeign.FeignClient;

@Deprecated
@FeignClient("velen-etl-dispatcher")
public interface AnalysisApi
{
}
