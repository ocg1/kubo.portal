package mx.com.kubo.notificaciones.receptores;

import java.util.ArrayList;

import mx.com.kubo.model.EventNotification;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;

public class ReceptorIMP extends ReceptoresAMO
implements ReceptoresIMO 
{
	public ReceptorIMP(Evento evento, Membership receptor)
	{		
		lista_receptores = new ArrayList<EventNotification>();
		
		asignarReceptor(evento, receptor);
		asignarListasReceptoresPorTipoEnvio();
	}
}
