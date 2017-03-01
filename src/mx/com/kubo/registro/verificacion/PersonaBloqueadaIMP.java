package mx.com.kubo.registro.verificacion;

import mx.com.kubo.model.Clients;

public final class PersonaBloqueadaIMP extends PersonaBloqueadaAMO
implements PersonaBloqueadaIMO
{	
	public void init_busqueda_clientes() 
	{
		lista_clientes = service_clients.getListClients();
		
		for(Clients client : lista_clientes)
		{
			SAFI_client_id = client.getSafi_client_id();
			
			if(SAFI_client_id != null)
			{			
				natural_person = service_natural_person.getPersonBySAFI_client_id(SAFI_client_id);		
			}
			
			setPerson(natural_person);
			
			if(natural_person != null)
			{			
				tipo_operacion = BUSQUEDA_PERSONA_BLOQUEADA;
				
				init();			
				
				if(persist_OK && change_control_OK)
				{
					blocked_person_TOTAL++;
				}
			}
		}
	}
	
	public final void init() 
	{		
		if(mx_rfc != null)
		{
			lista_blocked_person = service_blocked_person.getBlockedPersonByRFC(mx_rfc.substring(0, 9));
		
			blocked_person_ENABLED = lista_blocked_person != null && lista_blocked_person.size() > 0;
		}
		
		if(!blocked_person_ENABLED)
		{
			if(full_name_ENABLED)
			{
				lista_blocked_person = service_blocked_person.getBlockedPersonByFullName(full_name.toUpperCase());
			}
			
			blocked_person_ENABLED = lista_blocked_person != null && lista_blocked_person.size() > 0;
		}
		
		if(blocked_person_ENABLED)
		{								
			person = lista_blocked_person.get(0);	
			
			persist_OK        = false;
			change_control_OK = false;
			
			if(pld_blocked == null || pld_blocked.equals("N"))
			{
				membership.setIs_pld_blocked("S");		
				
				persist_OK = service_membership.update(membership);
				
				init_change_control("N", "S");				
				
				switch(tipo_operacion)
				{
					case OPERACION_PERSONA_BLOQUEADA:
						notificar(PERSONA_BLOQUEADA_OPERACION);
					break;
						
					case REGISTRO_PERSONA_BLOQUEADA:				
						notificar(PERSONA_BLOQUEADA_REGISTRO);
					break;
					
					case BUSQUEDA_PERSONA_BLOQUEADA:
						notificar(PERSONA_BLOQUEADA_COINCIDENCIA);
					break;
				}
			}
			
		} else {						
			
			if(pld_blocked == null || pld_blocked.equals("S"))
			{
				membership.setIs_pld_blocked("N");
				
				persist_OK = service_membership.update(membership);
				
				init_change_control("S", "N");
			}
		}						
	}
}
