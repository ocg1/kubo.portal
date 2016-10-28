package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Tutor;
import mx.com.kubo.repositories.TutorDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TutorDaoImp implements Serializable, TutorDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;


	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@Transactional
	public boolean addTutor( Tutor tutor ){
		
		String query = "select MAX(t.tutor_id) from Tutor t";
		
		int idtutor = 0;
		
		if(em.createQuery(query).getSingleResult() != null)
		{
			idtutor = (Integer) em.createQuery(query).getSingleResult();
			idtutor++;
			
		} else {
			
			idtutor++;
		}
		
		tutor.setTutor_id(idtutor);
		try{
			em.persist(tutor);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public List<Tutor> getTutorByProspectus( Integer prospectus_id, Integer company_id ){
		String query = "from Tutor t where t.prospectus_id = ? and company_id = ?";
		
		try{
			return em.createQuery(query,Tutor.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
		}catch(Exception e ){
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Override
	public List<Tutor> getProspectusFromTutor( Integer prospectus_id, Integer company_id ){
		String query = "from Tutor t where t.prospectus_id_tutor = ? and company_id = ?";
		
		try{
			return em.createQuery(query,Tutor.class).setParameter(1, prospectus_id).setParameter(2, company_id).getResultList();
		}catch(Exception e ){
			e.printStackTrace();
			return null;
			
		}
	}
	
	
}
