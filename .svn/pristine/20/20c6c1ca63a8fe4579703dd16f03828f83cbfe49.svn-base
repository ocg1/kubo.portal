package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Referred;
import mx.com.kubo.model.ReferredPK;
import mx.com.kubo.repositories.ReferredDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReferredDaoImp implements ReferredDao {
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional
	@Override
	public boolean addReferred( Referred referred ){
		boolean flag = false;
		try{
		
			em.persist(referred);
			flag = true;
		
		}catch(Exception e){
			
			flag = false;
			
		}
		
		return flag;
	}
	
	@Transactional
	@Override
	public boolean removeReferred( Referred referred ){
		
		boolean flag = false;
		
		try{
			
			Referred ref = em.find( Referred.class, referred.getPk());
			
			em.remove(ref);
			flag = true;
		
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
			
		}
		
		return flag;
	}
	
	@Transactional
	@Override
	public boolean updateReferred( Referred referred ){
		boolean flag = false;
		
		try{
			
			em.merge(referred);
			flag = true;
		
		}catch(Exception e){
			
			flag = false;
			
		}
		
		return flag;
	}
	
	@Override
	public Referred getReferred( ReferredPK pk ){
		Referred ref = null;
		
		try{
			
			return em.find(Referred.class, pk);
			
		
		}catch(Exception e){
			
			ref = null;
			
		}
		
		return ref;
	}
	
	@Override 
	public Referred getQuienLoRecomendo( int prospectus_id_destiny, int company_id ){
		
		String query = "from Referred where prospectus_id_destiny = ? and company_id = ?";
		try{
			return em.createQuery(query,Referred.class).setParameter(1,prospectus_id_destiny).setParameter(2,company_id).getSingleResult();
		}catch(Exception e){
			
			return null;
			
		}
		
	}
	
}
