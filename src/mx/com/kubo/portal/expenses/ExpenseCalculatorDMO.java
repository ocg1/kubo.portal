package mx.com.kubo.portal.expenses;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.portal.expenses.TotalExpensesIMO;
import mx.com.kubo.services.ExpensesHistoryService;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.services.ExpensesTypeService;
import mx.com.kubo.tools.Utilities;

public abstract class ExpenseCalculatorDMO 
implements ExpenseCalculatorIMO
{	
	protected        ExpensesService service_expenses;
	protected    ExpensesTypeService service_expenses_type;
	protected ExpensesHistoryService service_expenses_history;
	
	protected ExpensesBean expensesBean;
	protected ExpensesBean ammountConsolidate;
	
	protected TotalExpensesIMO total_calculator; 
	
	protected ProyectLoan proyect_loan;
	
	protected SystemParam system_param;
	
	protected List <Expenses> 	  listExpenses;
	protected List <ExpensesBean> listExpensesBean;
	protected List <ExpensesType> listExpensesType;
	
	protected Locale locale = new Locale("es","mx");
	
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);
	
	protected Integer consolidate;
	protected Integer expense_type_id;
	
	protected Double ammount;
	protected Double ammount_modified;
	protected Double totalExpenses=0.0;	
	protected Double totalExpensesControl=0D;
	protected Double totalExpenseTemp;
	protected Double excedenteControl=0D;
	protected Double monto_deudas_cliente=0D;	
	protected Double monto_deudas_control=0D;
	protected Double monto_tanda=0D;
	protected Double monto_creditos=0D;
	protected Double monto_tanda_control=0D;
	protected Double monto_creditos_control=0D;
			
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_loan_id;	
	
	protected final int TANDA    = 6;
	protected final int CREDITOS = 7;
	protected final int GASTOS_TOTALES = 14;
		

	protected boolean flagExpenses;	
	protected boolean flagTotalExpenses;
	protected boolean type_EQUALS;
	protected boolean expense_bean_NEW_ENABLED;
	
	protected ExpenseCalculatorDMO()
	{
		service_expenses         = Utilities.findBean("expensesServiceImp");
		service_expenses_type    = Utilities.findBean("expensesTypeServiceImp");
		service_expenses_history = Utilities.findBean("expensesHistoryServiceImp");
		
		totalExpenseTemp = 0D;		
		totalExpenses = 0.0;
		
		listExpensesBean = new ArrayList<ExpensesBean>();	
	}
	
	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		   company_id = proyect_loan.getProyectloanPk().getCompany_id();
		   
		   proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();		   
	}	
	
	public void setAmmountConsolidate(ExpensesBean ammountConsolidate) 
	{
		this.ammountConsolidate = ammountConsolidate;
	}
	
	public void setSystem_param(SystemParam system_param) 
	{
		this.system_param = system_param;
	}		

	public void setListExpensesType(List<ExpensesType> listExpensesType) 
	{
		this.listExpensesType = listExpensesType;
	}
	
	public ExpensesBean getAmmountConsolidate() 
	{
		return ammountConsolidate;
	}	
	
	public List<ExpensesBean> getListExpensesBean() 
	{
		return listExpensesBean;
	}	
	
	public List<Expenses> getListExpenses() 
	{
		return listExpenses;
	}

	public Integer getConsolidate() 
	{
		return consolidate;
	}		

	public Double getMonto_deudas_cliente() 
	{
		return monto_deudas_cliente;
	}

	public Double getMonto_deudas_control() 
	{
		return monto_deudas_control;
	}

	public Double getTotalExpensesControl() 
	{
		return totalExpensesControl;
	}
		
	public Double getTotalExpenses() 
	{
		return totalExpenses;
	}

	public void setExcedenteControl(Double excedenteControl) 
	{
		this.excedenteControl = excedenteControl;
	}
}
