package mx.com.kubo.notificaciones.notificador;

import java.util.List;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.notificables.Evento;

public interface NotificadorIMO 
{
	void setEmisor     (Membership member);
	void setAcreditado (Membership member);
	void setLista_archivos_adjuntos( List<String> lista_archivos_adjuntos );
	
	void notificar(Evento evento) throws NotificacionException;
	void notificar(Evento evento, String password)	throws NotificacionException;
	void notificar(Evento evento, MovimientosIMO movimiento) throws NotificacionException;
	
	void notificar(Evento evento, Scoring score, ProyectLoan myPyLn) 				  throws NotificacionException;
	void notificar(Evento evento, Scoring score, ProyectLoan myPyLn, String errormsg) throws NotificacionException;	
}
