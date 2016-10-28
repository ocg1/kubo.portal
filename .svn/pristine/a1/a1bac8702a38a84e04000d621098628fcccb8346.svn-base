package mx.com.kubo.mesa.autenticacion.preguntas;

import java.util.List;

import mx.com.kubo.mesa.autenticacion.AutenticacionDMO;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.tools.Utilities;

public abstract class TelefonoDMO extends AutenticacionDMO
{
	protected PhoneService service_phone;
	
	protected List<Phone> celulares;
	
	protected String phone_number;
	
	protected boolean celular_ENABLED;
	
	protected TelefonoDMO()
	{
		service_phone = Utilities.findBean("phoneServiceImp");
	}
	
	public final void setNatural_person(NaturalPerson natural_person) 
	{
		this.natural_person = natural_person;
		
		company_id    = natural_person.getNatPerPK().getCompany_id();
		prospectus_id = natural_person.getNatPerPK().getProspectus_id();
		
		celulares = service_phone.getPhoneListByType(prospectus_id, company_id, 6);									
	}
}
