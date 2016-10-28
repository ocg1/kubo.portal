package mx.com.kubo.portal.efectivo;

import java.util.ArrayList;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeHistory;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.IncomeTypePK;

public abstract class IncomeCalculatorAMO extends IncomeCalculatorDMO 
{	
	protected void init_ammount_modified(Income income, String name) 
	{
		income_bean = new IncomeBean(income_type_id, ammount, name);
		
		if(ammount_modified != null)
		{
			tmp = num.format(ammount_modified);
			
			if(tmp.equals("0"))
			{
				tmp = "0";
			}						
			
		} else {
			
			tmp = num.format(ammount);
			
			if(tmp.equals("0"))
			{
				tmp = "0";
			}						
		}
		
		income_bean.setIncome(income);
		income_bean.setAmmount_modified(tmp);
		
		listIncomeBean.add(income_bean);
											
		if(obs_income_type.equals("IE"))
		{			
			if(ammount_modified != null)
			{
				totalIncomeControl +=  (operation_sign * ammount_modified);
				
			} else {
				
				totalIncomeControl +=  (operation_sign * ammount);
			}
			
			totalIncome += (operation_sign * ammount);
		}
		
		isEquals = true;
	}
	
	protected void init_monto_indicadores() 
	{
		if(income_type_id == NEGOCIO_EMPRESA)
		{
			monto_negocio = ammount;
		}
		
		if(income_type_id == SUELDO_NETO)
		{
			monto_sueldo = ammount;														
		}	
		
		if(income_type_id == NEGOCIO_EMPRESA)
		{
			monto_negocio_control = ammount_modified != null ? ammount_modified : ammount;
		}
		
		if(income_type_id == SUELDO_NETO)
		{
			monto_sueldo_control = ammount_modified != null ? ammount_modified : ammount;
		}	
	}

	protected void init_income_bean(int income_type_id, String name)
	{
		income_bean = new IncomeBean(income_type_id,0D,name);														
		income_bean.setAmmount_modified("0");
		
		ex = new Income();
		ex.setAmmount(0D);
		ex.setAmmount_modified(0D);
		ex.setIncome_type_id(income_type_id);
		ex.setProspectus_id_modified(null);
		
		expk = new IncomePK();
		expk.setIncome_id(0);
		expk.setCompany_id(company_id);						
		expk.setProspectus_id(prospectus_id);
		
		ex.setIncomePk(expk);
		
		income_bean.setIncome(ex);
			
		listIncomeBean.add(income_bean);
	}
	
	protected boolean existlistIncomeHistory()
	{	
		if(proyect_loan == null)
		{
			return false;
		}
		
		lstInHis = service_income_history.getIncomeHistoryByProyectLoan(proyect_loan_id);
		
		if(lstInHis != null && lstInHis.size() > 0)
		{			
			listIncome = new ArrayList<Income>();
			
			for( IncomeHistory inH : lstInHis )
			{			
				income = new Income();				
				income.setAmmount          (inH.getAmmount());
				income.setAmmount_modified (inH.getAmmount_modified());
				income.setDatetime_modified(inH.getDatetime_modified());
				income.setDescription      (inH.getDescription());
				income.setIncome_type_id   (inH.getIncome_type_id());
				
				incomePk = new IncomePK();				
				incomePk.setCompany_id   (inH.getPk().getCompany_id());
				incomePk.setIncome_id    (inH.getPk().getIncome_id());
				incomePk.setProspectus_id(inH.getPk().getProspectus_id());
				
				income.setIncomePk(incomePk);
				
				income_type_PK = new IncomeTypePK();				
				income_type_PK.setCompany_id    (inH.getPk().getCompany_id());
				income_type_PK.setIncome_type_id(inH.getIncome_type_id());
				
				income_type = service_income_type.getIncomeTypeById(income_type_PK);
				
				income.setIncomeType( income_type );
				income.setProspectus_id_modified(inH.getProspectus_id_modified());
				
				listIncome.add(income);				
			}
			
			return true;
			
		} else {
			
			return false;			
		}		
	}
}
