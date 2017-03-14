package mx.com.kubo.rest.tienda;

import javax.ws.rs.core.Response;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.InvestorSession;

public interface InvestmentListIMO 
{
	void setSesion(SessionBean sesion);
	void setSesion_investor(InvestorSession sesion_investor);
	
	void init();
	
	Response getResponseJSON();
}
