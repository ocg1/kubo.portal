package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.repositories.InvestorDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvestorDaoImp implements Serializable, InvestorDao {

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
  
  @Transactional
  @Override
  public boolean addInvestor( Investor investor ){
	  try{
		  
		  em.persist(investor);
		  return true;
		  
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  return false;
		  
	  }
  }
  
  @Override
  public Investor getInvestorById ( InvestorPK invPk ){
	  
	  try{
		  
		  return em.find( Investor.class, invPk);		  
		  
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  return null;
		  
	  }
	  
  }
  
  @Override
  public List<Investor> getInvestorList (  ){
	  
	  try{
		  
		  return em.createQuery("from Investor" , Investor.class).getResultList();		  
		  
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  return null;
		  
	  }
	  
  }
  
  @Transactional
  @Override
  public boolean updateInvestor ( Investor investor ){
	  
	  try{
		  
		  em.merge(investor);
		  return true;
		  
	  }catch(Exception e){
		  
		  e.printStackTrace();
		  return false;
		  
	  }
	  
  }

  @Override
  public List<Investor> getInvestorListByRole( Role_Searching rolesearching ){
	  List<Investor> listOfProyects = (List<Investor>) em.createQuery("from Investor i where i.status_id in ("+rolesearching.getStatus_id()+") order by "+rolesearching.getField_order()+" ",Investor.class).getResultList();
		return listOfProyects;
  }
  
  @Override
  public List<Investor> getInvestorByFiltering( String cad ){
	  List<Investor> proyectLoan= em.createNativeQuery("SELECT i.* From in_investor i  WHERE "+cad+";",Investor.class).getResultList();
		return proyectLoan;
  }
  
}
