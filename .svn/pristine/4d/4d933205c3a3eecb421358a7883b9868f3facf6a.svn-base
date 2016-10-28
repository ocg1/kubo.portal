package mx.com.kubo.mesa.solicitud.telefonos;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;

public abstract class TelefonosAMO extends TelefonosDMO
{
/*	
	protected void init_phones() 
	{
		int index = 0;
		
		lista_phone_view = new ArrayList<PhoneReview>();
		
		for(Phone phone : lista_phone_prospectus)
		{			
			asignar_tipo_telefono(phone);
			
			if(!insertado)
			{				
				init_phone_type_name(phone);				
				
				view            = new PhoneReview();				
				lstPerson       = new ArrayList<PersonAndPhones>();
				lstReferences   = new ArrayList<ReferencesReview>();				
				lstPhonesUpdate = new ArrayList<Phone>();
				
				view.setPhone_label(phone_label);
				view.setPanel_extension_ENABLED(panel_extension_ENABLED);
				view.setPanel_lada_ENABLED(panel_lada_ENABLED);
				view.setPhone(phone);
				view.setIndex(index);
				
				index++;
				
				if(phone.getPhone_number() == null) 
				{
					phone.setPhone_number( "" );
				}
				
				srr = phone.getPhone_number().split("\\)");								
				
				if((srr).length > 1)
				{
					String phone_number = srr[0].replace("(", "").trim() + phone.getPhone_number().split("\\)")[1];
					
					view.setNumPhone(phone_number);
					
				} else {
					
					String phone_number =  srr[0];
					
					view.setNumPhone(phone_number);
				}
									
				view.setLstPersonPhone(lstPerson);
				view.setLstReferences (lstReferences);				
				view.setLstPhoneUpdate(lstPhonesUpdate);
				view.getLstPhoneUpdate().add(phone);
				
				String phone_number = phone.getPhone_number();
				
				phone_number = phone_number.replace(" ", "").replace("-", "").replace(".", "");
				
				phone.setPhone_number(phone_number);
				
				if( phone.getPhone_number().trim().length() > 0 )
				{				
					existPhoneStr = service_telefono.existPhone(phone, prospectus_id, company_id);
				
				} else {
					
					existPhoneStr = "0;;0";
				}
				
				existPerson  = existPhoneStr.split(";;")[0];
				existReferen = existPhoneStr.split(";;")[1];
				
				if(existPerson != null && Integer.parseInt( existPerson ) > 0)
				{
					view.setHasPerson(true);
					
				} else {
					
					view.setHasPerson(false);
				}
				
				if(existReferen != null && Integer.parseInt( existReferen ) > 0)
				{
					view.setHasReferences(true);
					
				} else {
					
					view.setHasReferences(false);
				}
				
				lista_phone_view.add(view);																		
			}			
		}		
	}
	
	private void init_phone_type_name(Phone phone) 
	{
		switch (phone.getPhone_type_id())
		{		  
			case 1:
				phone.getPhoneType().setName(" Fijo Trabajo");
				insertado = true;
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = true;
				
				if(phone.getExtension() != null)
				{
					phone_label = phone.getPhone_number() + " Ext. " + phone.getExtension();
					
				} else {
					
					phone_label = phone.getPhone_number();
				}
			break;
				
			case 2:
				phone.getPhoneType().setName("Celular Trabajo");
				insertado = true;
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
			
			case 3:
				phone.getPhoneType().setName("Fijo Negocio");
				insertado = true;
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
				phone_label = phone.getPhone_number();
			break;
			
			case 4:
				phone.getPhoneType().setName("Celular Negocio");
				insertado = true;
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
			
			case 5:
				phone.getPhoneType().setName("Fijo Casa");
				insertado = true;
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
				phone_label = phone.getPhone_number();
			break;
			
			case 6:
				phone.getPhoneType().setName(" Celular Propio");
				insertado = true;
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
		}
	}

	private void asignar_tipo_telefono(Phone phone) 
	{					
		insertado = false;
		
		for(PhoneReview view : lista_phone_view)
		{							
			String phone_number = view.getPhone().getPhone_number();
			tipo_telefono       = view.getPhone().getPhoneType().getName();
			
//			if( phone.getPhone_number() != null && phone.getPhone_number().equals(phone_number))
			{								
				view.getLstPhoneUpdate().add(phone);
				
				switch (phone.getPhone_type_id())
				{					
					case 1:
					case 2:
						view.getPhone().getPhoneType().setName(tipo_telefono + "/Trabajo");
						insertado = true;
					break;
					
					case 3:
					case 4:
						view.getPhone().getPhoneType().setName(tipo_telefono+"/Negocio");
						insertado = true;
					break;
					
					case 5:
						view.getPhone().getPhoneType().setName(tipo_telefono+"/Casa");
						insertado = true;
					break;
					
					case 6:
						view.getPhone().getPhoneType().setName(tipo_telefono+"/Propio");
						insertado = true;
					break;
						
				}
			}
		}
	}
*/	
	
	protected final void init_change_control(String field, String origValue, String newValue, String comment)
	{
		change_control_PK = new Change_controlPK();
		
		change_control_PK.setProspectus_id(prospectus_id);
		change_control_PK.setCompany_id   (company_id);
		
		changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(change_control_PK);
		changeCtrl.setChange_prospectus_id(sesion.getProspectus_id());
		changeCtrl.setAfected_table("gn_phone");
		changeCtrl.setIp(sesion.getIP_address_client());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);		
		
		change_control_OK = service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id);				
	}
}
