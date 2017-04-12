package mx.com.kubo.mesa.solicitud.permisos;

public class RoleFunctionIMP extends RoleFunctionAMO
implements RoleFunctionIMO
{
	public void init()
	{		
		if(role_id != null)
		{
			lista_funciones = role_function.getFunctionByRole(role_id);
			
			menuAccess = accessService.loadControlMenu(role_id, company_id);
		}
		
		init_role_function();								
		init_access_collector();					
		init_constraints();
	}
}
