package mx.com.kubo.services.fondeo.credito;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.soa.model.businessobject.BurResume;
import com.soa.webServices.WsSgbRiskServiceLocator;

import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import safisrv.ws.CreditosServicios.CreaCreditoRequest;

public abstract class CreacionCreditoAMO extends CreacionCreditoDMO 
{
	protected void init_request() 
	{
		request = new CreaCreditoRequest();
		
		if(deposit_method_id != null && deposit_method_id == TRANSFERENCIA)
		{
			init_mx_clabe();
			
		} else {
			
			request.setCuentaClabe("");
			
			creacion_credito_ENABLED = true;
			
		}

		request.setSolicitudCreditoID(SAFI_solicitud_id + "");
		request.setAjustarFecVen("N");
		
		init_fecha_consulta();
	}
	
	private void init_fecha_consulta() 
	{
		resume = getBuroResume(prospectus_id + "", "0",  solicitud_buro_id);
		
		if(resume != null)
		{					
			fecha_consulta = resume.getFecConsulta();
			folio_consulta = resume.getBurFol();
			
			calendar = new ConvertCalendar(Long.parseLong(fecha_consulta));
			 
			date_consulta = calendar.getDate(); 
			
			date_format = new SimpleDateFormat("yyyy-MM-dd");
			
			fecha_consulta = date_format.format(date_consulta);
									
			request.setFechaConBuro(fecha_consulta);
			request.setFolioConBuro(folio_consulta);
			
		} else {
			
			request.setFechaConBuro("2013-06-19");
			request.setFolioConBuro("09871234578");					
		}
	}

	private void init_mx_clabe() 
	{
		lista_cuentas_bancarias = service_clabe_account.loadClabeAccountListByProspectus(prospectus_id, company_id);
		
		if(lista_cuentas_bancarias != null && lista_cuentas_bancarias.size() > 0)
		{
			cuenta_bancaria = lista_cuentas_bancarias.get(0);
			
			mx_clabe = cuenta_bancaria.getMx_clabe();
				
			if(mx_clabe != null && !mx_clabe.isEmpty() && mx_clabe.length() == 18)
			{
				request.setCuentaClabe(mx_clabe);
				
				creacion_credito_ENABLED = true;
				
			} else {
	
/*				
				creacion_credito_ENABLED = false;
				
				lista_errores.add("Cuenta CLABE diferente de 18 dígitos.");
				
				creacion_credito_OK = false;	
*/				
				request.setCuentaClabe("");
				
				creacion_credito_ENABLED = true;
			}			
						
		} else {		
			
/*			
			creacion_credito_ENABLED = false;
			
			lista_errores.add("Cuenta CLABE no especificada.");
			
			creacion_credito_OK = false;	
*/			
			request.setCuentaClabe("");
			
			creacion_credito_ENABLED = true;
		}
	}

	protected void init_creacion_credito() 
	{
		SAFI_credit_id = null;				
		
		response = service_SAFI.creaCreditoSAFI(request, prospectus_id, company_id);
		
		if(response != null)
		{
			codigo_respuesta  = response.getCodigoRespuesta();
			mensaje_respuesta = response.getMensajeRespuesta();
			SAFI_credit_id    = response.getCreditoID() != null && !response.getCreditoID().equals("0") ? response.getCreditoID() : null;
			
			try{
				
				if( SAFI_credit_id != null ){
					
					notificaHSCreaCredito( );
					
				}
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private boolean notificaHSCreaCredito( ){
		
		if( this.proyect_loan != null ){
		
			NaturalPerson person = this.proyect_loan.getPerson();
			
			if( person != null && person.getProspectus() != null && person.getProspectus().getHs_vid() != null ){	
				
				SystemParamPK sysid = new SystemParamPK(96, person.getNatPerPK().getCompany_id() );
				
				SystemParam sysP = service_system_param.loadSelectedSystemParam(sysid);
				
				String valueSys = sysP.getValue(); // Bandera que indica si esta habilitada la conección con HUBSPOT
				
				if( valueSys != null && valueSys != null && valueSys.equals("S") ){
					
					HubSpotController hs =  new HubSpotController();
					
					String properties = "{ \"property\" : \"estatus_prospecto\" , \"value\" : \"formalizado\"}";
					
					hs.updateProspectus(person.getProspectus().getHs_vid(), properties);
					
				 }
			}
		
		}
		
		return true;
		
	}
	
	protected void init_response() 
	{
		if(response != null && codigo_respuesta.equals(SUCCESS))
		{					
			proyect_loan.setFunding_date(new Date());									
			proyect_loan.setSafi_credit_id(SAFI_credit_id);
			
			service_proyect_loan.update(proyect_loan);
			
			creacion_credito_OK = true;
			
		} else {	
			
			if(response != null)
			{
				lista_errores.add(mensaje_respuesta);
				
			} else { 
				
				lista_errores.add("Error al ejecutar creaCreditoSAFI");
			}			
			
			creacion_credito_OK = false;	
		}
	}	
	
	private BurResume getBuroResume(String cliProId, String indCliPro, String burSol) 
	{
		resume = null;
		
		try 
		{
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();
			
			resume = service.getBurResume(null, null,burSol);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return resume;
	}
}
