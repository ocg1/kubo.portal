package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.EconomicActivityPK;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.repositories.EconomicActivityDao;
import mx.com.kubo.services.EconomicActivityService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EconomicActivityServiceImp 
implements EconomicActivityService
{
	
	Logger log = Logger.getLogger(getClass());
	@Autowired
	private EconomicActivityDao economicActivityRepository;
	
	@Override
	public Economic_Activity getEconomicActivityById(EconomicActivityPK pk) {
		return economicActivityRepository.loadSelectedEconomicActivity(pk);
	}
	
	@Override
	public void add(Economic_Activity newEconomicActivity) {
		economicActivityRepository.saveEconomicActivity(newEconomicActivity);		
	}
	
	@Override
	public List<Economic_Activity> getEconomicActivityList() {
		return economicActivityRepository.loadEconomicActivityList();
	}
	
	@Override
	public List<Economic_Activity> getEconomicActivityListEnabled() {
		return economicActivityRepository.loadEconomicActivityListEnabled();
	}
	
	

}
