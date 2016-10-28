package mx.com.kubo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.MenuBean;
import mx.com.kubo.managedbeans.Simulator;

@ManagedBean
@SessionScoped

public class MenuController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MenuBean> menus;
	private List<MenuBean> menusTipos;
	private List<MenuBean> menusAlianza;
	private String paginaActual = "credito_negocio";
	private String frasePaginaActual = "Préstamos con tasas bajas fijas";
	
	@PostConstruct
	public void init(){
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		Simulator simulator 
	    = (Simulator) FacesContext.getCurrentInstance().getApplication()
	        .getELResolver().getValue(elContext, null, "simulator");
	
		//simulator.simulaCred();
		simulator.simulaCred(false);
		
	}
	
	public MenuController(){
		menus = new ArrayList<MenuBean>();
		menusTipos = new ArrayList<MenuBean>();
		menusAlianza = new ArrayList<MenuBean>();
		
		//int i = 1;
		//for(; i<4;i++){
			MenuBean m = new MenuBean();
			m.setNombre("Créditos personales");
			m.setDireccion("creditos_personales");
			m.setId("0");
			menus.add(m);
			
			MenuBean m1 = new MenuBean();
			m1.setNombre("Compara tasas");
			m1.setDireccion("comparando_tasas");
			m1.setId("1");
			menus.add(m1);
			
			m1 = new MenuBean();
			m1.setNombre("Tasas y tarifas");
			m1.setDireccion("costos");
			m1.setId("2");
			menus.add(m1);
			
			m1 = new MenuBean();
			m1.setNombre("Proceso de pago");
			m1.setDireccion("proceso_pago");
			m1.setId("3");
			menus.add(m1);
			
		//}
			
			//Tipos
			
			MenuBean m2 = new MenuBean();
			m2.setNombre("Crédito para tu negocio");
			m2.setDireccion("credito_negocio");
			m2.setId("cap5");
			menusTipos.add(m2);
			
			m2 = new MenuBean();
			m2.setNombre("Consolidación de deudas");
			m2.setDireccion("consolidacion_deudas");
			m2.setId("cap3");
			menusTipos.add(m2);
			
			m2 = new MenuBean();
			m2.setNombre("Mejora tu casa");
			m2.setDireccion("mejora_tu_casa");
			m2.setId("cap4");
			menusTipos.add(m2);
			
//			m2 = new MenuBean();
//			m2.setNombre("Prestamos verdes");
//			m2.setDireccion("prestamos_verdes");
//			m2.setId("cap6");
//			menusTipos.add(m2);
			
			m2 = new MenuBean();
			m2.setNombre("Impulsa tus proyectos");
			m2.setDireccion("impulsa_proyectos");
			m2.setId("cap7");
			menusTipos.add(m2);
			
			///////////////////Alianzas  
			m2 = new MenuBean();
			m2.setNombre("ProEmpleo");
			m2.setDireccion("curated");
			m2.setId("al1");
			menusAlianza.add(m2);
			
			m2 = new MenuBean();
			m2.setNombre("Natura");
			m2.setDireccion("curatedNatura");
			m2.setId("al2");
			menusAlianza.add(m2);
		
	}

	
	public String getFrasePaginaActual() {
		return frasePaginaActual;
	}


	public void setFrasePaginaActual(String frasePaginaActual) {
		this.frasePaginaActual = frasePaginaActual;
	}


	public List<MenuBean> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}
	
	public List<MenuBean> getMenusTipos() {
		return menusTipos;
	}

	public void setMenusTipos(List<MenuBean> menusTipos) {
		this.menusTipos = menusTipos;
	}
	
	public String getPaginaActual() {
		return paginaActual;
	}

	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}

	public void cambiaPagina(ActionEvent e)
	{
		String v = (String) e.getComponent().getAttributes().get("capitulo").toString();
		
		System.out.printf("\nMenuController.cambiaPagina(): " + v);
		
		this.paginaActual =v;
	}


	public List<MenuBean> getMenusAlianza() {
		return menusAlianza;
	}


	public void setMenusAlianza(List<MenuBean> menusAlianza) {
		this.menusAlianza = menusAlianza;
	}
	
	
	
	
}
