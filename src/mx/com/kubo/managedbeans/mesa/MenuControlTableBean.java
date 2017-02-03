package mx.com.kubo.managedbeans.mesa;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.MenuRegBean;
import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.mesa.solicitud.SummaryRequest;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.AccessCollector;
import mx.com.kubo.model.Membership;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.RestructureBean;
import mx.com.kubo.model.RoleAccess;

import org.primefaces.context.RequestContext;

@SuppressWarnings("serial")
@ManagedBean @SessionScoped
public class MenuControlTableBean extends MenuControlTableBeanPMO
implements Serializable 
{		
	@PostConstruct
	public void init()
	{	
		
		Date dI = new Date();
		Calendar cd_I = Calendar.getInstance();
		cd_I.setTime(dI);
		
		faces     = FacesContext.getCurrentInstance();
		resolver  = faces.getApplication().getELResolver();
		elContext = faces.getELContext();		
		
		sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{							
			external = faces.getExternalContext();
			
			//HttpSession session = (HttpSession)ec.getSession(false);
			
			request = (HttpServletRequest) external.getRequest();			
			path    = request.getContextPath();			
			url     = (path + "/Portal/sesion-expirada.xhtml?mesaContro=S");
					
			try 
			{
				//session.invalidate();
		        external.redirect(url);
			        
			} catch (IOException ex) {
			        //Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
			}
			
			return;
		}	
			
		setMenus(new ArrayList<MenuRegBean>());
		
		recurso = ResourceBundle.getBundle("Message.MessageResourceBundle");
		
		menuAccess = service_access.loadControlMenu(sesion.getRole_id(), sesion.getCompany_id());
//		roleAccess = null; 
//		
//		if(sesion.getRole_id() != null && sesion.getRole_id() != 0)
//		{
//			roleAccess = roleaccessService.getAccessListByRole(sesion.getRole_id(), sesion.getCompany_id());
//		}
		
		setPaginaActual("controlTable/searchRequest");
		
		if(sesion.getArea().equals('M'))
		{
			try
			{
				int i = 1;
				
				for(AccessCollector a:menuAccess)
				{
//					if(inAccessbyRole(roleAccess, a))
//					{						
						MenuRegBean m = new MenuRegBean();
						
						m.setIdItem("menu" + a.getMenu_order());
						m.setNameItem(recurso.getString(a.getResource_name()));
						m.setTargetItem(a.getName());
						m.setScreenid(a.getScreen_id());
						m.setPosition(i);
						
						if(m.getTargetItem().equals( getPaginaActual() ) )
						{
							m.setClassItem("selectedMenu");
							setMenuSel(m.getIdItem());		
							
							Date dIA = new Date();
							Calendar cd_IA = Calendar.getInstance();
							cd_IA.setTime(dIA);
							
							registrar_bitacora_accesso(m.getScreenid(), 0, sesion, false);
							
							Date dFIA = new Date();
							Calendar cd_FIA = Calendar.getInstance();
							cd_FIA.setTime(dFIA);
							
							long dif_FIA_1 = cd_FIA.getTimeInMillis() - cd_IA.getTimeInMillis();
							
							System.out.println("Tiempo en ejecutar PR_ACCESS: " + dif_FIA_1 + " milisegundos " );
											
							
						}
						
						getMenus().add(m);
						
						i++;
//					}					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		if(sesion.getLastPage() != null)
//		{
//			if(sesion.getLastPage().length() >0)
//			{
//				if(sesion.getLastPage().equals("reporteBuro"))
//				{
//					setPaginaActual("controlTable/searchRequest");
//				} else {enu
//					setPaginaActual(sesion.getLastPage());
//				}
//				
//			} else {
//				setPaginaActual("controlTable/searchRequest");
//			}
//			
//		} else {
//			setPaginaActual("controlTable/searchRequest");
//		}
		
//		for(MenuRegBean menu: getMenus())
//		{
//			if(menu.getTargetItem().equals(getPaginaActual()))
//			{
//				menu.setClassItem("selectedMenu");
//				setMenuSel(menu.getIdItem());		
//				
//				registrar_bitacora_accesso(menu.getScreenid(), 0, sesion);
//								
//				break;
//			}
//		}
		
		
		Date dF = new Date();
		Calendar cd_F = Calendar.getInstance();
		cd_F.setTime(dF);
		
		long dif_1 = cd_F.getTimeInMillis() - cd_I.getTimeInMillis();
		
		System.out.println("Tiempo total en cargar MenuControlTable: " + dif_1 + " milisegundos " );
		
	}
	
	public void toRestructurePage(ActionEvent e){
		
		RestructureBean proyect = (RestructureBean) e.getComponent().getAttributes().get("proyect");
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		viewMap.clear();
		
		String fromAction = (String) e.getComponent().getAttributes().get("fromAction").toString();
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		Restructure restructure  = (Restructure) FacesContext.getCurrentInstance().getApplication()
			        .getELResolver().getValue(elContext, null, "restructure");
		
//		ProyectLoanPK pylPK = new ProyectLoanPK();
//		
//		pylPK.setCompany_id(Integer.parseInt(proyectPK.split("::")[1]));
//		pylPK.setProspectus_id(Integer.parseInt(proyectPK.split("::")[0]));
//		pylPK.setProyect_id(Integer.parseInt(proyectPK.split("::")[3]));
//		pylPK.setProyect_loan_id(Integer.parseInt(proyectPK.split("::")[2]));
//		
//		ProyectLoan proyect = servicioProyecto.findProyect(pylPK);
		
		restructure.setProyect(proyect);
		restructure.setFromAction(fromAction);
		restructure.inicializaReestructura();
		
		setPaginaActual("controlTable/restructure");
		
		Access access = new Access();
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(27);//Pantalla para realizar reestructuras
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		access.setProspectus_id_viewed(proyect.getProyect().getProyectloanPk().getProspectus_id());
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		service_access.add(access, true);				
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.addPartialUpdateTarget(":actualPage");	
		
	}
		
	public void cambiaPagina(ActionEvent e)
	{
		String v = (String) e.getComponent().getAttributes().get("section").toString();
		
		String t = (String) e.getComponent().getAttributes().get("tipoLog");
		
		Object o = (Object) e.getComponent().getAttributes().get("new_proyect");
		
		boolean flag_newProyect = false;
		
		if( o != null  ){
			flag_newProyect = true;
		}
		
		System.out.println(v);
		
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		viewMap.clear();
	
		setMenuSel(v.split("::")[2]);
		setPaginaActual(v.split("::")[0]);
		
		for(MenuRegBean a: getMenus())
		{
			if(a.getTargetItem().equals(getPaginaActual()))
			{
				a.setClassItem("selectedMenu");
				setMenuSel(a.getIdItem());				
			} else {
				a.setClassItem("");
			}
		}
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		resolver = FacesContext.getCurrentInstance()
                .getApplication().getELResolver();
		
		SearchSummaySession searchsum= (SearchSummaySession) resolver.getValue(elContext, null, "searchSummaySession");
		
		if(t != null){
		searchsum.setTypeLog(t);
		}
		
		String cadenaProyecto = searchsum.getSearchSummary();
		
		// cadenaProyecto.split("::")[0]) -- proyect_loan_id 
		// cadenaProyecto.split("::")[1]) -- proyect_id
		// cadenaProyecto.split("::")[2]) -- prospectus_id
		// cadenaProyecto.split("::")[3]) -- company_id
		
		Membership member = null;
		
		if( cadenaProyecto != null && cadenaProyecto.trim().length()>0 ){
			
			MembershipPK mpk = new  MembershipPK();
			if(cadenaProyecto.split("::").length >2){
				mpk.setCompany_id ( Integer.parseInt( cadenaProyecto.split("::")[3] ) );
				mpk.setProspectus_id ( Integer.parseInt( cadenaProyecto.split("::")[2] ) );
				
				member = membershipService.getMembershipById(mpk);
				
				if(member.getIs_canceled() != null 
						&&  member.getIs_canceled().trim().length() > 0 
						&& !member.getIs_canceled().equals("N") )
						{				
							setProspectus_is_canceled(true);			
						}
			}
			
			if(flag_newProyect){
				ProyectLoan pl = proyectloanservice.getMaxProyectLoanByProspect(Integer.parseInt( cadenaProyecto.split("::")[2]), Integer.parseInt( cadenaProyecto.split("::")[3] ));
				
				if( pl != null && pl.getStatus_id() != null && pl.getStatus_id().intValue() == 0 ){
					
					cadenaProyecto = pl.getProyectloanPk().getProyect_loan_id()+"::"+pl.getProyectloanPk().getProyect_id()+"::"+pl.getProyectloanPk().getProspectus_id()+"::"+pl.getProyectloanPk().getCompany_id();
				
					searchsum.setSearchSummary( cadenaProyecto);
					
				// cadenaProyecto.split("::")[0]) -- proyect_loan_id 
				// cadenaProyecto.split("::")[1]) -- proyect_id
				// cadenaProyecto.split("::")[2]) -- prospectus_id
				// cadenaProyecto.split("::")[3]) -- company_id
				
				}
					
			}
			
			SummaryRequest summary_request = (SummaryRequest)  resolver.getValue(elContext, null, "summaryRequest");
//			
			summary_request.init();	
			
		}
		
		Access access = new Access();
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		access.setScreen_id(Integer.parseInt(v.split("::")[1]));
		access.setPercentage(0);
		access.setWeb_browser         (sesion.getNamebrawser());
		access.setWeb_browser_version (sesion.getVersionbrawser());
		access.setOp_system           (sesion.getOsbrawser());
		access.setHorizontal_size     (sesion.getBrowser_height());
		access.setVertical_size       (sesion.getBrowser_width());
		access.setIpaddress           (sesion.getIP_address_client());		
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setUrl_access		  (sesion.getUrl_access());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		if(member != null){
			
			access.setProspectus_id_viewed(member.getMembershipPK().getProspectus_id());
			
		}
		
		service_access.add(access, true);
	}
	
	public boolean inAccessbyRole(List<RoleAccess> lstroleAccess, AccessCollector a)
	{		
		boolean flag = false;
		
		if( lstroleAccess != null){
			for(RoleAccess ra : lstroleAccess )
			{			
				if(ra.getScreen().getScreenPK().getScreen_id() == a.getScreen_id() )
				{
					flag = true;
					break;
				}			
			}
		}
		return flag;
	}
	
}
