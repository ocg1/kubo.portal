package mx.com.kubo.managedbeans.mesa.reportes.buro;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import mx.com.kubo.comparators.BurGraphicComparator;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.buro.Bur_maxoto;
import mx.com.kubo.managedbeans.buro.Bur_spca;
import mx.com.kubo.managedbeans.buro.SaldoDeudas;
import mx.com.kubo.managedbeans.mesa.solicitud.notas.NotasDelCasoIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Bank;
import mx.com.kubo.model.BuroCache;
import mx.com.kubo.model.IdentifiedCredit;
import mx.com.kubo.model.IdentifiedCreditPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONObject;

import com.soa.model.businessobject.BurGraphic;
import com.soa.model.businessobject.BurResume;
import com.soa.model.businessobject.Vtbur_infocnsltms;
import com.soa.model.businessobject.Vtbur_infocnsltult;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocte;
import com.soa.webServices.WsSgbRiskServiceLocator;

@ManagedBean (name = "chartBackBean") @ViewScoped 
public final class ChartBackBeanIMP extends ChartBackBeanAMO 
implements ChartBackBeanIMO, Serializable 
{	
	private static final long serialVersionUID = 4179061942557336552L;

	@PostConstruct
	public void init()
	{		
		Date d1 = new Date();
		Calendar cd_1 = Calendar.getInstance();
		cd_1.setTime(d1);
		
		faces = FacesContext.getCurrentInstance();	
		
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		parameter = external.getRequestParameterMap();
		
		burSolNum        = parameter.get("solNum");		
		promotorIDOfMail = parameter.get("promotorID");
		
		String company = parameter.get("company_id");
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if(company != null && promotorIDOfMail != null)
		{						
			SimpleDateFormat date_format = new SimpleDateFormat("dd / MM / yyyy  hh:mm:ss a");
			
			membership_pk = new MembershipPK(Integer.parseInt(promotorIDOfMail), Integer.parseInt(company));
			
			Membership mesa = membershipservice.getMembershipById(membership_pk);
			NaturalPerson person = mesa.getPerson();
			Prospectus prospecto = person.getProspectus();
			
			String last_login;
			
			if(mesa.getLast_login_attempt() != null)
			{
				last_login = date_format.format(mesa.getLast_login_attempt());
				
			} else {
				
				last_login = date_format.format(new Date());
			}					
			
			sesion.setIsClient(true);
			sesion.setCompany_id   (Integer.parseInt(company));
			sesion.setProspectus_id(Integer.parseInt(promotorIDOfMail));
			
			sesion.setArea   (prospecto.getArea());
			sesion.setUsrImg (prospecto.getImage());
			sesion.setTrackId(prospecto.getTracking_id());
			sesion.setRole_id(prospecto.getRole_id());
						
			sesion.setEmail   (mesa.getEmail());
			sesion.setNickname(mesa.getNickname());
			sesion.setNombre(person.getFirst_name() + " " + person.getFather_last_name());
			sesion.setFecha_acceso_LAST(last_login);
			sesion.setFecha_acceso_ACTUAL(date_format.format(new Date()));
			
			HttpSession http_sesion = (HttpSession) external.getSession(false);
			
			http_sesion.setMaxInactiveInterval(20 * 60);
			
	    	sesion.setMaxTimeInactiveSegundos( 20 * 60);
		} 
		
		is_sesion_ENABLED = (sesion.getProspectus_id() !=null && sesion.getProspectus_id() != 0) && (sesion.getCompany_id() != null && sesion.getCompany_id() != 0);				
		
		if(!is_sesion_ENABLED)
		{
			init_prospecto();
			
		} else {
			
			init_promotor_ENABLED();
			init_promotor_ID();
		}
		
		Date d2 = new Date();
		Calendar cd_2 = Calendar.getInstance();
		cd_2.setTime(d2);
		
		if( sesion.getProspectus_id() != null && sesion.getCompany_id() != null ){
		
			membership_pk = new MembershipPK(sesion.getProspectus_id(), sesion.getCompany_id());
			
			Membership mbs = membershipservice.getMembershipById(membership_pk);
			
			lstMember = new ArrayList<Membership>(); 			 
			
			lstMember.add(mbs);
		
		}
		
		Date d2_1 = new Date();
		Calendar cd_2_1 = Calendar.getInstance();
		cd_2_1.setTime(d2_1);
		
		lstRecommendationType = recommendationservice.getRecommendationTypeLst();
		
		Date d2_2 = new Date();
		Calendar cd_2_2 = Calendar.getInstance();
		cd_2_2.setTime(d2_2);
		
		lstBanks 			  = bankService.searchBankList("",false);
		
		Date d3 = new Date();
		Calendar cd_3 = Calendar.getInstance();
		cd_3.setTime(d3);
		
		if(is_prospectus_OK)
		{					
			initChart();
			maxWidthTableVig = "868";
			
		} else {
			
			initModal = true;
			maxWidthTableVig = "788";
		}
		
		Date d4 = new Date();
		Calendar cd_4 = Calendar.getInstance();
		cd_4.setTime(d4);
		
		long dif_1 = cd_2.getTimeInMillis() - cd_1.getTimeInMillis();
		
		//System.out.println("ChartBackBeanIMP.Inicia Carga Memebership: " + dif_1 + " milisegundos " );
		
		long dif_1_1 = cd_2_1.getTimeInMillis() - cd_2.getTimeInMillis();
		//System.out.println("ChartBackBeanIMP. CARGA getMemberShipForAssignPerfil: " + dif_1_1 + " milisegundos " );
		long dif_1_2 = cd_2_2.getTimeInMillis() - cd_2_1.getTimeInMillis();
		//System.out.println("ChartBackBeanIMP. CARGA getRecommendationTypeLst : " + dif_1_2 +" milisegundos" ) ;
		long dif_1_3 = cd_3.getTimeInMillis() - cd_2_2.getTimeInMillis();
		//System.out.println("ChartBackBeanIMP. bankService : " + dif_1_3 + " milisegundos");
		
		
		
		long dif_2 = cd_3.getTimeInMillis() - cd_2.getTimeInMillis();
		
		//System.out.println("ChartBackBeanIMP.Finaliza Carga Memebership: " + dif_2 + " milisegundos " );
		
		long dif_3 = cd_4.getTimeInMillis() - cd_3.getTimeInMillis();
		
		//System.out.println("ChartBackBeanIMP.llega al final: " + dif_3 + " milisegundos " );
		
		long dif_4 = cd_4.getTimeInMillis() - cd_1.getTimeInMillis();
		
		System.out.println("Tiempo total Cargar ChartBackBeanIMP : " + dif_4 + " milisegundos " );
		
		System.out.println("ChartBackBeanIMP.init(): OK");
	}
	
	public void initChart()
	{		
		if(burSolNum != null)
		{			
			loadChart();
			init_nota_del_coach();
			
			scriptAction = "<script> drawChartBuro();  </script> "
						 + "<style> #buroResp{width: 820px !important;margin-top: 15px !important;font-size: 1.2em !important;} </style>";
			
			hasSolNum        = true;
			maxWidthTableVig = "788";
			
		} else {
			
			maxWidthTableVig = "868";
		}		
	}	
					
	public final void loadChart()
	{
		ct1 = Calendar.getInstance();
		ct1.setTime(new Date());
		
		try
		{		
			totalConsult	  = 0;
			consultasKubo     = 0;
			consultasOtras    = 0;
			consultas_propias = 0;
			
			consultasKuboULT     = 0;
			consultasOtrasULT    = 0;
			consultas_propiasULT = 0;
			
			consultasKuboMS     = 0;
			consultasOtrasMS    = 0;
			consultas_propiasMS = 0;
			
			faces = FacesContext.getCurrentInstance();	
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
							
/*			
			sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
*/			
			
			htFecVen        = new Hashtable<String, String[]>();
			htFecMaxReporte = new Hashtable<String, String[]>();
			htConsultMS     = new Hashtable<String,List<Vtbur_infocnsltms>>();
			htConsultUlt    = new Hashtable<String,List<Vtbur_infocnsltult>>();
			
			initModal = false;	
			n = 0;
			
									
			tableScriptJSon = new StringBuilder();
													
			saldoCorriente         = 0D;
			montoAPagarCorriente   = 0D;
			maxCredCorriente       = 0D;
			pagoPeriodicoCorriente = 0D;
			saldoVencidoCorriente  = 0D;
			LimiteMaxCorriente     = 0D;				 				
			saldoAtraso            = 0D;
			montoAPagarAtraso      = 0D;
			maxCredAtraso          = 0D;
			pagoPeriodicoAtrasado  = 0D;
			saldoVencidoAtrasado   = 0D;
			LimiteMaxAtrasado      = 0D;
			
			noActivas    = 0;
			numAtraso    = 0;
			numCorriente = 0;	

			asignar_proyect_loan_info();
			asignar_transunion_rechazo();
			
			BuroCache cache = burocacheservice.getBuroCache(burSolNum);
			
			if( cache == null )
			{ 						
				cBr_1 = Calendar.getInstance();
				cBr_1.setTime(new Date());
				 
				bur = getBuroResume(burSolNum);
				
				cBr_2 = Calendar.getInstance();
				cBr_2.setTime(new Date());
				difBr_2 = cBr_2.getTimeInMillis() - cBr_1.getTimeInMillis();
				
				//System.out.println("Tiempo en consultar datos BuroResume: "+difBr_2+" milisegundos "+ bur);
								
				if(bur != null)
				{					
					folioNum     = bur.getBurFol();					
					idProvider   = (!(bur.getTipoConsulta().equals("FIRMA")));					
				}
									
				// identify = null; TODO												
				maxGridD  = 4D;
				
				htIdentified  = new Hashtable <Integer , IdentifiedCredit> ();	
				conv          = new ConvertCalendar(Long.parseLong(bur.getFecConsulta()));		
				
				dFecConsult = conv.getDate(); 					
				fecConsult  = formatStr.format( dFecConsult );
				
				
				asignar_all_data_bur();
				
				/*
				String a[] = null;
				
				
				BurGraphic grs[] = service.getBurGraphic(burSolNum, a);
				YOO-BurGraphic grsTemp[] = service.getBurGraphic(burSolNum, a);
				 */
	
				cnsltms  =  allDataBur.getVtbur_infocnsltms();  // consultas que le han hecho hace más de 6 meses
				cnsltult =  allDataBur.getVtbur_infocnsltult(); // consultas que le han hecho en ultimos 6 meses
				
				totalConsult = ((cnsltms == null) ? 0 : cnsltms.length) +  ((cnsltult == null) ? 0 : cnsltult.length);
				
				infocalkubo 	= allDataBur.getVtbur_infocalkubo();
				infocredcte_c 	= allDataBur.getVtbur_infocredcte_c();
				infocredcte_m 	= allDataBur.getVtbur_infocredcte_m();
				infocredcte_vig = allDataBur.getVtbur_infocredcte_vig();
				infocte  	    = allDataBur.getVtbur_infocte();
				infodircte 	    = allDataBur.getVtbur_infodircte();
				infoAlertaInc	= allDataBur.getVtbur_infoAlertaInc();
				infoAlertaPrev	= allDataBur.getVtbur_infoAlertaPrev();
				
				
				asignar_calificacion_kubo();
										
				setFormatName();
				
				grs = allDataBur.getBurGraphic();
					
				if( grs != null )
				{
					grs = getBurGraphicFilter(grs);
				}									
								
				prospectus_id = Integer.parseInt(infocte.getIdprospecto());
							
				asignarCorreoAcreditado();
				
				if(isNombreAcreditadoHomogeneo())
				{
					warningNombreAcreditadoHomogeneo = false;
					
				} else  {
					
					warningNombreAcreditadoHomogeneo = true;
				}
				
				if(isRFCAcreditadoHomogeneo())
				{
					warningRFCAcreditadoHomogeneo = false;
					
				} else {
					
					warningRFCAcreditadoHomogeneo = true;
				}
												
				asignar_datos_alerta_reporte();																		 
				asignar_datos_alerta_consulta();
														
				if(infocte.getFecha_nacimiento() != null)
				{
					fecNac = formatStr.format(fm3.parse(infocte.getFecha_nacimiento()));
				}
														 
					nombreCompleto = infocte.getNombre1()	== null	? "" : (	  infocte.getNombre1())
								   + infocte.getNombre2()	== null	? "" : (" " + infocte.getNombre2())
								   + infocte.getApellido1() == null	? "" : (" " + infocte.getApellido1())
								   + infocte.getApellido2() == null	? "" : (" " + infocte.getApellido2());									
					
				asignar_consultas_semestre_anterior();
				asignar_consultas_semestre_posterior();
				asignar_dias_transcurridos_consulta();						        
				asignar_creditos_vigentes();							 
				asignar_direcciones();		 
				asignar_creditos_cerrados_semestre_anterior();
				asignar_creditos_cerrados_semestre_posterior();
				asignar_grafica();
							 
				scriptJSon = "<script>"+tableScriptJSon.toString()+"</script>";
		 		
				saldos = new SaldoDeudas();
				
				scriptSaldos = saldos.calculaGráfica(infocredcte_c, infocredcte_m, infocredcte_vig, infocte );
				
				insertaDatosCache();
				
			} else {								
				
				infocte = new Vtbur_infocte();
				
				
				folioNum 			= cache.getMx_folio();
				//idProvider 			= Integer.parseInt( cache.getIdProvider() );
				
				dFecConsult  		= cache.getFecConsult();
				calificacion_kubo	= cache.getCalificacion_kubo();
				kubo_score_class	= cache.getKubo_score_class();
				nombreCompleto		= cache.getNombreCompleto();
				infocte.setRfc(cache.getRfc());
				
				infocte.setIdprospecto( cache.getProspectus_id() );
			
				if( cache.getProspectus_id() != null ){
					prospectus_id = Integer.parseInt( cache.getProspectus_id() );
				}
				
				infocte.setNombre1    ( cache.getNombre1()   );
				infocte.setNombre2    ( cache.getNombre2()   );
				infocte.setApellido1  ( cache.getApellido1() ); 
				infocte.setApellido2  ( cache.getApellido2() );
				infocte.setBur_solnum ( cache.getMx_solicitud_buro());
				
				if( cache.getKubo_score_letter() != null ){
				kubo_score_letter = Character.valueOf(cache.getKubo_score_letter().charAt(0) );
				}
				if( cache.getKubo_score_number() != null ){
				kubo_score_number = Character.valueOf(cache.getKubo_score_number().charAt(0) );
				}
				if( cache.getConsultas_propias() != null ){
				consultas_propias	= Integer.parseInt(cache.getConsultas_propias());
				}
				if( cache.getConsultas_propiasMS() != null ){
				consultas_propiasMS	= Integer.parseInt(cache.getConsultas_propiasMS());
				}
				if( cache.getConsultas_propiasULT() != null ){
				consultas_propiasULT= Integer.parseInt(cache.getConsultas_propiasULT());
				}
				if( cache.getConsultasOtras() != null ){
				consultasOtras		= Integer.parseInt(cache.getConsultasOtras());
				}
				if( cache.getConsultasOtrasMS() != null ){
				consultasOtrasMS	= Integer.parseInt(cache.getConsultasOtrasMS());
				}
				if( cache.getConsultasOtrasULT() != null ){
					consultasOtrasULT	= Integer.parseInt(cache.getConsultasOtrasULT());
				}
				if( cache.getConsultasKubo() != null ){
					consultasKubo		= Integer.parseInt(cache.getConsultasKubo());
				}
				if( cache.getConsultasKuboULT() != null ){
					consultasKuboULT	= Integer.parseInt(cache.getConsultasKuboULT());
				}
				if( cache.getConsultasKuboMS() != null ){
					consultasKuboMS		= Integer.parseInt(cache.getConsultasKuboMS());
				}
				if( cache.getTotalConsult() != null ){
					totalConsult		= Integer.parseInt(cache.getTotalConsult());
				}
				
				tableRulesStr		= cache.getTableRulesStr();
				tableAlertas1Str	= cache.getTableAlertas1Str();
				tableAlertas2Str	= cache.getTableAlertas2Str();
				tableConsultUltData	= cache.getTableConsultUltData();
				tableConsultMSData	= cache.getTableConsultMSData();
				tableVigData		= cache.getTableVigData();
				tableVigDataCom		= cache.getTableVigDataCom();
				tableDomicilios		= cache.getTableDomicilios();
				tableCredCerr		= cache.getTableCredCerr();
				tableCredCerr_M_6	= cache.getTableCredCerr_M_6();
				chartModel			= cache.getChartModel();
				
				
				if( cache.getMaxGrid() != null ){
					maxGrid			= Integer.parseInt(cache.getMaxGrid());
				}
				
				scriptJSon			= cache.getScriptJSon();
				scriptSaldos		= cache.getScriptSaldos();
				
				
				fecConsult  = formatStr.format( dFecConsult );
				
				bur = new BurResume();
				
				//System.out.println( "Inicializando  bur = new BurResume()" );
				
				bur.setScore_buro(cache.getScore_buro() );
				
				bur.setBurFol(cache.getBurFol());
				bur.setTipoConsulta(cache.getTipoConsulta());
				bur.setScoreIcc(cache.getScoreIcc());
				
				bur.setNumsols(cache.getNumsols());
				bur.setCta_cgarantia(cache.getCta_cgarantia());
				bur.setQuebrantos(cache.getQuebrantos());
				
				if( cache.getProspectus_id() != null ){
					bur.setIdprospecto( cache.getProspectus_id() );
				}
				if( cache.getFraudes() != null ){
					bur.setFraudes(Integer.parseInt(cache.getFraudes()));
				}
				if( cache.getRoboidentidad() != null ){
					bur.setRoboidentidad(Integer.parseInt(cache.getRoboidentidad()));
				}
				if( cache.getFraudesnoatribuible() != null ){
					bur.setFraudesnoatribuible(Integer.parseInt(cache.getFraudesnoatribuible()));
				}
				if( cache.getFianzas() != null ){
					bur.setFianzas(Integer.parseInt(cache.getFianzas()));
				}
				if( cache.getAplicogarantia() != null ){
					bur.setAplicogarantia(Integer.parseInt(cache.getAplicogarantia()));
				}
				if( cache.getNolocalizable() != null ){
					bur.setNolocalizable(Integer.parseInt(cache.getNolocalizable()));
				}
				if( cache.getCodemandado() != null ){
					bur.setCodemandado(Integer.parseInt(cache.getCodemandado()));
				}
				if( cache.getCondonaciones() != null ){
					bur.setCondonaciones(Integer.parseInt(cache.getCondonaciones()));
				}
				if( cache.getReestructura() != null ){
					bur.setReestructura(Integer.parseInt(cache.getReestructura()));
				}
				
				bur_fec_credantiguo = cache.getFec_credantiguo();
				
				spca = new Bur_spca();
				spca.setStrCreditos(cache.getStrCreditos());
				
				if( cache.getLimiteagregado() != null ){
					spca.setLimiteagregado(Double.parseDouble( cache.getLimiteagregado() ));
				}
				if( cache.getSaldoagregado() != null ){
					spca.setSaldoagregado( Double.parseDouble( cache.getSaldoagregado()));
				}
				if( cache.getMontoagregado() != null ){
					spca.setMontoagregado( Double.parseDouble( cache.getMontoagregado()));
				}
				
			  
				maxoto= new Bur_maxoto();
				maxoto.setPrim_credito(  cache.getPrim_credito());
				maxoto.setUlt_credito(   cache.getUlt_credito());
				
				if( cache.getMax_liquidado() != null ){
					maxoto.setMax_liquidado( Double.parseDouble( cache.getMax_liquidado() ));
				}
				if( cache.getMax_noliquidado() != null ){
					maxoto.setMax_noliquidado( Double.parseDouble(cache.getMax_noliquidado()));
				}
				
				if( cache.getNumCorriente() != null ){
					numCorriente = Integer.parseInt( cache.getNumCorriente() );
				}
				if( cache.getSaldoCorriente() != null ){
					saldoCorrienteStr = cache.getSaldoCorriente();
				}
				if( cache.getMontoAPagarCorriente( ) != null ){
					montoAPagarCorrienteStr = cache.getMontoAPagarCorriente( );
				}
				if( cache.getPagoPeriodicoCorriente() != null ){
					pagoPeriodicoCorrienteStr = cache.getPagoPeriodicoCorriente();
				}
				if( cache.getSaldoVencidoCorriente() != null ){
					saldoVencidoCorrienteStr = cache.getSaldoVencidoCorriente();
				}
				if( cache.getMaxCredCorriente() != null ){
					maxCredCorrienteStr = cache.getMaxCredCorriente();
				}
				if( cache.getLimiteMaxCorriente() != null ){
					limiteMaxCorrienteStr = cache.getLimiteMaxCorriente();
				}
				if( cache.getNumAtraso() != null ){
					numAtraso = Integer.parseInt(cache.getNumAtraso() );
				}
				if( cache.getSaldoAtraso( ) != null ){
					saldoAtrasoStr = cache.getSaldoAtraso( );
				}
				if( cache.getMontoAPagarAtraso() != null ){
					montoAPagarAtrasoStr = cache.getMontoAPagarAtraso();
				}
				if( cache.getPagoPeriodicoAtrasado( ) != null ){
					pagoPeriodicoAtrasadoStr = cache.getPagoPeriodicoAtrasado( );
				}
				if( cache.getLimiteMaxCorriente( ) != null ){
					limiteMaxCorrienteStr = cache.getLimiteMaxCorriente( );
				}
				if( cache.getMaxCredAtraso( ) != null ){
					maxCredAtrasoStr = cache.getMaxCredAtraso( );
				}
				if( cache.getLimiteMaxAtrasado( ) != null ){
					limiteMaxAtrasadoStr = cache.getLimiteMaxAtrasado( );
				}
				if( cache.getSaldoVencidoAtrasado() != null ){
					saldoVencidoAtrasadoStr = cache.getSaldoVencidoAtrasado();
				}
				
				
				
				if(company_id == 0){
					company_id = 1;
				}
				
				
				asignarCorreoAcreditado();
				
			}
			
			asignar_dias_transcurridos_consulta();
			
			notasDelCaso = new NotasDelCasoIMP();
			notasDelCaso.init();
			
			if(proyecto != null)
			{									
				notasDelCaso.setPersona(proyecto.getPerson());
				
				loadNotes((proyecto != null) ? proyecto.getProyectloanPk().getCompany_id() : 1);
				
				nombreAcreditado = proyecto.getPerson().NombreCompletoNPM();
			} else {
				
				natural_person_PK = new gnNaturalPersonPK(prospectus_id, company_id);
				
				persona = naturalPersonService.getNaturalPersonById(natural_person_PK);
				
				notasDelCaso.setPersona(persona);
				
				loadNotes((proyecto != null) ? proyecto.getProyectloanPk().getCompany_id() : 1);
				
				nombreAcreditado = persona.NombreCompletoNPM();
			}
							
			notasDelCaso.loadNotes();
			
			
			registrar_bitacora_acceso();
			
			if(bur.getIdprospecto() != null)
			{
				sesion.setUser_graphic_temp(bur.getIdprospecto());
			}
			
			if( sesion.getArea().toString().equals("M") )
			{
				
				identifyStr = "<input type='hidden' id='identifyValStr' value='";
				
				
				if( company_id > 0 && prospectus_id > 0 )
				{
					identify = identifiedcreditservice.getIdentifiedCreditListByProspectus(company_id, prospectus_id);		
					
					if(identify != null && identify.size() > 0){
						
						identifyStr += "" + getCreditIdentifiedStr( identify ) ;
						
					}
					
					
				}
				
				identifyStr += "' />";
				
				cargaBloqueos();
				asignar_consultas_anteriores();
				
			}else{
			
				identifyStr = "<input type='hidden' id='identifyValStr' value='' />";
				
			}
			
			
			//System.out.println( "termina la carga  bur prospecto = "+bur.getIdprospecto() );
																							
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	
		ct2 = Calendar.getInstance();
		ct2.setTime(new Date());
		difT = ct2.getTimeInMillis() - ct1.getTimeInMillis();
		
		//System.out.println("Tiempo total en cargar la gráfica: "+difT+" milisegundos");				
	}
		
	public BurGraphic[] getBurGraphicFilter(BurGraphic [] arBurGraphicIn){
	    
	    SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
	    //Se convierte el array en lista para poder aplicar el metodo sort.
	    //List<BurGraphic> listBurGraphic = Arrays.asList(arBurGraphicIn);
	    List<BurGraphic> listBurGraphic = new ArrayList<BurGraphic>(Arrays.asList(arBurGraphicIn));
	    
	//    System.out.println("***** ANTES ******");
	//    for(BurGraphic bur: listBurGraphic){
	//        System.out.println("listaBurGraphic:"+bur.getCuenta()+"-"+bur.getMes());
	//    }
	    
	    BurGraphicComparator burGraphicComparator = new BurGraphicComparator();
	    //Desc
	    Collections.sort(listBurGraphic, Collections.reverseOrder(burGraphicComparator));
	    
	//    System.out.println("***** DESPUES ******");
	//    for(BurGraphic bur: listBurGraphic){
	//        System.out.println("listaBurGraphic:"+bur.getCuenta()+"-"+bur.getMes());
	//    }
	    
	    //String mopMes = "";
	    //Bandera para saber si es el ultimo reporte de buro y no restarle un mes
	    boolean flagFirstRegister = true; 
	    String cuenta = "";
	    Date lastMonth = null;
	    int cont = 1;
	    //BurGraphic burG = null;
	    BurGraphic burGTemp = null;
	    //Se crean dos listas temporales una para los objetos a agregar y otra para los obj a borrar.
	    List <BurGraphic> listToRemoveTemp = new ArrayList<BurGraphic>();
	    List <BurGraphic> listToAddTemp = new ArrayList<BurGraphic>();
	    for(BurGraphic burG : listBurGraphic){
	        if(burG!=null){
	            if(  burG.getCuenta()!=null && 
	                burG.getCuenta().indexOf("LINEA DE CONTROL")==(-1)
	                
	                    ){
	                        
	        
	            if(burG.getFrecuenciaPagos() != null && burG.getInd_circulo() != null){
	                if((burG.getFrecuenciaPagos().toUpperCase()).equals("SEMANAL") && burG.getInd_circulo().equals("1")){
	                    //System.out.println("Cuenta y fecha:"+burG.getMes()+"-"+burG.getCuenta()+""+burG.getFrecuenciaPagos());
	                    //System.out.println("Si son dierentes"+burG.getCuenta()+"**"+cuenta);
	                    
	                    
	                    //Se valida si son diferentas las cuentas
	                    if(!burG.getCuenta().equals(cuenta)){
	                        //System.out.println("Valor cont:"+cont);
	                        if(cont != 1){
	                            if(flagFirstRegister){
	                                //Antes de guardarlo se le actualiza el ultimo mes valido -1
	                                burGTemp.setMes(fm.format(lastMonth));
	                            }else{
	                                burGTemp.setMes(fm.format(Utilities.restarFechasMeses(lastMonth, 1)));
	                            }
	                            //System.out.println("Agrego un registro nuevo X DIF CUENTAS"+burGTemp.getCuenta()+""+burGTemp.getMes());
	                            listToAddTemp.add(burGTemp);
	                            //Despues de guardar se actualiza la mayor fecha de reporte
	                            try {
	                                lastMonth = fm.parse(burGTemp.getMes());
	                            } catch (ParseException e) {
	                                
	                                e.printStackTrace();
	                            }
	                            burGTemp = null;
	                            cuenta = "";
	                            cont = 1;
	                            flagFirstRegister = false;
	                        }
	                        flagFirstRegister = true;
	                        //System.out.println("Primer reporte:"+burG.getMes()+"-"+burG.getCuenta());
	                        burGTemp = burG;
	                        cuenta = burG.getCuenta();
	                        
	                        try {
	                            //System.out.println("Antes de actualizar lasMonth:"+lastMonth);
	                            lastMonth = fm.parse(burG.getMes());
	                            //System.out.println("Se actualizo lasMonth:"+lastMonth);
	                        } catch (ParseException e) {
	                            
	                            e.printStackTrace();
	                        }
	                        
	                        /*************/
	                        //
	                        BurGraphic bur23 =  new BurGraphic()  ;
	                        try{
	                        
	                            bur23.setBursolnum(burG.getBursolnum());
	                            bur23.setCreditoMaximo(burG.getCreditoMaximo());
	                            bur23.setCuenta(burG.getCuenta());
	                            bur23.setFechaApertura(burG.getFechaApertura());
	                            bur23.setFrecuenciaPagos(burG.getFrecuenciaPagos());
	                            bur23.setIdCliente(burG.getIdCliente());
	                            bur23.setIdFrecuenciaPagos(burG.getIdFrecuenciaPagos());
	                            bur23.setInd_circulo(burG.getInd_circulo());
	                            bur23.setLimiteCredito(burG.getLimiteCredito());
	                            bur23.setMes(burG.getMes());
	                            bur23.setMopMes(burG.getMopMes());
	                            bur23.setMopMesGraficar(burG.getMopMesGraficar());
	                            bur23.setNumeroPagos(burG.getNumeroPagos());
	                            bur23.setRegistro(burG.getRegistro());
	                            bur23.setSaldoactual(burG.getSaldoactual());
	                            bur23.setStatusCredito(burG.getStatusCredito());
	                            
	                            Date mesT = fm.parse(bur23.getMes());
	                            
	                            Calendar c = Calendar.getInstance();
	                            
	                            c.setTime(mesT);
	                            
	                            c.add(Calendar.DAY_OF_MONTH, 1);
	                            
	                            bur23.setMes(fm.format(c.getTime()));
	//                            System.out.println(" agregando --  listaBurGraphic:"+bur23.getCuenta()+"-"+bur23.getMes()+"-"+bur23.getMopMesGraficar());
	//                            System.out.println("Fecha Original: "+burG.getMes());
	                            listToAddTemp.add(bur23);
	                        
	                        }catch(Exception e){
	                            
	                            e.printStackTrace();
	                            
	                        }
	                        //
	                        
	                        /*************/
	                        
	                        
	                    }else{
	                        
	                    
	                        if(burGTemp == null){
	                            burGTemp = burG;
	                        }else{
	                            //System.out.println("prueba:"+burGTemp.getMopMes()+"-"+burG.getMopMes()+"-"+burG.getFrecuenciaPagos());
	                            //Se toma el mop mas alto, y un mes menos respecto al ultimo de reporte valido de los 4 valores tomados
	                            
	                            boolean isLetterA=false;
	                            boolean isLetterT=false;
	                            
	                            isLetterA = isLetterFnc( burG.getMopMes() );
	                            
	                            isLetterT = isLetterFnc(burGTemp.getMopMes());
	                            
	                            if(isLetterT && !isLetterA)
	                                burGTemp = burG;
	                            else
	                            
	                            
	                                if(!isLetterA && Float.parseFloat(burGTemp.getMopMes()) < Float.parseFloat(burG.getMopMes())){
	                                    //System.out.println("Fecha mes:"+lastMonth);
	                                    //System.out.println("Fecha mes:"+burG.getMes());
	                                    burGTemp = burG;
	                                }
	                                
	                            
	                                
	                        }
	                    }
	                    
	                    
	                    if(cont < 4)
	                    {
	                        cont ++ ;
	                    }else{
	                        cont = 1;
	                        //System.out.println("Setea a null por ser mayor q 4");
	                        if(flagFirstRegister){
	                        //Antes de guardarlo se le actualiza el ultimo mes valido -1
	                            burGTemp.setMes(fm.format(lastMonth));
	                        }else{
	                            burGTemp.setMes(fm.format(Utilities.restarFechasMeses(lastMonth, 1)));
	                        }
	                        
	                        //System.out.println("Agrego un registro nuevo x CONT = 4"+burGTemp.getCuenta()+""+burGTemp.getMes());
	                        listToAddTemp.add(burGTemp);
	                        //Despues de guardar se actualiza la mayor fecha de reporte
	                        try {
	                            lastMonth = fm.parse(burGTemp.getMes());
	                        } catch (ParseException e) {
	                            
	                            e.printStackTrace();
	                        }
	                        burGTemp = null;
	                        flagFirstRegister = false;
	                    }
	                    listToRemoveTemp.add(burG);
	            }
	        }
	            //En caso de que se terminen los creditos que guarde el ultimo obj burGTemp
	            if(burGTemp != null){
	                if(flagFirstRegister){
	                	
	                    //Antes de guardarlo se le actualiza el ultimo mes valido -1
	                        burGTemp.setMes(fm.format(lastMonth));
	                        
	                    }else{
	                    	
	                        burGTemp.setMes(fm.format(Utilities.restarFechasMeses(lastMonth, 1)));
	                        
	                    }
	                listToAddTemp.add(burGTemp);
	                
	            }
	            
	            }else{
	                listToRemoveTemp.add(burG);
	            }
	    }// fin de burGr != null
	} 
	    
	    if(infocredcte_m != null)
	    for( Vtbur_infocredcte_m cerr : infocredcte_m){
	    	
	    	SimpleDateFormat fm2 = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat fm3 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	    	try{
	    	if( cerr.getFec_cierre() != null ){
		    		
		    		String fecC  = (fm2.format(fm3.parse(cerr.getFec_cierre()   )));
		    		String fecR  = (fm2.format(fm3.parse(cerr.getFecha_reporte())));
		    		
		    		Calendar calCierre = Calendar.getInstance();
		    		
		    		calCierre.setTime(fm3.parse( cerr.getFec_cierre() ));
		    		
		    		
		    		
		    		String mC = fecC.split("/")[1];
		    		String mR = fecR.split("/")[1];
		    		String yC = fecC.split("/")[2];
		    		String yR = fecR.split("/")[2];
		    		
		    		if( ( Integer.parseInt(yC)<Integer.parseInt(yR) ) || ( Integer.parseInt(yC)==Integer.parseInt(yR) && Integer.parseInt(mC)<Integer.parseInt(mR) ) ){
		    		 
		    			//System.out.println("credito: "+cerr.getNo_credito());
		    			calCierre.setTime( getPrimerDiaDelMes( calCierre ) );
		    			
		    			for( BurGraphic burG : listBurGraphic ){
		    				//System.out.println(burG.getCuenta() + " - "+burG.getCuenta().indexOf("INEA DE CONTRO") );
		    				if(burG != null ){
		    				if(burG.getCuenta().indexOf("INEA DE CONTRO")<0){
		    					if((burG.getCuenta().split("_")).length>1){
				    				
		    						if( cerr.getNo_credito() != null && (Integer.parseInt( burG.getCuenta().split("_")[0])+"").equals( Integer.parseInt(cerr.getNo_credito()) +"")) {
		    							
		    							burGTemp = burG;
		    							
		    							//System.out.println(burG.getCuenta()+ " - "+(fm2.format(fm3.parse(burG.getMes())))+" - " +burG.getMopMesGraficar());
				    					//System.out.println(burG.getCuenta()+ " - "+(fm2.format(calCierre.getTime()))+" - " +burG.getMopMesGraficar() +" --C-- ");
				    					
				    					burGTemp.setMes((fm2.format(calCierre.getTime())));
				    					
				    					listToAddTemp.add(burGTemp);
				    					listToRemoveTemp.add(burG);
				    					calCierre.add(Calendar.MONTH, (-1));
				    				}
		    						
		    						
		    						
		    					}else{
		    						
		    						if( cerr.getNo_credito() !=null && (Integer.parseInt( burG.getCuenta().split("-")[1])+"").equals( Integer.parseInt(cerr.getNo_credito()) +"")) {
		    							
		    							burGTemp = burG;
		    							
				    					//System.out.println(burG.getCuenta()+ " - "+((burG.getMes()))+" - " +burG.getMopMesGraficar());
				    					//System.out.println(burG.getCuenta()+ " - "+(fm2.format(calCierre.getTime()))+" - " +burG.getMopMesGraficar() +" --C-- ");
				    					
				    					burGTemp.setMes((fm2.format(calCierre.getTime())));
				    					
				    					listToAddTemp.add(burGTemp);
				    					listToRemoveTemp.add(burG);
				    					
				    					calCierre.add(Calendar.MONTH, (-1));
				    				}
		    						
		    						
		    					}
		    					
		    					
		    				}
		    				}// fin de valida si burg != null
		    			}
		    			
		    			
		    		}
		    		
		    	}
		    	
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
		
//	    
//	    for( Vtbur_infocredcte_m cerrMas : infocredcte_m ){
//	    	
//	    }
//	    
	    listBurGraphic.removeAll(listToRemoveTemp);
	    listBurGraphic.addAll(listToAddTemp);
	    
	    
	    Collections.sort(listBurGraphic, burGraphicComparator);
	        
	        
	    BurGraphic arBurGraphicOut[] = new BurGraphic[listBurGraphic.size()];
	    arBurGraphicOut = listBurGraphic.toArray(arBurGraphicOut);
	    
	            
	//            System.out.println("***** RETURN  ******");
	//            for(BurGraphic bur: listBurGraphic){
	//                System.out.println("listaBurGraphic:"+bur.getCuenta()+"-"+bur.getMes());
	//            }
	            
	    return arBurGraphicOut;
	    
	}
		
	public boolean isLetterFnc(String cad){
		
		boolean isLetter = false;
		if(cad!= null){
			for(int i = 0 ; i< cad.length();i++){
			
				if( abc.indexOf(((cad.charAt(i))+"").toUpperCase()) != (-1)){
					isLetter = true;
					break;
				}
			
			}
		}
		
		return isLetter;
		
	}
	
	public void loadNotes(int company_id)
	{		
		boolean flag = false ;
		
		flgNtSz = false;
		
		if( bur != null || sesion.getUser_graphic_temp() != null  )
		{		
			if(bur != null)
			{
				lstNotes = noteService.getListNotesByProspectByType(Integer.parseInt(bur.getIdprospecto()), company_id,5,true);
				
			} 
			
			else if (sesion.getUser_graphic_temp() != null)
			{
				lstNotes = noteService.getListNotesByProspectByType(Integer.parseInt(sesion.getUser_graphic_temp()), company_id,5,true);
			}
			
			if(is_sesion_ENABLED)
			{			
				for( Notes note : lstNotes )
				{
					if(note != null && note.getNote() != null)
					{						
						if(note.getNote().split("-").length>1)
						{
							note.setNote( "<b>"+note.getNote().split("-")[0]+"</b> - "+note.getNote().split("-")[1] );
							
						} else {
							
							note.setNote( note.getNote());
						}
					
						if( (sesion.getProspectus_id()+"").equals(note.getChange_prospectus_id()+"") && (sesion.getCompany_id()+"").equals(note.getNotesPk().getCompany_id()+"") ){
							flag = true;
						}
					}
				}
				
				if( !flag )
				{					
					SystemParamPK id = new SystemParamPK();
					
					id.setCompany_id(sesion.getCompany_id());
					id.setSystem_param_id(50); // Bandera que indica si las notas de la gráfica estan disponibles aunque NO hayas dado algún comentario
					
					SystemParam sys = systemparamservice.loadSelectedSystemParam(id);
					
					if( sys.getValue().equals( "N" ) )
					{
						if( lstNotes!= null && lstNotes.size()>0 )
						{							
							flgNtSz = true;
							
						} else {
							
							flgNtSz = false;							
						}
						
						lstNotes = null;
					}					
				}	
				
			} else {
				lstNotes = null;
			}
			
		} else {
			
			lstNotes = null;
		}		
	}
	
	public void addNewNote(){
		
		//System.out.println(" procesando ! ! ! ");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion = (SessionBean) FacesContext.getCurrentInstance()
	            .getApplication().getELResolver()
	            .getValue(elContext, null, "sessionBean");
		
		if(sesion.getProspectus_id() != null && sesion.getProspectus_id() > 0){
			memberSelect = sesion.getProspectus_id()+"::"+sesion.getCompany_id();
		}
		
		if( memberSelect!=null ){
			
			gnNaturalPersonPK personPK = new gnNaturalPersonPK();
			
			//Systemout.println("--- Notas al prospecto: "+ bur + "  memberSelect: " + memberSelect );
			
			if( bur != null ){
				personPK.setProspectus_id( Integer.parseInt(bur.getIdprospecto()) );
			}else{
				if(sesion.getUser_graphic_temp() != null){
					personPK.setProspectus_id( Integer.parseInt(sesion.getUser_graphic_temp()) );
				}else {
					personPK.setProspectus_id( Integer.parseInt(sesion.getUser_graphic_temp()) );
				}
					
			}
			personPK.setCompany_id(Integer.parseInt(memberSelect.split("::")[1]));
			
			addNote = new Notes();
			
			if( bur != null ){
				getAddNote().setNotesPk(new NotesPK(Integer.parseInt(bur.getIdprospecto()),Integer.parseInt(memberSelect.split("::")[1]) ));
			}else{
				if(sesion.getUser_graphic_temp() != null){
					getAddNote().setNotesPk(new NotesPK(Integer.parseInt(sesion.getUser_graphic_temp()),Integer.parseInt(memberSelect.split("::")[1]) ));
				}else {
					getAddNote().setNotesPk(new NotesPK(Integer.parseInt(sesion.getUser_graphic_temp()),Integer.parseInt(memberSelect.split("::")[1]) ));
				}
			}
				getAddNote().setNote( getRecommendationLabel( getRecommendation_id() ) + " - " + ((getRecomendation_txt()==null)?"":getRecomendation_txt()));
				getAddNote().setNote_type_id(5);
				getAddNote().setChange_date(new Date());
				getAddNote().setChange_prospectus_id(Integer.parseInt(memberSelect.split("::")[0]));
				getAddNote().setProyect_id(null);
				getAddNote().setPriority_type_id(2);
				
				if(noteService.addNote(getAddNote())){
					loadNotes(sesion.getCompany_id());
					setMemberSelect(null);
					setRecommendation_id(null);
					setRecomendation_txt(null);
				}
			
		}
		
	}
		
	public void saveIdentified(){
		//System.out.println("validando datos: saveIdentified");
		
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		
		if(keyStrJS != null && valStrJS != null ){
			
			try{
				
				JSONObject jsonKey = new JSONObject(keyStrJS);
				JSONObject jsonVal = new JSONObject(valStrJS);
				
				//System.out.println("OriginalEntity: " + jsonKey.getString("OriginalEntity") );
				String newEntity = jsonVal.getString("newCompany") ;
				
				IdentifiedCreditPK pk = new IdentifiedCreditPK();
				// [{"Company_id":"1","Frequency":"Pago minimo (Revolvente o Abierta - sin pago preestablecido)","OriginalEntity":"BANCO","Prospectus_id":"50","Totalpayment":"0","StartDate":"30/10/2007"}]
				pk.setCompany_id(Integer.parseInt( jsonKey.getString("Company_id") ));
				pk.setFrequency(jsonKey.getString("Frequency"));
				pk.setOriginal_entity(jsonKey.getString("OriginalEntity"));
				pk.setProspectus_id(Integer.parseInt(jsonKey.getString("Prospectus_id")));
				pk.setStart_date( fm.parse( jsonKey.getString("StartDate") ) );
				pk.setTotal_payments(jsonKey.getString("Totalpayment"));
				
				IdentifiedCredit ident = identifiedcreditservice.getIdentifiedCreditByPK(pk);
				
				if( ident != null ){
					
					ident.setActual_entity(newEntity.toUpperCase());
					
					identifiedcreditservice.updateIdentifiedCredit(ident);
					
				}else{
					
					ident = new IdentifiedCredit();
					
					ident.setPk(pk);
					
					ident.setActual_entity(newEntity.toUpperCase());
					
					identifiedcreditservice.saveIdentifiedCredit(ident);
					
				}
				
				saveChangeData("gn_identified_credit","actual_entity",jsonVal.getString("Prev_Entity"),ident.getActual_entity(),"Crédito Identificado",ident.getPk().getProspectus_id(),ident.getPk().getCompany_id());
				
				
				loadChart();
				
			}catch(Exception e){
				
				e.printStackTrace();
				//System.out.println(keyStrJS);
			}
			
		}else{
			
			//System.out.println("los datos vienen nulos");
			
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
		
		//String val = event.getObject().toString();
		//updateBank(val);
		
	}
	
	public void updateBank(String name){
		
//		Bank b = bankService.getBankByShortName(name);
//		
//		if(b!=null){
//			credithistory.setCreditcard_company(b.getShort_name());
//			
//		}else{
//			credithistory.setCreditcard_company(null);
//		}
//		saveCreditHistory();
		
	}
		
	public void initSession()
	{
		faces     = FacesContext.getCurrentInstance();	
		request   = RequestContext.getCurrentInstance();
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		boolean flgUsr = false;
		
		if(promotorIDOfMail == null || promotorIDOfMail.trim().equals( "" ))
		{			 
			thisMember = membershipservice.getMembershipByEmail( usr ); 
			 
			request.addCallbackParam("isLogged", false);
			 
			if(thisMember != null)
			{
				 flgUsr = true;
			}
			
		} else {
			
			flgUsr = true;
		}
		
		if(flgUsr)
		{						
/*			
			nameb   = external.getRequestParameterMap().get("namebraw");
			verionb = external.getRequestParameterMap().get("verbraw");
			osb     = external.getRequestParameterMap().get("osbraw");						
			width   = external.getRequestParameterMap().get("browser_width");
			height  = external.getRequestParameterMap().get("browser_height");
*/		
			
			HeaderBean header = (HeaderBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "headerBean");
			
			
			header.setUser( thisMember.getEmail() );		
			header.setPassword(password);
			//header.is_called_FROM("ChartBackBean.initSession()");
			
			
			
			String res = header.iniciaSesion();
			
			if ( res != null && res.equals( "controlTable" ))
			{				
				//System.out.println("Loageado Satisfactoriamente");
								
				sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication()
				        .getELResolver().getValue(elContext, null, "sessionBean");
				
				request.addCallbackParam("isLogged", true);
				
			}else{
				//System.out.println("Sin logearse res: "+res);
				
				SessionBean session 
			    = (SessionBean) FacesContext.getCurrentInstance().getApplication()
			        .getELResolver().getValue(elContext, null, "sessionBean");
				
				if(res.equals("mensaje")){
					request.addCallbackParam("msg","Existe incongruencia en los datos, comuniquese a kubo.");
				}else{
					if( res.equals("changePassword") ){
						
						request.addCallbackParam("msg","Tu contraseña ha expirado, por favor cambiala en tu siguiente acceso al portal.");
						session.setTemporalUser("");
						session.setFailedActive(false);
						session.setFailedPass(false);
						session.setFailedUser(false);
						session.setSessionUsed(false);
						session.setFailedTracking(false);
						
					}
				}
												
				if(session.isFailedUser())
				{
					request.addCallbackParam("msg","El usuario "+header.getUser()+" no existe.");
					
					session.setTemporalUser("");
					session.setFailedActive(false);
					session.setFailedPass(false);
					session.setFailedUser(false);
					session.setSessionUsed(false);
					session.setFailedTracking(false);
				}
				if(session.isFailedPass()){
					request.addCallbackParam("msg","Contraseña incorrecta.");
					session.setTemporalUser("");
					session.setFailedActive(false);
					session.setFailedPass(false);
					session.setFailedUser(false);
					session.setSessionUsed(false);
					session.setFailedTracking(false);
				}
				if(session.isSessionUsed()){
					request.addCallbackParam("msg","El usuario "+header.getUser()+" ya cuenta con una sesión abierta.");
					
					session.setTemporalUser("");
					session.setFailedActive(false);
					session.setFailedPass(false);
					session.setFailedUser(false);
					session.setSessionUsed(false);
					session.setFailedTracking(false);
				}
				
				if(session.isFailedActive()){
					request.addCallbackParam("msg","La cuenta "+header.getUser()+" no esta activa." );
					
					session.setTemporalUser("");
					session.setFailedActive(false);
					session.setFailedPass(false);
					session.setFailedUser(false);
					session.setSessionUsed(false);
					session.setFailedTracking(false);
					
				}
				
				if(session.isFailedTracking()){
					
					ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
					
					request.addCallbackParam("msg","La cuenta "+header.getUser()+" tiene un error en su número de folio, póngase en contacto con soporte-kubo para solucionar el problema. "+recurso.getString("Kubo_telefono")+"");
					
					session.setTemporalUser("");
					session.setFailedActive(false);
					session.setFailedPass(false);
					session.setFailedUser(false);
					session.setSessionUsed(false);
					session.setFailedTracking(false);
				}
				
				
				
				
				request.addCallbackParam("isLogged", false);
				header.SignOut();
			}
		
		}else{
			
			request.addCallbackParam("msg","El usuario no existe. ");
			
			request.addCallbackParam("isLogged", false);
			
		}
		
	}
	
	public static Date getPrimerDiaDelMes( Calendar cal ) {
			        //Calendar cal = Calendar.getInstance();
			        cal.set(cal.get(Calendar.YEAR),
			                cal.get(Calendar.MONTH),
			                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
			                cal.getMinimum(Calendar.HOUR_OF_DAY),
			                cal.getMinimum(Calendar.MINUTE),
			                cal.getMinimum(Calendar.SECOND));
			        return cal.getTime();
			    }
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void deleteDiferentSession()
	{
		//Systemout.println("eliminando la sesssion");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		MembershipPK pk = new MembershipPK();
		
		pk.setCompany_id(1);
		pk.setProspectus_id(sesion.getProspectus_id());
		
		Membership member1 = membershipservice.getMembershipById(pk);
		
		HttpSession sessionUsed = (HttpSession) facesContext.getExternalContext().getSession(false);
		
		HttpSession otherSession= null;
		
/*
 * sessionUsed.invalidate();
 * sessionUsed = (HttpSession) facesContext.getExternalContext().getSession(true);
*/
		Hashtable<String, Hashtable<String,HttpSession>> ht = (Hashtable<String, Hashtable<String,HttpSession>>)sessionUsed.getServletContext().getAttribute("usuariosFirmados");
		if(ht == null)
		{
			sessionUsed.getServletContext().setAttribute("usuariosFirmados",new Hashtable());
			ht = (Hashtable<String, Hashtable<String,HttpSession>>)sessionUsed.getServletContext().getAttribute("usuariosFirmados");
		}
		
		Enumeration<String> enumKey = ht.keys();
		boolean flag= false;
		while(enumKey.hasMoreElements()) {
			
		    String key = enumKey.nextElement();
		    Hashtable<String,HttpSession> htVal = (Hashtable<String,HttpSession>)ht.get(key);
		    
		    if( htVal.get(member1.getPerson().getProspectus().getTracking_id()) != null ){
		    	flag=true;
		    	otherSession = htVal.get(member1.getPerson().getProspectus().getTracking_id());
		    	break;
		    }
		    
		}
		
		if( flag ){
			//Existe la session

			    	//System.out.println("myKey: " + key );
			    	//System.out.println("session Key: "+otherSession.getId());
			    	ht.remove(otherSession.getId());
			    	otherSession.invalidate();
			    
		}
		
	}
	
	public void cargaBloqueos()
	{		
		Calendar cBlock1_1 = Calendar.getInstance();
		cBlock1_1.setTime(new Date());
		
		try{
			
			//Systemout.println("cargaBloqueos ----");
			
			if( service_SGB_risk == null ){
				try{
					
					Calendar cCargaService1_1 = Calendar.getInstance();
					cCargaService1_1.setTime(new Date());
					
					locator     = new WsSgbRiskServiceLocator();
					service_SGB_risk     = locator.getWsSgbRisk();
					
					Calendar cCargaService1_2 = Calendar.getInstance();
					cCargaService1_2.setTime(new Date());
					
					Long difCargaService1_2 = cCargaService1_2.getTimeInMillis() - cCargaService1_1.getTimeInMillis();
					
					//Systemout.println("Tiempo en CARGARSERVICIOS: " + difCargaService1_2 + " milisegundos");
					
				}catch(Exception e){
					e.printStackTrace();
					
				}
				
			}
			
			if(bur != null){
			
				Calendar cEjecutaService1_1 = Calendar.getInstance();
				cEjecutaService1_1.setTime(new Date());
				
				rules = service_SGB_risk.getKuboRules(prospectus_id+"");
				//Systemout.println("Con prospectus");
				
				Calendar cEjecutaService1_2 = Calendar.getInstance();
				cEjecutaService1_2.setTime(new Date());
				
				Long difEjecutaService1_2 = cEjecutaService1_2.getTimeInMillis() - cEjecutaService1_1.getTimeInMillis();
				
				//Systemout.println("Tiempo en EJECUTASERVICIOS: " + difEjecutaService1_2 + " milisegundos");
			
			}else{
				
				Calendar cEjecutaService1_1 = Calendar.getInstance();
				cEjecutaService1_1.setTime(new Date());
				
				rules = service_SGB_risk.getKuboRules(sesion.getUser_graphic_temp());
				//Systemout.println("Con Temporal");
				
				Calendar cEjecutaService1_2 = Calendar.getInstance();
				cEjecutaService1_2.setTime(new Date());
				
				Long difEjecutaService1_2 = cEjecutaService1_2.getTimeInMillis() - cEjecutaService1_1.getTimeInMillis();
				
				//Systemout.println("Tiempo en EJECUTASERVICIOS: " + difEjecutaService1_2 + " milisegundos");
			}
			
			Calendar cASIGNABLOQUEOS1_1 = Calendar.getInstance();
			cASIGNABLOQUEOS1_1.setTime(new Date());
			
			asignar_bloqueos_autotizacion();
			
			//Systemout.println("bur -- " + bur  +"  flagRules: "+flagRules+ "  rules: "+rules);
			
			Calendar cASIGNABLOQUEOS1_2 = Calendar.getInstance();
			cASIGNABLOQUEOS1_2.setTime(new Date());
			
			Long difASIGNABLOQUEOS1_2 = cASIGNABLOQUEOS1_2.getTimeInMillis() - cASIGNABLOQUEOS1_1.getTimeInMillis();
			
			//Systemout.println("Tiempo en ASIGNA BLOQUEOS: " + difASIGNABLOQUEOS1_2 + " milisegundos");
			
			if( rules != null  ){
				 //Systemout.println( "rules.length: " + rules.length );
			}else{
				//Systemout.println( "rules NULL" );
			}
			
			
		
		}catch( Exception e ){
		
			e.printStackTrace();
			
		}
		
		Calendar cBlock1_2 = Calendar.getInstance();
		cBlock1_2.setTime(new Date());
		
		Long difBlock1_2 = cBlock1_2.getTimeInMillis() - cBlock1_1.getTimeInMillis();
		
		//Systemout.println("Tiempo en cargar Bloqueos: " + difBlock1_2 + " milisegundos");
		
	}
	
}