package mx.com.kubo.portal.efectivo;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.PersonOtherFamily;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeHistory;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.IncomeTypePK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.IncomeHistoryService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.IncomeTypeService;
import mx.com.kubo.tools.Utilities;

public abstract class IncomeCalculatorDMO 
implements IncomeCalculatorIMO
{
	protected    IncomeTypeService service_income_type;
	protected IncomeHistoryService service_income_history;
	protected        IncomeService service_income;
		
	protected IncomeBean income_bean;
	protected Income income;
	protected Income incomeBussinness;
	protected Income   ex;
	protected IncomePK expk;
	protected IncomePK     incomePk;
	protected IncomeType   income_type;
	protected IncomeTypePK income_type_PK;
	
	protected ProyectLoan proyect_loan;
	
	protected PersonOtherFamily personFamily;
	
	protected Locale locale = new Locale("es","mx");
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);				
	
	protected List <Income> 	   listIncome;
	protected List <IncomeType>    listIncomeType;
	protected List <IncomeBean>    listIncomeBean;	
	protected List <IncomeHistory> lstInHis;
	
	protected String tmp;
	protected String obs_income_type;
	
	protected Double totalIncome=0.0;
	protected Double totalIncomeControl=0D;	

	protected Double ammount;
	protected Double ammount_modified;
	protected Double monto_negocio = 0D;
	protected Double monto_negocio_control = 0D;
	protected Double monto_sueldo = 0D;
	protected Double monto_sueldo_control =0D;
	protected Double ingreso_neto_cliente;
	protected Double ingreso_neto_control;
	
	protected Integer income_type_id;
	protected Integer operation_sign;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_loan_id;
	
	protected final int NEGOCIO_EMPRESA = 2;
	protected final int OTHER_FAMILY_INCOMES = 3;
	protected final int SUELDO_NETO = 6;
		
	protected boolean business_ENABLED;
	protected boolean employment_ENABLED;
	protected boolean isEquals;
	
	protected IncomeCalculatorDMO()
	{
		service_income         = Utilities.findBean("incomeServiceImp");
		service_income_type    = Utilities.findBean("incomeTypeServiceImp");
		service_income_history = Utilities.findBean("incomeHistoryServiceImp");		
	}
	
	public void setListIncomeType(List<IncomeType> listIncomeType) 
	{
		this.listIncomeType = listIncomeType;
	}

	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		   company_id = proyect_loan.getProyectloanPk().getCompany_id();
		   
		   proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
	}
	
	public void setBusiness_ENABLED(boolean business_ENABLED) 
	{
		this.business_ENABLED = business_ENABLED;
	}

	public void setEmployment_ENABLED(boolean employment_ENABLED) 
	{
		this.employment_ENABLED = employment_ENABLED;
	}

	public Double getTotalIncome() 
	{
		return totalIncome;
	}
	
	public Double getTotalIncomeControl() 
	{
		return totalIncomeControl;
	}	
	
	public Double getIngreso_neto_cliente() 
	{
		return ingreso_neto_cliente;
	}

	public Double getIngreso_neto_control() 
	{
		return ingreso_neto_control;
	}

	public List<IncomeBean> getListIncomeBean() 
	{
		return listIncomeBean;
	}
	
	public List<Income> getListIncome() 
	{
		return listIncome;
	}
}
