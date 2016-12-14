package mx.com.kubo.managedbeans;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SecurityQuestionPoolService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.SystemParamService;

import org.apache.log4j.Logger;

public abstract class HeaderBeanDMO 
implements HeaderBeanIMO
{
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService service_system_param;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{securityQuestionPoolServiceImp}")
	protected SecurityQuestionPoolService secquestpoolservice;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{loginServiceImp}")
	protected LoginServiceIMO loginService;

	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	protected NavigationBeanIMP nav;
	
	protected Logger log;
	
	protected FacesContext    faces;
	protected ELResolver      resolver;
	protected ELContext       elContext;
	protected ExternalContext external;
	protected HttpSession        sessionUsed;
	protected HttpServletRequest servlet_request;
	
	protected SessionBean   sesion;
	protected Simulator     simulator;
	protected SimulatorBean sim;
	protected Access access;
	
	protected SystemParam sysParam;
	protected SystemParamPK sysPK;
	
	protected List<SavingAccount> countList;
		
	protected String user;
	protected String name; 
	protected String menu;
	protected String action;
	protected String answer;
	protected String answer2;
	protected String answer3;
	protected String header;
	protected String trackId;
	protected String active;
	protected String role;
	protected String partner;
	protected String temporalUser;
	protected String nameb;
	protected String verionb;
	protected String osb;
	protected String width;
	protected String height;
	protected String ipAddressClient;
	protected String fecha_acceso_ACTUAL;
	protected String fecha_acceso_LAST;
	protected String userAgent;
	protected String deviceInfo;
	protected String version_description;
	
	protected String enabledAnalytics;
	
	private String password;	
	private String activeStr;
	private String errorMail;
	private String errorPass;
	private String errorMailDis;
	private String errorPassDis;
	private String valPass;
	private String valMail;	
	private String menu2;
	private String warningUserMsg;
	
	private Integer seqQuest;
	private Integer seqQuest2;
	private Integer seqQuest3;
	
	protected boolean sessionUsedB;

	private boolean warninguserdisp;
	private boolean warninguserbut;
	private boolean dispBtnCred;
	private boolean dispBtnInicio;
	private boolean dispBtnPerfil;

	protected final long MILLSECS_PER_DAY;	
	protected final int INVERSIONES = 15;
	
	protected HeaderBeanDMO()
	{
		log = Logger.getLogger(getClass());
				
		menu    = "ENTRAR";
		menu2   = "ENTRAR";
		valPass = "0";
		valMail = "0";
		header  = "headerOut.xhtml";
				
		MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
		
		warninguserdisp = false;
		warninguserbut  = true;
		dispBtnCred     = true;
		dispBtnInicio   = true;
		dispBtnPerfil   = true;
	}
	
	public final String getFecha_acceso_ACTUAL() 
	{
		return fecha_acceso_ACTUAL;
	}
	
	public final String getFecha_acceso_LAST() {
		
		return fecha_acceso_LAST;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public Integer getSeqQuest() {
		return seqQuest;
	}

	public void setSeqQuest(Integer seqQuest) {
		this.seqQuest = seqQuest;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveStr() {
		return activeStr;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setActiveStr(String activeStr) {
		this.activeStr = activeStr;
	}

	public String getErrorMail() {
		return errorMail;
	}

	public void setErrorMail(String errorMail) {
		this.errorMail = errorMail;
	}

	public String getErrorPass() {
		return errorPass;
	}

	public void setErrorPass(String errorPass) {
		this.errorPass = errorPass;
	}

	public String getErrorMailDis() {
		return errorMailDis;
	}

	public void setErrorMailDis(String errorMailDis) {
		this.errorMailDis = errorMailDis;
	}

	public String getErrorPassDis() {
		return errorPassDis;
	}

	public void setErrorPassDis(String errorPassDis) {
		this.errorPassDis = errorPassDis;
	}

	public String getValPass() {
		return valPass;
	}

	public void setValPass(String valPass) {
		this.valPass = valPass;
	}

	public String getValMail() {
		return valMail;
	}

	public void setValMail(String valMail) {
		this.valMail = valMail;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public boolean isWarninguserdisp() {
		return warninguserdisp;
	}

	public void setWarninguserdisp(boolean warninguserdisp) {
		this.warninguserdisp = warninguserdisp;
	}

	public boolean isWarninguserbut() {
		return warninguserbut;
	}

	public void setWarninguserbut(boolean warninguserbut) {
		this.warninguserbut = warninguserbut;
	}

	public String getWarningUserMsg() {
		return warningUserMsg;
	}

	public void setWarningUserMsg(String warningUserMsg) {
		this.warningUserMsg = warningUserMsg;
	}

	public String getMenu2() {
		return menu2;
	}

	public void setMenu2(String menu2) {
		this.menu2 = menu2;
	}
	
	public final String getMenu() {
		return menu;
	}

	public final void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getSeqQuest2() {
		return seqQuest2;
	}

	public void setSeqQuest2(Integer seqQuest2) {
		this.seqQuest2 = seqQuest2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public Integer getSeqQuest3() {
		return seqQuest3;
	}

	public void setSeqQuest3(Integer seqQuest3) {
		this.seqQuest3 = seqQuest3;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public boolean isDispBtnCred() {
		return dispBtnCred;
	}

	public void setDispBtnCred(boolean dispBtnCred) {
		this.dispBtnCred = dispBtnCred;
	}

	public boolean isDispBtnInicio() {
		return dispBtnInicio;
	}

	public void setDispBtnInicio(boolean dispBtnInicio) {
		this.dispBtnInicio = dispBtnInicio;
	}

	public boolean isDispBtnPerfil() {
		return dispBtnPerfil;
	}

	public void setDispBtnPerfil(boolean dispBtnPerfil) {
		this.dispBtnPerfil = dispBtnPerfil;
	}
	
	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService service) 
	{
		membershipService = service;
	}

	public void setSimulatorService(SimulatorService service) 
	{
		simulatorService = service;
	}

	public void setService_system_param(SystemParamService service) 
	{
		service_system_param = service;
	}

	public void setPasswordhistoryservice(PasswordHistoryService service) 
	{
		passwordhistoryservice = service;
	}

	public void setSavingaccountservice(SavingAccountService service) 
	{
		savingaccountservice = service;
	}

	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}

	public void setSecquestpoolservice(SecurityQuestionPoolService service) 
	{
		secquestpoolservice = service;
	}

	public void setInvestorservice(InvestorService service) 
	{
		investorservice = service;
	}

	public void setScreenservice(ScreenService service) 
	{
		screenservice = service;
	}

	public void setLoginService(LoginServiceIMO service) 
	{
		loginService = service;
	}

	public String getAction() {
		
		if( action != null && action.equals( "registrar" ) ){
			
//			NavigationBeanIMP	nav = null;
//			
//			try{
//				nav = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
//			}catch(IllegalStateException i){
//				
//				elContext = faces.getELContext();
//				nav = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
//			
//			}
			
			
			faces     = FacesContext.getCurrentInstance();
			resolver  = faces.getApplication().getELResolver();
			elContext = faces.getELContext();
		
			nav = (NavigationBeanIMP) resolver.getValue(elContext, null, "navigationBean");
			
			//System.out.println("antes --  nav.regresaFormulario");
			
			action = nav.regresaFormulario();
			
		}else if (action != null && action.equals( "inversiones" )){
			
			//System.out.println("inversiones");
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			
			context.getSessionMap().put("navigationInvest", null);
			
			context.getSessionMap().put("myInvestments", null);
			
		}
		
		//System.out.println("regresaAction: "+action);
		
		return action;
		
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public String getEnabledAnalytics() {
		return enabledAnalytics;
	}

	public void setEnabledAnalytics(String enabledAnalytics) {
		this.enabledAnalytics = enabledAnalytics;
	}
	
	public void setELContext( ELContext elContext ){
		this.elContext  = elContext;
	}
	
	public void setExternalContext( ExternalContext external ){
		this.external = external;
	}
	
	public void setELResolver( ELResolver resolver ){
		this.resolver = resolver;
	}
	
}
