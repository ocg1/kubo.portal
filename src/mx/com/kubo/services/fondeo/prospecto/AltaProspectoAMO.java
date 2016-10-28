package mx.com.kubo.services.fondeo.prospecto;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.TownCatPK;
import safisrv.ws.CreditosServicios.AltaProspectoRequest;

public abstract class AltaProspectoAMO extends AltaProspectoDMO
{	
	protected void init_values()
	{															
		first_name       = acreditado.getFirst_name();
		middle_name      = acreditado.getMiddle_name();
		apellido_materno = acreditado.getMother_last_name();
		apellido_paterno = acreditado.getFather_last_name();	
		RFC   			 = acreditado.getMx_rfc();
		area             = acreditado.getProspectus().getArea();
		prospecto        = acreditado.getProspectus();
		
		//person_type = prospecto.getPerson_type();
		
		person_type = 'F';
		
		address = service_address.getMaxAddressByType(prospectus_id, company_id, 1);
		celular = service_phone.getPhoneByTypeByArea (prospectus_id, company_id, PARTICULAR_CELULAR, area);
		
		calle          = address.getStreet();
		delegacion_id  = address.getTown_id();
		colonia_id     = address.getNeighborhood_id();
		colonia_INFO   = address.getNeighborhood_text();
		codigo_postal  = address.getZip_code();
		state_id       = address.getState_id();
		lote           = address.getMx_lote();
		manzana        = address.getMx_manzana();
		address_number = address.getAddress_number();
		apt_number     = address.getApt_number();
		latitud        = address.getLatitude();
		longitud       = address.getLongitude();						
	}
	
	protected void init_request() 
	{						
		request = new AltaProspectoRequest();
		
		request.setProspectoIDExt (prospectus_id + "");
		request.setTercerNombre("");
		request.setRazonSocial("");
		request.setTipoPersona(person_type + "");				
		request.setPagaIVA("S");
		request.setApellidoMaterno(apellido_materno == null ? "" : apellido_materno);
		request.setApellidoPaterno(apellido_paterno == null ? "" : apellido_paterno);
		request.setCalle          (calle            == null ? "" : calle);
		request.setNumExterior    (address_number   == null ? "" : address_number);
		request.setNumInterior    (apt_number       == null ? "" : apt_number);
		request.setPrimerNombre   (first_name       == null ? "" : first_name);			
		request.setRFC            (RFC              == null ? "" : RFC);
		request.setSegundoNombre  (middle_name      == null ? "" : middle_name);
		request.setLatitud        (latitud          == null ? "" : latitud);
		request.setLongitud       (longitud         == null ? "" : longitud);
		request.setCP             (codigo_postal    == null ? "" : codigo_postal);
		request.setEstadoID      ((state_id         == null ? "" : state_id) + "");
		request.setLote           (lote             == null ? "" : lote);
		request.setManzana       (manzana           == null ? "" : manzana);
		
		if(colonia_id != null)
		{
			colonia_PK = new NeighborhoodCatPK();
			colonia_PK.setCompany_id(company_id);
			colonia_PK.setNeighborhood_id(colonia_id);
			
			colonia = service_neighborhood.getNeighborhoodById(colonia_PK);
			
			request.setColonia(colonia.getName() == null ? "" : colonia.getName());
		
		} else {
			
			request.setColonia(colonia_INFO != null ? colonia_INFO : "" );				
		}
		
		delegacion_PK = new TownCatPK();
		delegacion_PK.setCompany_id (company_id);
		delegacion_PK.setTown_id   (delegacion_id);
		
		delegacion = service_town.getTownById(delegacion_PK);

		if (delegacion != null)
		{
			SAFI_delegacion_id = delegacion.getSafi_town_id(); 
			
			request.setMunicipioID(SAFI_delegacion_id + "");
			
		} else {
			
			request.setMunicipioID("");
		}

		tel = "";
		
		if (celular.getPhone_number() != null)
		{
			tel = celular.getPhone_number().replace("(", "").replace(")", "").replace("-", "");
		}

		request.setTelefono(tel.replaceAll(" ", ""));
	}
	
	protected void init_alta_prospecto() throws ServiceException, RemoteException 
	{								
		service_calling.registrar(INIT, prospectus_id, company_id, SAFI_ALTA_PROSPECTO_INIT);
		
		locator = new safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator();								
		
		service = locator.getSAFIServiciosSoap11();
			
		response = service.altaProspecto(request);
		
		codigo_respuesta  = response.getCodigoRespuesta();
		mensaje_respuesta = response.getMensajeRespuesta(); 
	}
	
	protected void init_response() 
	{	
		if(codigo_respuesta.equals(SUCCESS))
		{																				
			SAFI_prospectus_id = Integer.parseInt(response.getProspectoID());
			
			prospecto = proyect_loan.getPerson().getProspectus();
			
			prospecto.setSafi_prospectus_id(SAFI_prospectus_id);
			
			service_prospectus.update(prospecto);
			
			service_calling.registrar(RESPONSE, prospectus_id, company_id, SAFI_ALTA_PROSPECTO_OK + mensaje_respuesta);
			
			alta_prospecto_OK = true;
			
			System.out.println("SAFIAltaProspectoAMO.init_alta_prospecto_response(SUCCESS): " + SAFI_prospectus_id);
			
		} else {
								
			lista_errores.add(response.getMensajeRespuesta());
			
			service_calling.registrar(ERROR, prospectus_id, company_id, mensaje_respuesta);
			
			alta_prospecto_OK = false;
		}
	}
	
	protected void init_SAFI_prospecto_id() 
	{		
		System.out.println("SAFIAltaProspectoAMO.init_SAFI_prospecto_id(): " + SAFI_prospectus_id);

		prospecto = proyect_loan.getPerson().getProspectus();
		
		prospecto.setSafi_prospectus_id(SAFI_prospectus_id);
		
		service_prospectus.update(prospecto);
		
		alta_prospecto_OK = true;
	}
}
