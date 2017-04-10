package mx.com.kubo.managedbeans.mesa.reportes.buro;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ConsultingBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.buro.Bur_maxoto;
import mx.com.kubo.managedbeans.buro.Bur_spca;
import mx.com.kubo.managedbeans.buro.SaldoDeudas;
import mx.com.kubo.managedbeans.mesa.solicitud.notas.NotasDelCasoIMO;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Bank;
import mx.com.kubo.model.IdentifiedCredit;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RecommendationType;
import mx.com.kubo.model.TransunionResp;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.BuroCacheService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.IdentifiedCreditService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RecommendationTypeService;
import mx.com.kubo.services.RoleFunctionService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SimulationConfigService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TransunionRespService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

import org.primefaces.context.RequestContext;

import com.soa.model.businessobject.BurGraphic;
import com.soa.model.businessobject.BurResume;
import com.soa.model.businessobject.Vtbur_infoAlertaInc;
import com.soa.model.businessobject.Vtbur_infoAlertaPrev;
import com.soa.model.businessobject.Vtbur_infocalkubo;
import com.soa.model.businessobject.Vtbur_infocnsltms;
import com.soa.model.businessobject.Vtbur_infocnsltult;
import com.soa.model.businessobject.Vtbur_infocredcte_c;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocredcte_vig;
import com.soa.model.businessobject.Vtbur_infocte;
import com.soa.model.businessobject.Vtbur_infodircte;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.AllDataBur;
import com.soa.webServices.responses.KuboRulesResponse;

public abstract class ChartBackBeanDMO 
implements ChartBackBeanIMO
{		
	@ManagedProperty("#{transunionRespServiceImp}")
	protected TransunionRespService transunionrespservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{notesServiceImp}")
	protected NotesService noteService;
	
	@ManagedProperty("#{bankServiceImp}")
	protected BankService bankService;
	
	@ManagedProperty("#{recommendationTypeServiceImp}")
	protected RecommendationTypeService recommendationservice;
	
	@ManagedProperty("#{identifiedCreditServiceImp}")
	protected IdentifiedCreditService identifiedcreditservice;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipservice;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService changeControlService;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{simulationConfigServiceImp}")
	protected SimulationConfigService simulationConfigService;
	
	@ManagedProperty("#{buroCacheServiceImp}")
	protected BuroCacheService burocacheservice;
	
	@ManagedProperty("#{roleFunctionServiceImp}")
	protected RoleFunctionService roleFunctionService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;	

	protected WsSgbRisk service_SGB_risk;
	protected WsSgbRiskServiceLocator locator;	
	
	protected FacesContext    faces;
	protected RequestContext  request;
	protected ELResolver      resolver;
	protected ELContext       elContext;
	protected ExternalContext external;
	
	protected SessionBean sesion;	
	
	protected NaturalPerson persona;
	protected NaturalPerson naturalPersonOfMail;
	protected gnNaturalPersonPK natural_person_PK;
	protected Membership    thisMember;
	protected Membership    acreditado;
	protected Membership    coach;
	protected MembershipPK membership_pk;
	protected MembershipPK pk;	
	
	protected ProyectLoan   proyecto;
	
	protected Notes addNote;
	protected Notes nota_del_coach;
	protected NotasDelCasoIMO notasDelCaso;
	protected ParameterReaderIMO reader;
	
	protected AllDataBur	allDataBur;
	protected ConvertCalendar conv;
	protected TransunionResp transunion;	
	protected Bur_spca    spca;
	protected Bur_maxoto  maxoto;
	protected BurResume   bur;
	protected SaldoDeudas saldos;
	
	protected Vtbur_infocalkubo infocalkubo ;
	protected Vtbur_infocte     infocte ;
	
	protected Vtbur_infocredcte_c[]   infocredcte_c ;
	protected Vtbur_infocredcte_m[]   infocredcte_m ;
	protected Vtbur_infocredcte_vig[] infocredcte_vig;
	protected Vtbur_infodircte[]      infodircte;
	protected Vtbur_infocnsltms[]     cnsltms;
	protected Vtbur_infocnsltult[]    cnsltult;
	protected Vtbur_infoAlertaInc[]   infoAlertaInc;
	protected Vtbur_infoAlertaPrev[]  infoAlertaPrev;
		
	protected KuboRulesResponse[] rules;
	protected BurGraphic [] grs;
	
	protected List<Membership>         lstMember;
	protected List<RecommendationType> lstRecommendationType;
	protected List<ConsultingBean>     lstConsultas;
	protected List<Notes>              lstNotes;
	protected List<Notes>              lista_notas_del_coach;
	protected List<Bank>               lstBanks;
	protected List <ProyectLoan>       lista_proyect_loan;
	protected List <IdentifiedCredit>  identify;
	
	protected ArrayList <String[]> arFecVen;
	
	protected Map<String, String> parameter;
	
	protected Hashtable <String, String[]> htFecVen;
	protected Hashtable <String, String[]> htFecMaxReporte;
	
	protected Hashtable <String, List<Vtbur_infocnsltms>>  htConsultMS;
	protected Hashtable <String, List<Vtbur_infocnsltult>> htConsultUlt;
	
	protected Hashtable <Integer, IdentifiedCredit> htIdentified;
	
	protected GregorianCalendar date1;
	protected GregorianCalendar date2;
	
	protected Calendar ct1;
	protected Calendar cBr_1;
	protected Calendar cBr_2;
	protected Calendar cWS_1;
	protected Calendar cWS_2;
	protected Calendar cAlert1_1;
	protected Calendar cAlert1_2;
	protected Calendar cAlert_1;
	protected Calendar cAlert_2;
	protected Calendar cCns_R_1;
	protected Calendar cCns_R_2;	
	protected Calendar cCns_M6_2;
	protected Calendar ct2;
	 
	protected SimpleDateFormat fm;
	protected SimpleDateFormat fm2; 
	protected SimpleDateFormat fm3;
	protected SimpleDateFormat formatStr;
	protected SimpleDateFormat date_format;
	
	protected Date dFecConsult;
	protected Date bur_fec_credantiguo;
	protected Date bur_fecBuro;
	protected Date fechaMaxGrafica;
	
	protected Locale locale;
	
	protected NumberFormat dec;
	protected NumberFormat num;
	
	protected StringBuilder tableRules;
	protected StringBuilder tableScriptJSon;
	protected StringBuilder tableAlertas1;
	protected StringBuilder tableAlertas2;
	protected StringBuilder tableConsultUlt;		
	protected StringBuilder sb;
	
	protected String page_title;
	protected String nombreCliente, rfcCliente;	
	protected String chartModel   	 = "";
	protected String burSolNum    	 = "";
	protected String scriptAction 	 = "";
	protected String colors 		   = "";
	protected String promotorIDOfMail = "";
	protected String fecConsult;
	protected String diasTrans;
	protected String password;	
	protected String tableVigData;
	protected String tableVigDataCom;
	protected String tableConsultUltData;
	protected String tableConsultMSData;
	protected String tableDomicilios;
	protected String tableCredCerr;
	protected String tableCredCerr_M_6;
	protected String folioNum;
	protected String fecNac;
	protected String maxWidthTableVig;
	protected String nombreCompleto;
	protected String saldoAtrasoStr;
	protected String montoAPagarAtrasoStr;
	protected String maxCredAtrasoStr;
	protected String pagoPeriodicoAtrasadoStr;
	protected String saldoVencidoAtrasadoStr;
	protected String limiteMaxAtrasadoStr;
	protected String incremento1;
	protected String incremento2;
	protected String tScore;	
	protected String memberSelect;
	protected String recomendation_txt;	
	protected String scriptJSon;
	protected String keyStrJS;
	protected String valStrJS;
	protected String account1;
	protected String account2;
	protected String tableAlertas1Str;
	protected String tableAlertas2Str;
	protected String saldoCorrienteStr;
	protected String montoAPagarCorrienteStr;
	protected String maxCredCorrienteStr;
	protected String pagoPeriodicoCorrienteStr;
	protected String saldoVencidoCorrienteStr;
	protected String limiteMaxCorrienteStr;
	protected String usr; 
	protected String msg;
	protected String nombreAcreditado;
	protected String rfcAcreditado;
	protected String scriptSaldos;
	protected String transErrorMsg;
	protected String tableRulesStr;
	protected String cci_score;
	protected String calificacion_kubo;
	protected String kubo_score_class;
	protected String coach_nombre;
	protected String coach_fecha_nota;
	protected String identifyStr;
	
	protected Character kubo_score_letter;
	protected Character kubo_score_number;
	
	protected final String abc = "QWERTYUIOPÑLKJHGFDSAZXCVBNM";
	
	protected String [] colores =  {
	  "'#3366cc'", "'#dc3912'", "'#ff9900'",
	  "'#109618'", "'#990099'", "'#0099c6'",
	  "'#dd4477'", "'#66aa00'", "'#b82e2e'",
	  "'#316395'", "'#994499'", "'#22aa99'",
	  "'#aaaa11'", "'#6633cc'", "'#e67300'",
	  "'#8b0707'", "'#651067'", "'#329262'",
	  "'#5574a6'", "'#3b3eac'", "'#b77322'",
	  "'#16d620'", "'#b91383'", "'#f4359e'",
	  "'#9c5935'", "'#a9c413'", "'#2a778d'",
	  "'#668d1c'", "'#bea413'", "'#0c5922'",
	  "'#743411'" 
	};
		
	protected Double saldoCorriente;
	protected Double montoAPagarCorriente;
	protected Double maxCredCorriente;
	protected Double pagoPeriodicoCorriente;
	protected Double saldoVencidoCorriente;
	protected Double LimiteMaxCorriente;	
	protected Double saldoAtraso;
	protected Double montoAPagarAtraso;
	protected Double maxCredAtraso;
	protected Double pagoPeriodicoAtrasado;
	protected Double saldoVencidoAtrasado;
	protected Double LimiteMaxAtrasado;
	protected Double 	maxGridD;
		
	protected Integer maxGrid;
	protected Integer recommendation_id;	
	protected Integer consultasKubo;
	protected Integer consultasOtras;
	protected Integer consultas_propias;
	
	protected Integer consultasKuboULT;
	protected Integer consultasOtrasULT;
	protected Integer consultas_propiasULT;
	
	protected Integer consultasKuboMS;
	protected Integer consultasOtrasMS;
	protected Integer consultas_propiasMS;
	
	protected long difBr_2;
	protected long difWS_2;
	protected long difAlert1_2;
	protected long difAlert_2;
	protected long difCns_R_2;
	protected long difCns_M6_2;
	protected long difT;
	
	protected int numCorriente;
	protected int numAtraso;
	protected int prospectus_id;
	protected int company_id;
	protected int totalConsult;
	protected int noActivas;
	protected int n;	
	protected int rangoAnyos;
	protected int rango;
	protected int diasAnyo;
	
	protected final int NOTA_DEL_COACH = 13;
	protected final int LAST = 0;
	
	protected final char SEPARADOR = ' ';

	protected boolean initModal;
	protected boolean flgNtSz;
	protected boolean hasSolNum;
	protected boolean idProvider;
	protected boolean showInputId;
	protected boolean diferentUsr;
	protected boolean warningNombreAcreditadoHomogeneo;
	protected boolean warningRFCAcreditadoHomogeneo;
	protected boolean validTransUnion;
	protected boolean flagRules;
	protected boolean is_sesion_ENABLED;
	protected boolean is_prospectus_OK;
	protected boolean is_promotor_ENABLED;
	protected boolean nota_coach_ENABLED;
	
	protected boolean recargarGrafica;
			
	protected ChartBackBeanDMO()
	{
		validTransUnion = true ;
		flagRules   = false;
		showInputId = false;
		diferentUsr = false;
		warningNombreAcreditadoHomogeneo = false;
		warningRFCAcreditadoHomogeneo    = false;
		is_sesion_ENABLED = false;
		is_prospectus_OK  = false;
		
		prospectus_id     = 0;
		company_id        = 0;
		consultasKubo     = 0;
		consultasOtras    = 0;
		consultas_propias = 0;
		maxGrid           = 4;
		
		chartModel   	 = "";
		burSolNum    	 = "";
		scriptAction 	 = "";
		colors 		     = "";
		promotorIDOfMail = "";
		
		fechaMaxGrafica     = null;
		naturalPersonOfMail = null;
		htFecVen            = null;
		htFecMaxReporte     = null;
		lstBanks            = null;
		
		locale = new Locale("es","mx");
		
		dec = NumberFormat.getCurrencyInstance(locale);
		num = NumberFormat.getNumberInstance(locale);
		
		fm  	    = new SimpleDateFormat("dd/MM/yyyy");
		fm2         = new SimpleDateFormat("dd'/'MMM'/'yyyy", new Locale("ES"));
		fm3 	    = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");				
		formatStr   = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));			
		date_format = new SimpleDateFormat("dd / MM / yyyy");
		
		arFecVen = new ArrayList<String[]>();
	}

	public final void setProyectLoanService(ProyectLoanService service) 
	{
		proyectLoanService = service;
	}

	public final void setNoteService(NotesService service) 
	{
		noteService = service;
	}

	public final void setBankService(BankService service) 
	{
		bankService = service;
	}

	public final void setRecommendationservice(RecommendationTypeService service) 
	{
		recommendationservice = service;
	}

	public final void setIdentifiedcreditservice(IdentifiedCreditService service) 
	{
		identifiedcreditservice = service;
	}

	public final void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}

	public final void setChangeControlService(Change_controlService service) 
	{
		changeControlService = service;
	}

	public final void setNaturalPersonService(NaturalPersonService service) 
	{
		naturalPersonService = service;
	}

	public final void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public final void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public final void setSimulationConfigService(SimulationConfigService service) 
	{
		simulationConfigService = service;
	}
	
	public final Notes getNota_del_coach() 
	{
		return nota_del_coach;
	}
	
	public final String getCoach_nombre() 
	{
		return coach_nombre;
	}
	
	public final String getCoach_fecha_nota() 
	{
		return coach_fecha_nota;
	}
	
	public final boolean isNota_coach_ENABLED() 
	{
		return nota_coach_ENABLED;
	}
	
	public BurResume getBur() {
		return bur;
	}
	
	public void setBur(BurResume bur) {
		this.bur = bur;
	}

	public int getNumCorriente() {
		return numCorriente;
	}

	public void setNumCorriente(int numCorriente) {
		this.numCorriente = numCorriente;
	}

	public int getNumAtraso() {
		return numAtraso;
	}

	public void setNumAtraso(int numAtraso) {
		this.numAtraso = numAtraso;
	}

	public int getProspectus_id() {
		return prospectus_id;
	}

	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getTotalConsult() {
		return totalConsult;
	}

	public void setTotalConsult(int totalConsult) {
		this.totalConsult = totalConsult;
	}

	public boolean isWarningNombreAcreditadoHomogeneo() 
	{
		return warningNombreAcreditadoHomogeneo;
	}

	public void setWarningNombreAcreditadoHomogeneo(boolean bandera) 
	{
		warningNombreAcreditadoHomogeneo = bandera;
	}

	public boolean isWarningRFCAcreditadoHomogeneo() 
	{
		return warningRFCAcreditadoHomogeneo;
	}

	public void setWarningRFCAcreditadoHomogeneo(boolean bandera) 
	{
		warningRFCAcreditadoHomogeneo = bandera;
	}

	public boolean isInitModal() {
		return initModal;
	}

	public void setInitModal(boolean initModal) {
		this.initModal = initModal;
	}

	public boolean isFlgNtSz() {
		return flgNtSz;
	}

	public void setFlgNtSz(boolean flgNtSz) {
		this.flgNtSz = flgNtSz;
	}

	public boolean isShowInputId() {
		return showInputId;
	}

	public void setShowInputId(boolean showInputId) {
		this.showInputId = showInputId;
	}

	public boolean isDiferentUsr() {
		return diferentUsr;
	}

	public boolean isHasSolNum() {
		return hasSolNum;
	}

	public void setHasSolNum(boolean hasSolNum) {
		this.hasSolNum = hasSolNum;
	}
	
	public SessionBean getSesion()
	{
		return sesion;
	}

	public final boolean isIdProvider() 
	{
		return idProvider;
	}

	public void setIdProvider(boolean idProvider) {
		this.idProvider = idProvider;
	}

	public String getChartModel() {
		return chartModel;
	}

	public void setChartModel(String chartModel) {
		this.chartModel = chartModel;
	}

	public String getBurSolNum() {
		return burSolNum;
	}

	public final void setBurSolNum(String burSolNum) 
	{
		this.burSolNum = burSolNum;
	}

	public String getScriptAction() {
		return scriptAction;
	}

	public void setScriptAction(String scriptAction) {
		this.scriptAction = scriptAction;
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}

	public String getPromotorIDOfMail() {
		return promotorIDOfMail;
	}

	public void setPromotorIDOfMail(String promotorIDOfMail) {
		this.promotorIDOfMail = promotorIDOfMail;
	}

	public String getFecConsult() {
		return fecConsult;
	}

	public void setFecConsult(String fecConsult) {
		this.fecConsult = fecConsult;
	}

	public String getDiasTrans() {
		return diasTrans;
	}

	public void setDiasTrans(String diasTrans) {
		this.diasTrans = diasTrans;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTableVigData() {
		return tableVigData;
	}

	public void setTableVigData(String tableVigData) {
		this.tableVigData = tableVigData;
	}

	public String getTableVigDataCom() {
		return tableVigDataCom;
	}

	public void setTableVigDataCom(String tableVigDataCom) {
		this.tableVigDataCom = tableVigDataCom;
	}

	public String getTableConsultUltData() {
		return tableConsultUltData;
	}

	public void setTableConsultUltData(String tableConsultUltData) {
		this.tableConsultUltData = tableConsultUltData;
	}

	public String getTableConsultMSData() {
		return tableConsultMSData;
	}

	public void setTableConsultMSData(String tableConsultMSData) {
		this.tableConsultMSData = tableConsultMSData;
	}

	public String getTableDomicilios() {
		return tableDomicilios;
	}

	public void setTableDomicilios(String tableDomicilios) {
		this.tableDomicilios = tableDomicilios;
	}

	public String getTableCredCerr() {
		return tableCredCerr;
	}

	public void setTableCredCerr(String tableCredCerr) {
		this.tableCredCerr = tableCredCerr;
	}

	public String getTableCredCerr_M_6() {
		return tableCredCerr_M_6;
	}

	public void setTableCredCerr_M_6(String tableCredCerr_M_6) {
		this.tableCredCerr_M_6 = tableCredCerr_M_6;
	}

	public String getFolioNum() {
		return folioNum;
	}

	public void setFolioNum(String folioNum) {
		this.folioNum = folioNum;
	}

	public String getFecNac() {
		return fecNac;
	}

	public void setFecNac(String fecNac) {
		this.fecNac = fecNac;
	}

	public String getMaxWidthTableVig() {
		return maxWidthTableVig;
	}

	public void setMaxWidthTableVig(String maxWidthTableVig) {
		this.maxWidthTableVig = maxWidthTableVig;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getSaldoAtrasoStr() {
		return saldoAtrasoStr;
	}

	public void setSaldoAtrasoStr(String saldoAtrasoStr) {
		this.saldoAtrasoStr = saldoAtrasoStr;
	}

	public String getMontoAPagarAtrasoStr() {
		return montoAPagarAtrasoStr;
	}

	public void setMontoAPagarAtrasoStr(String montoAPagarAtrasoStr) {
		this.montoAPagarAtrasoStr = montoAPagarAtrasoStr;
	}

	public String getMaxCredAtrasoStr() {
		return maxCredAtrasoStr;
	}

	public void setMaxCredAtrasoStr(String maxCredAtrasoStr) {
		this.maxCredAtrasoStr = maxCredAtrasoStr;
	}

	public String getPagoPeriodicoAtrasadoStr() {
		return pagoPeriodicoAtrasadoStr;
	}

	public void setPagoPeriodicoAtrasadoStr(String pagoPeriodicoAtrasadoStr) {
		this.pagoPeriodicoAtrasadoStr = pagoPeriodicoAtrasadoStr;
	}

	public String getSaldoVencidoAtrasadoStr() {
		return saldoVencidoAtrasadoStr;
	}

	public void setSaldoVencidoAtrasadoStr(String saldoVencidoAtrasadoStr) {
		this.saldoVencidoAtrasadoStr = saldoVencidoAtrasadoStr;
	}

	public String getLimiteMaxAtrasadoStr() {
		return limiteMaxAtrasadoStr;
	}

	public void setLimiteMaxAtrasadoStr(String limiteMaxAtrasadoStr) {
		this.limiteMaxAtrasadoStr = limiteMaxAtrasadoStr;
	}

	public String getIncremento1() {
		return incremento1;
	}

	public void setIncremento1(String incremento1) {
		this.incremento1 = incremento1;
	}

	public String getIncremento2() {
		return incremento2;
	}

	public void setIncremento2(String incremento2) {
		this.incremento2 = incremento2;
	}

	public String gettScore() {
		return tScore;
	}

	public void settScore(String tScore) {
		this.tScore = tScore;
	}

	public String getMemberSelect() {
		return memberSelect;
	}

	public void setMemberSelect(String memberSelect) {
		this.memberSelect = memberSelect;
	}

	public String getRecomendation_txt() {
		return recomendation_txt;
	}

	public void setRecomendation_txt(String recomendation_txt) {
		this.recomendation_txt = recomendation_txt;
	}

	public String getScriptJSon() {
		return scriptJSon;
	}

	public void setScriptJSon(String scriptJSon) {
		this.scriptJSon = scriptJSon;
	}

	public String getKeyStrJS() {
		return keyStrJS;
	}

	public void setKeyStrJS(String keyStrJS) {
		this.keyStrJS = keyStrJS;
	}

	public String getValStrJS() {
		return valStrJS;
	}

	public void setValStrJS(String valStrJS) {
		this.valStrJS = valStrJS;
	}

	public String getAccount1() {
		return account1;
	}

	public void setAccount1(String account1) {
		this.account1 = account1;
	}

	public String getAccount2() {
		return account2;
	}

	public void setAccount2(String account2) {
		this.account2 = account2;
	}

	public String getTableAlertas1Str() {
		return tableAlertas1Str;
	}

	public void setTableAlertas1Str(String tableAlertas1Str) {
		this.tableAlertas1Str = tableAlertas1Str;
	}

	public String getTableAlertas2Str() {
		return tableAlertas2Str;
	}

	public void setTableAlertas2Str(String tableAlertas2Str) {
		this.tableAlertas2Str = tableAlertas2Str;
	}

	public String getSaldoCorrienteStr() {
		return saldoCorrienteStr;
	}

	public void setSaldoCorrienteStr(String saldoCorrienteStr) {
		this.saldoCorrienteStr = saldoCorrienteStr;
	}

	public String getMontoAPagarCorrienteStr() {
		return montoAPagarCorrienteStr;
	}

	public void setMontoAPagarCorrienteStr(String montoAPagarCorrienteStr) {
		this.montoAPagarCorrienteStr = montoAPagarCorrienteStr;
	}

	public String getMaxCredCorrienteStr() {
		return maxCredCorrienteStr;
	}

	public void setMaxCredCorrienteStr(String maxCredCorrienteStr) {
		this.maxCredCorrienteStr = maxCredCorrienteStr;
	}

	public String getPagoPeriodicoCorrienteStr() {
		return pagoPeriodicoCorrienteStr;
	}

	public void setPagoPeriodicoCorrienteStr(String pagoPeriodicoCorrienteStr) {
		this.pagoPeriodicoCorrienteStr = pagoPeriodicoCorrienteStr;
	}

	public String getSaldoVencidoCorrienteStr() {
		return saldoVencidoCorrienteStr;
	}

	public void setSaldoVencidoCorrienteStr(String saldoVencidoCorrienteStr) {
		this.saldoVencidoCorrienteStr = saldoVencidoCorrienteStr;
	}

	public String getLimiteMaxCorrienteStr() {
		return limiteMaxCorrienteStr;
	}

	public void setLimiteMaxCorrienteStr(String limiteMaxCorrienteStr) {
		this.limiteMaxCorrienteStr = limiteMaxCorrienteStr;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getColores() {
		return colores;
	}

	public void setColores(String[] colores) {
		this.colores = colores;
	}

	public Vtbur_infocalkubo getInfocalkubo() {
		return infocalkubo;
	}

	public void setInfocalkubo(Vtbur_infocalkubo infocalkubo) {
		this.infocalkubo = infocalkubo;
	}

	public Vtbur_infocredcte_c[] getInfocredcte_c() {
		return infocredcte_c;
	}

	public void setInfocredcte_c(Vtbur_infocredcte_c[] infocredcte_c) {
		this.infocredcte_c = infocredcte_c;
	}

	public Vtbur_infocredcte_m[] getInfocredcte_m() {
		return infocredcte_m;
	}

	public void setInfocredcte_m(Vtbur_infocredcte_m[] infocredcte_m) {
		this.infocredcte_m = infocredcte_m;
	}

	public Vtbur_infocredcte_vig[] getInfocredcte_vig() {
		return infocredcte_vig;
	}

	public void setInfocredcte_vig(Vtbur_infocredcte_vig[] infocredcte_vig) {
		this.infocredcte_vig = infocredcte_vig;
	}

	public Vtbur_infocte getInfocte() {
		return infocte;
	}

	public void setInfocte(Vtbur_infocte infocte) {
		this.infocte = infocte;
	}

	public Vtbur_infodircte[] getInfodircte() {
		return infodircte;
	}

	public void setInfodircte(Vtbur_infodircte[] infodircte) {
		this.infodircte = infodircte;
	}

	public Vtbur_infocnsltms[] getCnsltms() {
		return cnsltms;
	}

	public void setCnsltms(Vtbur_infocnsltms[] cnsltms) {
		this.cnsltms = cnsltms;
	}

	public Vtbur_infocnsltult[] getCnsltult() {
		return cnsltult;
	}

	public void setCnsltult(Vtbur_infocnsltult[] cnsltult) {
		this.cnsltult = cnsltult;
	}

	public Vtbur_infoAlertaInc[] getInfoAlertaInc() {
		return infoAlertaInc;
	}

	public void setInfoAlertaInc(Vtbur_infoAlertaInc[] infoAlertaInc) {
		this.infoAlertaInc = infoAlertaInc;
	}

	public Vtbur_infoAlertaPrev[] getInfoAlertaPrev() {
		return infoAlertaPrev;
	}

	public void setInfoAlertaPrev(Vtbur_infoAlertaPrev[] infoAlertaPrev) {
		this.infoAlertaPrev = infoAlertaPrev;
	}

	public List<Membership> getLstMember() {
		return lstMember;
	}

	public void setLstMember(List<Membership> lstMember) {
		this.lstMember = lstMember;
	}

	public List<RecommendationType> getLstRecommendationType() {
		return lstRecommendationType;
	}

	public void setLstRecommendationType(
			List<RecommendationType> lstRecommendationType) {
		this.lstRecommendationType = lstRecommendationType;
	}

	public List<ConsultingBean> getLstConsultas() {
		return lstConsultas;
	}

	public void setLstConsultas(List<ConsultingBean> lstConsultas) {
		this.lstConsultas = lstConsultas;
	}

	public List<Notes> getLstNotes() {
		return lstNotes;
	}

	public void setLstNotes(List<Notes> lstNotes) {
		this.lstNotes = lstNotes;
	}

	public List<Bank> getLstBanks() {
		return lstBanks;
	}

	public void setLstBanks(List<Bank> lstBanks) {
		this.lstBanks = lstBanks;
	}

	public ArrayList<String[]> getArFecVen() {
		return arFecVen;
	}

	public void setArFecVen(ArrayList<String[]> arFecVen) {
		this.arFecVen = arFecVen;
	}

	public Hashtable<String, String[]> getHtFecVen() {
		return htFecVen;
	}

	public void setHtFecVen(Hashtable<String, String[]> htFecVen) {
		this.htFecVen = htFecVen;
	}

	public Hashtable<String, String[]> getHtFecMaxReporte() {
		return htFecMaxReporte;
	}

	public void setHtFecMaxReporte(Hashtable<String, String[]> htFecMaxReporte) {
		this.htFecMaxReporte = htFecMaxReporte;
	}

	public Integer getMaxGrid() {
		return maxGrid;
	}

	public void setMaxGrid(Integer maxGrid) {
		this.maxGrid = maxGrid;
	}

	public Integer getRecommendation_id() {
		return recommendation_id;
	}

	public void setRecommendation_id(Integer recommendation_id) {
		this.recommendation_id = recommendation_id;
	}

	public Date getFechaMaxGrafica() {
		return fechaMaxGrafica;
	}

	public void setFechaMaxGrafica(Date fechaMaxGrafica) {
		this.fechaMaxGrafica = fechaMaxGrafica;
	}

	public Membership getThisMember() {
		return thisMember;
	}

	public void setThisMember(Membership thisMember) {
		this.thisMember = thisMember;
	}

	public Membership getAcreditado() {
		return acreditado;
	}

	public void setAcreditado(Membership acreditado) {
		this.acreditado = acreditado;
	}

	public Notes getAddNote() {
		return addNote;
	}

	public void setAddNote(Notes addNote) {
		this.addNote = addNote;
	}

	public NaturalPerson getNaturalPersonOfMail() {
		return naturalPersonOfMail;
	}

	public void setNaturalPersonOfMail(NaturalPerson naturalPersonOfMail) {
		this.naturalPersonOfMail = naturalPersonOfMail;
	}

	public Date getdFecConsult() {
		return dFecConsult;
	}

	public void setdFecConsult(Date dFecConsult) {
		this.dFecConsult = dFecConsult;
	}

	public Date getBur_fec_credantiguo() {
		return bur_fec_credantiguo;
	}

	public void setBur_fec_credantiguo(Date bur_fec_credantiguo) {
		this.bur_fec_credantiguo = bur_fec_credantiguo;
	}

	public Date getBur_fecBuro() {
		return bur_fecBuro;
	}

	public void setBur_fecBuro(Date bur_fecBuro) {
		this.bur_fecBuro = bur_fecBuro;
	}

	public Bur_spca getSpca() {
		return spca;
	}

	public void setSpca(Bur_spca spca) {
		this.spca = spca;
	}

	public Bur_maxoto getMaxoto() {
		return maxoto;
	}

	public void setMaxoto(Bur_maxoto maxoto) {
		this.maxoto = maxoto;
	}

	public int getNoActivas() {
		return noActivas;
	}

	public void setNoActivas(int noActivas) {
		this.noActivas = noActivas;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public NumberFormat getDec() {
		return dec;
	}

	public void setDec(NumberFormat dec) {
		this.dec = dec;
	}

	public NumberFormat getNum() {
		return num;
	}

	public void setNum(NumberFormat num) {
		this.num = num;
	}

	public String getAbc() {
		return abc;
	}
	

	public String getRfcAcreditado() {
		return rfcAcreditado;
	}

	public void setRfcAcreditado(String rfcAcreditado) {
		this.rfcAcreditado = rfcAcreditado;
	}

	public String getNombreAcreditado() {
		return nombreAcreditado;
	}
	

	public void setNombreAcreditado(String nombreAcreditado) {
		this.nombreAcreditado = nombreAcreditado;
	}

	public NotasDelCasoIMO getNotasDelCaso() {
		return notasDelCaso;
	}

	public void setNotasDelCaso(NotasDelCasoIMO notasDelCaso) {
		this.notasDelCaso = notasDelCaso;
	}

	public String getScriptSaldos() {
		return scriptSaldos;
	}

	public void setScriptSaldos(String scriptSaldos) {
		this.scriptSaldos = scriptSaldos;
	}

	public TransunionRespService getTransunionrespservice() {
		return transunionrespservice;
	}

	public void setTransunionrespservice(TransunionRespService transunionrespservice) {
		this.transunionrespservice = transunionrespservice;
	}

	public TransunionResp getTransunion() {
		return transunion;
	}

	public void setTransunion(TransunionResp transunion) {
		this.transunion = transunion;
	}

	public String getTransErrorMsg() {
		return transErrorMsg;
	}

	public void setTransErrorMsg(String transErrorMsg) {
		this.transErrorMsg = transErrorMsg;
	}

	public boolean isValidTransUnion() {
		return validTransUnion;
	}

	public void setValidTransUnion(boolean validTransUnion) {
		this.validTransUnion = validTransUnion;
	}

	public String getTableRulesStr() {
		return tableRulesStr;
	}

	public void setTableRulesStr(String tableRulesStr) {
		this.tableRulesStr = tableRulesStr;
	}

	public boolean isFlagRules() {
		return flagRules;
	}

	public void setFlagRules(boolean flagRules) {
		this.flagRules = flagRules;
	}

	public Integer getConsultasKubo() {
		return consultasKubo;
	}

	public void setConsultasKubo(Integer consultasKubo) {
		this.consultasKubo = consultasKubo;
	}

	public Integer getConsultasOtras() {
		return consultasOtras;
	}

	public void setConsultasOtras(Integer consultasOtras) {
		this.consultasOtras = consultasOtras;
	}

	public final Integer getConsultas_propias() 
	{
		return consultas_propias;
	}

	public final String getCci_score() 
	{
		return cci_score;
	}

	public final Character getKubo_score_letter() 
	{
		return kubo_score_letter;
	}

	public final Character getKubo_score_number() 
	{
		return kubo_score_number;
	}

	public final String getKubo_score_class() 
	{
		return kubo_score_class;
	}

	public ProyectLoan getProyecto() {
		return proyecto;
	}

	public void setProyecto(ProyectLoan proyecto) {
		this.proyecto = proyecto;
	}

	public Integer getConsultasKuboULT() {
		return consultasKuboULT;
	}

	public void setConsultasKuboULT(Integer consultasKuboULT) {
		this.consultasKuboULT = consultasKuboULT;
	}

	public Integer getConsultasOtrasULT() {
		return consultasOtrasULT;
	}

	public void setConsultasOtrasULT(Integer consultasOtrasULT) {
		this.consultasOtrasULT = consultasOtrasULT;
	}

	public Integer getConsultas_propiasULT() {
		return consultas_propiasULT;
	}

	public void setConsultas_propiasULT(Integer consultas_propiasULT) {
		this.consultas_propiasULT = consultas_propiasULT;
	}

	public Integer getConsultasKuboMS() {
		return consultasKuboMS;
	}

	public void setConsultasKuboMS(Integer consultasKuboMS) {
		this.consultasKuboMS = consultasKuboMS;
	}

	public Integer getConsultasOtrasMS() {
		return consultasOtrasMS;
	}

	public void setConsultasOtrasMS(Integer consultasOtrasMS) {
		this.consultasOtrasMS = consultasOtrasMS;
	}

	public Integer getConsultas_propiasMS() {
		return consultas_propiasMS;
	}

	public void setConsultas_propiasMS(Integer consultas_propiasMS) {
		this.consultas_propiasMS = consultas_propiasMS;
	}			
	
	public String getIdentifyStr() {
		return identifyStr;
	}

	public void setIdentifyStr(String identifyStr) {
		this.identifyStr = identifyStr;
	}
	
	public BuroCacheService getBurocacheservice() {
		return burocacheservice;
	}

	public void setBurocacheservice(BuroCacheService burocacheservice) {
		this.burocacheservice = burocacheservice;
	}

	public boolean isRecargarGrafica() {
		return recargarGrafica;
	}

	public void setRecargarGrafica(boolean recargarGrafica) {
		this.recargarGrafica = recargarGrafica;
	}

	public RoleFunctionService getRoleFunctionService() {
		return roleFunctionService;
	}

	public void setRoleFunctionService(RoleFunctionService roleFunctionService) {
		this.roleFunctionService = roleFunctionService;
	}

	public ScoringService getScoringService() {
		return scoringService;
	}

	public void setScoringService(ScoringService scoringService) {
		this.scoringService = scoringService;
	}
	
}
