package mx.com.kubo.rest.tienda;

import mx.com.kubo.bean.FilterStore;

public abstract class FilterStoreDMO implements FilterStoreIMO
{
	protected FilterStore filter;
	
	protected String safi_client_id; 
	protected String safi_account_id;
	protected String ultimoFiltro;
	protected String concatRiskCad   	= "";
	protected String concatAmmountCad	= "";
	protected String termCad;
	protected String diasPorTrans;
	protected String riskCad;
	protected String genderCad = "";
	protected String genderCadTemp;
	protected String tipo_fondeo;
	protected String previousType;
	protected String concatPrevLoanCad = "";
	protected String destiny_values;
	protected String filter_ammount;	
	protected String filter_loan_type;
	protected String fondPrev;
	
	protected String[] filtros;
	
	protected int typeSearch = 0;
	
	protected final int TODOS = 0;
	protected final int FONDEADOS_PREVIAMENTE = 1;
	protected final int SIN_FONDEO_PREVIO = 2;			
	
	protected boolean flagGender;
	
	public void setSafi_client_id(String safi_client_id)
	{
		this.safi_client_id = safi_client_id;
	}
	
	public void setsafi_account_id(String safi_account_id)
	{
		this.safi_account_id = safi_account_id;
	}
	
	public FilterStore getFilter()
	{
		return filter;
	}
	
	public void setQuery(String query)
	{
		ultimoFiltro = query;				
	}
}
