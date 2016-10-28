package mx.com.kubo.test.notificaciones;

import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

public abstract class NotificacionesTestPMO extends NotificacionesTestAMO 
{		
	public final void registro_acreditado()
	{
		try 
		{
			if(asignar_registro_acreditado())
			{			
				notificador.notificar(Evento.REGISTRO_USUARIO);
				
				System.out.println("NotificacionesTestPMO.registro_acreditado(): OK");
			}
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void registro_inversionista()
	{
		try 
		{
			if(asignar_registro_acreditado())
			{			
				notificador.notificar(Evento.PREREGISTRO_INVERSIONISTA);
				
				System.out.println("NotificacionesTestPMO.registro_acreditado(): OK");
			}
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	public final void generacion_password_activacion()
	{
		try 
		{
			if(asignar_registro_acreditado())
			{			
				notificador.notificar(Evento.GENERACION_PASSWORD_ACTIVACION, "password_generated_TEST");
				
				System.out.println("NotificacionesTestPMO.generacion_password_activacion(): OK");
			}
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}
	
	/*
	public final void credito_rechazado()
	{
		try 
		{								
			asignar_credito_rechazado();
			
			notificador.notificar(Evento.CREDITO_RECHAZO, proyect_loan.getScoring(), proyect_loan, null);		
	    
			System.out.println("NotificacionesTestPMO.credito_rechazado(): OK");
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}
	}	
	*/
}
