package mx.com.kubo.mesa.solicitud.perfil;

import java.util.ArrayList;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class EditorIdentificationAMO extends EditorIdentificationDMO
{
	protected void init_label() 
	{		
		label_ENABLED = true;
		
		switch(identification_type_id)
		{
			case IFE:
				editor_ife_ENABLED = true;
				
				label_ENABLED = false;
			
				init_change_control_IFE();
				break;
		
			case INE:
				label = label_INE;
				
				editor_ife_ENABLED = false;
			break;
			
			case PASAPORTE:
				label = label_PASAPORTE;
			break;
			
			case CARTILLA:
				label = label_CARTILLA;
			break;
			
			case LICENCIA:
				label = label_LICENCIA;
			break;
			
			case CEDULA:
				label = label_CEDULA;
			break;						
		}
	}
	
	protected void init_change_control_IFE() 
	{
		changeDataIFE = new ChangeBean();
		
		lstChange_ife = null;
				
		int prospectus_id = person.getNatPerPK().getProspectus_id();
		int company_id    = person.getNatPerPK().getCompany_id();
			
		String[] tables = new String[]{"gn_natural_person"};
		String[] fields = new String[]{"mx_ife_cveelector","mx_ife_numemision","mx_ife_seccion","mx_ife_numvertical"};
			
		service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);		
		
		changeDataIFE.setHasChange( lstChange_ife != null && lstChange_ife.size() > 0 ? true : false);
		changeDataIFE.setLstChanges(lstChange_ife != null && lstChange_ife.size() > 0 ? lstChange_ife : null);
		
		ifeChangeTem = new ChangeBean();
		lstChangeIFE = new ArrayList<ChangeBean>();
		
		String  mx_ife_cveelector  = person.getMx_ife_cveelector();
		String  mx_ife_seccion     = person.getMx_ife_seccion();
		String  mx_ife_numvertical = person.getMx_ife_numvertical();
		Integer mx_ife_numemision  = person.getMx_ife_numemision();
		
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_cveelector");
		ifeChangeTem.setOrigValue(mx_ife_cveelector != null && mx_ife_cveelector != "" ? mx_ife_cveelector : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_cveelector != null && mx_ife_cveelector != "" ? mx_ife_cveelector : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_numemision");
		ifeChangeTem.setOrigValue(mx_ife_numemision != null && mx_ife_numemision.toString() != "" ? mx_ife_numemision.toString() : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_numemision != null && mx_ife_numemision.toString() != "" ? mx_ife_numemision.toString() : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_seccion");
		ifeChangeTem.setOrigValue(mx_ife_seccion != null && mx_ife_seccion != "" ? mx_ife_seccion : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_seccion != null && mx_ife_seccion != "" ? mx_ife_seccion : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);		
		
		ifeChangeTem = new ChangeBean();
		ifeChangeTem.setNameTable("gn_natural_person");
		ifeChangeTem.setNameField("mx_ife_numvertical");
		ifeChangeTem.setOrigValue(mx_ife_numvertical != null && mx_ife_numvertical != "" ? mx_ife_numvertical : "No proporcionado");
		ifeChangeTem.setNewValue( mx_ife_numvertical != null && mx_ife_numvertical != "" ? mx_ife_numvertical : "No proporcionado");
		
		lstChangeIFE.add(ifeChangeTem);	
	}
	
	protected final boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(person.getNatPerPK().getProspectus_id());
		changeCtrlPK.setCompany_id(person.getNatPerPK().getCompany_id());
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(sesion.getIP_address_client());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);
		
		int prospectus_id = person.getNatPerPK().getProspectus_id();
		int company_id    = person.getNatPerPK().getCompany_id();
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
		} else {
			return false;
		}
	}
}
