package com.velen.etl;


public interface ResultCode
{


	int INVALID_PARAMETERS = 1000; // parameter invalid


	int PROJECT_EXISTS = 2000;
	int TABLE_EXISTS = 2000;

	int GENERATOR_DATABASE_EXISTS = 3000;
	int GENERATOR_DATABASE_NOT_EXISTS = 3000;
	int GENERATOR_DATABASE_FAILURE = 3000;
	int GENERATOR_TABLE_EXISTS = 3000;
	int GENERATOR_TABLE_FAILURE = 3000;
	int GENERATOR_TABLE_NOT_EXISTS = 3000;
	int GENERATOR_COLUMN_EXISTS = 3000;
	int GENERATOR_COLUMN_FAILURE = 3000;


	int DISPATCH_UPDATE_STREAM_FAILURE = 3000; // stream flow update failure
	int DISPATCH_UPDATE_TASK_FAILURE = 3001; // task flow update failure

	int DISPATCH_INVALID_PROCESS = 3010; // process of input is invalid
	int DISPATCH_INVALID_PROCEDURE = 3011; // procedure of input is invalid
	int DISPATCH_UNSUPPORTED_PLATFORM = 3012; // platform of input is unsupported

}
