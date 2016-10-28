package mx.com.kubo.notificaciones.notificables;

import mx.com.kubo.model.Membership;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.notificaciones.conexion.ConexionIMP;
import mx.com.kubo.notificaciones.mailsender.MailSenderIMO;
import mx.com.kubo.notificaciones.notificacion.NotificacionIMO;
import mx.com.kubo.notificaciones.receptores.ReceptoresIMO;

public abstract class NotificableDMO
{	
	protected boolean promotor_enabled;
	
	protected Membership emisor, acreditado;
	
	protected NotificacionIMO  notificacion;
	protected ReceptoresIMO    receptores;
	protected MailSenderIMO    mail_sender;	
	protected ConexionIMP      conexion_SMTP;	

	protected final boolean tienePromotor(Membership acreditado)
	{
		return (acreditado.getPromotor() != null && acreditado.getPromotor().getMembership() != null);
	}
	
	protected final String getPlazo(ProyectLoan pyln)
	{
		String  plazo = pyln.getTerm_id() + "";
		int      freq = pyln.getFrequency_id();
		
		switch (freq)
		{
			case 1://Semanal
				plazo += " Semanas";
			break;
			
			case 2: //Catorcenal
				plazo += " Catorcenas";
			break;
			
			case 3: //Quincenal
				plazo += " Quincenas";
			break;
				
			case 4: //Mensual
				plazo += " Meses";
			break;
		}
		
		return plazo;		
	}
}
