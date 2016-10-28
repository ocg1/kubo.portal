package mx.com.kubo.managedbeans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import mx.com.kubo.bean.SearchSummaySession;
import mx.com.kubo.bean.ValBusiness;
import mx.com.kubo.managedbeans.buro.SaldoDeudas;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.EconomicActivityPK;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.Proyect;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.ProyectLoanPK;
import mx.com.kubo.services.AccessService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.EconomicActivityService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.ProyectLoanService;

import com.soa.model.businessobject.Vtbur_infocredcte_c;
import com.soa.model.businessobject.Vtbur_infocredcte_m;
import com.soa.model.businessobject.Vtbur_infocredcte_vig;
import com.soa.model.businessobject.Vtbur_infocte;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;


@ManagedBean
@ViewScoped
public class Diagnostico implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private ProyectLoan proyectloan;
	
	private String imagenProspecto="/img/sinimagen.jpg";
	
	private String imgWPr="No proporcionado";
	
	private String imgHPr="No proporcionado";
	
	private boolean female=false;
	
	private String frequencyStr="No proporcionado";
	
	private String frequencyStr02="No proporcionado";
	
	private String scriptGrp="No proporcionado";
	
	private String scriptGrp2="No proporcionado";
	
	private String scriptGrp3="No proporcionado";
	
	private String actividad_economica="No proporcionado";
	
	private String experiencia="No proporcionado";
	
	private Double limite_cred =0D;
	
	private String lineas_credito="No proporcionado";
	
	private String fecConsulStr = "No proporcionada";
	
	private String porc_usado_credito="No proporcionado";
	
	@ManagedProperty("#{proyectLoanServiceImp}")
	protected ProyectLoanService proyectLoanService;
	
	@ManagedProperty("#{economicActivityServiceImp}")
	private EconomicActivityService economicactivityService;
	
	@ManagedProperty("#{businessServiceImp}")
	private BusinessService businessservice;
	
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService employmentService;
	
	@ManagedProperty("#{accessServiceImp}")
	protected AccessService accessService;
	
	private SimpleDateFormat fm1 	  		= new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat fm2 	  		= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
	private SimpleDateFormat fm3 	  		= new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
	
	SessionBean session;
	
	
	@PostConstruct
	public void init(){
		
		proyectloan = new ProyectLoan();
		ProyectLoanPK plpk = new ProyectLoanPK();
		NaturalPerson nat= new NaturalPerson();
		Proyect proyect = new Proyect();
		Prospectus pros = new Prospectus();
		nat.setProspectus(pros);
		proyectloan.setProyectloanPk(plpk);
		proyectloan.setPerson(nat);
		proyectloan.setProyect(proyect);
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		
		session = (SessionBean) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "sessionBean");
		
	}
	
	private void setWidth_Height(){
		
		if(proyectloan.getPerson().getProspectus().getImage()!= null && proyectloan.getPerson().getProspectus().getImage().length() > 0	) {
			imagenProspecto = "/documents/cia_"
							+ proyectloan.getPerson().getProspectus().getProspectusPK().getCompany_id()
							+ "/pros_"
							+ proyectloan.getPerson().getProspectus().getProspectusPK().getProspectus_id()
							+ "/photo/" + proyectloan.getPerson().getProspectus().getImage().split("\\.")[0]+"_thump_150"+"."+proyectloan.getPerson().getProspectus().getImage().split("\\.")[1];
			if(!isDirectory(imagenProspecto))
			{
				if(isFemale())
				{
				
					imagenProspecto = "/img/sinimagenM.jpg";
					
				}else{
					
					imagenProspecto = "/img/sinimagen.jpg";
					
				}
				
				imgWPr = "150";
				imgHPr = "150";
				
			}else{
				
				String destination = FacesContext.getCurrentInstance()
						.getExternalContext().getRealPath("/resources/");
				String path = destination + imagenProspecto;
				
				File newFile = new File(path);
				if(newFile.exists()){
					Hashtable<String, Integer> ht = getDimImg(newFile);
					imgWPr = (Integer)ht.get("Width") +"";
					imgHPr = (Integer)ht.get("Height") +"";
				}
			}
		}else{
			
			if(isFemale())
			{
			
				imagenProspecto = "/img/sinimagenM.jpg";
				
			}else{
				
				imagenProspecto = "/img/sinimagen.jpg";
				
			}
			
			imgWPr = "150";
			imgHPr = "150";
			
		}
		
	}
	
	public boolean isDirectory(String other) {
		String hacia = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
		String path= hacia+"/"+other;
		File file = new File(path);
		return  file.exists();
	}
	
	public void inicializaDaignostico(){
		
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SearchSummaySession searchsum= (SearchSummaySession) FacesContext.getCurrentInstance()
                .getApplication().getELResolver()
                .getValue(elContext, null, "searchSummaySession");
		
		String cadenaProyecto = searchsum.getSearchSummary();
		
		ProyectLoanPK passingProyectB = new ProyectLoanPK(Integer.parseInt(cadenaProyecto.split("::")[0]),Integer.parseInt(cadenaProyecto.split("::")[1]),Integer.parseInt(cadenaProyecto.split("::")[2]),Integer.parseInt(cadenaProyecto.split("::")[3]));
		proyectloan = proyectLoanService.findProyect(passingProyectB);
		
		System.out.println("Inicializando Diagnostico.. bursolnum: "+proyectloan.getMx_solicitud_buro());
		
		setFrequencyName();
		imagenProspecto = proyectloan.getPerson().getProspectus().getImage();
		
		EconomicActivityPK eapk = new EconomicActivityPK();
		
		eapk.setCompany_id(proyectloan.getProyectloanPk().getCompany_id());
		eapk.setEconomic_activity_id( proyectloan.getPerson().getEconomic_activity_id());
		
		Economic_Activity ea  = economicactivityService.getEconomicActivityById(eapk);
		
		actividad_economica = ea.getDescription();
		
		setActivity();
		
		setGender();
		
		setWidth_Height();
		
		try{
		
		WsSgbRiskServiceLocator locator;
		WsSgbRisk 			    service;
		
		locator     = new WsSgbRiskServiceLocator();
		service     = locator.getWsSgbRisk();
		
		
		Vtbur_infocredcte_c[] infocredcte_c 	= service.getVtbur_infocredcte_c(proyectloan.getMx_solicitud_buro());
		Vtbur_infocredcte_m[] infocredcte_m 	= service.getVtbur_infocredcte_m(proyectloan.getMx_solicitud_buro());
		Vtbur_infocredcte_vig[] infocredcte_vig = service.getVtbur_infocredcte_vig(proyectloan.getMx_solicitud_buro());
		Vtbur_infocte infocte 					= service.getVtbur_infocte( proyectloan.getMx_solicitud_buro() );
		
		
		SaldoDeudas saldos = new SaldoDeudas();
		
		
		
		scriptGrp = saldos.calculaGráfica(infocredcte_c, infocredcte_m, infocredcte_vig, infocte);
		
		scriptGrp2 = getScriptLoans(infocredcte_c, infocredcte_m, infocredcte_vig);
		
		scriptGrp3 =  getScriptRvlvnt( infocredcte_vig );
		
		System.out.println(infocte.getFecha_consulta() +" ----------- ");
		
		if(infocte != null && infocte.getFecha_consulta() != null && infocte.getFecha_consulta().trim().length()>0){
			fecConsulStr = fm3.format( fm2.parse( infocte.getFecha_consulta() ) );
		}else{
			fecConsulStr = "No proporcionada";
		}
		
		
	//  AUDITORIA ACCESS
		
			Access access = new Access();
			access.setCompany_id   (session.getCompany_id());
			access.setProspectus_id(session.getProspectus_id());
			access.setScreen_id(32); //
			access.setUrl_access( session.getUrl_access() );
			access.setPercentage(0);
			access.setWeb_browser(session.getNamebrawser());
			access.setWeb_browser_version(session.getVersionbrawser());
			access.setOp_system      (session.getOsbrawser());
			access.setHorizontal_size(session.getBrowser_width());
			access.setVertical_size  (session.getBrowser_height());
			access.setIpaddress      (session.getIP_address_client());
			access.setProspectus_id_viewed(proyectloan.getPerson().getNatPerPK().getProspectus_id());
			access.setUser_agent    (session.getUser_agent());
			access.setDevice_info   (session.getDevice_info());
			
			access.setProspectus_id_coach (session.getCoachProspectus_id());
			access.setAccess_from		  (session.getAccess_from());
			access.setVersion_description (session.getVersion_description());
			
			accessService.add(access, true);
			
			// FIN AUDITORIA ACCESS
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public String getScriptLoans( Vtbur_infocredcte_c[]   infocredcte_c,Vtbur_infocredcte_m[]   infocredcte_m, Vtbur_infocredcte_vig[] infocredcte_vig ){
		
		/*********************Gráfica Actividades***********************************/
	    
		int activos = infocredcte_vig==null?0:infocredcte_vig.length;
		int pagados = (infocredcte_c==null?0:infocredcte_c.length)+(infocredcte_m==null?0:infocredcte_m.length);
		
		String valArrGraphic4 ="";
		
	    if(activos == 0 && pagados == 0){
	    	
		    	valArrGraphic4 = "<script>var graphic4 = null;</script>";
		    
	    }else{
	    	
	    	valArrGraphic4 = "<script>var graphic4 = [";
			
			   
	    	
	    	valArrGraphic4 += "['Activos',"+activos+"],";
	    	valArrGraphic4 += "['Pagados',"+pagados+"]";
	    	
	   
		
		valArrGraphic4 += "];" +
				
				"</script>";
	    	
	    }
	    
	    //System.out.println( valArrGraphic4 );
	    
		return valArrGraphic4;
	    
	    /*********************fin Gráfica Actividades***********************************/
		
	}

	public ProyectLoanService getProyectLoanService() {
		return proyectLoanService;
	}

	public void setProyectLoanService(ProyectLoanService proyectLoanService) {
		this.proyectLoanService = proyectLoanService;
	}

	public ProyectLoan getProyectloan() {
		return proyectloan;
	}

	public void setProyectloan(ProyectLoan proyectloan) {
		this.proyectloan = proyectloan;
	}
	
	private void setGender(){
		if(proyectloan.getPerson() != null && proyectloan.getPerson().getGender_id()!=null&&proyectloan.getPerson().getGender_id()==2){
			
			setFemale(true);
			
		}else{
			
			setFemale(false);
		}
	}

	@SuppressWarnings("unused")
	public Hashtable<String, Integer> getDimImg(File fOrigen) {
		try {
			BufferedImage img = ImageIO.read(fOrigen);
			int xac = (int) img.getWidth();
			int yac = (int) img.getHeight();

			int x = 150;
			int y = 150;
			double resx = 0;
			double resy = 0;
			double resxy = 0;
			if (xac > x) {
				resx = x / (double) xac;
				double yy = resx * (double) yac;
				y = (int) yy;
				
				resxy = resx;
			} 
			
			if (yac > y) {
				resy = y / (double) yac;
				double xx = resy * (double) xac;
				x = (int) xx;
				resxy = resy;
			}
			Hashtable<String, Integer> ht = new Hashtable<String, Integer>();
			ht.put("Height", y);
			ht.put("Width", x);
			return ht;
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}
	
	
	private void setFrequencyName(){
		
		if(proyectloan!=null){
			
			switch(proyectloan.getFrequency_id()){
				case 1:
					frequencyStr = "Semanas";
					frequencyStr02 = "semanales";
					break;
				case 2:
					frequencyStr = "Catorcenas";
					frequencyStr02 = "catorcenales";
					break;
				case 3:
					frequencyStr = "Quincenas";
					frequencyStr02 = "quincenales";
					break;
				case 4:
					frequencyStr= "Meses";
					frequencyStr02 = "mensuales";
					break;
			}
		}
		
	}
	
	public String getImagenProspecto() {
		return imagenProspecto;
	}

	public void setImagenProspecto(String imagenProspecto) {
		this.imagenProspecto = imagenProspecto;
	}

	public String getImgWPr() {
		return imgWPr;
	}

	public void setImgWPr(String imgWPr) {
		this.imgWPr = imgWPr;
	}

	public String getImgHPr() {
		return imgHPr;
	}

	public void setImgHPr(String imgHPr) {
		this.imgHPr = imgHPr;
	}

	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}

	public String getFrequencyStr() {
		return frequencyStr;
	}

	public void setFrequencyStr(String frequencyStr) {
		this.frequencyStr = frequencyStr;
	}

	public String getFrequencyStr02() {
		return frequencyStr02;
	}

	public void setFrequencyStr02(String frequencyStr02) {
		this.frequencyStr02 = frequencyStr02;
	}

	public String getScriptGrp() {
		return scriptGrp;
	}

	public void setScriptGrp(String scriptGrp) {
		this.scriptGrp = scriptGrp;
	}

	public String getScriptGrp2() {
		return scriptGrp2;
	}

	public void setScriptGrp2(String scriptGrp2) {
		this.scriptGrp2 = scriptGrp2;
	}

	public EconomicActivityService getEconomicactivityService() {
		return economicactivityService;
	}

	public void setEconomicactivityService(
			EconomicActivityService economicactivityService) {
		this.economicactivityService = economicactivityService;
	}

	public String getActividad_economica() {
		return actividad_economica;
	}

	public void setActividad_economica(String actividad_economica) {
		this.actividad_economica = actividad_economica;
	}
	
	public void setActivity() {

		/**  Acividad economica **/
		BusinessPK pk = new BusinessPK();
		pk.setCompany_id(proyectloan.getProyectloanPk().getCompany_id());
		pk.setProspectus_id(proyectloan.getProyectloanPk().getProspectus_id());
		pk.setBusiness_id(1);
		List<Business> listB = businessservice.getListBusinessByProspect(pk.getProspectus_id(), pk.getCompany_id());
		if(listB!=null && listB.size()>0){
			
			BmxEconActivityCat bmx = businessservice.findBmxActivityById(listB.get(0).getBmx_econ_activity_id(), listB.get(0).getBusinessPK().getCompany_id());
			if(bmx != null){
				ValBusiness val  = new ValBusiness();
				val.setItem("1");
				actividad_economica += "  "+bmx.getDescription();
				//listBusiness.add(val);
				
				if(listB.get(0).getYears_since_when() != null)
					experiencia = listB.get(0).getYears_since_when()+" años";
				
				if( listB.get(0).getMonths_since_when() != null && listB.get(0).getMonths_since_when()>0 ){
					experiencia += " " +listB.get(0).getMonths_since_when()+" meses";
				}
				
				
			}

			
		}

		EmploymentPK epk = new EmploymentPK();
		epk.setCompany_id(proyectloan.getProyectloanPk().getCompany_id());
		epk.setProspectus_id(proyectloan.getProyectloanPk().getProspectus_id());
		epk.setEmployment_id(1);
		
		Employment employment = employmentService.getEmploymentById(epk);
		
		if(employment != null){
			
			
			BmxEconActivityCat bmx = businessservice.findBmxActivityById(employment.getBmx_econ_activity_id(), employment.getEmploymentPK().getCompany_id());
			if(bmx != null){
				
				actividad_economica += "  "+bmx.getDescription();
				
			}
			
			if(employment.getTenure() != null)
				experiencia = employment.getTenure()+" años";
			
			if( employment.getTenure_month() != null && employment.getTenure_month()>0 ){
				experiencia += " " +employment.getTenure_month()+" meses";
			}
		}
		

	}
	
	private String getScriptRvlvnt(  Vtbur_infocredcte_vig[] infocredcte_vig ){
		String script = "";
		
		Double limit = 0D;
		Double saldo = 0D;
		
		Double disponible = 0D;
		
		int i = 0;
			
		try{
			
			if( infocredcte_vig != null ){
				for( Vtbur_infocredcte_vig vig : infocredcte_vig ){
					if( vig.getIdtipo_contrato().equals("CC") || vig.getIdtipo_contrato().equals("CL") ){
						limit += Double.parseDouble( (vig.getLimite_credito()==null)?"0":vig.getLimite_credito() ) ;
						saldo += Double.parseDouble( (vig.getSaldo_actual()==null)?"0":vig.getSaldo_actual() );
						
						i++;
						
					}
				}
			}
			
			disponible = limit-saldo;
			
			if(i>0){
			
				limite_cred = limit;
				
				if(i>1){
					lineas_credito = i+" líneas";
				}
				
				if(i==1){
					lineas_credito = i+" línea";
				}
				
				Double porc = 0D;
				
				if(limit>0){
					porc = (saldo*100)/limit;
				}else{
					porc = (saldo*100)/limit;
				}
				
				porc = (double)Math.round((porc)*100)/100;
				
				porc_usado_credito = porc+"%";
				
				if(porc == 0  && limit == 0 ){
					
					script = "<script>" +
							"var graphicRevolventes = null" +
							"</script>";
					
				}else 
				
				if( disponible > 0 ){
					
					script = "<script>" +
								"var graphicRevolventes = [" +
									"['Fecha', 'Monto Utilizado', 'Saldo Disponible']," +
									"['"+fm1.format(fm2.parse( infocredcte_vig[0].getFecha_reporte() ))+"',"+saldo+","+disponible+"]" +
								"];" +
								"var myColors=['#3366cc','#109618'];" +
							"</script>";
				}else{
					
					script = "<script>" +
								"var graphicRevolventes = [" +
									"['Fecha', 'Límite de Crédito', 'Sobregiro']," +
									"['"+fm1.format(fm2.parse( infocredcte_vig[0].getFecha_reporte() ))+"',"+(limit>0?limit:1)+","+((-1)*disponible)+"]" +
								"];" +
								"var myColors=['#22aa99','#ffad33'];" +
							"</script>";
					
				}
			}else{
				script = "<script>" +
						"var graphicRevolventes = null" +
						"</script>";
				
				limite_cred =0D;
				
				lineas_credito ="";
				
				porc_usado_credito ="";
				
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return script;
	}

	public BusinessService getBusinessservice() {
		return businessservice;
	}

	public void setBusinessservice(BusinessService businessservice) {
		this.businessservice = businessservice;
	}

	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public String getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public String getScriptGrp3() {
		return scriptGrp3;
	}

	public void setScriptGrp3(String scriptGrp3) {
		this.scriptGrp3 = scriptGrp3;
	}

	public Double getLimite_cred() {
		return limite_cred;
	}

	public void setLimite_cred(Double limite_cred) {
		this.limite_cred = limite_cred;
	}

	public String getLineas_credito() {
		return lineas_credito;
	}

	public void setLineas_credito(String lineas_credito) {
		this.lineas_credito = lineas_credito;
	}

	public String getPorc_usado_credito() {
		return porc_usado_credito;
	}

	public void setPorc_usado_credito(String porc_usado_credito) {
		this.porc_usado_credito = porc_usado_credito;
	}

	public SessionBean getSession() {
		return session;
	}

	public void setSession(SessionBean session) {
		this.session = session;
	}

	public AccessService getAccessService() {
		return accessService;
	}

	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}

	public String getFecConsulStr() {
		return fecConsulStr;
	}

	public void setFecConsulStr(String fecConsulStr) {
		this.fecConsulStr = fecConsulStr;
	}
}
