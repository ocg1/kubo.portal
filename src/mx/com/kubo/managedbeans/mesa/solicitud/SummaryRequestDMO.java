package mx.com.kubo.managedbeans.mesa.solicitud;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ConsultingBean;
import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.bean.ImagesBean;
import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.ListInvestor;
import mx.com.kubo.bean.PersonOtherFamily;
import mx.com.kubo.bean.ProyectBean;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.bean.ShowChangeSession;
import mx.com.kubo.managedbeans.ApplicationParams;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.InvestmentList;
import mx.com.kubo.managedbeans.mesa.solicitud.estatus.CasosPospuestosIMO;
import mx.com.kubo.managedbeans.mesa.solicitud.notas.NotasDelCasoIMO;
import mx.com.kubo.managedbeans.mesa.solicitud.perfil.FondeadorIMO;
import mx.com.kubo.mesa.solicitud.ReporteInusualIMO;
import mx.com.kubo.mesa.solicitud.documentacion.DocumentacionIMO;
//import mx.com.kubo.mesa.solicitud.investor.ActivadorIMO;
import mx.com.kubo.mesa.solicitud.resumen.purpose.EditorPurposeIMO;
import mx.com.kubo.mesa.solicitud.resumen.rate.EditorCommissionIMO;
import mx.com.kubo.mesa.solicitud.resumen.rate.EditorRateIMO;
import mx.com.kubo.mesa.solicitud.resumen.score.BuroReprocessIMO;
import mx.com.kubo.mesa.solicitud.resumen.score.EditorScoreIMO;
import mx.com.kubo.mesa.solicitud.resumen.loantype.EditorTipoCreditoIMO;
import mx.com.kubo.mesa.solicitud.perfil.ActividadEconomicaIMO;
import mx.com.kubo.mesa.solicitud.perfil.EditorIdentificationIMO;
import mx.com.kubo.mesa.solicitud.promo.PromocionIMO;
import mx.com.kubo.mesa.solicitud.telefonos.TelefonosIMO;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.ApprovalCredit;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.FileType;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FraudeDetection;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.model.ListPorc;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.LoanNegotiationPK;
import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Motive;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Offer;
import mx.com.kubo.model.PhoneType;
import mx.com.kubo.model.PospectusComment;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProfileFormValue;
import mx.com.kubo.model.ProfileInv;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectQuestion;
import mx.com.kubo.model.PublicForum;
import mx.com.kubo.model.References;
import mx.com.kubo.model.Referred;
import mx.com.kubo.model.RelatedPersonLoan;
import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.Residence;
import mx.com.kubo.model.RuleExecution;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StatusProyectCat;
import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.TableroNormativoDetallado;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.Tutor;
import mx.com.kubo.model.ViewInvestmetInProyect;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.catalogos.IdentificationType;
import mx.com.kubo.model.mesa.solicitud.notas.NoteType;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.model.mesa.solicitud.notas.NotesPK;
import mx.com.kubo.model.mesa.solicitud.notas.PriorityType;
import mx.com.kubo.model.mesa.solicitud.notas.TipoDeNota;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.mesa.solicitud.perfil.IndicePagoDeudasIMP;
import mx.com.kubo.mesa.solicitud.perfil.curp.EditorCurpIMO;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.DomicilioIMO;
import mx.com.kubo.mesa.solicitud.perfil.domicilio.EditorViviendaIMO;
import mx.com.kubo.mesa.solicitud.perfil.nombre.EditorNombreIMO;
import mx.com.kubo.mesa.solicitud.perfil.rfc.EditorRfcIMO;
import mx.com.kubo.mesa.solicitud.permisos.RoleFunctionIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.AutomaticInvestmentService;
import mx.com.kubo.services.BankService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.CapitalNetoService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.ClabeAccountService;
import mx.com.kubo.services.ContactWayProspectusService;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.services.EflScoreService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.EventNotificationService;
import mx.com.kubo.services.EventService;
import mx.com.kubo.services.ExpensesHistoryService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.FileTypeService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.IncomeDetailHistoryService;
import mx.com.kubo.services.IncomeDetailService;
import mx.com.kubo.services.IncomeHistoryService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.IncomeTypeService;
import mx.com.kubo.services.InstitutionalInvestorService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.LegalLimitService;
import mx.com.kubo.services.LegalStatusService;
import mx.com.kubo.services.LoanNegotiationService;
import mx.com.kubo.services.MaritalStatusService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.MotiveService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.OfferService;
import mx.com.kubo.services.OperationCostHistoryService;
import mx.com.kubo.services.OperationCostTypeService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.PospectusCommentService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProfileFormValueService;
import mx.com.kubo.services.ProfileInvService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectFundingService;
import mx.com.kubo.services.ProyectLoanInfoService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PublicForumService;
import mx.com.kubo.services.RecaService;
import mx.com.kubo.services.ReferencesService;
import mx.com.kubo.services.ReferredService;
import mx.com.kubo.services.RegistrationReasonService;
import mx.com.kubo.services.RelatedPersonLoanService;
import mx.com.kubo.services.RelationShipService;
import mx.com.kubo.services.ResidenceService;
import mx.com.kubo.services.RiskTaskService;
import mx.com.kubo.services.RoleAccessService;
import mx.com.kubo.services.RuleExecutionService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SellingDetailHistoryService;
import mx.com.kubo.services.SellingTypeService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.StackholderRelService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.StatusProyectCatService;
import mx.com.kubo.services.StudyLevelService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TableroNormativoService;
import mx.com.kubo.services.TimeLogService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.TutorService;
import mx.com.kubo.services.catalogos.ServiceCatalogosIMO;
import mx.com.kubo.services.impl.ExpensesTypeServiceImp;
import mx.com.kubo.services.impl.OfferRejectionMotiveServiceImp;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;
import mx.com.kubo.services.mesa.solicitud.estatus.EditorEstatusIMO;
import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;

import org.primefaces.context.RequestContext;

public abstract class SummaryRequestDMO 
implements SummaryRequestIMO
{
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{proyectLoanInfoServiceImp}")
	protected ProyectLoanInfoService proyectloaninfo_service;
	
	@ManagedProperty("#{naturalPersonServiceImp}")
	protected NaturalPersonService service_natural_person;
	
	@ManagedProperty("#{offerRejectionMotiveServiceImp}")
	protected OfferRejectionMotiveServiceImp  offerrejectionmotiveservice;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{fileTypeServiceImp}")
	protected FileTypeService fileTypeService;
	
	@ManagedProperty("#{countryServiceImp}")
	protected CountryService paisService;
	
	@ManagedProperty("#{stateServiceImp}")
	protected StateService estadoService;
	
	@ManagedProperty("#{studyLevelServiceImp}")
	protected StudyLevelService estudioService;
	
	@ManagedProperty("#{legalStatusServiceImp}")
	protected LegalStatusService estadoLegalService;
	
	@ManagedProperty("#{maritalStatusServiceImp}")
	protected MaritalStatusService estadoMaritalService;
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService employmentService;
	
	@ManagedProperty("#{residenceServiceImp}")
	protected ResidenceService residenciaService;
	
	@ManagedProperty("#{addressServiceImp}")
	protected AddressService service_address;
	
	@ManagedProperty("#{townServiceImp}")
	protected TownService ciudadService;
	
	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService businessService;		
	
	@ManagedProperty("#{incomeServiceImp}")
	protected IncomeService ingresosService;
	
	@ManagedProperty("#{incomeTypeServiceImp}")
	protected IncomeTypeService service_income_type;
	
	@ManagedProperty("#{expensesServiceImp}")
	protected ExpensesService egresosService;
	
	@ManagedProperty("#{expensesTypeServiceImp}")
	protected ExpensesTypeServiceImp expensesTypeService;
	
	@ManagedProperty("#{incomeDetailServiceImp}")
	protected IncomeDetailService incomeDetailService;
	
	@ManagedProperty("#{proyectServiceImp}")
	protected ProyectService proyectoService;
	
	@ManagedProperty("#{publicForumServiceImp}")
	protected PublicForumService foroPublicoService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService service_membership;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectoSercive;
	
	@ManagedProperty("#{proyectFundingServiceImp}")
	protected ProyectFundingService financiamientoService;
	
	@ManagedProperty("#{phoneServiceImp}")
	protected PhoneService service_telefono;
	
	@ManagedProperty("#{referencesServiceImp}")
	protected ReferencesService referencesService;
	
	@ManagedProperty("#{clabeAccountServiceImp}")
	protected ClabeAccountService clabeaccountservice;
	
	@ManagedProperty("#{relationShipServiceImp}")
	protected RelationShipService relationShipService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;

	@ManagedProperty("#{loanNegotiationServiceImp}")
	protected LoanNegotiationService negotiationservice;
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;

	@ManagedProperty("#{bankServiceImp}")
	protected BankService bankService;
	
	@ManagedProperty("#{notesServiceImp}")
	protected NotesService noteService;
	
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService prevencionldservice;
	
	@ManagedProperty("#{serviceCallingServiceImp}")
	protected ServiceCallingService servicecallingService;
	
	@ManagedProperty("#{incomeHistoryServiceImp}")
	protected IncomeHistoryService incomehistoryService;
	
	@ManagedProperty("#{incomeDetailHistoryServiceImp}")
	protected IncomeDetailHistoryService incomedetailhistoryService;
	
	@ManagedProperty("#{expensesHistoryServiceImp}")
	protected ExpensesHistoryService expenseshistoryService;
	
	@ManagedProperty("#{operationCostHistoryServiceImp}")
	protected OperationCostHistoryService opertaioncosthistoryService;
	
	@ManagedProperty("#{operationCostTypeServiceImp}")
	protected OperationCostTypeService opertaioncosttypeService;
	
	@ManagedProperty("#{sellingDetailHistoryServiceImp}")
	protected SellingDetailHistoryService sellingdetailhistoryService;
	
	@ManagedProperty("#{sellingTypeServiceImp}")
	protected SellingTypeService sellingtypeservice;
	
	@ManagedProperty("#{registrationReasonServiceImp}")
	protected RegistrationReasonService reasonsService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoreService;
	
	@ManagedProperty("#{statusProyectCatServiceImp}")
	protected StatusProyectCatService service_estatus;
	
	@ManagedProperty("#{eventNotificationServiceImp}")
	protected EventNotificationService eventnotificationservice;
	
	@ManagedProperty("#{institutionalInvestorServiceImp}")
	protected InstitutionalInvestorService service_fondeador;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{eventServiceImp}")
	protected EventService eventService;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{profileFormValueServiceImp}")
	protected ProfileFormValueService profileformvalueservice;
	
	@ManagedProperty("#{profileInvServiceImp}")
	protected ProfileInvService profileinvservice;
	
	@ManagedProperty("#{recaServiceImp}")
	protected RecaService recaservice;		
	
	@ManagedProperty("#{motiveServiceImp}")
	protected MotiveService motiveservice;		
	
	@ManagedProperty("#{clientViewFullNameServiceImp}")
	protected ClientViewFullNameService clientViewFullNameService;
	
	@ManagedProperty("#{tutorServiceImp}")
	protected TutorService tutorservice;
	
	@ManagedProperty("#{referredServiceImp}")
	protected ReferredService referredservice;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{roleAccessServiceImp}")
	protected RoleAccessService roleaccessService;
	
	@ManagedProperty("#{automaticInvestmentServiceImp}")
	protected AutomaticInvestmentService automaticinvestmentservice;
	
	@ManagedProperty("#{service_catalogos}")
	protected ServiceCatalogosIMO service_catalogos;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingAccountservice;
	
	@ManagedProperty("#{offerServiceImp}")
	protected OfferService offer_service;
	
	@ManagedProperty("#{eflScoreServiceImp}")
	protected EflScoreService eflscoreservice;
	
	@ManagedProperty("#{pospectusCommentServiceImp}")
	protected PospectusCommentService pospectuscommentservice;
	
	@ManagedProperty("#{contactWayProspectusServiceImp}")
	protected ContactWayProspectusService contactwayprospectusservice;
	
	@ManagedProperty("#{stackholderRelServiceImp}")
	protected StackholderRelService service_accionistas;
	
	@ManagedProperty("#{legalLimitServiceImp}")
	protected LegalLimitService legallimitservice;
	
	@ManagedProperty("#{relatedPersonLoanServiceImp}")
	protected RelatedPersonLoanService relatedpersonloanservice;
	
	@ManagedProperty("#{capitalNetoServiceImp}")
	protected CapitalNetoService capitalnetoservice;
	
	@ManagedProperty("#{tableroNormativoServiceImp}")
	protected TableroNormativoService tableronormativoservice;
	
	@ManagedProperty("#{timeLogServiceImp}")
	protected TimeLogService timelogservice;
	
	@ManagedProperty("#{riskTaskServiceImp}")
	protected RiskTaskService risktaskservice;
	
	@ManagedProperty("#{ruleExecutionServiceImp}")
	protected RuleExecutionService ruleexecutionservice;
	
	protected RequestContext request;
	protected FacesContext   faces;
	protected ExternalContext external;
	protected ELContext      context;
	protected ELResolver     resolver;
	protected HtmlInputText  ajax_inputText;			
	protected HttpServletRequest httpServletRequest;
	
	protected List<RuleExecution> ruleEmpLst;
	protected List<RuleExecution> ruleRelLst;
	
	protected SessionBean         sesion;
	protected SearchSummaySession session_sumary;
	protected RoleFunctionController role_function;
	
	protected FraudeDetection fd;
	
	protected Prospectus    prospecto;
	protected NaturalPerson persona;
	protected NaturalPerson tutor;
	protected NaturalPerson personTutor;
	protected NaturalPerson natural_person;
	protected gnNaturalPersonPK key;
	protected gnNaturalPersonPK natural_person_PK;
	protected MembershipPK  membership_PK;
	protected MembershipPK mpk;
	protected MembershipPK afiliacionId;
	protected Membership    member;
	protected Membership    user;
	protected Membership 	 membershipTemp;
	protected Membership    afiliacion;
	
	protected RelatedPersonLoan relatedProyect;
	
	protected ActividadEconomicaIMO editor_actividad_economica;	
	protected       NotasDelCasoIMO editor_notas;
	protected  EditorTipoCreditoIMO editor_tipo_credito;
	protected          EditorRfcIMO editor_RFC;
	protected         EditorCurpIMO editor_CURP;
	protected     EditorViviendaIMO editor_domicilio;
	protected     EditorViviendaIMO domicilio_fiscal;
	protected          DomicilioIMO domicilio_actividad;
	protected       EditorNombreIMO editor_nombre;
	protected      EditorEstatusIMO editor_estatus;	
	protected    CasosPospuestosIMO casosPospuestos;			
	protected   IndicePagoDeudasIMP indice;	
	protected          FondeadorIMO fondeador;
	protected      DocumentacionIMO documentacion;
	protected        NotificadorIMO notificador;
	protected     ReporteInusualIMO inusual;
	protected          TelefonosIMO telefono;
	protected          PromocionIMO check;
	protected 	EditorIdentificationIMO editor_identification;
	protected      EditorPurposeIMO editor_purpose;
	protected         EditorRateIMO editor_rate;
	protected         EditorRateIMO editor_rate_investor;
	protected   EditorCommissionIMO editor_commission;
	protected        EditorScoreIMO editor_score;
	protected      BuroReprocessIMO buro_reprocess;
	protected       RoleFunctionIMO permisos;
	//protected          ActivadorIMO activador;
	
	protected Proyect 		 proyecto;
	protected ProyectLoan   actualProyect = null;	
	protected ProyectLoanPK proyect_loan_PK;
	
	protected Scoring score;
	
	protected Notes   addNote;
	protected Notes   editNote;
	protected Notes   lastNote;
	protected NotesPK note_PK;
	
	protected TipoDeNota          tipo_de_nota;
	protected EstatusProyectLoan  estatus_ORIGINAL;
	protected EstatusProyectLoan  estatus_NEW;
	
	protected Residence 	     residencia;
	protected Address 		     direccion;
	protected TownCat           ciudad;
	protected TownCatPK         ciudadId;
	protected Country           pais;
	protected StateCat          estado;
	protected StateCat          estadoDireccion;
	
	protected Study_Level       estudios;
	protected Legal_Status      estadoLegal;
	protected Marital_Status    estadoMarital;
	protected PersonOtherFamily personFamily;
	protected RelationShip relationShip;
	
	protected Income checkOtherIncome;
	protected Income checkInOtherFamily;
	protected ExpensesBean 	 ammountConsolidate;
	protected ExpensesBean expensesBean;
	
	protected PrevencionLD      prevencionLD;	
	protected ApplicationParams applicationParam;
	protected Income incomeBussinness;	
	protected Referred		 referredIni;
	protected Tutor 	     thisTutor;
	protected LoanNegotiationPK lnpk;
	protected PublicForum 	     foro;
	protected Investor   investor;
	protected InvestorPK invPk;
	protected IncomeDetail      incomedetail;	
	protected SystemParam       thisSystem;
	protected LoanNegotiation   negotiation;	
	protected ClabeAccount      claveaccount;	
	protected ProfileInv        profile;
	protected SelectItem        typeF;
	protected Offer			 offer;
	
	protected InvestmentList	 investmentList;
	protected ItemLoanList itemSel;	
	
	protected File newFile;
	protected ImagesBean imageLogo1;
	protected ImagesBean imageLogo2;
	protected ImagesBean imageLogo3;
	
	protected ShowChangeSession showchange;
	
	protected ChangeBean changeBankData;
	protected ChangeBean changeConsolidate;
	protected ChangeBean changeReasons;
	protected ChangeBean changeStatusProyect;
	protected ChangeBean changeReferred;	
	
	protected TableroNormativoDetallado tableronormativodetallado;
	
	protected Change_control lastChange;
	
	protected StringBuilder sb;
	
	protected Character verificationClass;
	protected Character funding_type;

	protected Date changeSignatureDate;
	
	protected Date fechaRuleEmp;
	protected Date fechaRuleRel;
	
	protected Calendar mD01;
	protected Calendar mD02;
	protected Calendar i1;
	protected Calendar i2;	
	protected Calendar c1, c2, c3, c4, c5;
	protected Calendar c6, c7, c8, c9;
	protected Calendar today;
	protected Calendar fecha_leida;
	
	protected Locale locale = new Locale("es","mx");
	
	protected NumberFormat dec = NumberFormat.getCurrencyInstance(locale);
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);
	protected SimpleDateFormat fm;
	protected SimpleDateFormat fm1;
	protected SimpleDateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
	protected SimpleDateFormat formatter_date_complete = new SimpleDateFormat("dd 'de' MMM  'de' yyyy");
	
	protected String monthStr[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
	
	protected List <Employment>    listEmployment;
	protected List <Business> 	   listBusiness;
	protected List <Income> 	   listIncome;
	protected List <IncomeBean>   listIncomeBean;
	protected List <Expenses> 	   listExpenses;
	protected List <ExpensesBean> listExpensesBean;
	protected List <ExpensesType>  listExpensesType;	
	protected List <IncomeType>    listIncomeType;
	protected List <Files>		listIncomeFiles;
	protected List <PublicForum>  listPublicForum;
	protected List <PriorityType>  lstNotePrioriType;	
	protected List<ViewInvestmetInProyect>   listInvestors;
	protected List <References>   listReference;
	protected List <PhoneType>    listPhoneType;
	protected List <PhoneType>    listTempPhoneType;
	protected List<ProyectLoan> lstPL;
	protected List <ListPorc>     listPorcClientBean;
	protected List <ProyectBean>  lstPry;
	protected List <ConsultingBean> lstConsultas;
	protected List <ClabeAccount>   claveaccountlst;
	protected List <String>         selectedUnrealizedQuestions;
	protected List <NaturalPerson>    imTutor;
	protected List <Motive>   lista_motivos_cambio_estatus;
	protected List <Notes>    lstNoteByProspect;
	protected List <NoteType> lstNoteType;
	protected List <IdentificationType> lista_identification_type;
	protected List <FileType>    lFileType;
	protected List <SelectItem>  lisImgProyect;
	protected List <SelectItem>  ListaF;	
	protected List <StatusProyectCat>       lista_cambio_estatus;
	protected List <ProyectQuestion>        listUnrealizedQuestions;	
	protected List <IncomeDetailsBean> 	 listBusinessDetails;
	protected List <ProfileFormValue> profileformvaluelist;	
	protected List<Tutor> lstTutor;
	protected List<PospectusComment> lstcomm ;
	protected List<ApprovalCredit> lstApproval;
	protected List<Change_control> listChangeInvestor;
	protected List<Change_control> lstTemChangeClabe;
	protected List<Change_control> listChangConsolTemp;
	protected List<Change_control> listChangeReason;
	
	protected ArrayList<ListInvestor> listInvestor_lst;
	
	protected ArrayList<String> days  = new ArrayList<String>();
	protected ArrayList<String> meses = new ArrayList<String>();
	protected ArrayList<String> years = new ArrayList<String>();
	protected ArrayList<String> hrs   = new ArrayList<String>();
	
	protected Hashtable<String, List<SelectItem>> htCategFile;		
		
	protected String fondeadorestxt;
	protected String enableDisable;
	protected String responseAux;
	protected String imageSesion;
	protected String imgWPr = "150";
	protected String imgHPr = "150";
	protected String email;
	protected String addressStr;
	protected String latlongMap;
	protected String statusChangeID = "";
	protected String frequencyStr02="";
	protected String logs_navegation;
	protected String description_other_income;
	protected String porcActionUser;
	protected String cadenaProyecto;
	protected String nicknameSesion;
	protected String fullName;
	protected String day;
	protected String month;
	protected String year;
	protected String dateStr;
	protected String hh;
	protected String mm;
	protected String ampm;
	protected String ammount;
	protected String ammountLeft;
	protected String bc_score_range;
	protected String enabledBottomDetail;
	protected String enabledFlag;
	protected String kubo_score_a;
	protected String kubo_score_b;
	protected String kuboScoreLetter;
	protected String kuboScoreNumber;
	protected String payment;
	protected String verificationRange;
	protected String nameProyect;
	protected String imageProyect;
	protected String report;
	protected String country;
	protected String gender;
	protected String nationality;
	protected String state;
	protected String studyLevel;
	protected String statusLegal;
	protected String maritalStatus;
	protected String dependantsNumber;
	protected String lengthResidence;
	protected String residentType;
	protected String townType;
	protected String townName;
	protected String stateAddress;
	protected String computerBusiness;
	protected String computerEmployment;
	protected String computerHome;
	protected String internetBusiness;
	protected String internetEmployment;
	protected String internetHome;
	protected String printer;
	protected String frequency;
	protected String frequencyStr;
	protected String aboutMe;
	protected String proyectName;
	protected String tagLine;
	protected String goal;
	protected String benefits;
	protected String reason;
	protected String businessDescription;
	protected String aboutBusiness;
	protected String experienceDetail;
	protected String sinceWhenDetail;
	protected String businessGoal;
	protected String employmentAbout;
	protected String createButtonQuestion;
	protected String logo;
	protected String alias;
	protected String imagenProspecto;
	protected String proposito;
	protected String noshowRefStr;	
	protected String scoreClass;	
	protected String fecConsulta;	
	protected String montoNegotiation="0";	
	protected String whyChangeDataPromotor;	
	protected String chartValue;	
	protected String ipAddressClient;
	protected String burSolNum;	
	protected String registrationReason ="";
	protected String messageConsultationOff = "";
	protected String messageConsultationOn = "";
	protected String bCDesc;
	protected String inPreg1;
	protected String inPreg2;
	protected String inPreg3;
	protected String inPreg4;
	protected String inPreg5;
	protected String valInver;
	protected String cancelAccountComment;
	protected String transErrorMsg;
	protected String motive_id_selected;		
	protected String category_name;
	protected String file_type_name;
	protected String noteTypeName;		
	protected String estatus_SELECTED;
	protected String descripcion_del_cambio;
	protected String ajax_selected_value;
	protected String motive_descripcion;
	protected String motive_TOKEN;
	protected String tutor_search;
	protected String refered_search;	
	protected String file_creation_date;
	protected String last_update_date;
	protected String first_credit_date;
	protected String isYearss;
	protected String lada_phone;
	protected String sesion_search;
	protected String frequencyInvestment;
	protected String lastInvestment;
	protected String nextInvestment;
	protected String statusAutomaticInvestment;	
	protected String img_status_inv;
	protected String str_status_inv;
	protected String safi_account_id; 
	protected String promotionTitle;
	protected String pagePromo;	
	protected String offer_why_not;
	protected String rejectionMotiveStr;	
	protected String domicilio_pais_origen_TOKEN;
	protected String limiteUDIS = "";
	protected String limiteCapitalNeto = "";
	protected String stackholder_description;
	
	protected final String UNDEFINED = "No definido";	
	
	protected Double totalIncome=0.0;
	protected Double totalExpenses=0.0;
	protected Double amount_founded;
	protected Double bottomPorcent;
	protected Double investment_bite;
	protected Double min_ammount;
	protected Double rate_investor;
	protected Double liquidezReq;
	protected Double liquidezCli;
	protected Double totalExpensesControl=0D;
	protected Double totalIncomeControll=0D;
	protected Double liquidezCliControl=0D;
	protected Double excedenteControl=0D;
	protected Double pagoMen=0D;
	protected Double pagoMenControl=0D;
	protected Double excedente;
	protected Double montoIni;
	protected Double pagoMenIni;
	protected Double liqIni;		
	protected Double pagoIni;	
	protected Double saldoActualInv = 0D;
	protected Double ammounttoInv;
	protected Double provider_total_init;
	protected Double ingresosComprobables = 0D; 
	protected String ingresosComprobablesStr="0.0";
	
	protected Long daysLeft;
	
	protected Integer times_refill_init;		
	protected Integer numCliente;
	protected Integer days_online;
	protected Integer frequency_id;
	protected Integer investors_number;
	protected Integer investorsInt;
	protected Integer term_id;
	protected Integer verification_score;
	protected Integer months;
	protected Integer genderAux;
	protected Integer nationalityAux;
	protected Integer isYears=null;
	protected Integer dependantsNumberAux;
	protected Integer numberQuest;
	protected Integer anchorages;
	protected Integer termInt;
	protected Integer freqIni;
	protected Integer consolidate;
	protected Integer promotor_id;	
	protected Integer registration_reason_id;
	protected Integer displayConsulSuccess = 2;
	protected Integer motive_selected;
	
	protected long li1;
			
	protected int activity_id;
	protected int address_type_id;
	protected int proyect_loan_id;
	protected int proyect_id;
	protected int prospectus_id;
	protected int company_id;
	protected int emisor_prospectus_id;
	protected int acreditado_prospectus_id;
	protected int numberReferences;
	protected int category_id;
	protected int comapny_id;
    protected int motive_id;
    protected int hasOffer;
	
	protected final int IFE = 1;
	protected final int INE = 2;
	protected final int FISCAL = 9;
	protected final int COMERCIANTE = 1;
	protected final int EMPLEO  = 2;
	protected final int NEGOCIO = 3;
	protected final int EMPRESA = 4;	
	protected final int SCREEN_CONSULTA_SOLICITUD      = 12;
			
	protected boolean fechaPospuestaValida = false;	
	protected boolean displayReport;	
	protected boolean hasNegotiation;	
	protected boolean nameVisible;
	protected boolean female;
	protected boolean dispOKControl;
	protected boolean dispWarnControl;	
	protected boolean dispOKCl;
	protected boolean dispWarnCl;		
	protected boolean dispListPorc=false;
	protected boolean dispListPorcWait = true;
	protected boolean dispBotCondiciones = false;	
	protected boolean dispSendNegotiation = false;	
	protected boolean hasIncomeDeatil;
	protected boolean displayPnlOtherIncome;					
	protected boolean changeBurSolNum = false;	
	protected boolean flagInsert;
	protected boolean alertProyect = false;
	protected boolean alertPerson = false;
	protected boolean validTransUnion = false;
	protected boolean cambio_de_prioridad_OK;
	protected boolean cambio_de_estatus_OK;
	protected boolean guardar_nota_OK;
	protected boolean is_menor;
	protected boolean pais_origen_ENABLED;
	protected boolean estado_cuenta_ENABLED;
	protected boolean domicilio_fiscal_ENABLED;	
	protected boolean acreditado_sin_publicar_ENABLED;
	protected boolean reporte_inusual_ENABLED;
	protected boolean hasReferred;	
	protected boolean flagSolicitud = false;	
	protected boolean showInvestPnl = false;
	protected boolean flagInv;	
	protected boolean hasReinvestment = false;  
	protected boolean flagPromo = false;
	protected boolean hasEflTest = false;
	protected boolean efl_OK = false;
	protected boolean efl_ERROR = false;
	protected boolean blnComment = false ;
	protected boolean haveContactWay = false;
	protected boolean update_OK;
	protected boolean risktask1 = true;			
	protected boolean requireAutorizacionPersonaRelacionada = false;
	protected boolean requireAutorizacionConsejoAdmin = false;
	protected boolean contactWayPhone;
	protected boolean contactWayWhatsApp;
	protected boolean contactWayEmail;
	protected boolean flagSameAddress= false;
	protected boolean superaPorcCapitalNeto;
	protected boolean superaUDIS;

	public final void setMotiveservice(MotiveService service) 
	{
		motiveservice = service;
	}

	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public void  setProyectloaninfo_service ( ProyectLoanInfoService proyectloaninfo_service ){
		this.proyectloaninfo_service = proyectloaninfo_service;
	}
	
	public ProyectLoanInfoService  getProyectloaninfo_service ( ){
		return proyectloaninfo_service ;
	}

	public void setService_natural_person(NaturalPersonService service) 
	{
		service_natural_person = service;
	}

	public void setFilesService(FilesService service) 
	{
		filesService = service;
	}

	public void setFileTypeService(FileTypeService service) 
	{
		fileTypeService = service;
	}

	public void setPaisService(CountryService service)
	{
		paisService = service;
	}

	public void setEstadoService(StateService service) 
	{
		estadoService = service;
	}

	public void setEstudioService(StudyLevelService service)
	{
		estudioService = service;
	}

	public void setEstadoLegalService(LegalStatusService service)
	{
		estadoLegalService = service;
	}

	public void setEstadoMaritalService(MaritalStatusService service) 
	{
		estadoMaritalService = service;
	}

	public void setEmploymentService(EmploymentService service) 
	{
		employmentService = service;
	}

	public void setResidenciaService(ResidenceService service) 
	{
		residenciaService = service;
	}

	public void setService_address(AddressService service) 
	{
		service_address = service;
	}

	public void setCiudadService(TownService service) 
	{
		ciudadService = service;
	}
	
	public void setBusinessService(BusinessService service) 
	{
		businessService = service;
	}

	public void setIngresosService(IncomeService service) 
	{
		ingresosService = service;
	}

	public final void setService_income_type(IncomeTypeService service) 
	{
		service_income_type = service;
	}

	public void setEgresosService(ExpensesService service) 
	{
		egresosService = service;
	}

	public void setExpensesTypeService(ExpensesTypeServiceImp service) 
	{
		expensesTypeService = service;
	}

	public void setIncomeDetailService(IncomeDetailService service) 
	{
		incomeDetailService = service;
	}

	public void setProyectoService(ProyectService service) 
	{
		proyectoService = service;
	}

	public void setForoPublicoService(PublicForumService service) 
	{
		foroPublicoService = service;
	}

	public void setService_membership(MembershipService service) 
	{
		service_membership = service;
	}
	
	public final MembershipService getService_membership() 
	{
		return service_membership;
	}

	public void setProspectoSercive(ProspectusService service) 
	{
		prospectoSercive = service;
	}

	public void setFinanciamientoService(ProyectFundingService service) 
	{
		financiamientoService = service;
	}

	public void setService_telefono(PhoneService service) 
	{
		service_telefono = service;
	}

	public void setReferencesService(ReferencesService service) 
	{
		referencesService = service;
	}

	public void setClabeaccountservice(ClabeAccountService service) 
	{
		clabeaccountservice = service;
	}

	public void setRelationShipService(RelationShipService service) 
	{
		relationShipService = service;
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public void setNegotiationservice(LoanNegotiationService service) 
	{
		negotiationservice = service;
	}

	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}

	public void setBankService(BankService service) 
	{
		bankService = service;
	}

	public void setNoteService(NotesService service) 
	{
		noteService = service;
	}

	public void setPrevencionldservice(PrevencionLDService service) 
	{
		prevencionldservice = service;
	}

	public void setServicecallingService(ServiceCallingService service) 
	{
		servicecallingService = service;
	}

	public void setIncomehistoryService(IncomeHistoryService service) 
	{
		incomehistoryService = service;
	}

	public void setIncomedetailhistoryService(IncomeDetailHistoryService service) 
	{
		incomedetailhistoryService = service;
	}

	public void setExpenseshistoryService(ExpensesHistoryService service) 
	{
		expenseshistoryService = service;
	}

	public void setOpertaioncosthistoryService(OperationCostHistoryService service) 
	{
		opertaioncosthistoryService = service;
	}

	public void setOpertaioncosttypeService(OperationCostTypeService service) 
	{
		opertaioncosttypeService = service;
	}

	public void setSellingdetailhistoryService(SellingDetailHistoryService service) 
	{
		sellingdetailhistoryService = service;
	}

	public void setSellingtypeservice(SellingTypeService service) 
	{
		sellingtypeservice = service;
	}

	public void setReasonsService(RegistrationReasonService service) 
	{
		reasonsService = service;
	}
	
	public final RegistrationReasonService getReasonsService() 
	{
		return reasonsService;
	}

	public void setScoreService(ScoringService service) 
	{
		scoreService = service;
	}

	public void setService_estatus(StatusProyectCatService service) 
	{
		service_estatus = service;
	}

	public void setEventnotificationservice(EventNotificationService service) 
	{
		eventnotificationservice = service;
	}

	public void setService_fondeador(InstitutionalInvestorService service) 
	{
		service_fondeador = service;
	}

	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public void setEventService(EventService service) 
	{
		eventService = service;
	}

	public void setInvestorservice(InvestorService service) 
	{
		investorservice = service;
	}
	
	public void setReferredservice(ReferredService service) 
	{
		referredservice = service;
	}
	
	public void setProfileformvalueservice(ProfileFormValueService service) 
	{
		profileformvalueservice = service;
	}
	
	public void setProfileinvservice(ProfileInvService service) 
	{
		profileinvservice = service;
	}
	
	public void setRecaservice(RecaService service) 
	{
		recaservice = service;
	}
	
	public void setClientViewFullNameService(ClientViewFullNameService service) 
	{
		clientViewFullNameService = service;
	}
	
	public void setTutorservice(TutorService service) 
	{
		tutorservice = service;
	}
	
	public void setRoleaccessService( RoleAccessService service )
	{
		roleaccessService = service;
	}

	public void setScreenservice(ScreenService service) 
	{
		screenservice = service;
	}
	
	public void setService_catalogos(ServiceCatalogosIMO service)
	{
		service_catalogos = service;
	}

	public final CasosPospuestosIMO getCasosPospuestos() 
	{
		return casosPospuestos;
	}
	
	public final EditorViviendaIMO getEditor_domicilio()
	{
		return editor_domicilio;
	}
	
	public final EditorViviendaIMO getDomicilio_fiscal()
	{
		return domicilio_fiscal;
	}
	
	
	public final DomicilioIMO getDomicilio_actividad()
	{
		return domicilio_actividad;
	}
	
	public final NotasDelCasoIMO getNotas() 
	{
		return editor_notas;
	}
	
	public final ActividadEconomicaIMO getActividad_economica() 
	{
		return editor_actividad_economica;
	}
	
	public final EditorNombreIMO getEditor_nombre() 
	{
		return editor_nombre;
	}
	
	public final EditorRfcIMO getEditor_RFC() 
	{
		return editor_RFC;
	}
	
	public final EditorCurpIMO getEditor_CURP() 
	{
		return editor_CURP;
	}
	
	public final EditorTipoCreditoIMO getEditor_tipo_credito() 
	{
		return editor_tipo_credito;
	}
	
	public final IndicePagoDeudasIMP getIndice()
	{
		return indice;
	}

	public final ChangeBean getChangeStatusProyect() 
	{
		return changeStatusProyect;
	}
	
	public final boolean isDomicilio_fiscal_ENABLED() 
	{
		return domicilio_fiscal_ENABLED;
	}
	
	public final boolean isPais_origen_ENABLED()
	{
		return pais_origen_ENABLED;
	}

	public final boolean isEstado_cuenta_ENABLED() 
	{
		return estado_cuenta_ENABLED;
	}
	
	public final boolean isAcreditado_sin_publicar_ENABLED() 
	{
		return acreditado_sin_publicar_ENABLED;
	}
	
	public final boolean isReporte_inusual_ENABLED()
	{
		return reporte_inusual_ENABLED;
	}

	public final boolean isFechaPospuestaValida() 
	{
		return fechaPospuestaValida;
	}

	public final void setFechaPospuestaValida(boolean bandera) 
	{
		fechaPospuestaValida = bandera;
	}
		
	public final Integer getMotive_selected() {
		return motive_selected;
	}

	public final void setMotive_selected(Integer motive_selected) {
		this.motive_selected = motive_selected;
	}

	public void setEditNote(Notes selected)
	{
		//System.out.println("setEditNote()");
		this.editNote = selected;
	}
	
	public List<NoteType> getLstNoteType() {
		return lstNoteType;
	}

	public void setLstNoteType(List<NoteType> lstNoteType) {
		this.lstNoteType = lstNoteType;
	}

	public List<PriorityType> getLstNotePrioriType() {
		return lstNotePrioriType;
	}

	public void setLstNotePrioriType(List<PriorityType> lstNotePrioriType) {
		this.lstNotePrioriType = lstNotePrioriType;
	}

	public List<Notes> getLstNoteByProspect() {
		return lstNoteByProspect;
	}

	public void setLstNoteByProspect(List<Notes> lstNoteByProspect) {
		this.lstNoteByProspect = lstNoteByProspect;
	}
	
	public boolean isDisplayReport() {
		return displayReport;
	}

	public void setDisplayReport(boolean displayReport) {
		this.displayReport = displayReport;
	}

	public boolean isHasNegotiation() {
		return hasNegotiation;
	}

	public void setHasNegotiation(boolean hasNegotiation) {
		this.hasNegotiation = hasNegotiation;
	}

	public boolean isNameVisible() 
	{
		return nameVisible;
	}
	
	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}

	public boolean isDispOKControl() {
		return dispOKControl;
	}

	public void setDispOKControl(boolean dispOKControl) {
		this.dispOKControl = dispOKControl;
	}

	public boolean isDispWarnControl() {
		return dispWarnControl;
	}

	public void setDispWarnControl(boolean dispWarnControl) {
		this.dispWarnControl = dispWarnControl;
	}

	public boolean isDispOKCl() {
		return dispOKCl;
	}

	public void setDispOKCl(boolean dispOKCl) {
		this.dispOKCl = dispOKCl;
	}

	public boolean isDispWarnCl() {
		return dispWarnCl;
	}

	public void setDispWarnCl(boolean dispWarnCl) {
		this.dispWarnCl = dispWarnCl;
	}

	public boolean isDispListPorc() {
		return dispListPorc;
	}

	public void setDispListPorc(boolean dispListPorc) {
		this.dispListPorc = dispListPorc;
	}

	public boolean isDispListPorcWait() {
		return dispListPorcWait;
	}

	public void setDispListPorcWait(boolean dispListPorcWait) {
		this.dispListPorcWait = dispListPorcWait;
	}

	public boolean isDispBotCondiciones() {
		return dispBotCondiciones;
	}

	public void setDispBotCondiciones(boolean dispBotCondiciones) {
		this.dispBotCondiciones = dispBotCondiciones;
	}

	public boolean isDispSendNegotiation() {
		return dispSendNegotiation;
	}

	public void setDispSendNegotiation(boolean dispSendNegotiation) {
		this.dispSendNegotiation = dispSendNegotiation;
	}

	public boolean isHasIncomeDeatil() {
		return hasIncomeDeatil;
	}

	public boolean isDisplayPnlOtherIncome() {
		return displayPnlOtherIncome;
	}



	public boolean isChangeBurSolNum() {
		return changeBurSolNum;
	}

	public void setChangeBurSolNum(boolean changeBurSolNum) {
		this.changeBurSolNum = changeBurSolNum;
	}

	public int getNumberReferences() {
		return numberReferences;
	}

	public void setNumberReferences(int numberReferences) {
		this.numberReferences = numberReferences;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public Integer getDays_online() {
		return days_online;
	}

	public void setDays_online(Integer days_online) {
		this.days_online = days_online;
	}

	public Integer getFrequency_id() {
		return frequency_id;
	}

	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
	}

	public Integer getInvestors_number() {
		return investors_number;
	}

	public void setInvestors_number(Integer investors_number) {
		this.investors_number = investors_number;
	}

	public Integer getInvestorsInt() {
		return investorsInt;
	}

	public void setInvestorsInt(Integer investorsInt) {
		this.investorsInt = investorsInt;
	}

	public Integer getTerm_id() {
		return term_id;
	}

	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}

	public Integer getVerification_score() {
		return verification_score;
	}

	public void setVerification_score(Integer verification_score) {
		this.verification_score = verification_score;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Integer getGenderAux() {
		return genderAux;
	}

	public void setGenderAux(Integer genderAux) {
		this.genderAux = genderAux;
	}

	public Integer getNationalityAux() {
		return nationalityAux;
	}

	public void setNationalityAux(Integer nationalityAux) {
		this.nationalityAux = nationalityAux;
	}



	public Integer getDependantsNumberAux() {
		return dependantsNumberAux;
	}

	public void setDependantsNumberAux(Integer dependantsNumberAux) {
		this.dependantsNumberAux = dependantsNumberAux;
	}

	public Integer getNumberQuest() {
		return numberQuest;
	}

	public void setNumberQuest(Integer numberQuest) {
		this.numberQuest = numberQuest;
	}

	public Integer getAnchorages() {
		return anchorages;
	}

	public void setAnchorages(Integer anchorages) {
		this.anchorages = anchorages;
	}

	public Integer getTermInt() {
		return termInt;
	}

	public void setTermInt(Integer termInt) {
		this.termInt = termInt;
	}

	public Integer getFreqIni() {
		return freqIni;
	}

	public void setFreqIni(Integer freqIni) {
		this.freqIni = freqIni;
	}

	public Integer getConsolidate() {
		return consolidate;
	}

	public void setConsolidate(Integer consolidate) {
		this.consolidate = consolidate;
	}

	public Integer getPromotor_id() {
		return promotor_id;
	}

	public void setPromotor_id(Integer promotor_id) {
		this.promotor_id = promotor_id;
	}

	public Integer getRegistration_reason_id() {
		return registration_reason_id;
	}

	public void setRegistration_reason_id(Integer registration_reason_id) {
		this.registration_reason_id = registration_reason_id;
	}

	public Integer getDisplayConsulSuccess() {
		return displayConsulSuccess;
	}

	public void setDisplayConsulSuccess(Integer displayConsulSuccess) {
		this.displayConsulSuccess = displayConsulSuccess;
	}

	public Long getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(Long daysLeft) {
		this.daysLeft = daysLeft;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public Double getTotalExpenses() {
		return totalExpenses;
	}

	public void setTotalExpenses(Double totalExpenses) {
		this.totalExpenses = totalExpenses;
	}

	public Double getAmount_founded() {
		return amount_founded;
	}

	public void setAmount_founded(Double amount_founded) {
		this.amount_founded = amount_founded;
	}

	public Double getBottomPorcent() {
		return bottomPorcent;
	}

	public void setBottomPorcent(Double bottomPorcent) {
		this.bottomPorcent = bottomPorcent;
	}

	public Double getInvestment_bite() {
		return investment_bite;
	}

	public void setInvestment_bite(Double investment_bite) {
		this.investment_bite = investment_bite;
	}

	public Double getMin_ammount() {
		return min_ammount;
	}

	public void setMin_ammount(Double min_ammount) {
		this.min_ammount = min_ammount;
	}

	public Double getRate_investor() 
	{
		return rate_investor;
	}

	public Double getLiquidezReq() {
		return liquidezReq;
	}

	public void setLiquidezReq(Double liquidezReq) {
		this.liquidezReq = liquidezReq;
	}

	public Double getLiquidezCli() {
		return liquidezCli;
	}

	public void setLiquidezCli(Double liquidezCli) {
		this.liquidezCli = liquidezCli;
	}

	public Double getTotalExpensesControl() {
		return totalExpensesControl;
	}

	public void setTotalExpensesControl(Double totalExpensesControl) {
		this.totalExpensesControl = totalExpensesControl;
	}

	public Double getTotalIncomeControll() {
		return totalIncomeControll;
	}

	public Double getLiquidezCliControl() {
		return liquidezCliControl;
	}

	public void setLiquidezCliControl(Double liquidezCliControl) {
		this.liquidezCliControl = liquidezCliControl;
	}

	public Double getExcedenteControl() {
		return excedenteControl;
	}

	public void setExcedenteControl(Double excedenteControl) {
		this.excedenteControl = excedenteControl;
	}

	public Double getPagoMen() {
		return pagoMen;
	}

	public void setPagoMen(Double pagoMen) {
		this.pagoMen = pagoMen;
	}

	public Double getPagoMenControl() {
		return pagoMenControl;
	}

	public void setPagoMenControl(Double pagoMenControl) {
		this.pagoMenControl = pagoMenControl;
	}

	public Double getExcedente() {
		return excedente;
	}

	public void setExcedente(Double excedente) {
		this.excedente = excedente;
	}

	public Double getMontoIni() {
		return montoIni;
	}

	public void setMontoIni(Double montoIni) {
		this.montoIni = montoIni;
	}

	public Double getPagoMenIni() {
		return pagoMenIni;
	}

	public void setPagoMenIni(Double pagoMenIni) {
		this.pagoMenIni = pagoMenIni;
	}

	public Double getLiqIni() {
		return liqIni;
	}

	public void setLiqIni(Double liqIni) {
		this.liqIni = liqIni;
	}

	public Double getPagoIni() {
		return pagoIni;
	}

	public void setPagoIni(Double pagoIni) {
		this.pagoIni = pagoIni;
	}

	public Character getVerificationClass() {
		return verificationClass;
	}

	public void setVerificationClass(Character verificationClass) {
		this.verificationClass = verificationClass;
	}

	public Character getFunding_type() {
		return funding_type;
	}

	public void setFunding_type(Character funding_type) {
		this.funding_type = funding_type;
	}

	public String getFondeadorestxt() {
		return fondeadorestxt;
	}

	public void setFondeadorestxt(String fondeadorestxt) {
		this.fondeadorestxt = fondeadorestxt;
	}

	public String getEnableDisable() {
		return enableDisable;
	}

	public void setEnableDisable(String enableDisable) {
		this.enableDisable = enableDisable;
	}

	public String getResponseAux() {
		return responseAux;
	}

	public void setResponseAux(String responseAux) {
		this.responseAux = responseAux;
	}

	public String getImageSesion() {
		return imageSesion;
	}

	public void setImageSesion(String imageSesion) {
		this.imageSesion = imageSesion;
	}



	public String getImgWPr() {
		return imgWPr;
	}

	public void setImgWPr(String imgWPr) {
		this.imgWPr = imgWPr;
	}

	public String getImgHPr() {
		return imgHPr;
	}

	public void setImgHPr(String imgHPr) {
		this.imgHPr = imgHPr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {	
		this.email = email;
	}

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
	}


	public String getLatlongMap() {
		return latlongMap;
	}

	public void setLatlongMap(String latlongMap) {
		this.latlongMap = latlongMap;
	}
	
	public String getStatusChangeID() {
		return statusChangeID;
	}

	public void setStatusChangeID(String statusChangeID) {
		this.statusChangeID = statusChangeID;
	}

	public String getFrequencyStr02() {
		return frequencyStr02;
	}

	public void setFrequencyStr02(String frequencyStr02) {
		this.frequencyStr02 = frequencyStr02;
	}

	public String getLogs_navegation() {
		return logs_navegation;
	}

	public void setLogs_navegation(String logs_navegation) {
		this.logs_navegation = logs_navegation;
	}

	public String getDescription_other_income() {
		return description_other_income;
	}

	public String getPorcActionUser() {
		return porcActionUser;
	}

	public void setPorcActionUser(String porcActionUser) {
		this.porcActionUser = porcActionUser;
	}

	public String getCadenaProyecto() {
		return cadenaProyecto;
	}

	public void setCadenaProyecto(String cadenaProyecto) {
		this.cadenaProyecto = cadenaProyecto;
	}

	public String getNicknameSesion() {
		return nicknameSesion;
	}

	public void setNicknameSesion(String nicknameSesion) {
		this.nicknameSesion = nicknameSesion;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getHh() {
		return hh;
	}

	public void setHh(String hh) {
		this.hh = hh;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getAmmountLeft() {
		return ammountLeft;
	}

	public void setAmmountLeft(String ammountLeft) {
		this.ammountLeft = ammountLeft;
	}

	public String getBc_score_range() {
		return bc_score_range;
	}

	public void setBc_score_range(String bc_score_range) {
		this.bc_score_range = bc_score_range;
	}

	public String getEnabledBottomDetail() {
		return enabledBottomDetail;
	}

	public void setEnabledBottomDetail(String enabledBottomDetail) {
		this.enabledBottomDetail = enabledBottomDetail;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getKubo_score_a() {
		return kubo_score_a;
	}

	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}

	public String getKubo_score_b() {
		return kubo_score_b;
	}

	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}

	public String getKuboScoreLetter() {
		return kuboScoreLetter;
	}

	public void setKuboScoreLetter(String kuboScoreLetter) {
		this.kuboScoreLetter = kuboScoreLetter;
	}

	public String getKuboScoreNumber() {
		return kuboScoreNumber;
	}

	public void setKuboScoreNumber(String kuboScoreNumber) {
		this.kuboScoreNumber = kuboScoreNumber;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getVerificationRange() {
		return verificationRange;
	}

	public void setVerificationRange(String verificationRange) {
		this.verificationRange = verificationRange;
	}

	public String getNameProyect() {
		return nameProyect;
	}

	public void setNameProyect(String nameProyect) {
		this.nameProyect = nameProyect;
	}

	public String getImageProyect() {
		return imageProyect;
	}

	public void setImageProyect(String imageProyect) {
		this.imageProyect = imageProyect;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(String studyLevel) {
		this.studyLevel = studyLevel;
	}

	public String getStatusLegal() {
		return statusLegal;
	}

	public void setStatusLegal(String statusLegal) {
		this.statusLegal = statusLegal;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getDependantsNumber() {
		return dependantsNumber;
	}

	public void setDependantsNumber(String dependantsNumber) {
		this.dependantsNumber = dependantsNumber;
	}

	public String getLengthResidence() {
		return lengthResidence;
	}

	public void setLengthResidence(String lengthResidence) {
		this.lengthResidence = lengthResidence;
	}

	public String getResidentType() {
		return residentType;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public String getTownType() {
		return townType;
	}

	public void setTownType(String townType) {
		this.townType = townType;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getStateAddress() {
		return stateAddress;
	}

	public void setStateAddress(String stateAddress) {
		this.stateAddress = stateAddress;
	}

	public String getComputerBusiness() {
		return computerBusiness;
	}

	public void setComputerBusiness(String computerBusiness) {
		this.computerBusiness = computerBusiness;
	}

	public String getComputerEmployment() {
		return computerEmployment;
	}

	public void setComputerEmployment(String computerEmployment) {
		this.computerEmployment = computerEmployment;
	}

	public String getComputerHome() {
		return computerHome;
	}

	public void setComputerHome(String computerHome) {
		this.computerHome = computerHome;
	}

	public String getInternetBusiness() {
		return internetBusiness;
	}

	public void setInternetBusiness(String internetBusiness) {
		this.internetBusiness = internetBusiness;
	}

	public String getInternetEmployment() {
		return internetEmployment;
	}

	public void setInternetEmployment(String internetEmployment) {
		this.internetEmployment = internetEmployment;
	}

	public String getInternetHome() {
		return internetHome;
	}

	public void setInternetHome(String internetHome) {
		this.internetHome = internetHome;
	}

	public String getPrinter() {
		return printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyStr() {
		return frequencyStr;
	}

	public void setFrequencyStr(String frequencyStr) {
		this.frequencyStr = frequencyStr;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getProyectName() {
		return proyectName;
	}

	public void setProyectName(String proyectName) {
		this.proyectName = proyectName;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public String getAboutBusiness() {
		return aboutBusiness;
	}

	public void setAboutBusiness(String aboutBusiness) {
		this.aboutBusiness = aboutBusiness;
	}

	public String getExperienceDetail() {
		return experienceDetail;
	}

	public void setExperienceDetail(String experienceDetail) {
		this.experienceDetail = experienceDetail;
	}

	public String getSinceWhenDetail() {
		return sinceWhenDetail;
	}

	public void setSinceWhenDetail(String sinceWhenDetail) {
		this.sinceWhenDetail = sinceWhenDetail;
	}

	public String getBusinessGoal() {
		return businessGoal;
	}

	public void setBusinessGoal(String businessGoal) {
		this.businessGoal = businessGoal;
	}

	public String getEmploymentAbout() {
		return employmentAbout;
	}

	public void setEmploymentAbout(String employmentAbout) {
		this.employmentAbout = employmentAbout;
	}

	public String getCreateButtonQuestion() {
		return createButtonQuestion;
	}

	public void setCreateButtonQuestion(String createButtonQuestion) {
		this.createButtonQuestion = createButtonQuestion;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImagenProspecto() {
		return imagenProspecto;
	}

	public void setImagenProspecto(String imagenProspecto) {
		this.imagenProspecto = imagenProspecto;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public String getNoshowRefStr() {
		return noshowRefStr;
	}

	public void setNoshowRefStr(String noshowRefStr) {
		this.noshowRefStr = noshowRefStr;
	}

	public String getScoreClass() {
		return scoreClass;
	}	

	public String getFecConsulta() {
		return fecConsulta;
	}

	public void setFecConsulta(String fecConsulta) {
		this.fecConsulta = fecConsulta;
	}

	public String getMontoNegotiation() {
		return montoNegotiation;
	}

	public void setMontoNegotiation(String montoNegotiation) {
		this.montoNegotiation = montoNegotiation;
	}

	public String getWhyChangeDataPromotor() {
		return whyChangeDataPromotor;
	}

	public void setWhyChangeDataPromotor(String whyChangeDataPromotor) {
		this.whyChangeDataPromotor = whyChangeDataPromotor;
	}

	public String getChartValue() {
		return chartValue;
	}

	public void setChartValue(String chartValue) {
		this.chartValue = chartValue;
	}

	public String getIpAddressClient() {
		return ipAddressClient;
	}

	public void setIpAddressClient(String ipAddressClient) {
		this.ipAddressClient = ipAddressClient;
	}

	public String getBurSolNum() {
		return burSolNum;
	}

	public void setBurSolNum(String burSolNum) {
		this.burSolNum = burSolNum;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public void setRegistrationReason(String registrationReason) {
		this.registrationReason = registrationReason;
	}

	public String getMessageConsultationOff() {
		return messageConsultationOff;
	}

	public void setMessageConsultationOff(String messageConsultationOff) {
		this.messageConsultationOff = messageConsultationOff;
	}

	public String getMessageConsultationOn() {
		return messageConsultationOn;
	}

	public void setMessageConsultationOn(String messageConsultationOn) {
		this.messageConsultationOn = messageConsultationOn;
	}

	public String getbCDesc() {
		return bCDesc;
	}



	public Date getChangeSignatureDate() {
		return changeSignatureDate;
	}

	public void setChangeSignatureDate(Date changeSignatureDate) {
		this.changeSignatureDate = changeSignatureDate;
	}
	
	public Date getFechaRuleEmp() {
		return fechaRuleEmp;
	}

	public void setFechaRuleEmp(Date fechaRuleEmp) {
		this.fechaRuleEmp = fechaRuleEmp;
	}
	
	public Date getFechaRuleRel() {
		return fechaRuleRel;
	}

	public void setFechaRuleRel(Date fechaRuleRel) {
		this.fechaRuleRel = fechaRuleRel;
	}

	public ProyectLoan getActualProyect() {
		return actualProyect;
	}

	public void setActualProyect(ProyectLoan actualProyect) {
		this.actualProyect = actualProyect;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public Prospectus getProspecto() {
		return prospecto;
	}

	public void setProspecto(Prospectus prospecto) {
		this.prospecto = prospecto;
	}

	public NaturalPerson getPersona() {
		return persona;
	}

	public void setPersona(NaturalPerson persona) {
		this.persona = persona;
	}
	
	 
	
	public FraudeDetection getFd() {
		return fd;
	}

	public void setFd(FraudeDetection fd) {
		this.fd = fd;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public Membership getUser() {
		return user;
	}

	public void setUser(Membership user) {
		this.user = user;
	}

	public Membership getMembershipTemp() 
	{
		return membershipTemp;
	}

	public Membership getAfiliacion() {
		return afiliacion;
	}

	public void setAfiliacion(Membership afiliacion) {
		this.afiliacion = afiliacion;
	}

	public Country getPais() {
		return pais;
	}

	public void setPais(Country pais) {
		this.pais = pais;
	}

	public StateCat getEstado() {
		return estado;
	}

	public void setEstado(StateCat estado) {
		this.estado = estado;
	}

	public StateCat getEstadoDireccion() {
		return estadoDireccion;
	}

	public void setEstadoDireccion(StateCat estadoDireccion) {
		this.estadoDireccion = estadoDireccion;
	}

	public Study_Level getEstudios() {
		return estudios;
	}

	public void setEstudios(Study_Level estudios) {
		this.estudios = estudios;
	}

	public Legal_Status getEstadoLegal() {
		return estadoLegal;
	}

	public void setEstadoLegal(Legal_Status estadoLegal) {
		this.estadoLegal = estadoLegal;
	}

	public Marital_Status getEstadoMarital() {
		return estadoMarital;
	}

	public void setEstadoMarital(Marital_Status estadoMarital) {
		this.estadoMarital = estadoMarital;
	}

	public PersonOtherFamily getPersonFamily() {
		return personFamily;
	}

	public void setPersonFamily(PersonOtherFamily personFamily) {
		this.personFamily = personFamily;
	}

	
	public ExpensesBean getAmmountConsolidate() {
		return ammountConsolidate;
	}	

	public PrevencionLD getPrevencionLD() {
		return prevencionLD;
	}

	public void setPrevencionLD(PrevencionLD prevencionLD) {
		this.prevencionLD = prevencionLD;
	}

	public Proyect getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyect proyecto) {
		this.proyecto = proyecto;
	}

	public Residence getResidencia() {
		return residencia;
	}

	public void setResidencia(Residence residencia) {
		this.residencia = residencia;
	}

	public Address getDireccion() {
		return direccion;
	}

	public void setDireccion(Address direccion) {
		this.direccion = direccion;
	}

	public TownCat getCiudad() {
		return ciudad;
	}

	public void setCiudad(TownCat ciudad) {
		this.ciudad = ciudad;
	}

	public PublicForum getForo() {
		return foro;
	}

	public void setForo(PublicForum foro) {
		this.foro = foro;
	}

	public Scoring getScore() {
		return score;
	}

	public void setScore(Scoring score) {
		this.score = score;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public IncomeDetail getIncomedetail() {
		return incomedetail;
	}

	public void setIncomedetail(IncomeDetail incomedetail) {
		this.incomedetail = incomedetail;
	}

	public SystemParam getThisSystem() {
		return thisSystem;
	}

	public void setThisSystem(SystemParam thisSystem) {
		this.thisSystem = thisSystem;
	}


	public LoanNegotiation getNegotiation() {
		return negotiation;
	}

	public void setNegotiation(LoanNegotiation negotiation) {
		this.negotiation = negotiation;
	}
	
	public ClabeAccount getClaveaccount() {
		return claveaccount;
	}

	public void setClaveaccount(ClabeAccount claveaccount) {
		this.claveaccount = claveaccount;
	}

	public ImagesBean getImageLogo1() {
		return imageLogo1;
	}

	public void setImageLogo1(ImagesBean imageLogo1) {
		this.imageLogo1 = imageLogo1;
	}

	public ImagesBean getImageLogo2() {
		return imageLogo2;
	}

	public void setImageLogo2(ImagesBean imageLogo2) {
		this.imageLogo2 = imageLogo2;
	}

	public ImagesBean getImageLogo3() {
		return imageLogo3;
	}

	public void setImageLogo3(ImagesBean imageLogo3) {
		this.imageLogo3 = imageLogo3;
	}

	public ChangeBean getChangeBankData() {
		return changeBankData;
	}

	public void setChangeBankData(ChangeBean changeBankData) {
		this.changeBankData = changeBankData;
	}
	
	public List<Employment> getListEmployment() {
		return listEmployment;
	}

	public void setListEmployment(List<Employment> listEmployment) {
		this.listEmployment = listEmployment;
	}

	public List<Business> getListBusiness() {
		return listBusiness;
	}

	public void setListBusiness(List<Business> listBusiness) {
		this.listBusiness = listBusiness;
	}

	public ChangeBean getChangeConsolidate() 
	{
		return changeConsolidate;
	}

	public void setChangeConsolidate(ChangeBean changeConsolidate) 
	{
		this.changeConsolidate = changeConsolidate;
	}

	public ChangeBean getChangeReasons() {
		return changeReasons;
	}

	public void setChangeReasons(ChangeBean changeReasons) {
		this.changeReasons = changeReasons;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public java.text.NumberFormat getDec() {
		return dec;
	}

	public void setDec(java.text.NumberFormat dec) {
		this.dec = dec;
	}

	public String[] getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String[] monthStr) {
		this.monthStr = monthStr;
	}



	public ArrayList<String> getDays() {
		return days;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public ArrayList<String> getMeses() {
		return meses;
	}

	public void setMeses(ArrayList<String> meses) {
		this.meses = meses;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}

	public ArrayList<String> getHrs() {
		return hrs;
	}

	public void setHrs(ArrayList<String> hrs) {
		this.hrs = hrs;
	}

	public List<Income> getListIncome() {
		return listIncome;
	}

	public List<Expenses> getListExpenses() {
		return listExpenses;
	}

	public void setListExpenses(List<Expenses> listExpenses) {
		this.listExpenses = listExpenses;
	}

	public List<IncomeType> getListIncomeType() {
		return listIncomeType;
	}

	public void setListIncomeType(List<IncomeType> listIncomeType) {
		this.listIncomeType = listIncomeType;
	}
	
	public List<Files> getListIncomeFiles() {
		return listIncomeFiles;
	}

	public void setListIncomeFiles(List<Files> listIncomeFiles) {
		this.listIncomeFiles = listIncomeFiles;
	}

	public List<ExpensesType> getListExpensesType() {
		return listExpensesType;
	}

	public void setListExpensesType(List<ExpensesType> listExpensesType) {
		this.listExpensesType = listExpensesType;
	}

	public List<PublicForum> getListPublicForum() {
		return listPublicForum;
	}

	public void setListPublicForum(List<PublicForum> listPublicForum) {
		this.listPublicForum = listPublicForum;
	}

	public List<ProyectQuestion> getListUnrealizedQuestions() {
		return listUnrealizedQuestions;
	}

	public void setListUnrealizedQuestions(
			List<ProyectQuestion> listUnrealizedQuestions) {
		this.listUnrealizedQuestions = listUnrealizedQuestions;
	}

	public List<ViewInvestmetInProyect> getListInvestors() {
		return listInvestors;
	}

	public void setListInvestors(List<ViewInvestmetInProyect> listInvestors) {
		this.listInvestors = listInvestors;
	}



	public List<References> getListReference() {
		return listReference;
	}

	public void setListReference(List<References> listReference) {
		this.listReference = listReference;
	}

	public List<PhoneType> getListPhoneType() {
		return listPhoneType;
	}

	public void setListPhoneType(List<PhoneType> listPhoneType) {
		this.listPhoneType = listPhoneType;
	}



	public List<IncomeDetailsBean> getListBusinessDetails() {
		return listBusinessDetails;
	}

	public void setListBusinessDetails(List<IncomeDetailsBean> listBusinessDetails) {
		this.listBusinessDetails = listBusinessDetails;
	}

	public List<IncomeBean> getListIncomeBean() {
		return listIncomeBean;
	}

	public void setListIncomeBean(List<IncomeBean> listIncomeBean) {
		this.listIncomeBean = listIncomeBean;
	}

	public List<ExpensesBean> getListExpensesBean() {
		return listExpensesBean;
	}

	public void setListExpensesBean(List<ExpensesBean> listExpensesBean) {
		this.listExpensesBean = listExpensesBean;
	}

	public List<ListPorc> getListPorcClientBean() {
		return listPorcClientBean;
	}

	public void setListPorcClientBean(List<ListPorc> listPorcClientBean) {
		this.listPorcClientBean = listPorcClientBean;
	}

	public List<ProyectBean> getLstPry() {
		return lstPry;
	}

	public void setLstPry(List<ProyectBean> lstPry) {
		this.lstPry = lstPry;
	}

	public List<ConsultingBean> getLstConsultas() {
		return lstConsultas;
	}

	public void setLstConsultas(List<ConsultingBean> lstConsultas) {
		this.lstConsultas = lstConsultas;
	}

	public List<ClabeAccount> getClaveaccountlst() {
		return claveaccountlst;
	}

	public void setClaveaccountlst(List<ClabeAccount> claveaccountlst) {
		this.claveaccountlst = claveaccountlst;
	}

	public List<String> getSelectedUnrealizedQuestions() {
		return selectedUnrealizedQuestions;
	}

	public void setSelectedUnrealizedQuestions(
			List<String> selectedUnrealizedQuestions) {
		this.selectedUnrealizedQuestions = selectedUnrealizedQuestions;
	}
		
	public List<ProfileFormValue> getProfileformvaluelist() {
		return profileformvaluelist;
	}

	public void setProfileformvaluelist(List<ProfileFormValue> profileformvaluelist) {
		this.profileformvaluelist = profileformvaluelist;
	}

	public ProfileInv getProfile() {
		return profile;
	}

	public void setProfile(ProfileInv profile) {
		this.profile = profile;
	}

	public String getInPreg1() {
		return inPreg1;
	}

	public void setInPreg1(String inPreg1) {
		this.inPreg1 = inPreg1;
	}

	public String getInPreg2() {
		return inPreg2;
	}

	public void setInPreg2(String inPreg2) {
		this.inPreg2 = inPreg2;
	}

	public String getInPreg3() {
		return inPreg3;
	}

	public void setInPreg3(String inPreg3) {
		this.inPreg3 = inPreg3;
	}

	public String getInPreg4() {
		return inPreg4;
	}

	public void setInPreg4(String inPreg4) {
		this.inPreg4 = inPreg4;
	}

	public String getInPreg5() {
		return inPreg5;
	}

	public void setInPreg5(String inPreg5) {
		this.inPreg5 = inPreg5;
	}

	public String getValInver() {
		return valInver;
	}

	public void setValInver(String valInver) {
		this.valInver = valInver;
	}

	public String getCancelAccountComment() {
		return cancelAccountComment;
	}

	public void setCancelAccountComment(String cancelAccountComment) {
		this.cancelAccountComment = cancelAccountComment;
	}

	public boolean isAlertProyect() {
		return alertProyect;
	}

	public void setAlertProyect(boolean alertProyect) {
		this.alertProyect = alertProyect;
	}

	public boolean isAlertPerson() {
		return alertPerson;
	}

	public void setAlertPerson(boolean alertPerson) {
		this.alertPerson = alertPerson;
	}

	public boolean isValidTransUnion() {
		return validTransUnion;
	}

	public void setValidTransUnion(boolean validTransUnion) {
		this.validTransUnion = validTransUnion;
	}

	public String getTransErrorMsg() {
		return transErrorMsg;
	}

	public void setTransErrorMsg(String transErrorMsg) {
		this.transErrorMsg = transErrorMsg;
	}

	public final String getEstatus_SELECTED() 
	{
		return estatus_SELECTED;
	}

	public final void setEstatus_SELECTED(String selected) 
	{
		estatus_SELECTED = selected;
	}

	public List<StatusProyectCat> getLista_cambio_estatus() 
	{
		return lista_cambio_estatus;
	}
	
	public String getTutor_search() {
		return tutor_search;
	}

	public void setTutor_search(String tutor_search) {
		this.tutor_search = tutor_search;
	}

	public NaturalPerson getTutor() {
		return tutor;
	}

	public void setTutor(NaturalPerson tutor) 
	{
		this.tutor = tutor;
	}

	public final Tutor getThisTutor() 
	{
		return thisTutor;
	}
	
	public final Integer getIsYears() 
	{
		return isYears;
	}
	
	public List<NaturalPerson> getImTutor() 
	{
		return imTutor;
	}

	public NaturalPerson getPersonTutor() 
	{
		return personTutor;
	}

	public void setPersonTutor(NaturalPerson personTutor) {
		this.personTutor = personTutor;
	}

	public boolean isIs_menor() {
		return is_menor;
	}

	public void setIs_menor(boolean is_menor) {
		this.is_menor = is_menor;
	}
	
	public String getRefered_search() {
		return refered_search;
	}

	public void setRefered_search(String refered_search) {
		this.refered_search = refered_search;
	}

	public Referred getReferredIni() {
		return referredIni;
	}

	public void setReferredIni(Referred referredIni) {
		this.referredIni = referredIni;
	}

	public boolean isHasReferred() {
		return hasReferred;
	}

	public void setHasReferred(boolean hasReferred) {
		this.hasReferred = hasReferred;
	}

	public ChangeBean getChangeReferred() {
		return changeReferred;
	}

	public void setChangeReferred(ChangeBean changeReferred) {
		this.changeReferred = changeReferred;
	}

	public String getFile_creation_date() {
		return file_creation_date;
	}

	public void setFile_creation_date(String file_creation_date) {
		this.file_creation_date = file_creation_date;
	}

	public String getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}

	public String getFirst_credit_date() {
		return first_credit_date;
	}

	public void setFirst_credit_date(String first_credit_date) {
		this.first_credit_date = first_credit_date;
	}

	public ArrayList<ListInvestor> getListInvestor_lst() {
		return listInvestor_lst;
	}

	public void setListInvestor_lst(ArrayList<ListInvestor> listInvestor_lst) {
		this.listInvestor_lst = listInvestor_lst;
	}

	public boolean isFlagSolicitud() {
		return flagSolicitud;
	}

	public void setFlagSolicitud(boolean flagSolicitud) {
		this.flagSolicitud = flagSolicitud;
	}

	public Double getSaldoActualInv() {
		return saldoActualInv;
	}

	public void setSaldoActualInv(Double saldoActualInv) {
		this.saldoActualInv = saldoActualInv;
	}

	public InvestmentList getInvestmentList() {
		return investmentList;
	}

	public void setInvestmentList(InvestmentList investmentList) {
		this.investmentList = investmentList;
	}

	public ItemLoanList getItemSel() {
		return itemSel;
	}

	public void setItemSel(ItemLoanList itemSel) {
		this.itemSel = itemSel;
	}

	public Double getAmmounttoInv() {
		return ammounttoInv;
	}

	public void setAmmounttoInv(Double ammounttoInv) {
		this.ammounttoInv = ammounttoInv;
	}
	
	public Double getIngresosComprobables() {
		return ingresosComprobables;
	}

	public void setIngresosComprobables(Double ingresosComprobables) {
		this.ingresosComprobables = ingresosComprobables;
	}
	
	public String getIngresosComprobablesStr() {
		return num.format(ingresosComprobables);
	}

	public void setIngresosComprobablesStr(String ingresosComprobablesStr) {
		this.ingresosComprobablesStr = ingresosComprobablesStr;
	}

	public boolean isShowInvestPnl() {
		return showInvestPnl;
	}

	public void setShowInvestPnl(boolean showInvestPnl) {
		this.showInvestPnl = showInvestPnl;
	}
	
	public FondeadorIMO getFondeador() 
	{
		return fondeador;
	}

	public final DocumentacionIMO getDocumentacion() 
	{
		return documentacion;
	}	
	
	public final ReporteInusualIMO getInusual()
	{
		return inusual;
	}
	
	public final TelefonosIMO getTelefono()
	{
		return telefono;
	}
	
	public final PromocionIMO getCheck()
	{
		return check;
	}
	
	public final EditorIdentificationIMO getEditor_identification()
	{
		return editor_identification;
	}
	
	public EditorPurposeIMO getEditor_purpose()
	{
		return editor_purpose;
	}
	
	public EditorRateIMO getEditor_rate()
	{
		return editor_rate;
	}
	
	public EditorRateIMO getEditor_rate_investor()
	{
		return editor_rate_investor;
	}
	
	public EditorCommissionIMO getEditor_commission()
	{
		return editor_commission;
	}
	
	public EditorScoreIMO getEditor_score()
	{
		return editor_score;
	}
	
	public RoleFunctionIMO getPermisos()
	{
		return permisos;
	}
	
	public final List <IdentificationType> getLista_identification_type()
	{
		return lista_identification_type;
	}
	
	public boolean isHasReinvestment(){
		return hasReinvestment;
	}
	
	public void setHasReinvestment( boolean hasReinvestment ){
		this.hasReinvestment = hasReinvestment;
	}
	
	public String getFrequencyInvestment(){
		return frequencyInvestment;
	}
	
	public void setFrequencyInvestment( String frequencyInvestment ){
		this.frequencyInvestment = frequencyInvestment;
	}
	
	public String getLastInvestment(){
		return lastInvestment;
	}
	
	public void setLastInvestment( String lastInvestment ){
		this.lastInvestment = lastInvestment;
	}
	
	public String getNextInvestment(){
		return nextInvestment;
	}
	
	public void setNextInvestment( String nextInvestment ){
		this.nextInvestment = nextInvestment;
	}
	
	public String getStatusAutomaticInvestment(){
		return statusAutomaticInvestment;
	}
	
	public void setStatusAutomaticInvestment( String statusAutomaticInvestment ){
		this.statusAutomaticInvestment = statusAutomaticInvestment;
	}
	
	public void setAutomaticinvestmentservice( AutomaticInvestmentService service )
	{
		automaticinvestmentservice = service ;
	}
	
	public PospectusCommentService getPospectuscommentservice() {
		return pospectuscommentservice;
	}

	public void setPospectuscommentservice(PospectusCommentService pospectuscommentservice) {
		this.pospectuscommentservice = pospectuscommentservice;
	}
	
	public StackholderRelService getService_accionistas(){
		return service_accionistas;
	}
	
	public void setService_accionistas( StackholderRelService service_accionistas ){
		this.service_accionistas = service_accionistas ;
	}
	
	public CapitalNetoService getCapitalnetoservice(){
		return capitalnetoservice;
	}
	
	public void setCapitalnetoservice( CapitalNetoService capitalnetoservice ){
		this.capitalnetoservice = capitalnetoservice ;
	}
	
	public TableroNormativoService getTableronormativoservice(){
		return tableronormativoservice;
	}
	public void setTableronormativoservice( TableroNormativoService tableronormativoservice ){
		this.tableronormativoservice = tableronormativoservice ;
	}
	
	public TimeLogService getTimelogservice(){
		return timelogservice;
	}
	public void setTimelogservice( TimeLogService timelogservice ){
		this.timelogservice = timelogservice ;
	}
	
	public RelatedPersonLoanService getRelatedpersonloanservice(){
		return relatedpersonloanservice;
	}
	
	public void setRelatedpersonloanservice( RelatedPersonLoanService relatedpersonloanservice ){
		this.relatedpersonloanservice = relatedpersonloanservice ;
	}
	
	public LegalLimitService getLegallimitservice(){
		return legallimitservice;
	}
	
	public void setLegallimitservice( LegalLimitService legallimitservice ){
		this.legallimitservice = legallimitservice ;
	}
	
	public ContactWayProspectusService getContactwayprospectusservice() {
		return contactwayprospectusservice;
	}

	public void setContactwayprospectusservice(ContactWayProspectusService contactwayprospectusservice) {
		this.contactwayprospectusservice = contactwayprospectusservice;
	}
	
	public EflScoreService getEflscoreservice() {
		return eflscoreservice;
	}

	public void seteflscoreservice(EflScoreService eflscoreservice) {
		this.eflscoreservice = eflscoreservice;
	}
	
	public OfferService getOffer_service() {
		return offer_service;
	}

	public void setOffer_service(OfferService offer_service) {
		this.offer_service = offer_service;
	}

	public SavingAccountService getSavingAccountservice() {
		return savingAccountservice;
	}

	public void setSavingAccountservice(SavingAccountService savingAccountservice) {
		this.savingAccountservice = savingAccountservice;
	}
	
	public Integer getTimes_refill_init(){
		return times_refill_init;
	}
	
	public void setTimes_refill_init( Integer times_refill_init ){
		this.times_refill_init = times_refill_init;
	}
	
	public Double  getProvider_total_init(){
		return provider_total_init;
	}
	
	public void  setProvider_total_init( Double provider_total_init ){
		this.provider_total_init = provider_total_init;
	}
	
	public List<ApprovalCredit> getLstApproval(){
		return lstApproval;
	}
	
	public void  setLstApproval( List<ApprovalCredit> lstApproval ){
		this.lstApproval  = lstApproval;
	}
	
	public List<RuleExecution> getruleRelLst(){
		return ruleRelLst;
	}
	
	public void  setruleRelLst( List<RuleExecution> ruleRelLst ){
		this.ruleRelLst  = ruleRelLst;
	}
	
	public List<RuleExecution> getRuleEmpLst(){
		return ruleEmpLst;
	}
	
	public void  setRuleEmpLst( List<RuleExecution> ruleEmpLst ){
		this.ruleEmpLst  = ruleEmpLst;
	}
	
	public void setImg_status_inv( String img_status_inv){
		this.img_status_inv = img_status_inv;
	}
	public void setStr_status_inv( String str_status_inv){
		this.str_status_inv = str_status_inv;
	}
	public void setSafi_account_id( String safi_account_id){
		this.safi_account_id = safi_account_id;
	}
	
	public String getImg_status_inv(){
		return img_status_inv;
	}
	public String getStr_status_inv(){
		return str_status_inv;
	}
	public String getSafi_account_id(){
		return safi_account_id;
	}
	
	public boolean isFlagSameAddress(){
		
		return flagSameAddress;
		
	}
	
	public void setFlagSameAddress( boolean flagSameAddress ){
		this. flagSameAddress = flagSameAddress;
	}
	
	public boolean isFlagPromo(){
		
		return flagPromo;
		
	}
	
	public void setFlagPromo( boolean flagPromo ){
		this. flagPromo = flagPromo;
	}
	
	public boolean isHasEflTest(){
			
		return hasEflTest;
		
	}
	
	public void setHasEflTest( boolean hasEflTest ){
		this. hasEflTest = hasEflTest;
	}
	
	public boolean isEfl_OK(){
		return efl_OK;
	}
	
	public void setEfl_OK( boolean efl_OK ){
		this. efl_OK = efl_OK;
	}
	
	public boolean isBlnComment(){
		return blnComment;
	}
	
	public void setBlnComment( boolean blnComment ){
		this. blnComment = blnComment;
	}
	
	public boolean isContactWayPhone(){
		return contactWayPhone;
	}
	
	public void setContactWayPhone( boolean contactWayPhone ){
		this. contactWayPhone = contactWayPhone;
	}
	
	public boolean isContactWayWhatsApp(){
		return contactWayWhatsApp;
	}
	
	public void setContactWayWhatsApp( boolean contactWayWhatsApp ){
		this. contactWayWhatsApp = contactWayWhatsApp;
	}

	public boolean isHaveContactWay(){
		return haveContactWay;
	}
	
	public void setHaveContactWay( boolean haveContactWay ){
		this. haveContactWay = haveContactWay;
	}
	
	public boolean isContactWayEmail(){
		return contactWayEmail;
	}
	
	public void setContactWayEmail( boolean contactWayEmail ){
		this. contactWayEmail = contactWayEmail;
	}
	
	public boolean isEfl_ERROR(){
		return efl_ERROR;
	}
	
	public void setEfl_ERROR( boolean efl_ERROR ){
		this. efl_ERROR = efl_ERROR;
	}
	
	public void setOffer( Offer offer){
		this.offer = offer;
	}
	
	public Offer getOffer(){
		return offer;
	}
	
	public String getPromotionTitle(){
		return promotionTitle;
	}
	
	public void setPromotionTitle( String promotionTitle ){
		this.promotionTitle = promotionTitle;
	}
	
	
	public String getOffer_why_not(){
		return offer_why_not;
	}
	
	public void setOffer_why_not( String offer_why_not ){
		this.offer_why_not = offer_why_not;
	}
	
	public String getPagePromo(){
		return pagePromo;
	}
	
	public void setPagePromo( String pagePromo ){
		this.pagePromo = pagePromo;
	}
	
	public int getHasOffer(){
		return hasOffer;
	}
	
	public void setHasOffer( int hasOffer ){
		this.hasOffer = hasOffer;
	}
	
	public String getRejectionMotiveStr(){
		return rejectionMotiveStr;
	}
	
	public String getDomicilio_pais_origen_TOKEN()
	{
		return domicilio_pais_origen_TOKEN;
	}
	
	public String getStackholder_description(){
		return stackholder_description;
	}
	
	public void setStackholder_description( String stackholder_description ){
		this.stackholder_description = stackholder_description;
	}
	
	public void setRejectionMotiveStr( String rejectionMotiveStr ){
		this.rejectionMotiveStr = rejectionMotiveStr;
	}
	
	public OfferRejectionMotiveServiceImp getOfferrejectionmotiveservice(){
		return offerrejectionmotiveservice;
	}
	
	public void setOfferrejectionmotiveservice( OfferRejectionMotiveServiceImp offerrejectionmotiveservice ){
		this.offerrejectionmotiveservice = offerrejectionmotiveservice;
	}
	
	public List<PospectusComment> getLstcomm (){
		return lstcomm;
	}
	
	public void setLstcomm ( List<PospectusComment> lstcomm ){
		this. lstcomm = lstcomm ;
	}
	
	public String getLimiteUDIS (){
		return this.limiteUDIS;
	}
	
	public String getLimiteCapitalNeto(){
		return this.limiteCapitalNeto;
	}
	
	public void setLimiteUDIS ( String limiteUDIS ){
		this.limiteUDIS = limiteUDIS;
	}
	
	public void setLimiteCapitalNeto( String limiteCapitalNeto ){
		 this.limiteCapitalNeto = limiteCapitalNeto;
	}
	
	public boolean isRequireAutorizacionPersonaRelacionada() {
		return requireAutorizacionPersonaRelacionada;
	}

	public void setRequireAutorizacionPersonaRelacionada(boolean requireAutorizacionPersonaRelacionada) {
		this.requireAutorizacionPersonaRelacionada = requireAutorizacionPersonaRelacionada;
	}

	public boolean isRequireAutorizacionConsejoAdmin() {
		return requireAutorizacionConsejoAdmin;
	}

	public void setRequireAutorizacionConsejoAdmin(boolean requireAutorizacionConsejoAdmin) {
		this.requireAutorizacionConsejoAdmin = requireAutorizacionConsejoAdmin;
	}
	
	public boolean isSuperaPorcCapitalNeto() {
		return superaPorcCapitalNeto;
	}

	public void setSuperaPorcCapitalNeto(boolean superaPorcCapitalNeto) {
		this.superaPorcCapitalNeto = superaPorcCapitalNeto;
	}

	public boolean isSuperaUDIS() {
		return superaUDIS;
	}

	public void setSuperaUDIS(boolean superaUDIS) {
		this.superaUDIS = superaUDIS;
	}
	
	public RelatedPersonLoan getRelatedProyect() {
		return relatedProyect;
	}

	public void setRelatedProyect(RelatedPersonLoan relatedProyect) {
		this.relatedProyect = relatedProyect;
	}
	
	public TableroNormativoDetallado getTableronormativodetallado(){
		return this.tableronormativodetallado;
	}
	
	public void setTableronormativodetallado( TableroNormativoDetallado tableronormativodetallado ){
		this.tableronormativodetallado = tableronormativodetallado;
	}

	public RiskTaskService getRisktaskservice() {
		return risktaskservice;
	}

	public void setRisktaskservice(RiskTaskService risktaskservice) {
		this.risktaskservice = risktaskservice;
	}
	
	public RuleExecutionService getRuleexecutionservice() {
		return ruleexecutionservice;
	}

	public void setRuleexecutionservice(RuleExecutionService ruleexecutionservice) {
		this.ruleexecutionservice = ruleexecutionservice;
	}

	public boolean isRisktask1() {
		return risktask1;
	}

	public void setRisktask1(boolean risktask1) {
		this.risktask1 = risktask1;
	}
	
}