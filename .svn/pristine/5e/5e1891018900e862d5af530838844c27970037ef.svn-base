package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.LegalStatusPK;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.repositories.LegalStatusDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LegalStatusDaoImp 
implements LegalStatusDao
{	
	protected Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private TypedQuery<Legal_Status> typed;
	
	private List<Legal_Status> lista_legal_status;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }

	public Legal_Status loadSelectedLegalStatus(LegalStatusPK pk) 
	{
		return em.find(Legal_Status.class, pk);
	}

	@Transactional	
	public void saveLegalStatus(Legal_Status newLegalStatus) 
	{
		log.info("saveLegalStatus.LegalStatusDaoImp");
		
		em.persist(newLegalStatus);		
	}

	public List<Legal_Status> loadLegalStatusList() 
	{
		typed = em.createQuery("from Legal_Status as ls order by ls.menu_order asc", Legal_Status.class);
		
		lista_legal_status = typed.getResultList();
		
		return lista_legal_status;
	}
}
