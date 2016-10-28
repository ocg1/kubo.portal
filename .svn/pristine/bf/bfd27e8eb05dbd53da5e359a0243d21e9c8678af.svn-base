package mx.com.kubo.services.impl;

import mx.com.kubo.model.RoleAssignment;
import mx.com.kubo.repositories.RoleAssignmentDao;
import mx.com.kubo.services.RoleAssignmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleAssignmentServiceImp implements RoleAssignmentService {
	
	@Autowired
	private RoleAssignmentDao roleassignmentrepository;
	
	@Override
	public boolean saveRoleAssignment( RoleAssignment role ){
		
		return roleassignmentrepository.saveRoleAssignment(role);
		
	}
	
	@Override
	public boolean updateRoleAssignment( RoleAssignment role ){
		
		return roleassignmentrepository.updateRoleAssignment(role);
		
	}
	
	@Override
	public boolean removeRoleAssignment( RoleAssignment role ){
		
		return roleassignmentrepository.removeRoleAssignment(role);
		
	}
	
	@Override
	public RoleAssignment getActualRoleAssignmentByProspectus(int prospectus_id, int company_id){
		
		return roleassignmentrepository.getActualRoleAssignmentByProspectus(prospectus_id, company_id);
		
	}
	
	@Override
	public RoleAssignment getPendingRoleAssignmentByProspectus(int prospectus_id, int company_id){
		
		return roleassignmentrepository.getPendingRoleAssignmentByProspectus(prospectus_id, company_id);
		
	}
	
}
