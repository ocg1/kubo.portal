package mx.com.kubo.notificaciones.bitacora;

import java.util.ArrayList;
import java.util.HashMap;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionEstatusEnvio;
import mx.com.kubo.notificaciones.notificacion.NotificacionTipoEnvio;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;
import mx.com.kubo.services.MailLogService;
import mx.com.kubo.tools.Utilities;

public abstract class BitacoraMailLogDMO
{		
	private SMTPServerDMO    cuentaSMTP;
	private Evento               evento;	
	private NotificacionEstatusEnvio  estatus;
	private NotificacionTipoEnvio delivery;
	
	private Membership emisor, receptor, acreditado;
	
	protected MailLogService service;	
	protected MailLog        model;

	protected HashMap <NotificacionTipoEnvio, ArrayList<Membership>> receptores;		
	
	protected BitacoraMailLogDMO()
	{
		receptores = new HashMap<NotificacionTipoEnvio, ArrayList<Membership>>(3);
		service    =  (MailLogService) Utilities.getApplicationContext().getBean("mailLogServiceImp");
	}	
		
	protected void setMailService(MailLogService service)
	{
		this.service = service;		
	}
		
	public void setSMTPServer(SMTPServerDMO cuentaSMTP)
	{
		this.cuentaSMTP = cuentaSMTP;
	}
	
	public void setEvento(Evento evento)
	{
		this.evento = evento;
	}	
	
	public void setSendType(NotificacionTipoEnvio delivery)
	{
		this.delivery = delivery;
	}
	
	public void setEmisor(Membership emisor)
	{
		this.emisor = emisor;
	}
	
	public void setReceptor(Membership receptor)
	{
		this.receptor = receptor;		
	}
	
	public final void setReceptoresTO(ArrayList<Membership> receptoresTO)
	{
		receptores.put(NotificacionTipoEnvio.TO, receptoresTO);
	}	
	
	public final void setReceptoresCC(ArrayList<Membership> receptoresCC)
	{
		receptores.put(NotificacionTipoEnvio.CC, receptoresCC);
	}	
	
	public final void setReceptoresCCO(ArrayList<Membership> receptoresCCO)
	{
		receptores.put(NotificacionTipoEnvio.CCO, receptoresCCO);
	}		
	
	public void setAcreditado(Membership acreditado)
	{
		this.acreditado = acreditado;
	}

	public void setEstatus(NotificacionEstatusEnvio estatus)
	{
		this.estatus = estatus;
	}
	
	protected SMTPServerDMO getSMTPServer()
	{
		return cuentaSMTP;		
	}

	protected Evento getEvento()
	{
		return evento;
	}
	
	protected NotificacionTipoEnvio getSendType()
	{
		return delivery;		
	}
	
	protected Membership getEmisor()
	{		
		return emisor;
	
	}
	
	protected Membership getReceptor() 
	{
		return receptor;
	}
	
	protected Membership getAcreditado()
	{
		return acreditado;
	}
	
	protected MailLogService getMailService() 
	{		
		return service;
	}
	
	protected NotificacionEstatusEnvio getEstatus()
	{
		return estatus;
	}	
}
