package mx.com.kubo.registro.verificacion;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Stackholder_relationship;

public abstract class ProspectoDuplicadoAMO extends ProspectoDuplicadoDMO
implements ProspectoDuplicadoIMO
{
	public final boolean verificar_duplicidad_accionista() 
	{
		flagRelation = false;
		
		if(sesion.getArea() != null && sesion.getArea() == 'L')
		{										
			lista_accionistas = service_accionistas.getStackholderRelLst();						
			
			if(lista_accionistas != null && lista_accionistas.size() > 0)
			{				
				for(Stackholder_relationship accionista : lista_accionistas)
				{						
					if(accionista.getIs_enabled() == 'S')
					{												
						accionista_RFC    = accionista.getMx_rfc();
						accionista_CURP   = accionista.getMx_curp();
						accionista_nombre = accionista.NombreCompletoNPM().toUpperCase();
						
						if(prospecto_RFC != null && prospecto_RFC.trim().length()>0 && prospecto_RFC.equals(accionista_RFC))
						{							
							
							flagRelation = true;
							
							break;
							
						} else if( prospecto_CURP  != null && prospecto_CURP.trim().length()>0 && prospecto_CURP.equals(accionista_CURP) ) {
														
							flagRelation = true;
							
							break;
							
						} else if(prospecto_RFC != null && prospecto_RFC.length() > 10) {
							
							if(accionista_RFC != null  && accionista_RFC.length() > 10)
							{
								if(prospecto_RFC.substring(0,9).equals(accionista_RFC.substring(0,9)))
								{										
									if(prospecto_nombre.toUpperCase().equals(accionista_nombre)) {
																				
										flagRelation = true;
										
										break;											
									}
								}
							}								
						}
					}						
				}					
			}			
		} 
		
		return flagRelation;
	}
	
	public final boolean verificar_duplicidad_prospecto() 
	{		
		sb = new StringBuilder();
											
		if(prospecto_CURP != null && prospecto_CURP.length() > 10)
		{
			lista_membership = service_membership.getLista_personas_CURP_duplicado(prospectus_id, prospecto_CURP);
				
			for(Membership membership : lista_membership)
			{				
				duplicado = membership.getPerson();
				
				duplicado_company_id    = duplicado.getNatPerPK().getCompany_id();
				duplicado_prospectus_id = duplicado.getNatPerPK().getProspectus_id();
				duplicado_CURP          = duplicado.getMx_curp();
				duplicado_nombre        = duplicado.NombreCompletoNPM().toUpperCase().trim();
				
				duplicado_CURP = duplicado_CURP.substring(0, (duplicado_CURP.length() - 2)).toUpperCase().trim();
				
				prospecto_CURP = prospecto_CURP.substring(0, (prospecto_CURP.length() - 2)).toUpperCase().trim();

				if(prospecto_CURP.equals(duplicado_CURP))
				{					
					sb.append(membership.getEmail());
					sb.append(",");
					sb.append(membership.getPerson().NombreCompletoNPM());
					sb.append("::");
					
					prospecto.setIs_duplicated("S");
					service_membership.update(prospecto);														
				}							
			}				
		}
		
		lista_prospectos_duplicados = sb.toString();
		
		flagSamePros = !lista_prospectos_duplicados.isEmpty();
		
		return flagSamePros;
	}	
	
	public final void registrar_access() 
	{	
		access = new Access();
		access.setCompany_id     (sesion.getCompany_id());
		access.setProspectus_id  (sesion.getProspectus_id());
		access.setOp_system      (sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size  (sesion.getBrowser_height());
		access.setIpaddress      (sesion.getIP_address_client());
		access.setWeb_browser    (sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setVersion_description(sesion.getVersion_description());
		access.setUser_agent     (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		access.setScreen_id(SCREEN_CLIENTE_DUPLICADO);
		access.setPercentage(0);									
		access.setProspectus_id_viewed(null);
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		
		service_access.add(access, false);
	}
}
