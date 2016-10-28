package mx.com.kubo.repositories.impl;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.repositories.ScreenDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ScreenDaoImp implements ScreenDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	
	@Override
	public Screen loadSelectedScreen(ScreenPK pk) {
		return em.find(Screen.class,pk);
	}
	
	@Override
	public List<Screen> getLstScreenByArea(int company_id,String area){
		try{
			
			return em.createQuery("from Screen where area = ? and screenPK.company_id = ? ",Screen.class)
								.setParameter(1,area )
								.setParameter(2, company_id)
								.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
