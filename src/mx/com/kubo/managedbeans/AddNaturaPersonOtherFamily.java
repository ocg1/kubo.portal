package mx.com.kubo.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.ListNeighborhoodBean;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.AddressPK;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.Income;
import mx.com.kubo.model.IncomePK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.InegiEconActivityCatPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.NeighborhoodCat;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.RelationShip;
import mx.com.kubo.model.RelationShipPK;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.services.AddressService;
import mx.com.kubo.services.BusinessService;
import mx.com.kubo.services.CountryService;
import mx.com.kubo.services.EconomicActivityService;
import mx.com.kubo.services.EmploymentService;
import mx.com.kubo.services.IncomeService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProspectusService;
import mx.com.kubo.services.RelationShipService;
import mx.com.kubo.services.StateService;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

@ManagedBean
@ViewScoped
public class AddNaturaPersonOtherFamily implements Serializable {

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
	@ManagedProperty("#{naturalPersonServiceImp}")
	private NaturalPersonService naturalPersonService;
	
	@ManagedProperty("#{prospectusServiceImp}")
	private ProspectusService prospectusService;
	
	@ManagedProperty("#{relationShipServiceImp}")
	private RelationShipService relationShipService;
	
	@ManagedProperty("#{employmentServiceImp}")
	private EmploymentService  employmentService;
	
	@ManagedProperty("#{businessServiceImp}")
	private BusinessService businessService;
	
	@ManagedProperty("#{addressServiceImp}")
	private AddressService addressService;
	
	@ManagedProperty("#{economicActivityServiceImp}")
	private EconomicActivityService economicactivityService;
	
	@ManagedProperty("#{incomeServiceImp}")
	private IncomeService incomeService;
	
	@ManagedProperty("#{countryServiceImp}")
	private CountryService countryService;

	@ManagedProperty("#{stateServiceImp}")
	private StateService stateService;
	
	@ManagedProperty("#{phoneServiceImp}")
	private PhoneService phoneService;
	
	private Income thisIncome;
	
	private RelationShip thisRelationShip;
	private Prospectus thisProspectus;
	private NaturalPerson thisNaturalPerson;
	private Address thisAddress;
	private Employment thisEmployment;
	private Business thisBusiness;
	
	private boolean hasRelationShip=false;
	private boolean hasProspectus=false;
	private boolean hasNaturalPerson=false;
	private boolean hasAddress=false;
	private boolean hasEmployment=false;
	private boolean hasBusiness=false;
	
	private String mexDisp="block";
	private String extDisp="none";
	private String addressDisp="none";
	private String employDisp="none";
	private String businessDisp="none";
	
	//Address
	private String latLong;		
	private Integer neighborhood_id;
	private String zip_code;
	private int townId;
	private int stateId;
	private String delegMun;
	private String estado;		
	private int economicActivity;
	
	private List<NeighborhoodCat> listAsents;
	private List<Employment> listEmployment;
	private List<Business> listBusiness;
	private List<Economic_Activity> economicActivityList;
	private List<Economic_Activity> economicActivityCustom;
	private List<Income> listIncomeByProspect;
	private List<StateCat> stateList;
	private List<Country> countryList;

	private ArrayList<String> days = new ArrayList<String>();
	private ArrayList<String> months = new ArrayList<String>();
	private ArrayList<String> years = new ArrayList<String>();

	private String day;
	private String month;
	private String year;
	private String dateStr;
	
	
	private String monthStr[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo",
			"Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre" };
	
	private String ladaFixedProspectus;
	private String phoneFixedPropectus;
	private String ladaCelProspectus;
	private String phoneCellProspectus;
	private boolean hasPhoneFixedProspectus = false;
	private boolean hasPhoneCell = false;
	private Phone thisPhoneFixed;
	private Phone thisPhoneCell;
	
	private String ammountWagesSalStr="0.00";
	private String ammountBusComStr="0.00";
	private String ammountOtherFamEStr="0.00";
	private String ammountOtherFamNStr="0.00";
	
	private double ammountWagesSal;
	private double ammountBusCom;
	private double ammountOtherFam;
	private double ammountOtherFamE;
	private double ammountOtherFamN;
	
	private String actDescription;
	
	private int typeIncomeID;
	
	public  Locale locale = new Locale("es","mx");
	public  java.text.NumberFormat dec = java.text.NumberFormat.getCurrencyInstance(locale);
	public  java.text.NumberFormat num = java.text.NumberFormat.getNumberInstance(locale);
	
	SessionBean session;
	
	@PostConstruct
	public void init(){
		FacesContext faces = FacesContext.getCurrentInstance();
		ELContext elContext = faces.getELContext();
		session = (SessionBean) faces.getApplication().getELResolver().getValue(elContext, null, "sessionBean");
		
		if(isSesion_DISABLED(faces)){
			return;
		}
		
		thisRelationShip=relationShipService.getRelationShipByProspectOriginal(session.getProspectus_id(), session.getCompany_id());		
		
		if(thisRelationShip==null){
			thisRelationShip=new RelationShip();
			thisNaturalPerson=new NaturalPerson();
			thisProspectus=new Prospectus();
			setHasRelationShip(false);
			setHasNaturalPerson(false);
		}
		else{
			setHasRelationShip(true);
			setHasNaturalPerson(true);
			setHasProspectus(true);
			
			thisProspectus=thisRelationShip.getProspectus();
			
			setThisEmployment(employmentService.getEmploymentById(new EmploymentPK(1,thisProspectus.getProspectusPK().getCompany_id(),thisProspectus.getProspectusPK().getProspectus_id())));
			setThisBusiness(businessService.loadSelectedBusiness(new BusinessPK(1, thisProspectus.getProspectusPK().getCompany_id(),thisProspectus.getProspectusPK().getProspectus_id())));
			listIncomeByProspect=incomeService.getListIncomeByProspect(thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id());
			
			gnNaturalPersonPK npPK = new gnNaturalPersonPK();
			npPK.setCompany_id(thisProspectus.getProspectusPK().getCompany_id());
			npPK.setProspectus_id(thisProspectus.getProspectusPK().getProspectus_id());
			
			thisNaturalPerson = naturalPersonService.getNaturalPersonById(npPK);			
						
			if(getThisEmployment()!=null){
				employDisp="block";
				setHasEmployment(true);
			}
			if(getThisBusiness() !=null){
				businessDisp="block";	
				setHasBusiness(true);
			}

			for(Income incomReg: listIncomeByProspect){
				switch (incomReg.getIncome_type_id()) {
				case 1:
					ammountWagesSalStr=num.format(incomReg.getAmmount());
					break;
				case 2:
					ammountBusComStr=num.format(incomReg.getAmmount());					
					break;
				default:
					break;
				}
			}
			
			Income checkInOtherFamily=incomeService.getIncomeByTypeIncomeID(session.getProspectus_id(),  session.getCompany_id(), 3);
			
			if(checkInOtherFamily!=null){
				if(isHasEmployment() && isHasBusiness()){
					ammountOtherFamEStr=num.format(checkInOtherFamily.getAmmount()/2);
					ammountOtherFamNStr=ammountOtherFamEStr;
				}
				else if(isHasEmployment()){
					ammountOtherFamEStr=num.format(checkInOtherFamily.getAmmount());
				}
				else{
					ammountOtherFamNStr=num.format(checkInOtherFamily.getAmmount());
					ammountOtherFamEStr=num.format(checkInOtherFamily.getAmmount());
				}
			}
			
			
			if(thisProspectus.getSame_address()!=null && thisProspectus.getSame_address().equals("N")){
				addressDisp="block";
				thisAddress = addressService.getMaxAddressByType(thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id(), 1);
				if(thisAddress==null){
					 thisAddress=new Address();					 
					 setHasAddress(false);			 					 
				 }
				 else{
					 if(thisAddress.getZip_code()!=null && thisAddress.getZip_code().length()>0){
						 	setZip_code(thisAddress.getZip_code());
							if(thisAddress.getLatitude()!=null & thisAddress.getLongitude()!=null){
								setLatLong(thisAddress.getLatitude()+","+thisAddress.getLongitude()+",Mi domicilio");
							}
							//cpFunction();	
							listAsents =  employmentService.getAsentamientosByCP(thisAddress.getZip_code());
							neighborhood_id = thisAddress.getNeighborhood_id(); //colonia
							delegMun = thisAddress.getTownCat().getName();//town
							estado = thisAddress.getStateCat().getName();//state
							stateId = thisAddress.getState_id();
							townId = thisAddress.getTown_id();
						}
						setHasAddress(true);					 
				 }
				

				setThisPhoneFixed(phoneService.getPhoneByTypeByArea(thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id(), 5,thisProspectus.getArea()));
				setThisPhoneCell(phoneService.getPhoneByTypeByArea(thisProspectus.getProspectusPK().getProspectus_id(),thisProspectus.getProspectusPK().getCompany_id(), 6,thisProspectus.getArea()));

				if (getThisPhoneFixed() == null) {
					thisPhoneFixed = new Phone();
					setHasPhoneFixedProspectus(false);
				} else {
					if (getThisPhoneFixed().getPhone_number() != null) {
						String array[] = getThisPhoneFixed().getPhone_number().split(
								"\\)");
						
						if(array != null && array.length > 1){
						
							// setLadaFixedProspectus(array[0].replace("(", "").trim());
							setPhoneFixedPropectus( array[0].replace("(", "").trim() + array[1]);
						
						}else{
							setPhoneFixedPropectus( getThisPhoneFixed().getPhone_number() );
						}
					}
					setHasPhoneFixedProspectus(true);
				}
				if (getThisPhoneCell() == null) {
					thisPhoneCell = new Phone();
					setHasPhoneCell(false);
				} else {
					if (getThisPhoneCell().getPhone_number() != null) {
						String array[] = getThisPhoneCell().getPhone_number().split(
								"\\)");
						
						if(array != null && array.length > 1){
						
							// setLadaCelProspectus(array[0].replace("(", "").trim());
							setPhoneCellProspectus( array[0].replace("(", "").trim() + array[1]);
						
						}else{
							setPhoneCellProspectus( getThisPhoneFixed().getPhone_number() );
						}
						
					}
					setHasPhoneCell(true);
				}
			}
			if (thisNaturalPerson.getCitizenship() == null)
				thisNaturalPerson.setCitizenship(1);
			
			
		}
		// llenando Combos

				// Dias
				for (int i = 1; i <= 31; i++) {
					days.add("" + i);
				}
				// Meses
				for (int i = 0; i < monthStr.length; i++) {
					months.add("" + monthStr[i]);
				}

				// Años
				SimpleDateFormat frm = new SimpleDateFormat("dd/MM/yyyy");
				
				Calendar fecha = Calendar.getInstance();
				fecha.add(Calendar.YEAR,-90);
				
				Date d1 = fecha.getTime();
				Integer year1 = Integer.parseInt(frm.format(d1).split("/")[2]);
				fecha = Calendar.getInstance();
				fecha.add(Calendar.YEAR,-18);
				Date d2 = fecha.getTime();
				Integer year2 = Integer.parseInt(frm.format(d2).split("/")[2]);
				for (int i = year2; i >=year1; i--) {
					years.add("" + i);
				}

				if (thisNaturalPerson.getDate_of_birth() != null) {
					SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
					String dStr = fm.format(thisNaturalPerson.getDate_of_birth());
					String dd = dStr.split("/")[0];
					dd = "" + Integer.parseInt(dStr.split("/")[0]);
					setDay(dd);
					setMonth(getMonthStr()[Integer.parseInt(dStr.split("/")[1]) - 1]);
					setYear(dStr.split("/")[2]);
				}
				if (thisNaturalPerson.getEconomic_activity_id() != null) {
					setEconomicActivity(thisNaturalPerson.getEconomic_activity_id());
				}
				economicActivityList = economicactivityService.getEconomicActivityList();
				stateList = stateService.getStateList();
				countryList = countryService.getCountryList();
				economicActivityCustom=new ArrayList<Economic_Activity>();
				for(Economic_Activity reg: economicActivityList){					
					if(reg.getEconomicActivityPK().getEconomic_activity_id()==1 || reg.getEconomicActivityPK().getEconomic_activity_id()==2 || reg.getEconomicActivityPK().getEconomic_activity_id()==3){
						economicActivityCustom.add(reg);
					}
				}
				
				if(isHasBusiness() && getThisBusiness()!=null){					
					if(getThisBusiness().getBmxEconActivityCat()!=null)
						actDescription=getThisBusiness().getBmxEconActivityCat().getDescription();
				}
				
				
				
		
	}

	public NaturalPersonService getNaturalPersonService() {
		return naturalPersonService;
	}

	public ProspectusService getProspectusService() {
		return prospectusService;
	}

	public RelationShipService getRelationShipService() {
		return relationShipService;
	}

	public RelationShip getThisRelationShip() {
		return thisRelationShip;
	}

	public Prospectus getThisProspectus() {
		return thisProspectus;
	}

	public NaturalPerson getThisNaturalPerson() {
		return thisNaturalPerson;
	}

	public Address getThisAddress() {
		return thisAddress;
	}

	public Employment getThisEmployment() {
		return thisEmployment;
	}

	public Business getThisBusiness() {
		return thisBusiness;
	}

	public boolean isHasRelationShip() {
		return hasRelationShip;
	}

	public boolean isHasProspectus() {
		return hasProspectus;
	}

	public boolean isHasNaturalPerson() {
		return hasNaturalPerson;
	}

	public boolean isHasAddress() {
		return hasAddress;
	}

	public boolean isHasEmployment() {
		return hasEmployment;
	}

	public boolean isHasBusiness() {
		return hasBusiness;
	}
	public String getActDescription() {
		return actDescription;
	}

	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}

	public String getMexDisp() {
		if (getThisNaturalPerson().getCitizenship() == null
				|| getThisNaturalPerson().getCitizenship() == 1)
			mexDisp = "block";
		else
			mexDisp = "none";
		return mexDisp;
	}

	public String getExtDisp() {
		if (getThisNaturalPerson().getCitizenship() == null
				|| getThisNaturalPerson().getCitizenship() == 1)
			extDisp = "none";
		else
			extDisp = "block";
		return extDisp;
	}

	public String getAddressDisp() {
		return addressDisp;
	}

	public String getEmployDisp() {
		return employDisp;
	}

	public String getBusinessDisp() {
		return businessDisp;
	}

	public String getLatLong() {
		return latLong;
	}

	public Integer getNeighborhood_id() {
		return neighborhood_id;
	}

	public String getZip_code() {
		return zip_code;
	}

	public int getTownId() {
		return townId;
	}

	public int getStateId() {
		return stateId;
	}

	public String getDelegMun() {
		return delegMun;
	}

	public String getEstado() {
		return estado;
	}

	public List<NeighborhoodCat> getListAsents() {
		return listAsents;
	}

	public void setNaturalPersonService(NaturalPersonService naturalPersonService) {
		this.naturalPersonService = naturalPersonService;
	}

	public void setProspectusService(ProspectusService prospectusService) {
		this.prospectusService = prospectusService;
	}

	public void setRelationShipService(RelationShipService relationShipService) {
		this.relationShipService = relationShipService;
	}

	public void setThisRelationShip(RelationShip thisRelationShip) {
		this.thisRelationShip = thisRelationShip;
	}

	public void setThisProspectus(Prospectus thisProspectus) {
		this.thisProspectus = thisProspectus;
	}

	public void setThisNaturalPerson(NaturalPerson thisNaturalPerson) {
		this.thisNaturalPerson = thisNaturalPerson;
	}

	public void setThisAddress(Address thisAddress) {
		this.thisAddress = thisAddress;
	}

	public void setThisEmployment(Employment thisEmployment) {
		this.thisEmployment = thisEmployment;
	}

	public void setThisBusiness(Business thisBusiness) {
		this.thisBusiness = thisBusiness;
	}

	public void setHasRelationShip(boolean hasRelationShip) {
		this.hasRelationShip = hasRelationShip;
	}

	public void setHasProspectus(boolean hasProspectus) {
		this.hasProspectus = hasProspectus;
	}

	public void setHasNaturalPerson(boolean hasNaturalPerson) {
		this.hasNaturalPerson = hasNaturalPerson;
	}

	public void setHasAddress(boolean hasAddress) {
		this.hasAddress = hasAddress;
	}

	public void setHasEmployment(boolean hasEmployment) {
		this.hasEmployment = hasEmployment;
	}

	public void setHasBusiness(boolean hasBusiness) {
		this.hasBusiness = hasBusiness;
	}

	public void setMexDisp(String mexDisp) {
		this.mexDisp = mexDisp;
	}

	public void setExtDisp(String extDisp) {
		this.extDisp = extDisp;
	}

	public void setAddressDisp(String addressDisp) {
		this.addressDisp = addressDisp;
	}

	public void setEmployDisp(String employDisp) {
		this.employDisp = employDisp;
	}

	public void setBusinessDisp(String businessDisp) {
		this.businessDisp = businessDisp;
	}

	public void setLatLong(String latLong) {
		this.latLong = latLong;
	}

	public void setNeighborhood_id(Integer neighborhood_id) {
		this.neighborhood_id = neighborhood_id;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public void setTownId(int townId) {
		this.townId = townId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public void setDelegMun(String delegMun) {
		this.delegMun = delegMun;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setListAsents(List<NeighborhoodCat> listAsents) {
		this.listAsents = listAsents;
	}
	
	
	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public BusinessService getBusinessService() {
		return businessService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public EconomicActivityService getEconomicactivityService() {
		return economicactivityService;
	}

	public int getEconomicActivity() {
		return economicActivity;
	}

	public List<Employment> getListEmployment() {
		return listEmployment;
	}

	public List<Business> getListBusiness() {
		return listBusiness;
	}

	public List<Economic_Activity> getEconomicActivityList() {
		return economicActivityList;
	}

	public List<StateCat> getStateList() {
		return stateList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public ArrayList<String> getDays() {
		return days;
	}

	public ArrayList<String> getMonths() {
		return months;
	}

	public ArrayList<String> getYears() {
		return years;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

	public String getDateStr() {
		return dateStr;
	}

	public String[] getMonthStr() {
		return monthStr;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public void setEconomicactivityService(
			EconomicActivityService economicactivityService) {
		this.economicactivityService = economicactivityService;
	}

	public void setEconomicActivity(int economicActivity) {
		this.economicActivity = economicActivity;
	}

	public void setListEmployment(List<Employment> listEmployment) {
		this.listEmployment = listEmployment;
	}

	public void setListBusiness(List<Business> listBusiness) {
		this.listBusiness = listBusiness;
	}

	public void setEconomicActivityList(List<Economic_Activity> economicActivityList) {
		this.economicActivityList = economicActivityList;
	}

	public void setStateList(List<StateCat> stateList) {
		this.stateList = stateList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public void setMonths(ArrayList<String> months) {
		this.months = months;
	}

	public void setYears(ArrayList<String> years) {
		this.years = years;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public void setMonthStr(String[] monthStr) {
		this.monthStr = monthStr;
	}
	
	public CountryService getCountryService() {
		return countryService;
	}

	public StateService getStateService() {
		return stateService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}
	
	public List<Economic_Activity> getEconomicActivityCustom() {
		return economicActivityCustom;
	}

	public void setEconomicActivityCustom(
			List<Economic_Activity> economicActivityCustom) {
		this.economicActivityCustom = economicActivityCustom;
	}
	
	public Phone getThisPhoneFixed() {
		return thisPhoneFixed;
	}

	public Phone getThisPhoneCell() {
		return thisPhoneCell;
	}

	public void setThisPhoneFixed(Phone thisPhoneFixed) {
		this.thisPhoneFixed = thisPhoneFixed;
	}

	public void setThisPhoneCell(Phone thisPhoneCell) {
		this.thisPhoneCell = thisPhoneCell;
	}
	public String getLadaFixedProspectus() {
		return ladaFixedProspectus;
	}

	public String getPhoneFixedPropectus() {
		return phoneFixedPropectus;
	}

	public String getLadaCelProspectus() {
		return ladaCelProspectus;
	}

	public String getPhoneCellProspectus() {
		return phoneCellProspectus;
	}

	public boolean isHasPhoneFixedProspectus() {
		return hasPhoneFixedProspectus;
	}

	public boolean isHasPhoneCell() {
		return hasPhoneCell;
	}

	public void setLadaFixedProspectus(String ladaFixedProspectus) {
		this.ladaFixedProspectus = ladaFixedProspectus;
	}

	public void setPhoneFixedPropectus(String phoneFixedPropectus) {
		this.phoneFixedPropectus = phoneFixedPropectus;
	}

	public void setLadaCelProspectus(String ladaCelProspectus) {
		this.ladaCelProspectus = ladaCelProspectus;
	}

	public void setPhoneCellProspectus(String phoneCellProspectus) {
		this.phoneCellProspectus = phoneCellProspectus;
	}

	public void setHasPhoneFixedProspectus(boolean hasPhoneFixedProspectus) {
		this.hasPhoneFixedProspectus = hasPhoneFixedProspectus;
	}

	public void setHasPhoneCell(boolean hasPhoneCell) {
		this.hasPhoneCell = hasPhoneCell;
	}	
	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}	
	public IncomeService getIncomeService() {
		return incomeService;
	}

	public List<Income> getListIncomeByProspect() {
		return listIncomeByProspect;
	}

	public String getAmmountWagesSalStr() {
		return ammountWagesSalStr;
	}

	public String getAmmountBusComStr() {
		return ammountBusComStr;
	}

	public String getAmmountOtherFamEStr() {
		return ammountOtherFamEStr;
	}

	public String getAmmountOtherFamNStr() {
		return ammountOtherFamNStr;
	}

	public double getAmmountWagesSal() {
		return ammountWagesSal;
	}

	public double getAmmountBusCom() {
		return ammountBusCom;
	}

	public double getAmmountOtherFam() {
		return ammountOtherFam;
	}

	public double getAmmountOtherFamE() {
		return ammountOtherFamE;
	}

	public double getAmmountOtherFamN() {
		return ammountOtherFamN;
	}

	public int getTypeIncomeID() {
		return typeIncomeID;
	}

	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}

	public void setListIncomeByProspect(List<Income> listIncomeByProspect) {
		this.listIncomeByProspect = listIncomeByProspect;
	}

	public void setAmmountWagesSalStr(String ammountWagesSalStr) {
		this.ammountWagesSalStr = ammountWagesSalStr;
		setTypeIncomeID(1);
		setAmmountWagesSal(Float.parseFloat(ammountWagesSalStr.replaceAll(",", "")));
	}

	public void setAmmountBusComStr(String ammountBusComStr) {
		this.ammountBusComStr = ammountBusComStr;
		setTypeIncomeID(2);
		setAmmountBusCom(Float.parseFloat(ammountBusComStr.replaceAll(",", "")));
	}

	public void setAmmountOtherFamEStr(String ammountOtherFamEStr) {
		this.ammountOtherFamEStr = ammountOtherFamEStr;
		setTypeIncomeID(3);
		setAmmountOtherFam(Float.parseFloat(ammountOtherFamEStr.replaceAll(",", "")));
	}

	public void setAmmountOtherFamNStr(String ammountOtherFamNStr) {
		this.ammountOtherFamNStr = ammountOtherFamNStr;
		setTypeIncomeID(3);
		setAmmountOtherFam(Float.parseFloat(ammountOtherFamNStr.replaceAll(",", ""))+Float.parseFloat(getAmmountOtherFamEStr().replaceAll(",", "")));
	}
	public void setAmmountWagesSal(double ammountWagesSal) {
		this.ammountWagesSal = ammountWagesSal;
	}

	public void setAmmountBusCom(double ammountBusCom) {
		this.ammountBusCom = ammountBusCom;
	}

	public void setAmmountOtherFam(double ammountOtherFam) {
		this.ammountOtherFam = ammountOtherFam;
	}

	public void setAmmountOtherFamE(double ammountOtherFamE) {
		this.ammountOtherFamE = ammountOtherFamE;
	}

	public void setAmmountOtherFamN(double ammountOtherFamN) {
		this.ammountOtherFamN = ammountOtherFamN;
	}

	public void setTypeIncomeID(int typeIncomeID) {
		this.typeIncomeID = typeIncomeID;
	}	
	public Income getThisIncome() {
		return thisIncome;
	}

	public void setThisIncome(Income thisIncome) {
		this.thisIncome = thisIncome;
	}

	public void addNewNaturalPerson(){
		boolean flagSave=false;
		boolean flagUpdate=false;
		boolean flagRelationShip=false;
		int PROSPECTUS = 0;
		final int COMPANY = 1;
		
		if(isHasRelationShip()){
			try{			
				//Actualiza persona
				log.info("begin add NaturalPerson");
				naturalPersonService.update(getThisNaturalPerson());
				log.info("End add NaturalPerson");
				flagUpdate=true;
			}catch(Exception e){
				log.info("fallo al actualizar natural person");
				flagUpdate=false;
			}
		}		
		
		else{	
			//Guarda la nueva persona
			ProspectusPK newProspectusPK=new ProspectusPK(0,COMPANY);
			getThisProspectus().setProspectusPK(newProspectusPK);
			//log.info("Begin add new Prospectusn prospectus_id="+PROSPECTUS+" company_id="+COMPANY);
			getThisProspectus().setProspectusPK(newProspectusPK);
			getThisProspectus().setArea('R'); // L  credito   I inversion
			getThisProspectus().setPerson_type('F');//Fisica  Mora  (A) persona fisica con actividad empresarial
			
			flagSave=prospectusService.saveProspectAndNaturalPerson(getThisProspectus(), getThisNaturalPerson());
			setThisProspectus(getThisProspectus());
			PROSPECTUS = getThisProspectus().getProspectusPK().getProspectus_id();
			setHasProspectus(true);
			setHasNaturalPerson(true);
			
			log.info("!!!!!!!!!!!!!!!! PROSPECTUSID NEW="+PROSPECTUS);
			
			if(flagSave){
				try{
					log.info("Begin add RelationShip");	
					RelationShipPK relationPk=new RelationShipPK(session.getProspectus_id(),session.getCompany_id(),PROSPECTUS);									
					getThisRelationShip().setRelationShipPk(relationPk);
					relationShipService.addRelationShip(getThisRelationShip());
					log.info("End add RelationShip");
					setHasRelationShip(true);
					flagRelationShip=true;
				}catch(Exception e){
					log.info("fallo al insertar RelationShip");
					flagRelationShip=false;
				}
			}
			
		}
		
		if(flagSave)
			log.info("====>>>>  Prospectus,NaturalPerson saved successfully");
		else
			log.info("====>>>>  Prospectus,NaturalPerson NO saved");	
		if(flagUpdate)
			log.info("====>>>> NaturalPerson  update");
		else
			log.info("====>>>> NaturalPerson  NO update");
		if(flagRelationShip)
			log.info("====>>>> RelationShip save successfully");
		else
			log.info("====>>>> RelationShip  NO saved");
		
			
		
		
	}
	
	public void updateEmployment(){
		boolean flag;
				
			flag=employmentService.updateEmployment(getThisEmployment());
				if(flag)
					log.info("====>>>> Employment saved successfully");
				else
					log.info("====>>>> Employment  NO saved");	
		
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
			
			if(getThisAddress() == null){
				setThisAddress(new Address());
			}
			
			if(getZip_code()!=null && getZip_code().length()>0){
				
				getThisAddress().setCountry_id(700);				
				getThisAddress().setState_id(getStateId());				
				getThisAddress().setTown_id(getTownId());
				getThisAddress().setNeighborhood_id(getNeighborhood_id());				
				getThisAddress().setZip_code(getZip_code());
			}
			
			if(isHasAddress()){	
				flag = addressService.update(getThisAddress());
			}else{
				AddressPK adpk= new AddressPK();
				adpk.setCompany_id(getThisProspectus().getProspectusPK().getCompany_id());
				adpk.setProspectus_id(getThisProspectus().getProspectusPK().getProspectus_id());
				thisAddress.setAddressPK(adpk);
				thisAddress.setAddress_type_id(1);				
				getThisAddress().getAddressPK().setAddress_id(addressService.getMaxAddressId(getThisProspectus().getProspectusPK().getProspectus_id(),getThisProspectus().getProspectusPK().getCompany_id()));
				flag = addressService.add(getThisAddress());
				setHasAddress(true);
			}
			//end SaveAddress
			if(flag)
				log.info("====>>>> Address saved successfully!!!!!!!!!!!!!!!");
			else
				log.info("====>>>> Address  NO saved !!!!!!!!!!!!!!!");		
		}
			
		public void cpFunctionAndSave() {
			RequestContext requestContext = RequestContext.getCurrentInstance();
			cpFunction();
			if(listAsents.size()!=0){
				ArrayList<ListNeighborhoodBean> catalogo= new ArrayList<ListNeighborhoodBean>();
				ListNeighborhoodBean neigt=null;
				for (NeighborhoodCat iter : listAsents) {
					neigt=new ListNeighborhoodBean(iter.getNeighborhoodCatPK().getNeighborhood_id(),iter.getName());
					catalogo.add(neigt);	
				}
				requestContext.addCallbackParam("TownName", listAsents.get(0).getDelegMunicipio().getName());
				requestContext.addCallbackParam("StateName", listAsents.get(0).getDelegMunicipio().getEstados().getName());
				try {
					requestContext.addCallbackParam("neighborhood", new JSONArray(catalogo.toArray(),true).toString());
					requestContext.addCallbackParam("isValid", true);
					updateAddressBlur();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				requestContext.addCallbackParam("isValid", false);
			}

		}
		
		
		
		public void cpFunction() {
			
			System.out.println("codigo postal");
			
			log.info("CODIGO POSTAL ES= " + getZip_code());
			listAsents = employmentService.getAsentamientosByCP(getZip_code());
			boolean bandera = true;
			for (NeighborhoodCat resultado : listAsents) {
				if (bandera) {
					setDelegMun(resultado.getDelegMunicipio().getName());
					setTownId(resultado.getDelegMunicipio().getTownCatPK()
							.getTown_id());
					setEstado(resultado.getDelegMunicipio().getEstados().getName());
					setStateId(resultado.getDelegMunicipio().getEstados()
							.getStateCatPK().getState_id());
					break;
				}
				bandera = false;
				log.info("********* " + resultado.getName());
			}

			if (listAsents.size() == 1){
				setNeighborhood_id(listAsents.get(0).getNeighborhoodCatPK()
						.getNeighborhood_id());
			}else{
				setNeighborhood_id(null);
			}
		}
		
		
		public void getRFC() {
			System.out.println("Obteniendo RFC");
			String name = "";
			if (!getThisNaturalPerson().getFirst_name().isEmpty()
					&& !getThisNaturalPerson().getFather_last_name().isEmpty()
					&& !getThisNaturalPerson().getMother_last_name().isEmpty()
					&& getThisNaturalPerson().getDate_of_birth() != null) {
				if ( getThisNaturalPerson().getMiddle_name()!=null)
					name = getThisNaturalPerson().getFirst_name().trim() + " "
							+ getThisNaturalPerson().getMiddle_name().trim();
				else
					name = getThisNaturalPerson().getFirst_name().trim();

				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

				String birthday = fm.format(getThisNaturalPerson().getDate_of_birth());

				getThisNaturalPerson().setMx_rfc(naturalPersonService.getRFC(name,
						getThisNaturalPerson().getFather_last_name().trim(), getThisNaturalPerson()
								.getMother_last_name().trim(), birthday));
			}
		}
		public void dateChangeEvent() {
			// System.out.println("handleAgendaDateChange = " + event.getDate());
			getRFCAndSave();
		}

		public void getRFCAndSave() {
			getRFC();
			addNewNaturalPerson();
		}
		public void getCURP() {
			getRFC();
			System.out.println("Obteniendo CURP");
			System.out.println("fecha de nacimiento: "
					+ getThisNaturalPerson().getDate_of_birth());
			if (!getThisNaturalPerson().getFirst_name().isEmpty()
					&& !getThisNaturalPerson().getFather_last_name().isEmpty()
					&& !getThisNaturalPerson().getMother_last_name().isEmpty()
					&& getThisNaturalPerson().getDate_of_birth() != null) {
				String estado = "";
				boolean flag = false;
				if (getThisNaturalPerson().getCitizenship()!=null&&getThisNaturalPerson().getCitizenship() == 1){
					if (getThisNaturalPerson().getState_id() != null
							&& !getThisNaturalPerson().getState_id().equals("")
							&& !getThisNaturalPerson().getState_id().equals("0")) {
						StateCatPK pk = new StateCatPK();
						pk.setCompany_id(getThisNaturalPerson().getNatPerPK()
								.getCompany_id());
						pk.setState_id(getThisNaturalPerson().getState_id());
						estado = ((StateCat) stateService.getStateById(pk))
								.getName();
						flag = true;
					} else {
						estado = "Extranjero";
						flag = true;
					}
				} else {
					estado = "Extranjero";
					flag = true;
				}
				if (flag)
					getThisNaturalPerson().setMx_curp(naturalPersonService.generaCURP(
							getThisNaturalPerson(), estado.toUpperCase()));
				
			}
			addNewNaturalPerson();
		}

	/*
	
		public void generateDate() {
			String dayStr = getDay();
			String thisMonth = getMonth();
			String yearStr = getYear();
			String thisDate = "";
			String sM = "";

			if (dayStr != null && dayStr.length() > 0 && !dayStr.equals("0")) {
				thisDate += dayStr + "/";
			} else
				return;

			if (dayStr.equals("31")) {
				if (thisMonth.equals("Febrero") || thisMonth.equals("Abril")
						|| thisMonth.equals("Junio")
						|| thisMonth.equals("Septiembre")
						|| thisMonth.equals("Noviembre")) {
					setDay("0");
					return;
				}
			}
			if (thisMonth == null)
				return;
			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) > 29) {
				setDay("0");
				return;
			}

			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) == 29
					&& (Integer.parseInt(yearStr) % 4 != 0)) {
				setDay("0");
				return;
			}

			if (thisMonth != null && thisMonth.length() > 0
					&& !thisMonth.equals("0")) {
				for (int i = 0; i < getMonthStr().length; i++) {
					if ((getMonthStr()[i]).equals(thisMonth)) {
						if ((i + 1) < 10)
							sM = "0" + (i + 1);
						else
							sM = (i + 1) + "";
					}
				}
				thisDate += sM + "/";
			} else
				return;

			if (yearStr != null && yearStr.length() > 0 && !yearStr.equals("0")) {
				thisDate += yearStr;
			} else
				return;

			SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
			try {
				getThisNaturalPerson().setDate_of_birth(fm.parse(thisDate));
				getRFCAndSave();
				getCURP();
			} catch (Exception e) {
				getThisNaturalPerson().setDate_of_birth(null);
			}

		}*/
		
		public void generateDate() {
			String dayStr = getDay();
			String thisMonth = getMonth();
			String yearStr = getYear();
			String thisDate = "";
			String sM = "";

			if (dayStr != null && dayStr.length() > 0 && !dayStr.equals("0")) {
				thisDate += dayStr + "/";
			} else{
				setDay("0");
				return;
			}

			if (dayStr.equals("31")) {
				if (thisMonth.equals("Febrero") || thisMonth.equals("Abril")
						|| thisMonth.equals("Junio")
						|| thisMonth.equals("Septiembre")
						|| thisMonth.equals("Noviembre")) {
					setDay("0");
					return;
				}
			}
			if (thisMonth == null) {
				setMonth("0");
				return;
			}
			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) > 29) {
				setDay("0");
				return;
			}

			if (thisMonth.equals("Febrero") && Integer.parseInt(dayStr) == 29
					&& (Integer.parseInt(yearStr) % 4 != 0)) {
				setDay("0");
				return;
			}

			if (thisMonth != null && thisMonth.length() > 0
					&& !thisMonth.equals("0")) {
				for (int i = 0; i < getMonthStr().length; i++) {
					if ((getMonthStr()[i]).equals(thisMonth)) {
						if ((i + 1) < 10)
							sM = "0" + (i + 1);
						else
							sM = (i + 1) + "";
					}
				}
				thisDate += sM + "/";
			} else{
				setMonth("0");
				return;
			}

			if (yearStr != null && yearStr.length() > 0 && !yearStr.equals("0")) {
				thisDate += yearStr;
			} else{
				setYear("0");
				return;
			}

			SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
			try {
				getThisNaturalPerson().setDate_of_birth(fm.parse(thisDate));
				getRFCAndSave();
				try{
					getCURP();
				}catch(Exception e){
					log.info("ERROR AL GENERAR EL CURP EN CALCULA FECHA");
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				getThisNaturalPerson().setDate_of_birth(null);
			}

		}
	
		public void saveKinship(){
			boolean flagUpdate=false;
			if(hasRelationShip){
				flagUpdate=relationShipService.updateRelationShip(getThisRelationShip());
			}
			else{
				
				
			}
			
			if(flagUpdate)
				log.info("Se actualizo correctamente relationShip");
			else
				log.info("Error al actualizar relationShip");
		}
		public void saveSameAddress(){
			boolean flagUpdate=true;
			
			if(getThisProspectus().getSame_address().equals("N")){			 
				
				if(isHasProspectus()){
					addressDisp="block";
					try {
						prospectusService.update(getThisProspectus());
						
					} catch (Exception e) {
						flagUpdate=false;
					}
				}
				else{
					log.info("]!!!!!!!!11!!!!!!!!!!!!!!!!!!  NO TIENE PROSPECTUS");
					
				}												
			
			}
			else{
				if(isHasProspectus() ){
					addressDisp="none";
					thisAddress = addressService.getMaxAddressByType(thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id(), 1);
					
					if(thisAddress!=null){
						//Eliminar el addresss de este prospecto
						try {
							addressService.removeAddress(new AddressPK(getThisAddress().getAddressPK().getAddress_id(), getThisAddress().getAddressPK().getProspectus_id(), getThisAddress().getAddressPK().getCompany_id()));
							prospectusService.update(getThisProspectus());							
						} catch (Exception e) {							
							flagUpdate=false;
						}
					}
					else{
						try {
							prospectusService.update(getThisProspectus());
							
						} catch (Exception e) {							
							flagUpdate=false;
						}
					}
				
			}
				else
					log.info("No hay prospectus");
				
			}
			
			
			
			if(flagUpdate)
				log.info("Se actualizo correctamente prospectus");
			else
				log.info("Error al actualizar prospectus");
		}
	
		public void selectEconomicActivity() {
			BusinessPK pk = new BusinessPK();
			pk.setCompany_id(getThisNaturalPerson().getNatPerPK().getCompany_id());
			pk.setProspectus_id(getThisNaturalPerson().getNatPerPK().getProspectus_id());
			pk.setBusiness_id(1);
			businessService.remove(pk);

			EmploymentPK epk = new EmploymentPK();
			epk.setCompany_id(getThisNaturalPerson().getNatPerPK().getCompany_id());
			epk.setProspectus_id(getThisNaturalPerson().getNatPerPK().getProspectus_id());
			epk.setEmployment_id(1);
			employmentService.removeEmployment(epk);
			
			IncomePK incomePk1 = new IncomePK(1,getThisProspectus().getProspectusPK().getProspectus_id(),getThisProspectus().getProspectusPK().getCompany_id());
			IncomePK incomePk2 = new IncomePK(2,getThisProspectus().getProspectusPK().getProspectus_id(),getThisProspectus().getProspectusPK().getCompany_id());
			IncomePK incomePk3 = new IncomePK(3,getThisProspectus().getProspectusPK().getProspectus_id(),getThisProspectus().getProspectusPK().getCompany_id());
			
			incomeService.removeIncome(incomePk1);
			incomeService.removeIncome(incomePk2);
			incomeService.removeIncome(incomePk3);
			
			switch (getEconomicActivity()) {
			case 1:
				setBusinessDisp("block");
				setEmployDisp("none");
				break;
			case 2:
				setEmployDisp("block");
				setBusinessDisp("none");
				break;
			case 3:
				setBusinessDisp("block");
				setEmployDisp("block");
				break;
			default:
				break;
			}
			if (getEconomicActivity() == 1 || getEconomicActivity() == 3
					|| getEconomicActivity() == 4 || getEconomicActivity() == 5) {
				Business b = new Business();
				pk = new BusinessPK();
				pk.setCompany_id(getThisNaturalPerson().getNatPerPK().getCompany_id());
				pk.setProspectus_id(getThisNaturalPerson().getNatPerPK().getProspectus_id());
				pk.setBusiness_id(1);
				b.setBusinessPK(pk);
				businessService.save(b);
				setThisBusiness(businessService.loadSelectedBusiness(new BusinessPK(1, thisProspectus.getProspectusPK().getCompany_id(),thisProspectus.getProspectusPK().getProspectus_id())));				
				setHasBusiness(true);
			}
			if (getEconomicActivity() == 2 || getEconomicActivity() == 3
					|| getEconomicActivity() == 4 || getEconomicActivity() == 6) {
				Employment b = new Employment();
				epk = new EmploymentPK();
				epk.setCompany_id(getThisNaturalPerson().getNatPerPK().getCompany_id());
				epk.setProspectus_id(getThisNaturalPerson().getNatPerPK().getProspectus_id());
				epk.setEmployment_id(1);
				b.setEmploymentPK(epk);
				employmentService.add(b);
				setThisEmployment(employmentService.getEmploymentById(new EmploymentPK(1,thisProspectus.getProspectusPK().getCompany_id(),thisProspectus.getProspectusPK().getProspectus_id())));
				setHasEmployment(true);
			}

			getThisNaturalPerson().setEconomic_activity_id(getEconomicActivity());
			addNewNaturalPerson();
			
		}
		
		
		public void updatePhoneProspectus() {

			System.out.println("====>>>>updatePhoneProspectus()" );
			System.out.println("====>>>>LADA FIXED= " + getLadaFixedProspectus());
			System.out.println("====>>>>PHONE FIXED= " + getPhoneFixedPropectus());
			boolean flagSave = false;
			boolean flagUpdate = false;

			if (isHasPhoneFixedProspectus()) {
				if ( getPhoneFixedPropectus() != null ) {
					getThisPhoneFixed().setPhone_number(getPhoneFixedPropectus());
					flagUpdate = phoneService.updatePhone(getThisPhoneFixed());
				}
			} else {
				if (getPhoneFixedPropectus() != null) {
					getThisPhoneFixed().setPhone_number(  getPhoneFixedPropectus());
					PhonePK phonePk = new PhonePK(getThisProspectus().getProspectusPK()
							.getProspectus_id(), getThisProspectus().getProspectusPK()
							.getCompany_id());
					getThisPhoneFixed().setOwned("1");
					getThisPhoneFixed().setArea(getThisProspectus().getArea());
					getThisPhoneFixed().setPhonePk(phonePk);
					getThisPhoneFixed().setPhone_type_id(5);//
					flagSave = phoneService.addPhone(getThisPhoneFixed(),
							getThisProspectus().getProspectusPK().getProspectus_id(),
							getThisProspectus().getProspectusPK().getCompany_id());
					setHasPhoneFixedProspectus(true);
				}
			}
			if (flagSave)
				System.out.println("====>>>> Phone fixed employment saved successfully");
			else
				System.out.println("====>>>> Phone fixed employment  NO saved");
			if (flagUpdate)
				System.out.println("====>>>> Update phone fixed employment successfully");
			else
				System.out.println("====>>>> Phone Fixed employment  NO update");
		}

		public void updatePhoneCellProspectus() {

			System.out.println("====>>>> updatePhoneCellProspectus()" + getLadaCelProspectus());
			
			System.out.println("====>>>>LADA CEL FIXED= " + getLadaCelProspectus());
			System.out.println("====>>>>PHONE CEL FIXED= " + getPhoneCellProspectus());
			boolean flagSave = false;
			boolean flagUpdate = false;

			if (isHasPhoneCell()) {
				if (getPhoneCellProspectus() != null) {
					getThisPhoneCell().setPhone_number( getPhoneCellProspectus());
					flagUpdate = phoneService.updatePhone(getThisPhoneCell());
				}
			} else {
				if ( getPhoneCellProspectus() != null ) {
					getThisPhoneCell().setPhone_number( getPhoneCellProspectus() );

					PhonePK phonePk = new PhonePK(getThisProspectus().getProspectusPK()
							.getProspectus_id(), getThisProspectus().getProspectusPK()
							.getCompany_id());
					getThisPhoneCell().setPhonePk(phonePk);
					getThisPhoneCell().setPhone_type_id(6);
					getThisPhoneCell().setOwned("1");
					getThisPhoneCell().setArea(getThisProspectus().getArea());
					flagSave = phoneService.addPhone(getThisPhoneCell(), getThisProspectus()
							.getProspectusPK().getProspectus_id(), getThisProspectus()
							.getProspectusPK().getCompany_id());
					setHasPhoneCell(true);
				}
			}
			if (flagSave)
				System.out.println("====>>>> Phone fixed employment saved successfully");
			else
				System.out.println("====>>>> Phone fixed employment  NO saved");
			if (flagUpdate)
				System.out.println("====>>>> Update phone fixed employment successfully");
			else
				System.out.println("====>>>> Phone Fixed employment  NO update");

		}
		
		public List<String> autocomplete(String query) {

			List<BmxEconActivityCat> results = new ArrayList<BmxEconActivityCat>();
			List<String> lista = new ArrayList<String>();
			lista.removeAll(results);
			results = employmentService.searchActivityList(query);
			log.info("Tamaño result= " + results.size());
			for (BmxEconActivityCat resultado : results) {
				lista.add(resultado.getDescription());
			}

			return lista;

		}

		public BmxEconActivityCat getActivity(String query) {

			List<BmxEconActivityCat> results = new ArrayList<BmxEconActivityCat>();
			List<String> lista = new ArrayList<String>();
			lista.removeAll(results);
			results = employmentService.searchActivityList(query);
			log.info("Tamaño result= " + results.size());
			return results.get(0);

		}

		

		public void handleSelect(SelectEvent event) {
			String val = event.getObject().toString();
			String attr = (String) event.getComponent().getAttributes()
					.get("activity").toString();

			System.out.println("elemento selectionado: " + val + "   nivel: "
					+ attr);

			BmxEconActivityCat b = getActivity(val);

			BusinessPK pk = new BusinessPK();
			pk.setProspectus_id(getThisNaturalPerson().getNatPerPK().getProspectus_id());
			pk.setBusiness_id(Integer.parseInt(attr));
			pk.setCompany_id(getThisNaturalPerson().getNatPerPK().getCompany_id());
			Business bs = businessService.loadSelectedBusiness(pk);							
			InegiEconActivityCatPK apk = new InegiEconActivityCatPK();
			System.out.println("Actividad: " + b.getDescription() + "   id: "
					+ b.getInegi_activity_id());
			apk.setInegi_econ_activity_id(b.getInegi_activity_id());
			apk.setCompany_id(1);
			InegiEconActivityCat a = employmentService.searchActivitySector(apk);
			bs.setEcon_sector_id(a.getSector_econ().getEconSectorCatPK()
					.getEcon_sector_id());
			bs.setInegi_econ_activity_id(b.getInegi_activity_id());
			
			bs.setBmx_econ_activity_id(b.getBmxEconActivityCatPK().getBmx_econ_activity_id());
			
			businessService.update(bs);

			// log.info("ACTIVIDAD************=");
			// setFieldUpdate(inegiEconAct.getDescription());
			// setSectorUpdate(inegiEconAct.getSector_econ().getDescription());
		}

		public void updateOrSaveIncome(){
			boolean flagSave=false;
			boolean flagUpdate=false;
			System.out.println("<<<<<<<<<<<<<<<<< Type INCOME ID= "+getTypeIncomeID());
			
			if(getTypeIncomeID() != 3){
				setThisIncome(incomeService.getIncomeByTypeIncomeID(thisProspectus.getProspectusPK().getProspectus_id(),thisProspectus.getProspectusPK().getCompany_id(), getTypeIncomeID()));
				
			}else {
				setThisIncome(incomeService.getIncomeByTypeIncomeID(session.getProspectus_id(),session.getCompany_id(), getTypeIncomeID()));

			}
			
			if(getThisIncome()==null){
				//insertar
				setThisIncome(new Income());
				
				if( thisProspectus != null && thisProspectus.getProspectusPK() != null ){
				
					IncomePK incomePk=new IncomePK(thisProspectus.getProspectusPK().getProspectus_id(),thisProspectus.getProspectusPK().getCompany_id());
					
					switch (getTypeIncomeID()) {
						case 1:	
							if(hasEmployment){
							getThisIncome().setIncomePk(incomePk);
							getThisIncome().setAmmount(getAmmountWagesSal());
							getThisIncome().setIncome_type_id(getTypeIncomeID());
							flagSave=incomeService.addIncome(getThisIncome(), thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id());
							}
							break;
						case 2:	
							if(hasBusiness){
							getThisIncome().setIncomePk(incomePk);
							getThisIncome().setAmmount(getAmmountBusCom());
							getThisIncome().setIncome_type_id(getTypeIncomeID());
							flagSave=incomeService.addIncome(getThisIncome(), thisProspectus.getProspectusPK().getProspectus_id(), thisProspectus.getProspectusPK().getCompany_id());					
							}
							break;
	
						case 3:	
							
							IncomePK incomePk2=new IncomePK(session.getProspectus_id(),session.getCompany_id());
							
							getThisIncome().setIncomePk(incomePk2);
							getThisIncome().setAmmount(getAmmountOtherFam());
							getThisIncome().setIncome_type_id(getTypeIncomeID());
							flagSave=incomeService.addIncome(getThisIncome(),session.getProspectus_id(), session.getCompany_id());
							break;				
						default:
							break;
					}
				
				}

			}
			else{
				//Actualiza
				switch (getTypeIncomeID()) {
					case 1:
						getThisIncome().setAmmount(getAmmountWagesSal());
						flagUpdate=incomeService.updateIncome(getThisIncome());
						break;
					case 2:
						getThisIncome().setAmmount(getAmmountBusCom());
						flagUpdate=incomeService.updateIncome(getThisIncome());
						break;
					case 3:
						getThisIncome().setAmmount(getAmmountOtherFam());
						System.out.println("" + getThisIncome().getIncomePk().getIncome_id()+" - "+getThisIncome().getIncomePk().getProspectus_id()+ " - "+getThisIncome().getIncomePk().getCompany_id()+" - " +getThisIncome().getIncome_type_id()+" - "+getThisIncome().getAmmount());
						
						flagUpdate=incomeService.updateIncome(getThisIncome());
						break;			
					default:
						break;
				}			
				
			}
			if(flagSave)
				System.out.println("====>>>> Income saved successfully");
			else
				System.out.println("====>>>> Income  NO saved");	
			if(flagUpdate)
				System.out.println("====>>>> Update Income successfully");
			else
				System.out.println("====>>>> Income  NO update");
			
		}
		
		protected final boolean isSesion_DISABLED( FacesContext faces )
		{
			boolean bandera = false;
			String url = "";
			
			if(session.getProspectus_id() == null || session.getCompany_id() == null)
			{	
				try 
				{
				
					ExternalContext context = faces.getExternalContext();
					
					url = (getPath(context) + "/Portal/sesion-expirada.xhtml?redirecFrom=AddNaturalPersonOtherFamily");
					
					System.out.println( "Redirigiendo desde AddNaturalPersonOtherFamily: " + url);
					
					context.redirect(url);
				        
				} catch (IOException ex) {						      
					ex.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Redirect "+url);
				}
				
				bandera = true;
			}
			
			return bandera;
		}
		
		private String getPath( ExternalContext external )
		{
			HttpServletRequest request = (HttpServletRequest) external.getRequest();
			
			return request.getContextPath();
		}

}
