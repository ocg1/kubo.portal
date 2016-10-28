package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProfileFormValue;
import mx.com.kubo.repositories.ProfileFormValueDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProfileFormValueDaoImp implements Serializable,ProfileFormValueDao {

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
	public List<ProfileFormValue> getProfileFormValueListByProspectus( int prospectus_id , int company_id  ){
		
		return em.createQuery("from ProfileFormValue where pk.prospectus_id = ? and pk.company_id = ?",ProfileFormValue.class)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.getResultList();
		
	}
	
	
	@Override
	@SuppressWarnings(value="unchecked")
	public boolean getHaInvertidoEnAcciones( int prospectus_id , int company_id  ){
		
		String query = " select *  from in_profile_value where prospectus_id = ? and company_id = ? and profile_form_id in (13,14,15) and value = 1";
		boolean acciones = false;
		try{
			List<ProfileFormValue> lst = (List<ProfileFormValue>)em.createNativeQuery(query,ProfileFormValue.class)
																			.setParameter(1, prospectus_id)
																			.setParameter(2, company_id)
																			.getResultList();
			
			if( lst != null && lst.size() > 0 ){
				acciones = true;
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return acciones;
		
	}
	
	@Transactional
	@Override
	public boolean saveProfileFormValue(ProfileFormValue profile){
		
		try{
			
			em.persist(profile);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	@Override
	public boolean removeProfileFormValue(ProfileFormValue profile){
		
		try{
			
			em.remove(profile);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Transactional
	@Override
	public boolean updateProfileFormValue(ProfileFormValue profile){
		
		try{
			
			em.merge(profile);
			return true;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
