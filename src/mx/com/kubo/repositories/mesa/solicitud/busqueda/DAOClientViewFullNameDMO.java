package mx.com.kubo.repositories.mesa.solicitud.busqueda;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAOClientViewFullNameDMO 
implements ClientViewFullNameDao
{
	protected EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
}
