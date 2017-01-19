package mx.com.kubo.managedbeans;

import mx.com.kubo.controller.behaviorProspectus.BehaviorCheck;
import mx.com.kubo.model.FraudeDetection;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.fondeo.cliente.AltaClienteIMP;
import mx.com.kubo.services.fondeo.cliente.AltaConocimientoClienteIMP;
import mx.com.kubo.services.fondeo.cliente.CreacionClienteIMP;
import mx.com.kubo.services.fondeo.credito.CreacionCreditoIMP;
import mx.com.kubo.services.fondeo.credito.SolicitudCreditoIMP;
import mx.com.kubo.services.fondeo.cuenta.AltaConocimientoCuentaIMP;
import mx.com.kubo.services.fondeo.cuenta.AltaRelacionadosCuentaIMP;
import mx.com.kubo.services.fondeo.cuenta.AutorizarCuentaIMP;
import mx.com.kubo.services.fondeo.cuenta.CreacionCuentaIMP;
import mx.com.kubo.services.fondeo.prospecto.AltaProspectoIMP;
import mx.com.kubo.services.fondeo.seguro.CreacionSeguroVidaIMP;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

public abstract class CreaCreditoServiceAMO extends CreaCreditoServiceDMO
{		
	protected void init_param_values() 
	{		
		prospStr    = (String) attributes.get("prospectus_id").toString();
		ciaStr      = (String) attributes.get("company_id").toString();
		proyecStr   = (String) attributes.get("proyect_id").toString();
		proyloanStr = (String) attributes.get("proyect_loan_id").toString();									
		
		if(prospStr != null)
		{
			prospectus_id = Integer.parseInt(prospStr);
		}
		
		if(ciaStr != null)
		{
			company_id = Integer.parseInt(ciaStr);
		}
		
		if(proyecStr != null)
		{
			proyect_id = Integer.parseInt(proyecStr);
		}
		
		if(proyloanStr != null)
		{
			proyect_loan_id = Integer.parseInt(proyloanStr);
		}
		
		param_values_OK = prospectus_id != 0 && company_id != 0 && proyect_id != 0 && proyect_loan_id != 0;								
	}
	
	protected void validaInusualBehavior(String ipaddress){
		
		is_inussual_person = false;
		
		SystemParamPK spkH = new SystemParamPK();
		SystemParam param = null;
		
		spkH = new SystemParamPK();
		
		spkH.setCompany_id(sesion.getCompany_id());
		spkH.setSystem_param_id(99);
		
		param = system_param_service.loadSelectedSystemParam(spkH);
		
		if( param != null && param.getValue() != null && param.getValue().equals("S") ){
		
			FraudeDetection fd = behaviorprocessservice.getFraudeDetection(company_id, prospectus_id);
			
			if( fd == null ){
				
				BehaviorCheck bc = new BehaviorCheck();
				
				bc.checkProcess(company_id, prospectus_id, ipaddress);
				
				fd = behaviorprocessservice.getFraudeDetection(company_id, prospectus_id);
				
				if( fd != null ){
				
					is_inussual_person = true;
					
				}
				
			}else{
				
				is_inussual_person = true;
				
			}
			
		}
		
		
	}
	
	protected void init_proyect_loan() 
	{
		if(param_values_OK)
		{
			proyect_loan_PK = new ProyectLoanPK();
			
			proyect_loan_PK.setCompany_id(company_id);
			proyect_loan_PK.setProspectus_id(prospectus_id);
			proyect_loan_PK.setProyect_id(proyect_id);
			proyect_loan_PK.setProyect_loan_id(proyect_loan_id);
			
			proyect_loan = proyectloanservice.getProyectLoanById(proyect_loan_PK);
			
			acreditado_area = proyect_loan.getPerson().getProspectus().getArea();

/*			
			nacionalidad = proyect_loan.getPerson().getCitizenship();
			
			
			if(nacionalidad == EXTRANJERO)
			{
				extranjero_ENABLED = true;
				
			} else {
				
				extranjero_ENABLED = false;
			}	
*/			
		}
	}
					
	protected void init_alta_prospecto() 
	{			
		if(param_values_OK)
		{
			service_fondeo = new AltaProspectoIMP();	
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setAcreditado(proyect_loan);			
			
			service_fondeo.init();
			
			SAFI_prospectus_id = service_fondeo.getSAFI_prospectus_id();
			alta_prospecto_OK  = service_fondeo.isAlta_prospecto_OK();				
			lista_errores      = service_fondeo.getLista_errores();
		}
	}
	
	protected void init_solicitud_credito() 
	{
		if(alta_prospecto_OK)
		{		
			service_fondeo = new SolicitudCreditoIMP();
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setAcreditado(proyect_loan);			
			
			service_fondeo.init();
			
			solicitud_credito_OK = service_fondeo.isSolicitud_credito_OK();	
			lista_errores        = service_fondeo.getLista_errores();
		}
	}
	
	protected void init_creacion_cliente() 
	{
		if(solicitud_credito_OK)
		{	
			service_fondeo = new CreacionClienteIMP();
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setAcreditado(proyect_loan);			
			
			service_fondeo.init();
			
			SAFI_client_id = service_fondeo.getSAFI_client_id();
			lista_errores  = service_fondeo.getLista_errores();
		}
	}
	
	protected void init_alta_cliente() 
	{
/*		
		if(extranjero_ENABLED)
		{				
			service_fondeo = new AltaClienteIMP();
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setSesion(sesion);
			service_fondeo.setAcreditado(proyect_loan);					
			
			service_fondeo.init();
			
			status_funding = service_fondeo.isAlta_cliente_OK();	
			lista_errores  = service_fondeo.getLista_errores();
		}
			
		if(!extranjero_ENABLED)
		{
			service_fondeo = new AltaClienteIMP();
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setSesion(sesion);
			service_fondeo.setAcreditado(proyect_loan);			
			
			alta_cliente_ENABLED = service_fondeo.isAlta_cliente_ENABLED();			
			lista_errores        = service_fondeo.getLista_errores();
			
			if(alta_cliente_ENABLED)
			{
				service_fondeo.init();
				
				status_funding = service_fondeo.isAlta_cliente_OK();	
				
			} else {
				
				status_funding = false;				
			}			
		}
*/		
		
		service_fondeo = new AltaClienteIMP();
		service_fondeo.setLista_errores(lista_errores);
		service_fondeo.setSesion(sesion);
		service_fondeo.setAcreditado(proyect_loan);			
		
		alta_cliente_ENABLED = service_fondeo.isAlta_cliente_ENABLED();			
		lista_errores        = service_fondeo.getLista_errores();
		
		if(alta_cliente_ENABLED)
		{
			service_fondeo.init();
			
			status_funding = service_fondeo.isAlta_cliente_OK();	
			
		} else {
			
			status_funding = false;				
		}	
	}
	
	protected void init_creacion_cuenta() 
	{
		if(solicitud_credito_OK)
		{	
			service_fondeo = new CreacionCuentaIMP();			
			service_fondeo.setLista_errores(lista_errores);
			service_fondeo.setAcreditado(proyect_loan);
			
			service_fondeo.init();			
			
			SAFI_account_id = service_fondeo.getSAFI_account_id();		
			lista_errores   = service_fondeo.getLista_errores();
			
			cuenta_OK = service_fondeo.isCuenta_OK();
		}
	}
	
	protected void init_alta_relacionado_cuenta() 
	{
		service_fondeo = new AltaRelacionadosCuentaIMP();
		service_fondeo.setLista_errores(lista_errores);
		service_fondeo.setAcreditado(proyect_loan);
		
		service_fondeo.init();
		
		lista_errores = service_fondeo.getLista_errores();
	}
	
	protected void init_alta_conocimiento_cuenta()
	{
		service_fondeo = new AltaConocimientoCuentaIMP();				
		service_fondeo.setLista_errores(lista_errores);	
		service_fondeo.setAcreditado(proyect_loan);
		
		service_fondeo.init();
		
		lista_errores = service_fondeo.getLista_errores();
	}
	
	protected void init_alta_conocimiento_cliente() 
	{
		service_fondeo = new AltaConocimientoClienteIMP();
		service_fondeo.setLista_errores(lista_errores);	
		service_fondeo.setAcreditado(proyect_loan);		
		
		service_fondeo.init();
		
		lista_errores = service_fondeo.getLista_errores();		
	}
	
	protected void init_autorizar_cuenta() 
	{
		service_fondeo = new AutorizarCuentaIMP();
		service_fondeo.setLista_errores(lista_errores);	
		service_fondeo.setAcreditado(proyect_loan);		
		
		service_fondeo.init();
		
		lista_errores = service_fondeo.getLista_errores();	
	}
	
	protected void init_creacion_credito() 
	{		
		service_fondeo = new CreacionCreditoIMP();
		service_fondeo.setLista_errores(lista_errores);	
		service_fondeo.setAcreditado(proyect_loan);		
		
		service_fondeo.init();
		
		lista_errores  = service_fondeo.getLista_errores();	
		status_funding = service_fondeo.isCreacion_credito_OK();
	}
	
	protected void init_activacion_cuenta() 
	{						
		if(status_funding && acreditado_area.toString().equals("L"))
		{
			lista_saving_account_ACTIVA = service_saving_account.getListAccountActiveByProspect(prospectus_id, company_id);
			
			if(lista_saving_account_ACTIVA == null || lista_saving_account_ACTIVA.size() == 0)
			{
				lista_saving_account = service_saving_account.getListAccountByProspect(prospectus_id, company_id);
				
				if(lista_saving_account != null && lista_saving_account.size() > 0)
				{
					int last = lista_saving_account.size() - 1;
					
					saving_account_PK = lista_saving_account.get(last).getSaving_accountPk();
					
					saving_account = service_saving_account.getSavingAccountByID(saving_account_PK);
					
					saving_account.setStatus(1);
					
					service_saving_account.updateSavingAccount(saving_account);
				}
			}								
		}		
	}
	
	protected void init_creacion_seguro_vida() 
	{
		service_fondeo = new CreacionSeguroVidaIMP();
		service_fondeo.setLista_errores(lista_errores);
		service_fondeo.setAcreditado(proyect_loan);				
		
		service_fondeo.init();
		
		lista_errores  = service_fondeo.getLista_errores();
		status_funding = service_fondeo.isCreacion_seguro_OK();
	}
	
	protected void init_status_funding() 
	{
		request = RequestContext.getCurrentInstance();
		
		if(status_funding)
		{				
			request.addCallbackParam("isFunding", status_funding);
			request.addPartialUpdateTarget("creditos");
			
		} else {
			
			try 
			{
				if(status_funding == false && lista_errores.size() == 0)
				{
					lista_errores.add("Error al fondear.");
				}
				
				request.addCallbackParam("isFunding", status_funding);
				request.addCallbackParam("listaerror", new JSONArray(lista_errores.toArray(),true).toString());
				
			} catch (JSONException e1) {
				
				e1.printStackTrace();
			}
		}
	}
}
