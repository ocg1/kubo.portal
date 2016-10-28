package mx.com.kubo.repositories.mesa.solicitud.busqueda;

import java.util.List;

import javax.persistence.EntityManager;

import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.model.mesa.solicitud.busqueda.UserViewFullNameForCoach;

public interface DAOSearchRequestIMO 
{
	void setEntityManager(EntityManager em);
	
	List<ClientViewFullName> getLista_inversionistas(String strName, String area);

	List<UserViewFullNameForCoach> getLista_prospectus_for_coach(String search_TOKEN, Integer promotor_id);
	
	List<ClientViewFullName> getLista_inver_personas_morales(String strName, String area);
	
	List<ClientViewFullName>  getLista_by_email(String search_TOKEN, String Area ) ;
}
