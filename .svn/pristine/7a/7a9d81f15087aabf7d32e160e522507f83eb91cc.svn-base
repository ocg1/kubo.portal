package mx.com.kubo.managedbeans.investor;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.registro.verificacion.CierreDelDiaIMP;
import mx.com.kubo.registro.verificacion.UsuariosFirmadosIMP;

@ManagedBean @SessionScoped
public class NavigationInvest extends NavigationInvestAMO
implements Serializable 
{		
	private static final long serialVersionUID = 8460989929192971842L;

	@PostConstruct
	public void init()
	{		
		System.out.println("Iniciando NavigationInvest");
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		ELResolver resolver = faces.getApplication().getELResolver();		
		ELContext elContext = faces.getELContext();
		
		menuInveSel = 1;
		
		SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
//		
		paginaActualInt = "";
//		
		if( sesion == null || sesion.getArea() == null)
		{
//			
//			ExternalContext external   = faces.getExternalContext();
//			
//			try 
//			{
//		        external.redirect("expired.jsf");
//		        return;
//			} catch (IOException ex) {
//			       ex.printStackTrace();
//			}
			
		}else if( sesion != null && sesion.getArea() != null && sesion.getArea().toString().equals("M") ){
			
			paginaActualIntInv="templates/myInvestments.xhtml";
			
			flagDispEdoCuenta = true;											
		}

		if( sesion != null && sesion.getArea() != null)
		{
			initTokenAccess( sesion.getArea().toString() );
		}
		
//			
//			ExternalContext external   = faces.getExternalContext();
//			
//			try 
//			{
//		        external.redirect("expired.jsf");
//		        
//			} catch (IOException ex) {
//			       ex.printStackTrace();
//			}
//			
//		}
//		
	}

	
	public void changePage(ActionEvent e)
	{
		proyectlist_to_inv = null;
		paginaActualInt = "";
		
		System.out.println(" ****  NavigationInvest.changePage ");
		
		String seccionInv = (String) e.getComponent().getAttributes().get("seccionInv").toString();
		
		FacesContext faces = FacesContext.getCurrentInstance();
		
		ELResolver resolver = faces.getApplication().getELResolver();		
		ELContext elContext = faces.getELContext();
		
		SessionBean sesion = (SessionBean) resolver.getValue(elContext, null, "sessionBean");				
		
		Map<String, Object> viewMap = faces.getViewRoot().getViewMap();
/*
		if(v.equals("investment_Action"))
		{
			
			ListaCreditos lista_creditos 	= (ListaCreditos) viewMap.get("listaCreditos");
			proyectlist_to_inv 				= lista_creditos.getProyectListForInvesInd();
			account_to_inv 					= lista_creditos.getTagAccount();
			investor_id						= sesion.getProspectus_id();
			safi_client_id					= lista_creditos.getNaturalPerson().getSafi_client_id();
		}
*/
		
		viewMap.clear();
		
		setCashWithdrawalDisp(false);
		setResumenDisp(false);
		setListProyectDisp(false);
		setBasicDataDisp(false);
		setSummaryDisp(false);
		setLogsDisp(false);
		setProyectListInvestDisp(false);
		//paginaActualInt="templates/myInvestments.xhtml";
		flagDispEdoCuenta = false;
		
		ejecutor = new CierreDelDiaIMP();
		ejecutor.setSesion(sesion);
		ejecutor.setService_system_param(service_system_param);			
		ejecutor.setSystem_param_id(EJECUCION_CIERRE_DEL_DIA);
		ejecutor.init();
		
		cierre_del_dia_ENABLED = ejecutor.isCierre_del_dia_ENABLED();
		
		if(cierre_del_dia_ENABLED)
		{
			gestor = new UsuariosFirmadosIMP();
			gestor.setFacesContext(faces);
			gestor.setSesion(sesion);
			gestor.init();
			gestor.remove();
			
		} else {
		
			init_seccion(seccionInv, sesion, faces, resolver, elContext);
			
		}
		
	}	
}
