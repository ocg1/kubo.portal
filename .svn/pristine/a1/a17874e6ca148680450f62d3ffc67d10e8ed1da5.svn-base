package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.RendimientosInv;
import mx.com.kubo.repositories.RendimientosInvDao;

@Repository
public class RendimientosInvDaoImp implements Serializable, RendimientosInvDao  {

	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	public List<RendimientosInv> getRendimientosInvLst( int cuentaAhoId ){
		
		try{
			
			return em.createNamedQuery("collectorRendimientosInv",  RendimientosInv.class )
						.setParameter("CuentaAhoID", cuentaAhoId)
						.getResultList();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
