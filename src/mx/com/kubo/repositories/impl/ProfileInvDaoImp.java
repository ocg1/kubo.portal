package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.investor.OtherinvestmentCat;
import mx.com.kubo.repositories.ProfileInvDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProfileInvDaoImp 
implements Serializable, ProfileInvDao 
{
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;

	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Override
	public ProfileInv getProfileInvByProspectus( int prospectus_id, int company_id ){
		try
		{
			return em.createQuery("from ProfileInv where pk.prospectus_id = ? and pk.company_id = ?",ProfileInv.class)
									.setParameter(1, prospectus_id)
									.setParameter(2, company_id)
									.getSingleResult();
		}catch(Exception e){
			
			System.out.println("ProfileInvDaoImp.getProfileInvByProspectus(): NOT FOUND");
			
			return null;
		}
	}
	
	@Transactional
	@Override
	public ProfileInv  saveProfileInv( ProfileInv profile ){
		try{
			
			em.persist(profile);
			return profile;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
	}

	@Transactional
	@Override
	public boolean  updateProfileInv( ProfileInv profile ){
		try{
			
			em.merge(profile);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	@Transactional
	@Override
	public boolean  removeProfileInv( ProfileInv profile ){
		try{
			
			em.remove(profile);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	public List<OtherinvestmentCat> getOtherinvestmentCat(){
		
		try{
			
			return em.createQuery("from OtherinvestmentCat", OtherinvestmentCat.class).getResultList();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
}
