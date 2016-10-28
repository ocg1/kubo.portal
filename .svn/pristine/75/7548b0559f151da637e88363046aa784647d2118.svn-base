package mx.com.kubo.services.mesa.solicitud.busqueda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import mx.com.kubo.repositories.mesa.solicitud.busqueda.ClientViewFullNameDao;
import mx.com.kubo.repositories.mesa.solicitud.busqueda.DAOSearchRequestIMO;

public abstract class ServiceSearchRequestDMO 
implements ServiceSearchRequestIMO
{
	@Autowired @Qualifier("dao_view_full_name")
	protected ClientViewFullNameDao dao_view_full_name;
	
	@Autowired @Qualifier("dao_search_request")
	protected DAOSearchRequestIMO dao_search_request;
	
	protected static final int FILTRO_POR_NOMBRE        = 1;	
	protected static final int FILTRO_POR_MAIL          = 3;
	protected static final int FILTRO_POR_USUARIO       = 4;
	protected static final int FILTRO_POR_ACREDITADO    = 5;
	protected static final int FILTRO_POR_INVERSIONISTA = 6;
	protected static final int FILTRO_POR_PERSONA_MORAL = 7;
	protected static final int BUSQUEDA_EMAIL = 9;
	
}
