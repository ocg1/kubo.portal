package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.ProyectQuestionPK;
import mx.com.kubo.repositories.ProyectQuestionDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProyectQuestionDaoImp implements ProyectQuestionDao{
	
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
	public ProyectQuestion loadSelectedProyectQuestion(ProyectQuestionPK pk){
		return em.find(ProyectQuestion.class, pk);
	}
    
    @Transactional
	@Override
	public void saveProyectQuestion(ProyectQuestion newProyectQuestion) {
		log.info("saveProyectQuestion.ProyectQuestionDaoImp");
		em.persist(newProyectQuestion);
	}
    
    @Override
	public List<ProyectQuestion> loadProyectQuestionList() {
		List<ProyectQuestion> proyectQuestion = em.createQuery("from ProyectQuestion",ProyectQuestion.class).getResultList();
		return proyectQuestion;
	}
    
    @Override
	public List<ProyectQuestion> getListProyectQuestionByProyect(int proyectID, int companyID) {			
		List<ProyectQuestion> proyectQuestion = em.createQuery(
			    "from ProyectQuestion e where e.proyectQuestionPK.proyect_id = ? and e.proyectQuestionPKPK.company_id = ?", ProyectQuestion.class).setParameter(1, proyectID).setParameter(2, companyID)
			    .getResultList();
		return proyectQuestion;
	}
}
