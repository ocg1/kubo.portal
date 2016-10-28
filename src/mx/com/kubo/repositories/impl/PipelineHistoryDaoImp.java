package mx.com.kubo.repositories.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.PipelineHistory;
import mx.com.kubo.repositories.PipelineHistoryDao;

import org.springframework.stereotype.Repository;

@Repository
public class PipelineHistoryDaoImp implements PipelineHistoryDao{
private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@Override
	public List<PipelineHistory> getPipelineHistory() {
		// TODO Auto-generated method stub
		String query = "from PipelineHistory";
		return em.createQuery(query, PipelineHistory.class)
				.getResultList();
	}

	@Override
	public List<PipelineHistory> getPipelineHistoryByPeriodo(Date periodo) {
		// TODO Auto-generated method stub
		String query = "from PipelineHistory where periodo = :periodo";
		return em.createQuery(query, PipelineHistory.class)
				.setParameter("periodo", periodo)
				.getResultList();
	}

	@Override
	public List<Date> getPeriodoList() {
		// TODO Auto-generated method stub
		String query = "select periodo from PipelineHistory group by periodo "
				+ "order by periodo desc";
		return em.createQuery(query, Date.class)
				.getResultList();
	}
}
