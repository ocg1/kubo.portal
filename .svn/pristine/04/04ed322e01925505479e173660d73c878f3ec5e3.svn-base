package mx.com.kubo.notificaciones.notificador;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.notificables.Evento;

import org.springframework.stereotype.Service;

@Service(value = "notificadorImp")
public final class NotificadorIMP extends NotificadorPMO 
implements NotificadorIMO
{		
	public final void notificar(Evento evento) throws NotificacionException
	{		
		try
		{					
			procesarEvento(evento);	
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			throw new NotificacionException(e.getMessage());
		} 
	}
	
	public final void notificar(Evento evento, String password) throws NotificacionException
	{		
		try
		{					
			procesarEvento(evento, password);	
			
		} catch(NullPointerException e) {
			
			e.printStackTrace();
			
			throw new NotificacionException(e.getMessage());
		} 
	}
	
	public final void notificar(Evento evento, MovimientosIMO movimiento) throws NotificacionException
	{		
		try
		{					
			procesarEvento(evento, movimiento);	
			
		} catch(NullPointerException e) {
			
			e.printStackTrace();
			
			throw new NotificacionException(e.getMessage());
		} 
	}
	
	public final void notificar(Evento evento, Scoring score, ProyectLoan myPyLn) throws NotificacionException
	{		
		try
		{					
			procesarEvento(evento, score, myPyLn, "");	
			
		} catch(NullPointerException e) {
			throw new NotificacionException(e.getMessage());
		} 
	}
	
	public final void notificar(Evento evento, Scoring score, ProyectLoan myPyLn, String errormsg) throws NotificacionException
	{
		try
		{					
			procesarEvento(evento, score, myPyLn, errormsg);
			
		} catch(NullPointerException e) {
			throw new NotificacionException(e.getMessage());
		} 
	}	
}
