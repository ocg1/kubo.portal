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
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.PhoneService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class AddBusiness implements Serializable{

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	
	@ManagedProperty("#{businessServiceImp}")
	private BusinessService businessService;
	
	@ManagedProperty("#{addressServiceImp}")
	private AddressService addressService;
	
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService  employmentService;
	
	@ManagedProperty("#{phoneServiceImp}")
	private PhoneService phoneService;
	
	private Address thisAddress;
	private Business thisBusiness;
	private boolean hasAddress;
	
	private Phone thisPhoneBusinessFixed;
	private Phone thisPhoneBusinessCel;
	
	private boolean hasPhoneBusinessFixed=false;
	private boolean hasPhoneBusinessCel=false;
	
	private String ladaFixedBusiness;
	private String phoneFixedBusiness;
	private String extFixedBusiness;
	
	private String ladaCelBusiness;
	private String phoneCelBusiness;
	
	//Address
	private String latLong;
	
		private int neighborhood_id;
		private String zip_code;
		private int townId;
		private int stateId;

		private String delegMun;
		private String estado;
		
		private List<NeighborhoodCat> listAsents;
		private int idBusiness;
		
		SessionBean sesion;		

		FacesContext facesContext;
		ELContext elContext;
		HttpServletRequest request;
		
	
		public AddBusiness (){
			
		}
		@PostConstruct
		public void init(){			
		    facesContext= FacesContext.getCurrentInstance();
		    request =(HttpServletRequest) facesContext.getExternalContext().getRequest();		    
		    elContext = FacesContext.getCurrentInstance().getELContext();
			sesion  = (SessionBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "sessionBean");

			log.info("El id del Negocio es ::::::::::::::::::::::::::::::::: "+request.getParameter("idBusiness"));
			setIdBusiness(Integer.parseInt(request.getParameter("idBusiness")));
			setThisBusiness(businessService.loadSelectedBusiness(new BusinessPK(getIdBusiness(),sesion.getCompany_id(),sesion.getProspectus_id())));
			thisAddress=addressService.getAdressByTypeByBusiness(sesion.getProspectus_id(), sesion.getCompany_id(),3,getIdBusiness());
			
			thisPhoneBusinessFixed=phoneService.getPhoneByTypeByBusinessByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 3, getIdBusiness(),sesion.getArea());
			thisPhoneBusinessCel=phoneService.getPhoneByTypeByBusinessByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 4, getIdBusiness(),sesion.getArea());
			
			if(getThisPhoneBusinessFixed()==null){
				thisPhoneBusinessFixed=new Phone();
				setHasPhoneBusinessFixed(false);
			}
			else{
				setExtFixedBusiness(getThisPhoneBusinessFixed().getExtension());
				if(getThisPhoneBusinessFixed().getPhone_number()!=null){					
					String array[]=getThisPhoneBusinessFixed().getPhone_number().split("\\)");
					if(array.length==2){
						setLadaFixedBusiness(array[0]+")");
						setPhoneFixedBusiness(array[1]);
					}
				}
				setHasPhoneBusinessFixed(true);
			}
			if(getThisPhoneBusinessCel()==null){
				thisPhoneBusinessCel=new Phone();
				setHasPhoneBusinessCel(false);
			}
			else{
				if(getThisPhoneBusinessCel().getPhone_number()!=null){
					String array[]=getThisPhoneBusinessCel().getPhone_number().split("\\)");
					if(array.length==2){
						setLadaCelBusiness(array[0]+")");
						setPhoneCelBusiness(array[1]);
					}
				}
				setHasPhoneBusinessCel(true);
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
		public BusinessService getBusinessService() {
			return businessService;
		}
		public void setBusinessService(BusinessService businessService) {
			this.businessService = businessService;
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
		public Business getThisBusiness() {
			return thisBusiness;
		}
		public void setThisBusiness(Business thisBusiness) {
			this.thisBusiness = thisBusiness;
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
		public int getIdBusiness() {
			return idBusiness;
		}
		public void setIdBusiness(int idBusiness) {
			this.idBusiness = idBusiness;
		}
		public boolean isHasAddress() {
			return hasAddress;
		}
		public void setHasAddress(boolean hasAddress) {
			this.hasAddress = hasAddress;
		}
		
		public EmploymentService getEmploymentService() {
			return employmentService;
		}
		public void setEmploymentService(EmploymentService employmentService) {
			this.employmentService = employmentService;
		}
		
		public PhoneService getPhoneService() {
			return phoneService;
		}
		public void setPhoneService(PhoneService phoneService) {
			this.phoneService = phoneService;
		}
		public Phone getThisPhoneBusinessFixed() {
			return thisPhoneBusinessFixed;
		}
		public void setThisPhoneBusinessFixed(Phone thisPhoneBusinessFixed) {
			this.thisPhoneBusinessFixed = thisPhoneBusinessFixed;
		}
		public Phone getThisPhoneBusinessCel() {
			return thisPhoneBusinessCel;
		}
		public void setThisPhoneBusinessCel(Phone thisPhoneBusinessCel) {
			this.thisPhoneBusinessCel = thisPhoneBusinessCel;
		}
		public boolean isHasPhoneBusinessFixed() {
			return hasPhoneBusinessFixed;
		}
		public void setHasPhoneBusinessFixed(boolean hasPhoneBusinessFixed) {
			this.hasPhoneBusinessFixed = hasPhoneBusinessFixed;
		}
		public boolean isHasPhoneBusinessCel() {
			return hasPhoneBusinessCel;
		}
		public void setHasPhoneBusinessCel(boolean hasPhoneBusinessCel) {
			this.hasPhoneBusinessCel = hasPhoneBusinessCel;
		}		
		public String getLadaFixedBusiness() {
			return ladaFixedBusiness;
		}
		public void setLadaFixedBusiness(String ladaFixedBusiness) {
			this.ladaFixedBusiness = ladaFixedBusiness;
		}
		public String getPhoneFixedBusiness() {
			return phoneFixedBusiness;
		}
		public void setPhoneFixedBusiness(String phoneFixedBusiness) {
			this.phoneFixedBusiness = phoneFixedBusiness;
		}
		public String getExtFixedBusiness() {
			return extFixedBusiness;
		}
		public void setExtFixedBusiness(String extFixedBusiness) {
			this.extFixedBusiness = extFixedBusiness;
		}
		public String getLadaCelBusiness() {
			return ladaCelBusiness;
		}
		public void setLadaCelBusiness(String ladaCelBusiness) {
			this.ladaCelBusiness = ladaCelBusiness;
		}
		public String getPhoneCelBusiness() {
			return phoneCelBusiness;
		}
		public void setPhoneCelBusiness(String phoneCelBusiness) {
			this.phoneCelBusiness = phoneCelBusiness;
		}
		public String getLatLong() {
			return latLong;
		}
		public void setLatLong(String latLong) {
			this.latLong = latLong;
		}
		public void updatePhoneBusinessFixed(){
			boolean flagSave=false;
			boolean flagUpdate=false;
			
			if(isHasPhoneBusinessFixed()){
				if(getLadaFixedBusiness()!=null && getPhoneFixedBusiness()!=null){
					getThisPhoneBusinessFixed().setPhone_number(getLadaFixedBusiness()+getPhoneFixedBusiness());
					getThisPhoneBusinessFixed().setExtension(getExtFixedBusiness());
					flagUpdate=phoneService.updatePhone(getThisPhoneBusinessFixed());
				}
			}else{
				log.info("====>>>> getLadaFixedBusiness= "+getLadaFixedBusiness());
				log.info("====>>>> getLadaFixedBusiness= "+getPhoneFixedBusiness());
				
				if(getLadaFixedBusiness()!=null && getPhoneFixedBusiness()!=null){
					getThisPhoneBusinessFixed().setPhone_number(getLadaFixedBusiness()+getPhoneFixedBusiness());
					getThisPhoneBusinessFixed().setExtension(getExtFixedBusiness());
					PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
					getThisPhoneBusinessFixed().setPhonePk(phonePk);
					getThisPhoneBusinessFixed().setPhone_type_id(3);
					getThisPhoneBusinessFixed().setOwned("0");
					getThisPhoneBusinessFixed().setArea(sesion.getArea());
					getThisPhoneBusinessFixed().setBusiness_id(getIdBusiness());
					flagSave=phoneService.addPhone(getThisPhoneBusinessFixed(),sesion.getProspectus_id(),sesion.getCompany_id());
					setHasPhoneBusinessFixed(true);
				}
			}
			if(flagSave)
				log.info("====>>>> Phone fixed Business saved successfully");
			else
				log.info("====>>>> Phone fixed Business  NO saved");	
			if(flagUpdate)
				log.info("====>>>> Update phone fixed Business successfully");
			else
				log.info("====>>>> Phone Fixed Business  NO update");			
		}
		public void updatePhoneBusinessCel(){
			boolean flagSave=false;
			boolean flagUpdate=false;
			
			if(isHasPhoneBusinessCel()){
				if(getLadaCelBusiness()!=null && getPhoneCelBusiness()!=null){
					getThisPhoneBusinessCel().setPhone_number(getLadaCelBusiness()+getPhoneCelBusiness());
					flagUpdate=phoneService.updatePhone(getThisPhoneBusinessCel());
				}			
			}
			else{
				if(getLadaCelBusiness()!=null && getPhoneCelBusiness()!=null){
					getThisPhoneBusinessCel().setPhone_number(getLadaCelBusiness()+getPhoneCelBusiness());
					
					PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
					getThisPhoneBusinessCel().setPhonePk(phonePk);
					getThisPhoneBusinessCel().setPhone_type_id(4);
					getThisPhoneBusinessCel().setOwned("0");
					getThisPhoneBusinessCel().setArea(sesion.getArea());
					getThisPhoneBusinessCel().setBusiness_id(getIdBusiness());
					flagSave=phoneService.addPhone(getThisPhoneBusinessCel(),sesion.getProspectus_id(),sesion.getCompany_id());
					setHasPhoneBusinessCel(true);
				}
			}
			if(flagSave)
				log.info("====>>>> Phone fixed Business saved successfully");
			else
				log.info("====>>>> Phone fixed Business  NO saved");	
			if(flagUpdate)
				log.info("====>>>> Update phone fixed Business successfully");
			else
				log.info("====>>>> Phone Fixed Business  NO update");		
			
		}
		
		public void updateBusiness(){
			boolean flag;
			flag=businessService.update(getThisBusiness());
			if(flag)
				log.info("====>>>> Business saved successfully");
			else
				log.info("====>>>> Business  NO saved");	
				
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
				thisAddress.setAddress_type_id(3);
				thisAddress.setBusiness_id(getIdBusiness());
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
