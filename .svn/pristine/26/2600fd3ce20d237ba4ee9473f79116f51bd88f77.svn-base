package mx.com.kubo.managedbeans.mesa.solicitud;

import java.util.Date;

import javax.faces.context.FacesContext;

import mx.com.kubo.bean.ExpensesBean;
import mx.com.kubo.mesa.solicitud.perfil.IndicePagoDeudasIMP;

public abstract class EgresosPMO extends SummaryRequestPMO
{
	public final void changeExpenses(ExpensesBean ex)
	{		
		ex.getExpense().setAmmount_modified(Double.parseDouble(ex.getAmmount_modified().replace(",", "")));
		ex.getExpense().setProspectus_id_modified(sesion.getProspectus_id());
		ex.getExpense().setDatetime_modified(new Date());
		
		if(ex.getExpense_type_id() == 7)
		{	
			if(ex.getExpense().getAmmount_modified()==0 && ex.getExpense().getAmmount()==0)
			{
				ex.getExpense().setAmmount_minus(null);
				
				getAmmountConsolidate().setAmmount(0.0);
				getAmmountConsolidate().setAmmount_modified("0");
				getAmmountConsolidate().setExcedentConsolidate(getExcedenteControl()+getAmmountConsolidate().getAmmount());
				
				consolidate = null;
			} else {				
				
				if(ex.getExpense().getAmmount_minus() != null && ex.getExpense().getAmmount_minus() > 0)
				{					
					getAmmountConsolidate().setAmmount(ex.getExpense().getAmmount_minus());
					
				} else {
					
					consolidate = 2;
					
					getAmmountConsolidate().setAmmount(0.0);
				}
			}
		}
		
		if(ex.getExpense().getExpensesPk().getExpense_id() != 0)
		{
			egresosService.updateExpenses(ex.getExpense());
			
		} else {
			
			egresosService.addExpenses(ex.getExpense(), ex.getExpense().getExpensesPk().getProspectus_id(), ex.getExpense().getExpensesPk().getCompany_id());
		}
		
		calculaTotalExpenses();
		
		faces     = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		        
		/*		        
		indice_pago_deudas = (IndicePagoDeudasIMP) resolver.getValue(context, null, "indice_pago_deudas");
*/
		indice = new IndicePagoDeudasIMP();
		indice.init();
	}
}
