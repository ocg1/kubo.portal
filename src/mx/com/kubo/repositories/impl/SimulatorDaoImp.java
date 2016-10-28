package mx.com.kubo.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.CAT_Collector;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.repositories.SimulatorDao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SimulatorDaoImp implements SimulatorDao {
	
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
	public SimulatorBean loadSelectedSimulator(SimulatorPK pk) {
		return em.find(SimulatorBean.class,pk);
	}
	
	@Transactional
	@Override
	public void saveSimulator(SimulatorBean newSimulator) {
		log.info("saveSimulator.SimulatorDaoImp");
		String query=" select max(s.simulatorPK.simulation_id) from SimulatorBean s where s.simulatorPK.prospectus_id = ? and s.simulatorPK.company_id = ?";
//		int idsimulation=0;
//		int order=0;
		//List<SimulatorBean> ls ;
		Integer max = (Integer) em.createQuery(query)
							.setParameter(1,newSimulator.getSimulatorPK().getProspectus_id())
							.setParameter(2,newSimulator.getSimulatorPK().getCompany_id())
							.getSingleResult();
		
		if(max == null)
			max = 0;
		
//		if(ls !=null && ls.size()>0){
//			int id=0;
//			int or =0;
//			for (SimulatorBean s : ls) {
//				id =s.getSimulatorPK().getSimulation_id();
//				or =s.getSim_order();
//				if(id>idsimulation)
//					idsimulation = id;
//				if(or>order)
//					order=or;
//			}
//
//		}
//		idsimulation++;
//		order++;
		newSimulator.getSimulatorPK().setSimulation_id(max+1);
		newSimulator.setSim_order(max+1);
		em.persist(newSimulator);
	}
	
	@Override
	public List<SimulatorBean> loadSimulatorList() {
		 List<SimulatorBean> sim = em.createQuery(
			    "from SimulatorBean ", SimulatorBean.class)
			    .getResultList();
		 return sim;
	}
	
	@Override
	public SimulatorBean getMaxSimulationProspect(Integer prospectus_id, Integer company_id) {
		
		log.info("getMaxSimulationByProspect.SimulationDaoImp");
		String query="select MAX(s.simulatorPK.simulation_id) from SimulatorBean s where s.simulatorPK.prospectus_id = ? and s.simulatorPK.company_id = ?";
		
		
		Integer sim_id  =(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();
		
		if(sim_id == null)
			return null;
		
		query="from SimulatorBean s where s.simulatorPK.prospectus_id = ? and s.simulatorPK.company_id = ? and s.simulatorPK.simulation_id = ?";
		
		SimulatorBean simulator = em.createQuery(query,SimulatorBean.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3, sim_id).getSingleResult();
		
		return simulator;
		
	}
	
	@Override
	public SimulatorBean getMaxSimulationProspectWithPurpose(Integer prospectus_id, Integer company_id) {
		
		log.info("getMaxSimulationProspectWithPurpose.SimulationDaoImp");
		String query="select MAX(s.simulatorPK.simulation_id) from SimulatorBean s where s.simulatorPK.prospectus_id = ? and s.simulatorPK.company_id = ? and s.purpose_id is not null";
		
		
		Integer sim_id  =(Integer) em.createQuery(query).setParameter(1, prospectus_id).setParameter(2,company_id).getSingleResult();
		
		if(sim_id == null)
			return null;
		
		query="from SimulatorBean s where s.simulatorPK.prospectus_id = ? and s.simulatorPK.company_id = ? and s.simulatorPK.simulation_id = ?";
		
		SimulatorBean simulator = em.createQuery(query,SimulatorBean.class).setParameter(1, prospectus_id).setParameter(2,company_id).setParameter(3, sim_id).getSingleResult();
		
		return simulator;
		
	}
	
	
	
	@Override
	public Double getCatBySafi(Double monto,String valorCuotas, Integer diasFrec){
		
		try{ 
			Double catD = 0D;
			
			CAT_Collector cat =  em.createNamedQuery("catQuery",CAT_Collector.class)
											.setParameter("Par_MontoCredito",monto)
											.setParameter("Par_ValorCuotas",valorCuotas)
											.setParameter("Par_FrecuPago",diasFrec)
											.setParameter("Par_Salida",'S')
											.setParameter("Var_CAT",catD)
											.setParameter("NumTransaccion",0)
											.getSingleResult();
			
			//System.out.println("inout: "+catD+"   resObj: "+cat.getVar_Cat());
			
			return cat.getVar_Cat();
			
		}catch( Exception e ){
			return 0.00;
		}
		
	}

}
