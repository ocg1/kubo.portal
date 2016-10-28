package mx.com.kubo.notificaciones.notificador;

import java.util.List;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.NotificableIMO;

public abstract class NotificadorDMO
implements NotificadorIMO
{		
	protected NotificableIMO notificable;
	
	private Membership emisor, acreditado;
	
	private List<String> lista_archivos_adjuntos;
	
	public final void setEmisor(Membership member) 
	{
		emisor = member;
	}
	
	public final void setAcreditado(Membership member)
	{
		acreditado = member;
	}
	
	protected Membership getEmisor() throws NotificacionException 
	{
		if(emisor != null)
		{
			return emisor;
			
		} else {
			
			throw new NotificacionException();
		}			
	}
	
	protected Membership getAcreditado() throws NotificacionException
	{
		if(acreditado != null)
		{
			return acreditado;
			
		} else {
			
			return getEmisor();
		}
	}

	public List<String> getLista_archivos_adjuntos() {
		return lista_archivos_adjuntos;
	}

	public void setLista_archivos_adjuntos(List<String> lista_archivos_adjuntos) {
		this.lista_archivos_adjuntos = lista_archivos_adjuntos;
	}	
}
