package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import mx.com.kubo.model.SegmentProspectusHistory;
import mx.com.kubo.model.segment.SegmentProspectus;
import mx.com.kubo.repositories.SegmentProspectusDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SegmentProspectusDaoImp implements Serializable,SegmentProspectusDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected final String QUERY_SEGMENT_PROSPECTUS_TYPE = "from SegmentProspectus where pk.company_id = ? and pk.prospectus_id = ? and pk.segment_type_id = ? ";
	
	private EntityManager em = null;
	
	  /**
	   * Sets the entity manager.
	   */
		
	  @PersistenceContext
	  public void setEntityManager(EntityManager em) {
	      this.em = em;
	  }
	
	public List< SegmentProspectus > loadSegmentProspectListByType( int prospectus_id , int company_id, int segment_type_id){
		
		try{
			
			return em.createQuery("from SegmentProspectus where pk.prospectus_id = ? and pk.company_id = ? and pk.segment_type_id = ? ", SegmentProspectus.class)
						.setParameter(1, prospectus_id)
						.setParameter(2, company_id)
						.setParameter(3, segment_type_id)
						.getResultList();
			
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
			
		}
					
		
	}
	
	@Transactional
	public boolean saveSegmentProspectus( SegmentProspectus segment)
    {	
		/* TODO
		 * IMPLEMENTAR UN ELSE EN CASO DE QUE ESTE TIPO DE SEGMENTO TENGA QUE SER DE MULTIPLES SEGMENTOS 
		 */
    	TypedQuery<SegmentProspectus> typed_query;
    	
    	List<SegmentProspectus> segment2 = null;    	    	
    	
		try
		{
			typed_query = em.createQuery(QUERY_SEGMENT_PROSPECTUS_TYPE, SegmentProspectus.class);
			typed_query.setParameter(1, segment.getPk().getCompany_id());
			typed_query.setParameter(2, segment.getPk().getProspectus_id());
			typed_query.setParameter(3, segment.getPk().getSegment_type_id());
			
			segment2 = typed_query.getResultList() ;			
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
			
		try
		{		
			if(segment2 == null || segment2.size() == 0 )
			{					
				em.persist(segment);
				
				return true;
				
			} else {
				
				SegmentProspectusHistory history = new SegmentProspectusHistory(segment2.get(0));
				
				history.setLast_date(new Date());
									
				em.remove(segment2.get(0));					
				em.persist(history);
				em.merge(segment);
				
				return true;				
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			return false;			
		}	
	}

}
