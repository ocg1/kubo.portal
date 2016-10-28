package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Partner;
import mx.com.kubo.model.PartnerPK;
import mx.com.kubo.repositories.DAOPartnerIMO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DAOPartnerIMP 
implements DAOPartnerIMO
{
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	public final Partner getPartner(PartnerPK partner_PK)
	{			
		Partner partner = em.find(Partner.class, partner_PK);
		
		return partner;
	}
	
	public List<Partner> getPartnerList()
	{
		try{
			
			String query = "from Partner" ;
		
			return em.createQuery(query,Partner.class).getResultList();
		
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}

	@Transactional
	public boolean save(Partner partner) 
	{
		boolean save_OK = false;
		
		try
		{
			em.persist(partner);
			
			save_OK = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return save_OK;
	}
}
