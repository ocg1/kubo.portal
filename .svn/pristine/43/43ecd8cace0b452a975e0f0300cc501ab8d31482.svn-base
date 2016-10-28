package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.Study_LevelPK;
import mx.com.kubo.repositories.StudyLevelDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudyLevelDaoImp implements StudyLevelDao{
	
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
	public Study_Level loadSelectedStudyLevel(Study_LevelPK pk){
		return em.find(Study_Level.class, pk);
	}
    
    @Transactional
	@Override
	public void saveStudyLevel(Study_Level newStudyLevel) {
		log.info("saveStudyLevel.StudyLevelDaoImp");
		em.persist(newStudyLevel);		
	}
    
    @Override
	public List<Study_Level> loadStudyLevelList() {
		List<Study_Level> studylevel=em.createQuery("from Study_Level",Study_Level.class).getResultList();
		return studylevel;
	}
	
}
