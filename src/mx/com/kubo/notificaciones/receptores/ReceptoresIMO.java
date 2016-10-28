package mx.com.kubo.notificaciones.receptores;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.EventNotification;
import mx.com.kubo.model.Membership;

public interface ReceptoresIMO 
{
	boolean isEnvioPromotorENABLED();
	
	String getPartnerName();
	String getPartnerForwardMail();
		
	String getReceptores_TO();
	String getReceptores_CC();
	String getReceptores_CCO();
	
	SMTPServerDMO getSMTPServer();
	
	List<EventNotification> getLista_receptores();
	
	ArrayList<Membership> getLista_receptores_TO();
	ArrayList<Membership> getLista_receptores_CC();
	ArrayList<Membership> getLista_receptores_CCO();
}
