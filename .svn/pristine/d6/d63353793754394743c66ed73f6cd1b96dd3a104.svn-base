package mx.com.kubo.portal.efectivo;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.Operating_costPK;
import mx.com.kubo.model.Operating_cost_type;
import mx.com.kubo.model.Operating_cost_typePK;
import mx.com.kubo.model.OperationCostHistory;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SellingDetailHistory;
import mx.com.kubo.model.SellingDetailPK;
import mx.com.kubo.model.SellingType;
import mx.com.kubo.model.SellingTypePK;
import mx.com.kubo.services.IncomeDetailService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.OperationCostHistoryService;
import mx.com.kubo.services.OperationCostTypeService;
import mx.com.kubo.services.SellingDetailHistoryService;
import mx.com.kubo.services.SellingTypeService;
import mx.com.kubo.tools.Utilities;

public abstract class IncomeDetailDMO 
implements IncomeDetailIMO
{
	protected SellingDetailHistoryService service_selling_detail_history;
	protected          SellingTypeService service_selling_type;
	protected         IncomeDetailService service_income_detail;
	protected               IncomeService service_income;
	protected    OperationCostTypeService service_opertaion_cost_type;
	protected OperationCostHistoryService service_opertaion_cost_history;
	
	protected Income incomeBussinness;	
	protected IncomeDetail incomedetail;
	protected IncomeDetailsBean incomeDetaBean;
	
	protected SellingDetail in;
	protected SellingDetailPK sellHPk;
	protected SellingTypePK id;
	protected SellingType type;
	
	protected Operating_cost op;
	protected Operating_costPK opCostHPk;
	protected Operating_cost_type   op_type;
	protected Operating_cost_typePK op_PK;
	
	protected ProyectLoan   proyect_loan;
	
	protected List <IncomeBean>   listIncomeBean;
	protected List <IncomeDetailsBean> 	 listBusinessDetails;	
	protected List<SellingDetailHistory> lstSellHis;
	protected List<SellingDetail> lstSellingDetail;
	protected List<OperationCostHistory> lstInHis;
	protected List<Operating_cost> listOperationCost;
	protected List<Operating_cost_type> lstOperCostType;
	
	protected Locale locale = new Locale("es","mx");
	
	protected NumberFormat num = NumberFormat.getNumberInstance(locale);
	
	protected Double provider_total_init;
	protected Double totalIncome=0.0;
	
	protected Integer times_refill_init;
	
	protected int prospectus_id;
	protected int company_id;
	protected int proyect_loan_id;
	
	protected IncomeDetailDMO()
	{
		service_selling_detail_history = Utilities.findBean("sellingDetailHistoryServiceImp");
		service_selling_type           = Utilities.findBean("sellingTypeServiceImp");
		service_income_detail          = Utilities.findBean("incomeDetailServiceImp");
		service_income                 = Utilities.findBean("incomeServiceImp");
		service_opertaion_cost_type    = Utilities.findBean("operationCostTypeServiceImp");
		service_opertaion_cost_history = Utilities.findBean("operationCostHistoryServiceImp"); 
	}
	
	public final void setIncomedetail(IncomeDetail incomedetail) 
	{
		this.incomedetail = incomedetail;
	}

	public void setProyect_loan(ProyectLoan proyect_loan) 
	{
		this.proyect_loan = proyect_loan;
		
		prospectus_id = proyect_loan.getProyectloanPk().getProspectus_id();
		   company_id = proyect_loan.getProyectloanPk().getCompany_id();
		   
		   proyect_loan_id = proyect_loan.getProyectloanPk().getProyect_loan_id();
	}

	public List<IncomeDetailsBean> getListBusinessDetails() 
	{
		return listBusinessDetails;
	}
}
