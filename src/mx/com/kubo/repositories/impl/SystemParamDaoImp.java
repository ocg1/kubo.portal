package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.Version;
import mx.com.kubo.repositories.SystemParamDao;


//import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SystemParamDaoImp 
implements SystemParamDao 
{
	private EntityManager em = null;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
	public SystemParam loadSelectedSystemParam(SystemParamPK id) 
	{
		try
		{
			
			return em.find(SystemParam.class,id);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return null;			
		}
	}
	
	@Transactional
	public boolean updateSelectedSystemParam(SystemParam  sp ){
		
		try
		{
			
			em.merge( sp );
			
			return true;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;			
		}
		
	}
	
	public Version getVersion_ACTUAL() 
	{			  
		String query_version_MAX    = "select MAX(v.version_id) from Version v ";
		String query_version_ACTUAL = "from Version v where v.version_id = ?";
		
		Version version_ACTUAL = null;
					
		Integer version_MAX = (Integer) em.createQuery(query_version_MAX).getSingleResult();
			
		if(version_MAX != null)
		{							
			version_ACTUAL = em.createQuery(query_version_ACTUAL, Version.class).setParameter(1, version_MAX).getSingleResult();
		}
			
		return version_ACTUAL;					
	}
}

