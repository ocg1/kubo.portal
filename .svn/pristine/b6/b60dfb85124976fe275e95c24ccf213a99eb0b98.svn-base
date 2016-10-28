package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.AlertEvent;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.repositories.AlertEventDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlertEventDaoImp implements Serializable, AlertEventDao {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Transactional
    @Override
    public boolean saveAlertEvent( AlertEvent alertevent){
    	
    	try{
    		
    		em.persist(alertevent);
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
    	
    }
	
    @Override
    public boolean haveAlertsEventPerson(int prospectus_id, int company_id){
    	
    	try{
    		
    		
    				
	    		String query = "from AlertEvent where prospectus_id_viewed = ? and company_id = ? and proyect_loan_id is null and proyect_id is null ";
		    	List<AlertEvent> list = em.createQuery(query,AlertEvent.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
		    	return list.size()>0;
    		
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
    @Override
    public boolean haveAlertsEventProyectLoan(ProyectLoanPK pylnPk){
    	
    	try{
    		
    		if(pylnPk !=null){
    		
	    		String query = "from AlertEvent where prospectus_id_viewed = ? and company_id = ? and proyect_loan_id = ? and proyect_id = ? ";
		    	List<AlertEvent> list = em.createQuery(query,AlertEvent.class)
		    									.setParameter(1, pylnPk.getProspectus_id())
		    									.setParameter(2, pylnPk.getCompany_id())
		    									.setParameter(3, pylnPk.getProyect_loan_id())
		    									.setParameter(4, pylnPk.getProyect_id())
		    									.getResultList();
		    	return list.size()>0;
		    	
    		}else{
    			return false;
    		}
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
    @Override
    public List<AlertEvent> getAlertsEventPersonList(int prospectus_id, int company_id){
    	
    	try{
    		
    		String query = "from AlertEvent where prospectus_id_viewed = ? and company_id = ? and proyect_loan_id is null and proyect_id is null ";
	    	List<AlertEvent> list = em.createQuery(query,AlertEvent.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
	    	return list;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    @Override
    public List<AlertEvent> getAlertsEventProyectLoanList(ProyectLoanPK pylnPk){
    	
    	try{
    		
    		String query = "from AlertEvent where prospectus_id_viewed = ? and company_id = ? and proyect_loan_id = ? and proyect_id = ? ";
	    	List<AlertEvent> list = em.createQuery(query,AlertEvent.class)
	    									.setParameter(1, pylnPk.getProspectus_id())
	    									.setParameter(2, pylnPk.getCompany_id())
	    									.setParameter(3, pylnPk.getProyect_loan_id())
	    									.setParameter(4, pylnPk.getProyect_id())
	    									.getResultList();
	    	return list;
    		
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		return null;
    		
    	}
    	
    }
    
    
}
