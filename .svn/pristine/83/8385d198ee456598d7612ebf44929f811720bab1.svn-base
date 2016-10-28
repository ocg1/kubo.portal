package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.ClientView;
import mx.com.kubo.model.ViewClientInfo;
import mx.com.kubo.model.ViewEconomicInfo;

public interface ViewClientInfoDao {
	public ViewEconomicInfo getEconomicById(String bmxId);
	public ViewClientInfo getClientInfo(Integer prospectus_id,Integer company_id);
	//public List<ClientView> getListClientByName(String query);
	public List<ViewEconomicInfo> getListEconomicByDescription(String query);
	public abstract List<ClientView> getListClientViewAllByName(String name);
	//public List<ClientView> getListClientByEmail(String strEmail);
}
