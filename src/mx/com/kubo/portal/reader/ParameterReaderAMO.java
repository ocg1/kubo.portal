package mx.com.kubo.portal.reader;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.gnNaturalPersonPK;

public abstract class ParameterReaderAMO extends ParameterReaderDMO
{
	protected void read_parameters() 
	{
		company          = parameter.get("company_id");
		prospecto        = parameter.get("prospectus_id");
		prospecto_viewed = parameter.get("prospectus_id_viewed");
		
		phone_id     = parameter.get("phone_id");
		proyect_loan = parameter.get("proyect_loan_id");
		proyect      = parameter.get("proyect_id");
		credit_id    = parameter.get("credit_id");	
		file_type    = parameter.get("file_type_id");
	
		if(company != null)
		{
			company_id = Integer.parseInt(company);
		}
		
		if(prospecto != null)
		{			
			prospectus_id = Integer.parseInt(prospecto);
		}
		
		if(prospecto_viewed != null)
		{
			prospectus_id_viewed = Integer.parseInt(prospecto_viewed);
		}
		
		if(proyect_loan != null)
		{
			proyect_loan_id = Integer.parseInt(proyect_loan);
		}	
		
		if(file_type != null)
		{
			file_type_id = Integer.parseInt(file_type);
		}	
	}

	protected void init_faces_sesion() 
	{
		date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
		
		membership_pk = new MembershipPK(prospectus_id, company_id);
		
		Membership mesa = service_membership.getMembershipById(membership_pk);
		person = mesa.getPerson();
		prospectus = person.getProspectus();			
		
		if(mesa.getLast_login_attempt() != null)
		{
			last_login = date_format.format(mesa.getLast_login_attempt());
			
		} else {
			
			last_login = date_format.format(new Date());
		}									
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		sesion.setIsClient(true);
		sesion.setArea   (prospectus.getArea());
		sesion.setUsrImg (prospectus.getImage());
		sesion.setTrackId(prospectus.getTracking_id());
		sesion.setRole_id(prospectus.getRole_id());
		sesion.setCompany_id(company_id);
		sesion.setProspectus_id(prospectus_id_viewed);
		sesion.setCoachProspectus_id(prospectus_id);
		sesion.setIP_address_client(access_client_IP);		
		sesion.setEmail   (mesa.getEmail());
		sesion.setNickname(mesa.getNickname());
		sesion.setNombre(person.getFirst_name() + " " + person.getFather_last_name());
		sesion.setFecha_acceso_LAST(last_login);
		sesion.setFecha_acceso_ACTUAL(date_format.format(new Date()));
		
		http_sesion.setMaxInactiveInterval(20 * 60);
		
    	sesion.setMaxTimeInactiveSegundos( 20 * 60);
		
	
		person_PK = new gnNaturalPersonPK(prospectus_id_viewed, company_id);
		
		person = service_natural_person.getNaturalPersonById(person_PK);
		
		page_title = prospectus_id_viewed + " :: " + person.NombreCompletoNPM();
	}
}
