package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.FromWhereCat;
import mx.com.kubo.model.FromWhereCatPK;
import mx.com.kubo.repositories.FromWhereCatDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class FromWhereDaoImp implements FromWhereCatDao {

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
	public FromWhereCat getFromWhereByID(FromWhereCatPK pk) {
		return em.find(FromWhereCat.class, pk);
	}

	@Override
	public List<FromWhereCat> getListFromWhere() {
		List<FromWhereCat> listFw=em.createQuery("from FromWhereCat",FromWhereCat.class).getResultList();
		return listFw;
	}

}
