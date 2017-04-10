package mx.com.kubo.rest.tienda;

import javax.ws.rs.core.Response;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.InvestorSession;
import mx.com.kubo.rest.model.TiendaRequest;

public interface InvestmentListIMO 
{
	void setSesion(SessionBean sesion);
	void setSesion_investor(InvestorSession sesion_investor);
	
	void init();
	
	void updateByFiltering(TiendaRequest request);
	
	Response getResponseJSON();
}
