package mx.com.kubo.registro.datos.curp;

import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;

public final class CURPGeneratorIMP extends CURPGeneratorAMO
implements CURPGeneratorIMO
{
	public final void init_RFC() 
 	{ 
		if (rfc_ENABLED) 
		{
			if (person.getMiddle_name() != null)
			{
				name = person.getFirst_name().trim() + " " + person.getMiddle_name().trim();
				
			} else {
				
				name = person.getFirst_name().trim();
			}				
	
			father_last = person.getFather_last_name().trim();
			mother_last = person.getMother_last_name().trim();
			
			birthday = formatter.format(person.getDate_of_birth());				
			
			RFC = service_natural_person.getRFC(name, father_last, mother_last, birthday);
			
			value_ORIGINAL = person.getMx_rfc() != null ? person.getMx_rfc() : "";
			
			init_change_control("mx_rfc", value_ORIGINAL, RFC);
					
			person.setMx_rfc(RFC);
		}
 	}
 	
	public final void init_CURP() 
	{		
		if (rfc_ENABLED) 
		{
			estado = "";
			curp_ENABLED = false;
			
			if (citizenship_ENABLED)
			{
				if (state_ENABLED) 
				{
					state_cat_PK = new StateCatPK();					
					state_cat_PK.setCompany_id(person.getNatPerPK().getCompany_id());
					state_cat_PK.setState_id(person.getState_id());
					
					estado = ((StateCat) service_estado.getStateById(state_cat_PK)).getName();
					
					curp_ENABLED   = true;
					
				} else {
					
					estado = "Extranjero";
					
					curp_ENABLED = true;
				}
				
			} else {
				
				estado = "Extranjero";
				
				curp_ENABLED   = true;
			}
			
			if (curp_ENABLED)
			{
				CURP = service_natural_person.generaCURP(person, estado.toUpperCase());
				
				value_ORIGINAL = person.getMx_curp() != null ? person.getMx_curp() : "";
				
				init_change_control("mx_curp", value_ORIGINAL, CURP);
				
				person.setMx_curp(CURP);
				
				person = service_natural_person.update(person);
			}			
		}				
	}
}
