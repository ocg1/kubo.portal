package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.LogBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.mesa.solicitud.busqueda.ClientViewFullName;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.mesa.solicitud.busqueda.ClientViewFullNameService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class Logs implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{prospectusServiceImp}")
	private ProspectusService prospectusService;
	
	@ManagedProperty("#{clientViewFullNameServiceImp}")
	private ClientViewFullNameService clientViewFullNameService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	private String search;
	private String value;
	private int radioTypeSearch = 1;


	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}
	public ProspectusService getProspectusService() {
		return prospectusService;
	}
	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}

	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}
	public String getSearch() {
		return search;
	}
	public String getValue() {
		return value;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getRadioTypeSearch() {
		return radioTypeSearch;
	}
	public void setRadioTypeSearch(int radioTypeSearch) {
		this.radioTypeSearch = radioTypeSearch;
	}
	public ClientViewFullNameService getClientViewFullNameService() {
		return clientViewFullNameService;
	}
	public void setClientViewFullNameService(
			ClientViewFullNameService clientViewFullNameService) {
		this.clientViewFullNameService = clientViewFullNameService;
	}
	public void search_Request(ActionEvent e){	
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion = (SessionBean) FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, "sessionBean");
		

		ProspectusPK prospectusPK = new ProspectusPK();
		prospectusPK.setProspectus_id(Integer.parseInt(getSearch()));
		prospectusPK.setCompany_id(sesion.getCompany_id());	
		
		
		Prospectus prospectus = prospectusService.getProspectusById(prospectusPK);
		
		//Prospectus prospectus=prospectusService.getProspectByTrackingID(getSearch());
		
		
		if(prospectus!=null){
		ProyectLoan proyectLoan=proyectLoanService.getMaxProyectLoanByProspect(prospectus.getProspectusPK().getProspectus_id(), prospectus.getProspectusPK().getCompany_id());
			if(proyectLoan!=null){
				value=proyectLoan.getProyectloanPk().getProyect_loan_id()+"::"+proyectLoan.getProyectloanPk().getProyect_id()+"::"+proyectLoan.getProyectloanPk().getProspectus_id()+"::"+proyectLoan.getProyectloanPk().getCompany_id(); 															
				
				
				if(getValue()!=null){
					SearchSummaySession searchsum= (SearchSummaySession) FacesContext.getCurrentInstance()
			                .getApplication().getELResolver()
			                .getValue(elContext, null, "searchSummaySession");
				 searchsum.setSearchSummary(getValue());
				 searchsum.setTypeLog("EVA");
				 LogBean logbean = (LogBean) FacesContext.getCurrentInstance()
			                .getApplication().getELResolver()
			                .getValue(elContext, null, "logBean");
						if(logbean!=null){
							logbean.init();
						}
						
				searchsum.setPerson(false);
					
				 requestContext.addCallbackParam("isValid", true);
				 
				 MenuControlTableBean menu = (MenuControlTableBean) FacesContext.getCurrentInstance()
			                .getApplication().getELResolver()
			                .getValue(elContext, null, "menuControlTableBean");
					
					
					MembershipPK mpk = new  MembershipPK();
					
					mpk.setCompany_id ( Integer.parseInt( value.split("::")[3] ) );
					mpk.setProspectus_id ( Integer.parseInt( value.split("::")[2] ) );
					
					Membership member = membershipService.getMembershipById(mpk);
					
					if( member.getIs_canceled() != null && member.getIs_canceled().trim().length()>0 && !member.getIs_canceled().equals("N") ){
						
						menu.setProspectus_is_canceled( true );
						menu.setCanceledReason(member.getIs_canceled());
						
					}else{
						
						menu.setProspectus_is_canceled( false );
						menu.setCanceledReason("");
						
					}
				 
				}
				else{
					requestContext.addCallbackParam("isValid", false);
				}
			}
			else{
				 requestContext.addCallbackParam("isValid", false);
			}
		}
		else{
			 requestContext.addCallbackParam("isValid", false);
		}
	}
	
	public List<ClientViewFullName> completeinfoclient(String strSearch){
		List<ClientViewFullName> suggestions = new ArrayList<ClientViewFullName>();
		//Dependiendo del valor del checkBox sera la busqueda.
		//1:Por nombre
		if(getRadioTypeSearch() == 1){
			suggestions=clientViewFullNameService.getListClientByName(strSearch);
		}else //3:Por email
			if(getRadioTypeSearch() == 3){
			suggestions=clientViewFullNameService.getListClientByEmail(strSearch);
		}
		
		return suggestions;
		
	}
	public MembershipService getMembershipService() {
		return membershipService;
	}
	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}
	
}
