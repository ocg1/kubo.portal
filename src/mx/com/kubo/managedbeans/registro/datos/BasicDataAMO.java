package mx.com.kubo.managedbeans.registro.datos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.ValBusiness;
import mx.com.kubo.bean.jackson.AplicationPublicationInvestorDataDTO;
import mx.com.kubo.controller.infusion.InfusionSoft;
import mx.com.kubo.model.Access;
import mx.com.kubo.model.BusinessPK;
import mx.com.kubo.model.ClabeAccount;
import mx.com.kubo.model.ContactWayProspectus;
import mx.com.kubo.model.Country;
import mx.com.kubo.model.CountryPK;
import mx.com.kubo.model.EconomicActivityPK;
import mx.com.kubo.model.Economic_Activity;
import mx.com.kubo.model.EmploymentPK;
import mx.com.kubo.model.FullName;
import mx.com.kubo.model.FullNamePK;
import mx.com.kubo.model.MembershipPK;
import mx.com.kubo.model.ProspectusPK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.SavingAccount;
import mx.com.kubo.model.SavingAccountPK;
import mx.com.kubo.model.Scoring;
import mx.com.kubo.model.SimulatorBean;
import mx.com.kubo.model.StateCat;
import mx.com.kubo.model.StateCatPK;
import mx.com.kubo.model.SystemParam;
import mx.com.kubo.model.SystemParamPK;
import mx.com.kubo.model.gnNaturalPersonPK;

import mx.com.kubo.notificaciones.notificables.Evento;
import mx.com.kubo.notificaciones.notificador.NotificacionException;
import mx.com.kubo.notificaciones.notificador.NotificadorIMP;

import mx.com.kubo.registro.datos.domicilio.DomicilioIMP;
import mx.com.kubo.registro.datos.genero.GeneroIMP;
import mx.com.kubo.registro.datos.moral.PersonaMoralIMP;
import mx.com.kubo.registro.datos.nombre.PersonNameIMP;
import mx.com.kubo.registro.datos.pais.PaisOrigenIMP;
import mx.com.kubo.registro.datos.phone.TelefonoIMP;
import mx.com.kubo.registro.datos.simulador.SimuladorIMP;
import mx.com.kubo.registro.datos.state.BirthPlaceIMP;
import mx.com.kubo.registro.datos.birthdate.FechaNacimientoIMP;
import mx.com.kubo.registro.datos.citizen.NacionalidadIMP;
import mx.com.kubo.registro.datos.country.BirthCountryIMP;
import mx.com.kubo.registro.datos.curp.CURPGeneratorIMP;
import mx.com.kubo.tools.ImageUtils;
import mx.com.kubo.tools.Utilities;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soa.webServices.WsSgbRisk;
import com.soa.webServices.WsSgbRiskServiceLocator;

public abstract class BasicDataAMO extends BasicDataDMO
{		
	protected void init_sesion() 
	{
		company_id          = sesion.getCompany_id();
		prospectus_id       = sesion.getProspectus_id();		
		prospectus_id_coach = sesion.getCoachProspectus_id();
		
		change_prospectus_id = prospectus_id_coach != null ? prospectus_id_coach : prospectus_id;
	}
	
	protected void init_catalogos() 
	{
		legalstatuslist      = legalstatusService.getLegalStatusList();
		maritalstatuslist    = maritalstatusService.getMaritalStatusList();
		economicActivityList = economicactivityService.getEconomicActivityList();
		economicActivityList = economicactivityService.getEconomicActivityListEnabled();
		stateList            = service_estado.getStateList();
		countryList          = countryService.getCountryList();
		studylevellist       = studyLevelService.getStudyLevelList();	
		lista_de_generos     = service_gender.getLista_de_generos();
	}
		
	protected void init_pais_origen() 
	{
		pais_origen = new PaisOrigenIMP();
		
		pais_origen.setCompany_id(company_id);
		pais_origen.setProspectus_id(prospectus_id);
		pais_origen.init();
	}
	
	protected void init_prospectus() 
	{
		prosPK = new ProspectusPK();
		prosPK.setCompany_id(company_id);
		prosPK.setProspectus_id(prospectus_id);
		
		prospectus = service_prospectus.getProspectusById(prosPK);
		
		person_type = prospectus.getPerson_type();		
		infusion_id = prospectus.getInfusion_id();
		
		inversionista_ENABLED = prospectus.getArea().equals('I');
		
		if(prospectus.getElectronic_statement() == null)
		{
			prospectus.setElectronic_statement(1);
		}
	}
	
	protected void init_person_type() 
	{				
		switch(person_type)
		{
			case 'A':
			case 'F':
				address_type_id = DOMICILIO_CASA;
				
				residence_ENABLED = true;
				
				legal_address_ENABLED = false;
				
				fiscal_ENABLED = false;
			break;
			
			case 'M':
				address_type_id = DOMICILIO_EMPRESA;
				
				is_legal_address = "N";
				
				residence_ENABLED = false;
				
				legal_address_ENABLED = true;
			break;						
		}
	}
	
	protected void init_membership() 
	{
		mspk = new MembershipPK();
		mspk.setCompany_id(company_id);
		mspk.setProspectus_id(prospectus_id);
		
		membership = membershipService.getMembershipById(mspk);
	}
	
	protected void init_natural_person() 
	{
		npPK = new gnNaturalPersonPK();
		npPK.setCompany_id(company_id);
		npPK.setProspectus_id(prospectus_id);
		
		naturalPerson = service_natural_person.getNaturalPersonById(npPK);
							
		prospectus_id  = naturalPerson.getNatPerPK().getProspectus_id();
		company_id     = naturalPerson.getNatPerPK().getCompany_id();		
		coverage_zone  = naturalPerson.getIs_coverage_zone();				
		
		if (naturalPerson.getGender_id() == null)
		{
			naturalPerson.setGender_id(2);
		}
		
		if (naturalPerson.getCitizenship() == null)
		{
			naturalPerson.setCitizenship(1);
		}	
		
		service_natural_person.update(naturalPerson);
		
	}
	
	protected void init_simulador() 
	{		
		SimulatorBean sim = simulatorService.getMaxSimulationProspect(prospectus_id, company_id) ;
		
		if(sim != null)
		{
			simulator.setAmmount(sim.getAmmount());
			simulator.setFrequency_id(sim.getFrequency_id());
			
			if(sesion.getRate()!= null)
			{
				simulator.setTasaTotal(sesion.getRate());
				
			} else {
				
				simulator.setTasaTotal(61.50D);
			}
			
			if( sim.getTerm_id() != null )
			{
				simulator.setTerm_id(sim.getTerm_id());
			}
			
			if( sim.getPurpose_id() != null )
			{
				simulator.setPurpose_id(sim.getPurpose_id());
			}
		}
		
		simulador = new SimuladorIMP();
		simulador.setService_change_control(service_change_control);
		simulador.setSesion(sesion);			
		simulador.setSimulator(simulator);
	}
	
	protected void init_datos_personales() 
	{		
		name = new PersonNameIMP();
		name.setService_natural_person(service_natural_person);
		name.setService_change_control(service_change_control);
		name.setSesion(sesion);
		name.setPerson(naturalPerson);	
		
		gender = new GeneroIMP();
		gender.setService_natural_person(service_natural_person);
		gender.setService_change_control(service_change_control);
		gender.setSesion(sesion);
		gender.setPerson(naturalPerson);

		birthday = new FechaNacimientoIMP();
		birthday.setService_natural_person(service_natural_person);
		birthday.setService_change_control(service_change_control);
		birthday.setService_system_param(systemParamService);
		birthday.setSesion(sesion);
		birthday.setPerson(naturalPerson);
		birthday.init();
		
		citizenship = new NacionalidadIMP();
		citizenship.setService_natural_person(service_natural_person);
		citizenship.setService_change_control(service_change_control);
		citizenship.setSesion(sesion);
		citizenship.setPerson(naturalPerson);
		
		state = new BirthPlaceIMP();
		state.setService_natural_person(service_natural_person);
		state.setService_change_control(service_change_control);
		state.setSesion(sesion);
		state.setPerson(naturalPerson);
		
		country = new BirthCountryIMP();
		country.setService_natural_person(service_natural_person);
		country.setService_change_control(service_change_control);
		country.setSesion(sesion);
		country.setPerson(naturalPerson);
	}
	
	protected void init_CURP_generator() 
	{
		generator = new CURPGeneratorIMP();
		generator.setService_change_control(service_change_control);
		generator.setService_estado(service_estado);
		generator.setService_natural_person(service_natural_person);
		generator.setSesion(sesion);		
		generator.setChange_prospectus_id(change_prospectus_id);
	}
	
	protected void init_coverage_zone_ENABLED() 
	{
		coverage_zone_ENABLED = coverage_zone != null && coverage_zone.equals('N') && !inversionista_ENABLED;
		
		if(coverage_zone_ENABLED)
		{									
			access = new Access();
			access.setScreen_id(SCREEN_COVERAGE_ZONE);
			access.setPercentage(0);
			access.setCompany_id          (sesion.getCompany_id());
			access.setProspectus_id       (sesion.getProspectus_id());
			access.setWeb_browser         (sesion.getNamebrawser());
			access.setWeb_browser_version (sesion.getVersionbrawser());
			access.setOp_system           (sesion.getOsbrawser());
			access.setHorizontal_size     (sesion.getBrowser_width());
			access.setVertical_size       (sesion.getBrowser_height());
			access.setUser_agent          (sesion.getUser_agent());
			access.setDevice_info         (sesion.getDevice_info());
			access.setIpaddress           (sesion.getIP_address_client());
			access.setProspectus_id_viewed(sesion.getProspectus_id());
			
			access.setUrl_access		  (sesion.getUrl_access());
			access.setProspectus_id_coach (sesion.getCoachProspectus_id());
			access.setAccess_from		  (sesion.getAccess_from());
			access.setVersion_description (sesion.getVersion_description());
			
			accessService.add(access, true);
		}
	}
	
	protected void init_domicilio() 
	{
		domicilio = new DomicilioIMP();
		domicilio.setService_address(addressService);
		domicilio.setService_employment(employmentService);
		domicilio.setService_residence(residenceService);
		domicilio.setService_prospectus(service_prospectus);
		domicilio.setService_natural_person(service_natural_person);
		domicilio.setService_change_control(service_change_control);
		domicilio.setSesion(sesion);
		domicilio.setPerson(naturalPerson);			
		domicilio.setAddress_type_id(address_type_id);
		domicilio.setResidence_ENABLED(residence_ENABLED);
		domicilio.init();		
	}
	
	protected void init_domicilio_fiscal() 
	{
		fiscal = new DomicilioIMP();
		fiscal.setService_address(addressService);
		fiscal.setService_employment(employmentService);
		fiscal.setService_residence(residenceService);
		fiscal.setService_prospectus(service_prospectus);
		fiscal.setService_natural_person(service_natural_person);
		fiscal.setService_change_control(service_change_control);
		fiscal.setSesion(sesion);
		fiscal.setPerson(naturalPerson);
		fiscal.setAddress_type_id(DOMICILIO_FISCAL);
		fiscal.setResidence_ENABLED(false);
		
		if(legal_address_ENABLED)
		{
			fiscal.init();
		}
	}
	
	protected void init_domicilio_fiscal_ENABLED() 
	{
		if(legal_address_ENABLED)
		{
			if(domicilio.equals(fiscal))
			{								
				is_legal_address = "S";
				
				fiscal_ENABLED = false;
				
			} else {								
				
				is_legal_address = "N";
				
				fiscal_ENABLED = true;
			}
		}
	}
	
	protected void init_foto() 
	{
		if (prospectus.getImage() != null && prospectus.getImage().length() > 0)
		{
			photo = "documents/cia_"
					+ prospectus.getProspectusPK().getCompany_id()
					+ "/pros_"
					+ prospectus.getProspectusPK().getProspectus_id()
					+ "/photo/" + prospectus.getImage().split("\\.")[0]+"_thump_150"+"."+prospectus.getImage().split("\\.")[1];
			
			if(!isDirectory(photo))
			{
				photo = "img/sinimagen.jpg";
			}
		}
	}
	
	protected void init_actividad_economica() 
	{
		business_PK = new BusinessPK();
		business_PK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		business_PK.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		business_PK.setBusiness_id(1);
		
		lista_business = businessservice.getListBusinessByProspect(business_PK.getProspectus_id(), business_PK.getCompany_id());
		
		if(lista_business !=null && lista_business.size() > 0)
		{
			displaySector = "block";
			
			hasBusiness = true;
			
			bmx = businessservice.findBmxActivityById(lista_business.get(0).getBmx_econ_activity_id(), lista_business.get(0).getBusinessPK().getCompany_id());
			
			if(bmx != null)
			{
				val_business  = new ValBusiness();
				val_business.setItem("1");
				
				businessName = bmx.getDescription();
				
				//listBusiness.add(val);
			}
			
		} else {
			
			hasBusiness = false;
		}
	}
	
	protected void init_empleo() 
	{
		employment_PK = new EmploymentPK();
		employment_PK.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		employment_PK.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		employment_PK.setEmployment_id(1);
		
		employment = employmentService.getEmploymentById(employment_PK);
		
		if(employment != null)
		{
			displaySector = "block";
			
			hasEmployment = true;
			
			bmx = businessservice.findBmxActivityById(employment.getBmx_econ_activity_id(), employment.getEmploymentPK().getCompany_id());
			
			if(bmx != null)
			{				
				employmentName = bmx.getDescription();					
			}
			
		} else {
			
			hasEmployment = false;
		}
	}
	
	protected void init_inversionista() 
	{
		if(inversionista_ENABLED)
		{
			dispInvestor = "block";
			
			moral = new PersonaMoralIMP();
			moral.setService_natural_person(service_natural_person);
			moral.setService_prospectus(service_prospectus);
			moral.setService_change_control(service_change_control);
			moral.setSesion(sesion);
			moral.setPerson(naturalPerson);			
			moral.init();
			
			listAccount = accountService.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			
			if(listAccount.size() > 0)
			{
				for (SavingAccount element : listAccount) 
				{
					descriptioAccount = element.getDescription();
					break;
				}	
				
			} else { 
				accountPk = new SavingAccountPK(sesion.getProspectus_id(), sesion.getCompany_id());					
				account   = new SavingAccount();
				
				account.setSaving_accountPk(accountPk);
				account.setDescription("Cuenta de Inversión Kubo");
				account.setStatus(0);
				
				if(accountService.addSavingAccount(account, sesion.getProspectus_id(), sesion.getCompany_id()))
				{
					log.info("*********************Se creo una nueva cuenta para el prospecto "+sesion.getProspectus_id());
					//descriptioAccount="Cuenta con mucho dinero";
					listAccount = new ArrayList<SavingAccount>();
					listAccount.add(account);
					
				} else {
					
					log.info("*****************Error al crear la cuenta");	
				}	
				
				listAccount = accountService.getListAccountByProspect(sesion.getProspectus_id(), sesion.getCompany_id());
			}
			
				
		} else {			
			
			dispAcred = "block";	
		}
	}	
			
	protected void init_computer_INFO() 
	{
		selectedComputer = new ArrayList<String>();
		
		if (naturalPerson.getHas_computer_home() != null && naturalPerson.getHas_computer_home() == 1)
		{
			selectedComputer.add("1");
		}
		
		if (naturalPerson.getHas_computer_employment() != null && naturalPerson.getHas_computer_employment() == 1)
		{
			selectedComputer.add("2");
		}
		
		if (naturalPerson.getHas_computer_business() != null && naturalPerson.getHas_computer_business() == 1)
		{
			selectedComputer.add("3");
		}

		selectedInternet = new ArrayList<String>();
		
		if (naturalPerson.getHas_internet_home() != null && naturalPerson.getHas_internet_home() == 1)
		{
			selectedInternet.add("1");
		}
		
		if (naturalPerson.getHas_internet_employment() != null && naturalPerson.getHas_internet_employment() == 1)
		{
			selectedInternet.add("2");
		}
		
		if (naturalPerson.getHas_internet_business() != null && naturalPerson.getHas_internet_business() == 1)
		{
			selectedInternet.add("3");
		}
	}
	
	protected void init_telefonos() 
	{					
		phone = new TelefonoIMP();
		phone.setService_change_control(service_change_control);
		phone.setSesion(sesion);
		phone.setInfusion_id(infusion_id);
		phone.init();
	}
	
/*	
	protected void init_telefonos() 
	{							
		thisPhoneFixed = phoneService.getPhoneByTypeByArea(sesion.getProspectus_id(), sesion.getCompany_id(), 5,sesion.getArea());
		thisPhoneCell  = phoneService.getPhoneByTypeByArea(sesion.getProspectus_id(),sesion.getCompany_id(), 6,sesion.getArea());

		if (thisPhoneFixed == null) 
		{
			thisPhoneFixed = new Phone();
			hasPhoneFixedProspectus = (false);
			
		} else {
			
			if (thisPhoneFixed.getPhone_number() != null) 
			{
				String array[] = thisPhoneFixed.getPhone_number().split("\\)");
				
				if(array != null && array.length >1)
				{
					ladaFixedProspectus = array[0].replace("(", "").trim();
					phoneFixedPropectus = ladaFixedProspectus + array[1];
					
				} else {
					
					phoneFixedPropectus = thisPhoneFixed.getPhone_number();
				}
			}
			
			hasPhoneFixedProspectus = true;
		}
		
		if (thisPhoneCell == null) 
		{
			thisPhoneCell = new Phone();
			hasPhoneCell = false;
			
		} else {
			
			if (thisPhoneCell.getPhone_number() != null) 
			{
				String array[] = getThisPhoneCell().getPhone_number().split("\\)");
				
				if(array != null && array.length > 1)
				{
					String ph1 = array[0].replace("(", "").trim();
					String ph2 = array[1].replace("(", "").trim();
					
					ladaCelProspectus   = ph1 != null ? (ph1.trim().toUpperCase().equals("NULL") ? "" : ph1) : "";
					phoneCellProspectus = ph2 != null ? (ph2.trim().toUpperCase().equals("NULL") ? "" : ph2) : "";
					
					phoneCellProspectus = ladaCelProspectus + phoneCellProspectus;
					
				}else{
					phoneCellProspectus =  getThisPhoneCell().getPhone_number();
				}
			}
			
			hasPhoneCell = true;
		}
	}
*/	
	
	protected void init_Contract_Way_Prospectus_List(){
		
		List<ContactWayProspectus> lstCntWayPrs =  contactwayprospectusservice.getContactWayProspectusList( naturalPerson.getNatPerPK().getCompany_id(), naturalPerson.getNatPerPK().getProspectus_id() );
		
		scriptContactWay = "";
		
		if( lstCntWayPrs != null && lstCntWayPrs.size() > 0 ){
		
			scriptContactWay = "<script>";
					
			for( ContactWayProspectus cntWayPrs : lstCntWayPrs ){
			
				scriptContactWay += "$('#contacto_"+cntWayPrs.getPk().getContact_way_id()+"').prop('checked', true); ";
			
			}
			
			scriptContactWay += " revisarNUevosChecks (); </script>";
			
		}
		
		
		
	}
	
	protected void init_lista_actividad_economica() 
	{
		if(naturalPerson.getEconomic_activity_id() != null) 
		{			
			economicActivity = naturalPerson.getEconomic_activity_id();
			
			if( economicActivity != 1 && economicActivity != 2 )
			{				
				EconomicActivityPK epk = new EconomicActivityPK();
				
				epk.setCompany_id(company_id);
				epk.setEconomic_activity_id(economicActivity);
				
				Economic_Activity t = economicactivityService.getEconomicActivityById(epk);
				
				economicActivityList.add(t);
				
			}			
		}
	}
	
	public void inciaAccess()
	{	
		Access access = new Access();
		
		access.setCompany_id(sesion.getCompany_id());
		access.setProspectus_id(sesion.getProspectus_id());
		
		if( sesion.getArea().toString().equals("L") )
		{
			access.setScreen_id(2);
			
		} else if( sesion.getArea().toString().equals("I") ) {
			
			access.setScreen_id(9);
		}
		
		access.setPercentage(0);
		access.setWeb_browser(sesion.getNamebrawser());
		access.setWeb_browser_version(sesion.getVersionbrawser());
		access.setOp_system(sesion.getOsbrawser());
		access.setHorizontal_size(sesion.getBrowser_width());
		access.setVertical_size(sesion.getBrowser_height());
		access.setIpaddress(sesion.getIP_address_client());
		access.setUser_agent(sesion.getUser_agent());
		access.setDevice_info(sesion.getDevice_info());
		
		access.setUrl_access		  (sesion.getUrl_access());
		access.setProspectus_id_coach (sesion.getCoachProspectus_id());
		access.setAccess_from		  (sesion.getAccess_from());
		access.setVersion_description (sesion.getVersion_description());
		
		accessService.add(access, true);
	}
	
	protected boolean isDirectory(String other)
	{
		faces = FacesContext.getCurrentInstance();
		external = faces.getExternalContext();
		
		String hacia = external.getRealPath("/resources/");
		String path  = hacia + "/" + other;
		
		File file = new File(path);
		
		return  file.exists();
	}
			
    protected boolean validateDir(String path, boolean action) 
	{
		File file = new File(path);
		
		boolean isDirectory = file.isDirectory();
		
		if (!isDirectory) 
		{
			System.out.println("·#·#·#·#· Creando la ruta: " + path);
			file.mkdirs();
		} else {
			System.out.println("·#·#·#·#· Ya existe la ruta: " + path);
		}
		
		return isDirectory;
	}
    
	protected void copyFile(String fileName, InputStream in) 
	{
		try {

			// write the inputStream to a FileOutputStream
			String destination = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/temp/");
			String path = destination + "/" + fileName;

			OutputStream out = new FileOutputStream(new File(path));

			int read = 0;

			byte[] bytes = new byte[in.available()];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			//File newFile = new File(path);
			//newFile = scaleImg(newFile);
			setPhoto("temp/" + fileName);
			setPhotoTemp("temp/" + fileName);
			saveFile(path);

			System.out.println("Se creo el archivo!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
    
	protected void saveFile(String inicio) 
	{

		/*
		 * String inicio =
		 * FacesContext.getCurrentInstance().getExternalContext()
		 * .getRealPath("/resources/temp/");
		 * 
		 * 
		 * validateDir(inicio, true); inicio += "/"+getPhoto();
		 */
		String hacia = "";
		hacia = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/resources/");
		String path1 = "/documents";
		path1 += "/cia_" + prospectus.getProspectusPK().getCompany_id();
		path1 += "/pros_" + prospectus.getProspectusPK().getProspectus_id();
		path1 += "/photo";
		
		String metadata;
		
		String pathHistoric = "/historic/cia_"+prospectus.getProspectusPK().getCompany_id()+"/pros_" +prospectus.getProspectusPK().getProspectus_id()+"/photo";
		
		validateDir(hacia + path1, true);
		validateDir(hacia + pathHistoric, true);
		
		Utilities.deleteFileDirByEqualName(new File(hacia+path1), "photo");
		Utilities.deleteFileDirByEqualName(new File(hacia+pathHistoric), "photo");
		
		path1 += "/";
		Calendar cal2 = Calendar.getInstance();

		String name = "photo_"
				+ prospectus.getProspectusPK().getProspectus_id() + "_"
				+ (((cal2.getTimeInMillis()) / 1000) + "").substring(3)
				+ ".jpg";
		
		String nameThum = "photo_"
				+ prospectus.getProspectusPK().getProspectus_id() + "_"
				+ (((cal2.getTimeInMillis()) / 1000) + "").substring(3)
				+ "_thump"+".jpg";
		
		String nameThum2 = "photo_"
				+ prospectus.getProspectusPK().getProspectus_id() + "_"
				+ (((cal2.getTimeInMillis()) / 1000) + "").substring(3)
				+ "_thump_150"+".jpg";
		
		String pathTum = path1;
		
		//pathTum += nameThum;
		path1 += name;

		try {
			Utilities.copyFile(hacia+pathHistoric+"/"+name,getFile().getInputstream());
			
			File tmpFl = scaleImg(getFile(),150, 150);
			
			System.out.println( tmpFl.getAbsolutePath()+"  --  "+tmpFl.getCanonicalPath()+" -- "+tmpFl.getPath() );
					
			InputStream is = new FileInputStream(tmpFl.getPath() );
			
			Utilities.copyFile(hacia+pathTum +nameThum2,  is);
			
			
			tmpFl = scaleImg(getFile(),70, 70);
			
			System.out.println( tmpFl.getAbsolutePath()+"  --  "+tmpFl.getCanonicalPath()+" -- "+tmpFl.getPath() );
					
			is = new FileInputStream(tmpFl.getPath() );
			
			Utilities.copyFile(hacia+pathTum +nameThum,  is);
			
			metadata = ImageUtils.getMetadata(new File(hacia+pathHistoric+"/"+name));
			if(metadata!=null)
				prospectus.setMetadata(metadata);
			else
				prospectus.setMetadata(null);
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		hacia += path1;
		fileMove(inicio, hacia);		
		prospectus.setImage(name);
		
		service_prospectus.update(prospectus);
		
		setPhoto(path1);
		
		String photostr = "cia_"
				+ prospectus.getProspectusPK().getCompany_id()
				+ "/pros_"
				+ prospectus.getProspectusPK().getProspectus_id()
				+ "/photo/" + prospectus.getImage().split("\\.")[0]+"_thump_150"+"."+prospectus.getImage().split("\\.")[1];
		
		sesion.setUsrImg(photostr);
		
		sesion.setHaveUsrImg(true);
		
	}
    
	protected void fileMove(String sourceFile, String destinationFile) {
		System.out.println("Desde: " + sourceFile);
		System.out.println("Hacia: " + destinationFile);

		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);

			int c;
			while ((c = in.read()) != -1)
				out.write(c);

			in.close();
			out.close();

			File file = new File(sourceFile);
			if (file.exists()) {
				file.delete();
			}
			// Cambiando el metadata de los archivos
			try{
			File f = new File(destinationFile);
			Properties prop = new Properties();
			FileInputStream in2 = new FileInputStream(f);
			prop.load(in2);
			prop.setProperty("company", "");
			}catch(Exception e){
				System.err.println("Hubo un error de entrada/salida con las propiedades de la imagen!!! "
						+ e.getMessage());
				
			}
			//

		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!! "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
    
	protected File scaleImg(UploadedFile fOrigen,int maxWidth, int maxHeight) 
	{
		try 
		{
			
			String tmpPath = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/resources/temp/");
			System.out.println("noombre: "+fOrigen.getFileName());
			tmpPath += "/"+(new Date()).getTime()+"."+(fOrigen.getFileName().split("\\.")[1]);
			
			File thisFile = new File(tmpPath);
			
			FileUtils.copyInputStreamToFile(fOrigen.getInputstream(), thisFile);
			
			BufferedImage img = ImageIO.read(thisFile);
			int xac = (int) img.getWidth();
			int yac = (int) img.getHeight();

			int x = maxWidth;
			int y = maxHeight;
			double resx = 0;
			double resy = 0;
			double resxy = 0;
			System.out.println("ANTES:");
			System.out.println("XAC :" + xac);
			System.out.println("YAC :" + yac);

			boolean flagcambia = false;
			if (xac > x) {
				resx = x / (double) xac;
				double yy = resx * (double) yac;
				y = (int) yy;
				flagcambia = true;
				resxy = resx;
			} else if (yac > y) {
				resy = y / (double) yac;
				double xx = resy * (double) xac;
				x = (int) xx;
				flagcambia = true;
				resxy = resy;
			}

			System.out.println("DESPUES DEL CAMBIO:");
			System.out.println("X :" + x);
			System.out.println("Y :" + y);

			if (flagcambia) {
				BufferedImage novo = new BufferedImage(x, y,
						BufferedImage.TYPE_INT_RGB);// la nueva escala

				Graphics2D g = novo.createGraphics();
				g.setColor(Color.WHITE);
				g.fill(new Rectangle(0, 0, x, y));
				AffineTransform at = null;
				System.out.println("DENTRO:");
				System.out.println("RESX :" + resx);
				System.out.println("RESY :" + resy);
				/*
				 * if(resx>resy) at =
				 * AffineTransform.getScaleInstance(resy,resy);
				 * 
				 * if(resx<resy) at =
				 * AffineTransform.getScaleInstance(resx,resx);
				 * 
				 * if(resx==resy)
				 */
				at = AffineTransform.getScaleInstance(resxy, resxy);

				g.drawRenderedImage(img, at);
				ImageIO.write(novo, "JPG", thisFile);

				img = ImageIO.read(thisFile);

				xac = (int) img.getWidth();
				yac = (int) img.getHeight();
				
				System.out.println("Fin:");
				System.out.println("RESX :" + xac);
				System.out.println("RESY :" + yac);

			}
			
			  
			return thisFile;
			
		} catch (IOException io) {
			io.printStackTrace();
			return null;
		}
	}
	
	protected void saveFullName()
	{
		
		naturalPerson = service_natural_person.getNaturalPersonById(naturalPerson.getNatPerPK());
		
		String first_name       = naturalPerson.getFirst_name()        == null ? "" : naturalPerson.getFirst_name().trim();	
		String middle_name      = naturalPerson.getMiddle_name()       == null ? ""  : naturalPerson.getMiddle_name().trim();
		String father_last_name = naturalPerson.getFather_last_name()  == null ? "" : naturalPerson.getFather_last_name().trim();
		String mother_last_name = naturalPerson.getMother_last_name()  == null ? "" : naturalPerson.getMother_last_name().trim();
		
		String fullnameStr = first_name; 
		
		if( middle_name.length() > 0 )
		{			
			if( fullnameStr.length() > 0 )
			{
				fullnameStr += " ";
			}
			
			fullnameStr +=  middle_name;
			
		}
		if( father_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr += father_last_name;
			
		}
		if( mother_last_name.length() > 0 ){
			
			if( fullnameStr.length() > 0 ){
				fullnameStr += " ";
			}
			
			fullnameStr	+= mother_last_name ;
			
		}
		
		FullNamePK fpk = new FullNamePK();
		
		fpk.setCompany_id(naturalPerson.getNatPerPK().getCompany_id());
		fpk.setProspectus_id(naturalPerson.getNatPerPK().getProspectus_id());
		
		FullName fullname = fullnameservice.getFullName(fpk);
		
		if( fullname == null ){
			
			fullname = new FullName();
			
			fullname.setPk(fpk);
			fullname.setEmail( membership.getEmail());
			fullname.setFull_name(fullnameStr);
			fullname.setArea(naturalPerson.getProspectus().getArea().toString());
			fullnameservice.saveFullName(fullname);
			
		}else{
			
			fullname.setFull_name(fullnameStr);
			fullname.setEmail( membership.getEmail());
			fullname.setArea(naturalPerson.getProspectus().getArea().toString());
			fullnameservice.updateFullName(fullname);
			
		}
		
	}
	
/*	
	protected void actualizaPhoneInfusion( String phonestr )
	{	
		try
		{			
			Integer contactId = naturalPerson.getProspectus().getInfusion_id();
			
			if( contactId != null )
			{			
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
*/	
	
	protected void actualizaDatosInfusion()
	{	
		try
		{			
			String first_name       = naturalPerson.getFirst_name()       == null ? "" : naturalPerson.getFirst_name();	
			String father_last_name = naturalPerson.getFather_last_name() == null ? "" : naturalPerson.getFather_last_name();
			Integer contactId = naturalPerson.getProspectus().getInfusion_id();
			
			if( contactId != null )
			{			
				SystemParamPK system_param_PK_I = new SystemParamPK();
				
				system_param_PK_I.setCompany_id( 1 );
				system_param_PK_I.setSystem_param_id(88); // Bandera que índica si infusion esta habilitado
				
				 SystemParam system_param_I = systemParamService .loadSelectedSystemParam(system_param_PK_I);
				
				 if( system_param_I != null && system_param_I.getValue() != null && system_param_I.getValue().equals("S") )
				 {				 
					InfusionSoft infusion = new InfusionSoft();
					infusion.actualizaContacto(contactId, first_name, father_last_name, membership.getEmail());						
				 }			
			}
		 
		} catch( Exception e ){
			e.printStackTrace();
		}	
	}
	
	protected void notificar(Evento evento, Scoring score, String errormsg, ProyectLoan proyect_loan )
	{		
		try 
		{
			notificador = new NotificadorIMP();
			notificador.setEmisor(membership);
			notificador.notificar(evento, score, proyect_loan, errormsg);
			
		} catch (NotificacionException e) {			
			e.printStackTrace();
		}				
	}
	
	protected String generaFecha( Date nfecha)
	{	
//		Calendar c = Calendar.getInstance(); 
//		c.setTime( nfecha );
//		
//		int day = c.get(Calendar.DATE);
//		int month = c.get(Calendar.MONTH);
//		int year = c.get(Calendar.YEAR);
//		
		if( nfecha != null ){
			String res = "";
			
			SimpleDateFormat sd = new SimpleDateFormat( "ddMMyyyy" );
			
			res = sd.format(nfecha);
			
	//		if( day < 10)
	//			res += "0";
	//		
	//		res += day;
	//		
	//		if( month < 10)
	//			res += "0";
	//		
	//		res += month+year;
			
			return res;
		}else{
			return "";
		}
	}
	
	protected final boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=basicData");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
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
	
	protected String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
	protected void creaProspect_INV_SGB()
	{
		System.out.println("+++++    LLAMANDO AL SGB    +++++");

		try 
		{
			
			
			mspk = new MembershipPK();
			mspk.setCompany_id(company_id);
			mspk.setProspectus_id(prospectus_id);
			
			String clabe = "";
			
			String banco = "";
			
			membership = membershipService.getMembershipById(mspk);
			
			String _JSON_str = "";
			
			if( membership.getPerson().getProspectus().getArea().toString().equals("I") ){
			
					WsSgbRiskServiceLocator locator = new WsSgbRiskServiceLocator();
					WsSgbRisk  service = locator.getWsSgbRisk();// yyyymmdd. 19860131
					
					AplicationPublicationInvestorDataDTO ap_INV = new AplicationPublicationInvestorDataDTO();
					
					List<ClabeAccount> accountList    = clabeaccountservice.loadClabeAccountListByProspectus(prospectus_id, membership.getMembershipPK().getCompany_id());
					
					if( accountList != null && accountList.size() > 0  ){
					
						banco = accountList.get(0).getBank_description();
						clabe = accountList.get(0).getMx_clabe();
						
					}
					
					CountryPK cpk = new CountryPK(membership.getPerson().getCountry_id(),membership.getMembershipPK().getCompany_id() );
					
					Country c = countryService.getCountryById( cpk );
					
					StateCatPK stPK = null;
					
					StateCat st = null;
					
					if( membership.getPerson().getState_id() != null ){
					
						stPK = new  StateCatPK(membership.getPerson().getState_id() ,membership.getMembershipPK().getCompany_id() );
						st =  service_estado.getStateById(stPK);
						
					}
					
					
				
					if(c != null){
						ap_INV .setCountryOfBirthName( c.getName() );
					}
					
					ap_INV .setDateOfBirth( membership.getPerson().getDate_of_birth() );
					ap_INV .setFatherLastName(membership.getPerson().getFather_last_name());
					ap_INV .setFirstName(membership.getPerson().getFirst_name());
					ap_INV .setGenderId(membership.getPerson().getGender_id());
					ap_INV .setMail(membership.getEmail());
					ap_INV .setMiddleName(membership.getPerson().getMiddle_name());
					ap_INV .setMotherLastName(membership.getPerson().getMother_last_name());
					ap_INV .setMxBankDescription(banco);
					ap_INV .setMxClabe(clabe);
					ap_INV .setMxCurp(membership.getPerson().getMx_curp());
					ap_INV .setMxRfc(membership.getPerson().getMx_rfc());
					ap_INV .setProspectusId(membership.getMembershipPK().getProspectus_id());
					ap_INV .setReason(membership.getRegistration_reason().getName());
					
					if(st != null){
						ap_INV .setStateOfBirthName( st.getName() );
					}
					
					ap_INV .setStatusId(0);
					
					ObjectMapper mapper = new ObjectMapper();
					
									//Object to JSON in String
							try {
								String jsonInString = mapper.writeValueAsString(ap_INV);
								_JSON_str = jsonInString;
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
					
					// +
					
					
					System.out.println( "******" );
					System.out.println( "***SGB*REQUEST**" );
					System.out.println( _JSON_str );
					System.out.println( "******" );
					System.out.println( "******" );
					
					service.aplicationPublicationInvestor(_JSON_str);
					
			}
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
}
