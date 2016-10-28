package mx.com.kubo.managedbeans;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.services.RoleFunctionService;

public abstract class RoleFunctionControllerDMO 
{
	@ManagedProperty("#{roleFunctionServiceImp}")
	protected RoleFunctionService service_role_function;
	
	protected List<RoleFunction> lista_role_function;
	
	public void setService_role_function(RoleFunctionService service) 
	{
		service_role_function = service;
	}
}
