package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.BlockedPerson;
import mx.com.kubo.repositories.BlockedPersonDAO;

@Repository
public class BlockedPersonDAOImp implements BlockedPersonDAO 
{
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Transactional	
	public boolean save(BlockedPerson blocked_person)
	{
		try
		{
			em.persist(blocked_person);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
	
	@Transactional	
	public boolean update(BlockedPerson blocked_person)
	{
		try
		{
			em.merge(blocked_person);
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;
		}		 
	}
	
	@Transactional
	public boolean delete()
	{
		try
		{
			int deletedCount = em.createQuery("delete from BlockedPerson").executeUpdate();						
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return false;
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
	
	public List<BlockedPerson> getBlockedPerson() 
	{
		List<BlockedPerson> lista = null;
		
		TypedQuery<BlockedPerson> typed = null;
		
		typed = em.createQuery("from BlockedPerson ", BlockedPerson.class);		
		
		lista = typed.getResultList();
		
		return lista;
	}
}
