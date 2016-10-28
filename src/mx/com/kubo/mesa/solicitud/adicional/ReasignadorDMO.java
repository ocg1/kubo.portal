package mx.com.kubo.mesa.solicitud.adicional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.FilesPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.ProyectPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.ServiceCalling;
import mx.com.kubo.proyect_loan.documentacion.CopiarArchivosIMP;
import mx.com.kubo.repositories.ServiceCallingDao;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.SimulatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;
import com.soa.webServices.responses.WsSgbResponse;
import com.soa.webServices.util.InputParam;

public abstract class ReasignadorDMO 
implements ReasignadorIMO
{
	@Autowired @Qualifier("proyectLoanServiceImp")
	protected ProyectLoanService proyectloanService;
	
	@Autowired @Qualifier("proyectServiceImp")
	protected ProyectService proyectService;
	
	@Autowired @Qualifier("purposeServiceImp")
	protected PurposeService purposeService;
	
	@Autowired @Qualifier("scoringServiceImp")
	protected ScoringService scoreService;
	
	@Autowired @Qualifier("accessServiceImp")
	protected AccessService accessService;
	
	@Autowired @Qualifier("membershipServiceImp")
	protected MembershipService membershipservice;
	
	@Autowired @Qualifier("change_controlServiceImp")
	protected Change_controlService changeControlService;
	
	@Autowired @Qualifier("filesServiceImp")
	protected FilesService filesService;
	
	@Autowired @Qualifier("serviceCallingDaoImp")
	protected ServiceCallingDao service_calling;
	
	@Autowired @Qualifier("copiar_archivos_service")
	protected CopiarArchivosIMP copiar_archivos_service;
	
	@Autowired @Qualifier("simulatorServiceImp")
	protected SimulatorService simulatorService;
	
	
	protected FacesContext    faces;
	protected ExternalContext external;	
	
	protected SessionBean sesion;	
	
	protected ProyectLoan   proyect_loan;	
	protected ProyectLoan   proyect_loan_NEW;
	protected ProyectLoanPK proyect_loan_NEW_PK;
	protected Proyect       proyect_NEW;	
	protected ProyectPK     proyect_PK;
	
	protected Scoring score;
	protected ScoreAllocIMO allocater;
	
	protected MembershipPK  membershipPK;
	protected Membership    membership;	
	
	protected DocumentationDMO docBean;
	protected FilesPK          filessPk;
	protected Files            files;	
		
	protected Change_controlPK changeCtrlPK;
	protected Change_control   changeCtrl;
	protected ServiceCalling   srvCall;
	protected Access           access;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk     service;
	protected WsSgbResponse res;		
	
	protected List<DocumentationDMO> lista_files;
	protected List<Files> lista_files_loaded;	
	protected List<Files> lista_archivos;
	
	protected InputParam[] params;
	
	protected Date uploaded_date;
	protected Date fecha_consulta;
	
	protected String formatDocument;
	protected String file_type;
	protected String url_img;
	protected String real_path;	
	protected String bursolnum;
	protected String score_A;
	protected String score_B;
	protected String pago_inicial;
	protected String url;
	protected String partner_id;
	protected String cci_score;
	protected String prospectId;
	protected String projectId;
	protected String productId;
	protected String amount;
	protected String companyId;
	protected String mxTasa;
	protected String mxFrec;
	protected String mxNumPagos;
	protected String mxComisionApertura;
	protected String loan_type;
	protected String is_collection_solution;
	
	protected Double ammount;
	protected Double min_ammount;
	protected Double payment;
	protected Double CAT;    
	protected Double rate;
	protected Double rate_opening;
	protected Double comision;
	protected Double comision_amount;		
	protected Double liquidez;
	
	protected Integer BC_score;	
	protected Integer file_type_id;
	
	protected int term_id;
	protected int max_proyect_id;			
	protected int frequency_id;
	protected int company_id;
	protected int prospectus_id;
	protected int proyect_loan_id;
	protected int proyect_loan_id_ORIGINAL;
	protected int file_id;
	protected int category_id;
	protected int dias_vigencia;	
	
	protected boolean documento_vigente;
	protected boolean bandera;
	protected boolean error_al_mover;
	protected boolean is_proyect_NEW_OK;
	
	protected final boolean VALIDACION_VIGENCIA_DISABLED = false;
	
	private SimpleDateFormat formatter_date;
	private Calendar         calendar;
	private Date             fecha_vigente;		
	
	protected ReasignadorDMO()
	{
		formatter_date = new SimpleDateFormat("dd/MM/yyyy");
	}
	
	public final void setSesionBean(SessionBean sesion) 
	{
		this.sesion = sesion;
	}
	
	protected final void setDocumento_vigente_DISABLED(boolean disabled)
	{
		documento_vigente = disabled;
	}
	
	protected final boolean isDocumento_vigente_DISABLED()
	{
		return documento_vigente;
	}
	
	protected final Date getFecha_vigente(Date fecha_de_carga, int dias_vigencia)
	{
		calendar = Calendar.getInstance();	
		
		calendar.setTime(fecha_de_carga);		
		calendar.add(Calendar.DAY_OF_YEAR, dias_vigencia);
		
		fecha_vigente = calendar.getTime();
		
		System.out.println("\n > ReasignadorDMO.getFecha_vigente(): ");
		System.out.println(" > Fecha de carga (gn_file.uploaded_date): " + formatter_date.format(fecha_de_carga));
		System.out.println(" > Fecha de vigencia: " + formatter_date.format(fecha_vigente));
		
		return fecha_vigente;		
	}
	
	protected final void setProyectloan(ProyectLoan reasignable) 
	{
		proyect_loan = reasignable;
	}

	public final void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}

	public final void setProyectService(ProyectService service) 
	{
		proyectService = service;
	}

	public final void setScoreService(ScoringService service) 
	{
		scoreService = service;
	}

	public final void setAccessService(AccessService service) 
	{
		accessService = service;
	}

	public final void setMembershipservice(MembershipService service) 
	{
		membershipservice = service;
	}

	public final void setChangeControlService(Change_controlService service) 
	{
		changeControlService = service;
	}

	public final void setFilesService(FilesService service) 
	{
		filesService = service;
	}
	
	public void setService_calling(ServiceCallingDao service) 
	{
		service_calling = service;
	}

	public final void setCopiar_archivos_service(CopiarArchivosIMP service) 
	{
		copiar_archivos_service = service;
	}

	public SimulatorService getSimulatorService() {
		return simulatorService;
	}

	public void setSimulatorService(SimulatorService simulatorService) {
		this.simulatorService = simulatorService;
	}

	public PurposeService getPurposeService() {
		return purposeService;
	}

	public void setPurposeService(PurposeService purposeService) {
		this.purposeService = purposeService;
	}
}
