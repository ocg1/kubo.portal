package mx.com.kubo.repositories;

import java.util.List;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public interface Change_controlDAO 
{	
	boolean persist(Change_control change_control);
	
	boolean addChangeControl(Change_control change, int prospectusID, int companyID);
	
	Change_control getChangeControlByID(Change_controlPK changeControlPK);
	
	Change_control getChangeControlByProspectByAfectedTablesFields(int prospectus_id, int company_id,String[] tables, String[]fields);
	
	List<Change_control> getAllListChangeByProspectId(int prospectus_id, int company_id);
	
	List<Change_control> getListByChangeByProspectus(int change_prospectus_id);
	
	List<Change_control> getListByProspectByAfectedByTable      (int prospectus_id, int company_id, String table);
	List<Change_control> getListByProspectByAfectedTablesFields (int prospectus_id, int company_id, String[] tables,String[]fields);			
}
