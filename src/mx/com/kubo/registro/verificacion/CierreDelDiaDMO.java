package mx.com.kubo.registro.verificacion;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.SystemParamService;

public abstract class CierreDelDiaDMO 
implements CierreDelDiaIMO
{
	protected SystemParamService service_system_param;
	
	protected SessionBean sesion;
	
	protected Membership membership;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected Character area;
	
	protected int company_id;
	protected int system_param_id;
	
	protected boolean cierre_del_dia_ENABLED;
	
	public final void setService_system_param(SystemParamService service)
	{
		service_system_param = service;
	}
	
	public final void setSesion(SessionBean sesion)
	{				
		company_id = sesion.getCompany_id();
	}
	
	public final void setMembership(Membership membership)
	{		
		company_id = membership.getMembershipPK().getCompany_id();
	}
	
	public final void setSystem_param_id(int system_param_id)
	{
		system_param_PK = new SystemParamPK();
		system_param_PK.setCompany_id(company_id);
		system_param_PK.setSystem_param_id(system_param_id);
		
		system_param = service_system_param.loadSelectedSystemParam(system_param_PK);
	}
	
	public final boolean isCierre_del_dia_ENABLED()
	{
		return  cierre_del_dia_ENABLED;
	}
}
