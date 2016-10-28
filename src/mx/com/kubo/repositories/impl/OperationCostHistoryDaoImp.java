package mx.com.kubo.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.OperationCostHistory;
import mx.com.kubo.repositories.OperationCostHistoryDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OperationCostHistoryDaoImp implements OperationCostHistoryDao {
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
    @Override
    public List<OperationCostHistory> getOperationCostHistoryByProyectLoan(int proyect_loan_id){
    	
    	return em.createQuery("from OperationCostHistory where pk.proyect_loan_id = ? ",OperationCostHistory.class)
    			.setParameter(1, proyect_loan_id)
    			.getResultList();
    	
    }
    
    @Override
    public List<OperationCostHistory> getOperationCostHistoryListByProspectus( int prospectus_id, int company_id ){
    	return em.createQuery("from OperationCostHistory where pk.prospectus_id = ? and pk.company_id = ? ",OperationCostHistory.class)
    			.setParameter(1, prospectus_id)
    			.setParameter(2, company_id)
    			.getResultList();
    }
    
    @Override
    public OperationCostHistory getLastOperationCostHistoryByProspectus( int prospectus_id, int company_id ){
    	
		String query="select MAX(ih.creation_date) from OperationCostHistory ih where ih.pk.prospectus_id = ? and ih.pk.company_id = ?";
		Date creationDate= null;
		try{
			creationDate=(Date) em.createQuery(query)
								.setParameter(1, prospectus_id)
								.setParameter(2, company_id)
								.getSingleResult();
    	
			if( creationDate !=null ){
				
				return em.createQuery("from OperationCostHistory where creation_date = ? and pk.prospectus_id = ? and pk.company_id = ?  ",OperationCostHistory.class)
	    			.setParameter(1, creationDate)
	    			.setParameter(2, prospectus_id)
					.setParameter(3, company_id)
	    			.getSingleResult();
				
			}else{
				return null;
			}
    	
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
    @Transactional
	@Override
	public boolean saveOperationCostHistory( OperationCostHistory newOperationCostHistory ) {
    	
    	try{
    		
    		em.persist(newOperationCostHistory);
    		return true;
    		
    	}catch(Exception e){

    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
    
    @Transactional
	@Override
	public boolean deleteOperationCostHistory( OperationCostHistory newOperationCostHistory ) {
    	
    	try{
    		
    		em.merge( newOperationCostHistory );
    		return true;
    		
    	}catch(Exception e){

    		e.printStackTrace();
    		return false;
    		
    	}
    	
    }
}
