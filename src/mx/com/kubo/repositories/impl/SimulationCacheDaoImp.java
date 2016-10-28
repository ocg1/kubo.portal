package mx.com.kubo.repositories.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.kubo.model.Simulation_Cache;
import mx.com.kubo.repositories.SimulationCacheDao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SimulationCacheDaoImp implements SimulationCacheDao{
	
	private EntityManager em = null;
	
	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	

	@Override
	public Simulation_Cache getMaxByAmmountRateTermFrequency(Double ammount, Double rate, Integer term, Integer frequency, Integer companyID) {
		// TODO Auto-generated method stub

		Simulation_Cache simulationCache = new Simulation_Cache();
		Integer maxSimulationCacheID = null;
				maxSimulationCacheID = (Integer)em.createQuery("select max(sc.pk.simulation_cache_id) "
				+ " from Simulation_Cache sc where"
				+ " sc.ammount = :ammount and sc.period_rate = :rate and sc.term_id = :term"
				+ " and sc.frequency_id = :frequency and sc.pk.company_id = :companyID")
				.setParameter("ammount",ammount)
				.setParameter("rate", rate)
				.setParameter("term", term)
				.setParameter("frequency", frequency)
				.setParameter("companyID", companyID)
				.getSingleResult();
		try{
			if(maxSimulationCacheID != null){
				String query = "from Simulation_Cache sc where sc.ammount = :ammount"
						+ " and sc.period_rate = :rate and 	sc.term_id = :term"
						+ " and sc.frequency_id = :frequency and sc.pk.company_id = :companyID"
						+ " and sc.pk.simulation_cache_id = (:maxSimulationCacheID)";
				
				
		
						simulationCache = (Simulation_Cache)em.createQuery(query,Simulation_Cache.class)
						.setParameter("ammount",ammount)
						.setParameter("rate", rate)
						.setParameter("term", term)
						.setParameter("frequency", frequency)
						.setParameter("companyID", companyID)
						.setParameter("maxSimulationCacheID", maxSimulationCacheID)
						.getSingleResult();
			}else{
				 simulationCache = null;
			}
			
		}catch(Exception e){
			System.out.println("Error sql"+e.getMessage());
		}
		
		return simulationCache;
	}

	@Transactional
	@Override
	public boolean add(Simulation_Cache newSimulationCache) {
		
		try{
			// TODO Auto-generated method stub
			String query = "Select max(sc.pk.simulation_cache_id) from Simulation_Cache sc";
			int simulationCacheID = 0;
			
			if(em.createQuery(query).getSingleResult() != null){
				simulationCacheID = (Integer)em.createQuery(query).getSingleResult(); 
				simulationCacheID ++;
			}else{
				simulationCacheID ++;
			}
			
			newSimulationCache.getPk().setSimulation_cache_id(simulationCacheID);
			em.persist(newSimulationCache);
			return true;
		}catch(Exception e){
			System.out.println("Error"+e.getMessage());
			return false;
		}
		
	}

	@Transactional
	@Override
	public boolean update(Simulation_Cache newSimulationCache) {
		// TODO Auto-generated method stub
		try{
			em.merge(newSimulationCache);
			return true;
		}catch(Exception e){
			System.out.println("Error SimulationCacheDaoImp.update " + e.getMessage());
		}
		
		return false;
	}

}
