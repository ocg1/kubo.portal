package mx.com.kubo.managedbeans.mesa.solicitud;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ConsultingBean;
import mx.com.kubo.bean.EditImageSession;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.bean.InvestorsAccounts;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.ListInvestor;
import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ProyectBean;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.bean.ShowChangeSession;
import mx.com.kubo.controller.inversion.Inversion;
import mx.com.kubo.controller.shortURL.GeneraURLCorta;
import mx.com.kubo.controller.shortURL.RequestShortURL;
import mx.com.kubo.controller.shortURL.ResponseShortURL;
import mx.com.kubo.listeners.mesa.solicitud.EditorCurpIMP;
import mx.com.kubo.listeners.mesa.solicitud.EditorNombreIMP;
import mx.com.kubo.listeners.mesa.solicitud.EditorRfcIMP;
import mx.com.kubo.listeners.mesa.solicitud.EditorTipoCreditoIMP;
import mx.com.kubo.managedbeans.AlertsManaged;
import mx.com.kubo.managedbeans.ApplicationParams;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.MyInvestments;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMP;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.portal.referencias.ReferenciasPersonalesIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.AutomaticInvestment;
import mx.com.kubo.model.Bank;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ClabeAccountPK;
import mx.com.kubo.model.ContactWayProspectus;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesPK;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.LegalStatusPK;
import mx.com.kubo.model.ListPorc;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.model.Marital_StatusPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PrevencionLDPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanInfo;
import mx.com.kubo.model.ProyectLoanInfoPK;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.PublicForumPK;
import mx.com.kubo.model.ResidencePK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.Study_LevelPK;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.proyect_loan.documentacion.CopiarArchivosIMP;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMP;
import mx.com.kubo.mesa.solicitud.perfil.IndicePagoDeudasIMP;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;

import com.soa.model.businessobject.BurResponse;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.request.BCRiskRequest;
import com.soa.webServices.responses.ProspectBCRiskResponse;
import com.soa.webServices.responses.WsSgbResponse;

@ManagedBean(name = "summaryRequest") @ViewScoped 
public final class SummaryRequest extends IngresosPMO
implements SummaryRequestIMO,  Serializable
{	
	private static final long serialVersionUID = -25079005798083445L;		

	@PostConstruct
    public void init() 
	{
		
		System.out.println( "" );
		System.out.println( "" );
		
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		System.out.println( "***SummaryRequest.init()***" );
		System.out.println( "*******************************" );
		System.out.println( "*******************************" );
		
		System.out.println( "" );
		System.out.println( "" );
		
		Date d1 = new Date();
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(d1);
		
		//System.out.println("SummaryRequest.init()");
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
        sesion          = (SessionBean)         resolver.getValue(context, null, "sessionBean");
        session_sumary  = (SearchSummaySession) resolver.getValue(context, null, "searchSummaySession");  
        applicationParam = (ApplicationParams)  resolver.getValue(context, null, "applicationParams");
        
        showInvestPnl = session_sumary.isShowInvestPnl();
        sesion_search = session_sumary.getSearchSummary();                
        
        hasReinvestment  	= false;
		frequencyInvestment	= null;
		lastInvestment		= null;
		nextInvestment		= null;
        
        if(sesion.getProspectus_id() != null)
        {
	        membership_PK = new MembershipPK();
	        membership_PK.setProspectus_id(sesion.getProspectus_id());
	        membership_PK.setCompany_id(sesion.getCompany_id());
			
			user = service_membership.getMembershipById(membership_PK);		
        }

        if(sesion_search != null)
        {        	
        	mapeoDatos(sesion_search);
        	
        	session_sumary.isShowInvestPnl();
        }
        
        tutor = new NaturalPerson();
        
        asignarFecha();   
        
        Date d4 = new Date();
		Calendar cd_4 = Calendar.getInstance();
		cd_4.setTime(d4);
		
		//long dif_1 = cd_1.getTimeInMillis() - cd_4.getTimeInMillis();
		
		//System.out.println("SummaryRequest Tirmpo total Init(): " + dif_1 + " milisegundos " );
    }
	
	public void initInvesmentData(){
		
		//System.out.println( "INVERSIONISTA:  " +prospecto.getProspectusPK().getProspectus_id() );
		
		List<AutomaticInvestment> lstAI = automaticinvestmentservice.
												getAutomaticInvestmentListByProspect( 
															prospecto.
															getProspectusPK().
															getProspectus_id()
															);
		
		SimpleDateFormat f1 = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy hh:mm:ss a");
		
		SimpleDateFormat f2 = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' yyyy");
		
		if( lstAI != null && lstAI.size() > 0 ){
			
			//System.out.println( " LISTA AUTOMATIC_INVESTMENT:  " +lstAI.size() +" ELEMENTOS " );
			
			if( lstAI != null && lstAI.size() == 1 ){
				
				hasReinvestment = true; 
				frequencyInvestment = lstAI.get(0).getFrequency();
				lastInvestment = f1.format( lstAI.get(0).getLast_investment() );
				if( lstAI.get(0).getNext_investment_apply() != null ){
					nextInvestment = f2.format( lstAI.get(0).getNext_investment_apply());
				}else{
					nextInvestment = "Aún no realiza inversión automática.";
				}
				
				if(lstAI.get(0).getIs_active().equals("0")){
				
					statusAutomaticInvestment = "INACTIVO";
					
				}else if(lstAI.get(0).getIs_active().equals("1")){
					
					statusAutomaticInvestment = "ACTIVO"; ;
					
				}else if(lstAI.get(0).getIs_active().equals("2")){
					
					statusAutomaticInvestment = "FINALIZADO";;
					
				}
				
				
			}else if( lstAI != null && lstAI.size() > 1 ){
				
				hasReinvestment = true; 
				
				frequencyInvestment = lstAI.get( lstAI.size() - 1 ).getFrequency();
				
				lastInvestment = f1.format( lstAI.get( lstAI.size() - 1 ).getLast_investment() );
				nextInvestment = f2.format( lstAI.get(lstAI.size() - 1).getNext_investment_apply());
				
				if(lstAI.get(lstAI.size() - 1).getIs_active().equals("0")){
				
					statusAutomaticInvestment = "INACTIVO";
					
				}else if(lstAI.get(lstAI.size() - 1).getIs_active().equals("1")){
					
					statusAutomaticInvestment = "ACTIVO"; ;
					
				}else if(lstAI.get(lstAI.size() - 1).getIs_active().equals("2")){
					
					statusAutomaticInvestment = "FINALIZADO";;
					
				}
				
			}else{
				hasReinvestment = false;  
			}
			
		}else{
			//System.out.println( " LISTA AUTOMATIC_INVESTMENT:  LISTA VACIA " );
			hasReinvestment = false; 
			frequencyInvestment = "";
			lastInvestment = "";
			nextInvestment = "";
		}
		
		
		InvestorPK invPk = new InvestorPK();
		
		invPk.setCompany_id(prospecto.getProspectusPK().getCompany_id());
		invPk.setProspectus_id(prospecto.getProspectusPK().getProspectus_id());
		
		Investor inv = investorservice.getInvestorById(invPk);
		
		if( inv != null ){
		
			str_status_inv  = inv.getStatusinv().getName();
			img_status_inv  = inv.getStatusinv().getUrl_img();
		
		}
		
		SavingAccount sav = savingAccountservice.getSavingAccountByProspectus(prospecto.getProspectusPK().getProspectus_id(), prospecto.getProspectusPK().getCompany_id() );
		
		if(sav != null ){
		
			safi_account_id = sav.getSafi_account_id();
		
		}
		
	}
	
	public final void mapeoDatos(String session_search_summary)
	{
		faces = FacesContext.getCurrentInstance();
		
		httpServletRequest = (HttpServletRequest) faces.getExternalContext().getRequest(); 
		
		mD01 = Calendar.getInstance();
		mD01.setTime(new Date());				
		
		init_variables();
		
		
		if(sesion.getRole_id() != null)
		{
			setPermissions(sesion.getRole_id());
		}
		
		displayPnlOtherIncome = false;				
		
		ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	    
		if(ipAddressClient == null)  
		{
	    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
		}	    
		
		cadenaProyecto = session_search_summary;
		
		init_proyect_loan();
		
		if(actualProyect != null && actualProyect.getStatus_id() != 1)
		{
			changeActions = false;
			
			validaIsCancel();			
		}
		
		init_persona();		
		init_frecuencia_pagos();
		init_imagen_prospecto();
		init_resumen_solicitud();
		init_casos_pospuestos();		
		init_image_logo();									
		init_direccion();				
		init_membership();		
		init_reporte_inusual();
		
		
		
		init_name_visible();
	
		if( actualProyect != null && actualProyect.getSafi_credit_id() != null ){
			
			init_approval_users();
			
		}
		
		if(proyecto != null)
		{
			proyectName = proyecto.getName();
			tagLine     = proyecto.getTagline();
			goal        = proyecto.getGoal();
			benefits    = proyecto.getBenefits();
			reason      = proyecto.getReason();
			logo        = proyecto.getLogo();	
			proposito   =  proyecto.getPurpose() != null ? proyecto.getPurpose().getName():"No proporcionado";
		}
		
		score = scoreService.loadMaxScoringByProspectus(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id());
		
		if(score != null && additionalCredit && !persona.getProspectus().getArea().toString().equals("I"))
		{
			additionalCredit = true;
			
		} else {
			
			additionalCredit = false;
		}
		
		if(actualProyect != null || persona.getProspectus().getArea().toString().equals("I")  )
		{
			init_notas_del_caso();
			init_documentacion();
			init_fondeador();
			init_domicilio_fiscal();
			init_domicilio_pais_origen();
		}
		
		initAlerts();
		
		if(actualProyect == null)
		{
			casosPospuestos = new CasosPospuestosIMP();		
		}										
		
		init_lista_thisTutor();
		init_lista_imTutor();
		
		if(changeActions)
		{
			editor_CURP = new EditorCurpIMP();
			editor_CURP.setPerson(persona);
			editor_CURP.setSesion(sesion);
			
			editor_RFC = new EditorRfcIMP();
			editor_RFC.setPerson(persona);
			editor_RFC.setSesion(sesion);			
		}
		
		if(editor_tipo_credito_ENABLED && !prospecto.getArea().equals('I'))
		{
			editor_tipo_credito = new EditorTipoCreditoIMP();
			editor_tipo_credito.setSesion(sesion);
			editor_tipo_credito.setProyect_loan(actualProyect);
		}
				
		verificaRecomendado();
		
		if( persona != null && persona.getProspectus().getArea().toString().equals("I") )
		{
			cargaInfoCompleta();			
		} 
		
		mD02 = Calendar.getInstance();
		mD02.setTime(new Date());
		
		init_inversion_interna();
		
		if( prospecto.getArea().toString().equals("I") ){
			
			initInvesmentData();
			
		}
			
		
		if( persona.getProspectus().getArea().toString().equals("L") )
		{		
			inicializaPromo( actualProyect );
		}	
		
		//long l = mD02.getTimeInMillis() - mD01.getTimeInMillis();

		//System.out.println("SummaryRequest.mapeoDatos(): Tardó  "+l+" milisegundos en cargar mapeoDatos()");
	}

	public final void cargaInfoCompleta()
	{
		request   = RequestContext.getCurrentInstance();
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();	
		
		if(actualProyect != null && actualProyect.getSignature_date() != null)
		{ 			
			fm1 =  new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
 			
 			String signature_date = fm1.format(actualProyect.getSignature_date());
 			
 			String fechaStr = signature_date.split(" ")[0];
 			String horaStr  = signature_date.split(" ")[1];
 			String ampmStr  = signature_date.split(" ")[2];
 			
 			day   = fechaStr.split("/")[0];
 			month = fechaStr.split("/")[1];
 			year  = fechaStr.split("/")[2];
 			
 			hh   = horaStr.split(":")[0];
     		ampm = ampmStr;
     		mm   = horaStr.split(":")[1]; 			
 		}
		
		if( persona.getProspectus().getArea().toString().equals("I") )
		{			
			invPk = new InvestorPK();			
			invPk.setCompany_id(persona.getNatPerPK().getCompany_id());
			invPk.setProspectus_id(persona.getNatPerPK().getProspectus_id() );
			
			investor = investorservice.getInvestorById(invPk);		
		}
		
		init_loan_negotiation();										
		
		lista_identification_type = service_catalogos.getLista_tipo_credencial();
		
		init_editor_identification();		
		
/*		
		tipo_identificacion = persona.getIdentification_type_id();
		mx_ine_cic = persona.getMx_ine_cic();
		
		if(tipo_identificacion != null)
		{
			switch(tipo_identificacion)
			{
				case IFE:
					editor_ife_ENABLED = true;
					
					init_change_control_IFE();
				break;
				
				case INE:
					editor_ife_ENABLED = false;
				break;
			}	
		}
*/		
		
		if( prospecto.getArea().toString().equals("L") ){
			
			flagSameAddress = false;
			
			ProyectLoanInfoPK ppk = new ProyectLoanInfoPK();
			
			ppk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
			ppk.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
			ppk.setProyect_id(actualProyect.getProyectloanPk().getProyect_id());
			ppk.setProyect_loan_id(actualProyect.getProyectloanPk().getProyect_loan_id());
			
			ProyectLoanInfo pl_info = proyectloaninfo_service.getProyectLoanInfo(ppk);
			
			if( pl_info != null && pl_info.getMx_ife_domicilio() != null  ){
				
				if(pl_info.getMx_ife_domicilio().equals("S")){
					flagSameAddress = true; 
				}
				
			}
			
			
		}else if( prospecto.getArea().toString().equals("L") ){
			
			flagSameAddress = false;
			
			if( investor != null && investor.getMx_ife_domicilio() != null  ){
			
				if(investor.getMx_ife_domicilio().equals("S")){
				
					flagSameAddress = true; 
					
				}
			
			}
			
		}
		
		getReport(persona.getNatPerPK().getProspectus_id());
		
		init_membership_TEMP();
		
		init_change_control_reasons();
				
		fullName = persona.NombreCompletoNPM();
		email    = member.getEmail();
		
		init_relationship();
		
		if(persona.getCountry_id()==null)
		{
			persona.setCountry_id(0);
		}
		
		checkOtherIncome = ingresosService.getIncomeByTypeIncomeID(persona.getNatPerPK().getProspectus_id(),  persona.getNatPerPK().getCompany_id(), 4);
		
		if(checkOtherIncome!=null && checkOtherIncome.getAmmount()>0)
		{
			description_other_income=checkOtherIncome.getDescription()!=null?checkOtherIncome.getDescription():"";
			
		} else {
			
			description_other_income="";
		}
		
		if(persona.getCountry_id()!=null)
		{
			CountryPK paisId = new CountryPK(persona.getCountry_id(), persona.getNatPerPK().getCompany_id());
			pais = paisService.getCountryById(paisId);
		}
		
		if(persona.getState_id()!=null)
		{
			StateCatPK estadoId = new StateCatPK(persona.getState_id(), persona.getNatPerPK().getCompany_id());
			estado = estadoService.getStateById(estadoId);
		}
		
		if(persona.getStudy_level_id()!=null)
		{
			Study_LevelPK estudiosId = new Study_LevelPK(persona.getStudy_level_id(), persona.getNatPerPK().getCompany_id());
			estudios = estudioService.getStudyLevelById(estudiosId);
		}
			
		if(persona.getLegal_status_id()!=null)
		{
			LegalStatusPK estadoLegalId = new LegalStatusPK(persona.getLegal_status_id(),persona.getNatPerPK().getCompany_id());
			estadoLegal = estadoLegalService.getLegalStatusById(estadoLegalId);
		}
		
		if(persona.getMarital_status_id()!=null && persona.getLegal_status_id()!=null && persona.getLegal_status_id()==2)
		{
			Marital_StatusPK estadoMaritalId = new Marital_StatusPK(persona.getMarital_status_id(), persona.getNatPerPK().getCompany_id());
			estadoMarital = estadoMaritalService.getMaritalStatusById(estadoMaritalId);
		}

				
		prospectus_id = persona.getNatPerPK().getProspectus_id();
		company_id    = persona.getNatPerPK().getCompany_id();
				
		listEmployment = employmentService.getListEmployByProspect(prospectus_id, company_id);
		
		listBusiness = businessService.getListBusinessByProspect(prospectus_id, company_id);
		
		if(persona.getResidence_id()!=null)
		{
			ResidencePK residenciaId = new ResidencePK(persona.getResidence_id(), company_id);
			residencia = residenciaService.getResidenceById(residenciaId);
		}
		
		listIncomeType=service_income_type.getIncomeTypeListOrderByConsec();
		listExpensesType=expensesTypeService.getExpensesTypeList();
		
		listInvestor_lst = new ArrayList<ListInvestor>();
		
		Hashtable<String,Membership> member_inv_HT =  applicationParam. getHt_member_investor();
		
		if( listInvestors != null )
		{								
			for( ViewInvestmetInProyect invest : listInvestors )
			{				
				ListInvestor item = new ListInvestor();
				
				item.setAmmount( invest.getAmount() );
				item.setPorcentaje( invest.getPorcentajeFondeo() );
				
				//System.out.println("inversionista: "+invest.getProyectloanfundingPk().getInvestor());
				
				Membership membership = member_inv_HT.get(invest.getProyectloanfundingPk().getInvestor());  // service_membership.getMemberShipBySafiClientID( invest.getProyectloanfundingPk().getInvestor() ); service_membership.getMemberShipBySafiClientID( invest.getProyectloanfundingPk().getInvestor() );
				
				if( membership == null ){
					membership = getMemberInvNull();
				}

				
				item.setMembership(membership);
				
				listInvestor_lst.add(item);				
			}
		}
		
		afiliacionId = new MembershipPK(prospectus_id, company_id);
		afiliacion = service_membership.getMembershipById(afiliacionId);
						
		init_telefonos();		
		
		if(sesion.getArea().equals('M'))
		{
			init_phone_type();
								
			changeConsolidate=new ChangeBean();
			
			listChangConsolTemp= service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id,new String[]{"gn_expense"},new String[]{"ammount_minus"});
			
			changeConsolidate.setHasChange(listChangConsolTemp!=null && listChangConsolTemp.size()>0?true:false);
			changeConsolidate.setLstChanges(listChangConsolTemp!=null && listChangConsolTemp.size()>0?listChangConsolTemp:null);	
			
			prevencionLD=prevencionldservice.getSelectedPrevencionLDById(new PrevencionLDPK(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id()));		
			
			validaRegistrationReason();												
		}								
				
		firstParameter();
		
		init_referencias();				
		init_CLABE_account();
		
		if(actualProyect!=null && actualProyect.getStatus_id() == 3)
		{					
			loadRestructureBean();			
		}
		
		if( persona.getProspectus().getArea().toString().equals("L") )
		{		
			cargaConsultas();
			inicializaPromo( actualProyect );
		}		
		
		inicializafechasPLD();
		  
		init_access(SCREEN_CONSULTA_SOLICITUD);
						
		if(prospecto.getArea().toString().equals("I"))
		{			
			cargaPerfilInversion();			
		}

		indice = new IndicePagoDeudasIMP();
		indice.init();
		
		init_domicilio(false);		
					
		if(editor_nombre_ENABLED)
		{
			if(investor != null && investor.getStatus_id() < 3)
			{
				editor_nombre = new EditorNombreIMP();
				editor_nombre.setPerson(persona);
				editor_nombre.setSesion(sesion);
				
			} else if(actualProyect != null && actualProyect.getStatus_id() < 3) {
				
				editor_nombre = new EditorNombreIMP();
				editor_nombre.setPerson(persona);
				editor_nombre.setSesion(sesion);
				
			} else {
				
				editor_nombre_ENABLED = false;
			}
		}
		
		if( persona.getProspectus().getArea().toString().equals("L") )
		{	
		
			inicializaReferences();
		
		}
		
		blnComment = false;
		
		if( persona.getProspectus().getArea().toString().equals("L") )
		{
			inicializa_prospectus_comment();
		}
		
		inicializa_Contact_Way();
		
	}

	public void cargaConsultas()
	{
		
		try
		{
			
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			SimpleDateFormat formatStr = new SimpleDateFormat("dd/MMM/yyyy", new Locale("ES"));
			BurResponse consultas[] = service.getBurScores(null, null, persona.getNatPerPK().getProspectus_id()+"");
			
			
			lstConsultas = new ArrayList<ConsultingBean> ();
			
			for( BurResponse resume : consultas){
				
				if(resume.getBurScore() != null && !resume.getBurScore().equals("null")  ){
				
					//System.out.println( resume.getBurDate() +"   -   "+resume.getBurScore()+"   -   "+resume.getSolNum()+"   -   "+resume.getTipoConsulta());
					
					List<ProyectLoan> temporalProyect = service_proyect_loan.getProyectLoansListByBurSolNum(resume.getSolNum());
					
					if( temporalProyect != null && temporalProyect.size()>0 ){
					
						for(ProyectLoan tmp : temporalProyect ){
						
							ConsultingBean consult = new ConsultingBean();
							
							consult.setBcScore(resume.getBurScore());
							consult.setBursolnum(resume.getSolNum());
							
							ConvertCalendar conv = new ConvertCalendar(Long.parseLong(resume.getBurDate()));
							 
							Date dFecConsult = conv.getDate(); 
							
							String fecConsult = formatStr.format( dFecConsult );
							
							consult.setFecConsult(fecConsult);
							consult.setTipoConsulta(resume.getTipoConsulta());
							
							consult.setProyect_loan_id(tmp.getProyectloanPk().getProyect_loan_id());
							
							consult.setCredito(tmp.getSafi_credit_id()==null?"No asignado":tmp.getSafi_credit_id());
							consult.setStatus(tmp.getStatusProyect().getUrl_img());
							
							lstConsultas.add(consult);
						}
					
					}else{
						
						ConsultingBean consult = new ConsultingBean();
						
						consult.setBcScore(resume.getBurScore());
						consult.setBursolnum(resume.getSolNum());
						
						
						ConvertCalendar conv = new ConvertCalendar(Long.parseLong(resume.getBurDate()));
						 
						Date dFecConsult = conv.getDate(); 
						
						String fecConsult = formatStr.format( dFecConsult );
						
						consult.setFecConsult(fecConsult);
						
						
						consult.setTipoConsulta(resume.getTipoConsulta());
						
						consult.setCredito("Sin proyecto");
						consult.setStatus("Sin proyecto");
						
						consult.setProyect_loan_id(99999999);
						
						lstConsultas.add(consult);
						
					}
					
				}
			}
			
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
	public void loadImgProyect() throws FileNotFoundException
	{

		//ProyectPK proyectoId = new ProyectPK(actualProyect.getProyectloanPk().getProyect_id(), actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
		//proyecto = proyectoService.getProyectById(proyectoId);
		
		proyecto = actualProyect.getProyect();
		
		String origFilePath = "";
		String formatFile = "";
		File thumbFile = null;
		
		
		if(proyecto.getLogo()==null)
		{
			imageLogo1=new ImagesBean();
			imageLogo1.setWidth(150);
			imageLogo1.setHeight(150);
			imageLogo1.setThisSave(false);
			
			if(female)
			{
				imageLogo1.setUrlImgThumb("/img/sinimagenM.jpg");
				imageLogo1.setUrlImg("/img/sinimagenM.jpg");
				
			} else {
				
				imageLogo1.setUrlImgThumb("/img/sinimagen.jpg");
				imageLogo1.setUrlImg("/img/sinimagen.jpg");
			}
			
			String destination = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
			
			Hashtable<String, Integer> htWH=null;
			File fileLogo=null;
			
			if(proyecto.getLogo2()!=null)
			{
				fileLogo=new File(destination+"/"+proyecto.getLogo2());
				
				if(fileLogo.exists())
				{
					htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo2()), 445, 305);
					imageLogo2=new ImagesBean();
					imageLogo2.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
					imageLogo2.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
					
					origFilePath = (proyecto.getLogo2()).substring(0, (proyecto.getLogo2()).lastIndexOf("."));
					formatFile = (proyecto.getLogo2()).substring((proyecto.getLogo2()).lastIndexOf("."));
					
					thumbFile = new File(destination+origFilePath+"_thumb"+formatFile);
					//Si no existe el archivo thumb se crea
					if(!thumbFile.exists()){
						//Se crea el archivo temporal.
						Utilities.copyFile(thumbFile.getPath(), new FileInputStream(fileLogo.getPath()));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(thumbFile.getPath());
					}
					
					imageLogo2.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
					origFilePath = "";
					formatFile = "";
					thumbFile = null;
					
					imageLogo2.setUrlImg(proyecto.getLogo2());
					imageLogo2.setThisSave(true);
				}else
					imageLogo2=null;
				
			}else
				imageLogo2=null;				
				
			if(proyecto.getLogo3()!=null){
				fileLogo=new File(destination+"/"+proyecto.getLogo3());
				if(fileLogo.exists()){
					htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo3()), 445, 305);
					imageLogo3=new ImagesBean();
					imageLogo3.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
					imageLogo3.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
					
					origFilePath = (proyecto.getLogo3()).substring(0, (proyecto.getLogo3()).lastIndexOf("."));
					formatFile = (proyecto.getLogo3()).substring((proyecto.getLogo3()).lastIndexOf("."));
					
					thumbFile = new File(destination+origFilePath+"_thumb"+formatFile);
					//Si no existe el archivo thumb se crea
					if(!thumbFile.exists()){
						//Se crea el archivo temporal.
						Utilities.copyFile(thumbFile.getPath(), new FileInputStream(fileLogo.getPath()));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(thumbFile.getPath());
					}
					
					imageLogo3.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
					origFilePath = "";
					formatFile = "";
					thumbFile = null;
					
					imageLogo3.setUrlImg(proyecto.getLogo3());
					imageLogo3.setThisSave(true);
				}else
					imageLogo3=null;
			}else
				imageLogo3=null;
		}
		else{

			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/");
			
			Hashtable<String, Integer> htWH=null;
			File fileLogo=null;
			
			fileLogo=new File(destination+"/"+proyecto.getLogo());
			if(fileLogo.exists()){
				if(sesion.getArea().toString().equals("I")){
					htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo()), 300, 229);
				}else{
					htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo()), 400, 305);
				}
				imageLogo1=new ImagesBean();
				
				if(sesion.getArea().toString().equals("I")){
					imageLogo1.setWidth(htWH!=null?(Integer)htWH.get("Width"):300);
					imageLogo1.setHeight(htWH!=null?(Integer)htWH.get("Height"):229);
				}else{
					imageLogo1.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
					imageLogo1.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
				}
				
				
				origFilePath = (proyecto.getLogo()).substring(0, (proyecto.getLogo()).lastIndexOf("."));
				formatFile = (proyecto.getLogo()).substring((proyecto.getLogo()).lastIndexOf("."));
				
				thumbFile = new File(destination+origFilePath+"_thumb"+formatFile);
				//origFile = new File(destination+proyecto.getLogo());
				//Si no existe el archivo thumb se crea
				if(!thumbFile.exists()){
					//Se crea el archivo temporal.
					Utilities.copyFile(thumbFile.getPath(), new FileInputStream(fileLogo.getPath()));
					
					//Redimenciona y guarda img
					ImageUtils.resizeAndSaveImg(thumbFile.getPath());
				}
				
				imageLogo1.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
				origFilePath = "";
				formatFile = "";
				thumbFile = null;
				
				imageLogo1.setUrlImg(proyecto.getLogo());	
				imageLogo1.setThisSave(true);
			}else{
				imageLogo1=new ImagesBean();
				imageLogo1.setWidth(150);
				imageLogo1.setHeight(150);		
				imageLogo1.setThisSave(false);
				if(female){
					imageLogo1.setUrlImgThumb("/img/sinimagenM.jpg");
					imageLogo1.setUrlImg("/img/sinimagenM.jpg");
				}else{
					imageLogo1.setUrlImgThumb("/img/sinimagen.jpg");
					imageLogo1.setUrlImg("/img/sinimagen.jpg");
				}
			}
			
			if(proyecto.getLogo2()!=null){
				fileLogo=new File(destination+"/"+proyecto.getLogo2());
				if(fileLogo.exists()){
					if(sesion.getArea().toString().equals("I")){
						htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo2()), 300, 229);
					}else{
						htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo2()), 445, 305);
					}
					
					if(sesion.getArea().toString().equals("I")){
						imageLogo2=new ImagesBean();
						imageLogo2.setWidth(htWH!=null?(Integer)htWH.get("Width"):300);
						imageLogo2.setHeight(htWH!=null?(Integer)htWH.get("Height"):229);
					}else{
						imageLogo2=new ImagesBean();
						imageLogo2.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						imageLogo2.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
					}
					
					origFilePath = (proyecto.getLogo2()).substring(0, (proyecto.getLogo2()).lastIndexOf("."));
					formatFile = (proyecto.getLogo2()).substring((proyecto.getLogo2()).lastIndexOf("."));
					
					thumbFile = new File(destination+origFilePath+"_thumb"+formatFile);
					//origFile = new File(destination+proyecto.getLogo());
					//Si no existe el archivo thumb se crea
					if(!thumbFile.exists()){
						//Se crea el archivo temporal.
						Utilities.copyFile(thumbFile.getPath(), new FileInputStream(fileLogo.getPath()));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(thumbFile.getPath());
					}
					
					imageLogo2.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
					origFilePath = "";
					formatFile = "";
					thumbFile = null;
					
					
					imageLogo2.setUrlImg(proyecto.getLogo2());
					imageLogo2.setThisSave(true);
				}else
					imageLogo2=null;
				
			}else
				imageLogo2=null;				
				
			if(proyecto.getLogo3()!=null){
				fileLogo=new File(destination+"/"+proyecto.getLogo3());
				if(fileLogo.exists()){
					
					if(sesion.getArea().toString().equals("I")){
						htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo3()), 300, 229);
					}else{
						htWH=ImageUtils.scaleImage(new File(destination+"/"+proyecto.getLogo3()), 445, 305);
					}
					
					if(sesion.getArea().toString().equals("I")){
						
						imageLogo3=new ImagesBean();
						imageLogo3.setWidth(htWH!=null?(Integer)htWH.get("Width"):300);
						imageLogo3.setHeight(htWH!=null?(Integer)htWH.get("Height"):229);
						
					}else{
						
						imageLogo3=new ImagesBean();
						imageLogo3.setWidth(htWH!=null?(Integer)htWH.get("Width"):445);
						imageLogo3.setHeight(htWH!=null?(Integer)htWH.get("Height"):305);
						
					}
					
					origFilePath = (proyecto.getLogo3()).substring(0, (proyecto.getLogo3()).lastIndexOf("."));
					formatFile = (proyecto.getLogo3()).substring((proyecto.getLogo3()).lastIndexOf("."));
					
					thumbFile = new File(destination+origFilePath+"_thumb"+formatFile);
					//origFile = new File(destination+proyecto.getLogo());
					//Si no existe el archivo thumb se crea
					if(!thumbFile.exists()){
						//Se crea el archivo temporal.
						Utilities.copyFile(thumbFile.getPath(), new FileInputStream(fileLogo.getPath()));
						
						//Redimenciona y guarda img
						ImageUtils.resizeAndSaveImg(thumbFile.getPath());
					}
					
					imageLogo3.setUrlImgThumb(origFilePath+"_thumb"+formatFile);
					origFilePath = "";
					formatFile = "";
					thumbFile = null;
					
					imageLogo3.setUrlImg(proyecto.getLogo3());
					imageLogo3.setThisSave(true);
				}else
					imageLogo3=null;
			}else
				imageLogo3=null;
			
			
		}
	}
				
	public void loadProyectList()
	{		
		if(actualProyect!=null)
		{		
			lstPL = service_proyect_loan.getProyectLoanListByProspectus(actualProyect.getProyectloanPk().getProspectus_id(),actualProyect.getProyectloanPk().getCompany_id());
			
			lstPry = new ArrayList<ProyectBean>();
			
			for( ProyectLoan pl : lstPL){
			
				if(!pl.equals(actualProyect)){
					
					ProyectBean pyb = new ProyectBean();
					
					pyb.setCompany_id(pl.getProyectloanPk().getCompany_id());
	//				pyb.setImgHeight(imgHeight);
	//				pyb.setImgWidth(imgWidth);
					pyb.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					pyb.setProyect_id(pl.getProyectloanPk().getProyect_id());
					pyb.setProyect_loan_id(pl.getProyectloanPk().getProyect_loan_id());
					//pyb.setProyectFecPubli(proyectFecPubli);
					pyb.setProyectMonto(dec.format(pl.getAmmount()));
					pyb.setProyectName(pl.getProyect().getName());
					pyb.setPlazo(pl.getTerm().getName() + " "+ pl.getFrequency().getName());
					pyb.setRiesgo_kubo(pl.getKubo_score_a()+pl.getKubo_score_b());
					pyb.setSafi_credit_id(pl.getSafi_credit_id()==null?"No otorgado":pl.getSafi_credit_id());
					pyb.setStatus(pl.getStatus_id());
					
	//				pyb.setUrlImg(urlImg);
					
					pyb.setStatusName(pl.getStatusProyect().getName());
					pyb.setStatusUrl_img(pl.getStatusProyect().getUrl_img());
					
					pyb.setLoanTypeDesc((pl.getLoantype()!=null?pl.getLoantype().getLoan_type_desc():""));
					
					lstPry.add(pyb);
			
				}
			
			}
		}
	}	
		
	@SuppressWarnings("unused")
	public Hashtable<String, Integer> getDimImg(File fOrigen) 
	{
		try {
			BufferedImage img = ImageIO.read(fOrigen);
			int xac = (int) img.getWidth();
			int yac = (int) img.getHeight();

			int x = 150;
			int y = 150;
			double resx = 0;
			double resy = 0;
			double resxy = 0;
			if (xac > x) {
				resx = x / (double) xac;
				double yy = resx * (double) yac;
				y = (int) yy;
				
				resxy = resx;
			} 
			
			if (yac > y) {
				resy = y / (double) yac;
				double xx = resy * (double) xac;
				x = (int) xx;
				resxy = resy;
			}
			Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
			ht.put("Height", y);
			ht.put("Width", x);
			return ht;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}
		
	public void goToLogs(ActionEvent e){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();		
		String valueLog=(String) e.getComponent().getAttributes().get("gotypeLog").toString();
		if (sesion.getArea().equals('M')) {		
			RequestContext requestContext = RequestContext.getCurrentInstance();
			MenuControlTableBean navMenu= (MenuControlTableBean) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "menuControlTableBean");			
			e.getComponent().getAttributes().put("section", "controlTable/Logs::16::menu4");
			navMenu.cambiaPagina(e);
			setLogs_navegation(null);
			
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
			summarysesion.setTypeLog(valueLog);
			requestContext.addPartialUpdateTarget("form_Prin");	
			requestContext.addPartialUpdateTarget("actualPage");
		}else if (sesion.getArea().equals('I')) {
			SearchSummaySession summarysesion = (SearchSummaySession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "searchSummaySession");
				summarysesion.setTypeLog(valueLog);
				
				setLogs_navegation(null);
				
				NavigationInvest navigationinvest = (NavigationInvest) FacesContext.getCurrentInstance()
		                .getApplication().getELResolver()
		                .getValue(elContext, null, "navigationInvest");
				
				e.getComponent().getAttributes().put("seccionInv", "logs");
				navigationinvest.changePage(e);
				
				RequestContext requestContext = RequestContext.getCurrentInstance();
				requestContext.addPartialUpdateTarget("pnlContentInvest");	
				requestContext.addPartialUpdateTarget("panelContentListProyect");
		}
	}
	
	public String actionNavLogs()
	{
		return logs_navegation;
	}

	public void setNullAmmuntMinus()
	{
		if(getConsolidate()==2 && getAmmountConsolidate().getAmmount()>0)
		{
			getAmmountConsolidate().getExpense().setAmmount_minus(null);
			getAmmountConsolidate().setAmmount_modified("0");
			getAmmountConsolidate().setAmmount(0.0);
			getAmmountConsolidate().setExcedentConsolidate(getExcedenteControl()+getAmmountConsolidate().getAmmount());
			
			if(egresosService.updateExpenses(getAmmountConsolidate().getExpense()))
			{
				liquidezCliControl = excedenteControl/pagoMenControl;			
				liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
				getAmmountConsolidate().setWhyChangeData(null);
			}
				
		}
	}
	
	public void updateAmmountMinus()
	{
		if(getAmmountConsolidate()!=null && getAmmountConsolidate().getExpense()!=null && getAmmountConsolidate().getAmmount_modified()!=null && getConsolidate()==1 && getAmmountConsolidate().getWhyChangeData()!=null )
		{
			Double prevMinAmmount=getAmmountConsolidate().getExpense().getAmmount_minus();
			getAmmountConsolidate().setAmmount(Double.parseDouble(getAmmountConsolidate().getAmmount_modified().replace(",", "")));
			getAmmountConsolidate().getExpense().setAmmount_minus(getAmmountConsolidate().getAmmount());
			
			if(getAmmountConsolidate().getAmmount()>0)
			{
				getAmmountConsolidate().setExcedentConsolidate(getExcedenteControl()+getAmmountConsolidate().getAmmount());
				
				if(egresosService.updateExpenses(getAmmountConsolidate().getExpense()))
				{
					// determinamos nueva liquidez
					liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;					
					liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
					//-----------------------------------------------
					if(saveChangeData("gn_expense", "ammount_minus", prevMinAmmount==null?"0":""+prevMinAmmount, ""+getAmmountConsolidate().getAmmount(),getAmmountConsolidate().getWhyChangeData()))
					{
						//getAmmountConsolidate().setWhyChangeData(null);
						List<Change_control> listChangConsolTemp= service_change_control.getListByProspectByAfectedTablesFields
								(persona.getNatPerPK().getProspectus_id(),
								persona.getNatPerPK().getCompany_id(),new String[]{"gn_expense"},new String[]{"ammount_minus"});
						changeConsolidate.setHasChange(true);
						changeConsolidate.setLstChanges(listChangConsolTemp!=null && listChangConsolTemp.size()>0?listChangConsolTemp:null);
					}				
				}
			}
		}
	}
	
	public void editLogoProyect(ActionEvent e)
	{
		String realPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//");
		Integer typeLogo = Integer.parseInt((String) e.getComponent().getAttributes().get("typelogo").toString());
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		String formatFile="";
		String nameFile="";
		Integer typeFileID=typeLogo;
		String pathOriginalImg=null;
		
	if(typeLogo!=null){
		try{		
			switch (typeLogo) {
			case -1:
				pathOriginalImg=realPath+getImageLogo1().getUrlImg();
				break;
				
			case -2:
				pathOriginalImg=realPath+getImageLogo2().getUrlImg();
				break;
			case -3:
				pathOriginalImg=realPath+getImageLogo3().getUrlImg();
				break;

			default:
				break;
			}			
			formatFile= pathOriginalImg.substring(pathOriginalImg.lastIndexOf(".")+1);
		}
		catch (Exception ex) {
			formatFile="jpg";
		}			
	}
	try {				
		nameFile=Utilities.getRandomName()+"."+formatFile;	
		InputStream in=new FileInputStream(new File(pathOriginalImg));
		if(Utilities.copyFile(realPath+"/temp/"+nameFile,in)){				
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			EditImageSession editImg= (EditImageSession) FacesContext.getCurrentInstance()
	                .getApplication().getELResolver()
	                .getValue(elContext, null, "editImageSession");
			editImg.setProspectus_id(persona.getNatPerPK().getProspectus_id());
			editImg.setCompany_id(persona.getNatPerPK().getCompany_id());
			editImg.setOriginalImage(pathOriginalImg);
			editImg.setRotateImage(nameFile);
			editImg.setFormatImage(formatFile);
			editImg.setTypeFileID(typeFileID);
			editImg.setFile_id(null);
			editImg.setProyectLoanID(actualProyect.getProyectloanPk().getProyect_loan_id());
			
			Hashtable<String,Integer> wh=ImageUtils.getWidthAndHeightImg(new File(pathOriginalImg));
			requestContext.addCallbackParam("Width",wh.get("Width"));
			requestContext.addCallbackParam("Height",wh.get("Height"));
		}
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	}
				
	public void calculaPorcCliente(String user){
		
		boolean flagExpenses = false;
		boolean flagTotalExpenses = false;
		Double totalExpenseTemp = 0D;
		
		if( !existlistExpenses() ){
		
			listExpenses = egresosService.getListExpensesByProspect(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
		
		}
		
		listPorcClientBean=new ArrayList<ListPorc>();
		ListPorc listporc;
		for(ExpensesType iterType:listExpensesType){
			
			boolean isEquals=false;
			
			for(Expenses registro4: listExpenses){
				
				if( registro4.getExpense_type_id() != null && registro4.getExpense_type_id() != 14 ){ // GASTOS TOTALES
				
					if(registro4.getExpense_type_id().equals(iterType.getPk().getExpenses_type_id())){
						listporc=new ListPorc();
						listporc.setName(registro4.getExpensesType().getName());
						
							String tmp = num.format(registro4.getAmmount());
							if(tmp.equals("0"))
								tmp = "0";
							if(user.equals("cliente"))
								listporc.setAmmount(registro4.getAmmount());
							if(user.equals("mesacontrol")){
								if(registro4.getAmmount_modified()!=null)
									listporc.setAmmount(registro4.getAmmount_modified());
								else{
									listporc.setAmmount(registro4.getAmmount());
								}
							}
							
							if(listporc.getAmmount()!=null){
								Double d =0.0;
								if(user.equals("cliente"))
									d= (listporc.getAmmount()*100)/totalIncome;
								if(user.equals("mesacontrol"))
									d= (listporc.getAmmount()*100)/totalIncomeControll;
								d = ((double)Math.round((d)*100)/100);
								listporc.setPorcent(d);
							}
						
						isEquals=true;
						listPorcClientBean.add(listporc);
						this.totalExpenses += registro4.getAmmount();
						flagExpenses = true;
					}
					
				}else{
					
					flagTotalExpenses = true;
					totalExpenseTemp = registro4.getAmmount();
					
				}
			}
			
			if(!isEquals){
				listporc=new ListPorc();
				listporc.setName(iterType.getName());
				listporc.setAmmount(0D);
				listporc.setPorcent(0D);
				listPorcClientBean.add(listporc);
			}
			
		}
		
		
		if(!flagExpenses &&  flagTotalExpenses ){
			
			this.totalExpenses = totalExpenseTemp;
			
//			System.out.println("--------- ");
//			System.out.println("----1.1----   "+this.totalExpenses + "  -  " + totalExpenseTemp);
//			System.out.println("--------- ");
//			System.out.println("--------- ");
			
			
		}else if(!flagTotalExpenses && flagExpenses  ) {
			
			ExpensesPK expensesPK  = new ExpensesPK();
			expensesPK.setCompany_id( actualProyect.getProyectloanPk().getCompany_id() );
			expensesPK.setExpense_id(14);
			expensesPK.setProspectus_id( actualProyect.getProyectloanPk().getProspectus_id() );
			
			Expenses totalExpensesObj = new Expenses(); 
			totalExpensesObj.setExpensesPk(expensesPK);
			totalExpensesObj.setAmmount(this.totalExpenses);
			totalExpensesObj.setExpense_type_id(14);
			
			egresosService.addExpenses(totalExpensesObj, actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id() );
			
//			System.out.println("--------- ");
//			System.out.println("----2.1---- ");
//			System.out.println("--------- ");
//			System.out.println("--------- ");
			
		}else if( flagExpenses &&  flagTotalExpenses ){
			this.totalExpenses = totalExpenseTemp;
//			System.out.println("--------- ");
//			System.out.println("----3.1---- ");
//			System.out.println("--------- ");
//			System.out.println("--------- ");
		}else{
//			System.out.println("--------- ");
//			System.out.println("----4.1---- ");
//			System.out.println("--------- ");
//			System.out.println("--------- ");
			this.totalExpenses = totalExpenseTemp;
		}
		
		
		if(user.equals("cliente"))
			porcActionUser="(Cliente)";
		if(user.equals("mesacontrol"))
			porcActionUser="(Mesa de control)";
		dispListPorcWait=false;
		dispListPorc=true;		
		
	}
	
	public void cleanPorcCliente(){
		porcActionUser="";
		dispListPorcWait=true;
		dispListPorc=false;
	}
	
	public boolean isDirectory(String other) {
		String hacia = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
		String path= hacia+"/"+other;
		File file = new File(path);
		return  file.exists();
	}

	public void setQuestionsinForum(){
		PublicForumPK forunPk= new PublicForumPK();
		forunPk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
		forunPk.setProyect_id(actualProyect.getProyectloanPk().getProyect_id());
		PublicForum newPublicForum = new PublicForum();
		
		for(String est: selectedUnrealizedQuestions){		
			newPublicForum.setPublicForumPK(forunPk);
			newPublicForum.setProyect_question_id(Integer.parseInt(est));
			newPublicForum.setQuestion_date(new Date());
			newPublicForum.setProspectus_id(sesion.getProspectus_id());
			foroPublicoService.add(newPublicForum);
		}
		listPublicForum = foroPublicoService.getListPublicForumByProyect(actualProyect.getProyectloanPk().getProyect_id(), actualProyect.getProyectloanPk().getCompany_id());
		numberQuest = listPublicForum.size();
		listUnrealizedQuestions = foroPublicoService.getListUnrealizedQuestions(actualProyect.getProyectloanPk().getProyect_id(), actualProyect.getProyectloanPk().getCompany_id());
	};
	
	public void updatePublicForum(ValueChangeEvent e){
		String publicForumID = (String) e.getComponent().getAttributes().get("publicForumID").toString();
		String value = e.getNewValue().toString();
		
		PublicForumPK id = new PublicForumPK();
		PublicForum publicFor =  listPublicForum.get(0);
		id.setCompany_id(publicFor.getPublicForumPK().getCompany_id());
		id.setProyect_id(publicFor.getPublicForumPK().getProyect_id());
		id.setPublic_forum_id(Integer.parseInt(publicForumID));
		publicFor = foroPublicoService.getPublicForumById(id);
		
		publicFor.setAnswer(value);
		publicFor.setResponse_date(new Date());
		foroPublicoService.updatePublicForum(publicFor);
		
	};
	
	public boolean getReport(Integer prospectus_id){
		
		String realPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//documents//buro//");
		
		File dir = new File(realPath);
		
	
		
		File[] dirs = dir.listFiles();
		 
	    if (dirs != null) {
	        for (int i=0; i < dirs.length; i++) {
 
	            String filename = dirs[i].getName();
	            if(filename.indexOf("Prospecto_"+prospectus_id+"_")!=(-1)){
	            	report = "/documents/buro/"+filename;
	            	return true;
	            }
	        }
	    }
	    report ="";
		return false;
		
	}	
		
/*	
	public void saveChangeIFE(ChangeBean changeife){
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest(); 
		  String ipAddress  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
	        if(ipAddress == null)  
	        {  
	          ipAddress = httpServletRequest.getRemoteAddr();  
	        }
		if(persona!=null && changeife.getWhyChangeData()!=null){
			String originalVal="";
			String newValue="";
			String field="";
			if(changeife.getNameField().equals("mx_ife_cveelector")){
				originalVal=persona.getMx_ife_cveelector()!=null?persona.getMx_ife_cveelector():"";
				newValue=changeife.getNewValue();
				field="mx_ife_cveelector";
				persona.setMx_ife_cveelector(changeife.getNewValue());
			}else if(changeife.getNameField().equals("mx_ife_numemision")){
				originalVal=persona.getMx_ife_numemision()!=null?persona.getMx_ife_numemision().toString():"";
				newValue=changeife.getNewValue();
				field="mx_ife_numemision";	
				persona.setMx_ife_numemision(Integer.parseInt(changeife.getNewValue()));
			}else if(changeife.getNameField().equals("mx_ife_seccion")){
				originalVal=persona.getMx_ife_seccion()!=null?persona.getMx_ife_seccion():"";
				newValue=changeife.getNewValue();
				field="mx_ife_seccion";				
				persona.setMx_ife_seccion(changeife.getNewValue());
			}else if(changeife.getNameField().equals("mx_ife_numvertical")){
				originalVal=persona.getMx_ife_numvertical()!=null?persona.getMx_ife_numvertical():"";
				newValue=changeife.getNewValue();
				field="mx_ife_numvertical";			
				persona.setMx_ife_numvertical(changeife.getNewValue());
			}
			
			service_natural_person.update(getPersona());			
			if(saveChangeData("gn_natural_person", field, originalVal, newValue, changeife.getWhyChangeData())){
				setWhyChangeData(null);
				changeife.setWhyChangeData(null);
				changeDataIFE=new ChangeBean();
				List<Change_control> lstChange_ife=service_change_control.getListByProspectByAfectedTablesFields(persona.getNatPerPK().getProspectus_id(),
						persona.getNatPerPK().getCompany_id(), new String[]{"gn_natural_person"}, new String[]{"mx_ife_cveelector","mx_ife_numemision","mx_ife_seccion","mx_ife_numvertical"});
				changeDataIFE.setHasChange(lstChange_ife!=null && lstChange_ife.size()>0?true:false);
				changeDataIFE.setLstChanges(lstChange_ife!=null && lstChange_ife.size()>0?lstChange_ife:null);
			}
				
			
		}
	}
*/	
	
	public void saveNewBanckDesk()
	{		
		if(persona!=null && claveaccountlst!=null && claveaccountlst.size()>0)
		{
			String preOrgVal= claveaccount.getBank_description();
			
			getClaveaccount().setBank_description(getChangeBankData().getOrigValue());
			
			ClabeAccount clabeAccount = getNewClaveAccount();
			
			getClaveaccount().setMx_clabe(clabeAccount.getMx_clabe());
			getClaveaccount().setBank_id(clabeAccount.getBank_id());
			getClaveaccount().setBank_description(clabeAccount.getBank_description());
			
			clabeaccountservice.updateClabeAccount(getClaveaccount());	
			
			String original_value = preOrgVal != null ? preOrgVal : "";
			String new_value = getClaveaccount().getBank_description();
			String comments = getChangeBankData().getWhyChangeData();
			
			boolean persist_OK = saveChangeData("gn_clabe_account", "bank_description", original_value, new_value, comments);
			
			if(persist_OK)
			{				
				getChangeBankData().setWhyChangeData(null);
				changeBankData.setHasChange(true);
				
				Integer prospectus_id = persona.getNatPerPK().getProspectus_id();
				Integer    company_id = persona.getNatPerPK().getCompany_id();
				
				String [] tables = new String[]{"gn_clabe_account","ln_proyect_loan"};
				String [] fields = new String[]{"bank_description","signature_date","deposit_method_id"};
				
				List<Change_control> lstTemChangeClabe;
				
				lstTemChangeClabe = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, tables, fields);
				
				lstTemChangeClabe = lstTemChangeClabe != null && lstTemChangeClabe.size() > 0 ? lstTemChangeClabe : null;
				
				changeBankData.setLstChanges(lstTemChangeClabe);
			}
			
			
		}else if( getChangeBankData().getOrigValue()!=null && getChangeBankData().getWhyChangeData()!=null){
			String preOrgVal= claveaccount.getBank_description();
			ClabeAccountPK clabePK=new ClabeAccountPK();
			clabePK.setProspectus_id(persona.getNatPerPK().getProspectus_id());
			clabePK.setCompany_id(persona.getNatPerPK().getCompany_id());
			
			
			getClaveaccount().setClabepk(clabePK);
			ClabeAccount clabeAccount = getNewClaveAccount();
			getClaveaccount().setMx_clabe(clabeAccount.getMx_clabe());
			getClaveaccount().setBank_id(clabeAccount.getBank_id());
			getClaveaccount().setBank_description(clabeAccount.getBank_description());
			
			clabeaccountservice.saveClabeAccount(getClaveaccount());		
			
			boolean persist_OK = saveChangeData("gn_clabe_account","bank_description",preOrgVal!=null?preOrgVal:"", getChangeBankData().getOrigValue(), getChangeBankData().getWhyChangeData());
						
			if(persist_OK)
			{				
				claveaccountlst = clabeaccountservice.loadClabeAccountListByProspectus(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
				getChangeBankData().setWhyChangeData(null);
				getChangeBankData().setHasChange(true);
				
				List<Change_control> lstTemChangeClabe=service_change_control.getListByProspectByAfectedTablesFields
						(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{ "gn_clabe_account","ln_proyect_loan"},new String[]{"bank_description","signature_date","deposit_method_id"});
				getChangeBankData().setLstChanges(lstTemChangeClabe!=null && lstTemChangeClabe.size()>0?lstTemChangeClabe:null);
				
			}					
		}		
	}
	
	public void saveNewClabe()
	{
		if(persona!=null && claveaccountlst!=null && claveaccountlst.size()>0 && getChangeBankData().getOrigValue()!=null && getChangeBankData().getWhyChangeData()!=null)
		{
			String preOrgVal= claveaccount.getMx_clabe();
			getClaveaccount().setMx_clabe(getChangeBankData().getOrigValue());
			ClabeAccount clabeAccount=getNewClaveAccount();
			getClaveaccount().setMx_clabe(clabeAccount.getMx_clabe());
			getClaveaccount().setBank_id(clabeAccount.getBank_id());
			getClaveaccount().setBank_description(clabeAccount.getBank_description());
			
			clabeaccountservice.updateClabeAccount(getClaveaccount());
			
			if(saveChangeData("gn_clabe_account", "mx_clabe",preOrgVal!=null?preOrgVal:"", getChangeBankData().getOrigValue(), getChangeBankData().getWhyChangeData()))
			{				
				getChangeBankData().setWhyChangeData(null);
				changeBankData.setHasChange(true);
				List<Change_control> lstTemChangeClabe=service_change_control.getListByProspectByAfectedTablesFields
						(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{"gn_clabe_account","ln_proyect_loan"}, new String[]{"mx_clabe","signature_date","deposit_method_id"});
				changeBankData.setLstChanges(lstTemChangeClabe!=null && lstTemChangeClabe.size()>0?lstTemChangeClabe:null);
			}
			
			
		}else if( getChangeBankData().getOrigValue()!=null && getChangeBankData().getWhyChangeData()!=null){
			String preOrgVal= claveaccount.getMx_clabe();
			ClabeAccountPK clabePK=new ClabeAccountPK();
			clabePK.setProspectus_id(persona.getNatPerPK().getProspectus_id());
			clabePK.setCompany_id(persona.getNatPerPK().getCompany_id());
			
			
			getClaveaccount().setClabepk(clabePK);
			ClabeAccount clabeAccount=getNewClaveAccount();
			getClaveaccount().setMx_clabe(clabeAccount.getMx_clabe());
			getClaveaccount().setBank_id(clabeAccount.getBank_id());
			getClaveaccount().setBank_description(clabeAccount.getBank_description());
			
			clabeaccountservice.saveClabeAccount(getClaveaccount());
			
			if(saveChangeData("gn_clabe_account","mx_clabe",preOrgVal!=null?preOrgVal:"", getChangeBankData().getOrigValue(), getChangeBankData().getWhyChangeData()))
			{				
				claveaccountlst = clabeaccountservice.loadClabeAccountListByProspectus(actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id());
				getChangeBankData().setWhyChangeData(null);
				getChangeBankData().setHasChange(true);
				List<Change_control> lstTemChangeClabe=service_change_control.getListByProspectByAfectedTablesFields
						(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{ "gn_clabe_account","ln_proyect_loan"},new String[]{"mx_clabe","signature_date","deposit_method_id"});
				getChangeBankData().setLstChanges(lstTemChangeClabe!=null && lstTemChangeClabe.size()>0?lstTemChangeClabe:null);
				
			}						
		}			
	}
	
	public final void setListChanges(ChangeBean change)
	{
		faces    = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		showchange = (ShowChangeSession) resolver.getValue(context, null, "showChangeSession");
		
		showchange.setLstChanges(change.getLstChanges());
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
		getChangeBankData().setBank_description(val);
	}
	
	public void validateBank(){
		RequestContext requestContext = RequestContext.getCurrentInstance();		
		requestContext.addCallbackParam("isValid", isValidBank(getChangeBankData().getBank_description()));
	}
	
	public boolean isValidBank(String name){
		boolean flag=false;
		if(name!="" && name!=null){
			Bank b = bankService.getBankByShortName(name);
			if(b!=null)
				flag=true;
			else
				flag=false;
		}else
			flag=false;
		
		return flag;
	}
	
	public ClabeAccount getNewClaveAccount(){
		
		ClabeAccount account = new ClabeAccount();
		
		if( getChangeBankData() != null ){
			
			Bank b = bankService.getBankByShortName(getChangeBankData().getBank_description());
			
			account.setMx_clabe(getChangeBankData().getOrigValue());
			
			if(b != null){
				
				account.setBank_id(b.getBankpk().getBank_id());
				account.setBank_description(b.getShort_name());
				
			}
			
		}
		
		return account;
	}

	public void aceptaCondiciones(){
		
		//System.out.println("aceptaCondiciones");
		
		negotiation.setDate_finished(new Date());
		negotiation.setStatus("A");
		negotiationservice.update(negotiation);
		
		actualProyect.setAmmount(negotiation.getAmmount());
		actualProyect.setFrequency_id(negotiation.getFrequency_id());
		actualProyect.setFunding_type(negotiation.getFunding_type());
		actualProyect.setMx_cat(negotiation.getMx_cat());
		actualProyect.setOpening_payment(negotiation.getOpening_payment());
		actualProyect.setOpening_commission_amount(negotiation.getOpening_commission_amount());
		actualProyect.setPayment(negotiation.getPayment());
		actualProyect.setRate(negotiation.getRate());
		actualProyect.setRate_with_opening(negotiation.getRate_with_opening());
		actualProyect.setTerm_id(negotiation.getTerm_id());

		
		
		service_proyect_loan.update(actualProyect);
		
		callSGB(actualProyect);
		
		dispSendNegotiation = false;
		
		ammountLeft	= num.format(actualProyect.getAmmountLeft());
		ammount = num.format(actualProyect.getAmmount());
		months = actualProyect.getTerm_id();
		switch(actualProyect.getFrequency_id()){
		case 1:
			frequency = "Semanal";
			frequencyStr = "Semanas";
			frequencyStr02 = "semanales";
			break;
		case 2:
			frequency = "Catorcenal";
			frequencyStr = "Catorcenas";
			frequencyStr02 = "catorcenales";
			break;
		case 3:
			frequency = "Quincenal";
			frequencyStr = "Quincenas";
			frequencyStr02 = "quincenales";
			break;
		case 4:
			frequency = "Mensual";
			frequencyStr= "Meses";
			frequencyStr02 = "mensuales";
			break;
		};
		
		payment = num.format(actualProyect.getPayment());
		
		setPagoMen(actualProyect.getPayment());
		
		
		switch (negotiation.getFrequency_id()){
		case 1://semanal
			pagoMen = pagoMen*4;
			break;
		case 2://catorcenal
			pagoMen = pagoMen*2;
			break;
		case 3: //quincenal
			pagoMen = pagoMen*2;
			break;
		case 4://mensual
			break;
		}
		
		liquidezCli = excedente/pagoMen;
		
		liquidezCli = (double)Math.round((liquidezCli)*100)/100;
		
		if(liquidezReq!=null&&liquidezReq<liquidezCli){
			dispOKCl=true;
			dispWarnCl=false;
		}else{
			dispOKCl=false;
			dispWarnCl=true;
		}
		dispBotCondiciones=false;
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		        
/*		
		indice = (IndicePagoDeudasIMP) resolver.getValue(context, null, "indice_pago_deudas");
*/		
		indice = new IndicePagoDeudasIMP();
		indice.init();
	}

	public void modificaCondiciones(){
		
		realizaSimulacion();
		
		if(hasNegotiation){
			negotiationservice.update(negotiation);
			hasNegotiation = true;    /////se agregaron estas lineas
			dispBotCondiciones = true;  /////se agregaron estas lineas
		}else{
			negotiationservice.saveLoanNegotiation(negotiation);
			hasNegotiation = true;
			dispBotCondiciones = true;
		}
		
		montoIni = negotiation.getAmmount();
		termInt  = negotiation.getTerm_id();
		freqIni  = negotiation.getFrequency_id();
		pagoIni  = negotiation.getPayment();
		
		setPagoMenIni(negotiation.getPayment());
		
		
		switch (negotiation.getFrequency_id()){
		case 1://semanal
			pagoMenIni = pagoMenIni*4;
			break;
		case 2://catorcenal
			pagoMenIni = pagoMenIni*2;
			break;
		case 3: //quincenal
			pagoMenIni = pagoMenIni*2;
			break;
		case 4://mensual
			break;
		}
		
		//Si cuenta con monto de consolidacion
		if(getAmmountConsolidate().getAmmount()!=null && getAmmountConsolidate().getAmmount()>0){
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenIni;
		}else{//Si no cuenta con mont de consolidacion
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = excedenteControl/pagoMenIni;
		}		
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
		
		liqIni = liquidezCliControl;
		
		dispSendNegotiation = true;
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		        
/*
		indice_pago_deudas = (IndicePagoDeudasIMP) resolver.getValue(context, null, "indice_pago_deudas");
*/		
		indice = new IndicePagoDeudasIMP();
		indice.init();
		
	}
	
	public void setIniciales()
	{
		if(negotiation == null){
			
			negotiation = new LoanNegotiation();
			
			LoanNegotiationPK lnpk =  new LoanNegotiationPK();
			
			lnpk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
			lnpk.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
			lnpk.setProyect_id(actualProyect.getProyectloanPk().getProyect_id());
			lnpk.setProyect_loan_id(actualProyect.getProyectloanPk().getProyect_loan_id());
			
			negotiation.setPk(lnpk);
			
			negotiation.setAmmount(actualProyect.getAmmount());
			negotiation.setDays_online(actualProyect.getDays_online());
			negotiation.setFrequency_id(actualProyect.getFrequency_id());
			negotiation.setFunding_type(actualProyect.getFunding_type());
			negotiation.setKubo_score_a(actualProyect.getKubo_score_a());
			negotiation.setKubo_score_b(actualProyect.getKubo_score_b());
			negotiation.setMin_ammount(actualProyect.getMin_ammount());
			negotiation.setMx_cat(actualProyect.getMx_cat());
			negotiation.setOpening_commission_amount(actualProyect.getOpening_commission_amount());
			negotiation.setOpening_payment(actualProyect.getOpening_payment());
			negotiation.setPayment(actualProyect.getPayment());
			negotiation.setProspectus_id_proposed(sesion.getProspectus_id());
			negotiation.setRate(actualProyect.getRate());
			negotiation.setRate_with_opening(actualProyect.getRate_with_opening());
			
			
			negotiation.setStatus(null);
			negotiation.setTerm_id(actualProyect.getTerm_id());
			
		}
		
		pagoMenControl = pagoMenIni;
		liquidezCliControl = liqIni;
		negotiation.setAmmount(montoIni);
		negotiation.setTerm_id(termInt);
		negotiation.setFrequency_id(freqIni);
		negotiation.setPayment(pagoIni);
		
		negotiation.setRate(actualProyect.getRate());
		
		setMontoNegotiation(num.format(montoIni));
		
		//Si cuenta con monto de consolidacion
		if(getAmmountConsolidate().getAmmount()!=null && getAmmountConsolidate().getAmmount()>0){
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenIni;
		}else{//Si no cuenta con mont de consolidacion
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = excedenteControl/pagoMenIni;
		}
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
		
		if(liquidezReq!=null&&liquidezReq<liquidezCliControl){
			dispOKControl=true;
			dispWarnControl=false;
		}else{
			dispOKControl=false;
			dispWarnControl=true;
		}
		
		realizaSimulacion();
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
				
		requestContext.addCallbackParam("monto_inicial", getMontoNegotiation());
		requestContext.addCallbackParam("plazo", termInt);
		
	}
	
	public void realizaSimulacion(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		Simulator simulator 
	    = (Simulator) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "simulator");
		
		simulator.setFrequency_id(negotiation.getFrequency_id());
		
		simulator.generaNumCuotas();
		
		negotiation.setAmmount(Double.parseDouble(montoNegotiation.replace(",", "").replace("$","")));
		
		simulator.setAmmount(negotiation.getAmmount());
		simulator.setTerm_id(negotiation.getTerm_id());
		
		simulator.setTasaTotal(negotiation.getRate());		
		
		simulator.setComisionApertura(actualProyect.getOpening_commission());
		
		simulator.generaMontoCuota2();
		
		negotiation.setMx_cat(simulator.getCat());
		negotiation.setPayment(simulator.getMontoCuota());
		negotiation.setTotal_payment(simulator.getTotalPagar());
		
		negotiation.setMin_ammount(negotiation.getAmmount());
		
		Double c = (actualProyect.getOpening_commission()==null?5:actualProyect.getOpening_commission())/100;
		Double comision = c * negotiation.getAmmount();
		comision = ((double)Math.round((comision)*100)/100);
		
		negotiation.setOpening_commission_amount(comision);
		
		setPagoMenControl(negotiation.getPayment());
		
		
		switch (negotiation.getFrequency_id()){
			case 1://semanal
				pagoMenControl = pagoMenControl*4;
				break;
			case 2://catorcenal
				pagoMenControl = pagoMenControl*2;
				break;
			case 3: //quincenal
				pagoMenControl = pagoMenControl*2;
				break;
			case 4://mensual
				break;
		}
		
		//Si cuenta con monto de consolidacion
		if(getAmmountConsolidate().getAmmount()!=null && getAmmountConsolidate().getAmmount()>0){
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = getAmmountConsolidate().getExcedentConsolidate()/pagoMenControl;
		}else{//Si no cuenta con mont de consolidacion
			ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
			liquidezCliControl = excedenteControl/pagoMenControl;
		}		
		
		liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
		
		if(liquidezReq!=null&&liquidezReq<liquidezCliControl){
			dispOKControl=true;
			dispWarnControl=false;
		}else{
			dispOKControl=false;
			dispWarnControl=true;
		}
	}

	public PersonAndPhones existPerson(PhoneReview phRev,int company_id,int prospectus_id){
		
		for(PersonAndPhones perPhone : phRev.getLstPersonPhone()){
			if((perPhone.getPerson().getNatPerPK().getCompany_id() == company_id) && (perPhone.getPerson().getNatPerPK().getProspectus_id() == prospectus_id)){
				return perPhone;
			}
		}
		return null;
	}
		
	public void changeMeansOfPayment(){		
		String orgVal=actualProyect.getDeposit_method_id().equals(1)?"Cheque":"Transferencia";
		String newVal=actualProyect.getDeposit_method_id().equals(1)?"Transferencia":"Cheque";
		if(service_proyect_loan.update(getActualProyect())){
			if(saveChangeData("ln_proyect_loan","deposit_method_id",orgVal,newVal, "Se modifico medio de pago")){
				getChangeBankData().setHasChange(true);
				List<Change_control> lstTemChangeDataBank=service_change_control.getListByProspectByAfectedTablesFields
						(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{ "gn_clabe_account","ln_proyect_loan"},new String[]{"mx_clabe","signature_date","deposit_method_id"});
				getChangeBankData().setLstChanges(lstTemChangeDataBank!=null && lstTemChangeDataBank.size()>0?lstTemChangeDataBank:null);
			}
		}

	}	
	
	public void changeSignatureDate(DateSelectEvent event){
		//guardar cambios en change control y actualizar datos.
		if(event!=null){
			SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
			String prevSignatureDate=actualProyect.getSignature_date()==null?"No definido":formatoDeFecha.format(actualProyect.getSignature_date());
			actualProyect.setSignature_date(event.getDate());
				if(service_proyect_loan.update(getActualProyect())){
					if(saveChangeData("ln_proyect_loan","signature_date",prevSignatureDate!=null?prevSignatureDate:"",formatoDeFecha.format(actualProyect.getSignature_date()), "Se establecio fecha de desembolso")){
						getChangeBankData().setHasChange(true);
					List<Change_control> lstTemChangeDataBank=service_change_control.getListByProspectByAfectedTablesFields
							(persona.getNatPerPK().getProspectus_id(), persona.getNatPerPK().getCompany_id(),new String[]{ "gn_clabe_account","ln_proyect_loan"},new String[]{"mx_clabe","signature_date","deposit_method_id"});
					getChangeBankData().setLstChanges(lstTemChangeDataBank!=null && lstTemChangeDataBank.size()>0?lstTemChangeDataBank:null);
				}
			}
		}
	}
					
	public boolean callSGB(ProyectLoan pl){
		try{
			
			WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
			WsSgbRisk service = locator.getWsSgbRisk();
			ServiceCalling srvCall = new ServiceCalling();
			WsSgbResponse res1 =  new WsSgbResponse();
			WsSgbResponse res2 =  new WsSgbResponse();
			WsSgbResponse res3 =  new WsSgbResponse();
			WsSgbResponse res4 =  new WsSgbResponse();
			
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
			srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(1);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//service.updateCredit(userId, projectLoanId, prospectId, companyId, varId, newValue);
			
			res1 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "1" , pl.getAmmount()+"");
			res2 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "2" , pl.getFrequency().getName()+"");
			res3 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "3" , pl.getTerm_id()+"");
			res4 = service.updateCredit(sesion.getProspectus_id()+"", pl.getProyectloanPk().getProyect_loan_id()+"", pl.getPerson().getNatPerPK().getProspectus_id()+"", pl.getPerson().getNatPerPK().getCompany_id()+"", "4" , pl.getOpening_commission()+"");
			
			if(res1.getStatus().equals("0") && res2.getStatus().equals("0") && res3.getStatus().equals("0") && res4.getStatus().equals("0") ){
				srvCall = new ServiceCalling();
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
				srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
				srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
				srvCall.setStatus(2);
				
				servicecallingService.saveServiceCall(srvCall);
				
			}else{
				
				if(res1.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (monto) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar el MONTO EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res2.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (FRECUENCIA) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					srvCall.setException(res1.getMessage());
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar FRECUENCIA EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(3);
					srvCall.setException(res2.getMessage());
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res3.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (PLAZO) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar PLAZO EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setException(res3.getMessage());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				if(res4.getStatus().equals("0")){
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Regresando satisfactoriamente de Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit (ComisionApertura) ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setStatus(2);
					servicecallingService.saveServiceCall(srvCall);
					
				}else{
					
					srvCall = new ServiceCalling();
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
					srvCall.setInfo("Error al modificar ComisionApertura EN Web WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit ");
					srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
					srvCall.setException(res4.getMessage());
					srvCall.setStatus(3);
					servicecallingService.saveServiceCall(srvCall);
					
				}
				
				
			}
			
			
		}catch(Exception e){
			
			ServiceCalling srvCall = new ServiceCalling();
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(pl.getProyectloanPk().getCompany_id());
			srvCall.setInfo("Error al invocar WsSgbRiskServiceLocator.getWsSgbRisk.updateCredit");
			srvCall.setProspectus_id(pl.getProyectloanPk().getProspectus_id());
			srvCall.setStatus(3);
			srvCall.setException(e.getMessage());
			servicecallingService.saveServiceCall(srvCall);
			
		}
		
		return true;
		
	}

	public void showOtherIncome(){
		//System.out.println("entra a showOtherIncome");
			this.displayPnlOtherIncome = !this.displayPnlOtherIncome;
			
			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.addPartialUpdateTarget(":frmQuest:lstIncometb");
			
		//System.out.println("regresando: "+displayPnlOtherIncome);
		
		
	}
	
	public void validaRegistrationReason()
	{
		boolean flag = false;
		
		registrationReason = "";
		
		if(member !=null && member.getRegistration_reason()!=null)
		{
			if(member.getRegistration_reason_id()!=null && member.getRegistration_reason_id() == 7)
			{ 
				//Otro
			
				//registrationReason = membership.getRegistration_reason().getName();
				
					if(member.getOther_registration_reason()!=null)
					{
						flag = true;
						registrationReason = " "+member.getOther_registration_reason();
					}
			
			
			} else if(member.getRegistration_reason_id()!=null && member.getRegistration_reason_id() == 3){
			
				if( referredIni == null ){
				
					if(member.getWho_recommends()!=null)
					{
						flag = true;
						registrationReason = " Recomendado por "+member.getWho_recommends();
					} else {
						flag = true;
						registrationReason = " "+member.getRegistration_reason().getName();
					}
				
				}
			
			} else if(member.getRegistration_reason_id()!=null && member.getRegistration_reason_id() == 8) { //PriceShoes
			
				flag = true;
				
				registrationReason = " "+member.getRegistration_reason().getName();
				
				if(member.getPriceshoes_number()!=null && member.getPriceshoes_number().trim().length() >0 )
				{					
					registrationReason = " con numero de socio "+member.getPriceshoes_number();					
				}
				
			} else if(member.getRegistration_reason() != null && member.getRegistration_reason_id() != 6 ) {
			
				flag = true;
				
				registrationReason = " "+member.getRegistration_reason().getName();			
			}
		
			if(member.getPromotor_id()!=null && member.getPromotor_id()>0 )
			{ //Promotor			
				if(member.getPromotor()!=null)
				{				
					if(flag )
					{				
						registrationReason += " asignado al Promotor "+member.getPromotor().getName();
						
					} else {
						
						registrationReason = " Promotor "+member.getPromotor().getName();					
					}				
				}
				
			} else {
				
				registrationReason += " sin promotor asignado ";			
			}	
			
			if(referredIni != null){
				registrationReason += " Recomendado por  "+referredIni.getMembership().getPerson().NombreCompletoNPM();
			}
		}	
	}

	public void loadRestructureBean(){
/*		
		RestructureBean restructure = new RestructureBean();
		
		restructure.setProyect(actualProyect);
		
		
		
		if( posicionInt[i].getFechainicio()!=null ){
			
			String fec = formatStr.format( fm1.parse( posicionInt[i].getFechainicio() ) );
			credit.setFecInicio( fec );
			restructure.setFechaInicio(fec);
		}
		
		if( posicionInt[i].getFechavencimien()!=null ){
			
			String fec = formatStr.format( fm1.parse( posicionInt[i].getFechavencimien() ) );
			credit.setFecFin( fec );
			restructure.setFechaFin( fec );
			
		}
		
		Double saldoLiquidar = 0D;
		
		Double saldocapvigent = ((posicionInt[i].getSaldocapvigent())==null?0:Double.parseDouble(posicionInt[i].getSaldocapvigent()));
		Double saldointerprovi = ((posicionInt[i].getSaldointerprovi())==null?0:Double.parseDouble(posicionInt[i].getSaldointerprovi()));
		Double saldocapatrasad = ((posicionInt[i].getSaldocapatrasad())==null?0:Double.parseDouble(posicionInt[i].getSaldocapatrasad()));
		Double saldointeratras = ((posicionInt[i].getSaldointeratras())==null?0:Double.parseDouble(posicionInt[i].getSaldointeratras()));
		Double saldomoratorios = ((posicionInt[i].getSaldomoratorios())==null?0:Double.parseDouble(posicionInt[i].getSaldomoratorios()));
		Double saldcomfaltpago = ((posicionInt[i].getSaldcomfaltpago())==null?0:Double.parseDouble(posicionInt[i].getSaldcomfaltpago()));
		
		Double ivaD = (saldointerprovi*iva) + (saldointeratras*iva)  + (saldomoratorios*iva) + (saldcomfaltpago*iva);
		
		saldoLiquidar = saldocapvigent + 
						saldointerprovi + 
						saldocapatrasad + 
						saldointeratras + 
						saldomoratorios + 
						saldcomfaltpago + ivaD + 15;
		
		String ivaS = dec.format( ivaD );
		String saldoLiquidarStr = dec.format(saldoLiquidar);
		
		credit.setSaldoLiquidar(saldoLiquidarStr);
		
		restructure.setSaldoLiquidar(saldoLiquidar);
		*/
	}
	
	public void changeRegistationReason(){
		
//		System.out.println( member.getRegistration_reason_id() );
//		System.out.println( member.getPromotor_id() ); // 6
//		System.out.println( member.getWho_recommends() ); // 3
//		System.out.println( member.getPriceshoes_number() ); // 8
//		System.out.println( member.getOther_registration_reason() ); // 7
		
		if( member.getPromotor_id() == null || member.getPromotor_id() == 0 )
			member.setPromotor_id(null);
		
		switch (member.getRegistration_reason_id()) {
			case 3:
				
				member.setPriceshoes_number(null);
				member.setOther_registration_reason(null); // 7
				break;
			case 6:
				member.setWho_recommends(null); // 3
				member.setPriceshoes_number(null) ; // 8
				member.setOther_registration_reason(null); // 7
				
				break;
			case 7:
				
				member.setPromotor_id(null); // 6
				member.setWho_recommends(null); // 3
				member.setPriceshoes_number(null) ; // 8
				
				break;
			case 8:
				
				member.getPromotor_id(); // 6
				member.getWho_recommends() ; // 3
				member.getOther_registration_reason(); // 7
				
				break;
		}
		
		if(member.getWho_recommends() != null && member.getWho_recommends().trim().length()<1 )
			member.setWho_recommends( null ) ; // 3
		
		if(member.getOther_registration_reason() != null && member.getOther_registration_reason().trim().length()<1 )
			member.setOther_registration_reason( null ); // 7
		
		if(member.getPriceshoes_number() != null && member.getPriceshoes_number().trim().length()<1 )
			member.setPriceshoes_number(null);
		
//		System.out.println( member.getRegistration_reason_id() );
//		System.out.println( member.getPromotor_id() );
//		System.out.println( member.getWho_recommends() );
//		System.out.println( member.getPriceshoes_number() );
//		System.out.println( member.getOther_registration_reason() );
		//edrfgh
	}
	
	public void savePromotorData(){
		
//		System.out.println( member.getRegistration_reason_id() );
//		System.out.println( member.getPromotor_id() );
//		System.out.println( member.getWho_recommends() );
//		System.out.println( member.getPriceshoes_number() );
//		System.out.println( member.getOther_registration_reason() );
		
		service_membership.update(member);
		
		member = service_membership.getMembershipById(member.getMembershipPK());
		
		validaRegistrationReason();
		
		if(member.getPromotor_id() != membershipTemp.getPromotor_id() ){
		
			saveChangeData("ln_membership","promotor_id",membershipTemp.getPromotor()==null?"Sin Promotor":membershipTemp.getPromotor().getName()+"",member.getPromotor()==null?"Sin Promotor":member.getPromotor().getName()+"",whyChangeDataPromotor);
		}
		
		if(member.getRegistration_reason_id() != membershipTemp.getRegistration_reason_id() ){
			
			saveChangeData("ln_membership","registration_reason_id",membershipTemp.getRegistration_reason()==null?"Sin Dato":membershipTemp.getRegistration_reason().getName()+"",membershipTemp.getRegistration_reason()==null?"Sin Dato":member.getRegistration_reason().getName()+"",whyChangeDataPromotor);
		}
		
		if(member.getPriceshoes_number() != membershipTemp.getPriceshoes_number() ){
			
			saveChangeData("ln_membership","priceshoes_number",membershipTemp.getPriceshoes_number()==null?"Sin Dato":membershipTemp.getPriceshoes_number()+"",member.getPriceshoes_number()==null?"Sin dato":member.getPriceshoes_number()+"",whyChangeDataPromotor);
		}
		
		
membershipTemp = new Membership();
		
		membershipTemp.setAccept_subscribe(member.getAccept_subscribe());
		membershipTemp.setActivation_code(member.getActivation_code());
		membershipTemp.setActivation_date(member.getActivation_date());
		membershipTemp.setAnswer(member.getAnswer());
		membershipTemp.setContract(member.getContract());
		membershipTemp.setCreation_date(member.getCreation_date());
		membershipTemp.setEmail(member.getEmail());
		membershipTemp.setFailed_login_attempts(member.getFailed_login_attempts());
		membershipTemp.setFromwhere_id(member.getFromwhere_id());
		membershipTemp.setFromwhere_other(member.getFromwhere_other());
		membershipTemp.setFromWhereCat(member.getFromWhereCat());
		membershipTemp.setIs_blocked(member.getIs_blocked());
		membershipTemp.setIs_active(member.getIs_active());
		membershipTemp.setLast_login_attempt(member.getLast_login_attempt());
		membershipTemp.setMembershipPK(member.getMembershipPK());
		membershipTemp.setNickname(member.getNickname());
		membershipTemp.setOther_registration_reason(member.getOther_registration_reason());
		membershipTemp.setPassword(member.getPassword());
		membershipTemp.setPerson(member.getPerson());
		membershipTemp.setPriceshoes_number(member.getPriceshoes_number());
		membershipTemp.setPromotor(member.getPromotor());
		membershipTemp.setPromotor_id(member.getPromotor_id());
		membershipTemp.setRegistration_reason(member.getRegistration_reason());
		membershipTemp.setRegistration_reason_id(member.getRegistration_reason_id());
		membershipTemp.setSecurity_question_id(member.getSecurity_question_id());
		membershipTemp.setSecurityQuestion(member.getSecurityQuestion());
		membershipTemp.setWho_answered_id(member.getWho_answered_id());
		membershipTemp.setWho_answered_other(member.getWho_answered_other());
		membershipTemp.setWho_recommends(member.getWho_recommends());
		membershipTemp.setWhoAnswerCat(member.getWhoAnswerCat());
		
		
		changeReasons=new ChangeBean();
		changeReasons.setOrigValue(member.getRegistration_reason_id()+"");
		changeReasons.setNameTable("ln_membership");
		changeReasons.setNameField("registration_resaon_id");
		List<Change_control> listChangeReason= service_change_control.getListByProspectByAfectedTablesFields
				(persona.getNatPerPK().getProspectus_id(),
				persona.getNatPerPK().getCompany_id(),new String[]{"ln_membership"},new String[]{"promotor_id","registration_reason_id","priceshoes_number"});
		changeReasons.setHasChange(listChangeReason!=null && listChangeReason.size()>0?true:false);
		changeReasons.setLstChanges(listChangeReason!=null && listChangeReason.size()>0?listChangeReason:null);
		
		
	}
		
	public void saveNewSingDate(){
		
		saveChangeData("ln_proyect_loan", "signature_date",actualProyect.getSignature_date()==null?"No proporcionado":actualProyect.getSignature_date().toString(), changeSignatureDate.toString() , "Cambio de fecha de desembolso");
		
		actualProyect.setSignature_date(changeSignatureDate);
		service_proyect_loan.update(actualProyect);
		
	}
	
	public void generateDate(){
		//System.out.println(day+"/"+month+"/"+year +" "+hh+":"+mm+":00"+ampm);
		String sM=null;
		if (month != null && month.length() > 0
				&& !month.equals("0")) {
			for (int i = 0; i < monthStr.length; i++) {
				if ((monthStr[i]).equals(month)) {
					if ((i + 1) < 10)
						sM = "0" + (i + 1);
					else
						sM = (i + 1) + "";
				}
			}
			
		}
		
		if(day != null && sM!= null && year != null && hh != null &&  mm != null && ampm != null){
			
			SimpleDateFormat fm =  new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
			
			try{
				
				changeSignatureDate = fm.parse(day+"/"+sM+"/"+year +" "+hh+":"+mm+":00 "+ampm);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void newConsultation()
	{		
		try
		{
			WsSgbRiskServiceLocator locator;
			WsSgbRisk 				service;
			ServiceCalling          srvCall;
			
			locator = new WsSgbRiskServiceLocator();
			service = locator.getWsSgbRisk();						
			srvCall = new ServiceCalling();
			
			srvCall.setAcces_datetime(new Date());
			srvCall.setCompany_id(member.getMembershipPK().getCompany_id());
			srvCall.setInfo("Invocando Servicio Web consulta a buró manual getProspectBCRisk SGB");
			srvCall.setProspectus_id(member.getMembershipPK().getProspectus_id());
			srvCall.setStatus(1);
			
			servicecallingService.saveServiceCall(srvCall);
			
			//consulta a Buro de forma manual
			
			int age = member.getPerson().getEdad();
			
			BCRiskRequest request = new BCRiskRequest();
			
			request.setAge(age+"");
			request.setBursolnum(actualProyect.getMx_solicitud_buro());
			request.setBusinessType("3227");
			request.setCompanyId(member.getPerson().getNatPerPK().getCompany_id()+"");
			
			if(changeBurSolNum)
			{
				request.setEventId(Evento.CONSULTA_BC_MANUAL.getId()+"");
			} else {
				request.setEventId(Evento.CONSULTA_BC_MANUAL_SEGUIMIENTO.getId()+"");
			}
			
			request.setGender((member.getPerson().getGender_id()+""));
			request.setHomeType(member.getPerson().getResidence_id()+"");
			request.setProspectId(member.getPerson().getNatPerPK().getProspectus_id()+"");
			request.setProspectIdTemp(sesion.getProspectus_id()+"");
			
			ProspectBCRiskResponse bcRes =  service.getProspectBCRisk(request);
			
			// ProspectBCRiskResponse bcRes =  service.getProspectBCRisk((member.getMembershipPK().getProspectus_id()+""), (member.getPerson().getResidence_id()+""), (member.getPerson().getGender_id()+""), (age+""), "3227");
			
			if(bcRes != null)
			{				
				
	//			if(bcRes.getStatus().equals("0")){
	
					srvCall = new ServiceCalling();
					
					srvCall.setAcces_datetime(new Date());
					srvCall.setCompany_id(member.getMembershipPK().getCompany_id());
					srvCall.setInfo("Regresando Satisfactorioamente del Servicio Web consulta a buró manual getProspectBCRisk SGB");
					srvCall.setProspectus_id(member.getMembershipPK().getProspectus_id());
					srvCall.setStatus(2);
						
					servicecallingService.saveServiceCall(srvCall);	
					
					Scoring scoringResult = new Scoring();
					
					scoringResult.setScoring_result_id((scoreService.getMaxScoringResultID())+1);
					scoringResult.setCompany_id(member.getMembershipPK().getCompany_id());
					scoringResult.setProspectus_id(member.getMembershipPK().getProspectus_id());
					scoringResult.setResult_datetime(new Date());
					scoringResult.setBc_score(bcRes.getBurScore());
					scoringResult.setMx_folio(bcRes.getBurFol());
					scoringResult.setMx_solicitud_buro(bcRes.getBurSolNum()); 
					
					if( bcRes.getVocalkubo1() != null && bcRes.getVocalkubo1().trim().equals("N") )
					{					
						scoringResult.setKubo_score_a("G");
						scoringResult.setKubo_score_b("1");
						bcRes.setVocalkubo1("G");
						bcRes.setVocalkubo2("1");
						
					} else if( bcRes.getVocalkubo1() != null && bcRes.getVocalkubo1().trim().equals("E") && bcRes.getVocalkubo2() != null && bcRes.getVocalkubo2().trim().equals("7") )
					{					
						scoringResult.setKubo_score_a("F");
						scoringResult.setKubo_score_b("1");
						bcRes.setVocalkubo1("F");
						bcRes.setVocalkubo2("1");
						
					}else{
						
						scoringResult.setKubo_score_a(bcRes.getVocalkubo1()==null?"":bcRes.getVocalkubo1());
						scoringResult.setKubo_score_b(bcRes.getVocalkubo2()==null?"":bcRes.getVocalkubo2());
						
					}
					scoringResult.setRate(Double.parseDouble(bcRes.getTasaAcre()));
					
						scoringResult.setBc_score_date(new Date());
					
					scoringResult.setRate_investor(Double.parseDouble(bcRes.getTasaInv()));
					scoringResult.setCci_score(bcRes.getIcc());
					scoringResult.setOpening_commission(Double.parseDouble(bcRes.getVocomision()));
					scoringResult.setKubo_rate(bcRes.getVotasakubo());
					scoringResult.setRisk_level(bcRes.getVoriesgo());
					scoringResult.setLiquidity(Double.parseDouble(bcRes.getVoliquidez()));

					scoreService.saveScoring(scoringResult);
					
					if(changeBurSolNum)
					{						
						actualProyect.setMx_solicitud_buro(scoringResult.getMx_solicitud_buro());
						actualProyect.setConsulting_date(new Date());
						actualProyect.setRate(scoringResult.getRate());
						actualProyect.setRate_investor(scoringResult.getRate_investor());
						actualProyect.setKubo_score_a(scoringResult.getKubo_score_a());
						actualProyect.setKubo_score_b(scoringResult.getKubo_score_b());
						actualProyect.setLiquidity(scoringResult.getLiquidity());
						actualProyect.setBc_score(Integer.parseInt(scoringResult.getBc_score()));
						actualProyect.setRate_with_opening(scoringResult.getRate());
						actualProyect.setCci_score(scoringResult.getCci_score());
						service_proyect_loan.update(actualProyect);
					}
					
					messageConsultationOn= "Consulta realizada con éxito.";
					messageConsultationOff= "";
					//Bandera de consulta satisfactoria.
					displayConsulSuccess = 1;
					//notificacion(4, score,"",actualProyect);
					//notificacion(Evento.CONSULTA_BC_MANUAL, scoringResult,"",actualProyect);
	//			}else{
	//				
	//				srvCall = new ServiceCalling();
	//				
	//				srvCall.setAcces_datetime(new Date());
	//				srvCall.setCompany_id(member.getMembershipPK().getCompany_id());
	//				srvCall.setInfo("Error en regreso del Servicio Web consulta a buró manual getProspectBCRisk SGB");
	//				srvCall.setException(bcRes.getMessage());
	//				srvCall.setProspectus_id(member.getMembershipPK().getProspectus_id());
	//				srvCall.setStatus(3);
	//					
	//				servicecallingService.saveServiceCall(srvCall);	
	//				
	//			}
				
			}else{
				srvCall = new ServiceCalling();
				
				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(member.getMembershipPK().getCompany_id());
				srvCall.setInfo("Error al realizar la consulta del Servicio Web consulta a buró manual getProspectBCRisk SGB");
				srvCall.setProspectus_id(member.getMembershipPK().getProspectus_id());
				srvCall.setException("Servico getProspectBCRisk regresa objeto null");
				srvCall.setStatus(3);
				
				servicecallingService.saveServiceCall(srvCall);
				
				messageConsultationOn = "";
				messageConsultationOff = "Servicio sin objeto de respuesta";
				//Error en la consulta.
				displayConsulSuccess = 0;
			}
									
			//Fin de consulta a Buro de forma manual 
			}catch(Exception e){
				System.out.println("Error: " + e.getMessage());
				
				Writer writer = new StringWriter();
				PrintWriter printWriter = new PrintWriter(writer);
				e.printStackTrace(printWriter);
				String msg = e.getMessage();
				
				Evento evento = Evento.ERROR_DESARROLLO;
				evento.setError("managedbeans.SummaryRequest.newConsultation(5279): " + msg);
				
				notificar(evento, msg+" <br /> "+writer.toString());
				ServiceCalling srvCall = new ServiceCalling();

				srvCall.setAcces_datetime(new Date());
				srvCall.setCompany_id(member.getMembershipPK().getCompany_id());
				srvCall.setInfo("Error SummaryRequest.newConsultation (5286)");
				srvCall.setProspectus_id(member.getMembershipPK().getProspectus_id());
				srvCall.setStatus(3);
				
				String exception =  e.getStackTrace().toString().length()>990?e.getStackTrace().toString().substring(0,990):e.getStackTrace().toString();
				
				srvCall.setException(exception);
				
				servicecallingService.saveServiceCall(srvCall);
				
			}
			
			cargaConsultas();
	}
	
	public void notificar(Evento evento, String errormsg )
	{
		try 
		{
			notificador.setEmisor(user);
			notificador.setAcreditado(member);
			notificador.notificar(evento,  null, null, errormsg);
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}					
	}
			
	public void incializaEdoctaInv(){
		
		System.out.println(  " Cargando Estado de cuenta del Cliente : " + prospecto.getProspectusPK().getProspectus_id() );
		
		FacesContext facesContext     = FacesContext.getCurrentInstance();
		
		ELContext elContext = facesContext.getELContext();
		MyInvestments myInvestments = (MyInvestments) facesContext.getApplication().getELResolver().getValue(elContext, null, "myInvestments");
		
		
		
		facesContext.getExternalContext().getRequestMap().put("prospectus_id",(prospecto.getProspectusPK().getProspectus_id()+""));
		facesContext.getExternalContext().getRequestMap().put("company_id",(prospecto.getProspectusPK().getCompany_id()+""));
		myInvestments.webService();
		
	}
	
	public void cancelClientAction(ActionEvent e){
			String value=(String) e.getComponent().getAttributes().get("cancelType").toString();
			
			System.out.println("cancelando ... "+value);
			
			MembershipPK mpk = new MembershipPK();
			
			mpk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
			mpk.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
			
			Membership thisMem = service_membership.getMembershipById(mpk);
			
			String originalVal = thisMem.getIs_canceled();
			
			String newValue = "S"+value;
			
			thisMem.setIs_canceled(newValue);
			thisMem.setDate_canceled(new Date());
			
			service_membership.update(thisMem);
			
			validaIsCancel();
			
			saveChangeData("ln_membership", "is_canceled", originalVal, newValue, cancelAccountComment);
			
			System.out.println("membership ... Actualizada "+value);
			
	}
	
	public void activeAccountAction()
	{			
		System.out.println("Activando ... ");
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id(actualProyect.getProyectloanPk().getCompany_id());
		mpk.setProspectus_id(actualProyect.getProyectloanPk().getProspectus_id());
		
		Membership thisMem = service_membership.getMembershipById(mpk);
		
		String originalVal = thisMem.getIs_canceled();
		
		String newValue = "N";
		
		thisMem.setIs_canceled(newValue);
		thisMem.setDate_canceled(null);
		
		service_membership.update(thisMem);
		
		validaIsCancel();
		
		saveChangeData("ln_membership", "is_canceled", originalVal, newValue, cancelAccountComment);
		
		System.out.println("membership ... Actualizada ");		
	}
	
	public void validaIsCancel()
	{		
		faces = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		MenuControlTableBean menu = (MenuControlTableBean) resolver.getValue(context, null, "menuControlTableBean");
				
		MembershipPK mpk = new  MembershipPK();
		
		mpk.setCompany_id ( actualProyect.getProyectloanPk().getCompany_id() );
		mpk.setProspectus_id ( actualProyect.getProyectloanPk().getProspectus_id() );
		
		Membership member = service_membership.getMembershipById(mpk);
		
		if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") )
		{			
			menu.setProspectus_is_canceled( true );
			menu.setCanceledReason(member.getIs_canceled());
			
		} else {
			
			menu.setProspectus_is_canceled( false );
			menu.setCanceledReason("");			
		}		
	}
	
	public void initAlerts(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		AlertsManaged alerts = (AlertsManaged) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "alertsManaged");
		
		alerts.setProspectus_id(prospecto.getProspectusPK().getProspectus_id());
		alerts.setCompany_id(prospecto.getProspectusPK().getCompany_id());
		
		if(actualProyect != null)
			alerts.setPylnPk(actualProyect.getProyectloanPk());
		
		alerts.setProspectus_id_sender(sesion.getProspectus_id());
		
		alerts.setDisplayAlert(displayAlerts);
		
		alertProyect = alerts.haveAlertsProyect();
		alertPerson = alerts.haveAlertsPerson();
		
		
	}
		
	public boolean validaTerm( Integer termInst , Integer creditTerm , Integer freq ){
		
		boolean flagT = false;
		
		Float factor = 0f;
		
			switch (freq){
			
				case 1://Semanal
					
						factor = 52f/12f;
					
					break;
					
				case 2://Catorcenal
					
						factor = 26f/12f;
					
					break;
					
				case 3://Quincenal
						
						factor = 24f/12f;
					
					break;
					
				case 4://Mensual
					
						factor = 12f/12f;
					
					break;
			
			}
			
			Float term = ( factor*creditTerm );
			
			if( termInst < term  ){
				
				flagT = false;
				
			}else{
				flagT = true;
			}
			
		
		return flagT;
		
	}
	
	public void invierteEnProyectoActual(  ActionEvent e )
	{
		// investmentList.invierteList( List<ItemLoanList> listForInv )
		
		System.out.println("Inversion en proceso ---");
		
		List<ItemLoanList> listForInv = new ArrayList<ItemLoanList>();
		
		listForInv.add(itemSel);
		
		Inversion inversion = new Inversion();
		
		inversion.setProyectList(listForInv); //proyectList: Lista de proyectos a invertir
		inversion.setInvestor_id( sesion.getProspectus_id()+"" ); //: prospectus_id del inversionista
		
		List<String> lstAccount = service_proyect_loan .getOnlySAFIAccount( inversion.getInvestor_id() );
		
		inversion.inicializaSaldos(lstAccount);
		
		List<InvestorsAccounts> listInvAccounts = inversion.getListInvAccounts();
		
		if (listInvAccounts != null && listInvAccounts.size() == 1 ){
			
			inversion.setAccount( ( listInvAccounts.get(0) ).getAccount());
			
		}else{
			inversion.setAccount(investmentList.getCuentaActual()); // : safi_account_id del inversionista
		}
		
		
		
		//System.out.println("SummaryRequest Cuenta Actual: " + inversion.getAccount() + "  safi_client_id: " + investmentList.getNaturalPerson().getSafi_client_id() );
		
		inversion.setSafiClientId( investmentList.getNaturalPerson().getSafi_client_id() ); // : safiClientId del inversionista 
		
		inversion.setInvestmentprogress( null );
		inversion.setInvestmentType("Individual");
		
		 inversion.ejecutaInvestment();
		 inversion.ejecutaThreadDespuesDeFondeo();
		 inversion.ejecutaSPRecargaListaClienteCredito( investmentList.getNaturalPerson().getSafi_client_id() );
		 //
		//investmentList.invierteList( listForInv );
		
		 System.out.println( "investmentList.getCreditFounded(): " + inversion.getLstProyectosFondeados().size() );
		 //System.out.println( "investmentList.getAmmountFounded(): " + inversion.getMontoinvertido() );
		 //System.out.println( "investmentList.getInvestmentBite(): " + inversion.getInvestmentBite() );
	    
		 //System.out.println( "investmentList.getCreditNotFounded(): " + inversion.getProyectosNoFondeados() );
		 //System.out.println( "investmentList.getAmmountNotFounded(): " + inversion.getMontoNOinvertido());
		 
		 inversion.inicializaSaldos(lstAccount);
		 
		 saldoActualInv = inversion.getListInvAccounts().get(0).getSaldo();
		 
		 request   = RequestContext.getCurrentInstance();
		 
		 if(inversion.getProyectosNoFondeados() > 0 && inversion.getLstProyectosFondeados().size() == 0)
		 {		
			 request.addCallbackParam("isSuccess", "0");			 			 			 
			 request.addCallbackParam("errorMsg", inversion.getListToInv().get(0).getMotivo());
			
			 
		 } else if( inversion.getLstProyectosFondeados().size() > 0 && inversion.getProyectosNoFondeados() == 0) {
			 
			 request.addCallbackParam("isSuccess", "1");
			 itemSel.setInvestment_bite(0D);
			 investmentList.calculaSaldoActual();
			 investmentList.filteringAfterIndivInversion();
			 //init_inversion_interna();
			 
		 } else {
			 
			 request.addCallbackParam("isSuccess", "2");
			 
		 }		
	}
	
	
	public void asignaBite(AjaxBehaviorEvent e){
		
		//itemSel = (ItemLoanList) e.getComponent().getAttributes().get("proyectloanItem");
		
		
		investmentList.setNewBiteInv(itemSel.getInvestment_bite()+"");
		
		ammounttoInv = (Double)((HtmlInputText) e.getComponent()).getValue() ;
		
		// ammounttoInv = Double.parseDouble( ammounttoInvStr );
		
		itemSel.setInvestment_bite(ammounttoInv);
		
		//itemSel = item;
		
		e.getComponent().getAttributes().put("proyectloanItem",itemSel);
		
		investmentList.setFlagMasivo(false);
		
		investmentList.asignaBite(e);
		
		investmentList.setFlagMasivo(true);
		
		//e.getComponent().getAttributes().put("proyectloanItem",itemSel);
	}


	@SuppressWarnings("unchecked")
	public void validaSession () 
	{		
			boolean flag = false;
			
			faces          = FacesContext.getCurrentInstance();
			ExternalContext external    = faces.getExternalContext();		
			HttpSession sessionUsed = (HttpSession) external.getSession(false);
			ServletContext servlet     = sessionUsed.getServletContext();
			
			Hashtable<String, Hashtable<String,HttpSession>>  ht = (Hashtable<String, Hashtable<String,HttpSession>>)servlet.getAttribute("usuariosFirmados");
			
			Enumeration<String> enumKey = ht.keys();

			while(enumKey.hasMoreElements()) 
			{
				
			   String key = enumKey.nextElement();
			    
			   Hashtable<String,HttpSession> htVal = (Hashtable<String,HttpSession>)ht.get(key);
			    
			    if( htVal.get(prospecto.getTracking_id()) != null )
			    {
			    	flag = true;
			    	
			    	break;
			    }
			    
			}
			
			request   = RequestContext.getCurrentInstance();
			
			request.addCallbackParam("isLogged", flag);
			
	}
	

	public String changeSessionForEditForm(){
		
		faces = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		NavigationBeanIMP navi = (NavigationBeanIMP)resolver.getValue(context, null, "navigationBean");
		
		navi.setPaginaActual("registro/basicdata");
		
		SearchSummaySession search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
		
		String str = search.getSearchSummary();
		
		MembershipPK mpk = new MembershipPK();
		
		mpk.setCompany_id( sesion.getCompany_id()  );
		mpk.setProspectus_id( sesion.getProspectus_id() );
		
		Membership mCoach = service_membership.getMembershipById(mpk);
		
		mpk = new MembershipPK();
		
		mpk.setCompany_id( sesion.getCompany_id() );
		mpk.setProspectus_id( persona.getNatPerPK().getProspectus_id() );
		
		Membership mProspectus = service_membership.getMembershipById(mpk);
		
		HeaderBean headerbean = (HeaderBean)resolver.getValue(context, null, "headerBean");
		
		int i = 0;
		
		do {
			
			try{
			
				Thread.sleep(500);
				
				headerbean.iniciaSesionCoach(mCoach, mProspectus, true);
				
				search = (SearchSummaySession)resolver.getValue(context, null, "searchSummaySession");
				
				search.setSearchSummary( str );
				
				sesion = (SessionBean)resolver.getValue(context, null, "sessionBean");
				
				sesion.setCoachProspectus_id(mCoach.getMembershipPK().getProspectus_id());
				
				sesion.setAccess_from("portalKubo");
				
				i++;
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}while (sesion.getCompany_id() == null && i < 4 );
		
		return "registrocoach";
	
	}
	
	public void sendContract()
	{	
		String uRLContractInvestor = "http://www.kubofinanciero.com/Kubo/Portal/firma.xhtml?numberAcountInvestor=";
		String uRLContractCredit = "http://www.kubofinanciero.com/Kubo/Portal/firma.xhtml?numberCreditId=";
		
		List<SavingAccount> lstSaving =  savingAccountservice.getListAccountByProspect( persona.getNatPerPK().getProspectus_id() , persona.getNatPerPK().getCompany_id());
		
		String safi_account_id = "";
		
		if( lstSaving != null && lstSaving.size() > 0 ){
		
			for( SavingAccount s : lstSaving ){
				
				safi_account_id = s.getSafi_account_id();
				
			}
			
			GeneraURLCorta shortURl = new GeneraURLCorta();
			
			RequestShortURL request = new RequestShortURL();
			
			request.setCompany_id(persona.getNatPerPK().getCompany_id()+"");
			request.setProspectus_id(persona.getNatPerPK().getProspectus_id()+"");
			//request.setLongURL("http://www.kubofinanciero.com/Kubo/Portal/acreditado/preregistro/comenzar-registro.xhtml?selectedReg=s");
			
			if( persona.getProspectus().getArea().toString().equals("I") ){
				request.setLongURL(uRLContractInvestor+safi_account_id);
			}else{
				request.setLongURL(uRLContractCredit+actualProyect.getSafi_credit_id());
			}
			
			ResponseShortURL response =   shortURl.generaConsultaCorta( request );
		
			if( response != null && response.getStatus().equals("0") ){
				
				enviaSMSContract( response.getShortURL() , persona.getNatPerPK().getProspectus_id() );
			}
			
		}else{
			
			//System.out.println( "***  Sin Datos de Cuenta SAFI ***" );
			
		}	
	}
	
	public void fileUploadLastProyect(){
		
		ProyectLoan lastProyectloan = service_proyect_loan.getMaxProyectLoanByProspectAndStatus( actualProyect.getProyectloanPk().getProspectus_id(), actualProyect.getProyectloanPk().getCompany_id(), 3);
		
		ReasignadorIMP reasignador = new ReasignadorIMP();
		
		reasignador.setMembershipservice(service_membership);
		
		reasignador.setFilesService(filesService);
		
		reasignador.setProyect_loan_NEW(actualProyect);
		
		reasignador.init(lastProyectloan);
		
		CopiarArchivosIMP copiar_archivos  = Utilities.findBean("copiar_archivos_service");
		
		reasignador.setCopiar_archivos_service( copiar_archivos );
		
		reasignador.crear_lista_documentos(false);
		
		reasignador.copiar_documentos();
		
		init_documentacion();
		
	}
	
	public final void init_domicilio_actividad()
	{
		request = RequestContext.getCurrentInstance();
		
		init_domicilio(true);
		
		request.addCallbackParam("address_TOKEN", domicilio_actividad.getAddress_TOKEN());
	}
	
	public final void update_editor_identification()
	{
		request = RequestContext.getCurrentInstance();
		
		persona = editor_identification.getPerson();
		
		init_editor_identification();
		
		request.addCallbackParam("editor_ife_ENABLED", editor_identification.isEditor_ife_ENABLED());
	}

	public void aceptarOferta(){
		System.out.println( "aceptarOferta" );
		
		offer.setAnalyst_prospectus_id(sesion.getProspectus_id());
		offer.setAnalyzed_date(new Date());
		offer.setOffer_status_id(1);
		
		offer_service.updateOffer(offer);
		
	}

	public void rechazarOferta(){
		System.out.println( "rechazarOferta" );
		
		offer.setAnalyst_prospectus_id(sesion.getProspectus_id());
		offer.setAnalyzed_date(new Date());
		offer.setWhy_not(offer_why_not);
		offer.setOffer_status_id(2);
		
		offer_service.updateOffer(offer);
		
	}
	
	public String getOfferAnalyst(){
		
		if( offer != null && offer.getAnalyst_prospectus_id() != null ){
		
			gnNaturalPersonPK npk = new gnNaturalPersonPK(offer.getAnalyst_prospectus_id(), sesion.getCompany_id());
			
			NaturalPerson per =  service_natural_person.getNaturalPersonById(npk);
			
			return per.NombreCompletoNPM();
		
		}else{
			return "";
		}
		
	}
	
	private void inicializaReferences(){
		
		
		Map<String, Object> viewMap = faces.getViewRoot().getViewMap();
		ReferenciasPersonalesIMP references = (ReferenciasPersonalesIMP) viewMap.get("referencias_personales");
		
		if( references != null ){
			references.init();
		}
		
		
	}
	
	private void inicializa_prospectus_comment(){
		
		lstcomm = pospectuscommentservice.getPospectusCommentByType(persona.getNatPerPK().getProspectus_id(), actualProyect.getProyectloanPk().getProyect_loan_id() , 1);
		
		blnComment = false;
		
		if( lstcomm != null && lstcomm.size() > 0 ){
		
			blnComment = true;
			
		}
		
	}
	
	private void inicializa_Contact_Way(){
		
		List<ContactWayProspectus> lstCntWayPrs =  contactwayprospectusservice.getContactWayProspectusList( persona.getNatPerPK().getCompany_id(), persona.getNatPerPK().getProspectus_id() );
		
		contactWayPhone = false;
		contactWayWhatsApp = false;
		contactWayEmail = false;
		haveContactWay = false;
		
		if( lstCntWayPrs != null && lstCntWayPrs.size() > 0 ){
			
			for( ContactWayProspectus cntWayPrs : lstCntWayPrs ){
				
				if( cntWayPrs.getPk().getContact_way_id() == 1 ){
					contactWayPhone = true;
					haveContactWay = true;
				}
				if( cntWayPrs.getPk().getContact_way_id() == 2 ){
					contactWayWhatsApp = true;
					haveContactWay = true;
				}
				if( cntWayPrs.getPk().getContact_way_id() == 3 ){
					contactWayEmail = true;
					haveContactWay = true;
				}
				
			}
				
		}
		
	}
	
	
}