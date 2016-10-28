package mx.com.kubo.mesa.autenticacion.buscador;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;

public class BuscadorIMP extends BuscadorAMO
implements BuscadorIMO
{
	public final void listener_filtro_area(AjaxBehaviorEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		select_one_radio = (HtmlSelectOneRadio) evento.getComponent();						
		
		filtro_area_SELECTED = Integer.parseInt(select_one_radio.getValue().toString());						
		
		request.addCallbackParam("filtro_area_SELECTED", filtro_area_SELECTED);
	}
	
	public final List<ClientViewFullName> init_auto_complete(String search_TOKEN)
	{
		suggestions = service_search_request.getLista_por_filtro(search_TOKEN, filtro_area_SELECTED, FILTRO_POR_NOMBRE);
		
		return suggestions;
	}
	
	public final void init_busqueda_prospecto(ActionEvent evento)
	{
		request = RequestContext.getCurrentInstance();
		
		init_prospectus();
		init_prospectus_TOKEN();
		
		if(prospectus_TOKEN != null)
		{
			sesion_search.setSearchSummary(prospectus_TOKEN);	
		}
				
		request.addCallbackParam("prospectus_OK", prospectus_OK);
	}
	
	public final void init_prospectus() 
	{
		prospectus_OK = false;
		
		if(prospectus_id != null)
		{
			membership_PK = new  MembershipPK();
			
			membership_PK.setCompany_id    (company_id);
			membership_PK.setProspectus_id (prospectus_id);
			
			membership = service_membership.getMembershipById(membership_PK);						
			
			natural_person = membership.getPerson();									
			
			prospectus = natural_person.getProspectus();
			
			if(prospectus != null)
			{				
				area = prospectus.getArea();
				
				prospectus_OK = true;
			}				
		}
	}
}
