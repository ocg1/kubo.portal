package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.mesa.solicitud.PldNotification;
import mx.com.kubo.repositories.PrevencionLDDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PrevencionLDDaoImp 
implements PrevencionLDDao 
{
	Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Transactional	
	public  void savePrevencionLD(PrevencionLD prvencionld)
	{
		log.info(" save PrevencionLDDao.savePrevencionLD");
		
		 em.persist(prvencionld);
	}
	
	@Transactional	
	public  void updatePrevencionLD(PrevencionLD prvencionld)
	{
		log.info(" update PrevencionLDDao.savePrevencionLD");
		
		 em.merge(prvencionld);
	}
	
	@Transactional	
	public  void deletePrevencionLD(PrevencionLD prvencionld)
	{
		log.info(" delete PrevencionLDDao.savePrevencionLD");
		
		 em.remove(prvencionld);
	}
	
	
	public  PrevencionLD getSelectedPrevencionLDById(PrevencionLDPK pk)
	{
		log.info(" find PrevencionLDDao.getSelectedPrevencionLDById");
		
		return em.find(PrevencionLD.class,pk);
	}
	
	
	public  PrevencionLD getPrevencionLDByProspectus(int prospectus_id,int company_id)
	{
		log.info(" find PrevencionLDDao.getSelectedPrevencionLDById");
		
		try
		{			
			String query = "from PrevencionLD pld where pld.pk.prospectus_id = ? and pld.pk.company_id = ? " ;
			
			return em.createQuery(query, PrevencionLD.class).setParameter(1, prospectus_id).setParameter(2,company_id ).getSingleResult();
		
		}catch(Exception e){
			return null;
		}
	}
	
	public List<BlockedPerson> getLista_blocked_person()
	{	
		List<BlockedPerson> lista = null;
		
		lista = em.createQuery("from BlockedPerson", BlockedPerson.class).getResultList();
		
		return lista;
	}
	
	public List<BlockedPerson> getBlockedPersonByFullName(String full_name)
	{		
		List<BlockedPerson> lista = null;
		
		TypedQuery<BlockedPerson> typed = null;
		
		typed = em.createQuery("from BlockedPerson where full_name like ?", BlockedPerson.class);
		typed.setParameter(1, "%" + full_name + "%");
		
		lista = typed.getResultList();
		
		return lista;
	}
	
	public List<BlockedPerson> getBlockedPersonByRFC(String mx_rfc)
	{	
		List<BlockedPerson> lista = null;
		
		TypedQuery<BlockedPerson> typed = null;
		
		typed = em.createQuery("from BlockedPerson where mx_rfc like ?", BlockedPerson.class);
		typed.setParameter(1, "%" + mx_rfc + "%");
		
		lista = typed.getResultList();
		
		return lista;
	}

	@Transactional
	public final boolean add(PldNotification notification_log) 
	{
		try
		{
			em.persist(notification_log);
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}

	public List<PldNotification> getLista_pld_notification(int prospectus_id, int company_id) 
	{
		TypedQuery<PldNotification> typed;
		List<PldNotification> lista_pld_notification = null;
		
		try
		{				
			String query = "from PldNotification where prospectus_id_viewed = ? and company_id = ? " ;
			
			typed = em.createQuery(query, PldNotification.class);
			typed.setParameter(1, prospectus_id);
			typed.setParameter(2, company_id );
			
			lista_pld_notification = typed.getResultList();
									
			return lista_pld_notification;
		
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return lista_pld_notification;
	}
}
