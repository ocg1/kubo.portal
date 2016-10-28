package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.References;
import mx.com.kubo.model.ReferencesMotive;
import mx.com.kubo.model.ReferencesPK;
import mx.com.kubo.model.ReferencesScore;
import mx.com.kubo.repositories.ReferencesDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ReferencesDaoImp implements ReferencesDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	 @PersistenceContext
	    public void setEntityManager(EntityManager em) {
	        this.em = em;
	    }
	
	@Override
	public  References loadSelectedReferece(ReferencesPK id){
		try{
			return em.find(References.class,id);
		}catch(Exception e){
			return null;
		}
	}
	
	@Transactional
	@Override
	public  void saveReference(References newReferences){
		log.info("saveReferences.ReferencesDaoImp");
		em.persist(newReferences);
	}
	@Override
	public  List<References> loadReferencesList(){
		 List<References> ref = em.createQuery(
				    "from References ", References.class)
				    .getResultList();
			 return ref;
	}
	
	@Override
	public  List<References> loadReferencesListByProspect(int prospectus_id,int company_id){
		 List<References> ref = em.createQuery(
				    "from References where referencePK.prospectus_id = ? and referencePK.company_id = ? ", References.class)
				    .setParameter(1, prospectus_id)
				    .setParameter(2, company_id)
				    .getResultList();
			 return ref;
	}
	
	@Transactional
	@Override
	public  void updateReference(References newReferences){
		log.info("updateReferences.ReferencesDaoImp");
		em.merge(newReferences);
	}
	
	@Transactional
	@Override
	public  void removeReference(References newReferences){
		log.info("removeReferences.ReferencesDaoImp");
		
		References ref = em.find(References.class, newReferences.getReferencePK());
		em.remove(ref);
	}
	
	@Override 
	public List<References> loadReferencesListByNumber(String phone_str,int prospectus_id ,int company_id ){
		List<References> ref = em.createQuery(
			    "from References where referencePK.prospectus_id <> ? and referencePK.company_id = ? and phone = ? ", References.class)
			    .setParameter(1, prospectus_id)
			    .setParameter(2, company_id)
			    .setParameter(3,phone_str)
			    .getResultList();
		 return ref;
	}
	
	@Override
	public List<ReferencesMotive> loadReferencesMotiveLstEnabled( ){
		try{
			
			String query = "from ReferencesMotive where is_enabled = '1' order by menu_order";
			
			return em.createQuery(query,ReferencesMotive.class).getResultList();
			
		}catch(Exception e){
			return null;
		}
	}
	
	
	@Override
	public List<ReferencesScore> loadReferencesScoreLstEnabled( ){
		try{
			
			String query = "from ReferencesScore where is_enabled = '1' order by menu_order";
			
			return em.createQuery(query,ReferencesScore.class).getResultList();
			
		}catch(Exception e){
			return null;
		}
	}
	
}
