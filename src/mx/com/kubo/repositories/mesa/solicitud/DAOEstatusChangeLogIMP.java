package mx.com.kubo.repositories.mesa.solicitud;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.EstatusChangeLog;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("dao_estatus_change_log")
public class DAOEstatusChangeLogIMP 
implements DAOEstatusChangeLogIMO
{		
	private EntityManager em = null;
	
	private boolean persisted_OK;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
    
    @Transactional
	public final boolean registrar(EstatusChangeLog estatus_change_log)
	{		
		try
		{
			persisted_OK = false;
			
			em.persist(estatus_change_log);
			
			persisted_OK = true;
			
		} catch(Exception e) {
			
			e.printStackTrace();			
		} 
		
		return persisted_OK;
	}
}
