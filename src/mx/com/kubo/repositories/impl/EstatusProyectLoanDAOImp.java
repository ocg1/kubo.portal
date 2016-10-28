package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.StatusProyectCatPK;
import mx.com.kubo.repositories.EstatusProyectLoanDAO;

import org.springframework.stereotype.Repository;

@Repository
public class EstatusProyectLoanDAOImp 
implements EstatusProyectLoanDAO
{
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public StatusProyectCat getStatus_by_PK(StatusProyectCatPK estatus_PK) 
	{		
		return em.find(StatusProyectCat.class, estatus_PK);
	}

}
