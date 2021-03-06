package mx.com.kubo.managedbeans;

import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.PrevencionLD;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.services.BehaviorProcessService;
import mx.com.kubo.services.PrevencionLDService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SavingAccountService;
import mx.com.kubo.services.fondeo.ServiceFondeoIMO;
import mx.com.kubo.services.impl.SystemParamServiceImp;

public abstract class CreaCreditoServiceDMO 
{	
	@ManagedProperty("#{savingAccountServiceImp}")
	protected SavingAccountService service_saving_account;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectloanservice;
		
	@ManagedProperty("#{prevencionLDServiceImp}")
	protected PrevencionLDService service_PLD;
	
	@ManagedProperty("#{behaviorProcessServiceImp}")
	protected BehaviorProcessService behaviorprocessservice;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamServiceImp system_param_service;
		
	protected ServiceFondeoIMO service_fondeo;
	
	protected RequestContext request;
	protected FacesContext    faces;
	protected ELResolver      resolver;
	protected ELContext       elContext;	
	
	protected SessionBean sesion;
			
	protected NaturalPerson  natural_person;
	
	protected ProyectLoan   proyect_loan;
	protected ProyectLoanPK proyect_loan_PK;
	
	protected SavingAccount   saving_account;
	protected SavingAccountPK saving_account_PK;
	
	protected PrevencionLD pld;
	
	protected Map<String, Object> attributes;
		
	protected List<SavingAccount> lista_saving_account_ACTIVA;
	protected List<SavingAccount> lista_saving_account;	
	
	protected List<String> lista_errores;
	
	protected String prospStr;
	protected String ciaStr;
	protected String proyecStr;
	protected String proyloanStr;
		
	protected String SAFI_account_id;
	protected String SAFI_client_id;	
	protected String SAFI_seguro_id;	
					
	protected String acreditado_IFE;
	
	protected Character acreditado_area;	
	
	//protected Integer nacionalidad;
	protected Integer SAFI_prospectus_id;
	
	protected int company_id;
	protected int prospectus_id;	
	protected int proyect_id;
	protected int proyect_loan_id;
		
	protected boolean status_funding;			
	protected boolean SAFI_prospecto_DISABLED;
	protected boolean SAFI_account_NEW;
	protected boolean SAFI_client_ENABLED;
	//protected boolean extranjero_ENABLED;
	
	protected boolean alta_prospecto_OK;
	protected boolean alta_cliente_ENABLED;
	protected boolean solicitud_credito_OK;
	protected boolean cuenta_OK;
	protected boolean param_values_OK;
	protected boolean is_inussual_person;

	protected final String SAFI_ALTA_PROSPECTO_ACTUAL;
	
	//protected final int EXTRANJERO = 0;
		
	protected CreaCreditoServiceDMO()
	{
		SAFI_ALTA_PROSPECTO_ACTUAL  = "Prospecto ya dado de alta en safi con anterioridad con prospectoSafi: ";			
	}
		
	abstract void creaCliente(ActionEvent evento);
			
	abstract boolean callWSSafiAltaPros();	
	abstract boolean creaCuentaSafi();
	
	abstract boolean callSPSafiAltaIFE (int prospectus_id, int company_id, ProyectLoan proyect_loan);
	
	abstract String creaClienteSAFI();
	
	abstract boolean creaSolicitud();
	abstract boolean creaCreditoSafi ();
	
	abstract boolean creaSeguroVida  (ProyectLoan pl , SavingAccount saving);		
	
	abstract void creaPLDCuentaSAFI();	
	abstract void createPLDSAFI    ();	

	public void setService_saving_account(SavingAccountService service) 
	{
		service_saving_account = service;
	}

	public void setProyectloanservice(ProyectLoanService service) 
	{
		proyectloanservice = service;
	}

	public void setService_PLD(PrevencionLDService service) 
	{
		service_PLD = service;
	}
		
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
	}

	public void setLista_errores(List<String> lista_errores) 
	{
		this.lista_errores = lista_errores;
	}

	public void setParam_values_OK(boolean param_values_OK )
	{
		this.param_values_OK = param_values_OK;
	}
	
	public void setSolicitud_credito_OK(boolean solicitud_credito_OK)
	{
		this.solicitud_credito_OK = solicitud_credito_OK;
	}
	
	public List<String> getLstErrors() 
	{
		return lista_errores;
	}

	public BehaviorProcessService getBehaviorprocessservice() {
		return behaviorprocessservice;
	}

	public void setBehaviorprocessservice(BehaviorProcessService behaviorprocessservice) {
		this.behaviorprocessservice = behaviorprocessservice;
	}

	public SystemParamServiceImp getSystem_param_service() {
		return system_param_service;
	}

	public void setSystem_param_service(SystemParamServiceImp system_param_service) {
		this.system_param_service = system_param_service;
	}
	

/*
	private boolean isEnabledInv( Integer company )
	{		
		SystemParamPK pk = new SystemParamPK();
		
		boolean flag = false;
		
		pk.setCompany_id( company );
		
		pk.setSystem_param_id(51);//cobertura
		SystemParam sys = systemparamservice.loadSelectedSystemParam(pk);
		
		String flagInv = "";
		
		if( sys != null )
		{			
			flagInv = sys.getValue();
			
			if(flagInv.equals("S"))
			{
				flag=true;
			}			
		}
		
		return flag;		
	}
	

	private String creaCuentaTemp(){
		String cuenta="";
		
		Calendar cal2 = Calendar.getInstance();

		String name = ((cal2.getTimeInMillis()) / 1000) + "";
		
		while(name.length()<25){
			Random r = new Random(7654L);
			name = (r.nextInt()+"")+name;
		}
		
		cuenta = name.substring(name.length()-18);
		
		
		return cuenta;
	}
	
	private String getMontoComisionSinIva(ProyectLoan pl)
	{
		Double comision = 0D;
		
			if(pl.getOpening_commission()!=null&&(pl.getOpening_commission()+"").length()>0){
				Double c = pl.getOpening_commission()/100;
				comision = c * pl.getAmmount();
				comision = ((comision)/(1.16));
				comision = ((double)Math.round((comision)*100)/100);
			}
		
		return comision.toString();
	}

*/
}
