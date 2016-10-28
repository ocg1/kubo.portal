package mx.com.kubo.refactorizables;

import javax.persistence.EntityManager;

import mx.com.kubo.excepciones.ProspectRiskSetterException;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

public interface ProspectRiskSetterRefactorizable 
{
	public boolean isRequestValido();
	
	public void setEntityManager(EntityManager manager);
	public void procesarEvento() throws ProspectRiskSetterException, NotificacionException;
}
