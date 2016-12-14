package mx.com.kubo.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.Access;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.ScreenService;

@ManagedBean
@ViewScoped
public class WelcomeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	@ManagedProperty("#{screenServiceImp}")
	protected ScreenService screenservice;
	
	private String inicio;
	
	protected FacesContext       faces;
	protected ELContext          elContext;	
	protected ELResolver         resolver;
	protected ExternalContext    external;
	
	protected SessionBean sesion  ;

	
	
	@PostConstruct
	public void init(){
		try{
			
			
			System.out.println( "\n\n\n" );
			System.out.println( "++++++++ WelcomeBean.init() ++++++ " );
			System.out.println( "\n\n\n" );
			
			Thread.sleep(1000); 
			
			faces     = FacesContext.getCurrentInstance();
			
			elContext = faces.getELContext();
			resolver  = faces.getApplication().getELResolver();
			external  = faces.getExternalContext();
							
			sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			
			System.out.println( "\n\n\n" );
			
			System.out.println( sesion.getProspectus_id() + " " + sesion.getEmail() + " " + sesion.getArea() );
			
			System.out.println( "\n\n\n" );
			
			Access access = new Access();
			
			if( sesion.getArea().toString().equals("I") ){
				access.setScreen_id(39);
			}else{
				access.setScreen_id(38);
			}
			
			access.setPercentage(0);
			access.setWeb_browser(sesion.getNamebrawser());
			access.setWeb_browser_version(sesion.getVersionbrawser());
			access.setOp_system(sesion.getOsbrawser());
			access.setHorizontal_size(sesion.getBrowser_width());
			access.setVertical_size(sesion.getBrowser_height());
			access.setIpaddress(sesion.getIP_address_client());
			access.setUser_agent(sesion.getUser_agent());
			access.setDevice_info(sesion.getDevice_info());
			access.setVersion_description (sesion.getVersion_description());
			access.setProspectus_id_coach (sesion.getCoachProspectus_id());
			access.setAccess_from		  (sesion.getAccess_from());
			access.setUrl_access( sesion.getUrl_access() );
			boolean isValid = true; 
			
			if( sesion.getCompany_id() != null )
			{
				access.setCompany_id(sesion.getCompany_id());
				
			}else{
			
				isValid = false;
				
			}
			
			if( sesion.getProspectus_id() != null )
			{
				access.setProspectus_id( sesion.getProspectus_id() );
			
			}else{
				
				isValid = false;
			}
			
			if(isValid)
			{
				accessService.add(access, true);
			
			}else{
				
				System.out.println("....... -----  .......");
				System.out.println("....... NO GUARDO ACCESO1  .......");
				
			}
		
		}catch(Exception e){
			System.out.println("....... Error WelcomBean  .......");
			e.printStackTrace();
			
		}
			
	}
	
	public String iniciaRegistro(){
		
		System.out.println(" *** iniciaRegistro WelcomBean ");
		
//		faces     = FacesContext.getCurrentInstance();
//		
//		elContext = faces.getELContext();
//		resolver  = faces.getApplication().getELResolver();
//		external  = faces.getExternalContext();
//		
//		SessionBean sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
		
		Access access = new Access();
		
		if( sesion.getArea().toString().equals("I") ){
			access.setScreen_id(9);
			
		}else{
			access.setScreen_id(2);
		}
		
		ScreenPK spk = new ScreenPK();
		
		spk.setCompany_id(1);
		spk.setScreen_id(access.getScreen_id());
		
		Screen screen = screenservice.getScreenById(spk);
		
		sesion.setLastPage(screen.getName());
		access.setUrl_access( sesion.getUrl_access() );
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent      (sesion.getUser_agent());
		access.setDevice_info    (sesion.getDevice_info());
		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		
		boolean isValid = true; 
		
		if( sesion.getCompany_id() != null )
		{
			access.setCompany_id(sesion.getCompany_id());
			
		}else{
		
			isValid = false;
			
		}
		
		if( sesion.getProspectus_id() != null )
		{
			access.setProspectus_id( sesion.getProspectus_id() );
		
		}else{
			
			isValid = false;
		}
		
		if(isValid)
		{
			accessService.add(access, true);
		
		}else{
			
			System.out.println("....... -----  .......");
			System.out.println("....... NO GUARDO ACCESO  .......");
			
		}
		
		return "registrar";
		
	}
	
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public FacesContext getFaces() {
		return faces;
	}

	public void setFaces(FacesContext faces) {
		this.faces = faces;
	}

	public ELContext getElContext() {
		return elContext;
	}

	public void setElContext(ELContext elContext) {
		this.elContext = elContext;
	}

	public ELResolver getResolver() {
		return resolver;
	}

	public void setResolver(ELResolver resolver) {
		this.resolver = resolver;
	}

	public ExternalContext getExternal() {
		return external;
	}

	public void setExternal(ExternalContext external) {
		this.external = external;
	} 
	
	public ScreenService getScreenservice() {
		return screenservice;
	}

	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}
	
}
