package mx.com.kubo.managedbeans.registro.sobre;

import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import mx.com.kubo.bean.BusinessBean;
import mx.com.kubo.bean.EmploymentBean;
import mx.com.kubo.model.Legal_Status;
import mx.com.kubo.model.Marital_Status;
import mx.com.kubo.model.Study_Level;
import mx.com.kubo.model.catalogos.DependantsNumber;

public interface MiPrestamoIMO 
{
	void listener_estado_civil           (AjaxBehaviorEvent evento);
	void listener_regimen_conyugal       (AjaxBehaviorEvent evento);
	void listener_dependientes_economicos(AjaxBehaviorEvent evento);
	void listener_nivel_estudios         (AjaxBehaviorEvent evento);
	void listener_dependants_number      (AjaxBehaviorEvent evento);
	
	void listener_has_computer();
	void listener_has_internet();
	
	void listener_update_business_address(BusinessBean business_bean);
	void listener_update_employment_address(EmploymentBean employment);
	
	List<Legal_Status>     getLista_estado_civil();
	List<Marital_Status>   getLista_regimen_conyugal();
	List<Study_Level>      getLista_nivel_estudios();
	List<DependantsNumber> getLista_dependants_number();
}
