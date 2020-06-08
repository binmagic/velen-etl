package verification.entity;

public interface VerifyEnum
{

	/**
	 * 接收字段的Key验证规则
	 */
	enum FieldKeyRuleType implements VerifyEnum
	{
		NONE,
		REGEX,
		POSITION,
		FIELD,
	}

	/**
	 * 数据解析类型
	 */
	enum InputParseType implements VerifyEnum
	{
		JSON,
		REGEX,
		SPLIT,
	}
}
