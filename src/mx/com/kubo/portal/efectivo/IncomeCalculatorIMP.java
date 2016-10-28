package mx.com.kubo.portal.efectivo;

import java.util.ArrayList;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomeType;

public final class IncomeCalculatorIMP extends IncomeCalculatorAMO
implements IncomeCalculatorIMO
{
	public final void init() 
	{
		incomeBussinness = null;
		
		totalIncome = 0.0;
		totalIncomeControl = 0.0;
		
		listIncomeBean = new ArrayList<IncomeBean>();
		listIncome 	   = new ArrayList<Income>() ; 
		
		if(!existlistIncomeHistory())
		{						
			listIncome = service_income.getListIncomeByProspect(prospectus_id, company_id);								
		}	
		
		init_lista_income_bean();
		
		ingreso_neto_cliente = monto_negocio         + monto_sueldo;		
		ingreso_neto_control = monto_negocio_control + monto_sueldo_control;
	}		
	
	private void init_lista_income_bean() 
	{									
		for(IncomeType itertype: listIncomeType)
		{
			isEquals = false;
			
			String name     = itertype.getName();
			operation_sign  = itertype.getOperation_sign();
			obs_income_type = itertype.getObs_income_type();
				
			for(Income income: listIncome)
			{
				income_type_id   = income.getIncome_type_id();
				ammount          = income.getAmmount();
				ammount_modified = income.getAmmount_modified();
				
				boolean income_type_EQUALS = income_type_id.equals(itertype.getPk().getIncome_type_id());
				
				if(income_type_EQUALS)
				{
					if(income_type_id == OTHER_FAMILY_INCOMES && personFamily != null)
					{
						boolean person_family_ENABLED = personFamily.getRelationShip() != null && personFamily.getRelationShip().getRelationship() != null && personFamily.getRelationShip().getRelationship().length() > 0;
						
						if(person_family_ENABLED)
						{																		
							name = "Ingreso de " + personFamily.getRelationShip().getRelationship().toLowerCase();																	
						}								
					}
					
					init_ammount_modified(income, name);
					
					if( income_type_id == NEGOCIO_EMPRESA )
					{
						incomeBussinness = income;
					}								
				}
				
				init_monto_indicadores();				
			}			
					
			if(!isEquals)
			{						
				int income_type_id = itertype.getPk().getIncome_type_id();					
				
				if((employment_ENABLED && income_type_id == SUELDO_NETO) || (employment_ENABLED && obs_income_type.equals("TSE")))
				{
					init_income_bean(income_type_id, name);
						
				} else if(business_ENABLED && income_type_id == NEGOCIO_EMPRESA) {
						
					init_income_bean(income_type_id, name);
						
				} else if(income_type_id != 6 && income_type_id != NEGOCIO_EMPRESA && !obs_income_type.equals("TSE")){
				
					init_income_bean(income_type_id, name);
				}
			}
		}
	}
}
