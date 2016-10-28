package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Fields;
import mx.com.kubo.model.FieldsPK;
import mx.com.kubo.repositories.FieldsDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class FieldsDaoImp implements FieldsDao {
	
	Logger log = Logger.getLogger(getClass());
	private EntityManager em = null;
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Override
	public Fields getFieldsById(FieldsPK fieldPk) {
		return em.find(Fields.class, fieldPk);
	}

	@Override
	public List<Fields> getListByScreenId(Integer idScreen,Integer company_id){
		List<Fields> lstFieldsScreen=em.createQuery("from Fields f where f.fieldsPK.screen_id=? and f.fieldsPK.company_id=?",Fields.class)
				.setParameter(1,idScreen)
				.setParameter(2, company_id).getResultList();
		return lstFieldsScreen;
	}

}
