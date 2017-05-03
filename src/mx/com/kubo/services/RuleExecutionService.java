package mx.com.kubo.services;

import mx.com.kubo.model.RuleExecution;

public interface RuleExecutionService {

	public boolean saveRuleExecution( RuleExecution re );
	public RuleExecution getMaxRuleExecution( Integer prospectus , Integer rule_id );
	
}
