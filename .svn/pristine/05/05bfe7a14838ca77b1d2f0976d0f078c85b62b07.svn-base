package mx.com.kubo.notificaciones.receptores;

import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;

public final class ReceptoresIMP extends ReceptoresAMO
implements ReceptoresIMO
{	
	public ReceptoresIMP(Evento evento, Membership emisor)
	{				
		lista_receptores = getListaReceptoresPorEvento(evento);
		
		if(evento.equals(Evento.REGISTRO_SOCIO_PARTNER))
		{				
			asignarMailToPartner(emisor);
		}
				
		asignarListasReceptoresPorTipoEnvio();
	}	
}
