package mx.com.kubo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import mx.com.kubo.bean.Seccion;

@ManagedBean
@SessionScoped

public class MenuHelpController {
	private  Map<String,String> menus;
	private  String actual= "hlp/secciones";
	private  List secciones ;
	
	
	public List getSecciones() {
		return secciones;
	}

	public void setSecciones(List secciones) {
		this.secciones = secciones;
	}

	public  Map<String, String> getMenus() {
		return menus;
	}

	public  void setMenus(Map<String, String> menus) {
		this.menus = menus;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}
	
	public void valueMenuChanged(ValueChangeEvent e){
		//assign new value to localeCode
		actual = e.getNewValue().toString();
	}
	
	public void seleccionaPagina(ActionEvent e){
		actual =(String) e.getComponent().getAttributes().get("pregunta").toString();
	}
	
	public MenuHelpController (){
		menus = new LinkedHashMap<String,String>();
		menus.put("General", "hlp/secciones"); //label, value
		menus.put("- Acerca de KUBO", "hlp/hlpCatAcerca"); //label, value
		menus.put("- Privacidad y Seguridad", "hlp/hlpCapitulo2");
		menus.put("-Tasas y Tarifas", "hlp/hlpCapitulo3");
		menus.put("-Regulación y Cumplimiento", "hlp/hlpCapitulo3");
		
		secciones = new ArrayList();
		
		Seccion s = new Seccion("Acerca De KUBO","pregAcerca");
		secciones.add(s);
		s = new Seccion("Privacidad y Seguridad","pregPriv");
		secciones.add(s);
		s = new Seccion("Tasas y Tarifas","pregTasas");
		secciones.add(s);
		s = new Seccion("Regulación y Cumplimiento","pregReg");
		secciones.add(s);
		
	}
	

}
