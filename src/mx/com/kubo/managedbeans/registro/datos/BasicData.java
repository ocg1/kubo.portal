package mx.com.kubo.managedbeans.registro.datos;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.RequestShortScore;
import mx.com.kubo.bean.ResponseShortScore;
import mx.com.kubo.bean.ValBusiness;
import mx.com.kubo.controller.ObtieneConsultaCorta;
import mx.com.kubo.controller.behaviorProspectus.BehaviorCheck;
import mx.com.kubo.controller.hs_connect.HubSpotController;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.managedbeans.HeaderBean;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.managedbeans.Simulator;
import mx.com.kubo.managedbeans.navigation.NavigationBeanIMP;
import mx.com.kubo.managedbeans.registro.consulta.Preaprobacion;
import mx.com.kubo.mesa.buro.ProspectRiskIMP;
import mx.com.kubo.mesa.buro.ProyectLoanCreatorIMP;
import mx.com.kubo.model.Address;
import mx.com.kubo.model.BmxEconActivityCat;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.ConsultingManual;
import mx.com.kubo.model.ConsultingManualPK;
import mx.com.kubo.model.ContactWayProspectus;
import mx.com.kubo.model.ContactWayProspectusPK;
import mx.com.kubo.model.CreditHistoryAttempt;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.InegiEconActivityCat;
import mx.com.kubo.model.InegiEconActivityCatPK;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.Prospectus;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.Stackholder_relationship;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.gnNaturalPersonPK;
import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.registro.verificacion.ProspectoDuplicadoIMP;
import mx.com.kubo.services.mesa.solicitud.notas.NotesService;
import mx.com.kubo.tools.Utilities;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "basicData") @ViewScoped
public final class BasicData extends BasicDataPMO
implements Serializable, BasicDataIMO
{	
	private static final long serialVersionUID = -7601870717643478541L;

	@PostConstruct
	public final void init() 
	{						
		faces = FacesContext.getCurrentInstance();
		
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		external = faces.getExternalContext();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");	
		
		service_prospecto_duplicado = new ProspectoDuplicadoIMP();
		
		if(isSesion_DISABLED()){
			return;
		}
		
		navigation_bean = (NavigationBeanIMP) resolver.getValue(context, null, "navigationBean");
		simulator = (Simulator) resolver.getValue(context, null, "simulator");
		
		protectorValid	= false;
		consultValid    = false;		
		
		System.setProperty("java.awt.headless", "true");
				
		init_sesion();
		init_catalogos();						
		init_pais_origen();	
		init_prospectus();					
		init_person_type();	
		init_membership();			
		init_natural_person();
		
		init_contrasena_segura();
		
		init_simulador();
		init_datos_personales();
		init_CURP_generator();
		
		init_coverage_zone_ENABLED();				
		init_domicilio();
		init_domicilio_fiscal();					
		init_domicilio_fiscal_ENABLED();	
		
		init_foto();						
		init_actividad_economica();
		init_empleo();
		init_inversionista();									
		init_computer_INFO();
		init_telefonos();
		
		validaConsulta();
				
		init_lista_actividad_economica();
		
		init_Contract_Way_Prospectus_List();
		
		password_ENABLED  = false;
		
		tmp = "inicio";
		
	}	

	public final void init_address_type(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		person_type = input_text.getValue().toString().charAt(0);
		
		init_person_type();
		
		domicilio.setAddress_type_id(address_type_id);
		domicilio.setResidence_ENABLED(residence_ENABLED);
		domicilio.init();
		
		if(legal_address_ENABLED)
		{
			fiscal.init();
		}
		
		init_domicilio_fiscal_ENABLED();
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("legal_address_ENABLED", legal_address_ENABLED);
		request.addCallbackParam("fiscal_ENABLED", fiscal_ENABLED);
	}
	
	public final void init_domicilio(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();		

		init_domicilio_fiscal_ENABLED();
				
		request.addCallbackParam("is_legal_address", is_legal_address);	
		request.addCallbackParam("fiscal_ENABLED", fiscal_ENABLED);
	}
	
	public final void init_neighborhood_text_ENABLED(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		String ajax_TOKEN = input_text.getValue().toString();
		
		if(ajax_TOKEN.length() > 5)
		{		
			neighborhood_text_ENABLED = new Boolean(ajax_TOKEN.split("::")[0]);
							
			address_type_id = Integer.parseInt(ajax_TOKEN.split("::")[1]);
			
		} else {
			
			neighborhood_text_ENABLED = false;
			
			address_type_id = -1;
		}
		
		switch(address_type_id)
		{
			case DOMICILIO_CASA:
			case DOMICILIO_EMPRESA:
				domicilio.setNeighborhood_text_ENABLED(neighborhood_text_ENABLED);
			break;
			
			case DOMICILIO_FISCAL:
				fiscal.setNeighborhood_text_ENABLED(neighborhood_text_ENABLED);
			break;
			
			default: break;
		}
		
		request.addCallbackParam("address_type_id", address_type_id);
		request.addCallbackParam("neighborhood_text_ENABLED", neighborhood_text_ENABLED);
	}
	
	public final void init_legal_address(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_radio = (HtmlSelectOneRadio) event.getComponent();
		
		is_legal_address = select_radio.getValue().toString();
		
		if(is_legal_address.equals("S"))
		{
			fiscal.init_copy(domicilio);
			fiscal.init();
			
			save_address_OK = fiscal.isSave_address_OK();
			
			fiscal_ENABLED = false;
			
		} else {
			
			fiscal.init_reset();
			fiscal.init();
			
			save_address_OK = fiscal.isSave_address_OK();
			
			fiscal_ENABLED = true;
		}
		
		request.addCallbackParam("is_legal_address", is_legal_address);
		request.addCallbackParam("fiscal_ENABLED", fiscal_ENABLED);
		request.addCallbackParam("save_address_OK", save_address_OK);
	}
	
	public final void init_CURP_generator(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		naturalPerson = service_natural_person.getNaturalPersonById(npPK);
		
		generator.setPerson(naturalPerson);
		generator.init_RFC();
		generator.init_CURP();
		
		naturalPerson = generator.getPerson();
		
			   name.setPerson(naturalPerson);
		     gender.setPerson(naturalPerson);
		   birthday.setPerson(naturalPerson);
		citizenship.setPerson(naturalPerson);
		    country.setPerson(naturalPerson);		
		      state.setPerson(naturalPerson);		       		   
		
		request.addCallbackParam("generator_OK", true);
	}

	public void saveAccount()
	{
		if(listAccount != null )
		{
			
			SavingAccount account= listAccount.get(0);
			account.setDescription(getDescriptioAccount());
			accountService.updateSavingAccount(account);
			
		}else{
			
			SavingAccountPK accountPk=new SavingAccountPK(naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id());					
			SavingAccount account=new SavingAccount();
			account.setSaving_accountPk(accountPk);
			//account.setDescription("Cuenta con mucho dinero");
			account.setStatus(0);
			accountService.addSavingAccount(account, naturalPerson.getNatPerPK().getProspectus_id(), naturalPerson.getNatPerPK().getCompany_id());
			
		}
	}

	public void getListBusiness() {
		
		BusinessPK pk = new BusinessPK();
		pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		pk.setBusiness_id(1);
		List<Business> listB = businessservice.getListBusinessByProspect(pk.getProspectus_id(), pk.getCompany_id());
		if(listB!=null && listB.size()>0){
			setDisplaySector("block");
			
			//listBusiness = new ArrayList<ValBusiness>();
			
			BmxEconActivityCat bmx = null;
			if(listB.get(0).getBmx_econ_activity_id() != null)
				bmx = businessservice.findBmxActivityById(listB.get(0).getBmx_econ_activity_id(), listB.get(0).getBusinessPK().getCompany_id());
			
				ValBusiness val  = new ValBusiness();
				val.setItem("1");
				if(bmx != null){
					setBusinessName(bmx.getDescription());
				}
				//listBusiness.add(val);
			

			
		}
		
		//return listBusiness;
	}
	
	public void setHasComputer() {
		log.info("!!! setHasComputer ¡¡¡");

		Integer hasCompHome = 0;
		Integer hasCompEmp = 0;
		Integer hasCompBusi = 0;

		for (String s : selectedComputer) {

			if (s.equals("1")) {
				hasCompHome = 1;
			}
			if (s.equals("2")) {
				hasCompEmp = 1;
			}
			if (s.equals("3")) {
				hasCompBusi = 1;
			}
		}
		naturalPerson.setHas_computer_business(hasCompBusi);
		naturalPerson.setHas_computer_employment(hasCompEmp);
		naturalPerson.setHas_computer_home(hasCompHome);
		saveData();

	}
	
	public void setHasInternet() {
		log.info("!!! setHasInternet ¡¡¡");
		Integer hasIntHome = 0;
		Integer hasIntEmp = 0;
		Integer hasIntBusi = 0;

		for (String s : selectedInternet) {

			if (s.equals("1")) {
				hasIntHome = 1;
			}
			if (s.equals("2")) {
				hasIntEmp = 1;
			}
			if (s.equals("3")) {
				hasIntBusi = 1;
			}
		}
		naturalPerson.setHas_internet_business(hasIntBusi);
		naturalPerson.setHas_internet_employment(hasIntEmp);
		naturalPerson.setHas_internet_home(hasIntHome);
		saveData();

	}
	
	public void saveMembership() {
		log.info("!!! saveNickname ¡¡¡");
		membershipService.update(getMembership());
	}
		
	public void selectlegalstatus() {
		System.out.println("select Legal Status");
		if (naturalPerson.getLegal_status_id() == 2) {
			setMaritalstatusdis("none");
		} else {
			setMaritalstatusdis("block");
			naturalPerson.setMarital_status_id(null);
		}
		/*
		 * getNaturalPerson().setLegal_status_id(getLegalstatus());
		 * getNaturalPerson().setMarital_status_id(getMaritalstatus());
		 */
		saveData();
	}
	
	public void selectEconomicActivity() {
		/*
		 * log.info("Obteniendo Actividad Economica: "+getEconomicActivity());
		 * setHasBusiness("none"); setHasEmployment("none");
		 * setMoreBusiness("none"); setMoreEmployment("none");
		 * setDisplaySector("none"); setUniqueBusiness("");
		 * setUniqueEmployment("");
		 * if(getEconomicActivity()==1||getEconomicActivity
		 * ()==3||getEconomicActivity()==4||getEconomicActivity()==5){
		 * setHasBusiness("block"); }
		 * if(getEconomicActivity()==2||getEconomicActivity
		 * ()==3||getEconomicActivity()==4||getEconomicActivity()==6){
		 * setHasEmployment("block"); }
		 */
		
		
		
		
		BusinessPK pk = new BusinessPK();
		pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		pk.setBusiness_id(1);
		businessservice.remove(pk);
		setDisplaySector("none");
		
		setEmploymentName("");
		setBusinessName("");
		
		setHasEmployment(false);
		setHasBusiness(false);

		EmploymentPK epk = new EmploymentPK();
		epk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		epk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		epk.setEmployment_id(1);

		employmentService.removeEmployment(epk);

		if (getEconomicActivity() == 1 || getEconomicActivity() == 3
				|| getEconomicActivity() == 4 || getEconomicActivity() == 5) {
			
			Business b = new Business();
			pk = new BusinessPK();
			pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			pk.setBusiness_id(1);
			b.setBusinessPK(pk);
			businessservice.save(b);
			
			setDisplaySector("block");
			
			setHasBusiness(true);
			
		}
		if (getEconomicActivity() == 2 || getEconomicActivity() == 3
				|| getEconomicActivity() == 4 || getEconomicActivity() == 6) {
			Employment b = new Employment();
			epk = new EmploymentPK();
			epk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			epk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			epk.setEmployment_id(1);
			b.setEmploymentPK(epk);
			employmentService.add(b);
			setDisplaySector("block");
			setHasEmployment(true);
		}

		if( getEconomicActivity() != 0  ){
			
			naturalPerson.setEconomic_activity_id(getEconomicActivity());
			saveData();
		}
	}
		
	public void uniqueEmployment() {
		setMoreEmployment("none");
		setDisplayEmployed("none");
		if (getUniqueEmployment().equals("1")) {
			setMoreEmployment("none");
			setDisplayEmployed("block");
		}
		if (getUniqueEmployment().equals("0")) {
			setMoreEmployment("block");
			setDisplayEmployed("none");
		}
	}
	
	public void uniqueBusinessFunc() {
		System.out.println("validando more business");
		setMoreBusiness("none");
		setDisplaySector("none");
		if (getUniqueBusiness().equals("1")) {
			setMoreBusiness("none");
			setDisplaySector("none");
			setMoreBusinessStr("1");
			createBusiness();
		}
		if (getUniqueBusiness().equals("0")) {
			setMoreBusiness("block");
			setDisplaySector("none");
		}
	}
	
	public void createBusiness() {
		
			Business b = new Business();
			BusinessPK pk = new BusinessPK();
			pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			pk.setBusiness_id(1);
			b.setBusinessPK(pk);
			
			businessservice.save(b);

			setDisplaySector("block");
		
			setHasBusiness(true);

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
	
	public String getCompleteName() 
	{
		if (naturalPerson != null)
		{
			if (naturalPerson.getFirst_name() != null
			 && naturalPerson.getFather_last_name() != null
			&& !naturalPerson.getFirst_name().isEmpty()
			&& !naturalPerson.getFather_last_name().isEmpty()) 
			{

				if (naturalPerson.getMiddle_name()!=null && !naturalPerson.getMiddle_name().isEmpty())
				{
					completeName = naturalPerson.getFirst_name().trim() + " " + naturalPerson.getMiddle_name().trim();
					
				} else {
					
					completeName = naturalPerson.getFirst_name().trim();
				}

				completeName += " " + naturalPerson.getFather_last_name().trim();
				
				if (naturalPerson.getMother_last_name()!=null&&!naturalPerson.getMother_last_name().isEmpty())
				{
					completeName += " " + naturalPerson.getMother_last_name().trim();
				}
			}
		}
		
		return completeName;
	}
	
	public void validaItemSelect(ValueChangeEvent e){
		String val = e.getNewValue().toString();
		String attr = (String) e.getComponent().getAttributes()
				.get("activity").toString();
		System.out.println("elemento selectionado ValueChangeItem: " + val +"   ittem: "+attr);
		
		BmxEconActivityCat b = getActivity(val);
		
		if(b==null){
			
			BusinessPK pk = new BusinessPK();
			pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
			pk.setBusiness_id(Integer.parseInt(attr));
			pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
			businessservice.remove(pk);
			
		}
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addPartialUpdateTarget("moreBusiness");
		
	}
	
	public void handleSelect(SelectEvent event) {
		String val = event.getObject().toString();
//		String attr = (String) event.getComponent().getAttributes()
//				.get("activity").toString();
		
		String activityType = (String) event.getComponent().getAttributes()
				.get("activityType").toString();

//		System.out.println("elemento selectionado: " + val + "   nivel: "
//				+ attr);

		BmxEconActivityCat b = getActivity(val);
		if(b!=null){
			
			InegiEconActivityCatPK apk = new InegiEconActivityCatPK();
			System.out.println("Actividad: " + b.getDescription() + "   id: "
					+ b.getInegi_activity_id());
			apk.setInegi_econ_activity_id(b.getInegi_activity_id());
			apk.setCompany_id(1);
			InegiEconActivityCat a = employmentService.searchActivitySector(apk);
		
			if (activityType!= null && activityType.equals("business")) {
				
		
				BusinessPK pk = new BusinessPK();
				pk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				pk.setBusiness_id(1);
				pk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				Business bs = businessservice.loadSelectedBusiness(pk);
		
				
				if(bs!= null){
				
					bs.setEcon_sector_id(a.getSector_econ().getEconSectorCatPK()
							.getEcon_sector_id());
					bs.setInegi_econ_activity_id(b.getInegi_activity_id());
					
					bs.setBmx_econ_activity_id(b.getBmxEconActivityCatPK().getBmx_econ_activity_id());
					
					businessservice.update(bs);
				
				}
				
			}
			if ( activityType!= null && activityType.equals("employment") ) {
			
				EmploymentPK epk = new EmploymentPK();
					epk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
					epk.setEmployment_id(1);
					epk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				Employment emp = employmentService.getEmploymentById(epk);
				
				if(emp!= null){
					
					emp.setEcon_sector_id(a.getSector_econ().getEconSectorCatPK()
							.getEcon_sector_id());
					emp.setInegi_econ_activity_id(b.getInegi_activity_id());
					
					emp.setBmx_econ_activity_id(b.getBmxEconActivityCatPK().getBmx_econ_activity_id());
					
					employmentService.updateEmployment( emp );
				
				}
				
			}
			
		}

		// log.info("ACTIVIDAD************=");
		// setFieldUpdate(inegiEconAct.getDescription());
		// setSectorUpdate(inegiEconAct.getSector_econ().getDescription());
	}
	
	public final void saveData() 
	{
		service_natural_person.update(naturalPerson);
		
		prospectus_id  = naturalPerson.getNatPerPK().getProspectus_id();
		company_id     = naturalPerson.getNatPerPK().getCompany_id();	
		
		boolean flagUpdate = false;
		
		if(prospectus.getArea() == 'I')
		{
			service_prospectus.update(prospectus);
			
			flagUpdate = true;
		}
		
		if(flagUpdate)
		{
			log.info("Se actualizo prospectus");
			
		} else {
			
			log.info("Error al actualizar prospectus");
		}
	}
	
	public void upload() {
		if (file != null) {
			try {

				copyFile(getFile().getFileName(), getFile().getInputstream());
			} catch (IOException io) {
				io.printStackTrace();
			}
		}
	}
	
	public void changePicture(FileUploadEvent event)
	{
		file=event.getFile();
		upload();
	}
		
	public void cargaFoto(FileUploadEvent event) 
	{

		ExternalContext extContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		try {
			copyFile(extContext.getRealPath("//resources//img//"
					+ event.getFile().getFileName()), event.getFile()
					.getInputstream());
			FacesMessage msg = new FacesMessage("Descripcion del archivo",
					"Nombre: " + event.getFile().getFileName()
							+ "<br/>Tamaño: " + event.getFile().getSize()
							/ 1024 + " Kb<br/>content type: "
							+ event.getFile().getContentType()
							+ "<br/><br/>The file was uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/*		
	public void updatePhoneProspectus() {

		log.info("====>>>>LADA FIXED= " + getLadaFixedProspectus());
		log.info("====>>>>PHONE FIXED= " + getPhoneFixedPropectus());
		boolean flagSave = false;
		boolean flagUpdate = false;

		if (isHasPhoneFixedProspectus()) {
			if (getPhoneFixedPropectus() != null) {
				getThisPhoneFixed().setPhone_number(
						 getPhoneFixedPropectus()
						 );
				flagUpdate = phoneService.updatePhone(getThisPhoneFixed());
			}
		} else {
			if (getPhoneFixedPropectus() != null) {
				getThisPhoneFixed().setPhone_number(
						 getPhoneFixedPropectus());
				PhonePK phonePk = new PhonePK(prospectus.getProspectusPK()
						.getProspectus_id(), prospectus.getProspectusPK()
						.getCompany_id());
				getThisPhoneFixed().setOwned("1");
				getThisPhoneFixed().setArea(prospectus.getArea());
				getThisPhoneFixed().setPhonePk(phonePk);
				getThisPhoneFixed().setPhone_type_id(5);
				flagSave = phoneService.addPhone(getThisPhoneFixed(),
						prospectus.getProspectusPK().getProspectus_id(),
						prospectus.getProspectusPK().getCompany_id());
				setHasPhoneFixedProspectus(true);
			}
		}
		if (flagSave)
			log.info("====>>>> Phone fixed employment saved successfully");
		else
			log.info("====>>>> Phone fixed employment  NO saved");
		if (flagUpdate)
			log.info("====>>>> Update phone fixed employment successfully");
		else
			log.info("====>>>> Phone Fixed employment  NO update");
	}
	
	public void updatePhoneCellProspectus() {

		log.info("====>>>>LADA CEL FIXED= " + getLadaCelProspectus());
		log.info("====>>>>PHONE CEL FIXED= " + getPhoneCellProspectus());
		boolean flagSave = false;
		boolean flagUpdate = false;

		if (isHasPhoneCell()) {
			if (getPhoneCellProspectus() != null) {
				getThisPhoneCell().setPhone_number(
						getPhoneCellProspectus());
				flagUpdate = phoneService.updatePhone(getThisPhoneCell());
			}
		} else {
			if (getPhoneCellProspectus() != null) {
				
				getThisPhoneCell().setPhone_number( getPhoneCellProspectus() );

				PhonePK phonePk = new PhonePK(prospectus.getProspectusPK()
						.getProspectus_id(), prospectus.getProspectusPK()
						.getCompany_id());
				
				getThisPhoneCell().setPhonePk(phonePk);
				getThisPhoneCell().setPhone_type_id(6);
				getThisPhoneCell().setOwned("1");
				getThisPhoneCell().setArea(prospectus.getArea());
				
				flagSave = phoneService.addPhone(getThisPhoneCell(), prospectus
						.getProspectusPK().getProspectus_id(), prospectus
						.getProspectusPK().getCompany_id());
				setHasPhoneCell(true);
			}
		}
		
		if (flagSave){
		
			log.info("====>>>> Phone fixed employment saved successfully");
			actualizaPhoneInfusion( getPhoneCellProspectus() );
			
		
		}else{
		
			log.info("====>>>> Phone fixed employment  NO saved");
		
		}
		
		if (flagUpdate){
		
			log.info("====>>>> Update phone fixed employment successfully");
			actualizaPhoneInfusion( getPhoneCellProspectus() );
		
		}else{
			
			log.info("====>>>> Phone Fixed employment  NO update");
			
		}

	}
*/	
	
	public void guardaPrimerNombre(AjaxBehaviorEvent event)
	{
		
		faces  = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		String first_name       = naturalPerson.getFirst_name()        == null ? "" : naturalPerson.getFirst_name();	
		String father_last_name = naturalPerson.getFather_last_name()  == null ? "" : naturalPerson.getFather_last_name();
		
		name.init_first_name(event);
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		sesion.setNombre(first_name +  " " + father_last_name);
		
		header = (HeaderBean) resolver.getValue(context, null, "headerBean");
		
		header.setName(sesion.getNombre());
		
		name.init_first_name(event);
		
		saveFullName();
		
		actualizaDatosInfusion(  );
		
	}
	
	public void guardaTipoVivienda (AjaxBehaviorEvent event)
	{
		
		domicilio.setPerson(naturalPerson);
		domicilio.init_residence_id(event);
		
	}
	
	public void guardaMiddleName (AjaxBehaviorEvent event)
	{
		
		name.init_middle_name(event);
		
		saveFullName();
		
		actualizaDatosInfusion(  );
		
	}
	
	public void guardaApellidoMaterno(AjaxBehaviorEvent event)
	{
		
		name.init_mother_last_name(event);
		
		saveFullName();
		
		actualizaDatosInfusion(  );
		
	}
	
	public void guardaApellidoPaterno(AjaxBehaviorEvent event)
	{
		
		faces  = FacesContext.getCurrentInstance();
		resolver = faces.getApplication().getELResolver();
		context  = faces.getELContext();
		
		String first_name       = naturalPerson.getFirst_name()       == null ? "" : naturalPerson.getFirst_name();	
		String father_last_name = naturalPerson.getFather_last_name() == null ? "" : naturalPerson.getFather_last_name();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
		
		sesion.setNombre(first_name +  " " + father_last_name);
		
		header = (HeaderBean) resolver.getValue(context, null, "headerBean");
		
		header.setName(sesion.getNombre());
		
		name.init_father_last_name(event);
		
		saveFullName();
		
		actualizaDatosInfusion();		
		
		/*if( fullname == null ){
			
			fullname = new FullName();
			
			fullname.setPk(fpk);
			fullname.setEmail( membership.getEmail());
			fullname.setFull_name(fullnameStr);
			fullname.setArea(naturalPerson.getProspectus().getArea().toString());
			fullnameservice.saveFullName(fullname);
			
		}else{
			fullname.setEmail( membership.getEmail());
			fullname.setArea(naturalPerson.getProspectus().getArea().toString());
			fullname.setFull_name(fullnameStr);
			fullnameservice.updateFullName(fullname);
			
		}*/
		
	}
	/*
	private void actualizaPhoneInfusion( String phonestr ){
	
		try{
			
			Integer contactId = naturalPerson.getProspectus().getInfusion_id();
			
			if( contactId != null ){
			
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
				
				 SystemParam system_param_I = systemParamService .loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
				 
					InfusionSoft infusion = new InfusionSoft();
					infusion.actualizaTelefonoContacto(contactId, phonestr);
						
				 }
			 
			}
		 
		}catch( Exception e ){
			
			e.printStackTrace();
			
		}
	
	}
	
	private void actualizaDatosInfusion(  ){
	
		try{
			
			String first_name       = naturalPerson.getFirst_name()       == null ? "" : naturalPerson.getFirst_name();	
			String father_last_name = naturalPerson.getFather_last_name() == null ? "" : naturalPerson.getFather_last_name();
			Integer contactId = naturalPerson.getProspectus().getInfusion_id();
			
			if( contactId != null ){
			
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
				
				 SystemParam system_param_I = systemParamService .loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
				 
					InfusionSoft infusion = new InfusionSoft();
					infusion.actualizaContacto(contactId, first_name, father_last_name, membership.getEmail());
						
				 }
			 
			}
		 
		}catch( Exception e ){
			e.printStackTrace();
		}
	
	} */
	
		
	public void validaRelationship()
	{	
		request = RequestContext.getCurrentInstance();
		
		service_prospecto_duplicado.init_natural_person(sesion);
		
		service_prospecto_duplicado.valida_persona_relacionada_y_empleado();
		
		is_accionista_DUPLICADO = service_prospecto_duplicado.isFlagRelation();
		
		boolean is_employed = service_prospecto_duplicado.isFlagEmployee();
		
		is_prospecto_DUPLICADO  = service_prospecto_duplicado.verificar_duplicidad_prospecto();	
		
		Stackholder_relationship stackholder = service_prospecto_duplicado.getStackholder_selection();
		
		if(is_accionista_DUPLICADO)
		{
			//request.addCallbackParam("isRelation", "S" );
			// Actualizamos is_stackholder y campo de gn_stackholder_relationship
			
			membership.setIs_stackholder("S");
			
			//Para que  deje pasar personas relacionadas 20161219 RMB
			request.addCallbackParam("isRelation", "N" );
			
			System.out.println("BasicData.validaRelationship(): Persona Relacionada");
			
		} else {						
			//request.addCallbackParam("isRelation", "S" );
			// TODO Actualizamos is_stackholder y campo de gn_stackholder_relationship
			
			membership.setIs_stackholder("N");
			
			request.addCallbackParam("isRelation", "N" );
			
			System.out.println("BasicData.validaRelationship(): Persona No relacionada");	
		}
		
		if(is_employed)
		{
			
			membership.setIs_employee("S");
			
			
		} else {						
			
			membership.setIs_employee("N");
			
			
		}
		
		if( stackholder != null && stackholder.getPk() != null  ){
			
			membership.setStackholder_relationship_id( stackholder.getPk().getStackholder_relationship_id() );
			
		}else{
		
			membership.setStackholder_relationship_id(null);
		
		}
		
		membershipService.update(membership);
		
		if(is_prospecto_DUPLICADO)
		{
			service_prospecto_duplicado.registrar_access();
											
			lista_prospectos_duplicados = service_prospecto_duplicado.getLista_prospectos_duplicados();
			
			request.addCallbackParam("sameProspect", "S" );
			request.addCallbackParam("lstPros", lista_prospectos_duplicados);
							
		} else {
			
			request.addCallbackParam("sameProspect", "N" );
		}
		
		boolean edad_fuera_de_rango = !birthday.isRango_age_OK();

		request.addCallbackParam("edad_fuera_de_rango", edad_fuera_de_rango);
		
		creaProspect_INV_SGB();
		
	}
	
	public void consultaHistorialBreve(){
		
		try{
			
			faces  = FacesContext.getCurrentInstance();
			resolver = faces.getApplication().getELResolver();
			context  = faces.getELContext();
			external = faces.getExternalContext();
			
			validaDatosPersonales();
				
			BehaviorCheck bc = new BehaviorCheck();
			
			HttpServletRequest httpServletRequest = (HttpServletRequest) external.getRequest(); 
	
			String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
				    
					if(ipAddressClient == null)  
					{
				    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
					}
			
			bc.checkProcess(sesion.getCompany_id(), sesion.getProspectus_id(), ipAddressClient);
			
			SystemParamPK spkH = new SystemParamPK();
			
			spkH.setCompany_id(1);
			spkH.setSystem_param_id( 90 );
			
			SystemParam param = systemParamService.loadSelectedSystemParam( spkH );
			
			if( param != null && param.getValue().equals("S") ) {
				
				request = RequestContext.getCurrentInstance();
				request.addCallbackParam("isActive", true );
				
				System.out.println("iniciandoConsultaPropector");
				
				
				
				boolean is_prospect_SGB_OK = creaProspectoSGB(prospectus, naturalPerson );
				
				
				
				if( is_prospect_SGB_OK ){
				
					RequestShortScore shortrequest = new RequestShortScore();
		
					//Esta llamada se tiene que realizar ya que es probable que haya cambiado la dirección y los objetos  de estado neighborhood
					// no se hayan recargado 
					Address address = addressService.getMaxAddressByType(sesion.getProspectus_id(), sesion.getCompany_id(), 1);
					
					shortrequest.setClientId(naturalPerson.getNatPerPK().getProspectus_id()+"");
					
					shortrequest.setPrimerNombre(naturalPerson.getFirst_name());
					shortrequest.setSegundoNombre(naturalPerson.getMiddle_name());
					shortrequest.setApellidoMaterno(naturalPerson.getMother_last_name());
					shortrequest.setApellidoPaterno(naturalPerson.getFather_last_name());
					shortrequest.setFechaNacimineto(generaFecha(naturalPerson.getDate_of_birth()));
					shortrequest.setRfc(naturalPerson.getMx_rfc());
					
					String street = address.getStreet();
					
					if( street != null ){
						
						street = street.replace( "," , "");
						
					}
					
					shortrequest.setCalle( street );
					
					shortrequest.setCiudad(address.getTownCat().getName());
					
					shortrequest.setCodigoPostal(address.getZip_code());
					
					if(address.getNeighborhood() != null ){
						
						shortrequest.setColonia(address.getNeighborhood().getName());
						
					}else{
						
						shortrequest.setColonia(address.getNeighborhood_text());
						
					}
					
					shortrequest.setEstado(address.getStateCat().getBc_key());
					shortrequest.setMunicipio(address.getTownCat().getName());
					shortrequest.setNumeroExterior(address.getAddress_number());
					
					ObtieneConsultaCorta shortScore = new ObtieneConsultaCorta();
					
					ResponseShortScore res =  shortScore.generaConsultaCorta(shortrequest, true);
					
					if(res != null){
					
						request.addCallbackParam("isActive", true );
						request.addCallbackParam("isValid", res.getValido() );
						request.addCallbackParam("score", res.getScore());
						request.addCallbackParam("bursolnum",res.getBurSolNum());
					
						insertaProspector( res );
						
						if( !res.getValido() ){
							
							insertaScoring( res );
							enviaNotificacion();
							booleanListo	= false;
							protectorValid	= true;
							consultValid    = true;
							
							if( naturalPerson != null && naturalPerson.getProspectus() != null  ){
							
								SystemParamPK spk = new SystemParamPK();
								
								spk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
								spk.setSystem_param_id(88);
								
								SystemParam sys =  systemParamService.loadSelectedSystemParam( spk );
								
								if( sys != null && sys.getValue() != null && sys.getValue().equals("S") && naturalPerson.getProspectus().getInfusion_id() != null ){
								
									InfusionSoft infusion = new InfusionSoft();
								
									infusion.addTAgToContact( naturalPerson.getProspectus().getInfusion_id() , 261 ); // tag Rechazo Automático Prospector
							
									//sendRejectMailForEFL( String email, String body ){
									
								}
								
								//HUBSPOT CONSULTA PROSPECTOR RECHAZADO
								if( naturalPerson != null && naturalPerson.getProspectus() != null && naturalPerson.getProspectus().getHs_vid() != null ){	
									
									SystemParamPK system_param_PK_I = new SystemParamPK();
									
									system_param_PK_I.setCompany_id( 1 );
									system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
									
									 SystemParam system_param_I = systemParamService.loadSelectedSystemParam(system_param_PK_I);
									
									if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
										
										HubSpotController hs =  new HubSpotController();
										
										StringBuilder properties = new StringBuilder( "{ \"property\" : \"bc_score\" , \"value\" : \""+res.getScore()+"\"}," +
															"{ \"property\" : \"prospector_valid\" , 		 \"value\" : \""+res.getValido()+"\"}," +
											 "{ \"property\" : \"estatus_prospecto\" , \"value\" : \"consulta_prospector\"}");
										
										hs.updateProspectus(naturalPerson.getProspectus().getHs_vid(), properties);
										
									 }
								}
							}
							
							
							
						}else{
							
							booleanListo	= true;
							protectorValid	= true;
							consultValid    = false;
							
		//					if(){
		//						
		//						eflMail efl = new eflMail();
		//						
		//						efl.sendRejectMailRedirectEFL();
		//						
		//					}
							
							if( naturalPerson != null && naturalPerson.getProspectus() != null ){
								
								SystemParamPK spk = new SystemParamPK();
								
								spk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
								spk.setSystem_param_id(88);
								
								SystemParam sys =  systemParamService.loadSelectedSystemParam( spk );
								
								if( sys != null && sys.getValue() != null && sys.getValue().equals("S") && naturalPerson.getProspectus().getInfusion_id() != null ){
								
									InfusionSoft infusion = new InfusionSoft();
								
									infusion.addTAgToContact( naturalPerson.getProspectus().getInfusion_id() , 139 ); // tag Consulta Prospector  exitosa
							
								}
								
								//HUBSPOT CONSULTA PROSPECTOR EXITOSO
								if( naturalPerson != null && naturalPerson.getProspectus() != null && naturalPerson.getProspectus().getHs_vid() != null ){	
									
									SystemParamPK system_param_PK_I = new SystemParamPK();
									
									system_param_PK_I.setCompany_id( 1 );
									system_param_PK_I.setSystem_param_id(96); // Bandera que indica si esta habilitada la conección con HUBSPOT
									
									SystemParam system_param_I = systemParamService.loadSelectedSystemParam(system_param_PK_I);
									
									if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") ){
										
										HubSpotController hs =  new HubSpotController();
										
										StringBuilder properties = new StringBuilder();
										
										properties.append("{ \"property\" : \"bc_score\" , \"value\" : \""+res.getScore()+"\"}," +
											 "{ \"property\" : \"prospector_valid\" , 		 \"value\" : \""+res.getValido()+"\"}," +
											 "{ \"property\" : \"estatus_prospecto\" , \"value\" : \"consulta_prospector\"}," +
											 "{ \"property\" : \"tiene_prospector\" , \"value\" : \"Si\"}");
										
											hs.updateProspectus(naturalPerson.getProspectus().getHs_vid(), properties);
										
										
												
												
									 }
									
								}
								
							}
							
							
						}
						
					}
				}
				
				
		//			InfusionSoft infusion = new InfusionSoft();
		//			
		//			infusion.sendRejectMailForEFL( "alangustavo@kubofinanciero.com", "text" );
					
				
				faces.getViewRoot().getViewMap().remove("preaprobacion");
		
		
			}else{
				request = RequestContext.getCurrentInstance();
				request.addCallbackParam("isActive", false );
				request.addCallbackParam("isValid", "" );
				request.addCallbackParam("score", "");
				request.addCallbackParam("bursolnum", "" );
				
			}
		
   
		}catch( Exception e ){
			
			Writer      writer = new StringWriter();
			PrintWriter  printWriter = new PrintWriter(writer);
			
			e.printStackTrace(printWriter);
			
			String msg    = " ERROR PROSPECTOR: "+ e.getMessage();			
			Evento evento = Evento.ERROR_DESARROLLO;
			
			evento.setError("managedbeans.BasicData.consultaHistorialBreve()");
			
			notificar(evento, null,msg+" <br /> "+writer.toString(),null);
			
		}
		
	}
		
	public void updateContactWayValue(){
		
		try{
			
			String ischeck = contactWayValue.split("::")[0];
			
			if( ischeck.equals("true") ){
				
				String contact = contactWayValue.split("::")[1];
				
				
			
				ContactWayProspectus cont = new ContactWayProspectus();
				
				ContactWayProspectusPK cwpk = new ContactWayProspectusPK();
				
				cwpk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				cwpk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				
				cwpk.setContact_way_id(Integer.parseInt(contact));
				
				cont.setPk(cwpk);
				cont.setSelection_date(new Date());
				
				contactwayprospectusservice.saveContactWayProspectus(cont);
			
			}
			
			if( ischeck.equals("false") ){
				
				String contact = contactWayValue.split("::")[1];
				
				ContactWayProspectus cont = new ContactWayProspectus();
				
				ContactWayProspectusPK cwpk = new ContactWayProspectusPK();
				
				cwpk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
				cwpk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
				
				cwpk.setContact_way_id(Integer.parseInt(contact));
				
				cont.setPk(cwpk);
				cont.setSelection_date(new Date());
				
				contactwayprospectusservice.removeContactWayProspectus(cont);
			
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public void consultaBCNIP(){
		
		prospect_risk_ENABLED = false;
		
		intento   = new CreditHistoryAttempt();
		
		request = RequestContext.getCurrentInstance();
		
		SystemParamPK sysId = new SystemParamPK();
		
		sysId.setCompany_id(company_id);
		sysId.setSystem_param_id(90);//Consulta a Buró Habiltada
		
		SystemParam param = systemParamService.loadSelectedSystemParam(sysId);
		
		if( param != null && param.getValue() != null && param.getValue().equals("S") ){
		
			if(pide_contrasena_segura && password_ENABLED )
			{
				
				faces  = FacesContext.getCurrentInstance();
				resolver = faces.getApplication().getELResolver();
				context  = faces.getELContext();
				external = faces.getExternalContext();
			
				
				ProspectRiskIMP risk = new ProspectRiskIMP();
				
				//validaCreditHistoryAttempt();
				
				validaDatosPersonales();
				
				BehaviorCheck bc = new BehaviorCheck();
				
				HttpServletRequest httpServletRequest = (HttpServletRequest) external.getRequest(); 
		
				String ipAddressClient  = httpServletRequest.getHeader("X-FORWARDED-FOR");  
					    
						if(ipAddressClient == null)  
						{
					    	ipAddressClient = httpServletRequest.getRemoteAddr();  	 
						}
				
				bc.checkProcess(sesion.getCompany_id(), sesion.getProspectus_id(), ipAddressClient);
				
				naturalPerson = service_natural_person.getNaturalPersonById(npPK);
				
				init_telefonos();
				
				
				boolean prospectoSGB = creaProspectoSGB( naturalPerson.getProspectus()  , naturalPerson );
				
				if( prospectoSGB ){
					
					try{
						
						asignar_credit_history_attempt( naturalPerson, domicilio.getAddress() , phone.getThisPhoneCell() );
						
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if( success_consulta_nip ){
					
							risk = new ProspectRiskIMP();
							risk.setPerson(naturalPerson);
							risk.setConsulting_renovation_ENABLED(false);
							risk.init();
							
							prospect_risk_ENABLED = risk.isProspect_risk_ENABLED();
											
							if(prospect_risk_ENABLED)
							{
			
								/*ProyectLoanCreatorIMP creator = new ProyectLoanCreatorIMP();
								creator.setScore(risk.getScore());
								creator.init();
								*/
								initProyectLoan( risk.getScore() );
								
								init_consulting_manual( risk.getScore() );
								
								/*faces     = FacesContext.getCurrentInstance();
								context = faces.getELContext();
								resolver  = faces.getApplication().getELResolver();*/
					
								/*NavigationBeanIMP navigation = (NavigationBeanIMP) resolver.getValue(context, null, "navigationBean");
								
								navigation.setHasValidScore(true);
								
								navigation.setFlagBCScore(true);*/
								
								intento.setStatus_res("0");
								intento.setInfo_res( "Consulta satisfactoria" );;
							
							}else{
								
								intento.setStatus_res("-99");
								intento.setInfo_res("Prospecto no encontrado");;
								
							}
							
							intento.setCreditcard_is_principal(null);
							intento.setCreditcard_four_digits(null);
							intento.setMortgage_is_principal(null);
							intento.setCar_is_principal(null);
			
							
							intento.setConsultation_date(new Date());
			
							intento.setIs_check("1");
			
							success_consulta_nip = prospect_risk_ENABLED;
							
							CreditHistoryAttempt  tmp = getTemporalCreditHistoryAttempt(intento); 
							creditAttemptService.add(tmp);
							
					}
					
					
				}
			
			}
			
			//request.addCallbackParam("isActiveNIP", true);
			
		}else{
			
			//request.addCallbackParam("isActiveNIP", false);
			success_consulta_nip = false;
        	msgWarningBurConsult = "" +
									    	"<h3><span></span>Lo sentimos...</h3>" +
									        "<p>Tenemos un problema técnico para continuar con tu solicitud.</p>" +
									        			
									        "<p>No te preocupes, tus datos están guardados, regresa en unos minutos para continuar.</p>" +
									        
									       	//"<div class='callAction btnNaranja' style='width:70%; margin:0 auto;'  onclick='aceptaSalir();'>Salir</div>" +
								        "" ;
        	
		}
		
		
		tmp = "consulta";
		
		request.addPartialUpdateTarget(":frmSuccessNIP");
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(":frmSuccessNIP");
		
		request.addCallbackParam("prospect_risk_ENABLED", prospect_risk_ENABLED);
		
		
		
		
		
		
	}
	
	private void initProyectLoan( Scoring score ){
		
		ProyectLoan proyect_loan = proyectloanService.getMaxProyectLoanByProspect(score.getProspectus_id(), score.getCompany_id());
		
		if( proyect_loan == null || ( proyect_loan.getStatus_id() != null && proyect_loan.getStatus_id().intValue() != 0) ){
			
			insertaProyectLoan( score , 0, "N" );
			
		}else{
			
			proyect_loan.setRate(score.getRate());
			proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
			proyect_loan.setRate_investor(score.getRate_investor());
			proyect_loan.setKubo_score_a(score.getKubo_score_a()==null?"":score.getKubo_score_a());
			proyect_loan.setKubo_score_b(score.getKubo_score_b()==null?"":score.getKubo_score_b());
			proyect_loan.setBc_score(Integer.parseInt(score.getBc_score()));
			proyect_loan.setRate_with_opening(score.getRate());
			proyect_loan.setOpening_commission_amount((score.getOpening_commission()*proyect_loan.getAmmount())/100);
			proyect_loan.setMx_solicitud_buro(score.getMx_solicitud_buro());
			proyect_loan.setVerification_score(1);
			proyect_loan.setOpening_commission(score.getOpening_commission());
			proyect_loan.setLiquidity(score.getLiquidity());
			proyect_loan.setCci_score(score.getCci_score());
			proyect_loan.setConsulting_date(score.getResult_datetime());
			proyectloanService.update(proyect_loan);
			
		}
		
	}
	
	private void init_consulting_manual( Scoring score )
	{				
		ConsultingManual consulting    = new ConsultingManual();
		ConsultingManualPK consulting_PK = new ConsultingManualPK();
					
		consulting_PK.setCompany_id( score.getCompany_id() );
		consulting_PK.setProspectus_id( score.getProspectus_id() );
		
		consulting.setPk(consulting_PK);						
		consulting.setConsulting_prospectus_id(score.getProspectus_id());			
		consulting.setConsulting_date(new Date());				
		consulting.setMx_solicitud_buro( score.getMx_solicitud_buro()  );
		consulting.setIs_consulting_for_renovation("N");
		
		service_consulting_manual.saveConsultingManual(consulting);
	}
	
	public void init_password()
	{
		password_ENABLED  = false;
		
		//HtmlInputSecret input_secret = (HtmlInputSecret) event.getComponent();
		
		String password = nip_consulta;
		
		password = Utilities.encrypt(password);
		
		password_ENABLED = membership.getPassword().equals(password);
		
		request = RequestContext.getCurrentInstance();
		
		request.addCallbackParam("password_ENABLED", password_ENABLED);
	}
	
}

