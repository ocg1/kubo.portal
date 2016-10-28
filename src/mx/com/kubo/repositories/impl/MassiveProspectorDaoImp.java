package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.MassiveProspector;
import mx.com.kubo.repositories.MassiveProspectorDao;

@Repository
public class MassiveProspectorDaoImp implements MassiveProspectorDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) 
	{
        this.em = em;
    }
	
	@Transactional
	public boolean massiveInsert( String insertquery ){
		try{
			
			return true;
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
	}
	
	@Transactional
	public boolean addElement( MassiveProspector element ){
		
		try{
			em.persist(element);
			return true;
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	public List<MassiveProspector> getMassiveLstByFileName( String filename ){
		
		try{
			
			String query = "from MassiveProspector where archivo_carga = ?";
			
			return em.createQuery(query,MassiveProspector.class).setParameter(1, filename).getResultList();
			
		}catch( Exception e ){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	@Transactional
	public boolean updateElement( MassiveProspector element ) {
		
		try{
			em.merge(element);
			return true;
		}catch( Exception e ){
			
			e.printStackTrace();
			return false;
			
		}
		
	}
	
}
