package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.bean.FunctionBean;
import mx.com.kubo.model.Function;
import mx.com.kubo.model.Role;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.services.FunctionService;
import mx.com.kubo.services.RoleFunctionService;
import mx.com.kubo.services.RoleService;


@ManagedBean
@ViewScoped
public class PermissionRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{functionServiceImp}")
	private FunctionService functionservice;
	
	@ManagedProperty("#{roleServiceImp}")
	private RoleService roleservice;
	
	@ManagedProperty("#{roleFunctionServiceImp}")
	private RoleFunctionService rolefunctionservice;
	
	private List<Function> lstfunction;
	private List<Role> lstRole;
	
	private List<String> thisfunction;  
	private Integer thisRole;
	
	@PostConstruct
	public void init() {
		
		lstfunction = functionservice.getLstFunction();
		lstRole = roleservice.getLstRoles();
		
	}
	
	public void saveFunctionRole(){
		System.out.println("into saveFunctionRole");
		
		List<FunctionBean> thisfunctionbean = new ArrayList<FunctionBean>(); 
		
		String description ="";
		
		for( Role r : lstRole){
			if(r.getRolepk().getRole_id() == thisRole){
				description = "El "+r.getName()+" puede ";
				break;
			}
		}
		
		for(String i : thisfunction ){
			
			if(i != null){
				
				for(Function f : lstfunction){
					if(f.getPk().getFunction_id() == Integer.parseInt(i)){
					FunctionBean fb = new FunctionBean();
					fb.setFunction_id(Integer.parseInt(i));
					fb.setDescription(description+f.getName());
					thisfunctionbean.add(fb);
					break;
					}
				}
				
			}
			
			
		}
		
		rolefunctionservice.deleteAndSaveRoleFunctions(thisfunctionbean, 1, thisRole);
		System.out.println("exit from saveFunctionRole");
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		RoleFunctionController rfc = (RoleFunctionController) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "roleFunctionController");
		
		rfc.init();
		
	}
	
	public void selectRole(){
		
		System.out.println("into selectRole");
		
		List<RoleFunction> roleFunction =  rolefunctionservice.loadRoleFunctionList();
		
//		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
//		viewMap.clear();
		
		thisfunction = new ArrayList<String>()	;
		
		for(RoleFunction rf : roleFunction){
			if(rf != null ){
				if(rf.getPk().getRole_id() == thisRole){
					thisfunction.add(""+rf.getPk().getFunction_id());
				}
			}
		}
		
	}
	
	public FunctionService getFunctionservice() {
		return functionservice;
	}

	public void setFunctionservice(FunctionService functionservice) {
		this.functionservice = functionservice;
	}

	public List<Function> getLstfunction() {
		return lstfunction;
	}

	public void setLstfunction(List<Function> lstfunction) {
		this.lstfunction = lstfunction;
	}

	public List<Role> getLstRole() {
		return lstRole;
	}

	public void setLstRole(List<Role> lstRole) {
		this.lstRole = lstRole;
	}

	public RoleService getRoleservice() {
		return roleservice;
	}

	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}

	public Integer getThisRole() {
		return thisRole;
	}

	public void setThisRole(Integer thisRole) {
		this.thisRole = thisRole;
	}

	public List<String> getThisfunction() {
		return thisfunction;
	}

	public void setThisfunction(List<String> thisfunction) {
		this.thisfunction = thisfunction;
	}

	public RoleFunctionService getRolefunctionservice() {
		return rolefunctionservice;
	}

	public void setRolefunctionservice(RoleFunctionService rolefunctionservice) {
		this.rolefunctionservice = rolefunctionservice;
	}

}
