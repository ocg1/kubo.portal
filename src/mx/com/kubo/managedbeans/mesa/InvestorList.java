package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.managedbeans.ApplicationParams;
import mx.com.kubo.managedbeans.CreaCreditoService;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.IdentificationCollector;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Role_Searching_PK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.StatusInvCat;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.Tutor;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import static mx.com.kubo.notificaciones.notificables.Evento.ACTIVACION_INVERSIONISTA;
import static mx.com.kubo.notificaciones.notificables.Evento.ERROR_DESARROLLO;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "investorList") @ViewScoped
public final class InvestorList extends InvestorListAMO
implements InvestorListIMO, Serializable 
{	
	private static final long serialVersionUID = -6800210668459419694L;

	@PostConstruct
	public void init()
	{
		faces     = FacesContext.getCurrentInstance();		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		role_searching = null ;
		
		if(sesion.getRole_id() != null && !sesion.getArea().toString().equals("I"))
		{		
			role_searching_PK = new Role_Searching_PK();
			role_searching_PK.setCompany_id(sesion.getCompany_id());
			role_searching_PK.setRole_id(sesion.getRole_id());
			role_searching_PK.setArea_used("I");
			
			role_searching = rolesearchingservice.getRolesearchingbyPK(role_searching_PK);
		}
		
		setupList();
				
		scriptStatus = "<script>";
							
		lstStatus = statusinvcatservice.getListStatusInvCat();
		
		for( StatusInvCat stts :lstStatus)
		{			
			if(getCheckedByStatus(stts.getStatusPK().getStatus_id()))
			{
				scriptStatus += "$('#status_"+stts.getStatusPK().getStatus_id()+"').attr('checked', true);";
			}			
		}
		
		scriptStatus += "</script>";
		
		if(sesion.getRole_id() !=null )
		{
			setPermissions(sesion.getRole_id());
		}		
	}
	
	public final void initSearch(ActionEvent e)
	{		
		String value = (String) e.getComponent().getAttributes().get("proyectAtrr").toString();
		
		System.out.println("value: "+value);
		
		request = RequestContext.getCurrentInstance();
		faces = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();
		
		if (sesion.getArea().equals('M')) 
		{		
			navMenu = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");			
			e.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
			navMenu.cambiaPagina(e);
			
			summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setSearchSummary(value);
			summarysesion.setTypeLog("EVA");
			
			summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");			
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			//summary.cargaInfoCompleta();
			
			request.addPartialUpdateTarget("form_Prin");	
			request.addPartialUpdateTarget("actualPage");
			request.addPartialUpdateTarget("pnlBankDispersion");
			
		} else if (sesion.getArea().equals('I')) {
			
			summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setTypeLog("SOL");
			summarysesion.setSearchSummary(value);
			
			summary = (SummaryRequest) resolver.getValue(elContext, null, "summaryRequest");			
			summary.mapeoDatos(summarysesion.getSearchSummary());
			
			navigationinvest = (NavigationInvest) resolver.getValue(elContext, null, "navigationInvest");
			
			e.getComponent().getAttributes().put("seccionInv", "summary");
			navigationinvest.changePage(e);
						
			request.addPartialUpdateTarget("pnlContentInvest");	
			request.addPartialUpdateTarget("panelContentListProyect");
			request.addPartialUpdateTarget("actualPage");
			
		} else {	
			
			summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setSearchSummary(value);			
		}
	}
	
	public void creaClienteInvSafi(ActionEvent e)
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion      = (SessionBean)        resolver.getValue(elContext, null, "sessionBean");	
		creacredito = (CreaCreditoService) resolver.getValue(elContext, null, "creaCreditoService");
		
		String prospectus=(String) e.getComponent().getAttributes().get("prospectusid").toString();	
		// String company=(String) e.getComponent().getAttributes().get("companyid").toString();
		
		company_id = sesion.getCompany_id();
		prospectus_id_inv = Integer.parseInt(prospectus);
				
		init_proyect_loan();
		
		lista_errores = creacredito.getLstErrors();
		
		creacredito.setParam_values_OK(true);
		creacredito.setLista_errores(lista_errores);
		creacredito.setProyect_loan(proyect_loan);
		
		alta_prospecto_OK = creacredito.callWSSafiAltaPros();		
		lista_errores     = creacredito.getLstErrors();
		
		if(alta_prospecto_OK)
		{		
			creacredito.setLista_errores(lista_errores);
			creacredito.setSolicitud_credito_OK(true);
			
			SAFI_client_id = creacredito.creaClienteSAFI();
			lista_errores  = creacredito.getLstErrors();
			
			if(proyect_loan.getPerson().getCitizenship() == EXTRANJERO)
			{
				callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
				
			} else {
			
				if(SAFI_client_id != null)
				{
					if(acreditado_IFE != null)
					{
						 callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
						 
					} else {
						
						lista_errores.add("El cliente no cuenta con la clave de elector.");						
					}
				 	
				} else {
					
					lista_errores.add("No existe el id del cliente en Safi.");					
				}
			}
			
			
			if(SAFI_client_id != null && SAFI_client_id.length() > 0)
			{
				creacredito.setLista_errores(lista_errores);
				
				cuenta_OK     = creacredito.creaCuentaSafi();
				lista_errores = creacredito.getLstErrors();
				
				if(cuenta_OK)
				{
					pld = prevencionldservice.getPrevencionLDByProspectus(prospectus_id_inv, company_id);
					
					if(pld != null)
					{
						saving = savingaccountservice.getSavingAccountByProspectus(prospectus_id_inv, company_id);
						
						if(saving != null && saving.getSafi_account_id() != null && !saving.getSafi_account_id().equals("") && !saving.getSafi_account_id().equals(" ") )
						{							
							creacredito.setLista_errores(lista_errores);
							
							creacredito.creaPLDCuentaSAFI();
							
							lista_errores = creacredito.getLstErrors();
						}
						
						if(SAFI_client_id != null && !SAFI_client_id.equals("") && !SAFI_client_id.equals(" ")  )
						{			
							creacredito.setLista_errores(lista_errores);
							
							creacredito.createPLDSAFI();
							
							lista_errores = creacredito.getLstErrors();
							
							investor_PK = new InvestorPK();
							
							investor_PK.setCompany_id(company_id);
							investor_PK.setProspectus_id(prospectus_id_inv);
							
							investor = investorservice.getInvestorById(investor_PK);
							investor.setStatus_id(2);
							
							investorservice.updateInvestor(investor);
							
							notificaHS();
							
							notificacion( Evento.ACTIVACION_INVERSIONISTA , "" ,membership, investor.getMember());	
							
													
							ApplicationParams applicationParam = (ApplicationParams) FacesContext.getCurrentInstance()
									.getApplication().getELResolver()
									.getValue(elContext, null, "applicationParams");
							
							applicationParam.getHt_member_investor().put(SAFI_client_id, membership);
							
							
						}
					}
					
					 //msg = "Tus datos como inversionista han sido creados satisfactoriamente";
					 
				} else {
					
					notificacion( ERROR_DESARROLLO, "fallo alta de cuenta Safi!!",membership, membership );
				}
				
			} else {
				
				notificacion( ERROR_DESARROLLO, "fallo alta de clienteSafi !!",membership, membership );
			}
			
		} else {
			
			notificacion(ERROR_DESARROLLO, "fallo alta de prospecto Inversionista!!", membership, membership);
		}
		
		request = RequestContext.getCurrentInstance();
		
		strRes = "";
		
		lista_errores = creacredito.getLstErrors();
		
		if(lista_errores != null && lista_errores.size() > 0)
		{			
			request.addCallbackParam("isError", true);
			
			strRes = "";
			
			for (String target : lista_errores)
			{
				 if(!target.equals("remove"))
				 {
					 strRes += target + "::";
				 }
			}

		} else {
			
			request.addCallbackParam("isError", false);
			
			strRes = "El Inversionista se dió de alta satisfactoriamente.";			
		}
		
		request.addCallbackParam("respuestaStr", strRes);
		
		request.addPartialUpdateTarget("creditos");		
	}
	
	private void notificaHS(){
		
		//HUBSPOT CONSULTA PROSPECTOR RECHAZADO
		if( membership.getPerson() != null && membership.getPerson().getProspectus() != null && membership.getPerson().getProspectus().getHs_vid() != null ){	
			
			SystemParamPK system_param_PK_I = new SystemParamPK();
			
			system_param_PK_I.setCompany_id( 1 );
			system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
			
			SystemParam system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
			
			if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
				
				HubSpotController hs =  new HubSpotController();
				
				StringBuilder properties = new StringBuilder( "{ \"property\" : \"estatus_inversionista\" , \"value\" : \"contratos_creados\"}");
				
				//String properties = "estatus_inversionista=finalizo_solicitud&hbuhu&";
				
				hs.updateProspectus(membership.getPerson().getProspectus().getHs_vid(), properties);
				
				hs.sendEvent("000002060784", membership.getEmail() );
				
				//hs.createField( properties);
				
			 }
		}
		
	}
	
	public boolean creaClienteByProspect(Integer prospectus_id, Integer company_id, boolean crea_cuenta )
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		creacredito = (CreaCreditoService) resolver.getValue(elContext, null, "creaCreditoService");
				
		this.company_id  = company_id;
		prospectus_id_inv = prospectus_id;
		
		init_proyect_loan();
		
		lista_errores = creacredito.getLstErrors();
		
		creacredito.setParam_values_OK(true);
		creacredito.setLista_errores(lista_errores);
		creacredito.setProyect_loan(proyect_loan);
		
		alta_prospecto_OK = creacredito.callWSSafiAltaPros();
		lista_errores     = creacredito.getLstErrors();
		
		if(alta_prospecto_OK)
		{			
			creacredito.setSolicitud_credito_OK(true);
			creacredito.setLista_errores(lista_errores);
			
			SAFI_client_id = creacredito.creaClienteSAFI();
			lista_errores  = creacredito.getLstErrors();
			
			if(proyect_loan.getPerson().getCitizenship() == EXTRANJERO)
			{
				callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
				
			} else {
			
				if(proyect_loan.getPerson().getSafi_client_id() != null)
				{
					if(proyect_loan.getPerson().getMx_ife_cveelector() != null)
					{
						 callSPSafiAltaIFE(prospectus_id_inv, company_id, proyect_loan);
						 
					} else {
						
						System.out.println("El cliente no cuenta con la clave de elector.");						
					}
				 	
				} else {
					
					System.out.println("No existe el id del cliente en Safi.");					
				}
			}
						
			if( crea_cuenta )
			{			
				saving = new SavingAccount();
				
				if(SAFI_client_id != null && SAFI_client_id.length() > 0)
				{
					creacredito.setLista_errores(lista_errores);
					
					cuenta_OK      = creacredito.creaCuentaSafi();
					lista_errores  = creacredito.getLstErrors();
					
					if(cuenta_OK)
					{
						pld = prevencionldservice.getPrevencionLDByProspectus(prospectus_id_inv, company_id);
						
						if(pld != null)
						{						
							saving = savingaccountservice.getSavingAccountByProspectus(prospectus_id_inv, company_id);
							
							if(saving != null && saving.getSafi_account_id() != null && !saving.getSafi_account_id().equals("") && !saving.getSafi_account_id().equals(" ") )
							{
								creacredito.setLista_errores(lista_errores);
								
								creacredito.creaPLDCuentaSAFI();
								
								lista_errores = creacredito.getLstErrors();
							}
							
							if(SAFI_client_id != null && !SAFI_client_id.equals("") && !SAFI_client_id.equals(" "))
							{					
								creacredito.setLista_errores(lista_errores);
								
								creacredito.createPLDSAFI();
								
								lista_errores = creacredito.getLstErrors();
								
								investor_PK = new InvestorPK();								
								investor_PK.setCompany_id(company_id);
								investor_PK.setProspectus_id(prospectus_id_inv);
								
								investor = investorservice.getInvestorById(investor_PK);
								investor.setStatus_id(2);
								
								investorservice.updateInvestor(investor);
								
								notificacion(ACTIVACION_INVERSIONISTA , "" ,membership, investor.getMember() );								
							}													
						}
							
						 //msg = "Tus datos como inversionista han sido creados satisfactoriamente";
						 
					} else {
						
						notificacion(ERROR_DESARROLLO, "fallo alta de cuenta Safi!!",null, membership );
					}
					
				} else {
					
					notificacion(ERROR_DESARROLLO, "fallo alta de clienteSafi !!",null, membership );
				}
			
				
				Asignamos_cuenta_a_tutor ( saving );
				
				if (ejecuta_store_en_safi(membership))
				{
					return true;
					
				} else {
					
					return false;
				}
				
			}//No crea Cuenta
			
		} else {
			
			notificacion(ERROR_DESARROLLO, "fallo alta de prospecto Inversionista!!", null,membership);
		}
		
		request = RequestContext.getCurrentInstance();
		
		strRes = "";
		
		lista_errores = creacredito.getLstErrors();
		
		if(lista_errores !=null && lista_errores.size() > 0 )
		{			
			request.addCallbackParam("isError", true);
			
			strRes="";
			
			for (String target : lista_errores)
			{
				 if(!target.equals("remove"))
				 {
					 strRes += target+"::";
				 }
		    }		
			
		} else {
			
			request.addCallbackParam("isError", false);
			
			strRes = "El Inversionista se dió de alta satisfactoriamente.";			
		}
		
		request.addCallbackParam("respuestaStr",strRes);		
		request.addPartialUpdateTarget("creditos");
		
		return true;	
	}

	public boolean callSPSafiAltaIFE(int prospectus_id_inv,int company_id, ProyectLoan pl) {
		System.out.println("+++++    LLAMANDO SP SAFI    +++++");
			
			try {
				
				
				ServiceCalling srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
				srvCall.setInfo("Invocando el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
				srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
				srvCall.setStatus(1);
				
				servicecallingService.saveServiceCall(srvCall);
				
				IdentificationCollector objIFE;				
				
				if(pl.getPerson().getCitizenship() == EXTRANJERO)
				{
					objIFE = naturalPersonService.getIdentificationCollector(Long.parseLong(pl.getPerson().getSafi_client_id()), 
							6, "S", "111111111111111111", null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
				} else {
					objIFE = naturalPersonService.getIdentificationCollector(Long.parseLong(pl.getPerson().getSafi_client_id()), 
							1, "S", acreditado_IFE, null, null, 1, 2, new Date(), "1.1.1.1", "Portal Kubo", 1, 1);
				}
				
				srvCall = new ServiceCalling();
				if(objIFE != null){
					if(objIFE.getNumErr().equals("001") || objIFE.getNumErr().equals("000")){
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
						srvCall.setInfo("Regresando Satisfactoriamente de invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT: "+objIFE.getErrMen());
						srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
						srvCall.setStatus(2);
						
						servicecallingService.saveServiceCall(srvCall);
						
						return true;
					}else{
						
						srvCall.setAcces_datetime(new Date());
						srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
						srvCall.setInfo("Regresando satisfactoriamente de invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT:"+objIFE.getErrMen());
						srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
						srvCall.setStatus(2);
						
						servicecallingService.saveServiceCall(srvCall);
						System.out.println(objIFE.getErrMen());
						return false;
					}
				}else{
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
					srvCall.setInfo("Error al invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
					srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
					srvCall.setStatus(3);
					srvCall.setException("El objeto es null. Error en el DAO NaturalPerson.getIdentificationCollector()");
					
					servicecallingService.saveServiceCall(srvCall);
					System.out.println("Error al dar de alta los datos de identificación en Safi.");
					return false;
				}

			} catch (Exception e) {
				System.out.println("****************************************************************************************************");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				System.out.println("Exception:");
				e.printStackTrace();
				System.out.println("-----Exception-----");
				System.out.println(e.getMessage());
				System.out.println("****************************************************************************************************");
				System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				
				ServiceCalling srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getPerson().getNatPerPK().getCompany_id());
				srvCall.setInfo("Error al invocar el procedimiento almacenado microfin.IDENTIFICLIENTEALT");
				srvCall.setProspectus_id(pl.getPerson().getNatPerPK().getProspectus_id());
				srvCall.setStatus(3);
				
				if(e.getStackTrace().toString().length()>1999)
					srvCall.setException(e.getStackTrace().toString().substring(0,1999));
				else
					srvCall.setException(e.getStackTrace().toString());
				servicecallingService.saveServiceCall(srvCall);
				
				System.out.println("Error al dar de alta los datos de identificación en Safi.");
				
				return false;
				
			}
		}
	
	public boolean notificacion(Evento evento,String errormsg,Membership emisor,  Membership acreditado )
	{
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(emisor);
			notificador.setAcreditado(acreditado);
			notificador.notificar(evento, null, null, errormsg);
			
		} catch (NotificacionException e) {
			
			e.printStackTrace();
		}	
		
		return true;
	}
			
	public String validaRegistrationReason( Membership membership ){
		
		boolean flag = false;
		
		String registrationReason ="";
		
		if(membership !=null && membership.getRegistration_reason()!=null){
			
			if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 7){ //Otro
				//registrationReason = membershipship.getRegistration_reason().getName() ;
				if(membership.getOther_registration_reason()!=null){
					flag = true;
					registrationReason = " "+membership.getOther_registration_reason();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 3){
				
				if(membership.getWho_recommends()!=null){
					flag = true;
					registrationReason = " Recomendado por "+membership.getWho_recommends();
				}else{
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
				}
				
			}else if(membership.getRegistration_reason_id()!=null && membership.getRegistration_reason_id() == 8){ //PriceShoes
				
					flag = true;
					registrationReason = " "+membership.getRegistration_reason().getName();
					
					if(membership.getPriceshoes_number()!=null && membership.getPriceshoes_number().trim().length() >0 ){
						
						registrationReason = " con numero de socio "+membership.getPriceshoes_number();
						
					}
					
			}else if(membership.getRegistration_reason() != null && membership.getRegistration_reason_id() != 6 ){
				
				flag = true;
				registrationReason = " "+membership.getRegistration_reason().getName();
				
			}
			
			if(membership.getPromotor_id()!=null && membership.getPromotor_id()>0 ){ //Promotor
				
				if(membership.getPromotor()!=null){
					
					if(flag ){
					
						registrationReason += " asignado al Promotor "+membership.getPromotor().getName();
						
					}else{
						
						registrationReason += " Promotor "+membership.getPromotor().getName();
						
					}
					
				}
				
			}else{
				
				registrationReason += " sin promotor asignado ";
				
			}
			
		}
		
		return registrationReason;
		
	}
	
	public boolean getCheckedByStatus(Integer status){
			
			boolean flag = false;
			
			if(role_searching == null ){
			
				flag =  true;
				
			}else{
				String val[] = role_searching.getStatus_id().split(",");
				for( int i = 0; i< val.length; i++ ){
					
					if( val[i].equals( status.toString() )) {
					
						flag = true;
						break;
						
					}else{
						
						flag =  false;
						
					}
				}
				
			}
			
			return flag;
		}
	
	public Double getPorcentByStatus(Integer status){
		int total=this.investorlist.size();
		Double min = 0.0;
		for(Investor p : investorlist){
			if(p.getStatus_id().equals(status)){
				min++;
			}
		}	
		DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
		simbolo.setDecimalSeparator('.');
		DecimalFormat formateador = new DecimalFormat("#####.##",simbolo);
		Double valor = 0d;
		if(total>0)
			valor = (((100*total)-100*(total-min))/total);
		if(valor>100.00)
			return 100.00;
		else 
			return Double.valueOf(formateador.format(valor)); 
		
	}
	
	public void updateByFiltering(ActionEvent e){
		
		String getStatus=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cadena3");
		String concatRiskCad="";
		
			concatRiskCad="i.status_id in("+getStatus+") ";
		
		
		String cad = concatRiskCad;
		System.out.println( "cadena:"+cad  );
		
		
		if(!cad.equals("")){
			
			if(sesion.getArea().equals('M')){
				
				investorlist = investorservice.getInvestorByFiltering(cad);
				
			}
			
			
			
		}
		
		//sdsd
		
		System.out.println("status: "+getStatus);
		
		String[] varstatus = getStatus.split(",");
		
		if(varstatus != null && varstatus.length >0 ){
			scriptStatus = "<script>";
				for(int i = 0; i < varstatus.length ; i++ ){
		    		
		    		
		    		scriptStatus += "$('#status_"+varstatus[i].replaceAll("'", "")+"').attr('checked', true);";
		    		
		    	}
				
				scriptStatus += "</script>";	
				
			}
		
		
		/*
		if(getCheckedByStatus(stts.getStatusPK().getStatus_id()))
			scriptStatus += "$('#status_"+stts.getStatusPK().getStatus_id()+"').attr('checked', true);";
		*/
		
		
	}
	
	public void validaTutor(ActionEvent e){
		
		RequestContext request =  RequestContext.getCurrentInstance();
		
		String prospectus=(String) e.getComponent().getAttributes().get("prospectusid").toString();	
		String companyid =(String) e.getComponent().getAttributes().get("companyid").toString();
		
		thisTutor = null;
		
		NaturalPerson personTutor = null;
		
		List<Tutor> lstTutor =  tutorservice.getTutorByProspectus( Integer.parseInt(prospectus), Integer.parseInt(companyid) );
		
		if(lstTutor != null && lstTutor.size() > 0){
			thisTutor = lstTutor.get(0);
			
			gnNaturalPersonPK tPK = new gnNaturalPersonPK();
			tPK.setProspectus_id(thisTutor.getProspectus_id_tutor());
			tPK.setCompany_id(thisTutor.getCompany_id());	
			
			personTutor = gnNaturalService.getNaturalPersonById(tPK);
			
		}else{
			thisTutor = null;
			personTutor = null;
		}
		
		if( thisTutor == null || personTutor == null )
		{
			request.addCallbackParam("resultado", "1");//SIN_TUTOR
			request.addCallbackParam("nombreTutor", true);
			
		} else if(personTutor.getSafi_client_id() == null)
		{
			
			request.addCallbackParam("resultado", "2");//TUTOR_SIN_ACTIVAR
			request.addCallbackParam("nombreTutor", personTutor.NombreCompletoNPM());
			
		}else{
			
			if(creaClienteByProspect( Integer.parseInt(prospectus), Integer.parseInt(companyid), true )){
				request.addCallbackParam("resultado", "3");//Exito
				request.addCallbackParam("resultadoMsg", "El cliente fue dado de alta exitosamente");
			}else{
				
					request.addCallbackParam("resultado", "4");//Error
					request.addCallbackParam("resultadoMsg", "El cliente fue dado de alta exitosamente");
				
			}
			
		}
		
		
	}
	
	public void activaTutor_Y_Menor(ActionEvent e){
		
		System.out.println("ACTIVANDO A MENOR Y TUTOR");
		
		RequestContext request =  RequestContext.getCurrentInstance();
		
		String prospectus=(String) e.getComponent().getAttributes().get("prospectusid").toString();	
		String companyid =(String) e.getComponent().getAttributes().get("companyid").toString();
		
		thisTutor = null;
		
		List<Tutor> lstTutor =  tutorservice.getTutorByProspectus( Integer.parseInt(prospectus), Integer.parseInt(companyid) );
		
		thisTutor = lstTutor.get(0);
		
		if (creaClienteByProspect( thisTutor.getProspectus_id_tutor() , thisTutor.getCompany_id() , false) ){
			
			InvestorPK invPk = new InvestorPK();
			
			invPk.setCompany_id( thisTutor.getCompany_id() );
			invPk.setProspectus_id(thisTutor.getProspectus_id_tutor());
			
			Investor inv = investorservice.getInvestorById(invPk);
			inv.setStatus_id(2);
			investorservice.updateInvestor(inv);
			
			if( creaClienteByProspect( Integer.parseInt(prospectus) , thisTutor.getCompany_id() , true) ){
				request.addCallbackParam("resultado", "3");//Error
				request.addCallbackParam("resultadoMsg", "Los Clientes fueron dados de alta satisfactoriamente");
			}else{
				
				request.addCallbackParam("resultado", "4");//Error
				request.addCallbackParam("resultadoMsg", "Error al dar de alta al Menor");
				
			}
		}else{
			
			request.addCallbackParam("resultado", "4");//Exito
			request.addCallbackParam("resultadoMsg", "Error al dar de alta al tutor");
			
		}
		
	}
				
	public void setupList()
	{
		if( role_searching == null )
		{			
			if(sesion.getArea().equals('M'))
			{
				investorlist = investorservice.getInvestorList();
			}
			
		}else{
			
			investorlist = investorservice.getInvestorListByRole( role_searching );
			
		}
	}

}
