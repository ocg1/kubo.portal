package mx.com.kubo.notificaciones.bitacora;

import java.util.ArrayList;

import mx.com.kubo.model.MailLog;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionTipoEnvio;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public final class BitacoraMailLogIMP extends BitacoraMailLogAMO 
implements BitacoraMailLogIMO
{		
	public BitacoraMailLogIMP(Evento evento, SMTPServerDMO SMTP_server, Membership emisor, Membership receptor, Membership acreditado)
	{
		setSMTPServer(SMTP_server);
		setEvento(evento);
		setSendType(NotificacionTipoEnvio.TO);
		
		setEmisor(emisor);
		setReceptor(receptor);	
		setAcreditado(acreditado);
		
	}
	
	public BitacoraMailLogIMP(Evento evento, SMTPServerDMO SMTP_server, Membership emisor, ReceptoresIMO receptores, Membership acreditado)
	{
		setSMTPServer(SMTP_server);				
		setEvento(evento);									
		
		setEmisor(emisor);
		setAcreditado(acreditado);
		
		setReceptoresTO(receptores.getLista_receptores_TO());
		setReceptoresCC(receptores.getLista_receptores_CC());
		setReceptoresCCO(receptores.getLista_receptores_CCO());
	}
	
	public final void registrar()
	{					
		if(receptores.isEmpty())
		{		
			registrarReceptor();
		} else {
			registrarReceptores();
		}	
	}
	
	private void registrarReceptores()
	{		
		ArrayList<Membership> lista_receptores_TO_CC_CCO;
		
		for(NotificacionTipoEnvio tipo_envio: receptores.keySet())
		{
			setSendType(tipo_envio);
			lista_receptores_TO_CC_CCO = receptores.get(tipo_envio);
			
			for(Membership receptor: lista_receptores_TO_CC_CCO)
			{						
				setReceptor(receptor);				
				registrarReceptor();
			}
		}
	}
	
	private void registrarReceptor()
	{
		model = new MailLog();
		
		asignarEvento();
		asignarEstatus();
		asignarEmisor();
		asignarReceptor();
		asignarAcreditado();
		
		registrarNotificacion();
	}
	
	private void registrarNotificacion()
	{		
		service.add(model);
	}
}
