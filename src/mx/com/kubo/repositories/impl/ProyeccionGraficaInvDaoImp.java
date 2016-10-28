package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.ProyeccionGraficaInv;
import mx.com.kubo.repositories.ProyeccionGraficaInvDao;

@Repository
public class ProyeccionGraficaInvDaoImp implements ProyeccionGraficaInvDao {

	private EntityManager em = null;
	
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Override
	public List<ProyeccionGraficaInv> getProyeccionGraficaInvLst( int cuentaAhoId ){
		
		try{
		
			return em.createNamedQuery("collectorProyeccionGraficaInv", ProyeccionGraficaInv.class)
				.setParameter("CuentaAhoID", cuentaAhoId)
				.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
