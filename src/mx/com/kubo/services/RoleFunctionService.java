package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.bean.FunctionBean;
import mx.com.kubo.model.RoleFunction;

public interface RoleFunctionService {

	public List<RoleFunction> loadRoleFunctionList();
	
	public boolean deleteAndSaveRoleFunctions(List<FunctionBean> fnc, int company, int role );
	
	public List<RoleFunction> getLstFunctionByRole( int role, int company_id );
	
}
