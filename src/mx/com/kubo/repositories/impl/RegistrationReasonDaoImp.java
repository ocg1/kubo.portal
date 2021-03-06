package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.RegistrationReason;
import mx.com.kubo.model.UtmPartnerConversion;
import mx.com.kubo.repositories.RegistrationReasonDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
public class RegistrationReasonDaoImp 
implements RegistrationReasonDao 
{
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private String query;
	
	private List<RegistrationReason> cats;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
	@Transactional	
	public boolean save(RegistrationReason registration_reason) 
	{					
		boolean save_OK = false;
		
		try
		{
			em.persist(registration_reason);
			
			save_OK = true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return save_OK;
	}
	
	public int getMaxRegistration_reason_id()
	{
		String query = "SELECT MAX(rr.regPK.registration_reason_id) FROM RegistrationReason rr";
		
		Integer registration_reason_id = (Integer) em.createQuery(query).getSingleResult();
		
		if(registration_reason_id != null)
		{
			registration_reason_id++;
			
		} else {
			
			registration_reason_id = 1;
		}
		
		return registration_reason_id;
	}

	public List<RegistrationReason> loadRegistrationReasonList() 
	{
		cats = em.createQuery("from RegistrationReason ", RegistrationReason.class).getResultList();
		
		 return cats;
	}

	public List<RegistrationReason> loadRegistrationReasonWithoutOtherList() 
	{
		cats = null;
		
		query = "from RegistrationReason as r where r.regPK.registration_reason_id <> ? order by r.order_list desc";
		
		cats = em.createQuery(query, RegistrationReason.class).setParameter(1, 7).getResultList();
		
		return cats;
	}
	
	public UtmPartnerConversion existUTM( String utm ){
		
		try{
		
			String query = " from UtmPartnerConversion where utm_source_value = ? ";
			
			UtmPartnerConversion utmObj =em.createQuery(query, UtmPartnerConversion.class).setParameter(1, utm).getSingleResult();
			
			return utmObj;
		
		}catch( EntityNotFoundException nf){
			
			System.out.println( "No encontrado: " + utm );
			return null;
			
		}
		catch(NoResultException nr){
			
			System.out.println( "No encontrado: " + utm );
			return null;
			
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public RegistrationReason getRegistrationReasonByPartner( String partner_id ){
		
		try{
			
			String query = " from RegistrationReason where partner_id = ? ";
			RegistrationReason utmObj =em.createQuery(query, RegistrationReason.class).setParameter(1, partner_id).getSingleResult();
			return utmObj;
		
		}catch( EntityNotFoundException nf){
			
			System.out.println( "No encontrado: " + partner_id );
			return null;
			
		}catch(NoResultException nr){
			
			System.out.println( "No encontrado: " + partner_id );
			return null;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}

}
