package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import mx.com.kubo.model.Membership;
import mx.com.kubo.services.LoginServiceIMO;
import mx.com.kubo.services.MembershipService;

@ManagedBean
@ViewScoped
public class ActivationController implements Serializable
{

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{loginServiceImp}")
	private LoginServiceIMO loginService;
	
	@ManagedProperty("#{membershipServiceImp}")
	protected MembershipService membershipService;
	
	private String activeStr;
	private String pageActiv;
	private String displayActivedPage = "none";
	private String displayFalsePage = "none";
	private String displayMessagePage = "none";
	private String displayActivePage = "none";
	

	public String getActiveStr() {
		return activeStr;
	}

	public void setActiveStr(String activeStr) {
		this.activeStr = activeStr;
	}
	
	@PostConstruct
	public void init(){		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String active = (String) facesContext.getExternalContext().getRequestParameterMap().get("counter");
		
		setActiveStr(active);
		
		if(active==null){
//			setPageActiv("messagePage.xhtml");
			setDisplayMessagePage("block");
		}
		else{
			
			activeStr = active;
			
				String res = loginService.validateActivationNumber(active);
				if(res.split("::")[0].equals("FALSA")){
//					setPageActiv("messageFalsePage.xhtml");
					setDisplayFalsePage("block");
				}
				else if(res.split("::")[0].equals("ACTIVADA")){
//					setPageActiv("messageActivedPage.xhtml");
					setDisplayActivedPage("block");
					ELContext elContext = facesContext.getELContext();
					SessionBean sesion = (SessionBean) facesContext
							.getApplication().getELResolver()
							.getValue(elContext, null, "sessionBean");
					
					sesion.setTemporalUser(res.split("::")[1]);
					sesion.setHoldMail(true);
				}
				else if(res.split("::")[0].equals("ACCESO") ){
//					setPageActiv("ActivePage.xhtml");
					setDisplayActivePage("block");
				}
		}
		
	}
	
	public void verificarCorreo(){
		
		RequestContext request = RequestContext.getCurrentInstance();	
		
		try{
		
			Membership member =  loginService.getMembershipByActivationNumber( activeStr );
			
			member.setEmail_verified("S");
			member.setIs_active(1);
			
			membershipService.update( member );
			
			request.addCallbackParam("isVerified", true);
			
		
		}catch(Exception e){
			
			e.printStackTrace();
			
			request.addCallbackParam("isVerified", false);
			
		}
	}

	public String getPageActiv() {
		return pageActiv;
	}

	public void setPageActiv(String pageActiv) {
		this.pageActiv = pageActiv;
	}

	public LoginServiceIMO getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceIMO loginService) {
		this.loginService = loginService;
	}

	public String getDisplayActivedPage() {
		return displayActivedPage;
	}

	public void setDisplayActivedPage(String displayActivedPage) {
		this.displayActivedPage = displayActivedPage;
	}

	public String getDisplayFalsePage() {
		return displayFalsePage;
	}

	public void setDisplayFalsePage(String displayFalsePage) {
		this.displayFalsePage = displayFalsePage;
	}

	public String getDisplayMessagePage() {
		return displayMessagePage;
	}

	public void setDisplayMessagePage(String displayMessagePage) {
		this.displayMessagePage = displayMessagePage;
	}

	public String getDisplayActivePage() {
		return displayActivePage;
	}

	public void setDisplayActivePage(String displayActivePage) {
		this.displayActivePage = displayActivePage;
	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}
	
}
