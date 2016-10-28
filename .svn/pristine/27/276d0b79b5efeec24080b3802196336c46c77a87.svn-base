package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ComparisonMod;
import mx.com.kubo.repositories.ComparisonDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ComparisonDaoImp implements ComparisonDao{

	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Transactional
	@Override
	public boolean add(ComparisonMod newComparison) {
		// TODO Auto-generated method stub
		try{
			String query = "select max(comparisonPK.comparison_id) from ComparisonMod";
			
			Integer id = 0;
			id = em.createQuery(query, Integer.class)
					.getSingleResult();
			id= (id==null)?0:id;
			id++;
			newComparison.getComparisonPK().setComparison_id(id);
			em.persist(newComparison);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
