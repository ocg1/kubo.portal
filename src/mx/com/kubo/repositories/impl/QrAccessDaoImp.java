package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.QrAccess;
import mx.com.kubo.model.ViewHomeStatistics;
import mx.com.kubo.repositories.QrAccessDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class QrAccessDaoImp 
implements QrAccessDao , Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(getClass());
	
	private EntityManager em = null;
	
	private ViewHomeStatistics statistics;

    @PersistenceContext
    public void setEntityManager(EntityManager em) 
    {
        this.em = em;
    }
	
    @Transactional
	public boolean saveQrAccess(QrAccess newQrAccess) 
    {
		log.info("savePurpose.PurposeDaoImp");
		
		try
		{
			//newQrAccess.setQr_id(getMaxProyectID());
			em.persist(newQrAccess);
			
			return true;
			
		}catch(Exception e){
			return false;
		}
	}
    
    public int getMaxProyectID() 
    {
		String query = "select MAX(q.qr_id) from QrAccess q";
		
		int idQr = 0;
		
		if(em.createQuery(query).getSingleResult() !=null)
		{
			idQr = (Integer) em.createQuery(query).getSingleResult();
			idQr++;
			
		} else {
			
			idQr++;
		}
		
		return idQr;
	}

	public ViewHomeStatistics getHomeStatistics() 
	{
		statistics = em.createQuery("from ViewHomeStatistics", ViewHomeStatistics.class).getSingleResult();
		
		return statistics;
	}
    
}
