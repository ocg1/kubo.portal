package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.model.RoleFunction;

@ManagedBean @SessionScoped
public class RoleFunctionController extends RoleFunctionControllerDMO
implements Serializable 
{
	private static final long serialVersionUID = -6707289931498946943L;
	
	@PostConstruct
	public void init()
	{		
		lista_role_function = service_role_function.loadRoleFunctionList();	
	}
	
	public List<RoleFunction> getFunctionByRole(int role_id)
	{		
		List<RoleFunction> temporallist = new ArrayList<RoleFunction>() ;
		
		for(RoleFunction rf : lista_role_function)
		{			
			if(rf.getPk().getRole_id() == role_id)
			{				
				temporallist.add( rf );			
			}				
		}
		
		return temporallist;		
	}	
}
