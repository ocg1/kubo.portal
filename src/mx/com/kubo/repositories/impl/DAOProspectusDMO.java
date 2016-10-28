package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.repositories.ProspectusDao;

public abstract class DAOProspectusDMO 
implements ProspectusDao 
{
	protected List<Prospectus> cats;
	private List<Integer> list;
	
	protected Prospectus ProsByAreaPesonType;
	protected Prospectus prospectByTrackingId;
	
	protected Logger log = Logger.getLogger(getClass());
	
	protected EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) 
	{
		this.em = em;
	}

	public Prospectus loadSelectedProspectus(ProspectusPK pk) 
	{
		return em.find(Prospectus.class, pk);
	}

	public List<Prospectus> loadProspectsList() 
	{
		List<Prospectus> cats = em.createQuery("from Prospectus ", Prospectus.class).getResultList();
		
		return cats;
	}
	
	public int getMaxProspectus() 
	{
		String hql = "select max(prospectusPK.prospectus_id) from Prospectus";
		
		list = em.createQuery(hql, Integer.class).getResultList();
		
		int maxID = (((Integer) list.get(0)) == null) ? 0 : ((Integer) list.get(0)).intValue();
		
		return maxID;
	}

	public Prospectus getProspectByAreaByPersonType(int prospectID, int companyID, char area, char personType) 
	{
		ProsByAreaPesonType = em.createQuery("from Prospectus p where p.prospectusPK.prospectus_id=? and p.prospectusPK.company_id=? and p.area=? and p.person_type=?", Prospectus.class).setParameter(1, prospectID)
						        .setParameter(2, companyID).setParameter(3, area)
						        .setParameter(4, personType).getSingleResult();
		
		return ProsByAreaPesonType;
	}

	public Prospectus getProspectByTrackingID(String tracking_id) 
	{
		try 
		{
			prospectByTrackingId = em.createQuery("from Prospectus p where  p.tracking_id = ?", Prospectus.class).setParameter(1, tracking_id).getSingleResult();
			
			return prospectByTrackingId;

		} catch (Exception e) {
			log.info("No se encontro ninguna entidad " + e.getMessage());
			return null;
		}
	}
}
