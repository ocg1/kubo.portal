package mx.com.kubo.mesa.autenticacion.fabrica;

import java.util.List;
import java.util.Random;

import mx.com.kubo.mesa.autenticacion.Autenticacion;
import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.model.AuthenticationPool;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.tools.Utilities;

public abstract class AutenticacionFactoryDMO 
implements AutenticacionFactoryIMO
{
	protected ServiceCatalogosIMO service_catalogos;
	
	protected Membership membership;
	protected NaturalPerson natural_person;
	
	protected Autenticacion autenticacion_id;
	protected AutenticacionIMO autenticacion;	
	protected AuthenticationPool authentication;
	
	protected List<AutenticacionIMO> lista_preguntas;
	protected List<AuthenticationPool> pool_autenticacion;	
	
	protected Random generator;
	
	protected int random;		
	protected int authentication_id;
	
	protected int[] random_list = {-1, -1, -1};
	
	protected final int MAX_NUMBER_QUESTIONS = 3;
	
	protected boolean random_ENABLED;
			
	protected AutenticacionFactoryDMO()
	{
		service_catalogos = Utilities.findBean("service_catalogos");				

		generator = new Random();				
	}

	public final void setMembership(Membership membership) 
	{
		this.membership = membership;
		
		natural_person = membership.getPerson();
	}

	public final List<AutenticacionIMO> getLista_preguntas() 
	{
		return lista_preguntas;
	}
}
