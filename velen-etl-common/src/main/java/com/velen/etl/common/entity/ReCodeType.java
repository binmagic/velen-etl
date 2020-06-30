package com.velen.etl.common.entity;

public interface ReCodeType
{
	int code();
	ReCodeType type();


	enum ResultCode implements ReCodeType
	{
		SUCCESS(200),//成功
		FAIL(400),//失败
		UNAUTHORIZED(401),//未认证（签名错误）
		NOT_FOUND(404),//接口不存在
		INTERNAL_SERVER_ERROR(500);//服务器内部错误

		private final int code;

		ResultCode(int code) {
			this.code = code;
		}


		@Override
		public int code()
		{
			return code;
		}

		@Override
		public ReCodeType type()
		{
			return null;
		}
	}



}
