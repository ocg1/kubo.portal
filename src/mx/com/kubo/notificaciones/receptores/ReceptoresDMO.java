package mx.com.kubo.notificaciones.receptores;

import java.util.ArrayList;
import java.util.List;

import mx.com.kubo.model.Event;
import mx.com.kubo.model.EventNotification;
import mx.com.kubo.model.EventPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.tools.Utilities;

public abstract class ReceptoresDMO 
{
	protected boolean envioPromotorENABLED;
	
	private int account_code_id;
			
	protected String 
		account_code,
		partner_email,
		partner_name,
		partner_forward_email,
		receptores_TO, 
		receptores_CC, 
		receptores_CCO;
	
	protected EventNotification receptor_tmp;
	
	protected ArrayList<Membership> 
		lista_receptores_TO, 
		lista_receptores_CC, 
		lista_receptores_CCO;
	
	protected List<EventNotification> lista_receptores;
	
	protected SocioParterIMP            service_socio_partner;
	protected EventNotificationService  service_event_NTF;	
	protected EventService              service_event;	
	protected MembershipService		 service_membership;
	
	private EventPK event_PK;
	private Event   gn_evento;
	
	protected ReceptoresDMO()
	{			
		envioPromotorENABLED = false;	
		
		receptores_TO  = "";
		receptores_CC  = "";
		receptores_CCO = "";
		
        lista_receptores_TO  = new ArrayList<Membership>();
        lista_receptores_CC  = new ArrayList<Membership>();
        lista_receptores_CCO = new ArrayList<Membership>();
				
		service_socio_partner = Utilities.getApplicationContext().getBean("socioParterImp", SocioParterIMP.class);				
		service_membership    = Utilities.getApplicationContext().getBean("membershipServiceImp", MembershipService.class);
		service_event         = Utilities.getApplicationContext().getBean("eventServiceImp", EventService.class);
		service_event_NTF     = Utilities.getApplicationContext().getBean("eventNotificationServiceImp", EventNotificationService.class);
	}	
	
	protected final List<EventNotification> getListaReceptoresPorEvento(Evento evento)
	{				
		return service_event_NTF.loadListProspectusByEvent(evento.getId());											
	}
		
	protected final Event getEventoFromCatalogo(Evento evento, Membership member) 
	{		
		event_PK = new EventPK(evento.getId(), member.getMembershipPK().getCompany_id());	
		
		gn_evento = service_event.getEventByID(event_PK);
		
		return gn_evento;
	}	
	
	public final SMTPServerDMO getSMTPServer()
	{		
		if( lista_receptores != null && lista_receptores.size() > 0 ){
			account_code    = lista_receptores.get(0).getEvent().getAccountType().getAccount_code();
			account_code_id = lista_receptores.get(0).getEvent().getAccountType().getAccountTypePK().getAccount_type_id();
			
			return new SMTPServerDMO(account_code, account_code_id);	
		}else{
			return null;
		}
	}
	
	public final String getPartnerName()
	{
		return partner_name;
	}
	
	public final String getPartnerForwardMail()
	{
		return partner_forward_email;
	}
	
	public final boolean isEnvioPromotorENABLED() 
	{
		envioPromotorENABLED = lista_receptores.size() > 0 && (lista_receptores.get(0).getEvent().getIs_send_to_promotor() + "").equals("S");

		return envioPromotorENABLED;
	}
						
	public final String getReceptores_TO() 
	{
		return receptores_TO;
	}

	public final String getReceptores_CC() 
	{
		return receptores_CC;
	}

	public final String getReceptores_CCO() 
	{
		return receptores_CCO;
	}
	
	public final List<EventNotification> getLista_receptores()
	{
		return lista_receptores;
	}

	public final ArrayList<Membership> getLista_receptores_TO() 
	{
		return lista_receptores_TO;
	}

	public final ArrayList<Membership> getLista_receptores_CC() 
	{
		return lista_receptores_CC;
	}

	public final ArrayList<Membership> getLista_receptores_CCO()
	{
		return lista_receptores_CCO;
	}
}
