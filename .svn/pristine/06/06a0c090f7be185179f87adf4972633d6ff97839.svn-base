package mx.com.kubo.services.impl;

import java.io.Serializable;

import mx.com.kubo.model.Role_Searching;
import mx.com.kubo.model.Role_Searching_PK;
import mx.com.kubo.repositories.RoleSearchingDao;
import mx.com.kubo.services.RoleSearchingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSearchingServiceImp implements Serializable,RoleSearchingService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RoleSearchingDao rolesearchingdao;
	
	@Override
	public Role_Searching getRolesearchingbyPK(Role_Searching_PK pk){
		
		return rolesearchingdao.getRolesearchingbyPK(pk);
		
	}
	
}
