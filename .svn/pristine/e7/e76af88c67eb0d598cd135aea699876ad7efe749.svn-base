package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.repositories.NaturalPersonDao;

public abstract class DAONaturalPersonDMO 
implements NaturalPersonDao
{
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;
	
	protected String query;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    @Transactional
	public NaturalPerson update(NaturalPerson newNaturalPerson) 
	{
		NaturalPerson person = null;
		
		try
		{		
			person = em.merge(newNaturalPerson);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return person;
	}
   
	public NaturalPerson loadSelectedNaturalPerson(gnNaturalPersonPK pk) 
	{
		log.info("FindNaturalPerson.NaturalPersonDaoImp loadSelectedNaturalPerson prospectus: "+pk.getProspectus_id());
		
		return em.find(NaturalPerson.class,pk);
	}
	
	public final NaturalPerson getPersonBySAFI_client_id(String SAFI_client_id)
	{
		NaturalPerson person = null;
		TypedQuery<NaturalPerson> typed; 
		
		typed = em.createQuery("from NaturalPerson where safi_client_id = ?", NaturalPerson.class);
		typed.setParameter(1, SAFI_client_id);
		
		try
		{
			person = typed.getSingleResult();
			
		} catch (Exception e) {
			
			System.out.println("DAONaturalPersonDMO.getPersonBySAFI_client_id(): " + SAFI_client_id);
			
			e.printStackTrace();
		}
		 
		 return person;
	}
	
	public List<NaturalPerson> loadNaturalPersonList() 
	{
		List<NaturalPerson> lista_natural_person = em.createQuery("from NaturalPerson ", NaturalPerson.class).getResultList();
		 
		 return lista_natural_person;
	}
}
