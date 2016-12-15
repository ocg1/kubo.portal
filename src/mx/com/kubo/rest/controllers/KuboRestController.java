package mx.com.kubo.rest.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.rest.ResumenSaldos;
import mx.com.kubo.rest.saldos.ResumenSaldosIMO;
import mx.com.kubo.rest.saldos.ResumenSaldosIMP;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.tools.Utilities;

@Path("/KuboREST")
public class KuboRestController 
{
	protected ProyectLoanService servicioProyecto;
	protected NaturalPersonService personaNaturalService;
	
	@GET
	@Path("{saldos}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResumenSaldos getResumenSaldos(@QueryParam("prospectus_id") String prospectus_id, @QueryParam("company_id") String company_id)
	{
		ResumenSaldosIMO saldos;
		ResumenSaldos response;
		
		gnNaturalPersonPK key;
		NaturalPerson persona;
		
		servicioProyecto = Utilities.findBean("proyectLoanServiceImp");
		
		personaNaturalService = Utilities.findBean("naturalPersonServiceImp");
		
		key = new gnNaturalPersonPK( Integer.parseInt( prospectus_id ) , Integer.parseInt( company_id ) );
		
		persona = personaNaturalService.getNaturalPersonById(key);
		
		saldos = new ResumenSaldosIMP();
		saldos.setServicioProyecto(servicioProyecto);
		saldos.setPerson(persona);		
		saldos.init();
		saldos.init_resumen_saldos();
		
		response = saldos.getResumenSaldos();
		
		return response;
	}
}
