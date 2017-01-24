package mx.com.kubo.rest.saldos;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.ws.rs.core.Response;

import mx.com.kubo.model.ProyectLoanActiveInSafi;
import mx.com.kubo.rest.model.ResponseProyectLoanActiveInSafi;
import mx.com.kubo.services.ProyectLoanService;

public abstract class ProyectLoanActiveDMO 
implements ProyectLoanActiveIMO
{
	protected List<ProyectLoanActiveInSafi> safiProyecLoanActiveLst;
	
	protected List<ResponseProyectLoanActiveInSafi> active_list;
	
	protected ResponseProyectLoanActiveInSafi response;
	
	protected ProyectLoanService service_proyect_loan;
	
	protected SimpleDateFormat date_format;
	
	protected Response responseJSON;
	
	protected ProyectLoanActiveDMO()
	{
		date_format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es","MX"));
	}

	public void setService_proyect_loan(ProyectLoanService service)
	{
		service_proyect_loan = service;
	}

	public Response getResponseJSON() 
	{
		return responseJSON;
	}
}
