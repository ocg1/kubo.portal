package mx.com.kubo.mesa.solicitud.telefonos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.PersonAndPhones;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ReferencesReview;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.model.Phone;
import mx.com.kubo.model.PhonePK;
import mx.com.kubo.model.ProyectLoan;
import mx.com.kubo.model.References;
import mx.com.kubo.model.gnNaturalPersonPK;

public class TelefonosIMP extends TelefonosAMO 
implements TelefonosIMO
{
	public void init()
	{	
		changephones = new ChangeBean();
		newAddPhone  = new AddPhoneBean();
		
		lista_phone_prospectus  = service_telefono.getPhoneByProspectusList(prospectus_id, company_id);																
		lista_change_control    = service_change_control.getListByProspectByAfectedTablesFields(prospectus_id, company_id, new String[]{"gn_phone"}, new String[]{"phone_number", "phone_type_id"});
		
		boolean has_change = lista_change_control != null && lista_change_control.size() > 0 ? true : false;
		
		lista_change_control = lista_change_control != null && lista_change_control.size() > 0 ? lista_change_control : null;
				
		changephones.setHasChange (has_change);
		changephones.setLstChanges(lista_change_control);	
		
		whyChangeData = "";
		
		factory = new PhoneReviewIMP();
		factory.setService_telefono(service_telefono);
		factory.setLista_phones(lista_phone_prospectus);
		factory.setPerson(persona);
		factory.init();
		
		lista_phone_view        = factory.getLista_phone_view();
		panel_extension_ENABLED = factory.isPanel_extension_ENABLED();
		panel_lada_ENABLED      = factory.isPanel_lada_ENABLED();
		
		//init_phones();		
	}

	public void init_phone(PhoneReview view)
	{
		request = RequestContext.getCurrentInstance();
		
		Phone phone = view.getPhone();
		
		phone_type_id   = phone.getPhone_type_id() != null ? phone.getPhone_type_id() : 0;
		phone_number    = phone.getPhone_number()  != null ? phone.getPhone_number()  : "";		
		phone_extension = phone.getExtension()     != null ? phone.getExtension()     : "";
		
		request.addCallbackParam("phone_number", phone_number);
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("phone_extension", phone_extension);
	}
	
	public void init_phone_type(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		select_menu = (HtmlSelectOneMenu) event.getComponent();
		
		phone_type_id = Integer.parseInt(select_menu.getValue().toString());
		
		switch (phone_type_id) 
		{
			case 1:
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = true;
			break;
			
			case 2:
			case 4:
			case 6:
				panel_lada_ENABLED = true;
				panel_extension_ENABLED = false;
			break;
	
			default:
				panel_lada_ENABLED = false;
				panel_extension_ENABLED = false;
			break;
		}
				 		
		request.addCallbackParam("phone_type_id", phone_type_id);
		request.addCallbackParam("panel_lada_ENABLED", panel_lada_ENABLED);
		request.addCallbackParam("panel_extension_ENABLED", panel_extension_ENABLED);
	}
	
	public void init_phone_number(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		phone_number = input_text.getValue().toString();
		
		request.addCallbackParam("phone_number", phone_number);
	}
	
	public void init_phone_extension(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text = (HtmlInputText) event.getComponent();
		
		phone_extension = input_text.getValue().toString();
		
		request.addCallbackParam("phone_extension", phone_extension);
	}
	
	public void init_why_change_data(AjaxBehaviorEvent event)
	{
		request = RequestContext.getCurrentInstance();
		
		input_text_area = (HtmlInputTextarea) event.getComponent();
		
		whyChangeData = input_text_area.getValue().toString();
		
		request.addCallbackParam("whyChangeData", whyChangeData);
	}
	
	public void init_update(PhoneReview view)
	{
		request = RequestContext.getCurrentInstance();
		
		Phone phone = view.getPhone();
		
		     new_value = phone_number != null ? phone_number : "";
		original_value = phone.getPhone_number() != null ? phone.getPhone_number() : "";
		
		change_control_ENABLED = !original_value.equals(new_value);
		
		if(change_control_ENABLED)
		{
			phone.setPhone_number(phone_number);
			
			init_change_control("phone_number",  original_value, new_value, whyChangeData);
		}						
		
			  new_value = phone_type_id.toString();
		 original_value = phone.getPhone_type_id() != null ? phone.getPhone_type_id().toString() : "";
	
		change_control_ENABLED = !original_value.equals(new_value);
	
		if(change_control_ENABLED)
		{
			phone.setPhone_type_id(phone_type_id);
			
			init_change_control("phone_type_id", original_value, new_value, whyChangeData);
		}
		
		update_OK = false;
		
		if(change_control_OK)
		{									
			update_OK = service_telefono.updatePhone(phone);
		}
		
		if(update_OK)
		{
			init();
		}
		
		request.addCallbackParam("update_OK", update_OK);
	}
	
	public void addNewPhone()
	{				
		faces = FacesContext.getCurrentInstance();        
        
        phone_type_id   = newAddPhone.getPhone_type_id();
//        lada_phone    = newAddPhone.getLadaPhone();
        phone_number    = newAddPhone.getNumPhone();
        whyChangeData   = newAddPhone.getComments();
        phone_extension = newAddPhone.getExtension();
	        
        if(persona != null && phone_type_id != 0 && phone_number != null && whyChangeData != null)
        {			
        	prospectus_id = persona.getNatPerPK().getProspectus_id();
        	company_id    = persona.getNatPerPK().getCompany_id();
        	
			newPhone   = new Phone();
			newPhonePK = new PhonePK(prospectus_id, company_id);
			
			newPhone.setPhonePk(newPhonePK);
			newPhone.setOwned("1");
			newPhone.setArea(sesion.getArea());
			newPhone.setPhone_type_id(phone_type_id);
			newPhone.setPhone_number (phone_number);
			newPhone.setExtension    (phone_extension);
			
			buss   = null;
			employ = null;
				
			if (phone_type_id == 1 || phone_type_id == 2) 
			{
				employ = listEmployment.get(0);	
				
				newPhone.setEmployment_id(employ.getEmploymentPK().getEmployment_id());
				
			} else if(phone_type_id == 3 || phone_type_id == 4) {
				
				buss = listBusiness.get(0);
				
				newPhone.setBusiness_id(buss.getBusinessPK().getBusiness_id());
			}
			
			add_phone_OK = service_telefono.addPhone(newPhone, prospectus_id, company_id);
			
			if(add_phone_OK)
			{	
				init_change_control("phone_number", "", phone_number, whyChangeData);
				
				if(change_control_OK)
				{							
					newAddPhone = new AddPhoneBean();
					
					init();
				}	
			}			
		}
	}

	public void saveEditPhone(PhoneReview phone)
	{		
		new_value = phone.getNumPhone();		
	        
        if(persona != null && whyChangeData != null)
        {					
			original_value = phone.getPhone().getPhone_number();
			
			original_value = original_value != null ? original_value : "";
			
			phone.getPhone().setPhone_number(new_value);			
			phone.getPhone().setPhone_type_id(phone_type_id);
			
			update_OK = service_telefono.updatePhone(phone.getPhone());
			
			init_change_control("phone_number", original_value, new_value, whyChangeData);
			
			if(change_control_OK)
			{
				whyChangeData = null;
				
				init();
				
				request = RequestContext.getCurrentInstance();
				request.addPartialUpdateTarget("updateSectionPhone");
			}
		}	       
	}
	
	public void getListRelationPhone( String phone_str,String idShow){
		
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		//Obtener toda la lista de telefonos
		List<Phone> listAllPhone = service_telefono.loadAllPhones();	
		List<PersonAndPhones> returList=new ArrayList<PersonAndPhones>();
		
		//recorrer primer nivel
		if(lstPersonPhone==null ||  !(lstPersonPhone.get(0).getPhoneRepeat().get(0).getPhone_number()) .equals( phone_str ) )
		{
			cargaTelefonosOtherPerson(   phone_str);
		}
		
		for (PersonAndPhones personAndPhones : lstPersonPhone) 
		{
			//Obtener telefonos de cada persona del primer nivel
			List<Phone> lstPhoneTemp=service_telefono.getPhoneByProspectusList(personAndPhones.getPerson().getNatPerPK().getProspectus_id(), personAndPhones.getPerson().getNatPerPK().getCompany_id());						
			personAndPhones.setOtherPersonPhone(new ArrayList<PersonAndPhones>());						
			//Recorrer la lista de telefonos del actual prospectus
			
			for(Phone phone: lstPhoneTemp)
			{
				for(Phone allphone : listAllPhone)
				{
					String phoneA = 	   phone.getPhone_number().replace(" ", "").replace("-", "").replace(".", "");
					String phoneB=  allphone.getPhone_number().replace(" ", "").replace("-", "").replace(".", "");
					
					if(phoneA.equals(phoneB) && !( phone.getPhonePk().getProspectus_id() == allphone.getPhonePk().getProspectus_id() ) && !( persona.getNatPerPK().getProspectus_id() == allphone.getPhonePk().getProspectus_id() ) && !phoneA.trim().equals("") )
					{
						PersonAndPhones perPhone = null;
						//Existe persona ya en la lista.
						for(PersonAndPhones personPhoneA : personAndPhones.getOtherPersonPhone())
						{
							if((personPhoneA.getPerson().getNatPerPK().getCompany_id() == allphone.getPhonePk().getCompany_id()) && (personPhoneA.getPerson().getNatPerPK().getProspectus_id() == allphone.getPhonePk().getProspectus_id()))
							{
								perPhone=personPhoneA;
							}
						}
						
						if(perPhone == null)
						{
							ProyectLoan proyectLoan= service_proyect_loan.getMaxProyectLoanByProspect(allphone.getPhonePk().getProspectus_id(), allphone.getPhonePk().getCompany_id());														
							gnNaturalPersonPK pk = new gnNaturalPersonPK();
							pk.setCompany_id(allphone.getPhonePk().getCompany_id());
							pk.setProspectus_id(allphone.getPhonePk().getProspectus_id());
							
							perPhone =  new PersonAndPhones();
							perPhone.setProyectloan(proyectLoan);
							perPhone.setPhoneRepeat(new ArrayList<Phone>());
							perPhone.setPerson(service_natural_person.getNaturalPersonById(pk));							
							perPhone.getPhoneRepeat().add(allphone);
							personAndPhones.getOtherPersonPhone().add(perPhone);
							
						}else{							
							for(PersonAndPhones personPhoneB : personAndPhones.getOtherPersonPhone())
							{
								if((personPhoneB.getPerson().getNatPerPK().getCompany_id() == allphone.getPhonePk().getCompany_id()) && (personPhoneB.getPerson().getNatPerPK().getProspectus_id() == allphone.getPhonePk().getProspectus_id()))
								{
									boolean flg = false;
									
									for(Phone phoneRep : personPhoneB.getPhoneRepeat())
									{
										if(allphone.getPhone_type_id() == phoneRep.getPhone_type_id() )
										{
											flg = true;
										}
									}
									
									if(!flg)
									{
										personPhoneB.getPhoneRepeat().add(allphone);
									}
									break;
								}
							}
							
							
						}
					}
					
				}
			}
			returList.add(personAndPhones);
		}
		
		Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		
		//System.out.println(" tardó "+difmilis+" milisegundos");
		
		try 
		{
			//System.out.println("Lista: "+returList.size());
			requestContext.addCallbackParam("personPhone", new JSONArray(returList.toArray(),true).toString());
			requestContext.addCallbackParam("appendhere", "appendhere"+idShow);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public final void cargaTelefonosOtherPerson( String phone_str)
	{				
		ProyectLoan proyectLoan;
		PersonAndPhones perPhone;
		gnNaturalPersonPK pk;
		
		if( phone_str != null && phone_str.trim().length() > 0  )
		{
		
			lstPhoneTmp = service_telefono.getPhoneListByNumber( phone_str, persona.getNatPerPK().getProspectus_id() ,persona.getNatPerPK().getCompany_id() );
		
		}else{
			
			lstPhoneTmp = new ArrayList<Phone>();
			
		}
		
		lstPersonPhone = new ArrayList<PersonAndPhones>();
				
		for( Phone p001 : lstPhoneTmp)
		{						
			proyectLoan =  service_proyect_loan.getMaxProyectLoanByProspect(p001.getPhonePk().getProspectus_id(), p001.getPhonePk().getCompany_id());
			
			pk = new gnNaturalPersonPK();
			pk.setCompany_id(p001.getPhonePk().getCompany_id());
			pk.setProspectus_id(p001.getPhonePk().getProspectus_id());
			
			perPhone =  new PersonAndPhones();
			perPhone.setProyectloan(proyectLoan);
			perPhone.setPhoneRepeat(new ArrayList<Phone>());
			perPhone.setPerson(service_natural_person.getNaturalPersonById(pk));
			
			perPhone.getPhoneRepeat().add(p001);
			
			lstPersonPhone.add(perPhone);		
		}	
	}
	
	public void cargaTelefonosReferencias(String phone_str)
	{		
		List<References> lstReferencesTmp = service_references.loadReferencesListByNumber(phone_str, persona.getNatPerPK().getProspectus_id() ,persona.getNatPerPK().getCompany_id() );
		
		lstReferences = new ArrayList<ReferencesReview>();
				
		for(References r001 : lstReferencesTmp)
		{
			if(r001.getPhone() !=null)
			{
				
				NaturalPerson person;
				
				gnNaturalPersonPK key = new gnNaturalPersonPK(r001.getReferencePK().getProspectus_id(), r001.getReferencePK().getCompany_id()); 
				person = service_natural_person.getNaturalPersonById(key);
				
				
				ReferencesReview ref =  new ReferencesReview();
				
				ref.setNombreCliente(person.NombreCompletoNPM());
				ref.setTracking_id(person.getProspectus().getTracking_id());
				
				ProyectLoan loan  = service_proyect_loan.getMaxProyectLoanByProspect(r001.getReferencePK().getProspectus_id(), r001.getReferencePK().getCompany_id());
				
						if(loan != null){
							
							ref.setStatus(loan.getStatusProyect().getName());
							ref.setMontoSolicitado(num.format(loan.getAmmount()));
						
						}else{
							
							ref.setStatus("No publicado");
							ref.setMontoSolicitado("No publicado");
							
						}
				
				ref.setEmail(r001.getEmail());
				
				String nombreReferencia = "";
				
				if(r001.getFirst_name()!=null && !r001.getFirst_name().replace(" ", "").equals(""))
					nombreReferencia += r001.getFirst_name();
				if(r001.getMiddle_name()!=null && !r001.getMiddle_name().replace(" ", "").equals(""))
					nombreReferencia += " "+r001.getMiddle_name();
				if(r001.getFather_last_name()!=null && !r001.getFather_last_name().replace(" ", "").equals(""))
					nombreReferencia += " "+r001.getFather_last_name();
				if(r001.getMother_last_name()!=null && !r001.getMother_last_name().replace(" ", "").equals(""))
					nombreReferencia += " "+r001.getMother_last_name();
				
				ref.setNombreReferencia(nombreReferencia);
				ref.setTelefonoReferencia(r001.getPhone());
				
				lstReferences.add(ref);
				
			}
		}
		
	}
}
