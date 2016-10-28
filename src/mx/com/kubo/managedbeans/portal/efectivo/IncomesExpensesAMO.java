package mx.com.kubo.managedbeans.portal.efectivo;

import java.util.List;

import mx.com.kubo.model.Change_control;
import mx.com.kubo.model.Change_controlPK;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.IncomeDetailHistory;
import mx.com.kubo.model.IncomeDetailPK;
import mx.com.kubo.portal.AccessIMP;
import mx.com.kubo.portal.expenses.ExpenseCalculatorIMP;
import mx.com.kubo.portal.efectivo.IncomeCalculatorIMP;
import mx.com.kubo.portal.efectivo.IncomeDetailIMP;
import mx.com.kubo.portal.efectivo.IndicadoresIMP;
import mx.com.kubo.portal.reader.ParameterReaderIMP;

public abstract class IncomesExpensesAMO extends IncomesExpensesDMO 
{
	protected void init_sesion() 
	{
		reader = new ParameterReaderIMP();
		reader.setFaces(faces);
		reader.init_sesion();
				  
		     sesion = reader.getSesion();
		 page_title = reader.getPage_title();
		access_from = reader.getAccess_from();
				
		proyect_loan_id = reader.getProyect_loan_id();
		
		if(sesion != null)
		{			   			
			auditor = new AccessIMP();
			auditor.setService_access(service_access);
			auditor.setSesion(sesion);
			auditor.setScreen_id(SCREEN_EDITAR_INCOMES_EXPENSES);
			auditor.setAccess_from(access_from);
		}		
		
		if(sesion != null)
		{
	                    company_id = sesion.getCompany_id();
					 prospectus_id = sesion.getCoachProspectus_id();
	          prospectus_id_viewed = sesion.getProspectus_id();	          																
	          
	        proyect_loan = service_proyect_loan.getProyectLoanByProyectLoanID(proyect_loan_id, prospectus_id_viewed, company_id);
	        
	        proyect_id = proyect_loan.getProyectloanPk().getProyect_id();
		}	
	}
	
	protected void init_income_calculator() 
	{
		calculator = new IncomeCalculatorIMP();
		calculator.setListIncomeType(listIncomeType);
		calculator.setProyect_loan(proyect_loan);
		calculator.setBusiness_ENABLED(business_ENABLED);
		calculator.setEmployment_ENABLED(employment_ENABLED);
		calculator.init();
		
		listIncomeBean       = calculator.getListIncomeBean();		
		totalIncome          = calculator.getTotalIncome();
		totalIncomeControl   = calculator.getTotalIncomeControl();		
		ingreso_neto_cliente = calculator.getIngreso_neto_cliente();
		ingreso_neto_control = calculator.getIngreso_neto_control();
	}
	
	protected double loadIncomeBussinessDetails() 
	{
		detail = new IncomeDetailIMP();
		detail.setProyect_loan(proyect_loan);
		detail.setIncomedetail(incomedetail);
		
		double income_bussiness_details = detail.init();
		
		listBusinessDetails = detail.getListBusinessDetails();
		
		return income_bussiness_details;
	}
	
	protected void init_expense_calculator() 
	{
		expenses = new ExpenseCalculatorIMP();
		expenses.setSystem_param(system_param);		
		expenses.setExcedenteControl(excedenteControl);
		expenses.setAmmountConsolidate(ammountConsolidate);
		expenses.setProyect_loan(proyect_loan);
		expenses.setListExpensesType(listExpensesType);
		expenses.init();
				
		listExpenses         = expenses.getListExpenses();
		listExpensesBean     = expenses.getListExpensesBean();
		totalExpenses        = expenses.getTotalExpenses();
		totalExpensesControl = expenses.getTotalExpensesControl();		
		consolidate          = expenses.getConsolidate();
		ammountConsolidate   = expenses.getAmmountConsolidate();				
		monto_deudas_cliente = expenses.getMonto_deudas_cliente();
		monto_deudas_control = expenses.getMonto_deudas_control();
	}
	
	protected void init_indicadores() 
	{		
		indicador = new IndicadoresIMP(); 
		indicador.setProyect_loan(proyect_loan);
		indicador.setAmmountConsolidate(ammountConsolidate);
		indicador.setNegotiation(negotiation);
		indicador.setTotalIncome         (totalIncome);
		indicador.setTotalExpenses       (totalExpenses);
		indicador.setTotalIncomeControl  (totalIncomeControl);
		indicador.setTotalExpensesControl(totalExpensesControl);
		indicador.setIngreso_neto_cliente(ingreso_neto_cliente);
		indicador.setIngreso_neto_control(ingreso_neto_control);
		indicador.setMonto_deudas_cliente(monto_deudas_cliente);
		indicador.setMonto_deudas_control(monto_deudas_control);
		indicador.init();
		
		excedenteControl   = indicador.getExcedenteControl();
		ammountConsolidate = indicador.getAmmountConsolidate();
		pagoMenControl     = indicador.getPagoMenControl();		
	}		
	
	protected void calculaSueldoNeto()
	{
		Double sueldoNeto = 0D;
		
		listIncomeTemp = ingresosService.getListIncomeByProspect  (prospectus_id_viewed, company_id);
			
		if(listIncomeTemp != null)
		{		
			incomeUpdate = null;
			
			for(Income incomeTemp: listIncomeTemp)
			{				
				String obs_income_type = incomeTemp.getIncomeType().getObs_income_type();
				Integer operation_sign = incomeTemp.getIncomeType().getOperation_sign();
				Double ammount_modified = incomeTemp.getAmmount_modified();
				Double ammount = incomeTemp.getAmmount();
				
				if(obs_income_type.equals("TSE"))
				{					
					if(ammount_modified != null )
					{
						sueldoNeto += (operation_sign * ammount_modified);
						
						System.out.println("sueldoNeto("+ sueldoNeto+") += operation_sign "+ operation_sign + " ammount_modified ("+ammount_modified+")");
						
					} else {
						
						sueldoNeto += (operation_sign * ammount);
						
						System.out.println("sueldoNeto("+ sueldoNeto+") += operation_sign "+ operation_sign + " ammount_modified ("+ammount+")");
					}
				}
				
				if(incomeTemp.getIncome_type_id() == 6)
				{
					incomeUpdate = incomeTemp;					
				}											
			}
			
			if(incomeUpdate != null)
			{
				incomeUpdate.setAmmount_modified(sueldoNeto);
				
				boolean update_OK = ingresosService.updateIncome(incomeUpdate);
				
				System.out.println("update_OK = " + update_OK);
			}			
		}
	}
	
	protected boolean existlistIncomeDetailHistory()
	{		
		if(proyect_loan == null)
		{
			return false;
		}
	
		List<IncomeDetailHistory> lstInHis = incomedetailhistoryService.getIncomeDetailHistoryByProyectLoan(proyect_loan_id);
		
		if(lstInHis != null && lstInHis.size() > 0)
		{			
			for( IncomeDetailHistory inH : lstInHis )
			{
				
				incomedetail = new IncomeDetail();
				
				IncomeDetailPK incomDetailPk = new IncomeDetailPK();
				
				incomDetailPk.setCompany_id(inH.getPk().getCompany_id());
				incomDetailPk.setIncome_detail_id(inH.getPk().getIncome_detail_id());
				incomDetailPk.setIncome_id(inH.getPk().getIncome_id());
				incomDetailPk.setProspectus_id(inH.getPk().getProspectus_id());
				
				incomedetail.setIncomDetailPk(incomDetailPk);				
				incomedetail.setOperative_costs(inH.getOperative_costs());
				incomedetail.setProfil_after_costs(inH.getProfil_after_costs());
				incomedetail.setProfit_before_costs(inH.getProfit_before_costs());
				incomedetail.setProvider_amount(inH.getProvider_amount());
				incomedetail.setProvider_total(inH.getProvider_total());
				incomedetail.setProvider_type(inH.getProvider_type());
				incomedetail.setSales_freq(inH.getSales_freq());
				incomedetail.setSales_total(inH.getSales_total());
				incomedetail.setSales_type(inH.getSales_type());
				incomedetail.setTimes_refill(inH.getTimes_refill());								
			}
			
			return true;
			
		} else {
			
			return false;		
		}	
	}
	
	protected boolean saveChangeData(String table, String field, String origValue, String newValue, String comment)
	{		
		Change_controlPK changeCtrlPK = new Change_controlPK();
		
		changeCtrlPK.setProspectus_id(prospectus_id);
		changeCtrlPK.setCompany_id(company_id);
		
		Change_control changeCtrl = new Change_control();
		
		changeCtrl.setChange_controlPK(changeCtrlPK);
		changeCtrl.setChange_prospectus_id(prospectus_id_viewed);
		changeCtrl.setAfected_table(table);
		changeCtrl.setIp(sesion.getIP_address_client());			
		changeCtrl.setOriginal_value(origValue);
		changeCtrl.setNew_value(newValue);
		changeCtrl.setField(field);
		changeCtrl.setComments(comment);		
		
		if(service_change_control.addChangeControl(changeCtrl, prospectus_id, company_id))
		{
			return true;	
			
		} else {
			
			return false;
		}
	}	
}
