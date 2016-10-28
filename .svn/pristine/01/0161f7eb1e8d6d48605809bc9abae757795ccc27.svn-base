package mx.com.kubo.services.impl;

import java.io.Serializable;
import java.util.List;

import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.repositories.RoleAccessDao;
import mx.com.kubo.services.RoleAccessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAccessServiceImp implements Serializable,RoleAccessService  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RoleAccessDao roleaccessdao;
	
	@Override
	public List<RoleAccess> getAccessListByRole(int role_id, int company_id){
		
		return roleaccessdao.getAccessListByRole(role_id, company_id);
		
	}
	
	@Override
	public boolean deleteAndSaveRoleAccess( List<RoleAccess> roleaccess, int company_id, Integer role_id){
		
		return roleaccessdao.deleteAndSaveRoleAccess( roleaccess, company_id, role_id);
		
	}
	
}
