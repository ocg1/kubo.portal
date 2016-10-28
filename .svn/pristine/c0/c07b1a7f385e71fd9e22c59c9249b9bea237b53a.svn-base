package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SecurityQuestion;
import mx.com.kubo.model.SecurityQuestionPK;
import mx.com.kubo.repositories.SecurityQuestionDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SecurityQuestionDaoImp 
implements SecurityQuestionDao, Serializable
{

	private static final long serialVersionUID = 1L;
	
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
	public SecurityQuestion loadSelectedSecurityQuestion(SecurityQuestionPK pk) 
	{
		return em.find(SecurityQuestion.class, pk);
	}

	@Transactional
	@Override
	public void saveSecurityQuestion(SecurityQuestion newSecurityQuestion) {
		log.info("saveSecurityQuestion.SecurityQuestionDaoImp");
		em.persist(newSecurityQuestion);		
	}

	@Override
	public List<SecurityQuestion> loadSecurityQuestionList() {
		List<SecurityQuestion> securityQ=em.createQuery("from SecurityQuestion",SecurityQuestion.class).getResultList();
		return securityQ;
	}
    
    

}
