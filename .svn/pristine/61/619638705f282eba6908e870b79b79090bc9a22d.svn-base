package mx.com.kubo.managedbeans.registro.sobre;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.change_control.ChangeControlEMO;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.PurposePK;

public abstract class MiPrestamoPMO extends MiPrestamoAMO
{		
	public final void prepararChangeData()
	{				
		change_control_bean = new ChangeBean(ChangeControlEMO.REGISTRO_MI_PRESTAMO_OTHER_DEBTS);
				
		change_control_bean.setNewValue(thisProyect.getOther_debts());
		change_control_bean.setOrigValue(other_debts_ORIGINAL != null ? other_debts_ORIGINAL : "");				
		
		if(change_control_bean.isValueChanged())
		{
			updateProyectBlur();
			
			if(saveChangeData(change_control_bean))
			{
				other_debts_ORIGINAL = thisProyect.getOther_debts();
				
				System.out.println("ChangeControl.REGISTRO_MI_PRESTAMO_OTHER_DEBTS");
			}
		}
	}
	
	public final boolean saveChangeData(ChangeBean field)
	{		
		int prospectus_id, company_id;
		
		prospectus_id = naturalperson.getNatPerPK().getProspectus_id();
		company_id    = naturalperson.getNatPerPK().getCompany_id();
		
		changeCtrlPK = new Change_controlPK();
		changeCtrl   = new Change_control();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		changeCtrl.setChange_controlPK(changeCtrlPK);		
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());		
		changeCtrl.setAfected_table (field.getChange_control().getAfected_table());	
		changeCtrl.setField         (field.getChange_control().getField());		
		changeCtrl.setOriginal_value(field.getOrigValue());
		changeCtrl.setNew_value     (field.getNewValue());		
		changeCtrl.setComments      (field.getWhyChangeData());
		changeCtrl.setIp            (field.getIp());
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
			
		} else {
			
			return false;
		}
	}
	
	public void updateProyectBlur()
	{		
		log.info("tipos de dato es !!!!!!!!!= " + getPurpose_id());
		
		if(purpose_id != null)
		{			
			thisProyect.setPurpose_id(purpose_id);
			
			purpose_PK = new PurposePK(purpose_id, sesion.getCompany_id());
			
			purpose = service_purpose.getPurposeById(purpose_PK);
			
			thisProyect.setType_id(purpose.getType_id());
		}
		
		if(hasProyect)
		{		
			log.info("Actualizando...................................");
			
			flag = service_proyect.update(thisProyect);
			
		} else {
			
			log.info("Insertando...................................");
			
			project_PK = new ProyectPK(service_proyect.getMaxProyectID(),sesion.getProspectus_id(),sesion.getCompany_id());
			
			thisProyect.setProyectoPk(project_PK);
			
			flag = service_proyect.add(thisProyect);
			
			hasProyect = true;
		}
		
		if(flag)
		{
			log.info("!!!!!!!! Proyect saved successfully !!!!!!!!!!!!!!!");
			
		} else {
			
			log.info("!!!!!!!! Proyect  not saved !!!!!!!!!!!!!!!");
		}		
	}
}
