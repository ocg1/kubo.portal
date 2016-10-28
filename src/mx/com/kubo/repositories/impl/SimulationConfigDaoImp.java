package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.SimulationConfig;
import mx.com.kubo.repositories.SimulationConfigDao;

import org.springframework.stereotype.Repository;

@Repository
public class SimulationConfigDaoImp implements SimulationConfigDao{

	private EntityManager em = null;
	
	 @PersistenceContext
	 public void setEntityManager(EntityManager em) {
	        this.em = em;
	 }
	 
	 
	@Override
	public SimulationConfig getSimulationByLoanTypeIDandArea(String loanTypeID, String area, int companyID) {
		// TODO Auto-generated method stub
		SimulationConfig simulationConfig;
		
		try{
		simulationConfig = em.createQuery("from SimulationConfig where loan_type_id = ? "
				+ "and partner_id is null and area = ? and company_id = ?", SimulationConfig.class)
				.setParameter(1, loanTypeID)
				.setParameter(2, area)
				.setParameter(3, companyID)
				.getSingleResult();
		}catch(Exception e){
			simulationConfig = null;
		}
		return simulationConfig;
	}

	@Override
	public SimulationConfig getSimulationByPartnerIDandArea(String partnerID, String area, int companyID) {
		// TODO Auto-generated method stub
		SimulationConfig simulationConfig = null;
		
		try{
		simulationConfig = em.createQuery("from SimulationConfig where loan_type_id is null "
				+ "and partner_id = ? and area =? and company_id = ?", SimulationConfig.class)
				.setParameter(1, partnerID)
				.setParameter(2, area)
				.setParameter(3, companyID)
				.getSingleResult();
		}catch(Exception e){
			simulationConfig = null;
		}
		return simulationConfig;
	}

	@Override
	public SimulationConfig getSimulationByLoanTypeIDandPartnerIDandArea(
			String loanTypeID, String partnerID, String area, int companyID) {
		// TODO Auto-generated method stub
		SimulationConfig simulationConfig;
		
		try{
		simulationConfig = em.createQuery("from SimulationConfig where loan_type_id = ? "
				+ "and partner_id = ? and area = ? and company_id = ?", SimulationConfig.class)
				.setParameter(1, loanTypeID)
				.setParameter(2, partnerID)
				.setParameter(3, area)
				.setParameter(4, companyID)
				.getSingleResult();
		}catch(Exception e){
			simulationConfig = null;
		}
		return simulationConfig;
	}
	
	@Override
	public SimulationConfig getSimulationByArea(String area, int companyID) {
		// TODO Auto-generated method stub
		SimulationConfig simulationConfig;
		
		try{
		simulationConfig = em.createQuery("from SimulationConfig where loan_type_id is null and partner_id is null and area = ? and company_id = ?", SimulationConfig.class)
				.setParameter(1, area)
				.setParameter(2, companyID)
				.getSingleResult();
		}catch(Exception e){
			simulationConfig = null;
		}
		return simulationConfig;
	}
	

}
