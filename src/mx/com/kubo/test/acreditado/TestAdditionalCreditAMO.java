package mx.com.kubo.test.acreditado;

import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;

public abstract class TestAdditionalCreditAMO extends TestAdditionalCreditDMO
{
	protected void init_sesion() 
	{
		faces = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
		sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		sesion.setCompany_id(1);
		sesion.setProspectus_id(12347);
		
		proyect_loan = service_proyect_loan.getMaxProyectLoanByProspect(12347, 1);
	}
}
