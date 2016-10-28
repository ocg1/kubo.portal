package mx.com.kubo.portal.expenses;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.ExpensesType;
import mx.com.kubo.model.ListPorc;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.services.ExpensesService;
import mx.com.kubo.tools.Utilities;

public abstract class PercentageCalculatorDMO 
implements PercentageCalculatorIMO 
{
	protected ExpensesService egresosService;		
			
	protected List <ListPorc>     listPorcClientBean;	
	protected List <Expenses> 	  listExpenses;
	protected List <ExpensesType> listExpensesType;
	
	protected Locale locale = new Locale("es","mx");
	
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);
	
	protected String user;
	protected String porcActionUser;
	
	protected Double totalExpenses = 0.0;
	protected Double totalIncome;
	protected Double totalIncomeControl;
	
	protected ProyectLoan proyect_loan;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_loan_id;	
	
	protected boolean dispListPorcWait;
	protected boolean dispListPorc;
	
	protected PercentageCalculatorDMO()
	{
		egresosService = Utilities.findBean("expensesServiceImp");
		
		dispListPorcWait = true;
	}		
	
	public void setListExpenses(List<Expenses> listExpenses) 
	{
		this.listExpenses = listExpenses;
	}
	
	public void setListExpensesType(List<ExpensesType> listExpensesType) 
	{
		this.listExpensesType = listExpensesType;
	}
	
	public void setUser(String user) 
	{
		this.user = user;
	}

	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		   company_id = proyect_loan.getProyectloanPk().getCompany_id();
		   
		   proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();		   
	}	

	public void setTotalIncome(Double totalIncome) 
	{
		this.totalIncome = totalIncome;
	}

	public void setTotalIncomeControl(Double totalIncomeControl) 
	{
		this.totalIncomeControl = totalIncomeControl;
	}

	public List<ListPorc> getListPorcClientBean() 
	{
		return listPorcClientBean;
	}
	
	public String getPorcActionUser() 
	{
		return porcActionUser;
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
