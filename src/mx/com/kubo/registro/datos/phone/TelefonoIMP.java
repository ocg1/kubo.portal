package mx.com.kubo.registro.datos.phone;

import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;

public class TelefonoIMP extends TelefonoAMO
implements TelefonoIMO
{
	public void init() 
	{
		thisPhoneFixed = phoneService.getPhoneByTypeByArea(prospectus_id, company_id, 5, area);
		thisPhoneCell  = phoneService.getPhoneByTypeByArea(prospectus_id, company_id, 6, area);

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
		
		if (thisPhoneCell == null) 
		{
			thisPhoneCell = new Phone();
			hasPhoneCell = false;
			
		} else {
			
			if (thisPhoneCell.getPhone_number() != null) 
			{
				String array[] = getThisPhoneCell().getPhone_number().split("\\)");
				
				if(array != null && array.length > 1)
				{
					String ph1 = array[0].replace("(", "").trim();
					String ph2 = array[1].replace("(", "").trim();
					
					ladaCelProspectus   = ph1 != null ? (ph1.trim().toUpperCase().equals("NULL") ? "" : ph1) : "";
					phoneCellProspectus = ph2 != null ? (ph2.trim().toUpperCase().equals("NULL") ? "" : ph2) : "";
					
					phoneCellProspectus = ladaCelProspectus + phoneCellProspectus;
					
				} else {
					
					phoneCellProspectus =  getThisPhoneCell().getPhone_number();
				}
			}
			
			hasPhoneCell = true;
		}
	}
	
	public void updatePhoneCellProspectus() 
	{
		log.info("TelefonoIMP.updatePhoneCellProspectus():");
		log.info("====>>>>LADA CEL FIXED= " + getLadaCelProspectus());
		log.info("====>>>>PHONE CEL FIXED= " + getPhoneCellProspectus());
		
		boolean flagSave = false;
		boolean flagUpdate = false;

		if (isHasPhoneCell()) 
		{
			if (getPhoneCellProspectus() != null) 
			{
				getThisPhoneCell().setPhone_number(getPhoneCellProspectus());
				
				flagUpdate = phoneService.updatePhone(getThisPhoneCell());
			}
			
		} else {
			
			if (getPhoneCellProspectus() != null) 
			{				
				getThisPhoneCell().setPhone_number( getPhoneCellProspectus() );

				PhonePK phonePk = new PhonePK(prospectus.getProspectusPK()
						.getProspectus_id(), prospectus.getProspectusPK()
						.getCompany_id());
				
				getThisPhoneCell().setPhonePk(phonePk);
				getThisPhoneCell().setPhone_type_id(6);
				getThisPhoneCell().setOwned("1");
				getThisPhoneCell().setArea(prospectus.getArea());
				
				flagSave = phoneService.addPhone(getThisPhoneCell(), prospectus_id, company_id);
				
				setHasPhoneCell(true);
			}
		}
		
		if (flagSave)
		{		
			log.info("====>>>> Phone fixed employment saved successfully");
			
			actualizaPhoneInfusion( getPhoneCellProspectus() );
					
		} else {
		
			log.info("====>>>> Phone fixed employment  NO saved");
		
		}
		
		if (flagUpdate)
		{		
			log.info("====>>>> Update phone fixed employment successfully");
			
			actualizaPhoneInfusion( getPhoneCellProspectus() );
		
		} else {
			
			log.info("====>>>> Phone Fixed employment  NO update");			
		}
	}
}
