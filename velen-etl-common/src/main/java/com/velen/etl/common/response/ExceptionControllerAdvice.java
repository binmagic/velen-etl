package com.velen.etl.common.response;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice
{
	/*@ExceptionHandler(Exception.class)
	public Result jsonErrorHandler(HttpServletRequest req, Exception e){
		return ResultGenerator.genFailResult(e.getMessage());
	}*/
}
