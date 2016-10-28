package mx.com.kubo.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "infoProyect")
@ViewScoped
public class InfoProyectoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String destination="/opt/Tomcat7/wtpwebapps/Kubo1.1/resources/img/";
	
	String name;
	String tagline;
	String image;
	String history;
	String objectives;
	String impact;
	String beneficios;
	String consecuences;
	String resources;
	String pathLogo;
	String pathLogoPrincipal;
	String sujetoCredit;

	String monto;
	String plazo;
	String frecuencia;
	String montoMin;
	String tipoFondeo;
	String pago;

	String fecha;
	int fondeado;
	int persFondeo;
	int diaPublicado;	
	private Integer idTab;
	int actTab;  
    
	private List<String> images;
	UploadedFile  file=null;

	boolean collapse = true;

	public InfoProyectoBean() {
		pathLogo = "/resources/img/sinimagen.jpg";
		pathLogoPrincipal = "/resources/img/sinimagen.jpg";
	}
	
	@PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
  
        for(int i=1;i<=5;i++) {  
            images.add("galleria" + i + ".jpg");  
        }
        actTab=0;
    }  
  
    public List<String> getImages() {  
        return images;  
    }  
 
	private List<String> fileData;

	public String getPathLogoPrincipal() {
		return pathLogoPrincipal;
	}

	public void setPathLogoPrincipal(String pathLogoPrincipal) {
		this.pathLogoPrincipal = pathLogoPrincipal;
	}

	public String getPathLogo() {
		return pathLogo;
	}

	public void setPathLogo(String pathLogo) {
		this.pathLogo = pathLogo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getConsecuences() {
		return consecuences;
	}

	public void setConsecuences(String consecuences) {
		this.consecuences = consecuences;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public List getFileData() {
		return fileData;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getMontoMin() {
		return montoMin;
	}

	public void setMontoMin(String montoMin) {
		this.montoMin = montoMin;
	}

	public String getTipoFondeo() {
		return tipoFondeo;
	}

	public void setTipoFondeo(String tipoFondeo) {
		this.tipoFondeo = tipoFondeo;
	}

	public String getPago() {
		return pago;
	}

	public void setPago(String pago) {
		this.pago = pago;
	}

	public String getBeneficios() {
		return beneficios;
	}

	public void setBeneficios(String beneficios) {
		this.beneficios = beneficios;
	}

	public String getSujetoCredit() {
		return sujetoCredit;
	}

	public void setSujetoCredit(String sujetoCredit) {
		this.sujetoCredit = sujetoCredit;
	}

	public String getFecha() {
		return fecha;
	}

	// Manejo de eventos
	public void handleFileUpload(FileUploadEvent event) {
				
		FacesMessage msg = new FacesMessage("Succesful",event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
        	copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            pathLogo = "/resources/img/"+event.getFile().getFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void handleFileUploadLogo(FileUploadEvent event) {
		
		FacesMessage msg = new FacesMessage("Succesful",event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
        	copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            pathLogoPrincipal = "/resources/img/"+event.getFile().getFileName();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
public void uploadFilesImg(FileUploadEvent event) {
		
		FacesMessage msg = new FacesMessage("Succesful",event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        
        try {
        	copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            images.add(event.getFile().getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void onTabChange(TabChangeEvent event) {
		FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: "+ event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);		
	}
	
	public void copyFile(String fileName, InputStream in) {
        try {
          
        	System.out.println("Uploaded: 2");
             // write the inputStream to a FileOutputStream
             OutputStream out = new FileOutputStream(new File(destination + fileName));
             
             int read = 0;
             byte[] bytes = new byte[1024];
          
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }
          
             in.close();
             out.flush();
             out.close();
          
             System.out.println("Se creo el archivo!");
             } catch (IOException e) {
             System.out.println(e.getMessage());
             }
 }
	
	public void backTab(ActionEvent event) {		
		System.out.println("Id Back: "+event.getComponent().getId());
		if(event.getComponent().getId().equals("a1")){
			setActTab(0);
		}
		if(event.getComponent().getId().equals("a2")){
			setActTab(0);
		}
		
	}
	
	public void nextTab(ActionEvent event) {
		System.out.println("Id Next: "+event.getComponent().getId());
		if(event.getComponent().getId().equals("s1")){
			setActTab(1);
		}
		if(event.getComponent().getId().equals("s2")){
			setActTab(2);
		}
	}

	public void setFecha(String fecha) {
		// Calendar fechaActual = Calendar.getInstance();
		this.fecha = fecha;
	}

	public int getFondeado() {
		return (int) (Math.random() * 100 + 1);
	}

	public void setFondeado(int fondeado) {
		this.fondeado = (int) (Math.random() * 100 + 1);
	}

	public int getPersFondeo() {
		return (int) (Math.random() * 30 + 1);
	}

	public void setPersFondeo(int persFondeo) {
		this.persFondeo = persFondeo;
	}

	public int getDiaPublicado() {
		return (int) (Math.random() * 30);
	}

	public void setDiaPublicado(int diaPublicado) {
		this.diaPublicado = (int) Math.random() * 30;
	}

	public boolean isCollapse() {
		return collapse;
	}

	public void setCollapse(boolean collapse) {
		this.collapse = collapse;
	}
	
	public Integer getIdTab() {
	        return idTab;
	    }
	    public void setIdTab(Integer idTab) {
	        this.idTab = idTab;
	    }

		public int getActTab() {
			return actTab;
		}

		public void setActTab(int actTab) {
			this.actTab = actTab;
		}
	    
	

}
