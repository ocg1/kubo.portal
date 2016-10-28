package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.WhoAnsweredCat;
import mx.com.kubo.model.WhoAnsweredCatPK;
import mx.com.kubo.repositories.WhoAnsweredCatDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class WhoAnsweredCatDaoImp implements WhoAnsweredCatDao{
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public WhoAnsweredCat getAnsweredByID(WhoAnsweredCatPK pk) {
		return em.find(WhoAnsweredCat.class, pk);
	}

	@Override
	public List<WhoAnsweredCat> getListWhoAnswered() {
		List<WhoAnsweredCat> listWhoAns=em.createQuery("from WhoAnsweredCat",WhoAnsweredCat.class).getResultList();
		return listWhoAns;
	}

}
