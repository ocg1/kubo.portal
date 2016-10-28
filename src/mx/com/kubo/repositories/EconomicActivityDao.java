package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.EconomicActivityPK;
import mx.com.kubo.model.Economic_Activity;


public interface EconomicActivityDao {

	public Economic_Activity loadSelectedEconomicActivity(EconomicActivityPK pk);
	public void saveEconomicActivity(Economic_Activity newEconomicActivity);
	public List<Economic_Activity> loadEconomicActivityList();
	public List<Economic_Activity> loadEconomicActivityListEnabled();
}
