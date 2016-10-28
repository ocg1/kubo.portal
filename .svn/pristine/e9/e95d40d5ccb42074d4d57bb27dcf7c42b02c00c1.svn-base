package mx.com.kubo.notificaciones.receptores;

import mx.com.kubo.model.EventNotification;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.notificaciones.notificables.Evento;

public abstract class ReceptoresAMO extends ReceptoresDMO
{	
	protected final void asignarListasReceptoresPorTipoEnvio() 
	{		       		
		for(EventNotification receptor: lista_receptores)
		{					
			if(receptor.getMail_to() == null || receptor.getMail_to().toUpperCase().equals("TO"))
			{
				if(receptores_TO.trim().length() > 0)
				{
					receptores_TO += ",";
				}
				
				receptores_TO += receptor.getMembership().getEmail();
				lista_receptores_TO.add(receptor.getMembership());						
			}
						
			if(receptor.getMail_to() != null && receptor.getMail_to().toUpperCase().equals("CC"))
			{
				if(receptores_CC.trim().length() > 0)
				{
					receptores_CC += ",";
				}
				
				receptores_CC += receptor.getMembership().getEmail();
				lista_receptores_CC.add(receptor.getMembership());				
			}
			
			if(receptor.getMail_to() != null && receptor.getMail_to().toUpperCase().equals("CCO"))
			{
				if(receptores_CCO.trim().length() > 0)
				{
					receptores_CCO += ",";
				}
				
				receptores_CCO += receptor.getMembership().getEmail();
				lista_receptores_CCO.add(receptor.getMembership());				
			}					
		}		
	}
	
	protected final void asignarReceptor(Evento evento, Membership receptor) 
	{
		receptor_tmp = new EventNotification();
		receptor_tmp.setMembership(receptor);
		receptor_tmp.setEvent(getEventoFromCatalogo(evento, receptor));
		receptor_tmp.setMail_to("TO");
				
		lista_receptores.add(receptor_tmp);
	}
	
	protected final void asignarMailToPartner(Membership emisor)
	{				
		MembershipPK membership_PK;
		Membership   receptor;
		
		int company_id = emisor.getMembershipPK().getCompany_id();
		
		service_socio_partner.setCompany_id(company_id);
		
		partner_email         = service_socio_partner.getCorreo();
		partner_name          = service_socio_partner.getNombreAvisoAlta();
		partner_forward_email = service_socio_partner.getCorreoAvisoAlta();
		
		if(partner_email != null && partner_email.length() > 0)
		{						
			if(receptores_TO != null && receptores_TO.length() > 0)
			{
				receptores_TO = partner_email + "," + receptores_TO;
			} else {
				receptores_TO = partner_email;
			}
			
			membership_PK = new MembershipPK(0, company_id);
			receptor      = service_membership.getMembershipById(membership_PK);			
			receptor.setEmail(partner_email);
			
			lista_receptores_TO.add(receptor);
		}
	}	
}
