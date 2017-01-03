package mx.com.kubo.managedbeans.navigation;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import mx.com.kubo.controller.hs_connect.HubSpotController;
//import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.model.Fields;
import mx.com.kubo.model.Screen;
import mx.com.kubo.model.ScreenPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;

@ManagedBean(name = "navigationBean") 
@SessionScoped
public final class NavigationBeanIMP extends NavigationBeanPMO
implements Serializable, NavigationBeanIMO
{	
	//private HeaderBean header;			
	
	private static final long serialVersionUID = 1L;		
		
	@PostConstruct
	public final void init()
	{
		faces     = FacesContext.getCurrentInstance();
		
		elContext = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		external  = faces.getExternalContext();
						
		sesion  = (SessionBean) resolver.getValue(elContext, null, "sessionBean");
			
		System.out.println( "******* NavigationBeanIMP 1 *******" );
		System.out.println( "******* USER AGENT: "+ sesion.getUser_agent() +" *******" );
		System.out.println( "***************************************************" );
		
		if(isSesion_DISABLED())
		{
			
			return;
		}
		
		setProspectus(sesion.getProspectus_id());
		setCompany(sesion.getCompany_id());
		setHasValidScore(false);
		
		if( is_Rechazo() ){
			
			String url = (getPath() + "/Portal/no-posible-prestamo.xhtml?redirecFrom=NavigationBean");
			
			try 
			{
				System.out.println( "Redirigiendo Rechazo automatico desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			return;
			
		}
		
		iniciaPagina();
		
		simulator = (Simulator)  resolver.getValue(elContext, null, "simulator");
//		header    = (HeaderBean) resolver.getValue(elContext, null, "headerBean");
//		
//		header.setDispBtnCred(false);															
		
		asignar_loaner();	
		
		asignar_BC_score();
		asignar_menu_items();
		asignar_pagina_actual();
		
		registrar_bitacora_acceso();
		
		SystemParamPK system_param_PK_I = new SystemParamPK();
		
		system_param_PK_I.setCompany_id( 1 );
		system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
		
		 SystemParam system_param_I = systemparamservice.loadSelectedSystemParam(system_param_PK_I);
		
		if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
		
			if( membership.getPerson().getProspectus().getHs_vid() == null ){
				try{
					actualizaHS_VID();
				}catch(Exception e){
					System.out.println( "No encontrado en HUBSPOT:" + e.getMessage() );
				}
				
			}
			
		}
		
		if( is_EFL() ){
			
			String url = (getPath() + "/Portal/efl.xhtml?redirecFrom=NavigationBean");
			
			try 
			{
				System.out.println( "Redirigiendo Pantalla EFL desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			return;
			
		}
		
	}
			
	public final boolean disabledComponent(String key)
	{
		boolean result = false;
		
		if(sesion.getArea().equals('L') && isHasValidScore())
		{
			Fields fields = htDisabledFields.get(key);
			
			if(fields != null)
			{
				if(fields.getIs_enabled().equals("N"))
				{
					result = true;
				}
			}
		}
		
		return result;
	}
		
	public final String iniciar_registro()
	{
		
		return "registrar";
	}
	
	public final void redirect(String url)
	{
		try 
		{
			faces = FacesContext.getCurrentInstance();
			
			url = external.getRequestContextPath() + url;
			
			System.out.println("NavigationBeanIMP.redirect(): " + url);
			
			external.redirect(url);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void iniciaPagina(){
		
		if( sesion.getLastPage() == null || sesion.getLastPage().trim().length() == 0 ){
		
			int screen_id = 0;
	
			if( sesion.getArea().toString().equals("I") ){
				screen_id = 9;
				
			}else{
				screen_id = 2;
			}
			
			ScreenPK spk = new ScreenPK();
			
			spk.setCompany_id(sesion.getCompany_id());
			spk.setScreen_id(screen_id);
			
			System.out.println( "screen_id 77 : " );
			
			Screen screen = screenservice.getScreenById(spk);
			
			sesion.setLastPage(screen.getName());
			
			paginaIni = screen.getName();
		
		}else{
			
			if( sesion.getLastPage() != null && sesion.getLastPage().trim().equals( "registro/historialCred" ) ){
			
				ScreenPK spk = new ScreenPK();
				
				spk.setCompany_id(sesion.getCompany_id());
				spk.setScreen_id(2);
				
				Screen screen = screenservice.getScreenById(spk);
				
				sesion.setLastPage(screen.getName());
				
				paginaIni = screen.getName();
				
			}
				
		}
		
	}
	
	public void sendHubSpot(){
		
		HubSpotController hsC =  new HubSpotController();
		
		SystemParamPK sysid = new SystemParamPK(96,1);
		
		SystemParam systemParam =  systemparamservice.loadSelectedSystemParam(sysid);
		
		if( systemParam != null && systemParam.getValue().equals("S") ){
		
			 if( membership.getPerson().getProspectus().getHs_vid() != null ){
			
				 hsC.updateProspectus(membership.getPerson().getProspectus().getHs_vid(), sbHs);
				
				//hsC.createField( hs_values);
			
			}
		 
		}
		 
		 sbHs = null;
		 /*else{
		
		
			Integer vid = hsC.saveProspectus( hs_values);
			membership.getPerson().getProspectus().setHs_vid(vid);
		}*/
		
		// hsC.createField(hs_values);
		
	}
	
	public String regresaFormulario(){
		
		if( !is_Rechazo() ){
		
			viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
			
			viewMap.clear();
			
			init();
			//registrar_bitacora_acceso();
		
			return "registrar";
		
		}else{
			
			return "rechazadoPantalla";
			
		}
			
	}
	
}
