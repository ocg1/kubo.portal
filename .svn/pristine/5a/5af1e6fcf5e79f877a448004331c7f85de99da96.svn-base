package mx.com.kubo.mesa.autenticacion.buscador;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneRadio;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ServiceSearchRequestIMO;
import mx.com.kubo.tools.Utilities;

public abstract class BuscadorDMO 
implements BuscadorIMO
{		
	protected ClientViewFullNameService service_view_client;
	protected ServiceSearchRequestIMO   service_search_request;
	protected MembershipService         service_membership;
	protected ProyectLoanService        service_proyect_loan;	
	
	protected HtmlSelectOneRadio select_one_radio;
	protected RequestContext  request;
	
	protected SearchSummaySession sesion_search;
	
	protected ProyectLoanPK proyect_loan_PK;
	protected ProyectLoan   proyect_loan;
	protected MembershipPK  membership_PK;	
	protected Membership    membership;
	protected NaturalPerson natural_person;
	protected Prospectus    prospectus;			
	
	protected List<ClientViewFullName> suggestions;
	
	protected StringBuilder sb;
	
	protected String prospectus_TOKEN;
	protected String auto_complete_TOKEN;
	
	protected String[] lista_prospectus_TOKEN;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer filtro_area_SELECTED;
	
	protected Character area;
	
	protected int proyect_id;
	protected int proyect_loan_id;
	
	protected static final int FILTRO_POR_NOMBRE        = 1;
	protected static final int FILTRO_POR_ACREDITADO    = 5;
	protected static final int FILTRO_POR_INVERSIONISTA = 6;
	
	protected boolean prospectus_OK;
	
	protected BuscadorDMO()
	{
		service_view_client    = Utilities.findBean("clientViewFullNameServiceImp");
		service_membership     = Utilities.findBean("membershipServiceImp");
		service_proyect_loan   = Utilities.findBean("proyectLoanServiceImp");		
		service_search_request = Utilities.findBean("service_search_request");
		
		filtro_area_SELECTED = FILTRO_POR_INVERSIONISTA;
	}
	
	public final void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
	
	public final void setSesion_search(SearchSummaySession sesion_search) 
	{
		this.sesion_search = sesion_search;
		
		prospectus_TOKEN = sesion_search.getSearchSummary();
		
		if(prospectus_TOKEN != null)
		{		
			lista_prospectus_TOKEN = prospectus_TOKEN.split("::");
			
			if(lista_prospectus_TOKEN.length > 2)
			{						
				proyect_loan_id = Integer.parseInt(lista_prospectus_TOKEN[0]);
				proyect_id      = Integer.parseInt(lista_prospectus_TOKEN[1]);
				prospectus_id   = Integer.parseInt(lista_prospectus_TOKEN[2]);
				company_id      = Integer.parseInt(lista_prospectus_TOKEN[3]);	
			}						
		}
	}

	public final void setAuto_complete_TOKEN(String auto_complete_TOKEN) 
	{
		this.auto_complete_TOKEN = auto_complete_TOKEN;
		
		System.out.println("BuscadorDMO.setAuto_complete_TOKEN(): " + auto_complete_TOKEN);
		
		prospectus_id = null;
		
		try
		{
			if(auto_complete_TOKEN != null)
			{
				prospectus_id = Integer.parseInt(auto_complete_TOKEN);
			}						
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public final Membership getMembership() 
	{
		return membership;
	}

	public String getAuto_complete_TOKEN() 
	{
		return auto_complete_TOKEN;
	}

	public final boolean isProspectus_OK() 
	{
		return prospectus_OK;
	}
}
