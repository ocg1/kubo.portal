package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Contract_typeCat;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.InegiEconActivityCatPK;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Other_IncomeCat;
import mx.com.kubo.model.TenureCat;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class AddCapacidadPago  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService  employmentService;
	
	@ManagedProperty("#{addressServiceImp}")
	private AddressService addressService;
	
	private Address thisAddress;
	private boolean hasAddress;
	/*Datos de empresa*/
	private String employer;
	private String field;
	private String sector;
	private String position;
	private int tenure_id;
	private int previous_job_tenure_id;
	private int contract_type_id;
	private String nss;
	private int sector_bussines_id;
	private String check_in;
	private String check_out;

	
	
	private int other_income_id;
	
	private int neighborhood_id;
	private String zip_code;
	private int townId;
	private int stateId;

	private String delegMun;
	private String estado;
	List<NeighborhoodCat> listAsents;
	
	//Variables de Negocio
	private String employerBusiness;
	private String fieldBusiness;
	private String sectorBusiness;
	
	/*Variables de busqueta de actividad*/
	private String fieldSearch;
	private String fieldSearchUpdate;
	private String fieldSearchBusiness;
	
	private int negocioPropio;
	
	private int id_employment;
	private String divHtml="";
	private int hiddenValueUpdate;
	private int activeTabAcoordeon=0;
	private boolean disableNegocio=true;
	
	//Load List
	private List<Employment> listEmployment;
	private List<Contract_typeCat> listContractType;
	private List<TenureCat> listTenure;
	private List<Other_IncomeCat> listOtherIncome;
	
	//variables de actualizacion
	private String employerUpdate;
	private String fieldUpdate;
	private String sectorUpdate;
	/*
	@PostConstruct
	public void init(){
		listEmployment=employmentService.getEmploymentList();
		listContractType=employmentService.getContractTypeList();
		listTenure=employmentService.getTenureList();
		listOtherIncome=employmentService.getOtherIncomeList();
		
		log.info("Tamaño de la lista:"+listEmployment.size());
		for(Employment registro : listEmployment ){					
		divHtml+=	"<div id='dataEmployer"+registro.getEmploymentPK().getEmployment_id()+"'>" +
				"<div class='dvContent'>" +
							"<div class='buttonCP' style='float: left;' >"+"<a id='id_edit"+registro.getEmploymentPK().getEmployment_id()+"' style='text-decoration: none;color: #FFFFFF;' onclick='completeInformation(this);' href='#'>Completar</a></div><br/>"+
						"</div>" +						
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Nombre de la empresa</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>"+registro.getEmployer()+"</div>"+							
						"</div> <br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Giro/Actividad<label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>"+registro.getInegiEconActivityCat().getDescription()+"</div>"+							
						"</div><br/>" +
						
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Sector económico</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>"+registro.getInegiEconActivityCat().getSector_econ().getDescription()+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Puesto que desempeña</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'> ---"+"</div>"+							
						"</div><br/>" +
						
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Tipo de contrato<label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>---"+"</div>"+							
						"</div><br/>" +
												
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Número de seguridad social<label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>---"+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Sector</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>---"+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Antiguedad</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>---"+"</div>"+							
						"</div><br/>" +		
							
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Antiguedad en empleo anterior</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>---"+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Horario laboral</label></div>" +
							"<div class='dvElementLength'>---"+"</div>"+							
						"</div><br/><br/> <hr class='ui-separator ui-state-default ui-corner-all' />" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Dirección</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;' >--------------------------<br/>---------------"+"</div>"+							
						"</div><hr class='ui-separator ui-state-default ui-corner-all' />" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' ><label class='labelsStl' style='font-weight: bold;'>Ingresos<label></div>" +													
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' style='text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Ingreso mensual bruto:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>$ 0.00"+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' style='text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Ingresos Adicionales:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>$ 0.00"+"</div>"+							
						"</div><br/>" +
						
						"<div class='dvContent'>" +
							"<div class='dvLabel' style=' text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Comisiones o bonos:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>$ 0.00"+"</div>"+							
						"</div><br/>" +
						
						"<div class='dvContent'>" +
							"<div class='dvLabel' style='text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Otros ingresos:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;' >$ 0.00"+"</div>"+							
						"</div><hr class='ui-separator ui-state-default ui-corner-all' />" +

						"<div class='dvContent'>" +
							"<div class='dvLabel'><label class='labelsStl' style='font-weight: bold;'>Egresos</label></div>" +													
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' style='text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Comida en el trabajo:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;'>$ 0.00"+"</div>"+							
						"</div><br/>" +
							
						"<div class='dvContent'>" +
							"<div class='dvLabel' style='text-align: right;' ><label class='labelsStl' style='font-weight: bold;'>Transporte o gasolina:</label></div>" +
							"<div class='dvElementLength' style='padding-top: 6px;' >$ 0.00"+"</div>"+							
						"</div><hr class='ui-separator ui-state-default ui-corner-all' />"+
					"</div>";
		}
	}
	
	*/
	
	
	public AddCapacidadPago(){		
		
	}
	
	public void newEmployment(ActionEvent event){
		/*log.info("Inicia insert.......");
		Employment employment=new Employment();
		employment.setEmployer(getEmployer());
		employment.setField(getField());
		employment.setSector(getSector());
		employmentService.add(employment);
				
		log.info("Temina con.......= "+id_employment);*/
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
	

	public String getFieldSearch() {
		return fieldSearch;
	}
	public void setFieldSearch(String fieldSearch) {
		this.fieldSearch = fieldSearch;
	}
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
		
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getTenure_id() {
		return tenure_id;
	}

	public void setTenure_id(int tenure_id) {
		this.tenure_id = tenure_id;
	}
	
	public int getPrevious_job_tenure_id() {
		return previous_job_tenure_id;
	}

	public void setPrevious_job_tenure_id(int previous_job_tenure_id) {
		this.previous_job_tenure_id = previous_job_tenure_id;
	}

	public int getContract_type_id() {
		return contract_type_id;
	}

	public void setContract_type_id(int contract_type_id) {
		this.contract_type_id = contract_type_id;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public int getSector_bussines_id() {
		return sector_bussines_id;
	}

	public void setSector_bussines_id(int sector_bussines_id) {
		this.sector_bussines_id = sector_bussines_id;
	}


	public String getCheck_in() {		
		return check_in;
	}




	public void setCheck_in(String check_in) {
		this.check_in = check_in;
	}




	public String getCheck_out() {
		return check_out;
	}

	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}

	public int getOther_income_id() {
		return other_income_id;
	}

	public void setOther_income_id(int other_income_id) {
		this.other_income_id = other_income_id;
	}
	
	
	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public int getNeighborhood_id() {
		return neighborhood_id;
	}

	public void setNeighborhood_id(int neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public int getTownId() {
		return townId;
	}
	
	public void setTownId(int townId) {
		this.townId = townId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	

	public String getDelegMun() {
		return delegMun;
	}
	public void setDelegMun(String delegMun) {
		this.delegMun = delegMun;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<NeighborhoodCat> getListAsents() {
		return listAsents;
	}
	public void setListAsents(List<NeighborhoodCat> listAsents) {
		this.listAsents = listAsents;
	}
	public int getId_employment() {
		return id_employment;
	}

	public void setId_employment(int id_employment) {
		this.id_employment = id_employment;
	}
	
	public Address getThisAddress() {
		return thisAddress;
	}

	public void setThisAddress(Address thisAddress) {
		this.thisAddress = thisAddress;
	}




	public boolean isHasAddress() {
		return hasAddress;
	}




	public void setHasAddress(boolean hasAddress) {
		this.hasAddress = hasAddress;
	}




	public String getDivHtml() {
		return divHtml;
	}

	public void setDivHtml(String divHtml) {
		this.divHtml = divHtml;
	}

	public int getHiddenValueUpdate() {
		return hiddenValueUpdate;
	}
	public void setHiddenValueUpdate(int hiddenValueUpdate) {
		this.hiddenValueUpdate = hiddenValueUpdate;
	}
	public int getActiveTabAcoordeon() {
		return activeTabAcoordeon;
	}
	public void setActiveTabAcoordeon(int activeTabAcoordeon) {
		this.activeTabAcoordeon = activeTabAcoordeon;
	}
	
	public boolean isDisableNegocio() {
		return disableNegocio;
	}
	public void setDisableNegocio(boolean disableNegocio) {
		this.disableNegocio = disableNegocio;
	}
	
	
	public int getNegocioPropio() {
		return negocioPropio;
	}
	public void setNegocioPropio(int negocioPropio) {
		this.negocioPropio = negocioPropio;
	}
	public List<Employment> getListEmployment() {
		return listEmployment;
	}
	public void setListEmployment(List<Employment> listEmployment) {
		this.listEmployment = listEmployment;
	}

	public List<Contract_typeCat> getListContractType() {
		return listContractType;
	}

	public void setListContractType(List<Contract_typeCat> listContractType) {
		this.listContractType = listContractType;
	}

	public List<TenureCat> getListTenure() {
		return listTenure;
	}

	public void setListTenure(List<TenureCat> listTenure) {
		this.listTenure = listTenure;
	}

	public List<Other_IncomeCat> getListOtherIncome() {
		return listOtherIncome;
	}

	public void setListOtherIncome(List<Other_IncomeCat> listOtherIncome) {
		this.listOtherIncome = listOtherIncome;
	}

	public String getEmployerUpdate() {
		return employerUpdate;
	}
	public void setEmployerUpdate(String employerUpdate) {
		this.employerUpdate = employerUpdate;
	}	
	public String getFieldSearchUpdate() {
		return fieldSearchUpdate;
	}
	public void setFieldSearchUpdate(String fieldSearchUpdate) {
		this.fieldSearchUpdate = fieldSearchUpdate;
	}
	public String getFieldUpdate() {
		return fieldUpdate;
	}
	public void setFieldUpdate(String fieldUpdate) {
		this.fieldUpdate = fieldUpdate;
	}
	public String getSectorUpdate() {
		return sectorUpdate;
	}
	public void setSectorUpdate(String sectorUpdate) {
		this.sectorUpdate = sectorUpdate;
	}
	public String getEmployerBusiness() {
		return employerBusiness;
	}
	public void setEmployerBusiness(String employerBusiness) {
		this.employerBusiness = employerBusiness;
	}
	public String getFieldBusiness() {
		return fieldBusiness;
	}
	public void setFieldBusiness(String fieldBusiness) {
		this.fieldBusiness = fieldBusiness;
	}
	public String getSectorBusiness() {
		return sectorBusiness;
	}
	public void setSectorBusiness(String sectorBusiness) {
		this.sectorBusiness = sectorBusiness;
	}
	public String getFieldSearchBusiness() {
		return fieldSearchBusiness;
	}
	public void setFieldSearchBusiness(String fieldSearchBusiness) {
		this.fieldSearchBusiness = fieldSearchBusiness;
	}
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}
	
	public void updateEmploymentBlur(){
				/*
		 log.info("Valor idEmploymente:"+getId_employment());
		 
		int hourIn=0;
		int minuteIn=0;
		int second=0;
		
		int hourOut=0;
		int minuteOut=0;
		
		log.info("Inicia Update********************************************");
		if(getCheck_in()!=null){

			String[] timeIn= getCheck_in().split(":");
			hourIn=Integer.parseInt( timeIn[0]);
			minuteIn=Integer.parseInt( timeIn[1]);		  	
		}	
		if(getCheck_out()!=null){

			String[] timeOut= getCheck_out().split(":");
			hourOut=Integer.parseInt( timeOut[0]);
			minuteOut=Integer.parseInt( timeOut[1]);		  	
		}
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");				 
		EmploymentPK idPK=new EmploymentPK(getId_employment(),sesion.getCompany_id(),sesion.getProspectus_id());
		Employment employ=new Employment();
		employ.setPosition(getPosition());
		employ.setContract_type_id(getContract_type_id()== 0 ? null:getContract_type_id());
		employ.setNss(getNss());
		employ.setSector_bussines_id(getSector_bussines_id()== 0 ? null:getSector_bussines_id());
		employ.setTenure_id(getTenure_id()==0 ? null:getTenure_id());
		employ.setPrevious_job_tenure_id(getPrevious_job_tenure_id()== 0 ? null:getPrevious_job_tenure_id());
		employ.setCheck_in(new Time(hourIn, minuteIn, second));
		employ.setCheck_out(new Time(hourOut, minuteOut, second));
		boolean bandera=employmentService.updateEmploymentBlur(employ, idPK); 
		log.info("Bandera= "+bandera);*/
	}

	
	public void updateAddressBlur(){
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");				 
		
		boolean flag;
		
		if(getZip_code()!=null && getZip_code().length()>0){
			
			getThisAddress().setCountry_id(1);
			
			getThisAddress().setState_id(getStateId());
			
			getThisAddress().setTown_id(getTownId());

			getThisAddress().setNeighborhood_id(getNeighborhood_id());
			
			getThisAddress().setZip_code(getZip_code());
		}
		
		if(isHasAddress()){			
			flag = addressService.update(getThisAddress());
		}else{
			AddressPK adpk= new AddressPK();
			adpk.setCompany_id(sesion.getCompany_id());
			adpk.setProspectus_id(sesion.getProspectus_id());
			thisAddress.setAddressPK(adpk);
			thisAddress.setAddress_type_id(4);
			getThisAddress().getAddressPK().setAddress_id(addressService.getMaxAddressId(sesion.getProspectus_id(),sesion.getCompany_id()));
			flag = addressService.add(getThisAddress());
			setHasAddress(true);
		}
		//end SaveAddress
		if(flag)
			log.info("====>>>> Address saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  NO saved !!!!!!!!!!!!!!!");
		
		
		
		
		
		
	}
	
	
	public void zipCodeFunctionAndSave(){
		getAsentamientos();
		updateAddressBlur();
		
	}
	
	
	
	public void updateEmployment(ActionEvent event){
		/*log.info("ID Actual= "+ hiddenValueUpdate); 
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		EmploymentPK pk= new EmploymentPK(getHiddenValueUpdate(),sesion.getCompany_id(),sesion.getProspectus_id());
		Employment employ=new Employment();
		employ.setEmployer(getEmployerUpdate());
		employ.setField(getFieldUpdate());
		employ.setSector(getSectorUpdate());	
		boolean bandera=employmentService.updateEmployment(employ, pk); 
		log.info("Bandera= "+bandera);*/
	}
	
	public void updateQuery(ActionEvent e){		 
		/* int id =Integer.parseInt( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenIdQuery"));
		 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		 SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		 setId_employment(id);
		 
		 EmploymentPK idPk=new EmploymentPK(id,sesion.getCompany_id(),sesion.getProspectus_id());
		
		 Employment employment= employmentService.getEmploymentById(idPk);
		 
		 thisAddress=addressService.getMaxAddressByType(sesion.getProspectus_id(), sesion.getCompany_id(), 4);
		 
		 if(thisAddress==null){
			 thisAddress=new Address();
			 thisAddress.setEmployment_id(getId_employment());
			 setHasAddress(false);			 
			 
		 }
		 else{
			 if(thisAddress.getZip_code()!=null && thisAddress.getZip_code().length()>0){
					setZip_code(thisAddress.getZip_code());
					setNeighborhood_id(thisAddress.getNeighborhood_id());
					getAsentamientos();
				}
				setHasAddress(true);
			 
		 }
		 
		 
		 setEmployer(employment.getEmployer());
		 setField(employment.getInegiEconActivityCat().getDescription());
		 setSector(employment.getInegiEconActivityCat().getSector_econ().getDescription());
		 setPosition(employment.getPosition());
		 setContract_type_id(employment.getContract_type_id()== null ? 0:employment.getContract_type_id());
		 setNss(employment.getNss());
		 setSector_bussines_id(employment.getSector_bussines_id()==null ? 0:employment.getSector_bussines_id());
		 setTenure_id(employment.getTenure_id()==null ? 0:employment.getTenure_id());
		 setPrevious_job_tenure_id(employment.getPrevious_job_tenure_id()==null ? 0:employment.getPrevious_job_tenure_id());
		 setCheck_in(employment.getCheck_in()==null ? "00:00" : employment.getCheck_in().toString());
		 setCheck_out(employment.getCheck_out()==null ? "00:00" : employment.getCheck_out().toString());
		 
		 log.info("Query end ");*/

	}
	
	public void updateAddress(){
		/*Employment employment;
		boolean flag=true;
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");	
		
		EmploymentPK pkEmp=new EmploymentPK(getId_employment(),sesion.getProspectus_id(),sesion.getCompany_id());
	    employment= new Employment();
		try {
			employment=employmentService.getEmploymentById(pkEmp);
			if(employment.getAddress_id()==null){
				Address address=new Address();
				AddressPK pkAddres=new AddressPK(addressService.getMaxAddressId(sesion.getProspectus_id(), sesion.getCompany_id()),sesion.getProspectus_id(),sesion.getCompany_id());
				address.setAddressPK(pkAddres);
				address.setAddress_type_id(1);
				address.setCountry_id(1);
				address.setState_id(getStateId());
				address.setTown_id(getTownId());
				address.setStreet(getStreet());
				address.setAddress_number(getAddress_number());
				address.setApt_number(getApt_number());
				address.setMx_manzana(getMx_manzana());
				address.setMx_lote(getMx_lote());
				address.setNeighborhood_id(getNeighborhood_id());
				address.setFirst_street_reference(getFirst_street_reference());
				address.setSecond_street_reference(getSecond_street_reference());
				address.setZip_code(getZip_code());
				address.setDescription(getDescription());
				
				flag=addressService.add(address);
				
			}
			else{
				AddressPK pkAddres=new AddressPK(employment.getAddress_id(),sesion.getProspectus_id(),sesion.getCompany_id());
				Address address=addressService.getAddressById(pkAddres);
				address.setState_id(getStateId());
				address.setTown_id(getTownId());
				address.setStreet(getStreet());
				address.setAddress_number(getAddress_number());
				address.setApt_number(getApt_number());
				address.setMx_manzana(getMx_manzana());
				address.setMx_lote(getMx_lote());
				address.setNeighborhood_id(getNeighborhood_id());
				address.setFirst_street_reference(getFirst_street_reference());
				address.setSecond_street_reference(getSecond_street_reference());
				address.setZip_code(getZip_code());
				address.setDescription(getDescription());
				flag=addressService.update(address);				
			}
			
		} catch (Exception e) {
			log.info("Error al Actualizar o Insertar:"+ e.getMessage());
		}
		
		//end SaveAddress
		if(flag)
			log.info("====>>>> Address saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  NO saved !!!!!!!!!!!!!!!");
		*/
		
		
	}
	
	public void removeEmployment(ActionEvent e){
		int id =Integer.parseInt( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenIdDelete"));
		log.info("Eliminado "+id);
		 ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		 SessionBean sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");

		 EmploymentPK employpk=new EmploymentPK(id,sesion.getCompany_id(),sesion.getProspectus_id());
		employmentService.removeEmployment(employpk);	
		
	}	
	public List<String> complete(String query) {  
        List<BmxEconActivityCat> results = new ArrayList<BmxEconActivityCat>();
        List<String> lista = new ArrayList<String>();
        lista.removeAll(results);
        results=employmentService.searchActivityList(query);        
        log.info("Tamaño result= "+results.size());
        for(BmxEconActivityCat resultado : results){        	
        	lista.add(resultado.getInegi_activity_id()+"|"+resultado.getDescription());
        }        
        
        return lista;  
    } 
	
	public void handleSelect(SelectEvent event) { 	
		String [] arreglo=event.getObject().toString().split("\\|");
		log.info("Seach es "+arreglo[1].trim());
		setFieldSearch(arreglo[1].trim());
		int id=Integer.parseInt(arreglo[0].trim());
			log.info("ID************="+id);
			InegiEconActivityCatPK activityPk= new InegiEconActivityCatPK(id,0);
			InegiEconActivityCat inegiEconAct=employmentService.searchActivitySector(activityPk);
			log.info("ACTIVIDAD************=");
			setField(inegiEconAct.getDescription());
			setSector(inegiEconAct.getSector_econ().getDescription());
	
	} 
	public void handleSelectUpdate(SelectEvent event) { 	
		String [] arreglo=event.getObject().toString().split("\\|");
		setFieldSearchUpdate(arreglo[1].trim());
		int id=Integer.parseInt(arreglo[0].trim());
			log.info("ID************="+id);
			InegiEconActivityCatPK activityPk= new InegiEconActivityCatPK(id,0);
			InegiEconActivityCat inegiEconAct=employmentService.searchActivitySector(activityPk);
			log.info("ACTIVIDAD************=");
			setFieldUpdate(inegiEconAct.getDescription());
			setSectorUpdate(inegiEconAct.getSector_econ().getDescription());
	
	}
	
	public void handleSelectBusiness(SelectEvent event) { 			
		String [] arreglo=event.getObject().toString().split("\\|");
		setFieldSearchBusiness(arreglo[1].trim());
		int id=Integer.parseInt(arreglo[0].trim());
			InegiEconActivityCatPK activityPk= new InegiEconActivityCatPK(id,0);
			InegiEconActivityCat inegiEconAct=employmentService.searchActivitySector(activityPk);
			setFieldBusiness(inegiEconAct.getDescription());
			setSectorBusiness(inegiEconAct.getSector_econ().getDescription());
	
	}
	
	public void getAsentamientos() { 			
		log.info("CODIGO POSTAL ES= "+ getZip_code());		
		listAsents=employmentService.getAsentamientosByCP(getZip_code());
		boolean bandera=true;
		for(NeighborhoodCat resultado: listAsents){
			if(bandera){
				setDelegMun(resultado.getDelegMunicipio().getName());
				setTownId(resultado.getDelegMunicipio().getTownCatPK().getTown_id());
				setEstado(resultado.getDelegMunicipio().getEstados().getName());
				setStateId(resultado.getDelegMunicipio().getEstados().getStateCatPK().getState_id());				
				break;
			}
			bandera=false;
			
			if(listAsents.size()==1)
				setNeighborhood_id(listAsents.get(0).getNeighborhoodCatPK().getNeighborhood_id());
		}
		
	}

	
	
}
