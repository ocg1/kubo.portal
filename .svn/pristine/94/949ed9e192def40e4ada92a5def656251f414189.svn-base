package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Scoring;
import mx.com.kubo.repositories.ScoringDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ScoringDaoImp implements ScoringDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	
	@Override
	public Scoring loadSelectedScoring(int scoring_result_id) {
		return em.find(Scoring.class,scoring_result_id);
	}

	
	@Transactional
	@Override
	public void saveScoring(Scoring newScoring) {
		log.info("saveScoring.ScoringDaoImp");
		String query="select MAX(s.scoring_result_id) from Scoring s";
		Integer id=0;
		id=(Integer) em.createQuery(query).getSingleResult();
		id= (id==null)?0:id;
		id++;
		newScoring.setScoring_result_id(id);	
		em.persist(newScoring);
	}
	
	@Transactional
	@Override
	public void updateScoring(Scoring newScoring) {
		log.info("updateScoring.ScoringDaoImp");
		em.merge(newScoring);
	}
	
	@Override
	public Scoring loadMaxScoringByProspectus(Integer prospectus_id,Integer company_id) {
		log.info("loadScoringbyProspectus.ScoringDaoImp");
		String query="select MAX(s.scoring_result_id) from Scoring s where s.prospectus_id = ? and s.company_id = ?";
		Integer id=0;
		id=(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();	
		
		if( id != null && id != null)
			return em.find(Scoring.class,id);
		else
			return null;
	}
	
	@Override
	public  Scoring loadScoringByBurSolNum(String bursolnum){
		
		String query="from Scoring where mx_solicitud_buro = ?";
		
		return (Scoring) em.createQuery(query).setParameter(1, bursolnum).getSingleResult();	
		
		
	}
	
	@Transactional
	@Override
	public boolean removeScoring(Scoring score) 
	{
		boolean bandera = false;	
		
		 try
		 {			    
		    Scoring  newscore = em.find(Scoring.class,score.getScoring_result_id());
		    
		    if(newscore != null)
		    {
		    	em.remove(newscore);
		    }
		    
		    bandera = true;
			    
		 } catch (Exception e) {
			 
			e.printStackTrace();e.printStackTrace();
			
			
			bandera = false;
			
		} finally {
			    em.close();
		}
		 
		 return bandera;
	}

	
	

	@Override
	@Transactional
	public int removeScoring(Integer prospectus_id, Integer company_id) 
	{
		
		String query = "delete from Scoring s where s.prospectus_id = ? and s.company_id = ?";
		int rows_deleted = 0;
		
		rows_deleted = em.createQuery(query)
						 .setParameter(1, prospectus_id)
						 .setParameter(2,company_id)
					     .executeUpdate();	
		
		return rows_deleted;
	}


	@Override
	public Integer getMaxScoringResultID() 
	{		
		try{
			
			String query = "select max(scoring_result_id) from Scoring";
			return (Integer) em.createQuery(query).getSingleResult();
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
}
