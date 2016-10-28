package mx.com.kubo.services;

import java.util.List;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public interface Change_controlService 
{
	boolean registrar_change_control(ChangeBean change_control_bean, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id);
	boolean registrar_change_control(ChangeBean change_control_bean, CasosPospuestosIMO pospuesto, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id);
	boolean registrar_change_control(ChangeBean change_control_bean, String estatus_ORIGINAL, String estatus_NEW, String comentario, int company_id, int emisor_prospectus_id, int acreditado_prospectus_id);
	
	boolean persist(Change_control change_control);
	
	boolean addChangeControl(Change_control change, int prospectus_id, int company_id);
	 
	Change_control getChangeControlByID (Change_controlPK changeControlPK);
	
	Change_control get_last_change (int prospectus_id, int company_id,String[] tables,String[] fields);
	Change_control getChangeControlByProspectByAfectedTablesFields (int prospectus_id, int company_id,String[] tables,String[] fields);
	 
	List<Change_control> getListByChangeByProspectus            (int change_prospectus_id);
	List<Change_control> getAllListChangeByProspectId           (int prospectus_id, int company_id);	 
	List<Change_control> getListByProspectByAfectedByTable      (int prospectus_id, int company_id, String table);
	List<Change_control> getListByProspectByAfectedTablesFields (int prospectus_id, int company_id, String[] tables, String[] fields); 
}
