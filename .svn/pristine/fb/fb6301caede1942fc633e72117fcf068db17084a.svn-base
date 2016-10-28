package mx.com.kubo.services.impl;

import java.util.List;

import mx.com.kubo.model.Role;
import mx.com.kubo.repositories.RoleDao;
import mx.com.kubo.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

	@Autowired
	private RoleDao roleRepository;
	
	public List<Role> getLstRoles(){
		
		return roleRepository.getLstRoles();
		
	}
	
}
