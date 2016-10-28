package mx.com.kubo.mesa.solicitud.perfil.domicilio;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.Residence;

public interface AddressTokenIMO 
{
	String getAddressTOKEN();
	String getInvestor_TOKEN();
	String getAddress_TOKEN();
	String getActivity_TOKEN();
	String getAddress_activity_TOKEN();
	String getAddress_beneficiario_TOKEN();	
	
	void setResidence(Residence residence);
	void setAntiguedad(Integer length_residence);
	void setAddress(Address address);
	
	void setEmployment(Employment employment);
	void setBusiness(Business business);
}
