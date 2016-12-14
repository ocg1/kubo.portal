package mx.com.kubo.services.fondeo.credito;

import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SolicitudCreditoRequest;

public abstract class SolicitudCreditoAMO extends SolicitudCreditoDMO
{
	protected void init_values() 
	{
		kubo_score_A       = proyect_loan.getKubo_score_a();
		kubo_score_B       = proyect_loan.getKubo_score_b();
		monto_solicitado   = proyect_loan.getAmmount();
		tasa_activa        = proyect_loan.getRate();
		pago_inicial       = proyect_loan.getOpening_payment();
		frequency_id       = proyect_loan.getFrequency_id();
		plazo              = proyect_loan.getTerm_id();		
		tipo_credito       = proyect_loan.getProyect().getType_id();
		tipo_deposito      = proyect_loan.getDeposit_method();
		
		fecha_registro = date_format.format(new Date());
	}
	
	protected void init_request() 
	{				
		request = new SolicitudCreditoRequest();										
		
		if(SAFI_client_id != null && SAFI_client_id.length() > 0)
		{
			request.setClienteID(SAFI_client_id);
			request.setProspectoID("");
			
		} else {
			
			request.setClienteID("");
			request.setProspectoID(SAFI_prospectus_id + "");
		}
			
			request.setProductoCreditoID(tipo_credito + "");
			request.setCalificacion(kubo_score_A + kubo_score_B);
			request.setCuentaClabe("");									
			request.setFechaRegistro(fecha_registro);
			request.setMontoSolici(monto_solicitado + "");
			request.setPlazo(plazo + "");
							
			if(pago_inicial == null || pago_inicial.equals("D"))
			{
				request.setForCobComApert("D");
				
				monto_comision = getMontoComision( proyect_loan ); 
				
				request.setMontoComApert(monto_comision);
				request.setTasaActiva(tasa_activa + "");
				
			} else if(pago_inicial.equals("T")){
	//			sol.setForCobComApert("D");
	//			montoComision = "0";
	//			sol.setMontoComApert("0");
	//			String newRate = generateRateDif(score.getRate());
	//			sol.setTasaActiva(newRate);
			}
			
			switch (frequency_id)
			{
				case 1:
					periodicidad = "S";
				break;
				
				case 2:
					periodicidad = "C";
				break;
				
				case 3:
					periodicidad = "Q";
				break;
				
				case 4:
					periodicidad = "M";
				break;
				
				default:
					periodicidad = "";
				break;
			}
		
			request.setPeriodicidad(periodicidad);

			//sol.setTasaActiva(score.getRate()+"");
								
			if(tipo_deposito != null)
			{				
				request.setTipoDispersion(tipo_deposito.getSafi_code());
				
			} else {
				
				lista_cuentas_bancarias = service_clabe_account.loadClabeAccountListByProspectus(prospectus_id, company_id);
				
				if(lista_cuentas_bancarias != null && lista_cuentas_bancarias.size() > 0)
				{					
					request.setTipoDispersion("S");
					
				} else {
					
					request.setTipoDispersion("C");					
				}
			}
	}
	
	protected void init_solicitud_credito() throws ServiceException, RemoteException 
	{		
		service_calling.registrar(INIT, prospectus_id, company_id, SAFI_SOLICITUD_CREDITO_INIT);
			
		locator = new SAFIServiciosServiceLocator();
		
		service_SAFI = locator.getSAFIServiciosSoap11();
	
		response = service_SAFI.solicitudCredito(request);
		
		if(response != null)
		{
			codigo_respuesta  = response.getCodigoRespuesta();		
			mensaje_respuesta = response.getMensajeRespuesta();
		}
	}
	
	protected void init_response() 
	{
		if(response != null)
		{
			if(codigo_respuesta.equals(SUCCESS) || codigo_respuesta.equals(SUCCESS_PLD) || codigo_respuesta.equals(SUCCESS_PLD_2) )
			{									
				service_calling.registrar(RESPONSE, prospectus_id, company_id, SAFI_SOLICITUD_CREDITO_OK + mensaje_respuesta);						
				
				SAFI_solicitud_id = Integer.parseInt(response.getSolicitudCreditoID());
				
				proyect_loan.setSafi_mx_solicitud_id(SAFI_solicitud_id);
				
				service_proyect_loan.update(proyect_loan);
				
				solicitud_credito_OK = true;
				
				System.out.println("SAFISolicitudCreditoAMO.init_solicitud_credito():" + SAFI_solicitud_id);
			
			
			} else {
							
				service_calling.registrar(ERROR, prospectus_id, company_id, mensaje_respuesta);
				
				lista_errores.add(mensaje_respuesta);
				
				solicitud_credito_OK = false;
			}
		}
	}
	
	protected void init_SAFI_solicitud_id() 
	{
		System.out.println("SAFISolicitudCreditoAMO.init_SAFI_solicitud_id: " + SAFI_solicitud_id);
		
		solicitud_credito_OK = true;
	}
}
