package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.bean.BehaviorBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BlacklistIp;
import mx.com.kubo.model.BlacklistPassword;
import mx.com.kubo.model.BlacklistPhone;
import mx.com.kubo.model.FraudeDetection;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.References;
import mx.com.kubo.repositories.BehaviorProcessDao;

@Repository
public class BehaviorProcessDaoImp implements Serializable,BehaviorProcessDao {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    public  BehaviorBean getBehaviorElements( Integer prospectus_id ){
    	
    	try{
    		
    		BehaviorBean behaviorbean = new BehaviorBean();
    		
    		em.createQuery("from BlacklistIp", BlacklistIp.class).getResultList();
    		
    		
    		behaviorbean.setLstBlkIp(em.createQuery("from BlacklistIp", BlacklistIp.class).getResultList());
    		behaviorbean.setLstBlkPass(em.createQuery("from BlacklistPassword", BlacklistPassword.class).getResultList());
    		behaviorbean.setLstBlkphone(em.createQuery("from BlacklistPhone", BlacklistPhone.class).getResultList());
    		
    		
    		List<References>  lstreferences = em.createQuery("from References where referencePK.prospectus_id = ? ", References.class).setParameter(1, prospectus_id).getResultList();
    		
    		if( lstreferences != null && lstreferences.size() > 0 ){
    			behaviorbean.setLstreferences(lstreferences);
    		}else{
    			behaviorbean.setLstreferences(null);
    		}
    		
    		List<Access>  lstUserAccess = em.createQuery("from Access where prospectus_id = ? and prospectus_id_coach is null  ", Access.class).setParameter(1, prospectus_id).getResultList();
    		
    		if( lstUserAccess != null && lstUserAccess.size() > 0 ){
    			behaviorbean.setLstUserAccess(lstUserAccess);
    		}else{
    			behaviorbean.setLstUserAccess(null);
    		}
    		
    		List<PasswordHistory>  lstUserPass = em.createQuery("from PasswordHistory where pwdhpk.prospectus_id = ? ", PasswordHistory.class).setParameter(1, prospectus_id).getResultList();
    		
    		if( lstUserPass != null && lstUserPass.size() > 0 ){
    			behaviorbean.setLstUserPass(lstUserPass);
    		}else{
    			behaviorbean.setLstUserPass(null);
    		}
    		
    		List<Phone>  lstUserPhones = em.createQuery("from Phone where phonePk.prospectus_id = ? ", Phone.class).setParameter(1, prospectus_id).getResultList();
    		
    		if( lstUserPhones != null && lstUserPhones.size() > 0 ){
    			behaviorbean.setLstUserPhones(lstUserPhones);
    		}else{
    			behaviorbean.setLstUserPhones(null);
    		}
    		
    		return behaviorbean;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    @Transactional
    public boolean saveFraudeDetection( FraudeDetection fraudedet ){
    	
    	try{
    		
    		em.persist(fraudedet);
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    	
    }
    
    @Transactional
    public boolean updateFraudeDetection( FraudeDetection fraudedet ){
    	
    	try{
    		
    		em.merge(fraudedet);
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    	
    }
    
    public FraudeDetection getFraudeDetection( Integer company_id, Integer prospectus_id ){
    	
    	try{
    		
    		List<FraudeDetection> l = em.createQuery("from FraudeDetection where company_id = ? and prospectus_id = ?  ", FraudeDetection.class).setParameter(1, company_id).setParameter(2, prospectus_id).getResultList();
    		
    		if( l != null && l.size() > 0 ){
    		
    			return l.get(0);
    		
    		}else{
    			
    			return null;
    			
    		}
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
	
}
