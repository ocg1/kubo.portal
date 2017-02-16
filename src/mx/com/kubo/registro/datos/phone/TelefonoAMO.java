package mx.com.kubo.registro.datos.phone;

import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

public abstract class TelefonoAMO extends TelefonoDMO 
{
	protected void init_phone_fixed() 
	{
		if (thisPhoneFixed == null) 
		{
			thisPhoneFixed = new Phone();
			hasPhoneFixedProspectus = (false);
			
		} else {
			
			if (thisPhoneFixed.getPhone_number() != null) 
			{
				String array[] = thisPhoneFixed.getPhone_number().split("\\)");
				
				if(array != null && array.length >1)
				{
					ladaFixedProspectus = array[0].replace("(", "").trim();
					phoneFixedPropectus = ladaFixedProspectus + array[1];
					
				} else {
					
					phoneFixedPropectus = thisPhoneFixed.getPhone_number();
				}
			}
			
			hasPhoneFixedProspectus = true;
		}
	}
	
	protected void init_phone_cell() 
	{
		if (thisPhoneCell == null) 
		{
			thisPhoneCell = new Phone();
			hasPhoneCell = false;
			
		} else {
			
			if (thisPhoneCell.getPhone_number() != null) 
			{
				String array[] = thisPhoneCell.getPhone_number().split("\\)");
				
				if(array != null && array.length > 1)
				{
					String ph1 = array[0].replace("(", "").trim();
					String ph2 = array[1].replace("(", "").trim();
					
					ladaCelProspectus   = ph1 != null ? (ph1.trim().toUpperCase().equals("NULL") ? "" : ph1) : "";
					phoneCellProspectus = ph2 != null ? (ph2.trim().toUpperCase().equals("NULL") ? "" : ph2) : "";
					
					phoneCellProspectus = ladaCelProspectus + phoneCellProspectus;
					
				} else {
					
					phoneCellProspectus =  thisPhoneCell.getPhone_number();
				}
			}
			
			hasPhoneCell = true;
		}
	}
	
	protected void init_change_control(String field, String original_value, String new_value) 
	{			
		change_control_ENABLED = ! new_value.equalsIgnoreCase(original_value);
		
		if(change_control_ENABLED)
		{	
			change_control_PK = new Change_controlPK();
			change_control    = new Change_control();		
			
			change_control_PK.setProspectus_id(prospectus_id);
			change_control_PK.setCompany_id(company_id);
			
			change_control.setChange_controlPK(change_control_PK);		
			change_control.setChange_prospectus_id(change_prospectus_id);
			
			change_control.setAfected_table("gn_phone");	
			change_control.setField("phone_number");
			change_control.setAfected_table_type("gn_phone_type");
			change_control.setField_type_id(phone_type_id);
			
			change_control.setOriginal_value(original_value);
			change_control.setNew_value(new_value);
			
			change_control.setComments(comments);
			change_control.setIp(IP_address_client);
			change_control.setFocus_date(focus_date);

			change_control_OK = service_change_control.addChangeControl(change_control, prospectus_id, company_id);
		}
	}
	
	protected void updatePhoneCellProspectus() 
	{
		log.info("TelefonoAMO.updatePhoneCellProspectus():");
		log.info("====>>>>LADA CEL FIXED= " + ladaCelProspectus);
		log.info("====>>>>PHONE CEL FIXED= " + phoneCellProspectus);
		
		boolean flagSave = false;
		boolean flagUpdate = false;

		if (hasPhoneCell) 
		{
			if (phoneCellProspectus != null) 
			{
				thisPhoneCell.setPhone_number(phoneCellProspectus);
				
				flagUpdate = phoneService.updatePhone(thisPhoneCell);
			}
			
		} else {
			
			if (phoneCellProspectus != null) 
			{				
				thisPhoneCell.setPhone_number( phoneCellProspectus );

				PhonePK phonePk = new PhonePK(prospectus_id, company_id);
				
				thisPhoneCell.setPhonePk(phonePk);
				thisPhoneCell.setPhone_type_id(PARTICULAR_CELULAR);
				thisPhoneCell.setOwned("1");
				thisPhoneCell.setArea(area.charAt(0));
				
				flagSave = phoneService.addPhone(thisPhoneCell, prospectus_id, company_id);
				
				hasPhoneCell = true;
			}
		}
		
		if (flagSave)
		{		
			log.info("====>>>> Phone fixed employment saved successfully");
			
			actualizaPhoneInfusion( phoneCellProspectus );
					
		} else {
		
			log.info("====>>>> Phone fixed employment  NO saved");
		
		}
		
		if (flagUpdate)
		{		
			log.info("====>>>> Update phone fixed employment successfully");
			
			actualizaPhoneInfusion( phoneCellProspectus );
		
		} else {
			
			log.info("====>>>> Phone Fixed employment  NO update");			
		}	
	}
	
	protected void updatePhoneProspectus() 
	{
		log.info("TelefonoAMO.updatePhoneProspectus():");
		log.info("====>>>>LADA FIXED= " + ladaFixedProspectus);
		log.info("====>>>>PHONE FIXED= " + phoneFixedPropectus);
		
		boolean flagSave = false;
		boolean flagUpdate = false;

		if (hasPhoneFixedProspectus) 
		{
			if (phoneFixedPropectus != null) 
			{
				thisPhoneFixed.setPhone_number(phoneFixedPropectus);
				
				flagUpdate = phoneService.updatePhone(thisPhoneFixed);
			}
			
		} else {
			
			if (phoneFixedPropectus != null) 
			{
				thisPhoneFixed.setPhone_number(phoneFixedPropectus);
				
				PhonePK phonePk = new PhonePK(prospectus_id, company_id);
				thisPhoneFixed.setOwned("1");
				thisPhoneFixed.setArea(area.charAt(0));
				thisPhoneFixed.setPhonePk(phonePk);
				thisPhoneFixed.setPhone_type_id(PARTICULAR_FIJO);
				
				flagSave = phoneService.addPhone(thisPhoneFixed, prospectus_id, company_id);
				
				hasPhoneFixedProspectus = true;
			}
		}
		
		if (flagSave)
		{
			log.info("====>>>> Phone fixed employment saved successfully");
			
		} else {
			
			log.info("====>>>> Phone fixed employment  NO saved");
		}
		
		if (flagUpdate) 
		{
			log.info("====>>>> Update phone fixed employment successfully");
			
		} else {
			
			log.info("====>>>> Phone Fixed employment  NO update");
		}
	}
	
	private void actualizaPhoneInfusion( String phonestr )
	{	
		try
		{									
			if( infusion_id != null )
			{			
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id(company_id);
				system_param_PK_I.setSystem_param_id(IS_INFUSION_ENABLED);
				
				 SystemParam system_param_I = systemParamService .loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") )
				 {				 
					InfusionSoft infusion = new InfusionSoft();
					infusion.actualizaTelefonoContacto(infusion_id, phonestr);						
				 }			 
			}
		 
		} catch( Exception e ){
			
			e.printStackTrace();			
		}	
	}
}
