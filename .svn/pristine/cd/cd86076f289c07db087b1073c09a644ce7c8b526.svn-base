package mx.com.kubo.registro.beneficiarios;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.Benefi_ciaries;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BeneficiariesService;

public interface BeneficiarioIMO 
{
	void setService_address(AddressService service);
	void setService_beneficiario(BeneficiariesService service);
	
	void setSesion(SessionBean sesion);
	
	void setPerson(NaturalPerson person);
		
	void init();
	void init_same_addres(AjaxBehaviorEvent event);
	void init_date_of_birth(Benefi_ciaries bean);
	
	void persist(Benefi_ciaries bean); 
}
