package mx.com.kubo.repositories.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import mx.com.kubo.model.SegmentAction;
import mx.com.kubo.repositories.SegmentActionDao;

@Repository
public class SegmentActionDaoImp implements Serializable, SegmentActionDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	   * Sets the entity manager.
	   */
	
	private EntityManager em = null;
	
	  @PersistenceContext
	  public void setEntityManager(EntityManager em) {
	      this.em = em;
	  }
	

	public SegmentAction getSegmentActionBySegment(int segment_id , int company_id, int action_type){
		try{
		return em.createQuery("from SegmentAction where pk.segment_id = ? and pk.company_id = ? and pk.type_action_id = ?", SegmentAction.class)
							.setParameter(1, segment_id)
							.setParameter(2, company_id)
							.setParameter(3, action_type)
							.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			return null;
			
		}
	}
	
}
