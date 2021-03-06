package mx.com.kubo.managedbeans.mesa.solicitud.adicional;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.DocumentationDMO;
import mx.com.kubo.bean.FilesTypeCategoryBean;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.investor.NavigationInvest;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.managedbeans.util.ConvertCalendar;
import mx.com.kubo.model.Files;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.TSafiPagosCuota;
import mx.com.kubo.model.TSafiPosicionInt;
import mx.com.kubo.mesa.solicitud.adicional.ReasignadorIMO;
import mx.com.kubo.services.EstadoCuentaService;
import mx.com.kubo.services.FilesService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;

import com.soa.model.businessobject.BurResume;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class AdditionalCreditDMO 
{	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanService;
	
	@ManagedProperty("#{filesServiceImp}")
	protected FilesService filesService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	private SystemParamService systemparamservice;				
	
	@ManagedProperty("#{estadoCuentaServiceImp}")
	protected EstadoCuentaService estadocuentaservice;
							
	protected RequestContext  request;
	protected RequestContext  requestContext;
	protected FacesContext    faces;
	protected ELContext       elContext;
	protected ELResolver      resolver;
	protected ExternalContext external;
	
	protected SessionBean          sesion;	
	protected MenuControlTableBean control_table;
	protected SearchSummaySession  summarysesion;
	protected SummaryRequest       summary;
	protected NavigationInvest     navigationinvest;
	
	protected ReasignadorIMO reasignador;
		
	protected ProyectLoan            proyect_loan;
	protected RestructureBean        restructurebean;			
	protected DocumentationDMO       docBean;
	protected FilesTypeCategoryBean  fileCateg;
	protected RoleFunctionController role_function;
	protected TipoCreditoAdicional   tipo_credito_adicional;
	protected TSafiPosicionInt       posicion;
	protected SystemParamPK          syspk;
	protected SystemParam            systemparam;	
	protected ConvertCalendar        conv;
	protected Date                   dFecConsult;
	protected Utilities              objUtility;
	
	protected WsSgbRiskServiceLocator locator;
	protected WsSgbRisk service;
	protected WsSgbRisk service_SGB;
	protected BurResume res;
	protected BurResume buro_resume;	
	
	protected Date d1, d2;
	protected Calendar cal1, cal2;
	protected SimpleDateFormat fm1, formatStr1, formatStr;
			
//	protected TSafiPagosCuota[]  safi_lista_pagos;
//	protected TSafiPosicionInt[] safi_lista_posiciones;
	
	protected List<TSafiPagosCuota>  safi_lista_pagos;
	protected List<TSafiPosicionInt> safi_lista_posiciones;
	
	protected Hashtable <String, List<DocumentationDMO>> htCategFile;
	
	protected Set<String> claves;
	
	protected List<Files>             lista_files_loaded;
	protected List<DocumentationDMO>  lista_documentacion;
	protected List<TSafiPagosCuota>   lista_safi_pagos;
	
	protected List<FilesTypeCategoryBean> listFiles;
	protected List<RoleFunction>          lista_function_by_role;
	protected List<ProyectLoan>           lista_proyect_loan;	
	
	protected String fec;
	protected String formatDocument; 
	protected String real_path;
	protected String safi_pago_estatus; 	
	protected String safi_credit_id;
	protected String file_type;
	protected String url_img;
	protected String category_name;
	protected String bur_sol_num;
	protected String proyect_loan_SEARCH_TOKEN;
	
	protected String valores[];
	
	protected Double iva, ivaIp, ivaIa, ivaM, ivaC;
	protected Double saldo_capital_atrasado;
	protected Double saldo_capital_vigente;
	protected Double saldo_comision_falta_pago;
	protected Double saldo_interes_atrasado;
	protected Double saldo_interes_provi;
	protected Double saldo_por_liquidar;
	protected Double saldo_moratorios;	
	protected Double saldo_capital_vencido;
	protected Double saldo_capital_vencido_no_exigible;
	protected Double saldo_capital;
	protected Double monto_credito;
	protected Double saldo_deudor;	
	protected Double indice_saldo_deudor;
	protected Double indice_saldo_deudor_MIN;
	protected Double indice_saldo_capital;		

	protected Long fecha_consulta;
	protected Long ld1, ld2;
	protected Long dias_de_atraso;
	
	protected Integer file_type_id;		
	
	protected long milis1, milis2, diff;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyectloan_safi_credit_id;	
	protected int pagadas;	
	protected int vigentes;	
	protected int inactivos;	
	protected int atrasadas;		
	protected int vencidas;
	protected int safi_posicion_credito_id;
	protected int safi_pago_credito_id;		
	protected int proyect_loan_id;
	protected int proyect_id;
	protected int category_id;
	protected int file_id;		
	protected int dias_atras;
	
	protected boolean restructure;
	protected boolean saldo_deudor_superior_al_MIN;	
	protected boolean ver_nueva_solicitud_ENABLED;
	protected boolean solicitado, error_al_mover;
	
	protected final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	
	private NumberFormat         formatter; 
	private DecimalFormatSymbols symbols;	
	
	private String fecConsult;	
	private String diasTrans;	
	private String selectedItems;
	
	private Double porc_pag;
	
	private Integer loanType;
		
	private int additionalType;	

	private boolean renderDocuments;	
	private boolean renderError;	
	private boolean renderSuccess;	
	private boolean renderAdditional;	
	private boolean renderRenovacion;	
	private boolean renderRedocumentacion;		
	private boolean reestructAction;
	private boolean flagnopublish;
	
	protected AdditionalCreditDMO()
	{
		fm1        = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
		formatStr1 = new SimpleDateFormat("dd'-'MMM'-'yyyy",        new Locale("ES"));
		formatStr  = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		
		symbols = new DecimalFormatSymbols(new Locale("es", "mx"));
		
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		
		formatter = new DecimalFormat("#0.00", symbols);
		
		init_valores_default();
	}
	
	protected void init_valores_default() 
	{
		restructurebean = null;
		
		porc_pag  = 0D;
		
		additionalType = 0;
		loanType       = 0;
		dias_atras     = 0;
		pagadas        = 0;
		vigentes       = 0;
		inactivos      = 0;
		atrasadas      = 0;
		vencidas       = 0;
		
		renderDocuments       = true;
		renderError           = false;
		renderSuccess         = false;
		renderAdditional      = false;
		renderRenovacion      = false;
		renderRenovacion      = false;
		renderRedocumentacion = false;
		restructure           = false;
		reestructAction       = false;
		flagnopublish         = false;
		
		saldo_deudor_superior_al_MIN = false;
		ver_nueva_solicitud_ENABLED  = false;
	}
	
	protected void init_sesion_login() 
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
	}

	public boolean isSaldo_deudor_superior_al_MIN() 
	{
		return saldo_deudor_superior_al_MIN;
	}
	
	public boolean isVer_nueva_solicitud_ENABLED() 
	{
		return ver_nueva_solicitud_ENABLED;
	}

	public String getIndice_saldo_capital() 
	{
		return formatter.format(indice_saldo_capital);
	}
	
	protected void setPermissions(int role_id)
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
				
		role_function = (RoleFunctionController) resolver.getValue(elContext, null, "roleFunctionController");
		
		lista_function_by_role = role_function.getFunctionByRole(role_id);
		
		for(RoleFunction role_function : lista_function_by_role)
		{											
			if(role_function.getPk().getFunction_id() == 5)
			{ 				
				reestructAction = true;			
			}			
		}		
	}

	protected final BurResume getBuroResume(String cliProId, String indCliPro, String burSol) 
	{		
		res = null;
		
		try 
		{			
			locator = new WsSgbRiskServiceLocator();
			
			service = locator.getWsSgbRisk();
			res     = service.getBurResume(null, null, burSol);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return res;
	}	
	
	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}		

	public final void setProyectloanService(ProyectLoanService service) 
	{
		proyectloanService = service;
	}

	public final void setFilesService(FilesService service) 
	{
		filesService = service;
	}

	public String getDiasTrans() {
		return diasTrans;
	}

	public void setDiasTrans(String diasTrans) {
		this.diasTrans = diasTrans;
	}

	public List<FilesTypeCategoryBean> getListFiles() {
		return listFiles;
	}

	public void setListFiles(List<FilesTypeCategoryBean> listFiles) {
		this.listFiles = listFiles;
	}

	public String getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
	}

	public ProyectLoan getProyectloan() 
	{
		return proyect_loan;
	}

	public void setProyectloan(ProyectLoan actual) 
	{
		proyect_loan = actual;
	}
	
	public int getProyect_loan_id() 
	{
		return proyect_loan_id;
	}

	public int getProyect_id() 
	{
		return proyect_id;
	}
	
	public int getProspectus_id() 
	{
		return prospectus_id;
	}

	public int getCompany_id() 
	{
		return company_id;
	}

	public boolean isRenderDocuments() {
		return renderDocuments;
	}

	public void setRenderDocuments(boolean renderDocuments) {
		this.renderDocuments = renderDocuments;
	}

	public boolean isRenderError() {
		return renderError;
	}

	public void setRenderError(boolean renderError) {
		this.renderError = renderError;
	}

	public boolean isRenderSuccess() {
		return renderSuccess;
	}

	public void setRenderSuccess(boolean renderSuccess) {
		this.renderSuccess = renderSuccess;
	}

	public boolean isRenderAdditional() {
		return renderAdditional;
	}

	public void setRenderAdditional(boolean renderAdditional) {
		this.renderAdditional = renderAdditional;
	}

	public boolean isRenderRenovacion() {
		return renderRenovacion;
	}

	public void setRenderRenovacion(boolean renderRenovacion) {
		this.renderRenovacion = renderRenovacion;
	}

	public boolean isRenderRedocumentacion() {
		return renderRedocumentacion;
	}

	public void setRenderRedocumentacion(boolean renderRedocumentacion) {
		this.renderRedocumentacion = renderRedocumentacion;
	}

	public int getDias_atras() {
		return dias_atras;
	}

	public Double getPorc_pag() {
		return porc_pag;
	}

	public void setPorc_pag(Double porc_pag) {
		this.porc_pag = porc_pag;
	}

	public int getPagadas() {
		return pagadas;
	}


	public int getVigentes() {
		return vigentes;
	}

	public int getInactivos() {
		return inactivos;
	}


	public int getAtrasadas() {
		return atrasadas;
	}

	public boolean isRestructure() {
		return restructure;
	}

	public void setRestructure(boolean restructure) {
		this.restructure = restructure;
	}

	public RestructureBean getRestructurebean() {
		return restructurebean;
	}

	public void setRestructurebean(RestructureBean restructurebean) {
		this.restructurebean = restructurebean;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public int getAdditionalType() {
		return additionalType;
	}

	public void setAdditionalType(int additionalType) {
		this.additionalType = additionalType;
	}

	public boolean isReestructAction() {
		return reestructAction;
	}

	public boolean isFlagnopublish() {
		return flagnopublish;
	}

	public void setFlagnopublish(boolean flagnopublish) {
		this.flagnopublish = flagnopublish;
	}
	
	public BurResume getBur() {
		return buro_resume;
	}

	public void setBur(BurResume bur) {
		this.buro_resume = bur;
	}

	public String getFecConsult() {
		return fecConsult;
	}

	public void setFecConsult(String fecConsult) {
		this.fecConsult = fecConsult;
	}

	public EstadoCuentaService getEstadocuentaservice() {
		return estadocuentaservice;
	}

	public void setEstadocuentaservice(EstadoCuentaService estadocuentaservice) {
		this.estadocuentaservice = estadocuentaservice;
	}
}
