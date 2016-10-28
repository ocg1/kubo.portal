package mx.com.kubo.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.Contract_typeCat;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.TenureCat;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.PhoneService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class AddEmployment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService  employmentService;
	
	@ManagedProperty("#{addressServiceImp}")
	private AddressService addressService;
	
	@ManagedProperty("#{phoneServiceImp}")
	private PhoneService phoneService;
	
	private Address thisAddress;
	private Employment thisEmployment;
	
	private Phone thisPhoneWorkFixed;
	private Phone thisPhoneWorkCel;
	
	private boolean hasAddress=false;
	private boolean hasEmployment=false;
	private boolean hasPhoneWorkFixed=false;
	private boolean hasPhoneWorkCel=false;
	/*private boolean hasIncludingPhone=false;
	private boolean hasPhoneCel=false;
	
	private String phoneWordFixed;
	private String phoneWordCel;
	private String phoneWordExt;
	
	private String includingPhone;
	private String phoneCel;*/
	
	//Address
	private String latLong;
	
	private int neighborhood_id;
	private String zip_code;
	private int townId;
	private int stateId;

	private String delegMun;
	private String estado;
	
	private String check_inH1;
	private String check_inM1;
	
	private String check_outH1;
	private String check_outM1;
	
	private String ladaFixedEmploy;
	private String phoneFixedEmploy;
	private String extFixedEmploy;
	
	private String ladaCelEmploy;
	private String phoneCelEmploy;
	
	private List<NeighborhoodCat> listAsents;
	private List<Contract_typeCat> listContractType;
	private List<TenureCat> listTenure;
	//private List<Phone> listPhoneEmploymen;
	
	SessionBean sesion;
	
	private int idEmploy;
	
	public AddEmployment() {

	}
	
	@PostConstruct
	public void init(){
		listContractType=employmentService.getContractTypeList();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		FacesContext facesContext= FacesContext.getCurrentInstance();
		HttpServletRequest request =(HttpServletRequest) facesContext.getExternalContext().getRequest();
		log.info("El id es:::::::::::::::::::::::::::::::::"+request.getParameter("idEmploy"));
		setIdEmploy(Integer.parseInt(request.getParameter("idEmploy")));
		setThisEmployment(employmentService.getEmploymentById(new EmploymentPK(getIdEmploy(), sesion.getCompany_id(), sesion.getProspectus_id())));
		setThisAddress(addressService.getAdressByTypeByEmployment(sesion.getProspectus_id(), sesion.getCompany_id(), 4, getIdEmploy()));
		
		setThisPhoneWorkFixed(phoneService.getPhoneByTypeByEmploymentByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 1, getIdEmploy(),sesion.getArea()));
		setThisPhoneWorkCel(phoneService.getPhoneByTypeByEmploymentByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 2, getIdEmploy(),sesion.getArea()));
		
		if(getThisPhoneWorkFixed()==null){
			thisPhoneWorkFixed=new Phone();
			setHasPhoneWorkFixed(false);
		}
		else{
			setExtFixedEmploy(getThisPhoneWorkFixed().getExtension());
			if(getThisPhoneWorkFixed().getPhone_number()!=null){
				String array[]=getThisPhoneWorkFixed().getPhone_number().split("\\)");
				setLadaFixedEmploy(array[0]+")");
				setPhoneFixedEmploy(array[1]);
			}
			setHasPhoneWorkFixed(true);
		}
		if(getThisPhoneWorkCel()==null){
			thisPhoneWorkCel=new Phone();
			setHasPhoneWorkCel(false);
		}
		else{
			if(getThisPhoneWorkCel().getPhone_number()!=null){
				String array[]=getThisPhoneWorkCel().getPhone_number().split("\\)");
				setLadaCelEmploy(array[0]+")");
				setPhoneCelEmploy(array[1]);
			}
			setHasPhoneWorkCel(true);
		}
		/*
		listPhoneEmploymen=phoneService.getPhoneByEmploymentList(sesion.getProspectus_id(), sesion.getCompany_id(),getIdEmploy());
		
		if(listPhoneEmploymen.size()!=0){
			for(Phone registro: listPhoneEmploymen){
				int idTypePhone=registro.getPhone_type_id();
				if(idTypePhone==1){//Telefono fijo del empleo
					phoneWordFixed=registro.getPhone_number();
					phoneWordExt=registro.getExtension();
					hasPhoneWordFixed=true;
					}
				else if(idTypePhone==2){//Telefono celular del empleo
					phoneWordCel=registro.getPhone_number();
					hasPhoneWordCel=true;
				}
				else if(idTypePhone==5){//Telefono particular
					includingPhone=registro.getPhone_number();
					hasIncludingPhone=true;
				}
				else if(idTypePhone==6){//Telefono celular
					phoneCel=registro.getPhone_number();
					hasPhoneCel=true;
				}
				}
		}
		else{
			thisPhone=new Phone();
		}
		*/
		
		if(thisEmployment.getCheck_in()!=null & thisEmployment.getCheck_out()!=null){
			String arrayCheckin[]=thisEmployment.getCheck_in().split(":");
			String arrayCheckout[]=thisEmployment.getCheck_out().split(":");
			check_inH1=arrayCheckin[0];
			check_inM1=arrayCheckin[1];
			
			check_outH1=arrayCheckout[0];
			check_outM1=arrayCheckout[1];
		}
		else{
			check_inH1="09";
			check_inM1="00";
			
			check_outH1="18";
			check_outM1="00";
			getThisEmployment().setCheck_in(getCheck_inH1()+":"+getCheck_inM1());
			getThisEmployment().setCheck_out(getCheck_outH1()+":"+getCheck_outM1());						
		}
			
		 if(thisAddress==null){
			 thisAddress=new Address();
			 //thisAddress.setEmployment_id(getId_employment());
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
	}

	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public Address getThisAddress() {
		return thisAddress;
	}

	public void setThisAddress(Address thisAddress) {
		this.thisAddress = thisAddress;
	}

	public Employment getThisEmployment() {
		return thisEmployment;
	}

	public void setThisEmployment(Employment thisEmployment) {
		this.thisEmployment = thisEmployment;
	}

	public boolean isHasAddress() {
		return hasAddress;
	}

	public void setHasAddress(boolean hasAddress) {
		this.hasAddress = hasAddress;
	}

	public boolean isHasEmployment() {
		return hasEmployment;
	}

	public void setHasEmployment(boolean hasEmployment) {
		this.hasEmployment = hasEmployment;
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
	
	public int getIdEmploy() {
		return idEmploy;
	}

	public void setIdEmploy(int idEmploy) {
		this.idEmploy = idEmploy;
	}
	
	public String getCheck_inH1() {
		return check_inH1;
	}

	public void setCheck_inH1(String check_inH1) {
		this.check_inH1 = check_inH1;
	}

	public String getCheck_inM1() {
		return check_inM1;
	}

	public void setCheck_inM1(String check_inM1) {
		this.check_inM1 = check_inM1;
	}

	public String getCheck_outH1() {
		return check_outH1;
	}

	public void setCheck_outH1(String check_outH1) {
		this.check_outH1 = check_outH1;
	}

	public String getCheck_outM1() {
		return check_outM1;
	}

	public void setCheck_outM1(String check_outM1) {
		this.check_outM1 = check_outM1;
	}
	
	
	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}

	public Phone getThisPhoneWorkFixed() {
		return thisPhoneWorkFixed;
	}

	public void setThisPhoneWorkFixed(Phone thisPhoneWorkFixed) {
		this.thisPhoneWorkFixed = thisPhoneWorkFixed;
	}

	public Phone getThisPhoneWorkCel() {
		return thisPhoneWorkCel;
	}

	public void setThisPhoneWorkCel(Phone thisPhoneWorkCel) {
		this.thisPhoneWorkCel = thisPhoneWorkCel;
	}

	public boolean isHasPhoneWorkFixed() {
		return hasPhoneWorkFixed;
	}

	public void setHasPhoneWorkFixed(boolean hasPhoneWorkFixed) {
		this.hasPhoneWorkFixed = hasPhoneWorkFixed;
	}

	public boolean isHasPhoneWorkCel() {
		return hasPhoneWorkCel;
	}

	public void setHasPhoneWorkCel(boolean hasPhoneWorkCel) {
		this.hasPhoneWorkCel = hasPhoneWorkCel;
	}
	
	public String getLadaFixedEmploy() {
		return ladaFixedEmploy;
	}

	public void setLadaFixedEmploy(String ladaFixedEmploy) {
		this.ladaFixedEmploy = ladaFixedEmploy;
	}

	public String getPhoneFixedEmploy() {
		return phoneFixedEmploy;
	}

	public void setPhoneFixedEmploy(String phoneFixedEmploy) {
		this.phoneFixedEmploy = phoneFixedEmploy;
	}
	public String getExtFixedEmploy() {
		return extFixedEmploy;
	}

	public void setExtFixedEmploy(String extFixedEmploy) {
		this.extFixedEmploy = extFixedEmploy;
	}

	public String getLadaCelEmploy() {
		return ladaCelEmploy;
	}

	public void setLadaCelEmploy(String ladaCelEmploy) {
		this.ladaCelEmploy = ladaCelEmploy;
	}

	public String getPhoneCelEmploy() {
		return phoneCelEmploy;
	}

	public void setPhoneCelEmploy(String phoneCelEmploy) {
		this.phoneCelEmploy = phoneCelEmploy;
	}
	
	public String getLatLong() {
		return latLong;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public void updateCheckInCheckOutEmployment(){
		if(Integer.parseInt(getCheck_inH1())<Integer.parseInt(getCheck_outH1())){
			getThisEmployment().setCheck_in(getCheck_inH1()+":"+getCheck_inM1());
			getThisEmployment().setCheck_out(getCheck_outH1()+":"+getCheck_outM1());
			
			updateEmployment();
		}		
		
	}
	
	public void updatePhoneEmploymenFixed(){
		
		log.info("====>>>>LADA FIXED= "+getLadaFixedEmploy());
		log.info("====>>>>PHONE FIXED= "+getPhoneFixedEmploy());
		boolean flagSave=false;
		boolean flagUpdate=false;
		
		if(isHasPhoneWorkFixed()){
			if(getLadaFixedEmploy()!=null && getPhoneFixedEmploy()!=null){
				getThisPhoneWorkFixed().setPhone_number(getLadaFixedEmploy()+getPhoneFixedEmploy());
				getThisPhoneWorkFixed().setExtension(getExtFixedEmploy());
				flagUpdate=phoneService.updatePhone(getThisPhoneWorkFixed());
			}
		}else{
			if(getLadaFixedEmploy()!=null && getPhoneFixedEmploy()!=null){
				getThisPhoneWorkFixed().setPhone_number(getLadaFixedEmploy()+getPhoneFixedEmploy());
				getThisPhoneWorkFixed().setExtension(getExtFixedEmploy());
				PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
				getThisPhoneWorkFixed().setPhonePk(phonePk);
				getThisPhoneWorkFixed().setPhone_type_id(1);
				getThisPhoneWorkFixed().setOwned("0");
				getThisPhoneWorkFixed().setArea(sesion.getArea());
				getThisPhoneWorkFixed().setEmployment_id(getIdEmploy());
				flagSave=phoneService.addPhone(getThisPhoneWorkFixed(),sesion.getProspectus_id(),sesion.getCompany_id());
				setHasPhoneWorkFixed(true);
			}
		}
		if(flagSave)
			log.info("====>>>> Phone fixed employment saved successfully");
		else
			log.info("====>>>> Phone fixed employment  NO saved");	
		if(flagUpdate)
			log.info("====>>>> Update phone fixed employment successfully");
		else
			log.info("====>>>> Phone Fixed employment  NO update");
	}
	public void updatePhoneEmploymentCel(){
		
		log.info("====>>>>LADA CEL FIXED= "+getLadaCelEmploy());
		log.info("====>>>>PHONE CEL FIXED= "+getPhoneCelEmploy());
		boolean flagSave=false;
		boolean flagUpdate=false;
		
		if(isHasPhoneWorkCel()){
			if(getLadaCelEmploy()!=null && getPhoneCelEmploy()!=null){
				getThisPhoneWorkCel().setPhone_number(getLadaCelEmploy()+getPhoneCelEmploy());
				flagUpdate=phoneService.updatePhone(getThisPhoneWorkCel());
			}			
		}
		else{
			if(getLadaCelEmploy()!=null && getPhoneCelEmploy()!=null){
				getThisPhoneWorkCel().setPhone_number(getLadaCelEmploy()+getPhoneCelEmploy());
				
				PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
				getThisPhoneWorkCel().setPhonePk(phonePk);
				getThisPhoneWorkCel().setPhone_type_id(2);
				getThisPhoneWorkCel().setOwned("0");
				getThisPhoneWorkCel().setArea(sesion.getArea());
				getThisPhoneWorkCel().setEmployment_id(getIdEmploy());
				flagSave=phoneService.addPhone(getThisPhoneWorkCel(),sesion.getProspectus_id(),sesion.getCompany_id());
				setHasPhoneWorkCel(true);
			}
		}
		if(flagSave)
			log.info("====>>>> Phone fixed employment saved successfully");
		else
			log.info("====>>>> Phone fixed employment  NO saved");	
		if(flagUpdate)
			log.info("====>>>> Update phone fixed employment successfully");
		else
			log.info("====>>>> Phone Fixed employment  NO update");
		
	}
	
	//Events and Functions
	public void updateEmployment(){
		boolean flag;
			flag=employmentService.updateEmployment(getThisEmployment());
				if(flag)
					log.info("====>>>> Employment saved successfully");
				else
					log.info("====>>>> Employment  NO saved");	
		
	}
	public void updateAddressBlur(){
		boolean flag;
		
		if(getZip_code()!=null && getZip_code().length()>0){
			
			getThisAddress().setCountry_id(700);
			
			getThisAddress().setState_id(getStateId());
			
			getThisAddress().setTown_id(getTownId());

			getThisAddress().setNeighborhood_id(getNeighborhood_id());
			
			getThisAddress().setZip_code(getZip_code());
		}
		
		if(isHasAddress()){	
			setSplitLatLong();
			flag = addressService.update(getThisAddress());
		}else{
			AddressPK adpk= new AddressPK();
			adpk.setCompany_id(sesion.getCompany_id());
			adpk.setProspectus_id(sesion.getProspectus_id());
			thisAddress.setAddressPK(adpk);
			thisAddress.setAddress_type_id(4);
			thisAddress.setEmployment_id(getIdEmploy());
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
		RequestContext requestContext = RequestContext.getCurrentInstance();
		getAsentamientos();
		
		if(getListAsents().size()!=0){
			requestContext.addCallbackParam("isValid", true);									
			updateAddressBlur();
		}
		else{
			requestContext.addCallbackParam("isValid", false);
		}
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
	
	public void setSplitLatLong() {
		log.info("<<<<<<<<<<<<<<<lATLONG= "+getLatLong());
		if(getLatLong()!=null){
			try {
				String res = getLatLong().replace("(", "").replace(")", "").replace(" ", "");
				thisAddress.setLatitude(res.split(",")[0]);
				thisAddress.setLongitude(res.split(",")[1]);				
			} catch (Exception e) {
				log.info("Error split cordenadas map:"+e.getMessage());
			}
		}	
	}

}
