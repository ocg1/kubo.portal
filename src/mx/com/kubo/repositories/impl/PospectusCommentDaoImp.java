package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.PospectusComment;
import mx.com.kubo.repositories.PospectusCommentDao;

@Repository
public class PospectusCommentDaoImp implements Serializable, PospectusCommentDao {
	
	/**
	 * 
	 */
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
    
    public List<PospectusComment> getPospectusCommentByType( int prospectus_id, int proyect_loan_id , int comment_type_id  ){
    	
    	try{
    		
    		String query = "from PospectusComment where pk.prospectus_id = ? and pk.comment_type_id = ? and proyect_loan_id = ?  ";
    		
    		return em.createQuery(query, PospectusComment.class ).setParameter(1, prospectus_id ).setParameter(2, comment_type_id ).setParameter(3, proyect_loan_id ).getResultList() ;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
    	
    }
    
	public List<PospectusComment> getPospectusCommentByProspectus( int prospectus_id ){
		
		try{
			
			String query = "from PospectusComment where pk.prospectus_id = ?  ";
    		
    		return em.createQuery(query, PospectusComment.class ).setParameter(1, prospectus_id ).getResultList() ;
    		
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return null;
    	}
		
	}
	
	@Transactional
	public boolean savePospectusComment( PospectusComment pospectuscomment ){
		
		try{
			
			String query = "select MAX(pc.pk.comment_id) from PospectusComment pc where pc.pk.prospectus_id = ?";
			
			int idcomment = 0;
			
			try{
				idcomment = (Integer) em.createQuery(query).setParameter(1, pospectuscomment.getPk().getProspectus_id()) .getSingleResult();
			}catch(NullPointerException ne){
				
				idcomment = 0;
			}
			
			
			pospectuscomment.getPk().setComment_id( idcomment + 1 );
			
			pospectuscomment.setComment_date(new Date());
			
			em.persist(pospectuscomment);
			
			return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
		
	}
	
	@Transactional
	public boolean updatePospectusComment( PospectusComment pospectuscomment ){
		
		try{
			
			em.merge(pospectuscomment);
			return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
		
	}
	
	@Transactional
	public boolean removePospectusComment( PospectusComment pospectuscomment ){
		
		try{
			
			em.remove(pospectuscomment);
			return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
		
	}

}
