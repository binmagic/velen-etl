package verification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import verification.entity.VerifyEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldRuleTDO
{
	private VerifyEnum.FieldKeyRuleType keyRuleType = VerifyEnum.FieldKeyRuleType.NONE;
	private String keyFormula = "";
	private String storeFormula = "";
}
