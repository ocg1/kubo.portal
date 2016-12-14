package mx.com.kubo.managedbeans.portal.telefonos;

import java.util.ArrayList;

import mx.com.kubo.bean.PhoneReviewEditor;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Phone;

public abstract class EditorTelefonoAMO extends EditorTelefonoDMO
{	
	public final void init_sesion() 
	{
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.init_sesion();
		
		access_from = reader.getAccess_from();		
		
		if(access_from != null)
		{
			  phone_id = reader.getPhone_id();
			    sesion = reader.getSesion();
			page_title = reader.getPage_title();
			
			          company_id = sesion.getCompany_id();
			       prospectus_id = sesion.getProspectus_id();
			change_prospectus_id = sesion.getCoachProspectus_id();
			   IP_address_client = sesion.getIP_address_client();	
			   
			auditor = new AccessIMP();
			auditor.setService_access(service_access);
			auditor.setSesion(sesion);
			auditor.setScreen_id(SCREEN_EDITAR_TELEFONOS);
			auditor.setAccess_from(access_from);
		}
	}
	
	protected void init_lista_phone() 
	{
		lista_phone_prospectus = service_phone.getPhoneByProspectusList(prospectus_id, company_id);				
		
		lista_phone_view = new ArrayList<PhoneReviewEditor>();
		
		for(Phone phone : lista_phone_prospectus)
		{						
			view = new PhoneReviewEditor();		
			view.setPhone_label(phone.getPhone_number());
			view.setPhone(phone);		
			view.setNumPhone(phone.getPhone_number());				
			
			lista_phone_view.add(view);																			
		}				
	}
	
	protected void init_change_control(Integer field_type_id, String origValue, String newValue, String comment)
	{	
		change_control_PK = new Change_controlPK();
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id   (company_id);
		
		changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(change_control_PK);
		changeCtrl.setChange_prospectus_id(change_prospectus_id);
		changeCtrl.setAfected_table("gn_phone");
		changeCtrl.setField("phone_number");
		changeCtrl.setAfected_table_type("gn_phone_type");
		changeCtrl.setField_type_id(field_type_id);					
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);		
		changeCtrl.setComments(comment);		
		changeCtrl.setIp(IP_address_client);
		
		change_control_OK = service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id);				
	}
}
