package mx.com.kubo.notificaciones.mailsender;

import mx.com.kubo.model.Event;
import mx.com.kubo.model.EventPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.conexion.ConexionIMO;
import mx.com.kubo.notificaciones.control.ControlNotificacionIMO;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionEstatusEnvio;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;
import mx.com.kubo.services.EventService;
import mx.com.kubo.tools.Utilities;

public abstract class MailSenderDMO
{	
	protected int intentos_conexion;
	protected final int MAX_INTENTOS_CONEXION = 2;
	
	protected Membership emisor, acreditado;
	protected ReceptoresIMO receptores;
	
	protected NotificacionEstatusEnvio estatus_notificacion;
	protected ControlNotificacionIMO   control;
	protected ConexionIMO              conexion_SMTP;	
	protected NotificacionIMO          notificacion;
	
	private EventService service_notificables;
	private EventPK      notificable_PK;
	private Event        notificable;
	
	protected final Event getEventoFromCatalogo(Evento evento, Membership member) 
	{	
		service_notificables = Utilities.getApplicationContext().getBean("eventServiceImp", EventService.class);
		notificable_PK       = new EventPK(evento.getId(), member.getMembershipPK().getCompany_id());		
		
		notificable = service_notificables.getEventByID(notificable_PK);
		
		return notificable;
	}
}
