package mx.com.kubo.managedbeans.mesa.solicitud.perfil;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.InstitutionalInvestor;
import mx.com.kubo.model.InvestorCategory;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.InstitutionalInvestorService;
import mx.com.kubo.services.ProyectLoanService;

public abstract class FondeadorDMO 
implements FondeadorIMO 
{
	protected InstitutionalInvestorService service_fondeador;
	protected ProyectLoanService    service_proyect_loan;
	protected Change_controlService service_change_control;
	
	protected HtmlSelectOneMenu select_one_menu;
	protected HtmlInputText input_text ;
	
	protected RequestContext request;
	
	protected SessionBean sesion;
	protected NaturalPerson persona;
	protected ProyectLoan proyect_loan;
	
	protected ChangeBean change_bean;
	protected Change_control   changeCtrl;
	protected Change_controlPK changeCtrlPK;
	
	protected List<Change_control> bitacora_change_control;
	protected List <InstitutionalInvestor> lista_fondeadores;
	protected List <InvestorCategory>      lista_investor_category;
	
	protected StringBuilder sb;
	
	protected String IP_address_client;
	protected String institutional_investor_ORIGINAL;
	protected String institutional_investor_NEW;
	protected String investor_category_name;
	protected String category_name_ONLY_READ;
	protected String comment;
	protected String category_TOKEN;
	protected String SEPARADOR;
	
	protected Double ammount;
	
	protected Float factor;
	
	protected Integer kubo_property;
	protected Integer change_prospectus_id;
	protected Integer investor_category_id;
	protected Integer category_id_ONLY_READ;
	
	protected int prospectus_id;
	protected int company_id;
	protected int term_id;
	protected int frequency_id;
	
	protected boolean update_OK;
	protected boolean change_control_OK;
	protected boolean condiciones_ENABLED;
	protected boolean investor_category_ENABLED;
	
	protected final int NOT_SELECTED = 0;
	
	protected static final int SEMANAL    = 1;
	protected static final int CATORCENAL = 2;
	protected static final int QUINCENAL  = 3;
	protected static final int MENSUAL    = 4;
	
	protected FondeadorDMO()
	{
		change_bean = new ChangeBean();
		
		change_bean.setWhyChangeData("");
		change_bean.setNameTable("ln_proyect_loan");
		change_bean.setNameField("is_kubo_property");
	}

	public final void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}

	public final void setService_fondeador(InstitutionalInvestorService service) 
	{
		service_fondeador = service;
	}

	public final void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;				
	}

	public final void setSesion(SessionBean sesion) 
	{
		this.sesion = sesion;
		
		change_prospectus_id = sesion.getProspectus_id();
		IP_address_client    = sesion.getIP_address_client();
	}

	public final void setPersona(NaturalPerson persona) 
	{
		this.persona = persona;
		
		company_id    = persona.getNatPerPK().getCompany_id();
		prospectus_id = persona.getNatPerPK().getProspectus_id();		
	}

	public final void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		company_id           = proyect_loan.getProyectloanPk().getCompany_id();
		prospectus_id        = proyect_loan.getProyectloanPk().getProspectus_id();
		kubo_property        = proyect_loan.getIs_kubo_property();
		ammount              = proyect_loan.getAmmount();
		term_id              = proyect_loan.getTerm_id();
		frequency_id         = proyect_loan.getFrequency_id();
		investor_category_id = proyect_loan.getInvestor_category_id();
		
		investor_category_id = (investor_category_id != null) ? investor_category_id : 0;
		
		institutional_investor_ORIGINAL = proyect_loan.getInstitutionalInvestor().getName();
	}

	public final ProyectLoan getProyect_loan() 
	{
		return proyect_loan;
	}

	public final ChangeBean getChange_bean() 
	{
		return change_bean;
	}

	public Integer getKubo_property() 
	{
		return kubo_property;
	}

	public Integer getInvestor_category_id() 
	{
		return investor_category_id;
	}
	
	public final boolean isInvestor_category_ENABLED() 
	{
		return investor_category_ENABLED;
	}

	public final List<InstitutionalInvestor> getLista_fondeadores() 
	{
		return lista_fondeadores;
	}

	public final List<InvestorCategory> getLista_investor_category() 
	{
		return lista_investor_category;
	}
}
