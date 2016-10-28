package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.RoleFunctionController;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SystemParamService;
import safisrv.ws.CreditosServicios.ReestrucCreditoRequest;
import safisrv.ws.CreditosServicios.ReestrucCreditoResponse;


@ManagedBean
@ViewScoped
public class Restructure implements Serializable {

	/**
	 * 
	 */
	
	@ManagedProperty("#{systemParamServiceImp}")
	private SystemParamService systemparamservice;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectloanService;
	
	
	private static final long serialVersionUID = 1L;
	
	private RestructureBean proyect;
	
	private String monto;
	
	private String plazo;  
	
	private Double montoMax;
	
	private Double montoMin;
	
	private String montoNegotiation;
	
	private int frequency_id;
	
	private int termMax;
	
	private int term_id;
	
	private double payment;
	
	private double total_payment;
	
	private Double montoNegotiationD;
	
	private Simulator simulator ;
	
	private Double mx_cat;
	
	private boolean displayCase;
	
	private boolean renovacionAct;
	
	private String fromAction;
	
	private String msgBtn;
	
	private String btnPage; 
	
	private boolean restructDisplay;
	
	private String btnPageId; //22
	private String btnMenu; //menu6
	public  Locale locale = new Locale("es","mx");
	
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	public  java.text.NumberFormat num = java.text.NumberFormat.getNumberInstance(locale);
	
	@PostConstruct
	public void init(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		simulator 
	    = (Simulator) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "simulator");
		
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "sessionBean");
		
		if(proyect == null){
			
			displayCase = false;
			
		}else{
			
			displayCase = true;
			restructDisplay=true;
			
		}
		
		setPermissions(sesion.getRole_id());
		
	}
	
	public void inicializaReestructura(){
		
		if(proyect != null){
			
			monto = dec.format(proyect.getProyect().getAmmount());
			
			plazo = proyect.getProyect().getTerm_id()+" ";
			
			plazo += proyect.getProyect().getFrequency_id() == 1?"Semanas":proyect.getProyect().getFrequency_id() == 2?"Catorcenas":proyect.getProyect().getFrequency_id() == 3?"Quincenas":proyect.getProyect().getFrequency_id() == 4?"Meses":""; 
			
			Double d = (double) Math.round( proyect.getSaldoLiquidar()  * 100) / 100	;
			
			montoNegotiation = num.format(d);
			
			montoMax =  Double.parseDouble( montoNegotiation.replaceAll("$", "").replaceAll(",", "") );
			
			frequency_id = proyect.getProyect().getFrequency_id();
			
			term_id = ( proyect.getProyect().getFrequency_id()==1 ? 52 : proyect.getProyect().getFrequency_id()==2 ? 26 : proyect.getProyect().getFrequency_id()==3 ? 24 : proyect.getProyect().getFrequency_id()==4 ? 12 : 12);
			
			montoMin = 0D;
			
			SystemParamPK syspk = new SystemParamPK();
			
			syspk.setCompany_id(proyect.getProyect().getProyectloanPk().getCompany_id());
			syspk.setSystem_param_id(28); //Plazo maximo en meses para Renovación de Crédito
			
			SystemParam sys = systemparamservice.loadSelectedSystemParam(syspk);
			
			termMax = Integer.parseInt(sys.getValue());
			
			if( term_id > termMax ){
				term_id = termMax;
			}
			
			realizaSimulacion();
			
			displayCase = true;
			
			restructDisplay=true;
			
			if(fromAction!=null && fromAction.equals("EdoCuenta")){
				
				btnPage = "controlTable/edCuentaControl";
				btnPageId ="22";
				btnMenu = "menu6";
				
				msgBtn="Estado de Cuenta";
				
			}else{ //from Sabana 
				
				btnPage = "controlTable/searchRequest";
				btnPageId ="12";
				btnMenu = "menu1";
				msgBtn="Solicitud";
				
			}
			
		}else{
			
			displayCase = false;
			restructDisplay=false;
			
		}
		
	}
	
	
	public void realizaSimulacion(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		Simulator simulator 
	    = (Simulator) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "simulator");
		
		simulator.setFrequency_id(frequency_id);
		
		simulator.generaNumCuotas();
		
		montoNegotiationD = Double.parseDouble( montoNegotiation.replace(",", "").replace("$","") );
		
		simulator.setAmmount(montoNegotiationD);
		simulator.setTerm_id(term_id);
		simulator.setTasaTotal(proyect.getProyect().getRate());		
		
		simulator.generaMontoCuota2();
		
		setMx_cat(simulator.getCat());
		setPayment(simulator.getMontoCuota());
		setTotal_payment(simulator.getTotalPagar());
		
		
	}
	
	public void modificaCondiciones(){
		
		realizaSimulacion();
		
	}
	
	private void setPermissions(int role_id){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		RoleFunctionController rfc = (RoleFunctionController) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "roleFunctionController");
		
		List<RoleFunction> rolefunctionlistbyrole = rfc.getFunctionByRole(role_id);
		
		for(RoleFunction rf : rolefunctionlistbyrole){
			
			
			
			if( rf.getPk().getFunction_id() == 5 ){ // Reestructura Creditos
				
				renovacionAct = true;
				
			}
			
		}
		
	}
	
	public boolean createRestructure(){
		try{
			
			
				System.out.println("Creando Reestructura");
			
				safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator    locator = new safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator();
				safisrv.ws.CreditosServicios.SAFIServicios service = locator.getSAFIServiciosSoap11();
				
				SystemParamPK syspk = new SystemParamPK();
				
				syspk.setCompany_id(proyect.getProyect().getProyectloanPk().getCompany_id());
				syspk.setSystem_param_id(39);//AjusFecVen
				
				SystemParam sys = systemparamservice.loadSelectedSystemParam(syspk);
				String fecVen = sys.getValue();
				
				syspk.setSystem_param_id(40);//FactorMora
				sys = systemparamservice.loadSelectedSystemParam(syspk);
				String factorMora = sys.getValue();
				
				syspk.setSystem_param_id(41);//tipoPagoCap
				sys = systemparamservice.loadSelectedSystemParam(syspk);
				String tipoPagoCap = sys.getValue();
				
				ReestrucCreditoRequest rees = new ReestrucCreditoRequest();
				
				String freq = "";
				
				switch ( frequency_id ){
				case 1:
						freq = "S";
					break;
				case 2:
					freq = "C";
					break;
				case 3:
					freq = "Q";
					break;
				case 4:
					freq = "M";
					break;
				}
				
				
				rees.setAjustarFecVen(fecVen);
				rees.setClienteID(proyect.getProyect().getPerson().getSafi_client_id());
				rees.setFactorMora(factorMora);
				rees.setFrecuencia(freq+"");
				rees.setIvaComisionApertura("0");
				rees.setMontoComisionApertura("0");
				rees.setNumeroAmortizaciones(term_id+"");
				rees.setProductoCreditoID(proyect.getProyect().getProyect().getType_id()+"");
				rees.setRelacionado(proyect.getProyect().getSafi_credit_id());
				rees.setTasaFija(proyect.getProyect().getRate()+"");
				rees.setTipoPagoCapital(tipoPagoCap);
				
				System.out.println( "safisrv.ws.CreditosServicios.SAFIServiciosServiceLocator().getSAFIServiciosSoap11.reestrucCredito ");
				System.out.println( " rees.setAjustarFecVen(" +fecVen );
				System.out.println( "rees.setClienteID(" +proyect.getProyect().getPerson().getSafi_client_id() );
				System.out.println( "rees.setFactorMora(" +factorMora ) ;
				System.out.println( "rees.setFrecuencia(" +freq+"" );
				System.out.println( "rees.setIvaComisionApertura(" +"0" );
				System.out.println( "rees.setMontoComisionApertura(" +"0" );
				System.out.println( "rees.setNumeroAmortizaciones(" +term_id+"" );
				System.out.println( "rees.setProductoCreditoID(" +proyect.getProyect().getProyect().getType_id()+"");
				System.out.println( "rees.setRelacionado(" +proyect.getProyect().getSafi_credit_id() ) ;
				System.out.println( "rees.setTasaFija(" +proyect.getProyect().getRate()+"" );
				System.out.println( "rees.setTipoPagoCapital(" +tipoPagoCap);
				
				
				ReestrucCreditoResponse resp =  service.reestrucCredito(rees);
				
				System.out.println("resultado: "+resp.getMensajeRespuesta()+"   --  codigo respuesta: "+resp.getCodigoRespuesta());
				
				if(resp != null && resp.getCodigoRespuesta() != null && resp.getCodigoRespuesta().equals("0")){
					ProyectLoan myPyLn = new ProyectLoan();
					
					ELContext elContext = FacesContext.getCurrentInstance().getELContext();
					
					Simulator simulator 
				    = (Simulator) FacesContext.getCurrentInstance().getApplication()
				        .getELResolver().getValue(elContext, null, "simulator");
					
					myPyLn.setAmmount(simulator.getAmmount());
					myPyLn.setDay_published(new Date());
					myPyLn.setDays_online(15);
					myPyLn.setFrequency_id(simulator.getFrequency_id());
					myPyLn.setFunding_type('T');
					myPyLn.setKubo_score_a(proyect.getProyect().getKubo_score_a());
					myPyLn.setKubo_score_b(proyect.getProyect().getKubo_score_b());
					myPyLn.setBc_score(proyect.getProyect().getBc_score());
					myPyLn.setMx_cat(simulator.getCat());
					myPyLn.setRate_with_opening( proyect.getProyect().getRate() );
					myPyLn.setOpening_commission_amount(null);
					myPyLn.setOpening_payment(null);
					myPyLn.setMx_solicitud_buro(proyect.getProyect().getMx_solicitud_buro());
					myPyLn.setMin_ammount(simulator.getAmmount());								
					myPyLn.setPayment(simulator.getMontoCuota());
					myPyLn.setVerification_score(3);
					myPyLn.setOpening_commission(null);
					myPyLn.setLiquidity(null);
					myPyLn.setLoan_type("RST");
					myPyLn.setTerm_id(term_id);
					myPyLn.setRate(proyect.getProyect().getRate());
					myPyLn.setStatus_id(3);
					myPyLn.setSafi_credit_id(resp.getCreditoID());
					myPyLn.setAmount_founded(simulator.getAmmount());
					myPyLn.setProyectloanPk(proyect.getProyect().getProyectloanPk());
					myPyLn.setInvestment_bite(250D);
					myPyLn.setInvestors_number(1);
					myPyLn.setIs_kubo_property(0);
					proyectloanService.add(myPyLn);
					
					restructDisplay=false;
					
				}
				
				
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean isDisplayCase() {
		return displayCase;
	}

	public void setDisplayCase(boolean displayCase) {
		this.displayCase = displayCase;
	}
	
	public Double getMontoNegotiationD() {
		return montoNegotiationD;
	}

	public void setMontoNegotiationD(Double montoNegotiationD) {
		this.montoNegotiationD = montoNegotiationD;
	}

	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

	public Double getMx_cat() {
		return mx_cat;
	}

	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}
	
	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public double getTotal_payment() {
		return total_payment;
	}

	public void setTotal_payment(double total_payment) {
		this.total_payment = total_payment;
	}
	
	public int getFrequency_id() {
		return frequency_id;
	}

	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
	}

	public int getTerm_id() {
		return term_id;
	}

	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}

	public String getMontoNegotiation() {
		return montoNegotiation;
	}

	public void setMontoNegotiation(String montoNegotiation) {
		this.montoNegotiation = montoNegotiation;
	}

	public Double getMontoMax() {
		return montoMax;
	}

	public void setMontoMax(Double montoMax) {
		this.montoMax = montoMax;
	}

	public Double getMontoMin() {
		return montoMin;
	}

	public void setMontoMin(Double montoMin) {
		this.montoMin = montoMin;
	}

	public int getTermMax() {
		return termMax;
	}

	public void setTermMax(int termMax) {
		this.termMax = termMax;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public RestructureBean getProyect() {
		return proyect;
	}

	public void setProyect(RestructureBean proyect) {
		this.proyect = proyect;
	}
	
	public String getFromAction() {
		return fromAction;
	}

	public void setFromAction(String fromAction) {
		this.fromAction = fromAction;
	}

	public String getMsgBtn() {
		return msgBtn;
	}

	public void setMsgBtn(String msgBtn) {
		this.msgBtn = msgBtn;
	}

	public String getBtnPage() {
		return btnPage;
	}

	public void setBtnPage(String btnPage) {
		this.btnPage = btnPage;
	}

	public String getBtnPageId() {
		return btnPageId;
	}

	public void setBtnPageId(String btnPageId) {
		this.btnPageId = btnPageId;
	}

	public String getBtnMenu() {
		return btnMenu;
	}

	public void setBtnMenu(String btnMenu) {
		this.btnMenu = btnMenu;
	}

	public SystemParamService getSystemparamservice() {
		return systemparamservice;
	}

	public void setSystemparamservice(SystemParamService systemparamservice) {
		this.systemparamservice = systemparamservice;
	}
	
	
	public boolean isRenovacionAct() {
		return renovacionAct;
	}

	public void setRenovacionAct(boolean renovacionAct) {
		this.renovacionAct = renovacionAct;
	}

	public ProyectLoanService getProyectloanService() {
		return proyectloanService;
	}

	public void setProyectloanService(ProyectLoanService proyectloanService) {
		this.proyectloanService = proyectloanService;
	}

	public boolean isRestructDisplay() {
		return restructDisplay;
	}

	public void setRestructDisplay(boolean restructDisplay) {
		this.restructDisplay = restructDisplay;
	}
	
	
	
}
