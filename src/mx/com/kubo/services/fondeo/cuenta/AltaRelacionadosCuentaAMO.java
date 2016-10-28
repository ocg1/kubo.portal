package mx.com.kubo.services.fondeo.cuenta;

import mx.com.kubo.constantes.ActividadEconomica;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.AddressTokenIMP;
import safisrv.ws.CuentasServicios.AltaRelacionadoCtaRequest;

public abstract class AltaRelacionadosCuentaAMO extends AltaRelacionadosCuentaDMO
{
	protected void init_antiguedad() 
	{					
		 if(antiguedad_vivienda == null)
		 {
			 antiguedad = "";
			 
		 } else if (antiguedad_vivienda == 1) {
			 
			 antiguedad = antiguedad_vivienda + " año ";
				 
		 } else {
			
			 antiguedad = antiguedad_vivienda + " años ";
		 }
	}
	
	protected void init_address_TOKEN()
	{
		if(tipo_vivienda_id != null)
		{
			tipo_vivienda_PK = new ResidencePK(tipo_vivienda_id, company_id);
			
			tipo_vivienda = service_residencia.getResidenceById(tipo_vivienda_PK);
		}
		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, 1);
		
		token = new AddressTokenIMP();
		token.setResidence(tipo_vivienda);
		token.setAntiguedad(antiguedad_vivienda);
		token.setAddress(address);
		
		address_TOKEN = token.getAddress_TOKEN();			
	}
	
	protected final void init_saving_account() 
	{	
		lista_saving_account = service_saving_account.getListAccountByProspect(prospectus_id, company_id);
		
		int ultimo = lista_saving_account.size() - 1;
		
		if(ultimo > -1)
		{
			saving_account = lista_saving_account.get(ultimo);
		}
		
		if(saving_account != null)
		{
			SAFI_related_person = saving_account.getHas_related_person();
			SAFI_account_id     = saving_account.getSafi_account_id();
		}
	}
	
	protected  void init_request() 
	{		
		request = new AltaRelacionadoCtaRequest();
		
		request.setClienteID  (SAFI_client_id);
		request.setCuentaAhoID(SAFI_account_id); 
		
		request.setDomicilio(address_TOKEN.length() > 200 ? address_TOKEN.substring(0, 199) : address_TOKEN);	
						
		request.setPrimerNombre   (acreditado.getFirst_name());
		request.setSegundoNombre  (acreditado.getMiddle_name());
		request.setApellidoPaterno(acreditado.getFather_last_name());
		request.setApellidoMaterno(acreditado.getMother_last_name());	
		request.setNumIdentific   (acreditado_ID != null ? acreditado_ID : "");
		
		actividad_economica_id = acreditado.getEconomic_activity_id();
		
		actividad = ActividadEconomica.getInstance(actividad_economica_id);
		
		switch(actividad)
		{
			case COMERCIANTE:
			case EMPLEADO_Y_COMERCIANTE:
			case COMERCIANTE_Y_EMPLEADO:
			case EMPRESARIO_SOCIO:
				ocupacion_id = SAFI_EMPLEADO;
			break;
			
			case EMPLEADO:
			case JUBILADO:
			case DEPENDIENTE:
				ocupacion_id = SAFI_COMERCIANTE;
			break;
		}
		
		request.setOcupacionID((     view_client_info.getOcupacionID()        == null || view_client_info.getOcupacionID().equals(" "))        ? ocupacion_id + "" : (view_client_info.getOcupacionID() + ""));
		request.setRFC            ( (view_client_info.getRFC()                == null || view_client_info.getRFC().equals(" "))                ? "-" :  view_client_info.getRFC());		
		request.setCURP            ((view_client_info.getCurp()               == null || view_client_info.getCurp().equals(" "))               ? "-" :  view_client_info.getCurp());
		request.setSexo            ((view_client_info.getSexo()               == null || view_client_info.getSexo().equals(" "))               ? "-" :  view_client_info.getSexo());		
		request.setEstadoCivil(     (view_client_info.getEstado_civil()       == null || view_client_info.getEstado_civil().equals(" "))       ? "-" :  view_client_info.getEstado_civil());
		request.setActividadBancoMX((view_client_info.getActividadBancoMX()   == null || view_client_info.getActividadBancoMX().equals(" "))   ? "-" :  view_client_info.getActividadBancoMX());	
		request.setActividadINEGI((  view_client_info.getActividadINEGI()     == null || view_client_info.getActividadINEGI().equals(" "))     ? "-" : (view_client_info.getActividadINEGI() + ""));		
		request.setCorreo((          view_client_info.getCorreo()             == null || view_client_info.getCorreo().equals(" "))             ? "-" :  view_client_info.getCorreo());
		request.setNacion(          (view_client_info.getNacion()             == null || view_client_info.getNacion().equals(" "))             ? "-" :  view_client_info.getNacion());
		request.setPaisResidencia(  (view_client_info.getPais_de_residencia() == null || view_client_info.getPais_de_residencia().equals(" ")) ? "-" : (view_client_info.getPais_de_residencia()+""));
		request.setPaisNacimiento(  (view_client_info.getLugar_nacimiento()   == null || view_client_info.getLugar_nacimiento().equals(" "))   ? "-" : (view_client_info.getPais_de_residencia()+""));						
		
		if(view_client_info.getFecha_nacimiento() != null)
		{
			fecha_nacimiento = date_format.format(view_client_info.getFecha_nacimiento());
		}
				
		request.setFechaNacimiento((fecha_nacimiento == null || fecha_nacimiento.equals("")) ? "-" : fecha_nacimiento);
		request.setTitulo(          (view_client_info.getTitulo()             == null || view_client_info.getTitulo().equals(" "))             ? "-" :  view_client_info.getTitulo());
		request.setTelefonoCasa(    (view_client_info.getTelefono_casa()      == null || view_client_info.getTelefono_casa().equals(" "))      ? "-" :  view_client_info.getTelefono_casa().replaceAll(" ", ""));
		request.setTelefonoCelular(( view_client_info.getTelefono_celular()   == null || view_client_info.getTelefono_celular().equals(" "))   ? "-" :  view_client_info.getTelefono_celular().replaceAll(" ", ""));
		request.setFax(             (view_client_info.getFax()                == null || view_client_info.getFax().equals(" "))                ? "-" :  view_client_info.getFax());
		request.setEstadoCivil(     (view_client_info.getEstado_civil()       == null || view_client_info.getEstado_civil().equals(" "))       ? "-" :  view_client_info.getEstado_civil());
		request.setPuestoA(         (view_client_info.getPuesto()             == null || view_client_info.getPuesto().equals(" "))             ? "-" :  view_client_info.getPuesto());
		request.setSectorGeneral(   (view_client_info.getSector_general()     == null || view_client_info.getSector_general().equals(" "))     ? "-" : (view_client_info.getSector_general()+""));
		request.setSectorEconomico( (view_client_info.getSectorEconomico()    == null || view_client_info.getSectorEconomico().equals(" "))    ? "-" : (view_client_info.getSectorEconomico()+""));
		request.setEstadoID(        (view_client_info.getEstadoID()           == null || view_client_info.getEstadoID().equals(" "))           ? "-" : view_client_info.getEstadoID()+"");		
		
		request.setEsApoderado   ("N");		
		request.setEsCotitular   ("N");
		request.setEsBeneficiario("N");
		request.setEsTitular     ("S");
		request.setEsProvRecurso ("S");
		request.setEsPropReal    ("S");
		request.setEsFirmante    ("S");	
		
		request.setTipoIdentiID (SAFI_ELECTOR + "");

		request.setTitularNotaria  ("");
		request.setNumEscPub       ("");
		request.setFechaEscPub     ("");		
		request.setFecExIden       ("");
		request.setFecVenIden      ("");
		request.setFechaVenEst     ("");
		request.setTercerNombre    ("");		
		request.setPorcentaje      ("");
		request.setRazonSocial     ("");
		request.setOtraIdentifi    ("");
		request.setParentescoID    ("");
		request.setDocEstanciaLegal("");
		request.setDocExisLegal    ("");
		request.setNotariaID       ("");
		request.setMunicipioID     ("");										  								
	}
	
	protected void init_alta_relacionado()
	{
		response = service_relacionados_cuenta.init(request, prospectus_id, company_id);
		
		if(response != null)
		{
			codigo_respuesta    = response.getCodigoRespuesta();
			mensaje_respuesta   = response.getMensajeRespuesta();
			SAFI_related_person = response.getPersonaID();
			
			if(codigo_respuesta.equals(SUCCESS))
			{				 				
				saving_account.setHas_related_person("S");
				
				service_saving_account.updateSavingAccount(saving_account);
				
				System.out.println("AltaRelacionadosCuentaAMO.init_alta_relacionado(): "  + SAFI_related_person);
				
			} else {				
				
				lista_errores.add(mensaje_respuesta);
			}	
		}
	}
}
