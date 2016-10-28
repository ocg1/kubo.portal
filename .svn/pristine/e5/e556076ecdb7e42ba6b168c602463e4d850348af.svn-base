package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SellingType;
import mx.com.kubo.model.SellingTypePK;
import mx.com.kubo.repositories.SellingTypeDao;

import org.springframework.stereotype.Repository;

@Repository
public class SellingTypeDaoImp implements SellingTypeDao {

private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    public SellingType getSellingTypeByPK(SellingTypePK pk){
    	return em.find(SellingType.class, pk);
    }
	
	
}
