package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.repositories.ConsultingManualDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ConsultingManualDaoImp implements ConsultingManualDao  {
	
	
	private EntityManager em = null;

    /**
     * Sets the entity manager.
     */
	
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional
	@Override
	public void saveConsultingManual(ConsultingManual consulting) {
		String query="select MAX(c.pk.manual_consulting_id) from ConsultingManual c";
		Integer id=0;
		id=(Integer) em.createQuery(query).getSingleResult();
		id= (id==null)?0:id;
		id++;
		consulting.getPk().setManual_consulting_id(id)	;
		em.persist(consulting);
	}

}
