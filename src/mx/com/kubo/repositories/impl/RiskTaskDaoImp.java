package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.RiskTask;
import mx.com.kubo.repositories.RiskTaskDao;

@Repository
public class RiskTaskDaoImp implements RiskTaskDao, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

	public List<RiskTask> getRiskTaskByBurSolNum( String bursolnum ){
		
		try{
			
			return em.createQuery("from RiskTask where mx_solicitud_buro = ? ", RiskTask.class).setParameter(1, bursolnum).getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	public boolean saveRiskTask( RiskTask risktask ){
		
		try{
			
			em.persist(risktask);
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public RiskTask getRiskTaskByBurSolNumTaskId( String bursolnum, int task_id  ){
		
		try{
			
			return em.createQuery("from RiskTask where mx_solicitud_buro = ? and task_id = ? ", RiskTask.class)
							.setParameter(1, bursolnum)
							.setParameter(2, task_id)
							.getSingleResult();
			
		}catch(NoResultException e){
			
			return null;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
