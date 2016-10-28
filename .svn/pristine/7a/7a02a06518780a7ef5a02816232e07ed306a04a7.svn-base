package mx.com.kubo.services.mesa.solicitud.busqueda;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;

@Service("service_search_request")
public final class ServiceSearchRequestIMP extends ServiceSearchRequestDMO
implements ServiceSearchRequestIMO
{	
	public final List<ClientViewFullName> getLista_por_filtro(String search_TOKEN, Integer filtro_area, Integer filtro_prospecto) 
	{
		List<ClientViewFullName> suggestions = null;
		
		if( filtro_prospecto != BUSQUEDA_EMAIL ){
		
			switch (filtro_area) 
			{
				case FILTRO_POR_ACREDITADO: 
					suggestions = procesar_filtro_acreditado(search_TOKEN, filtro_prospecto);
				break;
				
				case FILTRO_POR_INVERSIONISTA :
					if( filtro_prospecto != FILTRO_POR_PERSONA_MORAL ){
						
						suggestions = dao_search_request.getLista_inversionistas(search_TOKEN, "I");
						
					}else{
						
						suggestions = dao_search_request.getLista_inver_personas_morales(search_TOKEN, "I");
						
					}
				break;
				
				default: break;
			}
		
		}else{
			
			String area = "";
			
			switch (filtro_area) 
			{
				case FILTRO_POR_ACREDITADO: 
					area = "L";
				break;
				
				case FILTRO_POR_INVERSIONISTA : 				
					area = "I";
				break;
			}
			
			suggestions = dao_search_request.getLista_by_email(search_TOKEN,area);
		}
		
		return suggestions;
	}
	
	public final List<UserViewFullNameForCoach> getLista_prospectus_for_coach(String search_TOKEN, Integer promotor_id)
	{
		return dao_search_request.getLista_prospectus_for_coach(search_TOKEN, promotor_id);
	}
	
	private List<ClientViewFullName> procesar_filtro_acreditado(String search_TOKEN, Integer filtro_prospecto) 
	{
		List<ClientViewFullName> suggestions = null;
		
		switch (filtro_prospecto) 
		{
			case FILTRO_POR_NOMBRE:						
				suggestions = dao_view_full_name.getListClientByName(search_TOKEN);
			break;
			
			case FILTRO_POR_MAIL:				
				suggestions = dao_view_full_name.getListClientByEmail(search_TOKEN);
			break;
			
			case FILTRO_POR_USUARIO:				
				suggestions = dao_view_full_name.getListAllUserByName(search_TOKEN);
			break;
				
			default: break;
		}
		
		return suggestions;
	}
}
