package mx.com.kubo.managedbeans.portal.efectivo;

import java.util.List;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.ListPorc;
import mx.com.kubo.model.LoanNegotiation;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.portal.AccessIMO;
import mx.com.kubo.portal.expenses.ExpenseCalculatorIMO;
import mx.com.kubo.portal.expenses.PercentageCalculatorIMO;
import mx.com.kubo.portal.efectivo.IncomeCalculatorIMO;
import mx.com.kubo.portal.efectivo.IncomeDetailIMO;
import mx.com.kubo.portal.efectivo.IndicadoresIMO;
import mx.com.kubo.portal.efectivo.LoanNegotiationIMO;
import mx.com.kubo.portal.reader.ParameterReaderIMO;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ExpensesHistoryService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.ExpensesTypeService;
import mx.com.kubo.services.IncomeDetailHistoryService;
import mx.com.kubo.services.IncomeDetailService;
import mx.com.kubo.services.IncomeHistoryService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.IncomeTypeService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.SystemParamService;

public abstract class IncomesExpensesDMO
{
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService service_access; 
		
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService service_proyect_loan;
	
	@ManagedProperty("#{incomeServiceImp}")
	protected IncomeService ingresosService;
	
	@ManagedProperty("#{incomeHistoryServiceImp}")
	protected IncomeHistoryService incomehistoryService;
	
	@ManagedProperty("#{incomeDetailHistoryServiceImp}")
	protected IncomeDetailHistoryService incomedetailhistoryService;
	
	@ManagedProperty("#{incomeDetailServiceImp}")
	protected IncomeDetailService incomeDetailService;
	
	@ManagedProperty("#{incomeTypeServiceImp}")
	protected IncomeTypeService service_income_type;
	
	@ManagedProperty("#{businessServiceImp}")
	protected BusinessService businessService;	
	
	@ManagedProperty("#{employmentServiceImp}")
	protected EmploymentService employmentService;	
	
	@ManagedProperty("#{change_controlServiceImp}")
	protected Change_controlService service_change_control;
	
	@ManagedProperty("#{systemParamServiceImp}")
	protected SystemParamService systemparamservice;
	
    @ManagedProperty("#{expensesServiceImp}")
	protected ExpensesService egresosService;
    
    @ManagedProperty("#{expensesTypeServiceImp}")
    protected ExpensesTypeService service_expenses_type;
    
    @ManagedProperty("#{expensesHistoryServiceImp}")
    protected ExpensesHistoryService service_expenses_history;
	    	
	protected    FacesContext faces;
	protected  RequestContext request;	
	protected       ELContext elContext;
	protected ExternalContext external;
	
	protected ELResolver resolver;
	
	protected HtmlInputText input_text;
	protected HtmlSelectOneRadio select_radio;
	
	protected SessionBean sesion;
	protected Simulator simulator;
	
	protected SystemParam   system_param;
	protected SystemParamPK system_param_PK;
	
	protected               AccessIMO auditor;
	protected      ParameterReaderIMO reader;
	protected     IncomeCalculatorIMO calculator;
	protected         IncomeDetailIMO detail;
	protected    ExpenseCalculatorIMO expenses;
	protected PercentageCalculatorIMO percentage;
	protected          IndicadoresIMO indicador;
	protected      LoanNegotiationIMO modifier;
		
	protected ProyectLoan proyect_loan;	
	
	protected Income incomeUpdate;
	protected Income checkOtherIncome;
	protected IncomeDetail incomedetail;
	
	protected ExpensesBean ammountConsolidate;
	
	protected LoanNegotiation negotiation;		
	
	protected ChangeBean changeConsolidate;
	protected Change_control lastChange;
	
	protected List<Change_control> listChangConsolTemp;
	
	protected List <Income>     listIncomeTemp;
	protected List <IncomeBean> listIncomeBean;
	protected List <IncomeType> listIncomeType;
	protected List <IncomeDetailsBean> 	 listBusinessDetails;
		
	protected List <Expenses> 	  listExpenses;
	protected List <ExpensesType> listExpensesType;
	protected List <ExpensesBean> listExpensesBean;
	protected List <ListPorc>     listPorcClientBean;
	
	protected List <Business> 	listBusiness;
	protected List <Employment> listEmployment;		
	

	
	protected String page_title;
	protected String description_other_income;
	protected String expense_id;
	protected String porcActionUser;	
	protected String access_from;

	protected Double provider_total_init;
	protected Double totalExpenses;
	protected Double totalExpensesControl;	
	protected Double totalIncome;
	protected Double totalIncomeControl;
	protected Double excedenteControl=0D;		
	protected Double liquidezCliControl;
	protected Double pagoMenControl;	
	protected Double ingreso_neto_cliente;
	protected Double ingreso_neto_control;
	protected Double monto_deudas_cliente;	
	protected Double monto_deudas_control;
	
	protected Integer company_id;
	protected Integer prospectus_id;
	protected Integer prospectus_id_viewed;
	protected Integer proyect_loan_id;	
	protected Integer proyect_id;
	
	protected Integer times_refill_init;
	protected Integer consolidate;	
	
	protected final int SCREEN_EDITAR_INCOMES_EXPENSES = 72;
	protected final int CALCULO_LIQUIDEZ = 5;
	protected final int CREDITOS = 7;
	
	protected boolean changeActions = true;
	protected boolean business_ENABLED;
	protected boolean employment_ENABLED;
	protected boolean hasIncomeDeatil;
	protected boolean displayPnlOtherIncome;
	protected boolean dispListPorcWait;
	protected boolean dispListPorc;	
	
	public final void setService_access(AccessService service) 
	{
		service_access = service;
	}
	
	public void setIngresosService(IncomeService service) 
	{
		ingresosService = service;
	}
	
	public void setIncomehistoryService(IncomeHistoryService service) 
	{
		incomehistoryService = service;
	}
	
	public final void setService_income_type(IncomeTypeService service) 
	{
		service_income_type = service;
	}
	
	public void setService_proyect_loan(ProyectLoanService service) 
	{
		service_proyect_loan = service;
	}
	
	public void setBusinessService(BusinessService service) 
	{
		businessService = service;
	}
	
	public void setEmploymentService(EmploymentService service) 
	{
		employmentService = service;
	}
	
	public void setIncomedetailhistoryService(IncomeDetailHistoryService service) 
	{
		incomedetailhistoryService = service;
	}
	
	public void setIncomeDetailService(IncomeDetailService service) 
	{
		incomeDetailService = service;
	}
	
	public void setService_change_control(Change_controlService service) 
	{
		service_change_control = service;
	}
	
	public void setSystemparamservice(SystemParamService service) 
	{
		systemparamservice = service;
	}
	
	public void setEgresosService(ExpensesService service) 
	{
		egresosService = service;
	}
			
	public void setService_expenses_type(ExpensesTypeService service) 
	{
		service_expenses_type = service;
	}

	public void setService_expenses_history(ExpensesHistoryService service) 
	{
		service_expenses_history = service;
	}

	public ChangeBean getChangeConsolidate() 
	{
		return changeConsolidate;
	}
	
	public List<IncomeBean> getListIncomeBean() 
	{
		return listIncomeBean;
	}
	
	public List<IncomeDetailsBean> getListBusinessDetails() 
	{
		return listBusinessDetails;
	}
	
	public List<ListPorc> getListPorcClientBean() 
	{
		return listPorcClientBean;
	}
	
	public IndicadoresIMO getIndicador() 
	{
		return indicador;
	}

	public final AccessIMO getAuditor()
	{
		return auditor;
	}					

	public Integer getConsolidate() 
	{
		return consolidate;
	}

	public void setConsolidate(Integer consolidate) 
	{
		this.consolidate = consolidate;
	}

	public IncomeCalculatorIMO getCalculator() 
	{
		return calculator;
	}	

	public LoanNegotiationIMO getModifier() 
	{
		return modifier;
	}

	public ExpensesBean getAmmountConsolidate() 
	{
		return ammountConsolidate;
	}

	public LoanNegotiation getNegotiation() 
	{
		return negotiation;
	}

	public List<ExpensesBean> getListExpensesBean() 
	{
		return listExpensesBean;
	}

	public final String getPage_title()
	{
		return page_title;
	}
	
	public String getPorcActionUser() 
	{
		return porcActionUser;
	}
	
	public String getDescription_other_income() 
	{
		return description_other_income;
	}	

	public Double getTotalExpenses() 
	{
		return totalExpenses;
	}
	
	public Double getTotalExpensesControl() 
	{
		return totalExpensesControl;
	}
	
	public Double getTotalIncome() 
	{
		return totalIncome;
	}
			
	public Double getTotalIncomeControl() 
	{
		return totalIncomeControl;
	}

	public Double getExcedenteControl() 
	{
		return excedenteControl;
	}	

	public Double getLiquidezCliControl() 
	{
		return liquidezCliControl;
	}	

	public boolean isDisplayPnlOtherIncome() 
	{
		return displayPnlOtherIncome;
	}
	
	public boolean isChangeActions() 
	{
		return changeActions;
	}
	
	public boolean isHasIncomeDeatil() 
	{
		return hasIncomeDeatil;
	}
	
	public boolean isDispListPorcWait() 
	{
		return dispListPorcWait;
	}

	public boolean isDispListPorc() 
	{
		return dispListPorc;
	}			
}
