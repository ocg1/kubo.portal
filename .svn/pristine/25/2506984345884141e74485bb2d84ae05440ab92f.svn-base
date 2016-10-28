package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.TicketConfig;
import mx.com.kubo.repositories.TicketConfigDao;

import org.springframework.stereotype.Repository;

@Repository
public class TicketConfigDaoImp  implements Serializable, TicketConfigDao {
	
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
	public List<TicketConfig> getTicketConfigLst(){
		
		return em.createQuery(
				
			    "from TicketConfig ", TicketConfig.class)
			    .getResultList();
		
	}
	

}
