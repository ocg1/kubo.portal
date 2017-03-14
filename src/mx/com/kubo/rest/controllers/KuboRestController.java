package mx.com.kubo.rest.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.InvestorSession;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.rest.model.ResumenSaldos;
import mx.com.kubo.rest.saldos.ProyectLoanActiveIMO;
import mx.com.kubo.rest.saldos.ProyectLoanActiveIMP;
import mx.com.kubo.rest.saldos.ResumenSaldosIMO;
import mx.com.kubo.rest.saldos.ResumenSaldosIMP;
import mx.com.kubo.rest.tienda.InvestmentListIMO;
import mx.com.kubo.rest.tienda.InvestmentListIMP;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

@Path("/KuboREST")
public class KuboRestController 
{
	private ProyectLoanService service_proyect_loan;
	private NaturalPersonService personaNaturalService;
	
	@GET
	@Path("{saldos}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResumenSaldos getResumenSaldos(@QueryParam("prospectus_id") String prospectus_id, @QueryParam("company_id") String company_id)
	{
		ResumenSaldosIMO saldos;
		ResumenSaldos response;
		
		gnNaturalPersonPK key;
		NaturalPerson persona;
		
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		personaNaturalService = Utilities.findBean("naturalPersonServiceImp");
		
		key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
		
		persona = personaNaturalService.getNaturalPersonById(key);
		
		saldos = new ResumenSaldosIMP();
		saldos.setServicioProyecto(service_proyect_loan);
		saldos.setPerson(persona);		
		saldos.init();
		saldos.init_resumen_saldos();
		
		response = saldos.getResumenSaldos();
		
		return response;
	}
	
	@GET
	@Path("saldos/{getProjectActive}")
	@Produces("application/json;charset=utf-8")
	public Response getProjectActive(@QueryParam("cuenta") String cuenta, @QueryParam("status") String status)
	{
		//String status = (String) e.getComponent().getAttributes().get("status").toString();
		//String cuenta = getStrCuentas();
		
		ProyectLoanActiveIMO active;
		Response responseJSON;
	
		char status_delinquentinv = 'C';
		
		service_proyect_loan = Utilities.findBean("proyectLoanServiceImp");
		
		active = new ProyectLoanActiveIMP();
		active.setService_proyect_loan(service_proyect_loan);
		active.init(cuenta, status, status_delinquentinv);
		
		responseJSON = active.getResponseJSON();
				
		return responseJSON;
	}
	
	@GET
	@Path("tienda")
	@Produces("application/json;charset=utf-8")
	public Response getProjectList(@QueryParam("prospectus_id") String prospectus_id, @QueryParam("company_id") String company_id)
	{		 
		SessionBean sesion = new SessionBean();
		sesion.setCompany_id   (Integer.parseInt(company_id));
		sesion.setProspectus_id(Integer.parseInt(prospectus_id));
		
		InvestorSession sesion_investor = new InvestorSession();
		sesion_investor.init();
		
		InvestmentListIMO store = new InvestmentListIMP();
		store.setSesion(sesion);
		store.setSesion_investor(sesion_investor);
		store.init();
		
		Response responseJSON = store.getResponseJSON();
				
		return responseJSON;
	}
}
