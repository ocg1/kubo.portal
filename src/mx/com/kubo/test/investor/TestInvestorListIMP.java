package mx.com.kubo.test.investor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.managedbeans.mesa.InvestorList;

@ManagedBean(name = "test_investor_list") @ViewScoped
public class TestInvestorListIMP extends TestInvestorListDMO
implements Serializable
{
	private static final long serialVersionUID = 6436003102469542687L;
	
	@PostConstruct
	public final void init()
	{
		faces     = FacesContext.getCurrentInstance();
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		
/*		
		investor_list = (InvestorList) resolver.getValue(elContext, null, "investorList");
*/		
	}
	
	public final void activar(ActionEvent e)
	{
		attributes = e.getComponent().getAttributes();
		
		String prospectus = (String) attributes.get("prospectusid").toString();	
		String company    = (String) attributes.get("companyid").toString();
		
		prospectus_id = Integer.parseInt(prospectus);
		company_id    = Integer.parseInt(company);
		
/*		
		activacion_OK = investor_list.creaClienteByProspect(prospectus_id, company_id, true);
*/		
		
		System.out.println("TestInvestorListIMP.activar(): " + activacion_OK);
	}
	
}
