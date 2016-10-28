package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.RoleAccess;

public interface RoleAccessDao {

	public List<RoleAccess> getAccessListByRole(int role_id, int company_id);
	public boolean deleteAndSaveRoleAccess( List<RoleAccess> roleaccess, int company_id, int role_id);
	
}
