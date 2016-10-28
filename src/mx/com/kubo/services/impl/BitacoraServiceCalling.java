package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.Date;

import mx.com.kubo.constantes.RiskSGBWebService;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.services.ServiceCallingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component @Scope("prototype")
public class BitacoraServiceCalling implements Serializable
{	
	@Autowired @Qualifier("serviceCallingServiceImp")
	private ServiceCallingService service;
		
	private int company_id, prospectus_id;
	
	private ServiceCalling model;
	
	public void registrar(int estatus, RiskSGBWebService descripcion)
	{
		registrar(estatus, descripcion, "");
	}
	
	public void registrar(int estatus, RiskSGBWebService descripcion, String error)
	{
		model = new ServiceCalling();
		model.setAcces_datetime(new Date());
		model.setCompany_id(company_id);
		model.setProspectus_id(prospectus_id);				
		
		switch(estatus)
		{
			case 1:								
				model.setInfo("Invocando Servicio Web" + descripcion.toString());				
				model.setStatus(estatus);
			break;
			
			case 2:
				model.setInfo("Regresando satisfactoriamente de Web" + descripcion.toString());				
				model.setStatus(estatus);
			break;
			
			case 3:
				model.setInfo("Error al invocar" + descripcion.toString());
				model.setStatus(estatus);
				model.setException(error);
			break;
		}
		
		service.saveServiceCall(model);
	}	
	
	public void setService(ServiceCallingService service) 
	{
		this.service = service;
	}
	
	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
	
	public void setProspectus_id(int prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}
}
