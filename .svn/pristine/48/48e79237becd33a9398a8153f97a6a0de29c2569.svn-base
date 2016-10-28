package mx.com.kubo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.com.kubo.bean.PreguntaFrecuente;




@ManagedBean
@SessionScoped

public class NavegaController implements Serializable{
	private List preguntas;
	private String seccionActual = "prestamos";
	public NavegaController(){
		preguntas = new ArrayList();
		/*preguntas estaticas or el momento*/
			PreguntaFrecuente m = new PreguntaFrecuente();
			m.setPregunta("¿Cómo puedo adquirir un préstamo en kubo?");
			m.setDireccion("LibroAyuda");
			m.setDirPreg("secciones/ayuda/faq1.xhtml");
			preguntas.add(m);
		
			m = new PreguntaFrecuente();
			m.setPregunta("¿Cómo puede kubo ofrecer las mejores tasas para  los clientes que requieren un crédito?");
			m.setDireccion("LibroAyuda");
			m.setDirPreg("secciones/ayuda/faq2.xhtml");
			preguntas.add(m);
			
			m = new PreguntaFrecuente();
			m.setPregunta("¿Cómo protege kubo mi información personal y confidencial?");
			m.setDireccion("LibroAyuda");
			m.setDirPreg("secciones/ayuda/faq3.xhtml");
			preguntas.add(m);
		
	}

	
	public List getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List menus) {
		this.preguntas = menus;
	}
	
	public String getSeccionActual() {
		return seccionActual;
	}

	public void setSeccionActual(String paginaActual) {
		this.seccionActual = paginaActual;
	}
	
}
