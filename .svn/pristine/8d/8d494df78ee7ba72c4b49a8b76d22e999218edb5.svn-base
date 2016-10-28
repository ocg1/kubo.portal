package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.repositories.PasswordHistoryDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PasswordHistoryDaoImp implements PasswordHistoryDao , Serializable {

	/**
	 * 
	 */
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
	@Transactional
	public boolean savePasswordHistory( PasswordHistory passwordhistory){
		try{
			
			Integer i = maxSeqByProspectus(passwordhistory.getPwdhpk().getProspectus_id(),passwordhistory.getPwdhpk().getCompany_id());
			boolean flag = false;;
			if(i != null){
				
				passwordhistory.getPwdhpk().setSeq((i+1));
				
			}else{
				
				passwordhistory.getPwdhpk().setSeq((1));
				
			}
				
			em.persist(passwordhistory);
			flag = true;
			
			return flag;
			
		}catch(Exception e){
			
			return false;
			
		}
	}
	
	public Integer maxSeqByProspectus(int prospectus_id, int company_id){
		try{
			
			Query query = em.createQuery("select max(p.pwdhpk.seq) from PasswordHistory p where p.pwdhpk.prospectus_id = ? and p.pwdhpk.company_id = ? ")
							.setParameter(1, prospectus_id)
							.setParameter(2, company_id);
			 
			Integer minActual = (Integer)query.getSingleResult();
			return minActual;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
		
		
	}
	
	@Override
	public boolean existPassword ( String password , int prospectus_id, int company_id ){
		
		try{
		
			boolean flag = false;
			Query query = em.createQuery("from PasswordHistory p where p.pwdhpk.prospectus_id = ? and p.pwdhpk.company_id = ? and password = ? ",PasswordHistory.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, password);
			
			List<PasswordHistory> lst = (List<PasswordHistory>)query.getResultList();
			
			if( lst != null && lst.size()>0){
				flag = true;
			}
			
			return flag;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	
	@Override
	public PasswordHistory getPasswordHistorySelec ( String password , int prospectus_id, int company_id ){
		
		try{
		
			
			Query query = em.createQuery("from PasswordHistory p where p.pwdhpk.prospectus_id = ? and p.pwdhpk.company_id = ? and password = ? ",PasswordHistory.class)
				.setParameter(1, prospectus_id)
				.setParameter(2, company_id)
				.setParameter(3, password);
			
			List<PasswordHistory> lst = (List<PasswordHistory>)query.getResultList();
			
			if( lst != null && lst.size()>0){
				return lst.get(0);
			}
			
			return null;
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			return null;
			
		}
		
	}
	
}
