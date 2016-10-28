package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.kubo.model.EflScore;
import mx.com.kubo.repositories.EflScoreDao;

@Repository
public class EflScoreDaoImp implements Serializable,EflScoreDao  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public boolean saveEflScore( EflScore efl ){
		
		try{
			
			em.persist(efl);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public EflScore getMaxEflScoreByBurSolNum( String bur_sol_num ){
		
		try{
			
			EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
			Session session = (Session) entityManager.unwrap(Session.class);
			
			Criteria crit =  session.createCriteria(EflScore.class);
			
			crit.setMaxResults(1);
			crit.add( Restrictions.eq("mx_solicitud_buro", bur_sol_num)  );
			crit.addOrder( Order.desc("consulting_date") );
			
			@SuppressWarnings("unchecked")
			List<EflScore> lst = crit.list();
			
			if( lst != null && lst.size() > 0 ){
				
				return lst.get(0);
				
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<EflScore> getEflScoreListByBurSolNum( String bur_sol_num ){
		
		try{
			
			EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
			Session session = (Session) entityManager.unwrap(Session.class);
			
			Criteria crit =  session.createCriteria(EflScore.class);
			
			crit.add( Restrictions.eq("mx_solicitud_buro", bur_sol_num)  );
			crit.addOrder( Order.desc("consulting_date") );
			
			@SuppressWarnings("unchecked")
			List<EflScore> lst = crit.list();
			
			if( lst != null && lst.size() > 0 ){
				
				return lst;
				
			}else{
				return null;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	
}
