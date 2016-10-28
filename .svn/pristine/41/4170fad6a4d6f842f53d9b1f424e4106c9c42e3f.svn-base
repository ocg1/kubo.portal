package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import mx.com.kubo.bean.ItemLoanList;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.mesa.MenuControlTableBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.services.MembershipService;

@ManagedBean
@ViewScoped
public class CargaOtroProyecto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	@PostConstruct
	public void init(){
		
		
		
	}

	public final void initSearch(ActionEvent evento_AJAX)
	{
		String proyect_loan_SEARCH_TOKEN = (String) evento_AJAX.getComponent().getAttributes().get("proyectAtrr").toString();
		
		RequestContext request   = RequestContext.getCurrentInstance();		
		FacesContext faces     = FacesContext.getCurrentInstance();
		
		ELContext elContext = faces.getELContext();
		ELResolver resolver  = faces.getApplication().getELResolver();
		
		SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		if (sesion.getArea().equals('M')) 
		{		
			Calendar cal1 = Calendar.getInstance();			
			cal1.setTime(new Date());									
			
			MenuControlTableBean control_table = (MenuControlTableBean) resolver.getValue(elContext, null, "menuControlTableBean");
			
			evento_AJAX.getComponent().getAttributes().put("section", "controlTable/searchRequest::12::menu1");
			
			SearchSummaySession summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
						
			summarysesion.setPerson(false);			
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);			
			summarysesion.setTypeLog("EVA");
			
			
			request.addPartialUpdateTarget("form_Prin");
			request.addPartialUpdateTarget("actualPage");
			request.addPartialUpdateTarget("pnlCancel");
			
			MembershipPK membership_PK = new  MembershipPK();
			
			membership_PK.setCompany_id 	( Integer.parseInt( summarysesion.getSearchSummary().split("::")[3] ) );
			membership_PK.setProspectus_id 	( Integer.parseInt( summarysesion.getSearchSummary().split("::")[2] ) );
			
			Membership member = membershipService.getMembershipById(membership_PK);
			
			if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") )
			{
				
				control_table.setProspectus_is_canceled( true );
				control_table.setCanceledReason(member.getIs_canceled());
				
			}else{
				
				control_table.setProspectus_is_canceled( false );
				control_table.setCanceledReason("");
				
			}		
			
			control_table.cambiaPagina(evento_AJAX);
			
			
			
		} else {	
			
			SearchSummaySession summarysesion = (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
			summarysesion.setSearchSummary(proyect_loan_SEARCH_TOKEN);
			
		}
		
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}
	
	
}
