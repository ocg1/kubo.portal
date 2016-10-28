package mx.com.kubo.managedbeans;

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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.model.Frequency;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.Purpose;
import mx.com.kubo.model.Term;
import mx.com.kubo.services.FrequencyService;
import mx.com.kubo.services.MembershipService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ProyectService;
import mx.com.kubo.services.PurposeService;
import mx.com.kubo.services.TermService;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;

@ManagedBean
@ViewScoped
public class AddProyect implements Serializable {
	
	private static final long serialVersionUID = 1L;
    
	Logger log = Logger.getLogger(getClass());
	
	private Purpose purpose=new Purpose();
	private Term term=new Term();
	private Frequency frequency=new Frequency();
	
	@ManagedProperty("#{purposeServiceImp}")
	private PurposeService purposeService;
	
	@ManagedProperty("#{termServiceImp}")
	private TermService termService;
	
	@ManagedProperty("#{frequencyServiceImp}")
	private FrequencyService frequencyService;
	
	@ManagedProperty("#{proyectServiceImp}")
	private ProyectService proyectService;
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	private ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{membershipserviceImp}")
	private MembershipService membershipservice;
	
	private List<Purpose> listPurpose;
	private List<Term> listTerm;
	private List<Frequency> listFrequency;
	
	private String name;
	private int type_id;
	private int purpose_id = 1;
	private int partner_id;
	private String tagline;
	private String logo;
	private String image;
	private String description;
	private String goal;
	private String benefits;
	private String consecuences;
	private String reason;
	
	//Propiedades de la tabla ln_proyect_loan
	private char funding_type;
	private Double ammount;
	private Double min_ammount;
	private int days_online;
	private int term_id;
	private int frequency_id;
	private Double payment;
	
	/*Navega tab/Galeria imga/fondeo/dias/personas fondeando*/
	int fondeado;
	int persFondeo;
	int diaPublicado;
	private Integer idTab;
	int actTab;      
	private List<String> images;
	
	
	@PostConstruct
	public void init(){
		listPurpose=purposeService.getPurposeList();	
		listTerm=termService.getTermList();
		listFrequency=frequencyService.getFrequencyList();
		
		images = new ArrayList<String>();  
		  
        for(int i=1;i<=5;i++) {  
            images.add("galleria" + i + ".jpg");  
        }
        actTab=0;
	}
	public AddProyect(){
		logo = "/resources/img/sinimagen.jpg";
		image = "/resources/img/sinimagen.jpg";
	}
	
	public void addNewProyect(){
		log.info("Begin add Proyect");
		Proyect proyect=new Proyect();
		proyect.setName(getName());
		proyect.setType_id(getType_id());
		proyect.setPurpose_id(getPurpose_id());
		proyect.setPartner_id("1");
		
		
		proyect.setTagline(getTagline());
		proyect.setLogo(getLogo());
		proyect.setImage(getImage());		
		proyectService.add(proyect);
		log.info("End add Proyect");
	}
	
	public void addNewProyectLoan(){
		log.info("Begin add ProyectLoan");
		ProyectLoan proyectLoan=new ProyectLoan();		
		proyectLoan.setFunding_type(getFunding_type());
		proyectLoan.setAmmount(getAmmount());
		proyectLoan.setMin_ammount(getMin_ammount());
		proyectLoan.setDays_online(getDays_online());
		proyectLoan.setTerm_id(getTerm_id());
		proyectLoan.setFrequency_id(getFrequency_id());		
		proyectLoan.setPayment(getPayment());
		proyectLoanService.add(proyectLoan);
		log.info("End add ProyectLoan");
		
	}
	
	
	
	public Purpose getPurpose() {
		return purpose;
	}
	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}
	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	public PurposeService getPurposeService() {
		return purposeService;
	}
	public void setPurposeService(PurposeService purposeService) {
		this.purposeService = purposeService;
	}
	
	
	public TermService getTermService() {
		return termService;
	}
	public void setTermService(TermService termService) {
		this.termService = termService;
	}
	
	
	public FrequencyService getFrequencyService() {
		return frequencyService;
	}
	public void setFrequencyService(FrequencyService frequencyService) {
		this.frequencyService = frequencyService;
	}
	
	public ProyectService getProyectService() {
		return proyectService;
	}
	public void setProyectService(ProyectService proyectService) {
		this.proyectService = proyectService;
	}
	
	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}
	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}
	public List<Purpose> getListPurpose() {
		return listPurpose;
	}
	public void setListPurpose(List<Purpose> listPurpose) {
		this.listPurpose = listPurpose;
	}
	public List<Term> getListTerm() {
		return listTerm;
	}
	public void setListTerm(List<Term> listTerm) {
		this.listTerm = listTerm;
	}
	public List<Frequency> getListFrequency() {
		return listFrequency;
	}
	public void setListFrequency(List<Frequency> listFrequency) {
		this.listFrequency = listFrequency;
	}
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public int getPurpose_id() {
		return purpose_id;
	}
	public void setPurpose_id(int purpose_id) {
		this.purpose_id = purpose_id;
	}
	public int getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(int partner_id) {
		this.partner_id = partner_id;
	}
	public String getTagline() {
		return tagline;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	public String getConsecuences() {
		return consecuences;
	}
	public void setConsecuences(String consecuences) {
		this.consecuences = consecuences;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}	
	
	// getters and setter ProyectLoan
	public char getFunding_type() {
		return funding_type;
	}
	public void setFunding_type(char funding_type) {
		this.funding_type = funding_type;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Double getMin_ammount() {
		return min_ammount;
	}
	public void setMin_ammount(Double min_ammount) {
		this.min_ammount = min_ammount;
	}
	public int getDays_online() {
		return days_online;
	}
	public void setDays_online(int days_online) {
		this.days_online = days_online;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	public int getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(int frequency_id) {
		this.frequency_id = frequency_id;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
		// Manejo de eventos
		public void handleFileUpload(FileUploadEvent event) {					
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
           	        
	        try {
	        	copyFile(extContext.getRealPath("//resources//img//"+event.getFile().getFileName()), event.getFile().getInputstream());
	            setLogo("/resources/img/"+event.getFile().getFileName());
	            log.info("Path: "+logo);
	            FacesMessage msg = new FacesMessage("Descripcion del archivo", "Nombre: " + event.getFile().getFileName() + "<br/>Tamaño: " + event.getFile().getSize() / 1024 + " Kb<br/>content type: " + event.getFile().getContentType() + "<br/><br/>The file was uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		public void handleFileUploadLogo(FileUploadEvent event) {
			
			FacesMessage msg = new FacesMessage("Succesful",event.getFile().getFileName() + " is uploaded.");  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
	        
	        try {
	        	copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
	            image = "/resources/img/"+event.getFile().getFileName();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}

		public void copyFile(String fileName, InputStream in) {
	        try {
	             // write the inputStream to a FileOutputStream
	             OutputStream out = new FileOutputStream(new File(fileName));
	             
	             int read = 0;
	             byte[] bytes = new byte[1024];
	          
	             while ((read = in.read(bytes)) != -1) {
	                 out.write(bytes, 0, read);
	             }
	          
	             in.close();
	             out.flush(); 
	             out.close();
	          
	             log.info("!!!!!!!!!!!!!!!!!     SE CREO EL ARCHIVO CORRECTAMENTE     !!!!!!!!!!!!!!!!");
	             } catch (IOException e) {
	            	 log.info("!!!!!!!!!! UUUUPS ERROOOOOOR AL SUBIR EL ARCHIVO  "+e.getMessage());
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
	/*Persona fondeando,dias ,activacion tab,progress /aleatorios*/
		public int getFondeado() {
			return (int) (Math.random() * 100 + 1);
		}
		public void setFondeado(int fondeado) {
			this.fondeado =  (int) (Math.random() * 100 + 1);
		}
		public int getPersFondeo() {
			return  (int) (Math.random() * 100 + 1);
		}
		public void setPersFondeo(int persFondeo) {
			this.persFondeo =  (int) (Math.random() * 100 + 1);
		}
		public int getDiaPublicado() {
			return  (int) (Math.random() * 30 + 1);
		}
		public void setDiaPublicado(int diaPublicado) {
			this.diaPublicado = (int) (Math.random() * 30 + 1);
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
		public List<String> getImages() {
			return images;
		}
		public void setImages(List<String> images) {
			this.images = images;
		}
		public MembershipService getMembershipservice() {
			return membershipservice;
		}
		public void setMembershipservice(MembershipService membershipservice) {
			this.membershipservice = membershipservice;
		}
		
		
	
}
