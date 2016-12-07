package mx.com.kubo.rest;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.tools.Utilities;

@Path("/membership")
public class LoginService extends LoginServiceDMO
{		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LoginMembership getLoginInfo(@QueryParam("email") String email, @Context HttpServletRequest request, @Context HttpServletResponse response) 
	{
		service_login = Utilities.findBean("loginServiceImp");
		
		membership = service_login.getMembershipByEmail(email);
		
		init_log_out(request);
		init_faces_context(request, response);
		        
		SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
		
		NaturalPerson person = membership.getPerson();
		
		Prospectus prospectus = person.getProspectus();
		
		login = new LoginMembership();
		login.setArea(prospectus.getArea().toString());
		login.setProspectus_id(membership.getMembershipPK().getProspectus_id() + "");
		login.setFull_name(person.NombreCompletoNPM());		
		login.setLast_login_attempt(date_format.format(membership.getLast_login_attempt()));
		login.setPassword_ENABLED(true);
        
        return login;        
	}
	
	@GET
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginMembership getMembership(@PathParam("email") String email)
	{
		service_login = Utilities.findBean("loginServiceImp");
		
		membership = service_login.getMembershipByEmail(email);
		
		login = new LoginMembership();
		login.setProspectus_id(membership.getMembershipPK().getProspectus_id() + "");
		login.setFull_name(membership.getPerson().NombreCompletoNPM());
        
        return login;  
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginMembership login(LoginRequest request)
	{
		service_login = Utilities.findBean("loginServiceImp");
		
		membership = service_login.getMembershipByEmail(request.getEmail());
		
		boolean password_ENABLED = false;
		
		if(membership != null)
		{		
			password_ENABLED = membership.getPassword().equals(request.getPassword());
			
			if(password_ENABLED)
			{
				SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
				
				NaturalPerson person = membership.getPerson();
				
				Prospectus prospectus = person.getProspectus();
				
				login = new LoginMembership();
				login.setArea(prospectus.getArea().toString());
				login.setFull_name(person.NombreCompletoNPM());
				login.setProspectus_id(membership.getMembershipPK().getProspectus_id() + "");					
				login.setLast_login_attempt(date_format.format(membership.getLast_login_attempt()));				
				login.setPassword_ENABLED(true);				
			} 
		}
		
		System.out.println("LoginService.login() = " + password_ENABLED + " " + request.getPassword());
		
        return login; 
	}
}