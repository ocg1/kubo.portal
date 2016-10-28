package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.model.ViewEconomicInfo;

public interface ViewClientInfoService {
	
	public abstract ViewEconomicInfo getEconomicById(String bmxId);
	public abstract ViewClientInfo getClientInfo(Integer prospectus_id,Integer company_id);
	//public abstract List<ClientView> getListClientByName(String query);
	public abstract List<ViewEconomicInfo> getListEconomicByDescription(String query);
	public abstract List<ClientView> getListClientViewAllByName(String name);
	//public abstract List<ClientView> getListClientByEmail(String strEmail);
	
}
