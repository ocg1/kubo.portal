package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.bean.FunctionBean;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.repositories.RoleFunctionDao;
import mx.com.kubo.services.RoleFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleFunctionServiceImp implements Serializable, RoleFunctionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RoleFunctionDao rolefunctionrepository;
	
	
	@Override
	public List<RoleFunction> loadRoleFunctionList(){
		
		return rolefunctionrepository.loadRoleFunctionList();
		
	}
	
	@Override
	public boolean deleteAndSaveRoleFunctions(List<FunctionBean> fnc, int company, int role ){
		return rolefunctionrepository.deleteAndSaveRoleFunctions( fnc,  company, role );
	}
	
	@Override
	public List<RoleFunction> getLstFunctionByRole( int role, int company_id ){
		
		return rolefunctionrepository.getLstFunctionByRole(role, company_id);
		
	}
	
}
