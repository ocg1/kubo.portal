package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ReportType;
import mx.com.kubo.repositories.ReportTypeDao;

import org.springframework.stereotype.Repository;


@Repository
public class ReportTypeDaoImp implements ReportTypeDao{
	private EntityManager em= null;

	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

	@Override
	public List<ReportType> getReportTypeList() {
		String query = "from ReportType where is_visible = 'S'";
		
		return em.createQuery(query, ReportType.class)
				.getResultList();
	}
}
