package mx.com.kubo.rest.tienda.filters;

import mx.com.kubo.bean.FilterStore;

public interface FilterStoreIMO 
{
	void setQuery(String query);
	void setSafi_client_id (String safi_client_id);
	void setsafi_account_id(String safi_account_id);
	
	void init();

	FilterStore getFilter();
}
