package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.RuleExecution;

public interface RuleExecutionService {

	public boolean saveRuleExecution( RuleExecution re );
	public RuleExecution getMaxRuleExecution( Integer prospectus , Integer rule_id );
	public List<RuleExecution> getRuleExecutionLst(Integer prospectus , Integer rule_id);
	
}
