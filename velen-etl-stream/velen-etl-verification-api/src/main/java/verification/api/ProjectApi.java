package verification.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import verification.dto.FieldRuleTDO;
import verification.dto.ParseFormatTDO;

import java.util.List;

@FeignClient("velen-etl-verification")
public interface ProjectApi
{
	/**
	 * 创建项目
	 */
	@PostMapping("/project/verification/create")
	ResponseEntity create(@RequestParam("appId") String appId, @RequestParam("operator") String operator);


	/**
	 * 设置清洗规则
	 */
	//@PostMapping("/project/create")
	//ResponseEntity extract(@RequestParam("project") String project);

	/**
	 * 设置规则
	 */
	@PostMapping("/project/verification/set")
	ResponseEntity set(@RequestParam("appId") String appId, @RequestParam("enforced") Boolean enforcedVerify
			, @RequestParam("formats") List<ParseFormatTDO> parseFormatsTDO, @RequestParam("fields") List<FieldRuleTDO> fieldRulesTDO
			, @RequestParam("operator") String operator);


}
