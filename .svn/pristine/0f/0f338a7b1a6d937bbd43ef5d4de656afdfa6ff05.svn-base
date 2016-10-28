package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.SystemParamService;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public final class LeyTransparencia  
implements Serializable 
{
	@ManagedProperty("#{systemParamServiceImp}")
	private SystemParamService systemparamservice;
	
	private SystemParam   system_param;
	private SystemParamPK system_param_PK = new SystemParamPK();
			
	private boolean disMenu;
	private boolean menu_captacion_ENABLED;
	
	public LeyTransparencia()
	{
		disMenu = false;
	}

	public boolean isDisMenu() 
	{
		return disMenu;
	}

	public void setDisMenu(boolean disMenu) 
	{
		this.disMenu = disMenu;
	}
	
	public String muestraComisiones()
	{
		setDisMenu(true);
		return "leyTransparencia";
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public final boolean isMenu_captacion_ENABLED() 
	{
		system_param_PK.setCompany_id(1);
		system_param_PK.setSystem_param_id(51);
		
		system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
		
		
		if( system_param != null )
		{
			if(system_param.getValue().equals("S"))
			{
				menu_captacion_ENABLED = true;
				
			} else {
				
				menu_captacion_ENABLED = false;
			}
		}
		
		return menu_captacion_ENABLED;
		
	}	
}
