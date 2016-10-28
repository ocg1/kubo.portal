package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.model.ViewEconomicInfo;
import mx.com.kubo.repositories.ViewClientInfoDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ViewClientInfoDaoImp implements ViewClientInfoDao {

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
	public ViewClientInfo getClientInfo(Integer prospectus_id,Integer company_id){
		return (ViewClientInfo)em.createNamedQuery("findViewClientInfo", ViewClientInfo.class).setParameter("prospect",prospectus_id ).setParameter("company", company_id).getSingleResult();
	}
	/*
	@Override
	public List<ClientView> getListClientByName(String query) {	
		return em.createNamedQuery("viewClient",ClientView.class)
				.setParameter("query", "%" + query + "%")
				.setMaxResults(30)
				.getResultList();
	}
	*/
	@Override
	public List<ViewEconomicInfo> getListEconomicByDescription(String query) {
		return em.createNamedQuery("findViewEconomicInfo",ViewEconomicInfo.class)
				.setParameter("query", "%" + query + "%")
				.setMaxResults(10)
				.getResultList();
	}

	@Override
	public ViewEconomicInfo getEconomicById(String bmxId) 
	{
		ViewEconomicInfo economicInfo=em.createNamedQuery("findViewEconomicInfoByID", ViewEconomicInfo.class).setParameter(1, bmxId).getSingleResult();
		
		return economicInfo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClientView> getListClientViewAllByName(String name) {	
		
		String query = "select * from view_full_name_cons_man where full_name like :name";
		
		
		List<ClientView> listClient = em.createNativeQuery(query, ClientView.class)
				.setParameter("name", "%" + name + "%")
				.setMaxResults(30)
				.getResultList();
		
		return listClient;
	}
	/*
	@Override
	public List<ClientView> getListClientByEmail(String strEmail) {
		String query = "from ClientView c where c.full_name is not null "
				+ " and c.email is not null and c.tracking_id is not null and c.email like :strEmail";
		
		return em.createQuery(query, ClientView.class)
				.setParameter("strEmail", "%" +strEmail+ "%")
				.setMaxResults(30)
				.getResultList();
		
	}
	*/

	private List extracted(String name, String query) {
		return em.createNativeQuery(query,ClientView.class)
				.setParameter("name", "%" + name + "%")
				.setMaxResults(30)
				.getResultList();
	}
}
