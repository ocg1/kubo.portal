package mx.com.kubo.notificaciones.receptores;

import mx.com.kubo.constantes.SystemParamNotificacion;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.SystemParamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class SocioParterDMO 
{
	protected Integer company_id;
	
	protected SystemParamPK system_param_PK;
	protected SystemParam   system_param;
	
    @Autowired @Qualifier("systemParamServiceImp")
	protected SystemParamService service_system_param;
	
	public final void setService_system_param(SystemParamService service)
	{
		service_system_param = service;
	}
	
	public final void setCompany_id(Integer company_id)
	{
		this.company_id = company_id;
	}
	
	protected final String getCorreo(SystemParamNotificacion evento) 
	{
		system_param_PK = new SystemParamPK();		
		system_param_PK.setCompany_id(company_id);				
		system_param_PK.setSystem_param_id(evento.getId());
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
		
		return system_param.getValue();	
	}
}
