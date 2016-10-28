package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.PublicForumPK;
import mx.com.kubo.repositories.PublicForumDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PublicForumDaoImp implements PublicForumDao{
	
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
	public PublicForum loadSelectedPublicForum(PublicForumPK pk) {
		return em.find(PublicForum.class, pk);
	}

	@Transactional
	@Override
	public void savePublicForum(PublicForum newPublicForum) {
		log.info("savePublicForum.PublicForumDaoImp");
		String query="select MAX(pf.publicForumPK.public_forum_id) from PublicForum pf";
		int idpublicForum = 0;
		if(em.createQuery(query).getSingleResult() !=null){
			idpublicForum=(Integer) em.createQuery(query).getSingleResult();
			idpublicForum++;
		}
		else{
			idpublicForum++;
		}
		newPublicForum.getPublicForumPK().setPublic_forum_id(idpublicForum);
		em.persist(newPublicForum);
	}

	@Override
	public List<PublicForum> loadPublicForumList() {
		List<PublicForum> publicForum=em.createQuery("from PublicForum",PublicForum.class).getResultList();
		return publicForum;
	}
	
	@Override
	public List<PublicForum> getListPublicForumByProyect(int proyectID,int companyID) {			
		List<PublicForum> publicForum = em.createQuery(
			    "from PublicForum e where e.publicForumPK.proyect_id = ? and e.publicForumPK.company_id = ?", PublicForum.class).setParameter(1, proyectID).setParameter(2, companyID)
			    .getResultList();
		return publicForum;
	}
	
	@Override
	public List<ProyectQuestion> getListUnrealizedQuestions(int proyectID,int companyID) {	
	String queryUnrealizedQuestions = "SELECT * FROM ln_proyect_question WHERE proyect_question_id NOT IN (SELECT proyect_question_id FROM ln_public_forum WHERE proyect_id = "+proyectID+" AND company_id = "+companyID+")";	
		@SuppressWarnings("unchecked")
		List<ProyectQuestion> proyectQuestion = em.createNativeQuery(queryUnrealizedQuestions, ProyectQuestion.class).getResultList();
		return proyectQuestion;
	}
	
	@Transactional
	@Override
	public boolean updatePublicForum(PublicForum publicForum) {
		try{
			em.merge(publicForum);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			log.info("Error al agregar respuesta :3"+e.getMessage());
			return false;
		}
	}
	
}
