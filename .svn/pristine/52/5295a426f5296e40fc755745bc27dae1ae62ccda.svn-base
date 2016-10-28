package mx.com.kubo.notificaciones.mailsender;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.control.ControlNotificacionIMP;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionEstatusEnvio;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;

public abstract class MailSenderPMO extends MailSenderEMO
{				
	public final void send_to_receptor(Evento evento, Membership emisor, Membership receptor, Membership acreditado) throws NotificacionException
	{		 	
		try 
		{									
			control = new ControlNotificacionIMP(evento, conexion_SMTP.getServerSMTP(), emisor, receptor, acreditado);			
			control.registrar(NotificacionEstatusEnvio.ENVIO_EN_PROCESO);
						
			notificacion.setProspectusID(receptor.getMembershipPK().getProspectus_id());
									
			conexion_SMTP.send(notificacion.getMIME_message(), receptor);
			
			estatus_notificacion = NotificacionEstatusEnvio.ENVIO_SATISFACTORIO;
			
			System.out.println("Mail Enviado Satisfactoriamente a:" + receptor.getEmail());
			
		}catch (SendFailedException sfe) {		
			
			asignarEstatusNotificacion(receptor, sfe.getMessage());
			
			throw new NotificacionException(sfe.getMessage());
			
		} catch (MessagingException me) {		
			
			asignarEstatusNotificacion(receptor, me.getMessage());
			
			throw new NotificacionException(me.getMessage());
			
		} catch (Exception e) {		
			
			asignarEstatusNotificacion(receptor, e.getMessage());
			
			throw new NotificacionException(e.getMessage());
			
		} finally {
			
			if(estatus_notificacion == null){
				asignarEstatusNotificacion(receptor, "Error al enviar correo.");
			}
			
			control.registrar(estatus_notificacion);
		}
	}
	
	private void asignarEstatusNotificacion(Membership receptor, String error_msg) 
	{
		System.out.println("Fallo al enviar el mail a:" + receptor.getEmail());
		
		estatus_notificacion = NotificacionEstatusEnvio.ERROR_DE_ENVIO;
		estatus_notificacion.setMensajeConfirmacion(error_msg);
	}

	public void send_to_receptores_por_lista(Evento evento, Membership emisor, ReceptoresIMO receptores, Membership acreditado) throws NotificacionException
	{
		try 
		{										
			control = new ControlNotificacionIMP(evento, conexion_SMTP.getServerSMTP(), emisor, receptores, acreditado);				
			control.registrar(NotificacionEstatusEnvio.ENVIO_EN_PROCESO);		
			
			conexion_SMTP.send(notificacion.getMIME_message(), receptores);				
			
			estatus_notificacion = NotificacionEstatusEnvio.ENVIO_SATISFACTORIO;
			
			System.out.println("Mail Enviado Satisfactoriamente a " + receptores.getReceptores_TO());
			
		} catch (Exception e) {		
			
			System.out.println("Fallo al enviar el mail a:" + receptores.getReceptores_TO());
			
			estatus_notificacion = NotificacionEstatusEnvio.ERROR_DE_ENVIO;
			estatus_notificacion.setMensajeConfirmacion(e.getMessage());
			throw new NotificacionException(e.getMessage());
			
		} finally {
			
			control.registrar(estatus_notificacion);
		}
	}
}
