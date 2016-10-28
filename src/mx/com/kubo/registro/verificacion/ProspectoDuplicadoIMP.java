package mx.com.kubo.registro.verificacion;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.gnNaturalPersonPK;

import org.springframework.stereotype.Service;

@Service("service_prospecto_duplicado")
public final class ProspectoDuplicadoIMP extends ProspectoDuplicadoAMO
implements ProspectoDuplicadoIMO
{	
	public final void init_natural_person(SessionBean sesion)
	{
		this.sesion = sesion;
		
		natural_person_PK = new gnNaturalPersonPK();
		natural_person_PK.setCompany_id(sesion.getCompany_id());
		natural_person_PK.setProspectus_id(sesion.getProspectus_id());
		
		naturalPerson = service_natural_person.getNaturalPersonById(natural_person_PK);
		
		company_id       = naturalPerson.getNatPerPK().getCompany_id();
		prospectus_id    = naturalPerson.getNatPerPK().getProspectus_id();
		prospecto_RFC    = naturalPerson.getMx_rfc();
		prospecto_CURP   = naturalPerson.getMx_curp();
		prospecto_nombre = naturalPerson.NombreCompletoNPM();
		
		prospecto_nombre = prospecto_nombre.toUpperCase().trim();
		
		membership_PK = new MembershipPK();
		membership_PK.setCompany_id(sesion.getCompany_id());
		membership_PK.setProspectus_id(sesion.getProspectus_id());
		
		prospecto = service_membership.getMembershipById(membership_PK);
	}
}
