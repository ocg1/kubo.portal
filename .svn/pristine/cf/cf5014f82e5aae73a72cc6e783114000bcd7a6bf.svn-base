package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.model.Role;
import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.model.RoleAccessPK;
import mx.com.kubo.model.Screen;
import mx.com.kubo.services.RoleAccessService;
import mx.com.kubo.services.RoleService;
import mx.com.kubo.services.ScreenService;

@ManagedBean
@ViewScoped
public class ScreenRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{screenServiceImp}")
	private ScreenService screenservice;
	
	@ManagedProperty("#{roleServiceImp}")
	private RoleService roleservice;
	
	@ManagedProperty("#{roleAccessServiceImp}")
	private RoleAccessService roleaccessservice;
	
	private List<Screen> lstscreen;
	private List<Role> lstRole;
	private Integer thisRole;
	
	private List<String> thisscreen;  
	private List<String> screenselected;
	
	private SessionBean sesion;
	
	@PostConstruct
	public void init() {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication()
			        .getELResolver().getValue(elContext, null, "sessionBean");
		
		ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		int company_id = sesion.getCompany_id();
		String area = sesion.getArea().toString();
		List<Screen> lstscreentemp = screenservice.getLstScreenByArea(company_id,area);
//		int i = 0;
		
		lstscreen = new ArrayList<Screen>();
		
		for( Screen screen : lstscreentemp ){
			
			try{
				
				if(screen.getResource_name() != null){
					
					System.out.println(screen.getScreenPK().getScreen_id()+" - "+screen.getResource_name());
					screen.setName( recurso.getString(screen.getResource_name()) );
					lstscreen.add(screen);
					
				}
				
			}catch(Exception e){
				
				System.out.println( screen.getScreenPK().getScreen_id() + " - " + screen.getName() );
				
			}
			
		}
		
		lstRole = roleservice.getLstRoles();
		
	}
	
	public void selectRole(){
		
		List<RoleAccess> tem =  roleaccessservice.getAccessListByRole(thisRole, sesion.getCompany_id());
		if(screenselected != null)
			screenselected.clear();
		screenselected = null;
		screenselected = new ArrayList<String>();
		for( RoleAccess role : tem ){
			
			screenselected.add(role.getPk().getScreen_id()+"");
			
		}
		
		
	}
	
	public void saveScreenRole(){
		
		List<RoleAccess> roleaccess = new ArrayList<RoleAccess>(); 
		
		String description ="";
		
		for( Role r : lstRole){
			if(r.getRolepk().getRole_id() == thisRole){
				description = "El "+r.getName()+" puede ver la pantalla ";
				break;
			}
		}
		for(String f : screenselected){
			
			for(Screen s : lstscreen){
				
				if(Integer.parseInt(f) == s.getScreenPK().getScreen_id()){
				
					RoleAccess roleaccessitem = new RoleAccess();
					RoleAccessPK pk = new RoleAccessPK();
					
					pk.setCompany_id(s.getScreenPK().getCompany_id());
					pk.setRole_id(thisRole);
					pk.setScreen_id(s.getScreenPK().getScreen_id());
					
					roleaccessitem.setPk( pk );
					
					roleaccessitem.setDescription( description + s.getName() );
					
					roleaccess.add(roleaccessitem);
				}
			
			}
			
		}
		
		
		roleaccessservice.deleteAndSaveRoleAccess(roleaccess, 1, thisRole);
		System.out.println("exit from saveFunctionRole");
	}
	

	public ScreenService getScreenservice() {
		return screenservice;
	}


	public void setScreenservice(ScreenService screenservice) {
		this.screenservice = screenservice;
	}


	public RoleService getRoleservice() {
		return roleservice;
	}


	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}


	public List<Screen> getLstscreen() {
		return lstscreen;
	}


	public void setLstscreen(List<Screen> lstscreen) {
		this.lstscreen = lstscreen;
	}


	public List<Role> getLstRole() {
		return lstRole;
	}


	public void setLstRole(List<Role> lstRole) {
		this.lstRole = lstRole;
	}


	public List<String> getThisscreen() {
		return thisscreen;
	}


	public void setThisscreen(List<String> thisscreen) {
		this.thisscreen = thisscreen;
	}


	public Integer getThisRole() {
		return thisRole;
	}


	public void setThisRole(Integer thisRole) {
		this.thisRole = thisRole;
	}

	public List<String> getScreenselected() {
		return screenselected;
	}

	public void setScreenselected(List<String> screenselected) {
		this.screenselected = screenselected;
	}

	public RoleAccessService getRoleaccessservice() {
		return roleaccessservice;
	}

	public void setRoleaccessservice(RoleAccessService roleaccessservice) {
		this.roleaccessservice = roleaccessservice;
	}


	
	
}
