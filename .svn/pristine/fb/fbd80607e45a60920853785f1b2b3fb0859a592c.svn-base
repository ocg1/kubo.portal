package mx.com.kubo.notificaciones.bitacora;

import java.util.ArrayList;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificacion.NotificacionEstatusEnvio;
import mx.com.kubo.notificaciones.notificacion.NotificacionTipoEnvio;
import mx.com.kubo.notificaciones.receptores.SMTPServerDMO;

public interface BitacoraMailLogIMO 
{	
	void setSMTPServer       (SMTPServerDMO cuentaSMTP);
	
	void setEvento           (Evento evento);	
	void setSendType         (NotificacionTipoEnvio delivery);
	
	void setEmisor           (Membership  emisor);
	void setReceptor         (Membership  receptor);
	void setAcreditado		  (Membership  acreditado);
	
	void setReceptoresTO     (ArrayList<Membership> receptoresTO);
	void setReceptoresCC     (ArrayList<Membership> receptoresCC);
	void setReceptoresCCO    (ArrayList<Membership> receptoresCCO);
	
	void setEstatus          (NotificacionEstatusEnvio estatus);
	
	void registrar();
}
