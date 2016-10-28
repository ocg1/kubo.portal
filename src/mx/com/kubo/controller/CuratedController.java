package mx.com.kubo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import mx.com.kubo.bean.Proyecto;

@ManagedBean
@SessionScoped
public class CuratedController {
	
	private String cuartedName;
	private String imgURL;
	private String cuartedDesc;
	private String tipoProy;
	private String cuartedURL;
	List<Proyecto> proyectosProEmp;
	List<Proyecto> proyectosNatura;
	List<Proyecto> proyectos;
	
	private String direccion;
	
	public CuratedController() {
		super();
		creaProyecyosNatura();
		creaProyecyosProEmpleo();
	}
	public String getCuartedName() {
		return cuartedName;
	}
	public void setCuartedName(String cuartedName) {
		this.cuartedName = cuartedName;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public String getCuartedDesc() {
		return cuartedDesc;
	}
	public void setCuartedDesc(String cuartedDesc) {
		this.cuartedDesc = cuartedDesc;
	}
	
	public void iniciaAlianza(ActionEvent e){
			inicializa((String) e.getComponent().getAttributes().get("curador").toString());
			
		}
	
	private void inicializa(String curated_id){
		System.out.println("curated page: "+curated_id);
		if(curated_id.equals("natura_ID")){
			setCuartedName("Natura");
			setImgURL("logonatura.jpg");
			setCuartedDesc("Natura es la verdadera expresión de que puedes cuidar de la naturaleza cuidando de ti misma. A través de sus productos y de sus servicios, Natura se ha comprometido a ayudar a las personas a mantener una mejor relación con ellos mismos, con los otros y con el medio ambiente.");
			setTipoProy("proyectosNatura");
			setCuartedURL("http://www.natura.com.mx");
			creaProyecyosNatura2();
		}
		else if(curated_id.equals("proempleo_id")){{
				setCuartedName("Fundación ProEmpleo");
				setImgURL("proEmpleo.jpg");
				setCuartedDesc("ProEmpleo habilita a las personas para obtener una vida productiva y una fuente de sustento digna, tanto para ellos como para sus familias y comunidades. Las impulsa para transformar su vida, recobrando su independencia y el respeto por sí mismas.");
				setTipoProy("proyectosProEmpleo");
				setCuartedURL("http://proempleo.org.mx");
				creaProyecyosProEmpleo2();
			}
		}
	}
	
	private void creaProyecyosNatura(){
		/**************************/
		//Natura
		proyectosNatura = new ArrayList<Proyecto>();
		Proyecto pr = new Proyecto();
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("labiales.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectosNatura.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("diversa.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectosNatura.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("natura1.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectosNatura.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("natura002.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectosNatura.add(pr);
	}
	private void creaProyecyosProEmpleo(){
		/**************************/
		//ProEmpleo
		Proyecto pr = new Proyecto();
		proyectosProEmp = new ArrayList<Proyecto>();
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("ninos-felices.jpg");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectosProEmp.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("autoservicio.jpg");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectosProEmp.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("Ciencia.png");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectosProEmp.add(pr);
	}
	
	public String getTipoProy() {
		return tipoProy;
	}
	public void setTipoProy(String tipoProy) {
		this.tipoProy = tipoProy;
	}

	public String getCuartedURL() {
		return cuartedURL;
	}
	public void setCuartedURL(String cuartedURL) {
		this.cuartedURL = cuartedURL;
	}
	public List<Proyecto> getProyectosProEmp() {
		return proyectosProEmp;
	}
	public void setProyectosProEmp(List<Proyecto> proyectosProEmp) {
		this.proyectosProEmp = proyectosProEmp;
	}
	public List<Proyecto> getProyectosNatura() {
		return proyectosNatura;
	}
	public void setProyectosNatura(List<Proyecto> proyectosNatura) {
		this.proyectosNatura = proyectosNatura;
	}
	
	
	
	
	private void creaProyecyosNatura2(){
		/**************************/
		//Natura
		proyectos = new ArrayList<Proyecto>();
		Proyecto pr = new Proyecto();
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("labiales.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("diversa.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("natura1.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor1");
		pr.setDescDescripcion("Descripcion");
		pr.setDescTitulo("Titulo1");
		pr.setDiasRestante("20");
		pr.setMontoRestante("30000");
		pr.setTipoImg("Lip-Stick-32.png");
		pr.setTipoTit("Belleza");
		pr.setUrlAlianza("natura.png");
		pr.setUrlImg("natura002.jpg");
		pr.setColor("rosa");
		pr.setCategoria("A");
		pr.setGrado("3");
		pr.setDisplay("show");
		pr.setRutaAlianza("natura_ID");
		//solo de muestra para vicente
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
	}
	private void creaProyecyosProEmpleo2(){
		/**************************/
		//ProEmpleo
		Proyecto pr = new Proyecto();
		proyectos = new ArrayList<Proyecto>();
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("ninos-felices.jpg");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("autoservicio.jpg");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
		
		pr = new Proyecto();
		pr.setDescAutor("Autor2");
		pr.setDescDescripcion("Descripcion2");
		pr.setDescTitulo("Titulo2");
		pr.setDiasRestante("10");
		pr.setMontoRestante("4000");
		pr.setTipoImg("Book-Bookmark-UI-32.png");
		pr.setTipoTit("Productivo");
		pr.setUrlAlianza("proempleo-32.png");
		pr.setUrlImg("Ciencia.png");
		pr.setColor("azul");
		pr.setCategoria("B");
		pr.setGrado("1");
		pr.setDisplay("show");
		pr.setRutaAlianza("proempleo_id");
		pr.setVisibleDescCalif("show");
		proyectos.add(pr);
	}
	public List<Proyecto> getProyectos() {
		return proyectos;
	}
	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void irCompara(ActionEvent e){
		setDireccion("comparation");
	}
	public void irPreregistro(ActionEvent e){
		setDireccion("preregistro");
	}
	public void clearIr(ActionEvent e){
		setDireccion("kubofinanciero");
	}
	public String getMyDireccion() {
		return direccion;
	}
	
}
