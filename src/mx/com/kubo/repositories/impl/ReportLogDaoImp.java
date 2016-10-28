package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.ReportLog;
import mx.com.kubo.repositories.ReportLogDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReportLogDaoImp implements ReportLogDao{
	private EntityManager em = null;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em){
		this.em = em;
	}
	
	@Transactional
	@Override
	public boolean add(ReportLog reportLog) {
		try {
			String query="select MAX(rl.reportLogPK.report_log_id) from ReportLog rl";
			int id=0;
			
			if(em.createQuery(query).getSingleResult() !=null){
				id=(Integer) em.createQuery(query).getSingleResult();
				id++;
			}
			else{
					id++;
				}
			
			reportLog.getReportLogPK().setReport_log_id(id);
			em.persist(reportLog);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
