package mx.com.kubo.repositories.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.StatusInvCatPK;
import mx.com.kubo.repositories.StatusInvCatDao;

import org.springframework.stereotype.Repository;

@Repository
public class StatusInvCatDaoImp implements Serializable, StatusInvCatDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private EntityManager em = null;


	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}



	@Override
	public List<StatusInvCat> getListStatusInvCat() {
		// TODO Auto-generated method stub
		List<StatusInvCat> listStatusInvCat = em.createQuery("from StatusInvCat",StatusInvCat.class).getResultList();
		
		return listStatusInvCat;
	}

	@Override
	public StatusInvCat getStatusInvCatByPK(StatusInvCatPK statusInvCatPK) {
		// TODO Auto-generated method stub
		String query = "from StatusInvCat where status_id = :statusID and company_id = :companyID";
		StatusInvCat statusInvCat = em.createQuery(query,StatusInvCat.class)
				.setParameter("statusID", statusInvCatPK.getStatus_id())
				.setParameter("companyID", statusInvCatPK.getCompany_id())
				.getSingleResult();
		
		return statusInvCat;
	}

	@Override
	public List<StatusInvCat> getListStatusInvCatByIsEnabled() {
		// TODO Auto-generated method stub
		List<StatusInvCat> listStatusInvCat = em.createQuery("from StatusInvCat where is_enabled = 'S' order by name ",StatusInvCat.class).getResultList();
		return listStatusInvCat;
	}
}
