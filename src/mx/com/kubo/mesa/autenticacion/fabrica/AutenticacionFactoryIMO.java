package mx.com.kubo.mesa.autenticacion.fabrica;

import java.util.List;

import mx.com.kubo.mesa.autenticacion.AutenticacionIMO;
import mx.com.kubo.model.Membership;

public interface AutenticacionFactoryIMO 
{
	void init();
	
	void setMembership(Membership membership);
	
	List<AutenticacionIMO> getLista_preguntas();
}
