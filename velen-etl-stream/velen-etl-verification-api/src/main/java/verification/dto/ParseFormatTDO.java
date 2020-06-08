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
public class ParseFormatTDO
{
	private VerifyEnum.InputParseType inputParseType;
	private String formula;
}
