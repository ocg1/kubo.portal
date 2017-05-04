package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.kubo.model.RuleExecution;
import mx.com.kubo.repositories.RuleExecutionDao;
import mx.com.kubo.services.RuleExecutionService;

@Service
public class RuleExecutionServiceImp implements RuleExecutionService, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	RuleExecutionDao dao;
	
	public boolean saveRuleExecution( RuleExecution re ){
		return dao.saveRuleExecution(re);
	}
	
	public RuleExecution getMaxRuleExecution( Integer prospectus , Integer rule_id ){
		return dao.getMaxRuleExecution(prospectus, rule_id);
	}
	
	public List<RuleExecution> getRuleExecutionLst(Integer prospectus , Integer rule_id){
		return dao.getRuleExecutionLst(  prospectus , rule_id );
	}
	
}
