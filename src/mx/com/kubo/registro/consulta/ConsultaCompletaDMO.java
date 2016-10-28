package mx.com.kubo.registro.consulta;

import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.soa.model.businessobject.BurResponse;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.CreditHistory;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.CreditHistoryAttemptPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.NeighborhoodCatPK;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SimulatorPK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TownCat;
import mx.com.kubo.model.TownCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.model.mesa.solicitud.notas.Notes;
import mx.com.kubo.notificaciones.notificador.NotificadorIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.CreditHistoryAttemptService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.NeighborhoodService;
import mx.com.kubo.services.ProspectorService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ServiceCallingService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.StateService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.services.TownService;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.tools.Utilities;

public abstract class ConsultaCompletaDMO 
implements ConsultaCompletaIMO
{
	protected ScoringService scoringService;
	protected ProyectLoanService proyectloanService;
	protected NeighborhoodService neighborhoodService;
	protected TownService townService;
	protected StateService stateService;
	protected ServiceCallingService servicecallingService;
	protected SystemParamService systemParamService;
	protected CreditHistoryAttemptService creditAttemptService;
	protected ProspectorService prospector_service; 
	protected AccessService accessService;	
	protected SimulatorService simulatorService;
	protected ProyectService proyectService;
	protected MembershipService membershipservice;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext   	elContext;
	protected ELResolver   resolver;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk 			   service;		
	protected ServiceCalling srvCall;
	protected BurResponse    response;
	protected SystemParam   system;
	protected SystemParamPK paramPK;	
	
	protected NotificadorIMO notificador;
	protected NavigationBeanIMP navigation;
	protected NotesService service_notas;
	
	protected SessionBean       sesion;	
	protected Prospectus        prospectus;
	protected NaturalPerson     naturalPerson;
	protected gnNaturalPersonPK naturalPersonPK;
	protected Membership        membership;
	protected MembershipPK      membership_PK;
	
	protected CreditHistory   credithistory;	
	protected CreditHistoryAttempt   intento;
	protected CreditHistoryAttemptPK intentoPK;
	protected Scoring score;
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	protected Proyect       proyect;
	protected ProyectPK     proyect_PK;
	protected Notes nota_del_coach;
	
	protected Simulator     simulator;
	protected SimulatorPK   simulator_PK;
	protected SimulatorBean simulacion_ACTUAL;
	
	protected Phone   thisPhoneFixed;
	protected Address thisAddress; 
	protected StateCat state;
	protected StateCatPK sPK;
	protected TownCat   town;
	protected TownCatPK tPK;
	protected NeighborhoodCat   neig;
	protected NeighborhoodCatPK nPK;
	
	protected ResourceBundle recurso;
	protected Writer      writer;
	protected PrintWriter printWriter;
	protected SimpleDateFormat format;
	protected SimpleDateFormat formatUtilDate;
	protected ConvertCalendar cv2;
	
	protected Date fechaLimite;
	protected Date fecLimiteUtilDate;
	protected Date birth;
	
	protected String tarjeta_credito_company;
	protected String tarjeta_credito_number;
	protected String user;	
	protected String password;
	protected String errorMsg;
	protected String msg;	
	protected String colonia;
	protected String birthday;
	protected String lada;
	protected String phone;
	protected String municipio;
	protected String thisEstado;
	protected String prospectId;
	protected String birthStr;
	protected String fecLimiteStr;
	protected String msgWarningBurConsult;
	protected String bur_sol_num;
	protected String hid_value;
	protected String credit_company_name;
	protected String mortgage_company;
	
	protected String msgStr = "<b>No nos fue posible autenticarte ante Buro de Crédito con la información que nos proporcionaste.</b>"
		  	  + "<br /> Por favor considera lo siguiente:"
			  + "<ul>"
			  + "<li>Revisa que tu nombre esté escrito exactamente como está en tu credencial de elector.</li>"
			  + "<li>Verifica tu fecha de cumpleaños.</li>"
			  + "<li>Si has contratado una tarjeta de crédito, crédito hipotecario o crédito de auto hace menos de un mes, es probable que aún no esté reflejado en buró de crédito, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
			  + "<li>Si has tenido un crédito de auto o hipotecario que ya liquidaste hace más de 3 meses, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>"
			  + "<li>Si tienes un crédito hipotecario de INFONAVIT, por favor intenta de nuevo indicando que no tienes dicho crédito.</li>";

	protected Double monto_solicitado;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	protected Integer proyect_id;
	protected Integer has_tarjeta_credito;
	protected Integer has_credito_hipotecario;
	protected Integer has_credito_automotriz;
	protected Integer diasValidos;
	protected Integer intentosPermitidos;
	protected Integer consultasAnteriores;
	protected Integer numConsulBuro;
	
	protected boolean mortgage_company_ENABLED;
	protected boolean blocked_person_ENABLED;
	protected boolean tarjeta_credito_company_ENABLED;
	protected boolean has_credito_ENABLED;
	protected boolean is_prospect_SGB_OK;
	protected boolean successTitle;
	protected boolean success;
	protected boolean noHit;
	protected boolean fail;
	protected boolean error;
	protected boolean wait;
	protected boolean flagDoubleCheck = false;
	protected boolean flagDoubleCheck_2 = true;
	protected boolean displayErrBurConsult;
	protected boolean consultaBuro;
	protected boolean displayGreen;
	protected boolean displayBlue;
	protected boolean success_OK = false;
	protected boolean hid_flag = false;
	protected boolean is_this_credit_company;
	protected boolean nota_coach_ENABLED;
	protected boolean is_nota_OK;
	protected boolean is_proyect_OK;
	
	protected ConsultaCompletaDMO()
	{
		scoringService     = Utilities.findBean("scoringServiceImp");
		proyectloanService = Utilities.findBean("proyectLoanServiceImp");
	}
	
	public void setCredithistory(CreditHistory credithistory)
	{
		this.credithistory = credithistory;		
	}

	public void setBlocked_person_ENABLED(boolean blocked_person_ENABLED) 
	{
		this.blocked_person_ENABLED = blocked_person_ENABLED;
	}
}
