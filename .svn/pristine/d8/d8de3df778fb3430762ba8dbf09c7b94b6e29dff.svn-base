package mx.com.kubo.managedbeans.registro.sobre;

import java.util.ArrayList;

import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.registro.sobreti.actividad.DomicilioIMP;

public abstract class MiPrestamoAMO extends MiPrestamoDMO
{	
	protected void init_natural_person() 
	{
		natural_person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
		
		naturalperson = service_natural_person.getNaturalPersonById(natural_person_PK);
		
		selectedComputer = new ArrayList<String>();
		selectedInternet = new ArrayList<String>();
		
		if (naturalperson.getHas_computer_home() != null && naturalperson.getHas_computer_home() == 1)
		{
			selectedComputer.add("1");
		}
		
		if (naturalperson.getHas_computer_employment() != null && naturalperson.getHas_computer_employment() == 1)
		{
			selectedComputer.add("2");
		}
		
		if (naturalperson.getHas_computer_business() != null && naturalperson.getHas_computer_business() == 1)
		{
			selectedComputer.add("3");
		}				
		
		if (naturalperson.getHas_internet_home() != null && naturalperson.getHas_internet_home() == 1)
		{
			selectedInternet.add("1");
		}
		
		if (naturalperson.getHas_internet_employment() != null && naturalperson.getHas_internet_employment() == 1)
		{
			selectedInternet.add("2");
		}
		
		if (naturalperson.getHas_internet_business() != null && naturalperson.getHas_internet_business() == 1)
		{
			selectedInternet.add("3");
		}
	}
	
	protected void init_membership() 
	{
		membership_PK  = new MembershipPK(prospectus_id,company_id);
		
		thisMembership = service_membership.getMembershipById(membership_PK);
		
		if (thisMembership.getFromwhere_id() == null)
		{
			thisMembership.setFromwhere_id(1);
		}
		
		if (thisMembership.getWho_answered_id() == null)
		{
			thisMembership.setWho_answered_id(1);
		}
	}
	
	protected void init_proyect() 
	{
		thisProyect = service_proyect.getMaxProyect(prospectus_id,company_id);

		if(thisProyect == null)
		{
			thisProyect = new Proyect();
			
			other_debts_ORIGINAL = "";
			hasProyect = false;			
			
		} else {
			
			if(thisProyect.getPurpose_id() != null)
			{
				purpose_id = thisProyect.getPurpose_id();
			}
			
			other_debts_ORIGINAL = thisProyect.getOther_debts();
			
			hasProyect = true;
		}
	}
			
	protected void init_domicilio(int address_type_id) 
	{
		if(address_type_id == address_type_NEGOCIO)
		{
			domicilio_negocio = new DomicilioIMP();
			domicilio_negocio.setService_address       (service_address);
			domicilio_negocio.setService_employment    (service_employment);
			domicilio_negocio.setService_change_control(service_change_control);						
			domicilio_negocio.setPerson(naturalperson);			
			domicilio_negocio.setSesion(sesion);
			domicilio_negocio.setAddress_type_id(address_type_id);
		}
		
		if(address_type_id == address_type_EMPLEO)
		{
			domicilio = new DomicilioIMP();
			domicilio.setService_address       (service_address);
			domicilio.setService_employment    (service_employment);
			domicilio.setService_change_control(service_change_control);			
			domicilio.setPerson(naturalperson);			
			domicilio.setSesion(sesion);
			domicilio.setAddress_type_id(address_type_id);
		}
	}
	
}
