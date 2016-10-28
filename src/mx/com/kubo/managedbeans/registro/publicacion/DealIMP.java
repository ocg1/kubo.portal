package mx.com.kubo.managedbeans.registro.publicacion;

import static mx.com.kubo.notificaciones.notificables.Evento.PUBLICACION;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.DealBean;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Bank;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificador.NotificacionException;

import org.primefaces.event.SelectEvent;

import safisrv.ws.CreditosServicios.SAFIServicios;
import safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoRequest;
import safisrv.ws.CreditosServicios.SimuladorCuotaCreditoResponse;

@SuppressWarnings("serial")
@ManagedBean(name = "deal") 
@ViewScoped
public class DealIMP extends DealPMO
implements Serializable, DealIMO
{ 									
	@PostConstruct
	public void init() 
	{				
		try
		{
			System.out.println( "Iniciando DealIMP" );
			
			faces     = FacesContext.getCurrentInstance();
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			external  = faces.getExternalContext();
			
			simulator = (Simulator)   resolver.getValue(elContext, null, "simulator");
			sesion    = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			
			if( isSesion_DISABLED() ){
				return;
			}
			
			prospectus_id = sesion.getProspectus_id();
			company_id    = sesion.getCompany_id();
			area          = sesion.getArea();
			rate          = sesion.getRate() != null ? sesion.getRate() : 52.6D;
			
			prosPK        = new ProspectusPK();
			prosPK.setProspectus_id(prospectus_id);
			prosPK.setCompany_id(company_id);

			prospectus  = prospectusService.getProspectusById(prosPK);
			if( prospectus.getArea().toString().equals("I")){
				dispInvestor = true;
			}else{
				dispInvestor = false;
			}
			
			membership_PK = new MembershipPK();
			membership_PK.setCompany_id(company_id);
			membership_PK.setProspectus_id(prospectus_id);
			
			membership = membershipservice.getMembershipById(membership_PK);
			
			promotor_id = membership.getPromotor_id();
			
			proyect        = proyectService.getMaxProyect(prospectus_id, company_id);			
			score          = scoringService.loadMaxScoringByProspectus(prospectus_id, company_id);			
			accountList    = clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, company_id);
			menuIncomplete = accessService.loadMenu(prospectus_id, company_id, area);
		
			if(area != 'I')
			{	
					
				change_prospectus_id = prospectus_id;
				
				if(menuIncomplete != null && menuIncomplete.size() > 0)
				{											
					if(asignarMenu())
					{
						setBeginDisp(false);
						setErrorDisp(true);
						setSuccessDisp(false);
						setContResp(true);
						
						setDisplayBtnDeal(true);
						
						return;
					}
				}			
			}else{
				change_prospectus_id = membership.getMembershipPK().getProspectus_id();
			}
										
			asignarClabeAccount(prospectus_id, company_id);	
						
			procesarEstatus(rate, simulator);
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}		
	}
	
	public final void altaProspectSafiAndProyectLoan()
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();

		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		
		natural_person_PK = new gnNaturalPersonPK();
		natural_person_PK.setCompany_id(company_id);
		natural_person_PK.setProspectus_id(prospectus_id);
		
		natural_person = naturalPersonService.getNaturalPersonById(natural_person_PK);
		
		membership_PK = new MembershipPK();
		
		membership_PK.setCompany_id(company_id);
		membership_PK.setProspectus_id(prospectus_id);
		
		membership = membershipservice.getMembershipById(membership_PK);
		
		prospectus = natural_person.getProspectus();

		proyect_loan = creaProyectLoan(prospectus_id, company_id);
				
		if(proyect_loan != null)
		{					
			documents_reviwed_OK = reasignador_service.callSGB(proyect_loan.getProyect(), proyect_loan);
					
			if(documents_reviwed_OK)
			{												 												 
				 try 
				 {								
				 	notificador.setEmisor(membership);
					notificador.notificar(PUBLICACION, score, proyect_loan, "");
					
					if( natural_person != null && natural_person.getProspectus() != null && 
						natural_person.getProspectus().getArea() != null && 
						natural_person.getProspectus().getArea().toString().equals("L") ){
						
						SystemParamPK spk = new SystemParamPK();
						
						spk.setCompany_id(natural_person.getNatPerPK().getCompany_id());
						spk.setSystem_param_id(88);
						
						SystemParam sys =  system_param_service.loadSelectedSystemParam( spk );
						
						if( sys != null && sys.getValue() != null && sys.getValue().equals("S")  && 
								natural_person.getProspectus().getInfusion_id() != null ){
						
							InfusionSoft infusion = new InfusionSoft();
						
							infusion.addTAgToContact( natural_person.getProspectus().getInfusion_id() , 115 ); // tag Publicación
					
						}
						
						//HUBSPOT PUBLICACION
						if( natural_person != null && natural_person.getProspectus() != null && natural_person.getProspectus().getHs_vid() != null ){	
							
							SystemParamPK system_param_PK_I = new SystemParamPK();
							
							system_param_PK_I.setCompany_id( 1 );
							system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
							
							 SystemParam system_param_I = system_param_service.loadSelectedSystemParam(system_param_PK_I);
							
							if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
								
								HubSpotController hs =  new HubSpotController();
								
								String properties = "{ \"property\" : \"estatus_prospecto\" , \"value\" : \"publicado\"}";
								
								hs.updateProspectus(natural_person.getProspectus().getHs_vid(), properties);
								
							 }
						}
						
					}
					
						
				 } catch (NotificacionException e) {			
					e.printStackTrace();
				 }	
						
				setBeginDisp(false);
				setErrorDisp(false);
				setSuccessDisp(true);
				setErrorDesc("");
				setContResp(true);
				
				setSuccess_OK(true); 
				
						
			} else {
				
				setBeginDisp(false);
				setErrorDisp(true);
				setSuccessDisp(false);
				setErrorDesc("Error al dar de alta los documentos en SGB");
				setContResp(true);
			}
					
		} else {
			
			setBeginDisp(false);
			setErrorDisp(true);
			setSuccessDisp(false);
			setErrorDesc("Error al crear el proyecto");
			setContResp(true);
		}		 
	}
					
	public void selectAccount()
	{
		if(getSelAccount() != null && getSelAccount().equals("1"))
		{
			setAccountDisp("block");
			
			if(hasAccount)
			{
				thisClabe = new ClabeAccount();
				ClabeAccountPK clabepk = new ClabeAccountPK();
				clabepk.setCompany_id(prospectus.getProspectusPK().getCompany_id());
				clabepk.setProspectus_id(prospectus.getProspectusPK().getProspectus_id());
				thisClabe.setClabepk(clabepk);
			}
			
		} else {
			setAccountDisp("none");
			removeClabe();
			thisClabe = new ClabeAccount();
			ClabeAccountPK clabepk = new ClabeAccountPK();
			clabepk.setCompany_id(prospectus.getProspectusPK().getCompany_id());
			clabepk.setProspectus_id(prospectus.getProspectusPK().getProspectus_id());
			thisClabe.setClabepk(clabepk);
			setHasAccount(false);
		}
	}
	
	public List<String> autocomplete(String query) {

		List<Bank> results = new ArrayList<Bank>();
		List<String> lista = new ArrayList<String>();
		lista.removeAll(results);
		results = bankService.searchBankList(query,true);
		for (Bank resultado : results) {
			lista.add(resultado.getShort_name());
		}

		return lista;

	}
	
	public void handleSelect(SelectEvent event) {
		
		String val = event.getObject().toString();
		updateBank(val);
		
	}
	
	public void updateBank(String name)
	{
		Bank banco = bankService.getBankByShortName(name);
		
		if(banco != null)
		{
			getThisClabe().setBank_id(banco.getBankpk().getBank_id());
			getThisClabe().setBank_description(banco.getShort_name());
			
		} else {
			
			getThisClabe().setBank_id(null);
		}
		
		updateClabe();
	}
	
	public void updateClabe()
	{
		if(hasAccount){
			clabeaccountservice.updateClabeAccount(getThisClabe());
			setHasAccount(true);
		}else{
			clabeaccountservice.saveClabeAccount(getThisClabe());
			setHasAccount(true);
		}
	}
	
	public void removeClabe()
	{
		if(hasAccount)
		{
			clabeaccountservice.removeClabeAccount(getThisClabe());
			
			setHasAccount(false);
		}
	}
	
	public boolean changeFormPagoCom(AjaxBehaviorEvent event)
	{
		System.out.println("Obteninedo....");
		DealBean id = (DealBean)event.getComponent().getAttributes().get("beanSel");
		System.out.println("beanSel...."+id.getTitle());
		
		for(DealBean dealbean : dealList)
		{
			if(id.getTitle().equals(id.getTitle()))
			{
				SimuladorCuotaCreditoRequest simulador = new SimuladorCuotaCreditoRequest();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				setFechaInicio(format.format(new Date()));
				
				//setFechaInicio("2012-09-07");
				String frecuencia = getFrecuencia(dealbean.getFrequency_Id());
				String montoSolici = dealbean.getAmountStr().replace("$", "").replace(",", "").replace(" ", "")+"";
				String plazo = dealbean.getTerm_Id()+"";
				
				
				simulador.setFechaInicio(getFechaInicio());
				simulador.setFrecuencia(frecuencia);
				simulador.setMontoSolici(montoSolici);
				simulador.setPlazo(plazo);
				String tasaAnualizada = score.getRate()+"";
				simulador.setAjustarFecVen("N");
					
				
				String montoComision = getMontoComision(score,Double.parseDouble(montoSolici));
				
				if(formaPagoCom.equals("T"))
				{
					//montoComision = "0";
					tasaAnualizada = generateRateDif(Double.parseDouble(tasaAnualizada));
					simulador.setComisionApertura("0");
					simulador.setFormaCobroComAp("D");
				}
				
				if(formaPagoCom.equals("D"))
				{
					simulador.setComisionApertura(montoComision);
					simulador.setFormaCobroComAp("D");
				}
				
				
				simulador.setTasaAnualizada(tasaAnualizada);
				
				//setFechaInicio(formatStr.format(new Date()));

				
				System.out.println("*******************************************************************");
				System.out.println("frechaInicio: "+getFechaInicio());
				System.out.println("frecuencia: "+frecuencia);
				System.out.println("montoSolici: "+montoSolici);
				System.out.println("plazo: "+plazo);
				System.out.println("tasaAnualizada: "+tasaAnualizada);
				System.out.println("*******************************************************************");
				
				ServiceCalling srvCall = new ServiceCalling();
				
				
				ELContext elContext = FacesContext.getCurrentInstance().getELContext();
				SessionBean sesion 
			    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
			        .getELResolver().getValue(elContext, null, "sessionBean");
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(sesion.getCompany_id());
				srvCall.setInfo("Invocando Servicio Web SAFIServiciosServiceLocator.getSAFIServiciosSoap11.simuladorCuotaCredito");
				srvCall.setProspectus_id(sesion.getProspectus_id());
				srvCall.setStatus(1);
				servicecallingRepository.saveServiceCall(srvCall);
				
				try
				{
					SAFIServiciosServiceLocator locator = new SAFIServiciosServiceLocator();
					SAFIServicios service = locator.getSAFIServiciosSoap11();
					SimuladorCuotaCreditoResponse response =  service.simuladorCuotaCredito(simulador);
					dealbean.setCat(response.getCat());
					dealbean.setCatStr(((double)Math.round((Double.parseDouble(response.getCat()))*10)/10)+"%");
					dealbean.setFee(response.getMontoCuota());
					dealbean.setRate(tasaAnualizada+"%");
					dealbean.setTotalCuotas(response.getNumeroCuotas());
					dealbean.setTotalStr("$"+response.getTotalPagar());
					dealbean.setFeeStrVal("$"+response.getMontoCuota());
					
					if(formaPagoCom.equals("D"))
					{
						Double d = Double.parseDouble(dealbean.getAmountStr().replace("$", "").replace(",","").replace(" ", ""))-
								Double.parseDouble(dealbean.getMontoComision().replace("$", "").replace(",","").replace(" ", ""));
						d = ((double)Math.round(d*100)/100);
						String str = num.format(d);
						dealbean.setAmountReal("$"+str);
					}else{
						dealbean.setAmountReal(dealbean.getAmountStr());
					}
				}catch(Exception e){
					
				}
			}
		}
		
	    return true;
	}
	
	public String getCompleteName(NaturalPerson person) {
		
		String completeName ="";
		
		if (person != null){
			if(person.getFirst_name()!=null){
				completeName += person.getFirst_name();
			}
			if(person.getMiddle_name()!=null){
				completeName += " "+person.getMiddle_name();
			}
			if(person.getFather_last_name()!=null){
				completeName += " "+person.getFather_last_name();
			}
			if(person.getMother_last_name()!=null){
				completeName += " "+person.getMother_last_name();
			}
		}
		return completeName;
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
	
	public void saveMembership(){
		
		membershipservice.update(membership);
		
	}
	
	private boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=DealIMP");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
	
	public void saveProspectData (){
		prospectusService.update(prospectus);
	}
}
