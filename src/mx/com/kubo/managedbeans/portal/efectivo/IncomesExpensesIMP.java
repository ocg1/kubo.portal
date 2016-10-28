package mx.com.kubo.managedbeans.portal.efectivo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Expenses;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.Operating_cost;
import mx.com.kubo.model.SellingDetail;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.portal.efectivo.LoanNegotiationIMP;
import mx.com.kubo.portal.expenses.PercentageCalculatorIMP;

@ManagedBean (name = "incomesExpenses") @ViewScoped
public final class IncomesExpensesIMP extends IncomesExpensesAMO
implements Serializable
{
	private static final long serialVersionUID = 6102334364968453051L;
	
	@PostConstruct
	public final void init()
	{
		faces = FacesContext.getCurrentInstance();
		
		external  = faces.getExternalContext();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		init_sesion();		
									   
		checkOtherIncome = ingresosService.getIncomeByTypeIncomeID(prospectus_id_viewed,  company_id, 4);
		
		if(checkOtherIncome != null && checkOtherIncome.getAmmount() > 0)
		{
			description_other_income = checkOtherIncome.getDescription() != null ? checkOtherIncome.getDescription() : "";
			
		} else {
			
			description_other_income = "";
		}
		
		listIncomeType = service_income_type.getIncomeTypeListOrderByConsec();		
		
		listEmployment = employmentService.getListEmployByProspect(prospectus_id_viewed, company_id);		
		listBusiness   = businessService.getListBusinessByProspect(prospectus_id_viewed, company_id);
		
		employment_ENABLED = listEmployment != null && listEmployment.size() != 0 ? true : false;
		  business_ENABLED = listBusiness   != null && listBusiness.size()   != 0 ? true : false;	
		  
		ammountConsolidate = new ExpensesBean();
		ammountConsolidate.setAmmount(0.0);
		ammountConsolidate.setExcedentConsolidate(0.0);
		
		init_income_calculator();
		
		incomedetail = null;
		
		if( !existlistIncomeDetailHistory() )
		{		
			incomedetail = incomeDetailService.loadMaxIncomeByProspectus(prospectus_id_viewed, company_id);							
		} 
		
		if(incomedetail != null)
		{		
			hasIncomeDeatil = true;
			
			loadIncomeBussinessDetails();
			
		} else {
			
			hasIncomeDeatil = false;			
		}
		
		changeConsolidate = new ChangeBean();
		
		listChangConsolTemp = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id,new String[]{"gn_expense"},new String[]{"ammount_minus"});
		
		changeConsolidate.setHasChange(listChangConsolTemp!=null && listChangConsolTemp.size()>0?true:false);
		changeConsolidate.setLstChanges(listChangConsolTemp!=null && listChangConsolTemp.size()>0?listChangConsolTemp:null);			
		
		if(changeConsolidate != null && changeConsolidate.isHasChange())
		{
			lastChange = changeConsolidate.getLstChanges().get(changeConsolidate.getLstChanges().size() - 1);
			
			ammountConsolidate.setWhyChangeData(lastChange.getComments());
		}		
		
		
		system_param_PK = new SystemParamPK();		
		system_param_PK.setCompany_id(company_id);
		system_param_PK.setSystem_param_id(CALCULO_LIQUIDEZ);
		
		system_param = systemparamservice.loadSelectedSystemParam(system_param_PK);
				
		listExpensesType = service_expenses_type.getExpensesTypeList();
		
		init_expense_calculator();									
		
		modifier = new LoanNegotiationIMP();
		modifier.setFaces(faces);
		modifier.setSesion(sesion);
		modifier.setProyect_loan(proyect_loan);
		modifier.init();		
		
		negotiation = modifier.getNegotiation();
				
		init_indicadores();
	}
	
	public void init_calculator() 
	{
		request = RequestContext.getCurrentInstance();			
		
		request.addCallbackParam("calculator_OK", true);
	}
	
	public void init_modifier_indicadores()
	{
		request = RequestContext.getCurrentInstance();
		
		proyect_loan = service_proyect_loan.getProyectLoanByProyectLoanID(proyect_loan_id, prospectus_id_viewed, company_id);
		
		negotiation = modifier.getNegotiation();
		
		init_indicadores();
		
		request.addCallbackParam("indicadores_OK", true);
	} 
	
	public void init_expenses_calculator(AjaxBehaviorEvent event) 
	{
		request = RequestContext.getCurrentInstance();			
		
		input_text = (HtmlInputText) event.getComponent();
		
		expense_id = input_text.getValue().toString();
		
		request.addCallbackParam("expense_id", expense_id);
	}
	
	
	public void changeExpenses(ExpensesBean bean)
	{	
		request = RequestContext.getCurrentInstance();
		
		Expenses expense = bean.getExpense();
		
		int expense_id = expense.getExpensesPk().getExpense_id();
		
		Double ammount_modified = Double.parseDouble(bean.getAmmount_modified().replace(",", ""));
		
		expense.setAmmount_modified(ammount_modified);
		expense.setProspectus_id_modified(prospectus_id);
		expense.setDatetime_modified(new Date());
		
		if(bean.getExpense_type_id() == CREDITOS)
		{	
			if(expense.getAmmount_modified() == 0 && expense.getAmmount() == 0)
			{
				expense.setAmmount_minus(null);
				
				ammountConsolidate.setAmmount(0.0);
				ammountConsolidate.setAmmount_modified("0");
				ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
				
				consolidate = null;
				
			} else {				
				
				if(expense.getAmmount_minus() != null && expense.getAmmount_minus() > 0)
				{					
					ammountConsolidate.setAmmount(expense.getAmmount_minus());
					
				} else {
					
					consolidate = 2;
					
					ammountConsolidate.setAmmount(0.0);
				}
			}
		}
		
		boolean update_OK = false;
		
		if(expense.getExpensesPk().getExpense_id() != 0)
		{
			egresosService.updateExpenses(expense);
			
			update_OK = true;
			
		} else {
			
			egresosService.addExpenses(expense, prospectus_id_viewed, company_id);
			
			update_OK = true;
		}
		
		init_expense_calculator();
		init_indicadores();		
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("ammount_modified", ammount_modified);
		request.addCallbackParam("expense_id", expense_id );
	}
	
	
	public void calculaPorcCliente(AjaxBehaviorEvent event)
	{
		input_text = (HtmlInputText) event.getComponent();
		
		String user = input_text.getValue().toString();
		
		request = RequestContext.getCurrentInstance();
		
		percentage = new PercentageCalculatorIMP();
		percentage.setProyect_loan(proyect_loan);
		percentage.setListExpenses(listExpenses);
		percentage.setListExpensesType(listExpensesType);
		percentage.setTotalIncome(totalIncome);
		percentage.setTotalIncomeControl(totalIncomeControl);	
		percentage.setUser(user);
		percentage.init();
		
		listPorcClientBean = percentage.getListPorcClientBean();
		dispListPorcWait   = percentage.isDispListPorcWait();
		dispListPorc       = percentage.isDispListPorc();
		porcActionUser     = percentage.getPorcActionUser();
		
		request.addCallbackParam("dispListPorc", dispListPorc);
		request.addCallbackParam("dispListPorcWait", dispListPorcWait);
	}
		
	public void changeIncome(IncomeBean bean)
	{
		request = RequestContext.getCurrentInstance();
		
		Income income = bean.getIncome();
		
		Double ammount_modified = Double.parseDouble(bean.getAmmount_modified().replace(",", ""));
		
		Integer     income_id = income.getIncomePk().getIncome_id();
		Integer prospectus_id = income.getIncomePk().getProspectus_id();
		Integer    company_id = income.getIncomePk().getCompany_id();
		
		income.setAmmount_modified(ammount_modified);
		income.setProspectus_id_modified(prospectus_id);
		income.setDatetime_modified(new Date());
		
		boolean update_OK = false;
		
		if(income_id != 0)
		{
			update_OK = ingresosService.updateIncome(income);
			
		} else {
			
			update_OK = ingresosService.addIncome(income, prospectus_id, company_id);
		}						
		
		calculaSueldoNeto();	
		
		init_income_calculator();
		init_indicadores();
		
		request.addCallbackParam("update_OK", update_OK);
		request.addCallbackParam("ammount_modified", ammount_modified);
	}
	
	
	public void showOtherIncome()
	{	
		this.displayPnlOtherIncome = !this.displayPnlOtherIncome;
	}
	
	
	public void saveChangeIncomDetails(IncomeDetailsBean incomeDetailBean)
	{
		Double newAmmount= 0D;
		
		if(incomeDetailBean.getAmmount_modified()!= null &&incomeDetailBean.getAmmount_modified().trim().replace(",", "").length()>0 )
		{
			newAmmount = Double.parseDouble(incomeDetailBean.getAmmount_modified().replace(",", ""));
		}
		
		if(incomeDetailBean.getSellingOrOperCostDetail() instanceof SellingDetail)
		{					
			SellingDetail updateSellDetail = (SellingDetail) incomeDetailBean.getSellingOrOperCostDetail();
			updateSellDetail.setAmmount_modified(newAmmount);
			
			if(incomeDetailBean.isSave()?incomeDetailService.updateSelligOrOperatingDetail(updateSellDetail):incomeDetailService.saveSelligOrOperatingDetail(updateSellDetail))
			{				 
				Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
				updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
				
				updateIncome.setDatetime_modified(new Date());
				updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
				
				if(ingresosService.updateIncome(updateIncome))
				{	
/*					
					calculaTotalIncome();
					calculaTotalExpenses();
*/
					
					init_income_calculator();
					
				}
			}				
				
		} else if(incomeDetailBean.getSellingOrOperCostDetail() instanceof Operating_cost){
			
				System.out.println( "INGRESOSPMO instanceof Operating_cost " );
			
				Operating_cost updateOperaCostDetail=(Operating_cost) incomeDetailBean.getSellingOrOperCostDetail();
				updateOperaCostDetail.setAmmount_modified(newAmmount);
				
				if(incomeDetailBean.isSave()?incomeDetailService.updateSelligOrOperatingDetail(updateOperaCostDetail):incomeDetailService.saveSelligOrOperatingDetail(updateOperaCostDetail))
				{
					Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
					updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
					updateIncome.setDatetime_modified(new Date());
					updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
					if(ingresosService.updateIncome(updateIncome))
					{	
						loadIncomeBussinessDetails();
/*						
						calculaTotalIncome();
						calculaTotalExpenses();
*/
						
						init_income_calculator();
						
					}
				}					
				
		}else if(incomeDetailBean.getSellingOrOperCostDetail() instanceof IncomeDetail){
			
			System.out.println( "INGRESOSPMO instanceof IncomeDetail " );
			
			IncomeDetail i  = (IncomeDetail)incomeDetailBean.getSellingOrOperCostDetail();
			
			if( incomeDetailBean.getName().equals("Veces que se surte al mes")  )
			{											
				saveChangeData("gn_income_detail", "times_refill_modified", times_refill_init + "" , "" + i.getTimes_refill_modified() , "Cambio de Mesa de Control en entrevista" );
				
				i.setTimes_refill_modified ( Integer.parseInt ( incomeDetailBean.getAmmount_modified()  ) );
				
				times_refill_init = i.getTimes_refill_modified();
				
				i.setTimes_refill_datetime_modified(new Date());
				i.setTimes_refill_prospectus_id_modified( sesion.getProspectus_id() ); 
				
			}else if ( incomeDetailBean.getName().equals( "Total de compras mensuales" ) ){
				
				String ammountMod =  (incomeDetailBean.getAmmount_modified() != null) ? incomeDetailBean.getAmmount_modified().replaceAll(",", "" ) : "0"  ;
				
				i.setProvider_total_modified(Double.parseDouble( ammountMod  ) );
				
				saveChangeData("gn_income_detail", "provider_total_modified", provider_total_init + "" , ""+ i.getProvider_total_modified() , "Cambio de Mesa de Control en entrevista" );
				
				provider_total_init = i.getProvider_total_modified();
				
				i.setProvider_total_datetime_modified( new Date());
				i.setProvider_total_prospectus_id_modified( sesion.getProspectus_id() );
			}			
			
			Income updateIncome=ingresosService.getIncomebyID(new IncomePK(incomedetail.getIncomDetailPk().getIncome_id(), incomedetail.getIncomDetailPk().getProspectus_id(), incomedetail.getIncomDetailPk().getCompany_id()));
			updateIncome.setAmmount_modified(loadIncomeBussinessDetails());
			updateIncome.setDatetime_modified(new Date());
			updateIncome.setProspectus_id_modified( sesion.getProspectus_id() );
			
			if(ingresosService.updateIncome(updateIncome))
			{
			
				loadIncomeBussinessDetails();
			
/*			
			calculaTotalIncome();
			calculaTotalExpenses();
*/
			
			init_income_calculator();
			
			}
			
			incomeDetailService.updateSelligOrOperatingDetail( i );
			
		}
		
		init_indicadores();		
	}
	
	
	public void updateAmmountMinus()
	{
		request = RequestContext.getCurrentInstance();
		
		if(ammountConsolidate!=null && ammountConsolidate.getExpense()!=null && ammountConsolidate.getAmmount_modified()!=null && consolidate==1 && ammountConsolidate.getWhyChangeData()!=null )
		{
			Double prevMinAmmount = ammountConsolidate.getExpense().getAmmount_minus();
			Double ammount = Double.parseDouble(ammountConsolidate.getAmmount_modified().replace(",", ""));
			
			ammountConsolidate.setAmmount(ammount);
			ammountConsolidate.getExpense().setAmmount_minus(ammountConsolidate.getAmmount());
			
			if(ammountConsolidate.getAmmount()>0)
			{
				ammountConsolidate.setExcedentConsolidate(excedenteControl+ammountConsolidate.getAmmount());
				
				if(egresosService.updateExpenses(ammountConsolidate.getExpense()))
				{				
					liquidezCliControl = ammountConsolidate.getExcedentConsolidate() / pagoMenControl;					
					liquidezCliControl = (double)Math.round((liquidezCliControl)*100)/100;
					
					if(saveChangeData("gn_expense", "ammount_minus", prevMinAmmount==null?"0":""+prevMinAmmount, ""+ammountConsolidate.getAmmount(),ammountConsolidate.getWhyChangeData()))
					{						
						List<Change_control> listChangConsolTemp= service_change_control.getListByProspectByAfectedTablesFields(prospectus_id_viewed, company_id, new String[]{"gn_expense"},new String[]{"ammount_minus"});
						
						changeConsolidate.setHasChange(true);
						changeConsolidate.setLstChanges(listChangConsolTemp!=null && listChangConsolTemp.size()>0?listChangConsolTemp:null);
					}				
				}
			}
			
			request.addCallbackParam("ammount", ammount);
		}
	}
	
	
	public void setNullAmmuntMinus(AjaxBehaviorEvent event)
	{
		select_radio = (HtmlSelectOneRadio) event.getComponent();
		
		consolidate = Integer.parseInt(select_radio.getValue().toString());
		
		request = RequestContext.getCurrentInstance();
		
		if(consolidate == 2 && ammountConsolidate.getAmmount() > 0)
		{
			ammountConsolidate.getExpense().setAmmount_minus(null);
			ammountConsolidate.setAmmount_modified("0");
			ammountConsolidate.setAmmount(0.0);
			ammountConsolidate.setExcedentConsolidate(excedenteControl + ammountConsolidate.getAmmount());
			
			boolean update_OK = egresosService.updateExpenses(ammountConsolidate.getExpense());
			
			if(update_OK)
			{
				liquidezCliControl = excedenteControl / pagoMenControl;			
				liquidezCliControl = (double) Math.round((liquidezCliControl) * 100) / 100;
				
				ammountConsolidate.setWhyChangeData(null);
			}				
		}
		
		request.addCallbackParam("consolidate", consolidate);
		request.addCallbackParam("liquidezCliControl", liquidezCliControl != null ? liquidezCliControl : 0);
	}
}
