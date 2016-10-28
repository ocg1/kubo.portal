package mx.com.kubo.mesa.solicitud.telefonos;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.AddPhoneBean;
import mx.com.kubo.bean.ChangeBean;
import mx.com.kubo.bean.PhoneReview;
import mx.com.kubo.bean.ReferencesReview;
import mx.com.kubo.managedbeans.SessionBean;
import mx.com.kubo.model.Business;
import mx.com.kubo.model.Employment;
import mx.com.kubo.model.NaturalPerson;
import mx.com.kubo.services.Change_controlService;
import mx.com.kubo.services.NaturalPersonService;
import mx.com.kubo.services.PhoneService;
import mx.com.kubo.services.ProyectLoanService;
import mx.com.kubo.services.ReferencesService;

public interface TelefonosIMO 
{
	void setService_telefono               (PhoneService service);
	void setService_change_control(Change_controlService service);
	void setService_natural_person (NaturalPersonService service);
	void setService_proyect_loan     (ProyectLoanService service);
	void setService_references        (ReferencesService service);
	
	void setSesion(SessionBean sesion); 
	void setPerson(NaturalPerson person);
	
	void setListEmployment(List<Employment> listEmployment);
	void setListBusiness  (List<Business> listBusiness);
	
	void init();
	void init_phone (PhoneReview view);
	void init_update(PhoneReview view);
	
	void init_phone_type      (AjaxBehaviorEvent event);
	void init_phone_number    (AjaxBehaviorEvent event);
	void init_phone_extension (AjaxBehaviorEvent event);
	void init_why_change_data (AjaxBehaviorEvent event);
	
	void getListRelationPhone( String phone_str, String idShow);
	
	AddPhoneBean getNewAddPhone();
	ChangeBean   getChangephones();
	List<PhoneReview> getListPhone();
	List<ReferencesReview> getLstReferences();
}
