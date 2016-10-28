package mx.com.kubo.portal.efectivo;

import java.util.List;

import mx.com.kubo.bean.IncomeBean;
import mx.com.kubo.model.IncomeType;
import mx.com.kubo.model.ProyectLoan;

public interface IncomeCalculatorIMO 
{	
	void setListIncomeType(List<IncomeType> listIncomeType);
	
	void setProyect_loan(ProyectLoan proyect_loan);
	
	void setBusiness_ENABLED  (boolean business_ENABLED);
	void setEmployment_ENABLED(boolean employment_ENABLED);
	
	void init();
	
	Double getTotalIncome();
	Double getTotalIncomeControl();
	Double getIngreso_neto_control();
	Double getIngreso_neto_cliente();
	
	List<IncomeBean> getListIncomeBean();

}
