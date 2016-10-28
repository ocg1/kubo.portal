package mx.com.kubo.registro.beneficiarios;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Beneficiaries;
import mx.com.kubo.model.BeneficiariesPK;

public abstract class BeneficiarioAMO extends BeneficiarioDMO
{
	protected void init_fecha_nacimiento_INPUT() 
	{		
		for (int i = 1; i <= 31; i++) 
		{
			days.add("" + i);
		}
		
		for (int i = 0; i < monthStr.length; i++) 
		{
			months.add("" + monthStr[i]);
		}
		
		fecha = Calendar.getInstance();
		
		if((sesion.getArea()+"").equals("I"))
		{
			fecha.add(Calendar.YEAR,-100);
			
		} else {
			
			fecha.add(Calendar.YEAR,-70);
		}
		
		Date d1 = fecha.getTime();
		Integer year1 = Integer.parseInt(date_format.format(d1).split("/")[2]);
		
		fecha = Calendar.getInstance();
		Date d2 = fecha.getTime();
		Integer year2 = Integer.parseInt(date_format.format(d2).split("/")[2]);
		
		for (int i = year2; i >=year1; i--) 
		{
			years.add("" + i);
		}
	}
	
	protected void init_date_of_birth() 
	{
		String dayStr    = beneficiario_bean.getDay();
		String thisMonth = beneficiario_bean.getMonth();
		String yearStr   = beneficiario_bean.getYear();
		
		String thisDate = "";
		String sM = "";
		
		datebirth_ENABLED = false;

		if (dayStr != null && dayStr.length() > 0 && !dayStr.equals("0")) 
		{
			thisDate += dayStr + "/";
			
		} else {
			
			return;
		}

		if (dayStr.equals("31")) 
		{
			if (thisMonth.equals("Febrero") || thisMonth.equals("Abril")
					|| thisMonth.equals("Junio")
					|| thisMonth.equals("Septiembre")
					|| thisMonth.equals("Noviembre")) 
			{
				beneficiario_bean.setDay("0");
				return;
			}
		}
		
		if (thisMonth == null)
		{
			return;
		}
		
		if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) > 29) 
		{
			beneficiario_bean.setDay("0");
			return;
		}

		if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) == 29
				&& (Integer.parseInt(yearStr) % 4 != 0)) 
		{
			beneficiario_bean.setDay("0");
			
			return;
		}

		if (thisMonth != null && thisMonth.length() > 0 && !thisMonth.equals("0")) 
		{
			for (int i = 0; i < monthStr.length; i++) 
			{
				if ((monthStr[i]).equals(thisMonth)) 
				{
					if ((i + 1) < 10)
					{
						sM = "0" + (i + 1);
						
					} else {
						
						sM = (i + 1) + "";
					}
				}
			}
			
			thisDate += sM + "/";
			
		} else {
			
			return;
		}

		if (yearStr != null && yearStr.length() > 0 && !yearStr.equals("0")) 
		{
			thisDate += yearStr;
			
		} else {
			
			return;
		}

		date_format = new SimpleDateFormat("dd/MM/yyyy");
		
		try 
		{			
			beneficiario_bean.setDate_of_birth(date_format.parse(thisDate));	
			
			datebirth_ENABLED = true;
		
		} catch (Exception e) {
			
			beneficiario_bean.setDate_of_birth(null);
		}
	}
	
	protected void init_beneficiario_bean() 
	{
		beneficiarie = null;
		
		beneficiario_id  = beneficiario_bean.getBeneficiarie_id();
		tipo_cuenta_id   = beneficiario_bean.getSaving_account_id();
		prospectus_id    = beneficiario_bean.getProspectus_id();
		company_id       = beneficiario_bean.getCompany_id();			
		first_name       = beneficiario_bean.getFirst_name();
		middle_name      = beneficiario_bean.getMiddle_name();
		mother_last_name = beneficiario_bean.getMother_last_name();
		father_last_name = beneficiario_bean.getFather_last_name();
		fecha_nacimiento = beneficiario_bean.getDate_of_birth();
		parentesco       = beneficiario_bean.getRelationship();			
		genero           = beneficiario_bean.getGender_id();
		same_address     = beneficiario_bean.getSame_address();
		porcentaje       = beneficiario_bean.getPercentage();
		
		if(beneficiario_id != 0 && tipo_cuenta_id != 0)
		{			
			beneficiario_PK = new BeneficiariesPK(prospectus_id, company_id, tipo_cuenta_id, beneficiario_id);
				
			beneficiarie = service_beneficiario.getBeneficiariesByID(beneficiario_PK);	
		}
	}
	
	public final void asignar_mismo_domicilio()
	{				
		address_ORIGINAL = service_address.getMaxAddressByType(prospectus_id, company_id, CASA);
		
		if( address != null ){
			address_PK = address.getAddressPK();
		}
		
		if(address_PK == null)
		{		
			address_id = service_address.getMaxAddressId(prospectus_id, company_id);
			
			address_PK = new AddressPK();
			address_PK.setCompany_id(company_id);
			address_PK.setProspectus_id(prospectus_id);
			address_PK.setAddress_id(address_id);
			
			address.setAddressPK(address_PK);
			
			address_ORIGINAL.setAddressPK(address_PK);
			address_ORIGINAL.setBeneficiarie_id(beneficiarie_id);
			address_ORIGINAL.setAddress_type_id(BENEFICIARIO);
			
			save_OK = service_address.add(address_ORIGINAL);
			
		} else {
			
			//if( beneficiarie_id == null ){
				beneficiarie_id =address.getBeneficiarie_id();
			//}
		
			address_ORIGINAL.setAddressPK(address_PK);
			address_ORIGINAL.setBeneficiarie_id(beneficiarie_id);
			address_ORIGINAL.setAddress_type_id(BENEFICIARIO);
			
			save_OK = service_address.update(address_ORIGINAL);		
		}
	}
	
	protected void update_beneficiarie() 
	{
		update_OK = false;
		save_OK   = false;
		
		if(beneficiarie != null)
		{
			beneficiarie.setFirst_name(first_name);
			beneficiarie.setMiddle_name(middle_name);
			beneficiarie.setFather_last_name(father_last_name);
			beneficiarie.setMother_last_name(mother_last_name);
			beneficiarie.setGender_id(genero);
			
			if(porcentaje != null)
			{											
				double val = porcentaje;
				
				val = val*10000000000000000L;
				val = Math.round(val);
				val = val /10000000000000000L;
				porcentaje = (val);
			}else{
				porcentaje = 0D;
			}
			
			beneficiarie.setPercentage( BigDecimal.valueOf(porcentaje));
			beneficiarie.setRelationship(parentesco);
			beneficiarie.setSame_address(same_address);
			
			if(fecha_nacimiento != null)
			{	
				beneficiarie.setDate_of_birth(fecha_nacimiento);				
			}	
			
			update_OK = service_beneficiario.updateBeneficiaries(beneficiarie);
			
		} else {
			
			beneficiarie = new Beneficiaries();
			
			BeneficiariesPK bpk = new BeneficiariesPK();
			beneficiarie.setBeneficPk(bpk);
			beneficiarie.getBeneficPk().setProspectus_id(prospectus_id);
			beneficiarie.getBeneficPk().setCompany_id(company_id);
			beneficiarie.getBeneficPk().setSaving_account_id(tipo_cuenta_id);
			
			save_OK = service_beneficiario.saveBeneficByID(beneficiarie);	
		}
	}
}
