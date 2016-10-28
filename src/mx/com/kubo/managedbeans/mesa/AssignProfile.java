package mx.com.kubo.managedbeans.mesa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.Role;
import mx.com.kubo.model.RoleAccess;
import mx.com.kubo.model.RoleAssignment;
import mx.com.kubo.model.RoleAssignmentPK;
import mx.com.kubo.model.RoleFunction;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.RoleAccessService;
import mx.com.kubo.services.RoleAssignmentService;
import mx.com.kubo.services.RoleFunctionService;
import mx.com.kubo.services.RoleService;


@ManagedBean
@ViewScoped
public class AssignProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{membershipServiceImp}")
	private MembershipService membershipService;
	
	@ManagedProperty("#{roleServiceImp}")
	private RoleService roleservice;
	
	@ManagedProperty("#{prospectusServiceImp}")
	private ProspectusService prospectusService;
	
	@ManagedProperty("#{roleFunctionServiceImp}")
	private RoleFunctionService rolefunctionService;
	
	@ManagedProperty("#{roleAssignmentServiceImp}")
	private RoleAssignmentService roleassignmentservice;
	
	@ManagedProperty("#{roleAccessServiceImp}")
	private RoleAccessService roleaccessservice;
	
	private List<Membership> lstmembers;
	private List<Role> lstroles;
	private Integer thisProspectus;
	private Integer thisRole;
	
	private String msgRes;
	
	private List<RoleFunction> lstFunction;
	
	private List<RoleFunction> lstFuncRolPendding;
	
	private List<RoleFunction> lstFuncRolActual;
	
	private List<RoleAccess> lstScreenRolActual;
	
	private List<RoleAccess> lstScreenRolPendding;
	
	private List<RoleAccess> lstScreen;
	
	private SessionBean sesion;
	
	private Membership membership;
	
	private Date fecIni;
	
	private RoleAssignment rolepending;
	
	private RoleAssignment roleActual;
	
	private ArrayList<String> days = new ArrayList<String>();
	private ArrayList<String> months = new ArrayList<String>();
	private ArrayList<String> years = new ArrayList<String>();

	private String day;
	private String month;
	private String year;
	private String dateStr;
	
	private String monthStr[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre" };
	
	
	@PostConstruct
	public void init(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion = (SessionBean) FacesContext.getCurrentInstance()
				.getApplication().getELResolver()
				.getValue(elContext, null, "sessionBean");
		
		lstmembers = membershipService.getMemberShipForAssignPerfil();
		lstroles = roleservice.getLstRoles();
		
		// Dias
		for (int i = 1; i <= 31; i++) {
			days.add("" + i);
		}
		// Meses
		for (int i = 0; i < monthStr.length; i++) {
			months.add("" + monthStr[i]);
		}
		
		Date d2 = new Date();
		
		SimpleDateFormat frm = new SimpleDateFormat("dd/MM/yyyy");
		Integer year1 = Integer.parseInt(frm.format(d2).split("/")[2]);
		Integer year2 = year1	 +1;
		for (int i = year2; i >=year1; i--) {
			years.add("" + i);
		}
		
	}
	
	
		public void generateDate() {
			
			String dayStr = getDay();
			String thisMonth = getMonth();
			String yearStr = getYear();
			String thisDate = "";
			String sM = "";
			
			
			try{
			
			if (dayStr != null && dayStr.length() > 0 && !dayStr.equals("0")) {
				thisDate += dayStr + "/";
			} else{
				setDay("0");
				return;
			}

			if (dayStr.equals("31")) {
				if(thisMonth != null){
					if (   	thisMonth.equals("Febrero") 
							|| thisMonth.equals("Abril")
							|| thisMonth.equals("Junio")
							|| thisMonth.equals("Septiembre")
							|| thisMonth.equals("Noviembre")) {
						setDay("0");
						return;
					}
				}
			}
			if (thisMonth == null) {
				setMonth("0");
				return;
			}
			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) > 29) {
				setDay("0");
				return;
			}

			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) == 29
					&& (Integer.parseInt(yearStr) % 4 != 0)) {
				setDay("0");
				return;
			}

			if (thisMonth != null && thisMonth.length() > 0
					&& !thisMonth.equals("0")) {
				for (int i = 0; i < getMonthStr().length; i++) {
					if ((getMonthStr()[i]).equals(thisMonth)) {
						if ((i + 1) < 10)
							sM = "0" + (i + 1);
						else
							sM = (i + 1) + "";
					}
				}
				thisDate += sM + "/";
			} else{
				setMonth("0");
				return;
			}

			if (yearStr != null && yearStr.length() > 0 && !yearStr.equals("0")) {
				thisDate += yearStr;
			} else{
				setYear("0");
				return;
			}

			SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
			
			
				
				fecIni = fm.parse(thisDate);
				
			}catch(Exception e){
				
				e.printStackTrace();
				
				fecIni = null;
				
			}
			
				
			

		}
	
	public void saveProspectusRole(){
		
//		ProspectusPK pk = new ProspectusPK();
//		pk.setCompany_id(1);
//		pk.setProspectus_id(thisProspectus);
		
		if(thisRole==null ||  thisRole==100){
			thisRole = null;
		}
		
		SimpleDateFormat fr = new SimpleDateFormat ("dd/MM/yyyy");
		
		
		
		
		Date now = new Date();
		try{
		
			if(fecIni!=null){
					Date d = (fr.parse( fr.format( now ) ));
					if(d.getTime()<=fecIni.getTime()){
						
						try{
							
							RoleAssignment pendiente = roleassignmentservice.getPendingRoleAssignmentByProspectus(membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id());
							
							if( pendiente == null ){
								
								RoleAssignmentPK rolepk = new RoleAssignmentPK() ;
								
								rolepk.setCompany_id(membership.getMembershipPK().getCompany_id());
								rolepk.setProspectus_id(membership.getMembershipPK().getProspectus_id());
								rolepk.setRole_id(thisRole);
								
								RoleAssignment role = new RoleAssignment() ;
								
								role.setPk(rolepk);
								role.setCreated_by(sesion.getProspectus_id());
								role.setDate_application(fecIni);
								role.setDate_created(new Date());
								role.setStatus_id("P");
								
								if(roleassignmentservice.saveRoleAssignment(role)){
									
									selectPerson();
									msgRes="¡El Rol fue asignado satisfactoriamente!";
									
								}else{
									
									msgRes="Ocurrio un error al asignar el rol";
									
								}
							}
							
						}catch(Exception e){
							
							e.printStackTrace();
							msgRes="Ocurrio un error al asignar el rol: "+e.getMessage();
							
						}
						
					}else{
						
						msgRes=" La fecha debe ser mayor o igual al día de hoy.";
						
					}
				}else{
					
					msgRes=" La fecha no fue seleccionada.";
					
				}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public void mergeProspectusRole(){
		
		
		
		if( roleassignmentservice.removeRoleAssignment(rolepending) ){
			
			rolepending.getPk().setRole_id(thisRole);
			rolepending.setDate_application(fecIni);
			rolepending.setDate_created(new Date());
			
			if(roleassignmentservice.saveRoleAssignment(rolepending)){
				
				rolepending = roleassignmentservice.getPendingRoleAssignmentByProspectus(rolepending.getPk().getProspectus_id(), rolepending.getPk().getCompany_id());
				msgRes = "Rol Modificado Satisfactoriamente";
				
			}else{
				
				msgRes = "Error al modificar la asignación de rol";
				
			}
		}
		
	}
	
	public void deleteProspectusRole(){
		
		if(roleassignmentservice.removeRoleAssignment(rolepending)){
			rolepending = null;
			msgRes = "Rol eliminado Satisfactoriamente";
		}else{
			msgRes = "Error al eliminar la asignación de rol";
		}
		
	}
	
	public void selectPerson(){
		
		if(thisProspectus != null && thisProspectus != 0){
			
			try{
				
				membership  = getMembershipByProspectusSel();
				
				if( membership.getPerson().getProspectus().getRole_id() == null ){
					thisRole = 100;
				}else{
					thisRole = membership.getPerson().getProspectus().getRole_id();
				}
				
				setFuctionsByRoleSel();
				//setScreenByRoleSel();
				
				roleActual = roleassignmentservice.getActualRoleAssignmentByProspectus(membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id()) ;
				if(roleActual != null ){
					
					setFuctionsByRole(roleActual.getPk().getRole_id(),0);
					setScreensByRole(roleActual.getPk().getRole_id(),0);
					
				}else{
					lstFuncRolActual = null;
					lstScreenRolActual = null;
				}
				
				rolepending = roleassignmentservice.getPendingRoleAssignmentByProspectus(membership.getMembershipPK().getProspectus_id(), membership.getMembershipPK().getCompany_id()) ;
				
				if(rolepending != null){
					
					setFuctionsByRole(rolepending.getPk().getRole_id(),1);
					thisRole = rolepending.getPk().getRole_id();
					setScreensByRole(thisRole,1);
					
				}else{
					
					lstFuncRolPendding = null;
					lstScreenRolPendding = null;
				}
				
			}catch(Exception e){
				
				e.printStackTrace();
				thisRole = 0;
				
			}
		}else{
			thisRole = 0;
			membership = null;
		}
	}
	
//	public void valuChangeListened(Prospectus pros){
//		System.out.println("Preparando Prospectus");
//		thisProspectus = pros;
//		System.out.println("Prospectus sel"+thisProspectus.getProspectusPK().getProspectus_id());
//	}

	public MembershipService getMembershipService() {
		return membershipService;
	}

	public void setMembershipService(MembershipService membershipService) {
		this.membershipService = membershipService;
	}

	public List<Membership> getLstmembers() {
		return lstmembers;
	}

	public void setLstmembers(List<Membership> lstmembers) {
		this.lstmembers = lstmembers;
	}

	public RoleService getRoleservice() {
		return roleservice;
	}

	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}

	public List<Role> getLstroles() {
		return lstroles;
	}

	public void setLstroles(List<Role> lstroles) {
		this.lstroles = lstroles;
	}

	public ProspectusService getProspectusService() {
		return prospectusService;
	}

	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}
	
	public Integer getThisRole() {
		return thisRole;
	}

	public void setThisRole(Integer thisRole) {
		this.thisRole = thisRole;
	}

	public Integer getThisProspectus() {
		return thisProspectus;
	}

	public void setThisProspectus(Integer thisProspectus) {
		this.thisProspectus = thisProspectus;
	}

	public List<RoleFunction> getLstFunction() {
		return lstFunction;
	}

	public void setLstFunction(List<RoleFunction> lstFunction) {
		this.lstFunction = lstFunction;
	}
	
	public void setFuctionsByRoleSel(){
		
		lstFunction = rolefunctionService.getLstFunctionByRole(thisRole, sesion.getCompany_id());
		setScreenByRoleSel();
		
	}
	
	public void setScreenByRoleSel(){
		
		List<RoleAccess> temp = roleaccessservice.getAccessListByRole(thisRole, sesion.getCompany_id());
		
		ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		lstScreen = null;
		
		lstScreen = new ArrayList<RoleAccess>();
		
		for(RoleAccess roleaccess : temp){
			
			roleaccess.getScreen().setName( recurso.getString(roleaccess.getScreen().getResource_name()) );
			lstScreen.add(roleaccess);
			
		}
		
	}
	
	public void setFuctionsByRole( int role, int type ){
		
		if(type == 0){
			
			lstFuncRolActual = rolefunctionService.getLstFunctionByRole(role, sesion.getCompany_id());
			
		}else if( type == 1 ){
			
			lstFuncRolPendding = rolefunctionService.getLstFunctionByRole(role, sesion.getCompany_id());
			
		}
		
	}
	
	public void setScreensByRole( int role, int type ){
		
		List<RoleAccess> temp = new  ArrayList<RoleAccess>();
					
		temp = roleaccessservice.getAccessListByRole(role, sesion.getCompany_id());
		
		ResourceBundle recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		for(RoleAccess roleaccess : temp){
			
			roleaccess.getScreen().setName( recurso.getString(roleaccess.getScreen().getResource_name()) );
			
		}
		
		if(type == 0){
			
			lstScreenRolActual = temp;
			
		}else if( type == 1 ){
			
			lstScreenRolPendding = temp;
			
		}
		
	}
	
	public Membership getMembershipByProspectusSel(){
		
		Membership res = null;
		for(Membership mem : lstmembers){
			if(thisProspectus == mem.getMembershipPK().getProspectus_id() && sesion.getCompany_id() == mem.getMembershipPK().getCompany_id()  ){
				res = mem;
				break;
			}
		}
		
		return res;
		
	}

	public SessionBean getSesion() {
		return sesion;
	}

	public void setSesion(SessionBean sesion) {
		this.sesion = sesion;
	}

	public RoleFunctionService getRolefunctionService() {
		return rolefunctionService;
	}

	public void setRolefunctionService(RoleFunctionService rolefunctionService) {
		this.rolefunctionService = rolefunctionService;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Date getFecIni() {
		return fecIni;
	}

	public void setFecIni(Date fecIni) {
		this.fecIni = fecIni;
	}

	public RoleAssignmentService getRoleassignmentservice() {
		return roleassignmentservice;
	}

	public void setRoleassignmentservice(RoleAssignmentService roleassignmentservice) {
		this.roleassignmentservice = roleassignmentservice;
	}

	public RoleAssignment getRolepending() {
		return rolepending;
	}

	public void setRolepending(RoleAssignment rolepending) {
		this.rolepending = rolepending;
	}

	public String getMsgRes() {
		return msgRes;
	}

	public void setMsgRes(String msgRes) {
		this.msgRes = msgRes;
	}

	public RoleAssignment getRoleActual() {
		return roleActual;
	}

	public void setRoleActual(RoleAssignment roleActual) {
		this.roleActual = roleActual;
	}

	public List<RoleFunction> getLstFuncRolPendding() {
		return lstFuncRolPendding;
	}

	public void setLstFuncRolPendding(List<RoleFunction> lstFuncRolPendding) {
		this.lstFuncRolPendding = lstFuncRolPendding;
	}

	public List<RoleFunction> getLstFuncRolActual() {
		return lstFuncRolActual;
	}

	public void setLstFuncRolActual(List<RoleFunction> lstFuncRolActual) {
		this.lstFuncRolActual = lstFuncRolActual;
	}

	public ArrayList<String> getDays() {
		return days;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public ArrayList<String> getMonths() {
		return months;
	}

	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String[] getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String[] monthStr) {
		this.monthStr = monthStr;
	}


	public RoleAccessService getRoleaccessservice() {
		return roleaccessservice;
	}


	public void setRoleaccessservice(RoleAccessService roleaccessservice) {
		this.roleaccessservice = roleaccessservice;
	}


	public List<RoleAccess> getLstScreenRolActual() {
		return lstScreenRolActual;
	}


	public void setLstScreenRolActual(List<RoleAccess> lstScreenRolActual) {
		this.lstScreenRolActual = lstScreenRolActual;
	}


	public List<RoleAccess> getLstScreenRolPendding() {
		return lstScreenRolPendding;
	}


	public void setLstScreenRolPendding(List<RoleAccess> lstScreenRolPendding) {
		this.lstScreenRolPendding = lstScreenRolPendding;
	}


	public List<RoleAccess> getLstScreen() {
		return lstScreen;
	}


	public void setLstScreen(List<RoleAccess> lstScreen) {
		this.lstScreen = lstScreen;
	}
	
}
