package mx.com.kubo.services.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.repositories.Change_controlDAO;
import mx.com.kubo.services.Change_controlService;

public abstract class ServiceChangeControlDMO 
implements Change_controlService
{
	@Autowired
	protected Change_controlDAO dao;
	
	protected SimpleDateFormat formatter_date;
	
	protected ServiceChangeControlDMO()
	{
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public Change_control getChangeControlByID(Change_controlPK changeControlPK) 
	{
		return dao.getChangeControlByID(changeControlPK);
	}
	
	public final boolean addChangeControl(Change_control change, int prospectus_id, int company_id) 
	{		
		return dao.addChangeControl(change, prospectus_id, company_id);
	}
	
	public final boolean persist(Change_control change_control) 
	{		
		return dao.persist(change_control);
	}

	public List<Change_control> getListByChangeByProspectus(int change_prospectus_id) 
	{
		return dao.getListByChangeByProspectus(change_prospectus_id);
	}

	public List<Change_control> getAllListChangeByProspectId(int prospectus_id,int company_id) 
	{		
		return dao.getAllListChangeByProspectId(prospectus_id, company_id);
	}

	public List<Change_control> getListByProspectByAfectedTablesFields(int prospectus_id, int company_id, String[] tables, String[]fields) 
	{
		return dao.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
	}

	public List<Change_control> getListByProspectByAfectedByTable(int prospectus_id, int company_id, String table) 
	{
		return dao.getListByProspectByAfectedByTable(prospectus_id, company_id, table);
	}
	
	public final Change_control get_last_change(int prospectus_id, int company_id, String[] tables, String[] fields)
	{
		return getChangeControlByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
	}
	
	public Change_control getChangeControlByProspectByAfectedTablesFields(int prospectus_id, int company_id, String[] tables, String[]fields) 
	{
		return dao.getChangeControlByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
	}
	
	protected boolean addChangeControl(ChangeBean change_control_bean) 
	{				 		
		Change_controlPK changeCtrlPK = new Change_controlPK();
		Change_control   changeCtrl   = new Change_control();
		
		ChangeControlEMO evento = change_control_bean.getChange_control();
					
		changeCtrlPK.setProspectus_id      (change_control_bean.getAcreditado_prospectus_id());
		changeCtrlPK.setCompany_id         (change_control_bean.getCompany_id());	
		
		changeCtrl.setChange_controlPK     (changeCtrlPK);		
					
		changeCtrl.setAfected_table        (evento.getAfected_table());	
		changeCtrl.setField                (evento.getField());		
		changeCtrl.setOriginal_value       (change_control_bean.getOrigValue());
		changeCtrl.setNew_value            (change_control_bean.getNewValue());		
		changeCtrl.setComments             (change_control_bean.getWhyChangeData());
		changeCtrl.setIp                   (change_control_bean.getIp());
		
		changeCtrl.setChange_prospectus_id (change_control_bean.getEmisor_prospectus_id());
		
		if(addChangeControl(changeCtrl, change_control_bean.getAcreditado_prospectus_id(), change_control_bean.getCompany_id()))
		{
			return true;
			
		} else {
			
			return false;
		}
	}
}
