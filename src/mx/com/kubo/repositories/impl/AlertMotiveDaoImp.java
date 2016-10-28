package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.AlertMotive;
import mx.com.kubo.repositories.AlertMotiveDao;

import org.springframework.stereotype.Repository;

@Repository
public class AlertMotiveDaoImp implements Serializable,AlertMotiveDao {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public List<AlertMotive> getAllAlertMotiveList(){
    	
    	try{
	    	
    		String query = "from AlertMotive ";
	    	List<AlertMotive> list = em.createQuery(query,AlertMotive.class).getResultList();
	    	return list;
	    	
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    @Override
    public List<AlertMotive> getAlertMotiveListByAlert(int alert_id,int company_id){
    	
    	try{
	    	
    		String query = "from AlertMotive where pk.alert_id = ? and pk.company_id = ? ";
	    	List<AlertMotive> list = em.createQuery(query,AlertMotive.class).setParameter(1, alert_id).setParameter(2, company_id).getResultList();
	    	return list;
	    	
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
	
}
