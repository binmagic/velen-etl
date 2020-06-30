package com.velen.etl.common.response;

import com.velen.etl.common.entity.ReCodeType;

public class ResultGenerator
{

	private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

	public static Result genSuccessResult() {
		return new Result()
				.setCode(ReCodeType.ResultCode.SUCCESS)
				.setSuccess(true)
				.setMessage(DEFAULT_SUCCESS_MESSAGE);
	}

	public static Result genSuccessResult(Object data) {
		return new Result()
				.setCode(ReCodeType.ResultCode.SUCCESS)
				.setSuccess(true)
				.setMessage(DEFAULT_SUCCESS_MESSAGE)
				.setData(data);
	}

	public static Result genFailResult(String message) {
		return new Result()
				.setCode(ReCodeType.ResultCode.FAIL)
				.setSuccess(false)
				.setMessage(message);
	}
}