package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.MontoInvertido_F_G_Collector;
import mx.com.kubo.repositories.MontoInvertido_F_G_Collector_Dao;

@Repository
public class MontoInvertido_F_G_Collector_DaoImp implements MontoInvertido_F_G_Collector_Dao, Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public List<MontoInvertido_F_G_Collector> getMontoInvertido_F_G(String safi_account){
		
		try{
		
			return em.createNamedQuery("queryMontoInvertido_FG",MontoInvertido_F_G_Collector.class).setParameter("safi_account", safi_account).getResultList();
		
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
	}
	
}
