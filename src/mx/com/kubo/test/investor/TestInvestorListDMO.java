package mx.com.kubo.test.investor;

import java.util.Map;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.mesa.InvestorListIMO;

import org.primefaces.context.RequestContext;

public abstract class TestInvestorListDMO 
{
	protected InvestorListIMO investor_list;
	
	protected RequestContext request;
	protected FacesContext faces;
	protected ELContext elContext;
	protected ELResolver resolver;	
	
	protected Map<String, Object> attributes;
	
	protected Integer prospectus_id;
	protected Integer company_id;
	
	protected boolean activacion_OK;
}
