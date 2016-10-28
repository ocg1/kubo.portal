package mx.com.kubo.mesa.solicitud.telefonos;

import java.util.ArrayList;

import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ReferencesReview;
import mx.com.kubo.model.Phone;

public abstract class PhoneReviewAMO extends PhoneReviewDMO
{	
	protected void init_phone_type_name(Phone phone) 
	{
		int phone_type = phone.getPhone_type_id();
		
		switch(phone_type)
		{		  
			case TRABAJO_FIJO:
				phone.getPhoneType().setName(" Fijo Trabajo");
				
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = true;
				
				if(phone.getExtension() != null)
				{
					phone_label = phone.getPhone_number() + " Ext. " + phone.getExtension();
					
				} else {
					
					phone_label = phone.getPhone_number();
				}
			break;
				
			case TRABAJO_CELULAR:
				phone.getPhoneType().setName("Celular Trabajo");
				
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
			
			case NEGOCIO_FIJO:
				phone.getPhoneType().setName("Fijo Negocio");
				
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
				phone_label = phone.getPhone_number();
			break;
			
			case NEGOCIO_CELULAR:
				phone.getPhoneType().setName("Celular Negocio");
				
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
			
			case PARTICULAR_FIJO:
				phone.getPhoneType().setName("Fijo Casa");
				
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
				phone_label = phone.getPhone_number();
			break;
			
			case PARTICULAR_CELULAR:
				phone.getPhoneType().setName(" Celular Propio");
				
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
				phone_label = "044" + phone.getPhone_number();
			break;
		}
	}
	
	protected void init_phone_view(Phone phone) 
	{
		view            = new PhoneReview();				
		lstPerson       = new ArrayList<PersonAndPhones>();
		lstReferences   = new ArrayList<ReferencesReview>();				
		lstPhonesUpdate = new ArrayList<Phone>();
		
		view.setLstPersonPhone(lstPerson);
		view.setLstReferences (lstReferences);				
		view.setLstPhoneUpdate(lstPhonesUpdate);
		view.setPhone_label(phone_label);
		view.setPanel_extension_ENABLED(panel_extension_ENABLED);
		view.setPanel_lada_ENABLED(panel_lada_ENABLED);
		view.setPhone(phone);
		view.setIndex(index);
		
		index++;
	}
	
	protected void init_phone_number(Phone phone) 
	{
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
		
		view.getLstPhoneUpdate().add(phone);
	}
	
	protected void init_has_references(Phone phone) 
	{
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
	}
}
