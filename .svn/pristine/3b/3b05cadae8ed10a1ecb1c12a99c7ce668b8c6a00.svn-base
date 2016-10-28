package mx.com.kubo.notificaciones.notificador;

import mx.com.kubo.managedbeans.investor.movimientos.MovimientosIMO;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.notificaciones.eventos.NotificableActivacionInversionista;
import mx.com.kubo.notificaciones.eventos.NotificableCambioCuentaCorreo;
import mx.com.kubo.notificaciones.eventos.NotificableCancelacionCuenta;
import mx.com.kubo.notificaciones.eventos.NotificableCancelacionCuentaInversionista;
import mx.com.kubo.notificaciones.eventos.NotificableDepositoEfectivo;
import mx.com.kubo.notificaciones.eventos.NotificableDisposicionEfectivo;
import mx.com.kubo.notificaciones.eventos.NotificableEnvioContratos;
import mx.com.kubo.notificaciones.eventos.NotificableErrorDesarrollo;
import mx.com.kubo.notificaciones.eventos.NotificableGeneracionPasswordActivacion;
import mx.com.kubo.notificaciones.eventos.NotificablePeticionCambioCuentaCorreo;
import mx.com.kubo.notificaciones.eventos.NotificablePeticionCambioPassword;
import mx.com.kubo.notificaciones.eventos.NotificablePreregistroInversionista;
import mx.com.kubo.notificaciones.eventos.NotificablePruebaEnvioCorreo;
import mx.com.kubo.notificaciones.eventos.NotificablePublicacion;
import mx.com.kubo.notificaciones.eventos.NotificableReenvioConfirmacion;
import mx.com.kubo.notificaciones.eventos.NotificableRegistroPartner;
import mx.com.kubo.notificaciones.eventos.NotificableRegistroUsuario;
import mx.com.kubo.notificaciones.eventos.NotificableSolicitudInversionista;
import mx.com.kubo.notificaciones.eventos.NotificableSolicitudInversionistaFinalizada;
import mx.com.kubo.notificaciones.notificables.Evento;

public abstract class NotificadorPMO extends NotificadorDMO
{		
	public void procesarEvento(Evento evento) throws NotificacionException
	{									
		switch(evento)
		{				
			case REGISTRO_USUARIO:
				new NotificableRegistroUsuario(getEmisor()).notificar();				
			break;	
		
			case REGISTRO_SOCIO_PARTNER:
				new NotificableRegistroPartner(getEmisor()).notificar();											
			break;		
			
			case REENVIO_MAIL_CONFIRMACION:
				new NotificableReenvioConfirmacion(getEmisor()).notificar();	
			break;
			
			case CAMBIO_CUENTA_CORREO:
				new NotificableCambioCuentaCorreo(getEmisor()).notificar();			
			break;
			
			case PETICION_CAMBIO_CUENTA_CORREO:
				new NotificablePeticionCambioCuentaCorreo(getEmisor()).notificar();			
			break;
			
			case PETICION_CAMBIO_PASSWORD:
				new NotificablePeticionCambioPassword(getEmisor()).notificar();
			break;
			
			case PREREGISTRO_INVERSIONISTA:				
				new NotificablePreregistroInversionista(getEmisor()).notificar();						
			break;
			
			case SOLICITUD_INVERSIONISTA_FINALIZADA:				
				new NotificableSolicitudInversionistaFinalizada(getEmisor(), getAcreditado()).notificar();				
			break;	
			
			case ENVIO_DE_CONTRATOS:				
				new NotificableEnvioContratos(getEmisor(),getLista_archivos_adjuntos()).notificar();				
			break;	
			
			default: throw new NotificacionException();
		}		
	}
	
	public void procesarEvento(Evento evento, String password) throws NotificacionException
	{
		switch (evento) 
		{
			case GENERACION_PASSWORD_ACTIVACION:
				new NotificableGeneracionPasswordActivacion(getEmisor(), password).notificar();
			break;
			
			default: throw new NotificacionException();
		}
	}
	
	public void procesarEvento(Evento evento,  MovimientosIMO movimiento) throws NotificacionException
	{
		switch(evento) 
		{	
			case DISPOSICION_DE_EFECTIVO:
				new NotificableDisposicionEfectivo(getEmisor(), getAcreditado(), movimiento).notificar();
			break;
			
			case DEPOSITO_DE_EFECTIVO:
				new NotificableDepositoEfectivo(getEmisor(), getAcreditado(), movimiento).notificar();
			break;
			
			case CANCELACION_CUENTA_INVERSIONES:
				new NotificableCancelacionCuenta(getEmisor(), getAcreditado(), movimiento).notificar();
			break;
			
			case CANCELACION_CUENTA_INVERSIONISTA:
				new NotificableCancelacionCuentaInversionista(getEmisor(), getAcreditado(), movimiento).notificar();
			break;
			
			default: throw new NotificacionException();
		}
	}
		
	public void procesarEvento(Evento evento, Scoring score, ProyectLoan myPyLn, String errormsg) throws NotificacionException
	{						
		switch(evento)
		{					
			case PRUEBA_ENVIO_CORREO:
				new NotificablePruebaEnvioCorreo(getEmisor(), score, myPyLn).notificar();
			break;
		
			case ACTIVACION_INVERSIONISTA :
				new NotificableActivacionInversionista(getEmisor(), getAcreditado(), score, myPyLn).notificar();
			break;		
			
			case SOLICITUD_INVERSIONISTA_EXITOSA:
				new NotificableSolicitudInversionista(getEmisor(), score, myPyLn).notificar();								
			break;
			
			case PUBLICACION:
				new NotificablePublicacion(getEmisor(), score, myPyLn).notificar();														
			break;
			
			case ERROR_DESARROLLO:
				new NotificableErrorDesarrollo(getEmisor(), getAcreditado(), score, errormsg).notificar();			
			break;

			
			default: break;
		}		
	}
}
