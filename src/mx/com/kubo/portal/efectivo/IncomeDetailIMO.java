package mx.com.kubo.portal.efectivo;

import java.util.List;

import mx.com.kubo.bean.IncomeDetailsBean;
import mx.com.kubo.model.IncomeDetail;
import mx.com.kubo.model.ProyectLoan;

public interface IncomeDetailIMO 
{
	void setIncomedetail(IncomeDetail incomedetail);
	
	void setProyect_loan(ProyectLoan proyect_loan) ;
	
	double init();
	
	 List<IncomeDetailsBean> getListBusinessDetails();
}
