package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.RuleExecution;
import mx.com.kubo.repositories.RuleExecutionDao;

@Repository
public class RuleExecutionDaoImp implements Serializable, RuleExecutionDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional
	public boolean saveRuleExecution( RuleExecution re ){
		
		try{
			
			em.persist(re);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	
	public RuleExecution getMaxRuleExecution( Integer prospectus , Integer rule_id ){
		
		try{
			
			String query = "select MAX( rule_execution_id ) from RuleExecution where prospectus_id = ? and rule_id = ? ";
			
			int max_rule_execution = 0;
			
			max_rule_execution = (Integer) em.createQuery(query).setParameter(1, prospectus).setParameter(2, rule_id).getSingleResult();
				
			query = "from RuleExecution where rule_execution_id = ? ";
			
			return em.createQuery(query,RuleExecution.class).setParameter(1, max_rule_execution).getSingleResult();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public List<RuleExecution> getRuleExecutionLst(Integer prospectus , Integer rule_id){
		
		try{
			String query = "from RuleExecution where prospectus_id = ? and rule_id = ? order by rule_execution_id desc ";
			
			return  em.createQuery(query,RuleExecution.class).setParameter(1, prospectus).setParameter(2, rule_id).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
