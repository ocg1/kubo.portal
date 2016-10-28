package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.TokenDB;
import mx.com.kubo.repositories.TokenDBDao;

@Repository
public class TokenDBDaoImp implements Serializable, TokenDBDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	
	/**
    * Sets the entity manager.
    */
	
   @PersistenceContext
   public void setEntityManager(EntityManager em) {
       this.em = em;
   }
   
   @Override
   public List<TokenDB> getTokenDBListByTokenId( String token, Integer prospectus_id ){
	   
	   String query = "from TokenDB t where t.token_gen = :tokenId "
	   					+ " and ( t.is_used = 'N' ) or "
	   					+ " ( t.is_used = 'S' and t.prospectus_id = :prospectus )";
	   
	   List<TokenDB> tokenList = em.createQuery( query, TokenDB.class )
			   							.setParameter("tokenId", token )
			   							.setParameter("prospectus", prospectus_id )
			   							.getResultList();
	   
	   return tokenList; 
	   
   }
   
   @Transactional
   @Override
   public boolean insertToken( TokenDB tokenBean ){
	   
	   boolean valid = false; 
	   
	   try{
		   
		   em.persist( tokenBean );
		   
		   valid = true;
		   
	   }catch( Exception e ){
		   e.printStackTrace();
		   valid = false;
	   }
	   
	   return valid;
	   
   }
   
   @Override
   public TokenDB getTokenDBById( String token_id ){

	   try{
	   
		   String query = "from TokenDB t where t.token_id = :tokenId ";
	
		   TokenDB token = em.createQuery( query, TokenDB.class )
		   							.setParameter("tokenId",  token_id ).getSingleResult();
		   
		   
		   return token;
	   
	   }catch(Exception e){
		   return null;
	   }
	   
	   
   }
   
   @Transactional
   @Override
   public boolean updateTokenUsed( TokenDB tokenBean ){
	   
	   boolean valid = false; 
	   
	   try{
		   
		   em.merge( tokenBean );
		   
		   valid = true;
		   
	   }catch( Exception e ){
		   e.printStackTrace();
		   valid = false;
	   }
	   
	   return valid;
	   
   }
   
	
}
