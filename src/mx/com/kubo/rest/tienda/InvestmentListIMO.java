package mx.com.kubo.rest.tienda;

import javax.ws.rs.core.Response;

import mx.com.kubo.managedbeans.SessionBean;

public interface InvestmentListIMO 
{
	void setSesion(SessionBean sesion);
	
	void init();
	
	Response getResponseJSON();
}
