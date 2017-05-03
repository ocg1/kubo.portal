package mx.com.kubo.repositories;

import mx.com.kubo.model.RuleExecution;

public interface RuleExecutionDao {

	public boolean saveRuleExecution( RuleExecution re );
	public RuleExecution getMaxRuleExecution( Integer prospectus , Integer rule_id );
	
}
