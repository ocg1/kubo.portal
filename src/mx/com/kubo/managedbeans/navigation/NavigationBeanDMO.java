package mx.com.kubo.managedbeans.navigation;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Fields;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.EflScoreService;
import mx.com.kubo.services.FieldsService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.RiskTaskService;
import mx.com.kubo.services.ScoringService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SegmentProspectusService;
import mx.com.kubo.services.SystemParamService;

public abstract class NavigationBeanDMO 
implements NavigationBeanIMO
{
	protected final int TAREA1 = 1;
	protected final int TAREA2 = 2;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{scoringServiceImp}")
	protected ScoringService scoringService;
	
	@ManagedProperty("#{fieldsServiceImpl}")
	protected FieldsService fieldsService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{segmentProspectusServiceImp}")
	protected SegmentProspectusService segment_prospectus_service;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{eflScoreServiceImp}")
	protected EflScoreService eflscoreservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	protected ProspectusService prospectusService;
	
	@ManagedProperty("#{riskTaskServiceImp}")
	protected RiskTaskService risktaskservice;
	
	
	protected String paginaIni;
		
	protected FacesContext       faces;
	protected ELContext          elContext;	
	protected ELResolver         resolver;
	protected ExternalContext    external;
	
	protected SessionBean sesion ;	
	protected Scoring     score;
	protected Simulator   simulator;
	
	protected String paginaActual;
	protected String area;
	protected String menuSel;
	protected String screen_id;
	protected String menu_item_selected;	
	
	//protected String hs_values;
	
	protected StringBuilder sbHs;
	
	protected boolean flagBCScore;
	
	protected List<MenuRegBean> lista_menu_items;
	
	protected Map<String, Object> viewMap;
	
	protected Hashtable<String,Fields> htDisabledFields;	

	private HttpServletRequest request;
		
	private SystemParamPK  system_param_PK;
	private SystemParam    system_param;
	
	private String block;
	private String contBlock;	
	private String url;
	
	protected Double porcentBasic;
	
	private Integer prospectus;
	private Integer company;

	private boolean hasValidScore;
	private boolean displaySim;
	private boolean loaner;
	
	protected boolean tarea1 = true;
	
	protected NavigationBeanDMO()
	{
		menuSel      = "menu1";
		paginaActual = "registro/basicdata";
		block        = "none";
		contBlock    = "none";
		
		displaySim = true;
		loaner     = false;
		
		sbHs = new StringBuilder();
		
	}
	
	protected final boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=NavigationBean");
							
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
	
	protected String getPath()
	{
		request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
	protected final boolean isPantalla_estado_cuenta_ENABLED(Integer company_id)
	{
		system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id(company_id);
		system_param_PK.setSystem_param_id(13);
		
		system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
		
		String param_value = system_param.getValue();
		
		return param_value != null && param_value.equals("S");
	}
	
	public void setMenuSel(String menuSel) {
		this.menuSel = menuSel;
	}

	public Double getPorcentBasic() {
		return porcentBasic;
	}

	public void setPorcentBasic(Double porcentBasic) {
		this.porcentBasic = porcentBasic;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getContBlock() {
		return contBlock;
	}

	public void setContBlock(String contBlock) {
		this.contBlock = contBlock;
	}
	
	public Integer getProspectus() {
		return prospectus;
	}

	public void setProspectus(Integer prospectus) {
		this.prospectus = prospectus;
	}

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public String getMenuSel() {
		return menuSel;
	}
	
	public String getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(String paginaActual) 
	{
		this.paginaActual = paginaActual;
	}

	public boolean isDisplaySim() {
		return displaySim;
	}

	public void setDisplaySim(boolean displaySim) {
		this.displaySim = displaySim;
	}

	public final List<MenuRegBean> getMenus() 
	{
		return lista_menu_items;
	}

	public final void setMenus(List<MenuRegBean> menus) 
	{
		lista_menu_items = menus;
	}
		
	public Hashtable<String, Fields> getHtDisabledFields() {
		return htDisabledFields;
	}

	public void setHtDisabledFields(Hashtable<String, Fields> htDisabledFields) {
		this.htDisabledFields = htDisabledFields;
	}

	public boolean isHasValidScore() 
	{		
		return hasValidScore;
	}

	public void setHasValidScore(boolean hasValidScore) {
		this.hasValidScore = hasValidScore;
	}

	public boolean isLoaner() {
		return loaner;
	}

	public void setLoaner(boolean loaner) {
		this.loaner = loaner;
	}
	
	public void setScoringService(ScoringService service) 
	{
		scoringService = service;
	}

	public void setFieldsService(FieldsService service) 
	{
		fieldsService = service;
	}
	
	public void setAccessService(AccessService service) 
	{
		accessService = service;
	}
	
	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}

	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}

	public void setSimulator(Simulator simulator) 
	{
		this.simulator = simulator;
	}

	public boolean isFlagBCScore() {
		return flagBCScore;
	}

	public void setFlagBCScore(boolean flagBCScore) {
		this.flagBCScore = flagBCScore;
	}

	public ScreenService getScreenservice() {
		return screenservice;
	}

	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}

	public String getPaginaIni() {
		return paginaIni;
	}

	public void setPaginaIni(String paginaIni) {
		this.paginaIni = paginaIni;
	}

	public SegmentProspectusService getSegment_prospectus_service() {
		return segment_prospectus_service;
	}

	public void setSegment_prospectus_service(SegmentProspectusService segment_prospectus_service) {
		this.segment_prospectus_service = segment_prospectus_service;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public EflScoreService getEflscoreservice() {
		return eflscoreservice;
	}

	public void setEflscoreservice(EflScoreService eflscoreservice) {
		this.eflscoreservice = eflscoreservice;
	}

	public String getHs_values() {
		if( sbHs!= null ){
			return sbHs.toString();
		}else{
			sbHs = new StringBuilder();
			return sbHs.toString();
		}
	}

	public void setHs_values(String hs_values) {
		
		sbHs = null;
		sbHs = new StringBuilder();
		sbHs.append( hs_values );
	}

	public ProspectusService getProspectusService() {
		return prospectusService;
	}

	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}

	public boolean isTarea1() {
		return tarea1;
	}

	public void setTarea1(boolean tarea1) {
		this.tarea1 = tarea1;
	}

	public RiskTaskService getRisktaskservice() {
		return risktaskservice;
	}

	public void setRisktaskservice(RiskTaskService risktaskservice) {
		this.risktaskservice = risktaskservice;
	}
}
