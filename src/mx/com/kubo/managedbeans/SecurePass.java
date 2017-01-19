package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.kubows.NotificadorConfigRequest;
import mx.com.kubo.kubows.PublicProyect;
import mx.com.kubo.kubows.PublicProyectServiceLocator;
import mx.com.kubo.kubows.WsResponse;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.Clients;
import mx.com.kubo.model.ClientsPK;
import mx.com.kubo.model.Investor;
import mx.com.kubo.model.InvestorPK;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.PasswordHistory;
import mx.com.kubo.model.PasswordHistoryPK;
import mx.com.kubo.model.ProspectusExtra;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ClientsService;
import mx.com.kubo.services.InvestorService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.PasswordHistoryService;
import mx.com.kubo.services.ProspectusExtraService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.ScreenService;
import mx.com.kubo.services.SimulatorService;
import mx.com.kubo.services.SystemParamService;
import mx.com.kubo.tools.Utilities;

@ManagedBean
@ViewScoped
public class SecurePass implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@ManagedProperty("#{passwordHistoryServiceImp}")
	protected PasswordHistoryService passwordhistoryservice;
	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService savingaccountservice;
	
	@ManagedProperty("#{investorServiceImp}")
	protected InvestorService investorservice;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
	@ManagedProperty("#{simulatorServiceImp}")
	protected SimulatorService simulatorService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
	
	@ManagedProperty("#{clientsServiceImp}")
	protected ClientsService clientsservice;
	
	@ManagedProperty("#{prospectusExtraServiceImp}")
	protected ProspectusExtraService prospectusextraservice;
	
	protected String [] lista_receptores;
	
	protected final String CONFIRMACION_CAMBIO_PASSWORD = "34";
	protected final int ESTADO_CUENTA_ENABLED = 13;
	protected final int INVERSIONES_SALDOS_MOVIMIENTOS = 15;
	protected final int INVERSIONES_ALTA_CLABE_INTERBANCARIA = 18;
	protected final int RECHAZO_AUTOMATICO = 6;
	
	protected NotificadorConfigRequest request_notificar_config;
	
	protected PublicProyectServiceLocator locator;
	protected PublicProyect kubo_services;
	protected WsResponse    response;
	
	private String email;
	private String name;
	private String password;
	private String warningConfPass;
	private String confPass;
	
	private String action = "";
	private String msgPass = "";
	
	private SessionBean sesion;
	
	private Membership member;
	
	@PostConstruct
	public void init(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		sesion = (SessionBean) FacesContext.getCurrentInstance()
												.getApplication().getELResolver()
												.getValue(elContext, null, "sessionBean");
		
		MembershipPK mpk = new MembershipPK() ;
		
		mpk.setCompany_id( sesion.getCompany_id() );
		mpk.setProspectus_id( sesion.getProspectus_id() );
		
		member = membershipService.getMembershipById(mpk);
		
		email 	=  	member.getEmail() ;
		name 	= 	member.getPerson().getFirst_name() ;
		
		System.out.println( "Iniciando  Serializable name: " + name +" email: " + email );
		
	}
	
	public void validaPass_portal(){
		
		if(password!=null&&confPass!=null&&password.length()>0&&confPass.length()>0)
		{
			if(password.equals(confPass))
			{
				
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=true;</script>");
			} else {
				
				setWarningConfPass("<script>$('#confpass').addClass('requiredClass');varPass=false;  alertify.error('Error al confirmar tu contraseña');</script>");
			
			}
		} else {
				
				setWarningConfPass("<script>$('#confpass').removeClass('requiredClass');varPass=false;</script>");
		}
	}
	
	public String saveNewPass(){
		
		String pass = Utilities.encrypt( password );
		msgPass  =  "";
		
		if(!passwordhistoryservice.existPassword(pass, sesion.getProspectus_id(), sesion.getCompany_id()) )
		{			
			member.setPassword(pass);
			member.setIs_client_pass("S");
			
			membershipService.update(member);
			
			ProspectusExtra extra =  new ProspectusExtra();
			
			extra.setProspectus_id(member.getMembershipPK().getProspectus_id());
			extra.setValue1_ps( Utilities.encodeBase64(password));
			
			prospectusextraservice.saveProspectusExtra(extra);
			
			PasswordHistoryPK passHPK = new PasswordHistoryPK();
			
			passHPK.setCompany_id(sesion.getCompany_id());
			passHPK.setProspectus_id(sesion.getProspectus_id());
			
			
			PasswordHistory ph = new PasswordHistory();
			ph.setDate_changed(new Date());
			ph.setPassword(pass);
			ph.setPwdhpk(passHPK);
			
			passwordhistoryservice.savePasswordHistory(ph);
			
			init_notificar_evento();
			
			sesion.setBlochHeader(true);
			
			if(sesion.getArea().equals('M'))
			{							
				action =  "controlTable";
					
			} else if(sesion.getArea().equals('I')) {
										
				init_inversionista();
				
			} else {
				
				init_acreditado();
			}	
			
			return action ;
			
		}else{
			
			msgPass  =  "La contraseña ya ha sido asignada anteriormente. Por favor intente con otra.";
			
			return "";
			
		}
		
		
		
	}
	
	private void init_notificar_evento()
	{
		try 
		{							
			lista_receptores = new String []{"TO::" + member.getEmail()};
			
			request_notificar_config = new NotificadorConfigRequest();												
			request_notificar_config.setEvento_id(CONFIRMACION_CAMBIO_PASSWORD);
			request_notificar_config.setCalled_FROM("ChangePass.init_notificar_evento()");	
			request_notificar_config.setCompany_id(member.getMembershipPK().getCompany_id() + "");
			request_notificar_config.setProspectus_id(member.getMembershipPK().getProspectus_id() + "");													
			request_notificar_config.setLista_receptores (lista_receptores);
			
			locator = new PublicProyectServiceLocator();
		
			kubo_services = locator.getPublicProyect();
			
			response = kubo_services.notificar(request_notificar_config);
			
			System.out.println("ChangePass.print_response():");		
			System.out.println("> status  = " + response.getStatus());
			System.out.println("> folio   = " + response.getFolio());
			System.out.println("> message = " + response.getMessage());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public Membership getMember() {
		return member;
	}

	public void setMember(Membership member) {
		this.member = member;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWarningConfPass() {
		return warningConfPass;
	}

	public void setWarningConfPass(String warningConfPass) {
		this.warningConfPass = warningConfPass;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public PasswordHistoryService getPasswordhistoryservice() {
		return passwordhistoryservice;
	}

	public void setPasswordhistoryservice(PasswordHistoryService passwordhistoryservice) {
		this.passwordhistoryservice = passwordhistoryservice;
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}
	
	private void init_inversionista(){
		
		InvestorPK investor_PK = new InvestorPK();
		
		investor_PK.setCompany_id(sesion.getCompany_id());
		investor_PK.setProspectus_id(sesion.getProspectus_id());
		
		boolean flagAccount = false;
		
		
		
		Investor investor = investorservice.getInvestorById(investor_PK);
		
		if(investor == null)
		{								
			investor = new Investor();
			investor.setDate_approved(null);
			investor.setDate_published(null);
			investor.setPk(investor_PK);
			investor.setStatus_id(0);
			
			if(investorservice.addInvestor(investor))
			{
				System.out.println("Inversionista Dado de alta: "+sesion.getProspectus_id());
				
			} else {
				
				System.out.println("Error Alta Inversionista: "+sesion.getProspectus_id());
			}
		}
		
		List<SavingAccount> countList = savingaccountservice.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
		
		if(countList != null && countList.size() > 0)
		{
			boolean inversiones = false;
		
			for(SavingAccount count : countList)
			{
				if(count.getStatus() != null && count.getStatus() == 1)
				{
					inversiones = true;
					break;
					
				} else if( count.getStatus() != null && count.getStatus() == 0)
				{
					flagAccount = true;
				}
			}
			
			if( flagAccount )
			{									
				for(SavingAccount count : countList)
				{										
					if(count.getStatus() == null || count.getStatus() == 0)
					{
						boolean verify_account_ENABLED = false;
						
						verify_account_ENABLED = savingaccountservice.verifyAccount(count);
						
						if(!inversiones)
						{
							inversiones = verify_account_ENABLED;
						}
						
						if(inversiones)
						{
							InvestorPK invPk2 = new InvestorPK();
							
							invPk2.setCompany_id(sesion.getCompany_id());
							invPk2.setProspectus_id(sesion.getProspectus_id());
							
							Investor inv2 = investorservice.getInvestorById(invPk2);
							inv2.setStatus_id(3);
							
							investorservice.updateInvestor(inv2);
													
							init_client();							
						}											
					}										
				}									
			}
			
			if(inversiones)
			{
				init_access(INVERSIONES_SALDOS_MOVIMIENTOS);								
				
				action =  "inversiones";
				
			} else {				
				
				if(investor != null && investor.getStatus_id() != 0)
				{
					init_access(INVERSIONES_ALTA_CLABE_INTERBANCARIA);
					
					ScreenPK screen_PK = new ScreenPK();
					screen_PK.setCompany_id(sesion.getCompany_id());
					screen_PK.setScreen_id(INVERSIONES_ALTA_CLABE_INTERBANCARIA);
					
					Screen screen = screenservice.getScreenById(screen_PK);
					
					sesion.setLastPage(screen.getName());
				}
					
				action =  "registrar";
			}
						
		} else {
			
			if(!sesion.isBlochHeader())
			{
				action = "registrar";
				
			} 
			
			action =  "registrar";
		}
	}
		
	protected void init_access(int screen_id) 
	{
		Access access = new Access();
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(screen_id);
		access.setPercentage(0);
		access.setUser_agent( sesion.getUser_agent() );
		access.setDevice_info( sesion.getDevice_info() );
		access.setUrl_access( sesion.getUrl_access() );
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access,true);
	}
	
	protected void init_acreditado() 
	{
		if(sesion.getArea().toString().equals("L"))
		{	
			
			if(sesion.getLastPage()!= null && sesion.getLastPage().equals("registro/cierre") )
			{
				inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),true);
				
			} else {
				
				inicializaSimulador(sesion.getCompany_id(), sesion.getProspectus_id(),false);
			}
			
		}
	
		SystemParamPK system_param_PK = new SystemParamPK();
		
		system_param_PK.setCompany_id(sesion.getCompany_id());
		system_param_PK.setSystem_param_id(ESTADO_CUENTA_ENABLED);
		
		SystemParam system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
			
		
						
		if((system_param.getValue()!=null && system_param.getValue().equals("S")) || (system_param.getValue()!=null && system_param.getValue().equals("N") && member.getIs_active().equals(1)))
		{								
			if(member.getPerson().getSafi_client_id() != null && member.getPerson().getSafi_client_id().trim().length() > 0)
			{									
				System.out.println("Session iniciada Satisfactoriamente!! ");
									
				action =  "edocuenta";
				
		} else {
			
			System.out.println("Session iniciada Satisfactoriamente!! ");
			
			ProyectLoan proyectloan = proyectloanservice.getMaxProyectLoanByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			init_rechazo_automatico_ENABLED( proyectloan );			
			
			if(!sesion.isBlochHeader())
			{
				this.action = "registrar";
				
			} else {
				
				this.action = "";
			}

				action =  "registrar";
			}
			
		} else {
			
			System.out.println("Session iniciada Satisfactoriamente!! ");
				
			action =  "registrar";
		}
		
	}
	
	protected void inicializaSimulador(Integer company_id,Integer prospectus_id, boolean  isSafi)
	{
		FacesContext faces     = FacesContext.getCurrentInstance();
		ELContext elContext = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
				
		Simulator simulator = (Simulator) resolver.getValue(elContext, null, "simulator");
		sesion    = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(prospectus_id, company_id) ;
		
		if(sim != null)
		{
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			
			if(sesion.getRate()!= null)
			{
				simulator.setTasaTotal(sesion.getRate());
				
			} else {
				
				simulator.setTasaTotal(61.50D);
			}
			
			if( sim.getTerm_id() != null )
			{
				simulator.setTerm_id(sim.getTerm_id());
			}
			
			if( sim.getPurpose_id() != null )
			{
				simulator.setPurpose_id(sim.getPurpose_id());
			}
		}
		
		simulator.simulaCred( isSafi );		
	}
	
	private void init_rechazo_automatico_ENABLED( ProyectLoan proyectloan ) 
	{
		boolean flagRechazado = false;
		
		if( proyectloan != null && proyectloan.getStatus_id() != null && proyectloan.getStatus_id() ==11 )
		{
			SystemParamPK system_param_PK = new SystemParamPK();
			system_param_PK.setCompany_id(sesion.getCompany_id());
			system_param_PK.setSystem_param_id(65);
			
			SystemParam system_param = systemparamservice.loadSelectedSystemParam( system_param_PK );
			
			if( system_param != null )
			{									
				Calendar c = Calendar.getInstance();
				c.setTime(proyectloan.getConsulting_date());
				Calendar c2 = Calendar.getInstance();
				c2.setTime( new Date() );
				
				long l = c2.getTimeInMillis() - c.getTimeInMillis();
				
				long dif = l /((24 * 60 * 60 * 1000)); 
				
				if( Integer.parseInt(system_param.getValue()) < dif )
				{
					flagRechazado = true;
				}									
			}									
		}
		
		if( flagRechazado )
		{									
			init_access(RECHAZO_AUTOMATICO);
			
			ScreenPK screen_PK = new ScreenPK();
			screen_PK.setCompany_id(sesion.getCompany_id());
			screen_PK.setScreen_id(RECHAZO_AUTOMATICO);
			
			Screen screen =  screenservice.getScreenById(screen_PK);
			
			sesion.setLastPage(screen.getName());
			
		}
	}
	
	private void init_client() 
	{
		if( !sesion.isIsClient() )
		{																				
			ClientsPK cliente_PK = new ClientsPK();
			
			Clients cliente = new Clients();
			
			cliente.setPk(cliente_PK);
			cliente.setSafi_client_id(member.getPerson().getSafi_client_id());
			
			clientsservice.saveClient(cliente);
			
			sesion.setIsClient(true);								
		}
	}

	public SavingAccountService getSavingaccountservice() {
		return savingaccountservice;
	}

	public void setSavingaccountservice(SavingAccountService savingaccountservice) {
		this.savingaccountservice = savingaccountservice;
	}

	public InvestorService getInvestorservice() {
		return investorservice;
	}

	public void setInvestorservice(InvestorService investorservice) {
		this.investorservice = investorservice;
	}

	public ScreenService getScreenservice() {
		return screenservice;
	}

	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}

	public AccessService getService_access() {
		return service_access;
	}

	public void setService_access(AccessService service_access) {
		this.service_access = service_access;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}

	public SimulatorService getSimulatorService() {
		return simulatorService;
	}

	public void setSimulatorService(SimulatorService simulatorService) {
		this.simulatorService = simulatorService;
	}

	public ProyectLoanService getProyectloanservice() {
		return proyectloanservice;
	}

	public void setProyectloanservice(ProyectLoanService proyectloanservice) {
		this.proyectloanservice = proyectloanservice;
	}

	public ClientsService getClientsservice() {
		return clientsservice;
	}

	public void setClientsservice(ClientsService clientsservice) {
		this.clientsservice = clientsservice;
	}

	public String[] getLista_receptores() {
		return lista_receptores;
	}

	public void setLista_receptores(String[] lista_receptores) {
		this.lista_receptores = lista_receptores;
	}

	public NotificadorConfigRequest getRequest_notificar_config() {
		return request_notificar_config;
	}

	public void setRequest_notificar_config(NotificadorConfigRequest request_notificar_config) {
		this.request_notificar_config = request_notificar_config;
	}

	public PublicProyectServiceLocator getLocator() {
		return locator;
	}

	public void setLocator(PublicProyectServiceLocator locator) {
		this.locator = locator;
	}

	public PublicProyect getKubo_services() {
		return kubo_services;
	}

	public void setKubo_services(PublicProyect kubo_services) {
		this.kubo_services = kubo_services;
	}

	public WsResponse getResponse() {
		return response;
	}

	public void setResponse(WsResponse response) {
		this.response = response;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCONFIRMACION_CAMBIO_PASSWORD() {
		return CONFIRMACION_CAMBIO_PASSWORD;
	}

	public int getESTADO_CUENTA_ENABLED() {
		return ESTADO_CUENTA_ENABLED;
	}

	public int getINVERSIONES_SALDOS_MOVIMIENTOS() {
		return INVERSIONES_SALDOS_MOVIMIENTOS;
	}

	public int getINVERSIONES_ALTA_CLABE_INTERBANCARIA() {
		return INVERSIONES_ALTA_CLABE_INTERBANCARIA;
	}

	public int getRECHAZO_AUTOMATICO() {
		return RECHAZO_AUTOMATICO;
	}

	public String getMsgPass() {
		return msgPass;
	}

	public void setMsgPass(String msgPass) {
		this.msgPass = msgPass;
	}

	public ProspectusExtraService getProspectusextraservice() {
		return prospectusextraservice;
	}

	public void setProspectusextraservice(ProspectusExtraService prospectusextraservice) {
		this.prospectusextraservice = prospectusextraservice;
	}
	
}
