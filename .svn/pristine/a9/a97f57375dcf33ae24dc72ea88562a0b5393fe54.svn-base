package mx.com.kubo.managedbeans.registro.sobre;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;

import mx.com.kubo.bean.ActividadEconomicaDMO;
import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.listeners.registro.ListenerMiPrestamoIMP;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;

import org.primefaces.context.RequestContext;

@ManagedBean (name = "addMyLoan") @ViewScoped 
public final class MiPrestamoIMP extends MiPrestamoPMO
implements MiPrestamoIMO, Serializable
{	
	private static final long serialVersionUID = -8502027843893231573L;

	@PostConstruct
	public void init()
	{	
		faces = FacesContext.getCurrentInstance();
		context   = faces.getELContext();
		resolver  = faces.getApplication().getELResolver();
		external  = faces.getExternalContext();
		
		sesion = (SessionBean) resolver.getValue(context, null, "sessionBean");
			
		if( isSesion_DISABLED() ){
			return;
		}
		
		
		company_id          = sesion.getCompany_id();
		prospectus_id       = sesion.getProspectus_id();		
		prospectus_id_coach = sesion.getCoachProspectus_id();
		
		change_prospectus_id = prospectus_id_coach != null ? prospectus_id_coach : prospectus_id;
				
        listPurpose             = service_purpose.getPurposeList();
        listContractType        = service_employment.getContractTypeList();        
        lista_tenure            = service_employment.getTenureList();
        lista_employment        = service_employment.getListEmployByProspect(prospectus_id, company_id);
        lista_estado_civil      = service_legal_status.getLegalStatusList();
        lista_regimen_conyugal  = service_marital_status.getMaritalStatusList();
        lista_nivel_estudios    = service_study_level.getStudyLevelList();        
        listFromWhere           = service_fromwhere.getListFromWhere();
        listWhoAnswered         = service_whoAnswered.getListWhoAnswered();  
        lista_dependants_number = service_natural_person.getLista_dependants_number();
		
		init_natural_person();
		init_membership();
		init_proyect();						
		
		listener_mi_prestamo = new ListenerMiPrestamoIMP();
		listener_mi_prestamo.setSesion(sesion);
		listener_mi_prestamo.setNatural_person(naturalperson);
		
		employmentList = listener_mi_prestamo.getLista_employment_bean();		
		businessList   = listener_mi_prestamo.getLista_business_bean();	
		
		if(employmentList != null && employmentList.size() > 0)
		{
			init_domicilio(address_type_EMPLEO);
		}
		
		if(businessList != null && businessList.size() > 0)
		{
			init_domicilio(address_type_NEGOCIO);
		}
		
		System.out.println("MiPrestamoIMP.init(): OK");
	}

	public final void listener_estado_civil(AjaxBehaviorEvent evento) 
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		estado_civil_SELECTED = (Integer) select_one_menu.getValue();
		if(estado_civil_SELECTED.intValue() == 0){
			estado_civil_SELECTED = null;
		}
		
		System.out.println("MiPrestamoIMP.listener_estado_civil() = " + estado_civil_SELECTED);
		
		
		if ( naturalperson.getLegal_status_id() != null  &&   naturalperson.getLegal_status_id() == 2) 
		{						
			regimen_conyugal_ENABLED = "none";
			
		} else {
			
			regimen_conyugal_ENABLED = "block";
			
			naturalperson.setMarital_status_id(null);
		}

		service_natural_person.update(naturalperson);
	}
	
	public final void listener_regimen_conyugal(AjaxBehaviorEvent evento)
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		marital_status_id = (Integer) select_one_menu.getValue();
		
		System.out.println("MiPrestamoIMP.listener_regimen_conyugal() = " + marital_status_id);
		
		if(marital_status_id != null)
		{									
			naturalperson.setMarital_status_id(marital_status_id);
			
			service_natural_person.update(naturalperson);
		}
	}
	
	public final void listener_dependientes_economicos(AjaxBehaviorEvent evento)
	{
		input_text = (HtmlInputText) evento.getComponent();
		
		dependientes_economicos = (Integer) input_text.getValue();
		
		System.out.println("MiPrestamoIMP.listener_dependientes_economicos() = " + dependientes_economicos);
		
		if(dependientes_economicos != null)
		{
			naturalperson.setDependants_number(dependientes_economicos);
			
			service_natural_person.update(naturalperson);
		}
	}
	
	public final void listener_nivel_estudios(AjaxBehaviorEvent evento)
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		nivel_estudios = (Integer) select_one_menu.getValue();
		
		System.out.println("MiPrestamoIMP.listener_nivel_estudios() = " + nivel_estudios);
		
		if(nivel_estudios != null)
		{
			naturalperson.setStudy_level_id(nivel_estudios);
			
			service_natural_person.update(naturalperson);
		}
	}	
	
	public final void listener_has_computer() 
	{
		System.out.println( "MiPrestamoIMP.listener_has_computer(): "+ strComputeHidden );
		
		Integer hasCompHome = 0;
		Integer hasCompEmp = 0;
		Integer hasCompBusi = 0;
		
		selectedComputer.clear();
		
		if(	strComputeHidden.indexOf('1') != (-1) )
		{
			selectedComputer.add("1");
		}
		
		if(	strComputeHidden.indexOf('2') != (-1) )
		{
			selectedComputer.add("2");
		}
		
		if(	strComputeHidden.indexOf('3') != (-1) )
		{
			selectedComputer.add("3");
		}
		

		for (String s : selectedComputer) 
		{

			if (s.equals("1")) 
			{
				hasCompHome = 1;
			}
			
			if (s.equals("2")) 
			{
				hasCompEmp = 1;
			}
			
			if (s.equals("3"))
			{
				hasCompBusi = 1;
			}
		}
		
		naturalperson.setHas_computer_business(hasCompBusi);
		naturalperson.setHas_computer_employment(hasCompEmp);
		naturalperson.setHas_computer_home(hasCompHome);
		
		service_natural_person.update(naturalperson);
	}
	
	public final void listener_has_internet() 
	{	
		System.out.println("MiPrestamoIMP.listener_has_internet(): "+ strInternetHidden );
		
		Integer hasIntHome = 0;
		Integer hasIntEmp = 0;
		Integer hasIntBusi = 0;
		
		
		selectedInternet.clear();
		
		if(	strInternetHidden.indexOf('1') != (-1) ){
			selectedInternet.add("1");
		}
		if(	strInternetHidden.indexOf('2') != (-1) ){
			selectedInternet.add("2");
		}
		if(	strInternetHidden.indexOf('3') != (-1) ){
			selectedInternet.add("3");
		}
		

		for (String s : selectedInternet) 
		{
			if (s.equals("1")) 
			{
				hasIntHome = 1;
			}
			
			if (s.equals("2")) 
			{
				hasIntEmp = 1;
			}
			
			if (s.equals("3")) 
			{
				hasIntBusi = 1;
			}
		}
		
		naturalperson.setHas_internet_business(hasIntBusi);
		naturalperson.setHas_internet_employment(hasIntEmp);
		naturalperson.setHas_internet_home(hasIntHome);
		
		service_natural_person.update(naturalperson);
	}
	
	public final void listener_dependants_number(AjaxBehaviorEvent evento)
	{
		select_one_menu = (HtmlSelectOneMenu) evento.getComponent();
		
		dependants_number_id = (Integer) select_one_menu.getValue();
		
		System.out.println("MiPrestamoIMP.listener_dependants_number() = " + dependants_number_id);
		
		if(dependants_number_id != null)
		{
			naturalperson.setDependants_number_id(dependants_number_id);
			
			service_natural_person.update(naturalperson);
		}
	}
	
	public final void listener_update_business_address(BusinessBean business_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		listener_mi_prestamo.setBusiness_bean(business_bean);		
		listener_mi_prestamo.update_business_address();
		
		businessList = listener_mi_prestamo.getLista_business_bean();
		
		request.addCallbackParam("same_address_ENABLED", business_bean.getSame_address_ENABLED());
		request.addCallbackParam("business_id",          business_bean.getBusiness().getBusinessPK().getBusiness_id());
	}
	
	public final void listener_update_employment_address(EmploymentBean employment_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		listener_mi_prestamo.setEmployment_bean(employment_bean);		
		listener_mi_prestamo.update_employment_address();		
		
		employmentList = listener_mi_prestamo.getLista_employment_bean();
		
		request.addCallbackParam("same_address_ENABLED", employment_bean.getSame_address_ENABLED());
		request.addCallbackParam("employment_id",        employment_bean.getEmployment().getEmploymentPK().getEmployment_id());
	}
	
	public final void updateNaturalPersonBlur()
	{
		if(naturalperson != null)
		{			
			service_natural_person.update(naturalperson);			
		}	
	}
	
	public void updateMembership()
	{
		if(getThisMembership()!=null)
		{
			if(getThisMembership().getFromwhere_id()!=null && getThisMembership().getFromwhere_id()!=255)
			{
				getThisMembership().setFromwhere_other(null);
			}
			
			if(getThisMembership().getWho_answered_id()!=null && getThisMembership().getWho_answered_id()!=99)
			{
				getThisMembership().setWho_answered_other(null);
			}
			
			service_membership.update(getThisMembership());			
		}				
	}
		
	public void updateEmployment(EmploymentBean employment_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		listener_mi_prestamo.setEmployment_bean(employment_bean);		
		listener_mi_prestamo.update_employment();		
		
		employmentList = listener_mi_prestamo.getLista_employment_bean();
		
		request.addCallbackParam("same_address_ENABLED", employment_bean.getSame_address_ENABLED());
		request.addCallbackParam("employment_id",        employment_bean.getEmployment().getEmploymentPK().getEmployment_id());
	}
	
	public void updateBusiness(BusinessBean business_bean)
	{
		request = RequestContext.getCurrentInstance();
		
		listener_mi_prestamo.setBusiness_bean(business_bean);		
		listener_mi_prestamo.update_business();
		
		businessList = listener_mi_prestamo.getLista_business_bean();
		
		request.addCallbackParam("same_address_ENABLED", business_bean.getSame_address_ENABLED());
		request.addCallbackParam("business_id",          business_bean.getBusiness().getBusinessPK().getBusiness_id());
	}
	
/*	
	public void updateAddressBlur(EmploymentBean employbean)
	{
		boolean flagUpdate=false;
		boolean flagSave=false;		
		
		if(employbean.getAddressbean().getAddress()!=null){
			if (employbean.getAddressbean().getZip_code() != null && employbean.getAddressbean().getZip_code().length() > 0) {
				employbean.getAddressbean().getAddress().setState_id(employbean.getAddressbean().getState_id());
				employbean.getAddressbean().getAddress().setTown_id(employbean.getAddressbean().getTown_id());
				employbean.getAddressbean().getAddress().setNeighborhood_id(employbean.getAddressbean().getNeighborhood_id());
				employbean.getAddressbean().getAddress().setZip_code(employbean.getAddressbean().getZip_code());
				employbean.getAddressbean().getAddress().setLatitude(employbean.getAddressbean().getLatitude());
				employbean.getAddressbean().getAddress().setLongitude(employbean.getAddressbean().getLongitude());
			}
		}
		if(employbean.getAddressbean().getAddress()!=null){	
			flagUpdate = service_address.update(employbean.getAddressbean().getAddress());
		}else{
			AddressPK adpk= new AddressPK();
			adpk.setCompany_id(sesion.getCompany_id());
			adpk.setProspectus_id(sesion.getProspectus_id());
			Address address=new Address();
			address.setCountry_id(700);
			address.setTown_id(employbean.getAddressbean().getTown_id());
			address.setNeighborhood_id(employbean.getAddressbean().getNeighborhood_id());
			address.setState_id(employbean.getAddressbean().getState_id());
			address.setZip_code(employbean.getAddressbean().getZip_code());
			address.setAddressPK(adpk);
			address.setAddress_type_id(4);
			address.setEmployment_id(employbean.getEmployment().getEmploymentPK().getEmployment_id());
			address.getAddressPK().setAddress_id(service_address.getMaxAddressId(sesion.getProspectus_id(),sesion.getCompany_id()));
			flagSave = service_address.add(address);
			if(flagSave){
				for(EmploymentBean iterar:employmentList){
					if(iterar.getEmployment().getEmploymentPK().getEmployment_id()==employbean.getEmployment().getEmploymentPK().getEmployment_id()){					
						iterar.setCheck_inH1(employbean.getEmployment().getCheck_in()!=null? employbean.getEmployment().getCheck_in().split(":")[0]:"09");
						iterar.setCheck_inM1(employbean.getEmployment().getCheck_in()!=null? employbean.getEmployment().getCheck_in().split(":")[1]:"00");
						iterar.setCheck_outH1(employbean.getEmployment().getCheck_out()!=null? employbean.getEmployment().getCheck_out().split(":")[0]:"18");
						iterar.setCheck_outM1(employbean.getEmployment().getCheck_out()!=null? employbean.getEmployment().getCheck_out().split(":")[1]:"00");
						iterar.getAddressbean().setAddress(address);
						break;
					}
				}
			}
		}
		//end SaveAddress
		if(flagSave)
			log.info("====>>>> Address saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  NO saved !!!!!!!!!!!!!!!");	
		
		if(flagUpdate)
			log.info("====>>>> Address Update successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  NO Update !!!!!!!!!!!!!!!");	
			
			
	}	
*/	
			
	public void updatePhoneFixed(EmploymentBean employbean)
	{		
		prospectus_id = sesion.getProspectus_id();
		company_id    = sesion.getCompany_id();
		area          = sesion.getArea();
		
		phone_fixed = employbean.getPhonebeanFixed();
		
		lada      = phone_fixed.getLadaPhone();
		telefono  = phone_fixed.getNumPhone();
		extension = phone_fixed.getExtension();
		
		employment_id_SELECTED = employbean.getEmployment().getEmploymentPK().getEmployment_id();
		
		if(phone_fixed.getPhone() != null)
		{
			if(telefono != null)
			{
				sb = new StringBuilder();
				
				sb.append(telefono);
				
				telefono = sb.toString();
				
				phone_fixed.getPhone().setPhone_number(telefono);
				phone_fixed.getPhone().setExtension(extension);
				
				flagUpdate = service_phone.updatePhone(phone_fixed.getPhone());
			}			
			
		} else {
			
			if(telefono != null)
			{
				phonePk   = new PhonePK(prospectus_id, company_id);
				phone_NEW = new Phone();
				
				sb = new StringBuilder();
				
				sb.append(telefono);
				
				telefono = sb.toString();
				
				phone_NEW.setPhone_number(telefono);					
				phone_NEW.setPhonePk(phonePk);
				phone_NEW.setPhone_type_id(1);
				phone_NEW.setOwned("0");
				phone_NEW.setArea(area);
				phone_NEW.setEmployment_id(employment_id_SELECTED);
				
				flagSave = service_phone.addPhone(phone_NEW, prospectus_id, company_id);
				
				if(flagSave)
				{
					for(ActividadEconomicaDMO iterar: employmentList)
					{
						EmploymentBean employment_bean = (EmploymentBean) iterar;
						 
						int employment_id = employment_bean.getEmployment().getEmploymentPK().getEmployment_id();
						
						if(employment_id == employment_id_SELECTED)
						{
							employment_bean.getPhonebeanFixed().setPhone(phone_NEW);
							
							break;
						}
					}
				}
			}
		}
		
		if(flagSave)
		{
			log.info("====>>>> Phone fixed saved successfully!!!!!!!!!!!!!!!");
			
		} else {
			
			log.info("====>>>> Phone fixed  NO saved !!!!!!!!!!!!!!!");
		}
		
		if(flagUpdate)
		{
			log.info("====>>>> Phone fixed Update successfully!!!!!!!!!!!!!!!");
			
		} else {
			
			log.info("====>>>> Phone fixed NO Update !!!!!!!!!!!!!!!");
		}
		
	}
	
	public void updatePhoneCel(EmploymentBean employbean)
	{
		boolean flagUpdate=false;
		boolean flagSave=false;
		
		if (employbean.getPhonebeanCel().getPhone()!=null) {
			if( employbean.getPhonebeanCel().getNumCelPhone()!=null ){
				employbean.getPhonebeanCel().getPhone().setPhone_number(employbean.getPhonebeanCel().getNumCelPhone());
				flagUpdate=service_phone.updatePhone(employbean.getPhonebeanCel().getPhone());
			}else{
				employbean.getPhonebeanCel().getPhone().setPhone_number(" ");
				flagUpdate=service_phone.updatePhone(employbean.getPhonebeanCel().getPhone());
			}
		} else {
			if(employbean.getPhonebeanCel().getNumCelPhone()!=null){
				PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
				Phone phone=new Phone();
				phone.setPhone_number(employbean.getPhonebeanCel().getNumCelPhone());					
				phone.setPhonePk(phonePk);
				phone.setPhone_type_id(2);
				phone.setOwned("0");
				phone.setArea(sesion.getArea());
				phone.setEmployment_id(employbean.getEmployment().getEmploymentPK().getEmployment_id());
				
				flagSave=service_phone.addPhone(phone,sesion.getProspectus_id(),sesion.getCompany_id());
				
				if(flagSave)
				{										
					for(ActividadEconomicaDMO iterar: employmentList)
					{
						EmploymentBean employment_bean = (EmploymentBean) iterar;
						
						if(employment_bean.getEmployment().getEmploymentPK().getEmployment_id()==employbean.getEmployment().getEmploymentPK().getEmployment_id())
						{
							iterar.getPhonebeanCel().setPhone(phone);
							break;
						}
					}
				}
			}
			
		}
		
		if(flagSave)
			log.info("====>>>> Phone Cel saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone Cel NO saved !!!!!!!!!!!!!!!");	
		
		if(flagUpdate)
			log.info("====>>>> Phone Cel Update successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone Cel  NO Update !!!!!!!!!!!!!!!");
		
	}
		
/*	
	public void zipCodeFunctionAndSave(EmploymentBean employbean)
	{
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String cp= employbean.getAddressbean().getZip_code();
		log.info("CODIGO POSTAL ES= "+cp);			
		listAsents = service_employment.getAsentamientosByCP(cp);				
		
		if(listAsents.size()!=0)
		{
			ArrayList<ListNeighborhoodBean> catalogo= new ArrayList<ListNeighborhoodBean>();
			ListNeighborhoodBean neigt=null;
			
			for (NeighborhoodCat iter : listAsents) 
			{
				neigt=new ListNeighborhoodBean(iter.getNeighborhoodCatPK().getNeighborhood_id(),iter.getName());
				catalogo.add(neigt);	
			}
			employbean.getAddressbean().setListNeighborhood(listAsents);
			employbean.getAddressbean().setTownName(listAsents.get(0).getDelegMunicipio().getName());
			employbean.getAddressbean().setStateName(listAsents.get(0).getDelegMunicipio().getEstados().getName());		
			employbean.getAddressbean().setTown_id(listAsents.get(0).getDelegMunicipio().getTownCatPK().getTown_id());
			employbean.getAddressbean().setState_id(listAsents.get(0).getDelegMunicipio().getEstados().getStateCatPK().getState_id());
			
			requestContext.addCallbackParam("TownName", listAsents.get(0).getDelegMunicipio().getName());
			requestContext.addCallbackParam("StateName", listAsents.get(0).getDelegMunicipio().getEstados().getName());	
			
			if(listAsents.size()==1)
			{
				employbean.getAddressbean().setNeighborhood_id(listAsents.get(0).getNeighborhoodCatPK().getNeighborhood_id());
			}else{
				employbean.getAddressbean().setNeighborhood_id(null);
			}
			
			try 
			{
				requestContext.addCallbackParam("neighborhood", new JSONArray(catalogo.toArray(),true).toString());
				requestContext.addCallbackParam("isValid", true);
				updateAddressBlur(employbean);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}else{			
			requestContext.addCallbackParam("isValid", false);
			employbean.getAddressbean().setZip_code(null);
		}
		
		
	}
*/	
	
/*	
	public void setSplitLatLongEmployment(EmploymentBean employbean) 
	{
		try {
			if(employbean.getAddressbean().getLatLong()!=null){
			employbean.getAddressbean().setLatitude(employbean.getAddressbean().getLatLong().split(",")[0].trim());
			employbean.getAddressbean().setLongitude(employbean.getAddressbean().getLatLong().split(",")[1].trim());
			}
		} catch (Exception e) {
			log.info("Error split");
		}
		log.info("!!!!!!!!!!!!! GUARDAR LAT LONG="+employbean.getAddressbean().getLatitude()+","+employbean.getAddressbean().getLongitude());
		updateAddressBlur(employbean);
	}
	
	public void setSplitLatLongBusiness(BusinessBean business) 
	{
		try {
			if(business.getAddressbean().getLatLong()!=null){
			business.getAddressbean().setLatitude(business.getAddressbean().getLatLong().split(",")[0]);
			business.getAddressbean().setLongitude(business.getAddressbean().getLatLong().split(",")[1]);
			}
		} catch (Exception e) {
			log.info("Error split");
		}
		log.info("!!!!!!!!!!!!! GUARDAR LAT LONG="+business.getAddressbean().getLatitude()+","+business.getAddressbean().getLongitude());
		updateAddressBlurBusiness(business);
	}
*/	
		
/*	
	public void updateAddressBlurBusiness(BusinessBean business)
	{
		boolean flagUpdate=false;
		boolean flagSave=false;
		
		if(business.getAddressbean().getAddress()!=null)
		{
			if (business.getAddressbean().getZip_code() != null && business.getAddressbean().getZip_code().length() > 0) {
				business.getAddressbean().getAddress().setState_id(business.getAddressbean().getState_id());
				business.getAddressbean().getAddress().setTown_id(business.getAddressbean().getTown_id());
				business.getAddressbean().getAddress().setNeighborhood_id(business.getAddressbean().getNeighborhood_id());
				business.getAddressbean().getAddress().setZip_code(business.getAddressbean().getZip_code());
				business.getAddressbean().getAddress().setLatitude(business.getAddressbean().getLatitude());
				business.getAddressbean().getAddress().setLongitude(business.getAddressbean().getLongitude());
			}
		}
		if(business.getAddressbean().getAddress()!=null){	
			flagUpdate = service_address.update(business.getAddressbean().getAddress());
		}else{
			AddressPK adpk= new AddressPK();
			adpk.setCompany_id(sesion.getCompany_id());
			adpk.setProspectus_id(sesion.getProspectus_id());
			Address address=new Address();
			address.setCountry_id(700);
			address.setTown_id(business.getAddressbean().getTown_id());
			address.setNeighborhood_id(business.getAddressbean().getNeighborhood_id());
			address.setState_id(business.getAddressbean().getState_id());
			address.setZip_code(business.getAddressbean().getZip_code());
			address.setAddressPK(adpk);
			address.setAddress_type_id(3);
			address.setBusiness_id(business.getBusiness().getBusinessPK().getBusiness_id());
			address.getAddressPK().setAddress_id(service_address.getMaxAddressId(sesion.getProspectus_id(),sesion.getCompany_id()));
			flagSave = service_address.add(address);
			if(flagSave){
				for(BusinessBean iterar:businessList){
					if(iterar.getBusiness().getBusinessPK().getBusiness_id()==business.getBusiness().getBusinessPK().getBusiness_id()){
						iterar.getAddressbean().setAddress(address);
					}
				}
			}
		}
		//end SaveAddress
		if(flagSave)
			log.info("====>>>> Address business saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  business NO saved !!!!!!!!!!!!!!!");	
		
		if(flagUpdate)
			log.info("====>>>> Address business Update successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Address  business NO Update !!!!!!!!!!!!!!!");	
			
			
	}
*/	
	
	public void updatePhoneBusinessFixed(BusinessBean business)
	{
		boolean flagUpdate=false;
		boolean flagSave=false;
		
		if(business.getPhonebeanFixed().getPhone()!=null)
		{
			if(business.getPhonebeanFixed().getNumPhone()!=null)
			{
				business.getPhonebeanFixed().getPhone().setPhone_number(business.getPhonebeanFixed().getNumPhone());
				
				flagUpdate=service_phone.updatePhone(business.getPhonebeanFixed().getPhone());
			}
			
		} else {
			
			if( business.getPhonebeanFixed().getNumPhone()!=null )
			{
				PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
				Phone phone=new Phone();
				phone.setPhone_number( business.getPhonebeanFixed().getNumPhone() );					
				phone.setPhonePk(phonePk);
				phone.setPhone_type_id(3);
				phone.setOwned("0");
				phone.setArea(sesion.getArea());
				phone.setBusiness_id(business.getBusiness().getBusinessPK().getBusiness_id());
				
				flagSave = service_phone.addPhone(phone,sesion.getProspectus_id(),sesion.getCompany_id());
				
				if(flagSave)
				{
					for(ActividadEconomicaDMO iterar:businessList)
					{						
						BusinessBean business_bean = (BusinessBean) iterar;
						
						if(business_bean.getBusiness().getBusinessPK().getBusiness_id()==business.getBusiness().getBusinessPK().getBusiness_id())
						{
							business_bean.getPhonebeanFixed().setPhone(phone);
						}
					}
				}
			}
		}
		if(flagSave)
			log.info("====>>>> Phone fixed business saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone fixed business NO saved !!!!!!!!!!!!!!!");	
		
		if(flagUpdate)
			log.info("====>>>> Phone fixed business Update successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone fixed business NO Update !!!!!!!!!!!!!!!");
	}
	
	public void updatePhoneBusinessCel(BusinessBean business)
	{
		boolean flagUpdate=false;
		boolean flagSave=false;
		
		if (business.getPhonebeanCel().getPhone()!=null) 
		{
			if( business.getPhonebeanCel().getNumCelPhone()!=null && business.getPhonebeanCel().getNumCelPhone()!="")
			{
				business.getPhonebeanCel().getPhone().setPhone_number(business.getPhonebeanCel().getNumCelPhone());
				flagUpdate=service_phone.updatePhone(business.getPhonebeanCel().getPhone());
			}else{
				business.getPhonebeanCel().getPhone().setPhone_number("");
				flagUpdate=service_phone.updatePhone(business.getPhonebeanCel().getPhone());			
				}
		} else {
			if(business.getPhonebeanCel().getNumCelPhone()!=null)
			{
				PhonePK phonePk=new PhonePK(sesion.getProspectus_id(),sesion.getCompany_id());
				Phone phone=new Phone();
				phone.setPhone_number(business.getPhonebeanCel().getNumCelPhone());					
				phone.setPhonePk(phonePk);
				phone.setPhone_type_id(4);
				phone.setOwned("0");
				phone.setArea(sesion.getArea());
				phone.setBusiness_id(business.getBusiness().getBusinessPK().getBusiness_id());
				flagSave=service_phone.addPhone(phone,sesion.getProspectus_id(),sesion.getCompany_id());
				
				if(flagSave)
				{
					for(ActividadEconomicaDMO iterar: businessList)
					{						
						BusinessBean business_bean = (BusinessBean) iterar;
						
						if(business_bean.getBusiness().getBusinessPK().getBusiness_id()==business.getBusiness().getBusinessPK().getBusiness_id())
						{
							business_bean.getPhonebeanCel().setPhone(phone);
						}
					}
				}
			}
			
		}
		
		if(flagSave)
			log.info("====>>>> Phone Cel business saved successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone Cel business NO saved !!!!!!!!!!!!!!!");	
		
		if(flagUpdate)
			log.info("====>>>> Phone Cel business Update successfully!!!!!!!!!!!!!!!");
		else
			log.info("====>>>> Phone Cel business  NO Update !!!!!!!!!!!!!!!");
	}
	
/*	
	public void zipCodeFunctionAndSaveBusiness(BusinessBean business) 
	{
		RequestContext requestContext = RequestContext.getCurrentInstance();
		String cp= business.getAddressbean().getZip_code();
		log.info("CODIGO POSTAL ES= "+cp);				
		listAsents=service_employment.getAsentamientosByCP(cp);
		
		if(listAsents.size()!=0){
			
			ArrayList<ListNeighborhoodBean> catalogo= new ArrayList<ListNeighborhoodBean>();
			ListNeighborhoodBean neigt=null;
			for (NeighborhoodCat iter : listAsents) {
				neigt=new ListNeighborhoodBean(iter.getNeighborhoodCatPK().getNeighborhood_id(),iter.getName());
				catalogo.add(neigt);	
			}
			business.getAddressbean().setListNeighborhood(listAsents);
			business.getAddressbean().setTownName(listAsents.get(0).getDelegMunicipio().getName());
			business.getAddressbean().setStateName(listAsents.get(0).getDelegMunicipio().getEstados().getName());
			business.getAddressbean().setTown_id(listAsents.get(0).getDelegMunicipio().getTownCatPK().getTown_id());
			business.getAddressbean().setState_id(listAsents.get(0).getDelegMunicipio().getEstados().getStateCatPK().getState_id());
			
			requestContext.addCallbackParam("TownName", listAsents.get(0).getDelegMunicipio().getName());
			requestContext.addCallbackParam("StateName", listAsents.get(0).getDelegMunicipio().getEstados().getName());	
			if(listAsents.size()==1)
				business.getAddressbean().setNeighborhood_id(listAsents.get(0).getNeighborhoodCatPK().getNeighborhood_id());
			else
				business.getAddressbean().setNeighborhood_id(null);
			
			try {
				requestContext.addCallbackParam("neighborhood", new JSONArray(catalogo.toArray(),true).toString());
				requestContext.addCallbackParam("isValid", true);
				updateAddressBlurBusiness(business);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}else{
			requestContext.addCallbackParam("isValid", false);
			business.getAddressbean().setZip_code(null);
		}
		
		
	}
*/	
	
	private boolean isSesion_DISABLED()
	{
		boolean bandera = false;
		
		if(sesion.getProspectus_id() == null || sesion.getCompany_id() == null)
		{																										
			String url = (getPath() + "/Portal/sesion-expirada.xhtml?redirecFrom=MiPrestamoIMP");
							
			try 
			{
				System.out.println( "Redirigiendo desde NavigationBean: " + url);
				external.redirect(url);
			        
			} catch (IOException ex) {						      
				ex.printStackTrace();
			}catch(Exception e){
				System.out.println("Redirect "+url);
			}
			
			bandera = true;
		}
		
		return bandera;
	}
	
	private String getPath()
	{
		HttpServletRequest request = (HttpServletRequest) external.getRequest();
		
		return request.getContextPath();
	}
	
	
}
